package com.javasampleapproach.springrest.mysql.message.response;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.javasampleapproach.springrest.mysql.model.rule.RuleMessages;

public class TagAnalyse {
	
	private String tagName;
	
	private long count;
	
	private List<String> listTag = new ArrayList<>();
	
	private List<String> shortListTag = new ArrayList<>();
	
	private String codeError;

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public List<String> getListTag() {
		return listTag;
	}

	public void setListTag(List<String> listTag) {
		this.listTag = listTag;
	}

	public List<String> getShortListTag() {
		return shortListTag;
	}

	public void setShortListTag(List<String> shortListTag) {
		this.shortListTag = shortListTag;
	}

	public String getCodeError() {
		return codeError;
	}

	public void setCodeError(String codeError) {
		this.codeError = codeError;
	}
	
	
  
}
