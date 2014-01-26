package com.scott.majiang;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Utils {
	
	final private static MjComparator mjComparator = new MjComparator();

	
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
			//sort card in list
			sortByCardNum(tList);
			sortByCardNum(sList);
			sortByCardNum(wList);
			sortByCardNum(fList);
			sortByCardNum(hList);
			
			//find combination
			findCombination(tList);
			findCombination(sList);
			findCombination(wList);
			findCombination(fList);
			findCombination(hList);
		}
		
		return cardListInMap;
	}
	
	public static void sortByCardNum(List<MaJiangCard> mjList){
		Collections.sort(mjList, mjComparator);
	}
	
	public static void findCombination(List<MaJiangCard> mjList){
		/*		11 123 123
				1111 22 33  ->put into a set get 1 2 3 left 111 2 3->> found 123 left 11
		
				11 123 123 35		
				1111 22 333 5 
				
				33 4444 5 678 ->put into a set->> found 345 678 left-cards 3 444 ->find cards with same cardNum->> found 444 left-card 3 -->correct!!
				3 345 444 678 ->find 33 444 left 456 78
				33 45 444 678
				33 456 444 678
				333 45 444 678
				*/
		//find x of isStraight has to be done first ?
		
		
		//find x of same cardNum 
		List<String> kindNumAndCardNumList = new ArrayList<String>();
		while (getkindNumAndCardNum(mjList) != null) {
			kindNumAndCardNumList.add(getkindNumAndCardNum(mjList));
			removeCardFromList(mjList, Character.getNumericValue(getkindNumAndCardNum(mjList).charAt(0)));
		}
			
	}		
	public static String findStraightCards(List<MaJiangCard> mjList){
		HashSet<Integer> cardNumSet = new HashSet<Integer>();
		return null;
	}
	public static void removeDuplicatedCardFromList(List<MaJiangCard> mjList){
		HashSet<MaJiangCard> cardSet = new HashSet<MaJiangCard>();
//		Collections.addAll(cardSet, mjList);
	}
	public static void removeCardFromList(List<MaJiangCard> mjList, int toRemove){
		for(MaJiangCard card:mjList){
			if(card.getNum()==toRemove){
				mjList.remove(card);
			}
		}
		
	}
	public static String getkindNumAndCardNum(List<MaJiangCard> mjList){
		HashSet<Integer> cardNumSet = new HashSet<Integer>();
		int kind = 0;
		int cardNumSetSize = 0;
		boolean kindSequenceEndReady = false;
		int kindNum = 0;
		for(MaJiangCard card:mjList){
			cardNumSet.add(card.getNum());
			if(cardNumSet.size() == cardNumSetSize){
				kindSequenceEndReady = true;
				kindNum++;
				kind = card.getNum();
			}
			else if(kindSequenceEndReady == true){
					break;
				}
		}
		if(kindSequenceEndReady == false){
			return null;
		}
		else{
			return new StringBuffer().append(kindNum).append('_').append(kind).append('_').append(mjList.get(0).getSuit().name()).toString();
		}
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
