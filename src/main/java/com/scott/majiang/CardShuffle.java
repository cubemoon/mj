package com.scott.majiang;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Queue;
import java.util.Random;
/**
 * 
 * @author scott.tang
 * 
 */
public class CardShuffle
{
	private static Random random = new Random();
	public Queue<String> initDeck()
	{
		/**
		TONGZI, SUOZI, WANZI ~ from 1 to 9  ~total: 9*3*4=108
		FENGXIANG : DONG, NAN, XI, BEI, BAIPI, FACAI, HONGZHONG (from 1 to 7) ~total: 7*4=28
		HUA: CHUN, XIA, QIU, DONG, MEI, LAN, ZHU, JU (from 1 to 8) ~total; 8

		sum: 108 + 28 + 8=144
		*/
		Queue<String> cards = new LinkedList<String>();
		StringBuffer sb = new StringBuffer();
		String[] ranks =
		{ "1", "2", "3", "4", "5", "6", "7", "8", "9"};
		String[] suits =
		{ "T", "S", "W"};
		for (int count = 1; count < 28; count++)
		{
			if (count < 9)
			{
				for(int num=0;num<4;num++){
					sb.setLength(0);
					sb.append(ranks[count - 1]).append(suits[(count - 1) / 9]);
					cards.offer(sb.toString());
				}
			}
			else
			{
				for(int num=0;num<4;num++){
					sb.setLength(0);
					sb.append(ranks[(count - 1) % 9]).append(suits[(count - 1) / 9]);
					cards.offer(sb.toString());
				}
			}
		}
		for(int f_count=1; f_count<8; f_count++){
			for(int num=0;num<4;num++){
				sb.setLength(0);
				sb.append(f_count).append("F");
				cards.offer(sb.toString());
			}
		}
		for(int h_count=1; h_count<9; h_count++){
			sb.setLength(0);
			sb.append(h_count).append("H");
			cards.offer(sb.toString());
		}
		return cards;
	}
	
	public Queue<MaJiangCard> initMaJiangCards(){
		Queue<MaJiangCard> mjCardQueue = new LinkedList<MaJiangCard>();
		Queue<String> cardShortNameQueue = initDeck();
		cardShortNameQueue = shuffle(cardShortNameQueue);
		for(int count=1; count<cardShortNameQueue.size(); count++){
			MaJiangCard tmpCard = new MaJiangCard(cardShortNameQueue.poll(), count);
			mjCardQueue.add(tmpCard);
		}
		return mjCardQueue;
	}

	private static void swap(Object[] arr, int i, int j)
	{
		Object tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T> Queue<T> shuffle(Queue<T> cards)
	{
		List<T> base = new LinkedList(cards);
		Object arr[] = base.toArray();
		for (int i = base.size(); i > 1; i--)
			swap(arr, i - 1, randInt(0,i));
		ListIterator it = base.listIterator();
		for (int i = 0; i < arr.length; i++)
		{
			it.next();
			it.set(arr[i]);
		}
		Queue<T> result = new LinkedList(base);
		return result;
	}
	
	public static int randInt(int min, int max) {
	    int randomNum = random.nextInt((max - min) + 1) + min;
	    return randomNum;
	}
}
