package com.javasampleapproach.springrest.mysql.message.response;

public class MessageSeo {
	
	private String code;
	
	private String value;
	
	private String codeError;
	
	private String referenceRule;
	
	private String keyWordInError;
	
	private String label;
	
	private String ruleDescription;
	
	private String labelError;
	
	private String googleView;
	
	private int order;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getCodeError() {
		return codeError;
	}
	public void setCodeError(String codeError) {
		this.codeError = codeError;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getRuleDescription() {
		return ruleDescription;
	}
	public void setRuleDescription(String ruleDescription) {
		this.ruleDescription = ruleDescription;
	}
	public String getLabelError() {
		return labelError;
	}
	public void setLabelError(String labelError) {
		this.labelError = labelError;
	}
	
	
	
	public String getGoogleView() {
		return googleView;
	}
	public void setGoogleView(String googleView) {
		this.googleView = googleView;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
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
	public MessageSeo(String code, String value,String codeError, String referenceRule, String keyWordInError, String label, String ruleDescription, String labelError,
			String googleView, int order) {
		super();
		this.code = code;
		this.value = value;
		this.codeError = codeError;
		this.referenceRule = referenceRule;
		this.keyWordInError = keyWordInError;
		this.label = label;
		this.ruleDescription = ruleDescription;
		this.labelError = labelError;
		this.googleView = googleView;
		this.order = order;
	}

	
}
