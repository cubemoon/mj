package com.scott.majiang;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Utils {
	
	public static boolean isOneCardToWinHand(){
//		1. Has one two of a kind
		
//		2. left are All three of a kind or straight except one or two card(same or in squence)
		return false;
	}
	public static boolean isWinHand(){
//		1. Has one two of a kind
		
//		2. left are All three of a kind or straight
		return false;
	}
	public static HashMap<String,List<MaJiangCard>> splitBySuit(List<MaJiangCard> cardList){
		HashMap<String,List<MaJiangCard>> cardListInMap = new HashMap<String, List<MaJiangCard>>();
		//T,S,W,F,H
		List<MaJiangCard> tList = new ArrayList<MaJiangCard>();
		List<MaJiangCard> sList = new ArrayList<MaJiangCard>();
		List<MaJiangCard> wList = new ArrayList<MaJiangCard>();
		List<MaJiangCard> fList = new ArrayList<MaJiangCard>();
		List<MaJiangCard> hList = new ArrayList<MaJiangCard>();
		for(MaJiangCard card: cardList){
			if(card.getSuit().equals(CardSuit.TONGZI)){
				tList.add(card);
			}
			if(card.getSuit().equals(CardSuit.SUOZI)){
				sList.add(card);
			}
			if(card.getSuit().equals(CardSuit.WANZI)){
				wList.add(card);
			}
			if(card.getSuit().equals(CardSuit.FENGXIANG)){
				fList.add(card);
			}
			if(card.getSuit().equals(CardSuit.HUA)){
				hList.add(card);
			}
			//sort card and find 
			
		}
		
		return cardListInMap;
	}
	
	private void sortByCardNum(List<MaJiangCard> mjList){
		
	}
	
	private void findCombination(List<MaJiangCard> mjList){
		
	}
		
	
	public static boolean isTwoOfAKind(){
		return false;
	}
	public static boolean isThreeOfAKind(){
		return false;
	}
	public static boolean isStraight(int[] inputs){
		if(inputs.length == 3){
			return true;
		}
		else
			return false;
	}

	public static String[] splitToStrArray(String src)
	{
		return src.split("\\s+");
	}

	public static String arrayToStrHand(String[] src)
	{
		StringBuffer result = new StringBuffer();
		if (src.length > 0)
		{
			result.append(src[0]);
			for (int i = 1; i < src.length; i++)
			{
				result.append(" ");
				result.append(src[i]);
			}
		}
		return result.toString();
	}
}
