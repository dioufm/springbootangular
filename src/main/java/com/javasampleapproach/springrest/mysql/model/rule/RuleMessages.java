package com.javasampleapproach.springrest.mysql.model.rule;

public class RuleMessages {

	private String message;

	private String codeError;

	private String referenceRule;

	private String keyWordInError;

	public static String RULE_WARNING = "WARNING";
	public static String RULE_ERROR = "ERROR";
	public static String RULE_SUCCESS = "SUCCESS";

	public static int LENGTH_MAX_TITLE_SEO = 70;// https://www.webrankinfo.com/dossiers/conseils/taille-maximale-balise-title

	// 1
	public static String MESSAGE_SEO_TITLE_EMPTY = "Assurez-vous que chaque page de votre site comporte un titre dans la balise <title>.";

	// 2 titre sommaire ininteressant
	public static String MESSAGE_SEO_TITLE_SOMMAIRE = "Les titres de page doivent être descriptifs et concis. Évitez les descripteurs vagues du type Accueil pour décrire votre page d'accueil ou bien Profil pour faire référence au profil particulier d'une personne. Évitez également les titres inutilement longs ou trop détaillés, car ils sont susceptibles d'être tronqués sur la page des résultats de recherche. Par experience limiter vous à 70 caractères au maximum.";

	// 3 spam mot clés trop présent
	public static String MESSAGE_SEO_TITLE_SPAM = "Évitez l'accumulation de mots clés dans vos titres. Il peut s'avérer utile d'avoir quelques termes descriptifs dans un titre, mais il ne sert à rien de faire figurer les mêmes mots ou les mêmes expressions plusieurs fois. Un titre comme trucbidule, truc bidule, trucbidules, trucs bidules n'aide pas l'internaute, et ce type d'accumulation de mots clés peut donner l'impression à ce dernier ainsi qu'à Google que votre site contient du spam.";

	public static String MESSAGE_SEO_TITLE_LENGTH = " Évitez également les titres inutilement longs ou trop détaillés, car ils sont susceptibles d'être tronqués sur la page des résultats de recherche.";
	public static String MESSAGE_SEO_TITLE_OK = "La longueur de la balise <strong>Title</strong> est parfaite. Bravo!";

	// https://www.redacteur.com/blog/erreurs-ecrire-meta-description/
	public static int LENGTH_MAX_DESCRIPTION_SEO = 160;
	// 1
	public static String MESSAGE_SEO_DESCRIPTION_EMPTY = "Assurez-vous que chaque page de votre site comporte une meta description (<meta name=\"description\" content=\"Description de la page\"/>)";

	public static String MESSAGE_SEO_DESCRIPTION_LENGTH = "La longueur de la balise <strong>Description</strong> chez Google est aujourd’hui est environ de 160 caractères en moyenne pour ne pas se la voir tronquer/couper.";
	public static String MESSAGE_SEO_DESCRIPTION_OK = "La longueur de la balise <strong>Description</strong> est parfaite. Bravo!";

	// Tag
	public static String MESSAGE_SEO_TAG_OK = "Votre page contient les principales balises html (h1, h2, h3, h4, h5, h6, span). Bravo!";
	public static String MESSAGE_SEO_TAG_OK_EMPTY = "Votre page ne contient pas les princiaples balises html (h1, h2, h3, h4, h5, h6, span). Grrr!";

	// occurence
	public static String MESSAGE_SEO_WORD_OCCURENCE_OK = "Votre page est optimisée pour les mots présents dans le titre ou la description de votre page. Bravo!";
	public static String MESSAGE_SEO_WORD_OCCURENCE_EMPTY = "Votre page n'est pas optimisée pour les mots présents dans le titre ou la description de votre page. Grrr !";

	// pages
	public static String MESSAGE_SEO_PAGES_OK = "Votre page contient des sous pages interne et externe. Bravo!";
	public static String MESSAGE_SEO_PAGES_EMPTY = "Votre page ne contient pas des sous pages interne et externe. Grrr !";

	// siteMap
	public static String MESSAGE_SEO_SITE_MAP_OK = "Votre site contient un site Map (trés important). Bravo!";
	public static String MESSAGE_SEO_SITE_MAP_EMPTY = "Votre site ne contient pas un site Map ( trés mauvais). Grrr !";

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCodeError() {
		return codeError;
	}

	public void setCodeError(String codeError) {
		this.codeError = codeError;
	}

	public String getReferenceRule() {
		return referenceRule;
	}

	public void setReferenceRule(String referenceRule) {
		this.referenceRule = referenceRule;
	}

	public String getKeyWordInError() {
		return keyWordInError;
	}

	public void setKeyWordInError(String keyWordInError) {
		this.keyWordInError = keyWordInError;
	}

	public RuleMessages(String message, String codeError, String referenceRule, String keyWordInError) {
		super();
		this.message = message;
		this.codeError = codeError;
		this.referenceRule = referenceRule;
		this.keyWordInError = keyWordInError;
	}

}
