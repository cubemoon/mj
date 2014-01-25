package com.scott.majiang;
import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author scott.tang
 * 
 */
public class CombinationGen
{
	public static void combination(String s, int k, List<String> result)
	{
		String head = "";
		String tail = s;
		combination(head, tail, k, result);
	}
	// fix the head and find combination of tail
	public static void combination(String head, String tail, int k,
			List<String> result)
	{
		if (tail.length() < k) // we are unable to pick k letters from tail
		{
			return; // no combination at all
		}
		else if (k == 0) // combination found
		{
			result.add(head);
		}
		else
		{
			String new_tail = tail.substring(1);
			String new_head = head + tail.charAt(0);
			combination(new_head, new_tail, k - 1, result);
			combination(head, new_tail, k, result);
		}
	}
	public static String[] generateHands(String raw, String[] tex7,
			int combination)
	{
		List<String> pos = new ArrayList<String>();
		combination(raw, combination, pos);
		String[] result = new String[pos.size()];
		for (int count = 0; count < pos.size(); count++)
		{
			// result[count] =
			// tex7[Character.getNumericValue(pos.get(count).charAt(0))]
			// + " "
			// + tex7[Character.getNumericValue(pos.get(count).charAt(1))]
			// + " "
			// + tex7[Character.getNumericValue(pos.get(count).charAt(2))]
			// + " "
			// + tex7[Character.getNumericValue(pos.get(count).charAt(3))]
			// + " "
			// + tex7[Character.getNumericValue(pos.get(count).charAt(4))];
			for (int i = 0; i < combination; i++)
			{
				if (result[count] == null)
				{
					result[count] = tex7[Character.getNumericValue(pos.get(
							count).charAt(i))];
				}
				else
				{
					result[count] += tex7[Character.getNumericValue(pos.get(
							count).charAt(i))];
				}
				if (i < combination - 1)
				{
					result[count] += " ";
				}
			}
		}
		return result;
	}
}
