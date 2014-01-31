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
	
//    @Test
    public void sortTest(){
    	Utils.sortByCardNum(cardList);
    	Assert.assertEquals(cardList.size(), 144);
    	for(MaJiangCard card:cardList){
    		logger.info(""+card.getNum());
    	}
    }
    
//    @Test
    public void findStraightTest(){
//    	456 234 44  -  4444 23 56-- 234 56 wrong!
        List<MaJiangCard> cardList = new ArrayList<MaJiangCard>();
    	List<String> cardQueue = new ArrayList<String>();
    	cardQueue.add("4T");
    	cardQueue.add("4T");
    	cardQueue.add("4T");
    	cardQueue.add("4T");
    	cardQueue.add("2T");
    	cardQueue.add("3T");
    	cardQueue.add("5T");
    	cardQueue.add("6T");
    	
    	for(String cardName: cardQueue){
    		MaJiangCard tmpCard = new MaJiangCard(cardName);
    		cardList.add(tmpCard);
    	}
    	Utils.sortByCardNum(cardList);
    	List<String> straightCardList = Utils.findStraightCards(cardList);
    	for(String card:straightCardList){
    		logger.info(card+" | ");
    	}
    }
    
//    @Test
    public void getkindNumAndCardNumTest(){
        List<MaJiangCard> cardList = new ArrayList<MaJiangCard>();
    	List<String> cardQueue = new ArrayList<String>();
    	cardQueue.add("4T");
    	cardQueue.add("4T");
    	cardQueue.add("4T");
    	cardQueue.add("4T");
    	cardQueue.add("2T");
    	cardQueue.add("3T");
    	cardQueue.add("5T");
    	cardQueue.add("6T");
    	
    	for(String cardName: cardQueue){
    		MaJiangCard tmpCard = new MaJiangCard(cardName);
    		cardList.add(tmpCard);
    	}
    	Utils.sortByCardNum(cardList);
    	
    	String out = Utils.getkindNumAndCardNum(cardList);
    	logger.info(out);
    }
    
    @Test
    public void findCombinTest(){
        List<MaJiangCard> cardList = new ArrayList<MaJiangCard>();
    	List<String> cardQueue = new ArrayList<String>();
    	cardQueue.add("4T");
    	cardQueue.add("4T");
    	cardQueue.add("4T");
    	cardQueue.add("4T");
    	cardQueue.add("2T");
    	cardQueue.add("3T");
    	cardQueue.add("5T");
    	cardQueue.add("6T");
    	
    	for(String cardName: cardQueue){
    		MaJiangCard tmpCard = new MaJiangCard(cardName);
    		cardList.add(tmpCard);
    	}
    	Utils.sortByCardNum(cardList);
    	
    	List<String> outList = Utils.findCombination(cardList);
    	
    	for(String out:outList){
    		logger.info(out);
    	}
    }
}
