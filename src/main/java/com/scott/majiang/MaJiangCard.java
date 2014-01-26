package com.scott.majiang;

public class MaJiangCard {
	//T,S,W,F,H
	private int id;
	private String name;
	private int num;
	private CardSuit suit;
	
	MaJiangCard(String shortName) {
		this.setNum(Character.getNumericValue(shortName.charAt(0)));
		this.setName(shortName);
		this.setSuit(Constant.cardSuit.get(Character.toString(shortName.charAt(1))));
	}
	
	public MaJiangCard(String shortName, int id) {
		this.setId(id);
		this.setNum(Character.getNumericValue(shortName.charAt(0)));
		this.setName(shortName);
		this.setSuit(Constant.cardSuit.get(Character.toString(shortName.charAt(1))));
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public CardSuit getSuit() {
		return suit;
	}
	public void setSuit(CardSuit suit) {
		this.suit = suit;
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		return sb.append(num).append(suit.name()).toString();
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
}
