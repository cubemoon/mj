package com.scott.majiang;

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
	private void sort(List<MaJiangCard> mjList){
		
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
