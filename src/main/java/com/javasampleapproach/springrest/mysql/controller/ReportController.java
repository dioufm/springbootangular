package com.javasampleapproach.springrest.mysql.controller;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javasampleapproach.springrest.mysql.message.response.MessageSeo;
import com.javasampleapproach.springrest.mysql.message.response.PageInfo;
import com.javasampleapproach.springrest.mysql.message.response.RapportAnalyse;
import com.javasampleapproach.springrest.mysql.message.response.TagAnalyse;
import com.javasampleapproach.springrest.mysql.model.Word;
import com.javasampleapproach.springrest.mysql.model.rule.RuleCheck;
import com.javasampleapproach.springrest.mysql.model.rule.RuleMessages;
import com.javasampleapproach.springrest.mysql.model.rule.TagEnum;
import com.javasampleapproach.springrest.mysql.repo.RoleRepository;
import com.javasampleapproach.springrest.mysql.repo.UserRepository;
import com.javasampleapproach.springrest.mysql.security.jwt.JwtProvider;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/report")
public class ReportController {

	Logger logger = LoggerFactory.getLogger(ReportController.class);

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtProvider jwtProvider;

	@PostMapping("/www")
	public ResponseEntity<?> getReport(@Valid @RequestBody String url) {

		logger.debug("start getReport for url = {}", url);

		RapportAnalyse rapportAnalyse = new RapportAnalyse();
		Document doc = new Document(url);
		try {
			doc = Jsoup.connect(url).get();
			doc.charset(Charset.forName("ISO-8859-1"));
			rapportAnalyse.setDomaine(doc.baseUri());
			// html render
			Element element = doc.head().select("link[href~=.*\\.(ico|png)]").first();
			if (element != null && element.attr("href") != null && element.attr("href").contains("http")) {
				rapportAnalyse.setImageDesktop(element.attr("href"));
			}
			rapportAnalyse.setUrlwebsite(url);
			rapportAnalyse.setDateReport(new Date());

			String titre = doc == null ? "null" : doc.title();
			rapportAnalyse.setTitle(titre);
			// checkRule
			RuleMessages ruleMessagesTitle = RuleCheck.checkRule(TagEnum.TITLE, titre);

			String labelError = "Longueur : " + titre.length();
			String googleView = "";
			MessageSeo messageTitre = new MessageSeo(TagEnum.TITLE.name(), titre, ruleMessagesTitle.getCodeError(),
					ruleMessagesTitle.getReferenceRule(), ruleMessagesTitle.getKeyWordInError(), "Balise Titre",
					ruleMessagesTitle.getMessage() + "<br> plus d'infos voir <a href="
							+ ruleMessagesTitle.getReferenceRule()
							+ ">Créer des titres et des extraits de qualité dans les résultats de recherche</a>",
					labelError, googleView, 1);
			rapportAnalyse.getListOfRules().add(messageTitre);

			// description
			Elements descriptionSelect = doc.select("meta[name=description]");
			String description = "";
			if (descriptionSelect != null && descriptionSelect.size() > 0) {
				description = descriptionSelect.get(0).attr("content");
			}
			rapportAnalyse.setDescription(description);
			// rules check
			RuleMessages ruleMessagesDescript = RuleCheck.checkRule(TagEnum.DESCRIPTION, titre);

			String labelDescError = "Longueur : " + description.length();
			MessageSeo messageDescription = new MessageSeo(TagEnum.DESCRIPTION.name(), description,
					ruleMessagesDescript.getCodeError(), ruleMessagesDescript.getReferenceRule(),
					ruleMessagesDescript.getKeyWordInError(), "Meta Description", ruleMessagesDescript.getMessage(),
					labelDescError, googleView, 2);
			rapportAnalyse.getListOfRules().add(messageDescription);

			// titres
			List<String> list = Arrays.asList("h1", "h2", "h3", "h4", "h5", "h6", "span");

			for (String tag : list) {
				TagAnalyse tagAnalyse = new TagAnalyse();
				Elements tags = doc.getElementsByTag(tag);

				tags.forEach((tg) -> {
					if (!StringUtils.isEmpty(tg.text())) {
						tagAnalyse.getListTag().add(tg.text());
					}
				});
				tagAnalyse.getShortListTag()
						.addAll(tagAnalyse.getListTag().stream().limit(3).collect(Collectors.toList()));

				tagAnalyse.setTagName(tag);
				tagAnalyse.setCount(tagAnalyse.getListTag().size());

				rapportAnalyse.getListTagAnalyse().add(tagAnalyse);

			}
			// check Tag rule
			RuleMessages tagMessageSeo = RuleCheck.checkRule(TagEnum.TAG, rapportAnalyse.getListTagAnalyse());
			rapportAnalyse.setTagRuleMessage(tagMessageSeo);
			rapportAnalyse.getListOfRules().add(new MessageSeo(TagEnum.TAG.name(), "", tagMessageSeo.getCodeError(), "",
					"", "Balises", "Balises", "Balises", "", 3));

			// occurence word
			String text = doc.body().text();
			Map<String, Word> countMap = new HashMap<String, Word>();
			// Create BufferedReader so the words can be counted
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(new ByteArrayInputStream(text.getBytes(StandardCharsets.UTF_8))));
			String line;
			while ((line = reader.readLine()) != null) {
				String[] words = line.split("[^A-ZÃƒâ€¦Ãƒâ€žÃƒâ€“a-zÃƒÂ¥ÃƒÂ¤ÃƒÂ¶]+");
				for (String word : words) {
					if ("".equals(word)) {
						continue;
					}

					Word wordObj = countMap.get(word);
					if (wordObj == null) {
						wordObj = new Word();
						wordObj.word = word;
						wordObj.count = 0;
						countMap.put(word, wordObj);
					}

					wordObj.count++;
				}
			}

			reader.close();

			for (String wordString : countMap.keySet()) {
				if (wordString.length() > 5 && countMap.get(wordString).count > 5) {
					rapportAnalyse.getWords().add(countMap.get(wordString));
				}
			}

			// check occurence rule
			RuleMessages wordOccurenceMessageSeo = RuleCheck.checkRule(TagEnum.WORD_OCCURENCE,
					rapportAnalyse.getWords());
			rapportAnalyse.setWordOccurenceMessage(wordOccurenceMessageSeo);
			rapportAnalyse.getListOfRules()
					.add(new MessageSeo(TagEnum.WORD_OCCURENCE.name(), "", wordOccurenceMessageSeo.getCodeError(), "",
							"", "Analyses de mots", "Analyses de mots", "Analyses de mots", "", 4));

			/**
			 * pages link
			 */
			Set<PageInfo> uniqueURL = new HashSet<PageInfo>();
			Elements links = doc.select("a");

			if (!links.isEmpty()) {
				// links.stream().map((link) -> link.attr("abs:href")).forEachOrdered((this_url)
				// -> {
				links.stream().forEach(link -> {
					String linkHref = link.attr("abs:href");
					String linkText = link.text();
					PageInfo pageInfo = null;
					if (linkHref.contains(url)) {
						if (linkHref != null && linkText != null) {
							pageInfo = new PageInfo(linkHref, linkText, "interne");
						}
					} else {
						if (linkHref != null && linkText != null) {
							pageInfo = new PageInfo(linkHref, linkText, "externe");
						}
					}
					if (pageInfo != null) {
						uniqueURL.add(pageInfo);
					}
				});
			}
			rapportAnalyse.setPagesList(uniqueURL);
			rapportAnalyse.setPagesListShort(uniqueURL.stream().limit(5).collect(Collectors.toSet()));

			// check page
			RuleMessages pageMessageSeo = RuleCheck.checkRule(TagEnum.PAGES, rapportAnalyse.getPagesList());
			rapportAnalyse.setPageMessage(pageMessageSeo);
			rapportAnalyse.getListOfRules()
					.add(new MessageSeo(TagEnum.PAGES.name(), "", wordOccurenceMessageSeo.getCodeError(), "", "",
							"Analyse des pages", "Analyse des pages", "Analyse des pages", "", 5));

			/**
			 * meta données
			 */
			// sitemap.txt
			Document docSiteMap = null;
			try {
				docSiteMap = Jsoup.connect(doc.baseUri() + "/sitemap.xml").get();
			} catch (IOException e) {
				logger.debug("getsiteMap  for url = {}", url);
				logger.error("getsiteMap for url = {}", url, e);
			}

			Set<String> locs = new HashSet<String>();
			if (docSiteMap != null) {
				rapportAnalyse.setSiteMap(true);
				Elements urlsLoc = docSiteMap.select("url");
				urlsLoc.stream().forEach(urlLoc -> {
					Elements loc = urlLoc.select("loc");
					locs.add(loc.text());
				});
				rapportAnalyse.setListSiteMapUrl(locs);
			} else {
				rapportAnalyse.setSiteMap(false);
			}

			// check page
			RuleMessages siteMapMessage = RuleCheck.checkRule(TagEnum.SITE_MAP, rapportAnalyse.getListSiteMapUrl());
			rapportAnalyse.setSiteMapMessage(siteMapMessage);
			rapportAnalyse.getListOfRules().add(new MessageSeo(TagEnum.SITE_MAP.name(), "",
					siteMapMessage.getCodeError(), "", "", "Site Map", "Site Map", "Site Map", "", 5));

			// robot.txt
			Document docRobots = null;
			try {
				docRobots = Jsoup.connect(doc.baseUri() + "/robots.txt").get();
			} catch (IOException e) {
				logger.debug("getdocRobots  for url = {}", url);
				logger.error("getdocRobots for url = {}", url, e);
			}
			if (docRobots != null) {
				rapportAnalyse.setRobot(true);
			} else {
				rapportAnalyse.setRobot(false);
			}

			// calcul score /100
			long nbRuleSucces = rapportAnalyse.getListOfRules().stream()
					.filter(rule -> RuleMessages.RULE_SUCCESS.equals(rule.getCodeError())).count();
			rapportAnalyse.setNbRuleSucces(nbRuleSucces);

			long nbRuleWarning = rapportAnalyse.getListOfRules().stream()
					.filter(rule -> RuleMessages.RULE_WARNING.equals(rule.getCodeError())).count();
			rapportAnalyse.setNbRuleWarning(nbRuleWarning);

			long nbRuleError = rapportAnalyse.getListOfRules().stream()
					.filter(rule -> RuleMessages.RULE_ERROR.equals(rule.getCodeError())).count();
			rapportAnalyse.setNbRuleError(nbRuleError);

			long nbRules = nbRuleSucces + nbRuleWarning + nbRuleError;

			rapportAnalyse.setNbRules(nbRules);

			float percentNbRuleSucces = (float) (((float) nbRuleSucces / (float) nbRules) * 100);
			rapportAnalyse.setPercentNbRuleSucces(percentNbRuleSucces);

			float percentNbRuleWarning = (float) (((float) nbRuleWarning / (float) nbRules) * 100);
			rapportAnalyse.setPercentNbRuleWarning(percentNbRuleWarning);

			float percentNbRuleError = (float) (((float) nbRuleError / (float) nbRules) * 100);
			rapportAnalyse.setPercentNbRuleError(percentNbRuleError);

			float score = (float) (100 * (float) nbRules)
					/ (float) ((float) nbRuleSucces + (float) 3 * nbRuleWarning + (float) 6 * nbRuleError);
			rapportAnalyse.setScore(score);

		} catch (IOException e) {
			logger.debug("getReport for url = {}", url);
			logger.error("getReport for url = {}", url, e);
		}
		return new ResponseEntity<>(rapportAnalyse, HttpStatus.OK);
	}

}