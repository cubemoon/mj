package com.scott.majiang;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
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
	
	public static List<String> findCombination(List<MaJiangCard> mjList){
		List<MaJiangCard> tmpCardList = new ArrayList<MaJiangCard>();
		tmpCardList = mjList;
		/*		11 123 123
				1111 22 33  ->put into a set get 1 2 3 left 111 2 3->> found 123 left 11
		
				11 123 123 35		
				1111 22 333 5 
				
				33 4444 5 678 ->put into a set->> found 345 678 left-cards 3 444 ->find cards with same cardNum->> found 444 left-card 3 -->correct!!
				3 345 444 678 ->find 33 444 left 456 78
				33 45 444 678
				33 456 444 678
				333 45 444 678
				
				456 234 44  -  4444 23 56-- 234 56 wrong!
				-
				sort- 234 44 456
				*/
		//find x of isStraight has to be done first ?
		List<String> straightCardList = findStraightCards(mjList);
		
				
		//find x of same cardNum 
		List<String> kindNumAndCardNumList = new ArrayList<String>();
		while (getkindNumAndCardNum(mjList) != null) {
			kindNumAndCardNumList.add(getkindNumAndCardNum(mjList));
			removeCardFromList(mjList, Character.getNumericValue(getkindNumAndCardNum(mjList).charAt(0)));
		}
		straightCardList.addAll(kindNumAndCardNumList);		
		return straightCardList;
			
	}
	
	public static List<String> StraightCardSeqToSingleCardList(List<String> straightCardString){
		List<String> out = new ArrayList<String>();
		for(String seq: straightCardString){
//			out.addAll(seq.split(" "));
		}
		
		return out;
	}
	/**
	 * 
	 * @param mjList
	 * @return
	 */
	public static List<String> findStraightCards(List<MaJiangCard> mjList){
		List<String> straightCardList = new ArrayList<String>();
		StringBuffer straightCards = new StringBuffer();
		for(int count=1;count<mjList.size();count++){
			if(mjList.get(count).getNum() == mjList.get(count-1).getNum()+1){
				if(straightCards.length() != 0){
					straightCards.append(" ").append(mjList.get(count).getName());
				}
				else{
					straightCards.append(mjList.get(count-1).getName()).append(" ").append(mjList.get(count).getName());
				}
			}
			else{
				if(straightCards.length() != 0){
					straightCardList.add(straightCards.toString());
					straightCards.delete(0, straightCards.length());
				}
			}
		}
		if(straightCards.length() != 0){
			straightCardList.add(straightCards.toString());
			straightCards.delete(0, straightCards.length());
		}
		return straightCardList;
	}
	public static void removeDuplicatedCardFromList(List<MaJiangCard> mjList){
		HashSet<MaJiangCard> cardSet = new HashSet<MaJiangCard>();
//		Collections.addAll(cardSet, mjList);
	}
	public static void removeCardFromList(List<MaJiangCard> mjList, int toRemove){
		Iterator<MaJiangCard> iter = mjList.iterator();
		while (iter.hasNext()) {
		    if (iter.next().getNum() == toRemove) {
		        iter.remove();
		    }
		}
		
	}
	public static String getkindNumAndCardNum(List<MaJiangCard> mjList){
		HashSet<Integer> cardNumSet = new HashSet<Integer>();
		int kind = 0;
		int cardNumSetSize = 0;
		boolean kindSequenceEndReady = false;
		int kindNum = 1;
		for(MaJiangCard card:mjList){
			cardNumSetSize = cardNumSet.size();
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
