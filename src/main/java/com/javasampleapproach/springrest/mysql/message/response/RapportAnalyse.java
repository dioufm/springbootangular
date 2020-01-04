package com.javasampleapproach.springrest.mysql.message.response;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.javasampleapproach.springrest.mysql.model.Word;
import com.javasampleapproach.springrest.mysql.model.rule.RuleMessages;

public class RapportAnalyse {

	// url du site
	private String urlwebsite;

	// domaine du site
	private String domaine;

	private Date dateReport;

	// desktop image
	private String imageDesktop;

	// mobile image
	private String imageResponsive;

	// titre du site
	private String title;

	// description du site
	private String description;

	// vue google titre et description
	private String googleViewTitleDescription;

	// note globale calculé en fonction des régles
	private String noteGlobal;

	// note global Desktop
	private String noteGlobalDesktop;

	// note global Desktop
	private String noteGlobalResponsive;

	// list of rules
	private List<MessageSeo> listOfRules = new ArrayList<>();

	// list of rules in succes
	private List<MessageSeo> listOfSuccessRules = new ArrayList<>();

	// list of rules in warning
	private List<MessageSeo> listOfWarningRules = new ArrayList<>();

	// list of rules in error
	private List<MessageSeo> listOfErrorRules = new ArrayList<>();

	// map titre => libelle Ex. h1 ==> 'plombier..."
	private List<TagAnalyse> listTagAnalyse = new ArrayList<>();

	// tag rule
	private RuleMessages tagRuleMessage;

	// word occurence rule
	private RuleMessages wordOccurenceMessage;

	// word occurence rule
	private RuleMessages pageMessage;

	private RuleMessages siteMapMessage;

	// score

	private long nbRuleSucces;

	private long nbRuleWarning;

	private long nbRuleError;

	private Float percentNbRuleSucces;

	private Float percentNbRuleWarning;

	private Float percentNbRuleError;

	private Float score;

	private long nbRules;

	private Set<Word> words = new HashSet<Word>();

	private Set<PageInfo> pagesList = new HashSet<PageInfo>();

	private Set<PageInfo> pagesListShort = new HashSet<PageInfo>();

	// is site Map present

	private boolean siteMap;

	private boolean robot;

	private Set<String> listSiteMapUrl = new HashSet<String>();

	public String getUrlwebsite() {
		return urlwebsite;
	}

	public void setUrlwebsite(String urlwebsite) {
		this.urlwebsite = urlwebsite;
	}

	public String getImageDesktop() {
		return imageDesktop;
	}

	public void setImageDesktop(String imageDesktop) {
		this.imageDesktop = imageDesktop;
	}

	public String getImageResponsive() {
		return imageResponsive;
	}

