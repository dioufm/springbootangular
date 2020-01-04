package com.javasampleapproach.springrest.mysql.message.response;

public class PageInfo {

	private String url;

	private String encre;

	private String type; // interne or externe

	public PageInfo(String url, String encre, String type) {
		super();
		this.url = url;
		this.encre = encre;
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getEncre() {
		return encre;
	}

	public void setEncre(String encre) {
		this.encre = encre;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
