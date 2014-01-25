package com.scott.majiang;

public class MaJiangCard {
	//T,S,W,F,H
	private int id;
	private String name;
	private int num;
	private CardSuit suit;
	
	MaJiangCard(String shortName) {
		this.setNum(shortName.charAt(0));
		this.setName(shortName);
		this.setSuit(Constant.cardSuit.get(shortName.charAt(1)));
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
		return sb.append(num).append(suit.name().charAt(0)).toString();
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
}
