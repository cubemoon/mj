package com.scott.majiang;
import java.util.Queue;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CardShuffleTest {
    private static final Logger logger = Logger.getLogger(CardShuffleTest.class.getName());
	
    @Mock
    private CardShuffle cardShuffle;
    
    @Before
    public void before()
    {
    	cardShuffle = new CardShuffle();
    }
	
	@Test
	public void initDeck(){
		Queue<String> cardsQueue = cardShuffle.initDeck();
//		System.out.println("total cards count: "+cardsQueue.size());
//		for(String card : cardsQueue){
//			System.out.print(card+" ");
//		}
		System.out.println("total cards count: "+cardsQueue.size());
		cardsQueue = cardShuffle.shuffle(cardsQueue);
		for(String card : cardsQueue){
//			System.out.print(card+" ");
//			logger.info(card+" ");
		}
		Queue<MaJiangCard> mjcardQueue = cardShuffle.initMaJiangCards();
		for(MaJiangCard card:mjcardQueue){
			logger.info(card.toString());
		}	
	}
}
