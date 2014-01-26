package com.scott.majiang;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UtilsTest {
    private static final Logger logger = Logger.getLogger(UtilsTest.class.getName());
    List<MaJiangCard> cardList = new ArrayList<MaJiangCard>();
    
    @Before
    public void before()
    {
    	CardShuffle cs = new CardShuffle();
    	Queue<String> cardQueue = cs.initDeck();
    	for(String cardName: cardQueue){
    		MaJiangCard tmpCard = new MaJiangCard(cardName);
    		cardList.add(tmpCard);
    	}
    }
	
    @Test
    public void sortTest(){
    	Utils.sortByCardNum(cardList);
    	Assert.assertEquals(cardList.size(), 144);
    	for(MaJiangCard card:cardList){
    		logger.info(""+card.getNum());
    	}
    }
}
