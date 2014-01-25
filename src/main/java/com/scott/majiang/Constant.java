package com.scott.majiang;

import java.util.HashMap;

public class Constant {
//	TONGZI, SUOZI, WANZI, FENGXIANG, HUA

	public static final HashMap<String, String> cardSuitName = new HashMap<String, String>();
	static{
		cardSuitName.put("T", "TONGZI");
		cardSuitName.put("S", "SUOZI");
		cardSuitName.put("W", "WANZI");
		cardSuitName.put("F", "FENGXIANG");
		cardSuitName.put("H", "HUA");
	}
	public static final HashMap<String, CardSuit> cardSuit = new HashMap<String, CardSuit>();
	static{
		cardSuit.put("T", CardSuit.TONGZI);
		cardSuit.put("S", CardSuit.SUOZI);
		cardSuit.put("W", CardSuit.WANZI);
		cardSuit.put("F", CardSuit.FENGXIANG);
		cardSuit.put("H", CardSuit.HUA);
	}
	public static final HashMap<String, String> cardSuitShortCut = new HashMap<String, String>();
	static{
		cardSuitName.put("TONGZI", "T");
		cardSuitName.put("SUOZI", "S");
		cardSuitName.put("WANZI", "W");
		cardSuitName.put("FENGXIANG", "FG");
		cardSuitName.put("HUA", "H");
	}
}
