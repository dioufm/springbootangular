package com.javasampleapproach.springrest.mysql.model;

public class Word implements  Comparable<Word>{

	public String word;
	public int count;
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int compareTo(Word o,Word o1) {
		 return o.getCount() - (o1.getCount());
	}
	@Override
	public int compareTo(Word o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	

}