	public void setImageResponsive(String imageResponsive) {
		this.imageResponsive = imageResponsive;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getGoogleViewTitleDescription() {
		return googleViewTitleDescription;
	}

	public void setGoogleViewTitleDescription(String googleViewTitleDescription) {
		this.googleViewTitleDescription = googleViewTitleDescription;
	}

	public String getNoteGlobal() {
		return noteGlobal;
	}

	public void setNoteGlobal(String noteGlobal) {
		this.noteGlobal = noteGlobal;
	}

	public String getNoteGlobalDesktop() {
		return noteGlobalDesktop;
	}

	public void setNoteGlobalDesktop(String noteGlobalDesktop) {
		this.noteGlobalDesktop = noteGlobalDesktop;
	}

	public String getNoteGlobalResponsive() {
		return noteGlobalResponsive;
	}

	public void setNoteGlobalResponsive(String noteGlobalResponsive) {
		this.noteGlobalResponsive = noteGlobalResponsive;
	}

	public List<MessageSeo> getListOfSuccessRules() {
		return listOfSuccessRules;
	}

	public void setListOfSuccessRules(List<MessageSeo> listOfSuccessRules) {
		this.listOfSuccessRules = listOfSuccessRules;
	}

	public List<MessageSeo> getListOfWarningRules() {
		return listOfWarningRules;
	}

	public void setListOfWarningRules(List<MessageSeo> listOfWarningRules) {
		this.listOfWarningRules = listOfWarningRules;
	}

	public List<MessageSeo> getListOfErrorRules() {
		return listOfErrorRules;
	}

	public void setListOfErrorRules(List<MessageSeo> listOfErrorRules) {
		this.listOfErrorRules = listOfErrorRules;
	}

	public List<TagAnalyse> getListTagAnalyse() {
		return listTagAnalyse;
	}

	public void setListTagAnalyse(List<TagAnalyse> listTagAnalyse) {
		this.listTagAnalyse = listTagAnalyse;
	}

	public List<MessageSeo> getListOfRules() {
		return listOfRules;
	}

	public void setListOfRules(List<MessageSeo> listOfRules) {
		this.listOfRules = listOfRules;
	}

	public Date getDateReport() {
		return dateReport;
	}

	public void setDateReport(Date dateReport) {
		this.dateReport = dateReport;
	}

	public long getNbRuleSucces() {
		return nbRuleSucces;
	}

	public void setNbRuleSucces(long nbRuleSucces) {
		this.nbRuleSucces = nbRuleSucces;
	}

	public long getNbRuleWarning() {
		return nbRuleWarning;
	}

	public void setNbRuleWarning(long nbRuleWarning) {
		this.nbRuleWarning = nbRuleWarning;
	}

	public long getNbRuleError() {
		return nbRuleError;
	}

	public void setNbRuleError(long nbRuleError) {
		this.nbRuleError = nbRuleError;
	}

	public Float getPercentNbRuleSucces() {
		return percentNbRuleSucces;
	}

	public void setPercentNbRuleSucces(Float percentNbRuleSucces) {
		this.percentNbRuleSucces = percentNbRuleSucces;
	}

	public Float getPercentNbRuleWarning() {
		return percentNbRuleWarning;
	}

	public void setPercentNbRuleWarning(Float percentNbRuleWarning) {
		this.percentNbRuleWarning = percentNbRuleWarning;
	}

	public Float getPercentNbRuleError() {
		return percentNbRuleError;
	}

	public void setPercentNbRuleError(Float percentNbRuleError) {
		this.percentNbRuleError = percentNbRuleError;
	}

	public Float getScore() {
		return score;
	}

	public void setScore(Float score) {
		this.score = score;
	}

	public long getNbRules() {
		return nbRules;
	}

	public void setNbRules(long nbRules) {
		this.nbRules = nbRules;
	}

	public Set<Word> getWords() {
		return words;
	}

	public void setWords(Set<Word> words) {
		this.words = words;
	}

	public Set<PageInfo> getPagesList() {
		return pagesList;
	}

	public void setPagesList(Set<PageInfo> pagesList) {
		this.pagesList = pagesList;
	}

	public Set<PageInfo> getPagesListShort() {
		return pagesListShort;
	}

	public void setPagesListShort(Set<PageInfo> pagesListShort) {
		this.pagesListShort = pagesListShort;
	}

	public boolean isSiteMap() {
		return siteMap;
	}

	public void setSiteMap(boolean siteMap) {
		this.siteMap = siteMap;
	}

	public boolean isRobot() {
		return robot;
	}

	public void setRobot(boolean robot) {
		this.robot = robot;
	}

	public Set<String> getListSiteMapUrl() {
		return listSiteMapUrl;
	}

	public void setListSiteMapUrl(Set<String> listSiteMapUrl) {
		this.listSiteMapUrl = listSiteMapUrl;
	}

	public RuleMessages getTagRuleMessage() {
		return tagRuleMessage;
	}

	public void setTagRuleMessage(RuleMessages tagRuleMessage) {
		this.tagRuleMessage = tagRuleMessage;
	}

	public RuleMessages getWordOccurenceMessage() {
		return wordOccurenceMessage;
	}

	public void setWordOccurenceMessage(RuleMessages wordOccurenceMessage) {
		this.wordOccurenceMessage = wordOccurenceMessage;
	}

	public RuleMessages getPageMessage() {
		return pageMessage;
	}

	public void setPageMessage(RuleMessages pageMessage) {
		this.pageMessage = pageMessage;
	}

	public RuleMessages getSiteMapMessage() {
		return siteMapMessage;
	}

	public void setSiteMapMessage(RuleMessages siteMapMessage) {
		this.siteMapMessage = siteMapMessage;
	}

	public String getDomaine() {
		return domaine;
	}

	public void setDomaine(String domaine) {
		this.domaine = domaine;
	}

}
