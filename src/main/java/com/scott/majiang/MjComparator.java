package com.scott.majiang;

import java.util.Comparator;

public class MjComparator implements Comparator<MaJiangCard>{

	public int compare(MaJiangCard card1, MaJiangCard card2) {
		if(card1.getNum() > card2.getNum()){
			return 1;
		}
		else if(card1.getNum() == card2.getNum()){
			return 0;
		}
		else{
			return -1;
		}
	}
}
