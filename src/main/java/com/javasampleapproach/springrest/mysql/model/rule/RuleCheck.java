package com.javasampleapproach.springrest.mysql.model.rule;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import com.javasampleapproach.springrest.mysql.message.response.PageInfo;
import com.javasampleapproach.springrest.mysql.message.response.TagAnalyse;
import com.javasampleapproach.springrest.mysql.model.Word;

public class RuleCheck {

	public static RuleMessages checkRule(TagEnum ruleEnum, Object value) {
		RuleMessages message = new RuleMessages("", "", "", "");
		switch (ruleEnum) {
		case TITLE: {
			String valueString = (String) value;
			// deffault OK
			message.setMessage(RuleMessages.MESSAGE_SEO_TITLE_OK);
			message.setCodeError(RuleMessages.RULE_SUCCESS);
			// ref https://support.google.com/webmasters/answer/35624?hl=fr
			message.setReferenceRule("https://support.google.com/webmasters/answer/35624?hl=fr");

			// 1- Comme indiqué plus haut, assurez-vous que chaque page de votre site
			// comporte un titre dans la balise <title>.
			if (valueString.isEmpty()) {
				message.setMessage(RuleMessages.MESSAGE_SEO_TITLE_EMPTY);
				message.setCodeError(RuleMessages.RULE_ERROR);
				break;
			}

			/**
			 * 2- SOMMAIRE Les titres de page doivent être descriptifs et concis. Évitez les
			 * descripteurs vagues du type Accueil pour décrire votre page d'accueil ou bien
			 * Profil pour faire référence au profil particulier d'une personne. Évitez
			 * également les titres inutilement longs ou trop détaillés, car ils sont
			 * susceptibles d'être tronqués sur la page des résultats de recherche
			 */
			if (!valueString.isEmpty()) {
				List<String> list = Arrays.asList("Accueil", "Bienvenue", "Wordpress", "Bonjour", "Mon site", "site");
				boolean match = list.stream().anyMatch(valueString::contains);
				if (match) {
					message.setMessage(RuleMessages.MESSAGE_SEO_TITLE_SOMMAIRE);
					message.setCodeError(RuleMessages.RULE_WARNING);
					message.setKeyWordInError("Accueil, Bienvenue, Wordpress,Bonjour, Mon site, site");
					break;
				}
			}
			/**
			 * 3-SPAM Évitez l'accumulation de mots clés dans vos titres. Il peut s'avérer
			 * utile d'avoir quelques termes descriptifs dans un titre, mais il ne sert à
			 * rien de faire figurer les mêmes mots ou les mêmes expressions plusieurs fois.
			 * Un titre comme trucbidule, truc bidule, trucbidules, trucs bidules n'aide pas
			 * l'internaute, et ce type d'accumulation de mots clés peut donner l'impression
			 * à ce dernier ainsi qu'à Google que votre site contient du spam.
			 */
			String[] valueArray = valueString.split(" ");
			int count = 0;
			for (String word : valueArray) {
				if (valueString.indexOf(word) != -1) {
					count++;
					if (count > 2) {
						message.setMessage(RuleMessages.MESSAGE_SEO_TITLE_SPAM);
						message.setCodeError(RuleMessages.RULE_ERROR);
						message.setKeyWordInError(word);
					}
					break;
				}
			}

			/**
			 * 4- titre unique par page :TODO
			 */

			/**
			 * 5- longueur
			 * 
			 */
			if (valueString.length() > RuleMessages.LENGTH_MAX_TITLE_SEO) {
				message.setMessage(RuleMessages.MESSAGE_SEO_TITLE_LENGTH);
				message.setCodeError(RuleMessages.RULE_WARNING);
				break;
			}
			break;
		}
		case DESCRIPTION: {
			String valueString = (String) value;
			message.setMessage(RuleMessages.MESSAGE_SEO_DESCRIPTION_OK);
			message.setCodeError(RuleMessages.RULE_SUCCESS);
			message.setReferenceRule("https://support.google.com/webmasters/answer/35624?hl=fr");

			/**
			 * 1 : description presente
			 */
			// 1- Comme indiqué plus haut, assurez-vous que chaque page de votre site
			// comporte un titre dans la balise <title>.
			if (valueString.isEmpty()) {
				message.setMessage(RuleMessages.MESSAGE_SEO_DESCRIPTION_EMPTY);
				message.setCodeError(RuleMessages.RULE_ERROR);
				break;
			}

			/**
			 * longueur
			 */
			if (valueString.length() > RuleMessages.LENGTH_MAX_DESCRIPTION_SEO) {
				message.setMessage(RuleMessages.MESSAGE_SEO_DESCRIPTION_LENGTH);
				message.setCodeError(RuleMessages.RULE_WARNING);
				break;
			}
			break;
		}
		case TAG: {
			List<TagAnalyse> listTags = (List<TagAnalyse>) value;

			message.setMessage(RuleMessages.MESSAGE_SEO_TAG_OK);
			message.setCodeError(RuleMessages.RULE_SUCCESS);
			message.setReferenceRule("");

			/**
			 * 1 : presence tag h1, h2, h...6
			 */
			// 1- Comme indiqué plus haut, assurez-vous que chaque page de votre site
			// comporte un titre dans la balise <title>.
			if (listTags == null || (listTags != null && listTags.isEmpty())) {
				message.setMessage(RuleMessages.MESSAGE_SEO_TAG_OK_EMPTY);
				message.setCodeError(RuleMessages.RULE_ERROR);
				break;
			}
			break;
		}
		case WORD_OCCURENCE: {
			Set<Word> words = (Set<Word>) value;

			message.setMessage(RuleMessages.MESSAGE_SEO_WORD_OCCURENCE_OK);
			message.setCodeError(RuleMessages.RULE_SUCCESS);
			message.setReferenceRule("");

			/**
			 * 1 : presence tag h1, h2, h...6
			 */
			// 1- Comme indiqué plus haut, assurez-vous que chaque page de votre site
			// comporte un titre dans la balise <title>.
			if (words == null || (words != null && words.isEmpty())) {
				message.setMessage(RuleMessages.MESSAGE_SEO_WORD_OCCURENCE_EMPTY);
				message.setCodeError(RuleMessages.RULE_ERROR);
				break;
			}
			break;
		}
		case PAGES: {
			Set<PageInfo> pages = (Set<PageInfo>) value;

			message.setMessage(RuleMessages.MESSAGE_SEO_PAGES_OK);
			message.setCodeError(RuleMessages.RULE_SUCCESS);
			message.setReferenceRule("");

			// 1- Comme indiqué plus haut, assurez-vous que chaque page de votre site
			// comporte un titre dans la balise <title>.
			if (pages == null || (pages != null && pages.isEmpty())) {
				message.setMessage(RuleMessages.MESSAGE_SEO_PAGES_EMPTY);
				message.setCodeError(RuleMessages.RULE_ERROR);
				break;
			}
			break;
		}
		case SITE_MAP: {
			Set<String> urls = (Set<String>) value;

			message.setMessage(RuleMessages.MESSAGE_SEO_SITE_MAP_OK);
			message.setCodeError(RuleMessages.RULE_SUCCESS);
			message.setReferenceRule("");

			// 1- Comme indiqué plus haut, assurez-vous que chaque page de votre site
			// comporte un titre dans la balise <title>.
			if (urls == null || (urls != null && urls.isEmpty())) {
				message.setMessage(RuleMessages.MESSAGE_SEO_SITE_MAP_EMPTY);
				message.setCodeError(RuleMessages.RULE_ERROR);
				break;
			}
			break;
		}

		default:
			message.setMessage("erreur inconnue");
			message.setCodeError("INCONNU");
			break;
		}

		return message;
	}
}
