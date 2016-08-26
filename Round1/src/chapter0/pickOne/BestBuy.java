package chapter0.pickOne;

import java.util.Arrays;

/**
 * + * money you have and price of items are given
+ * try to find out 2 items their cost is mostly near the money
+ * and return their index
 */
public class BestBuy {

	public static void main(String[] args) {
		//int[] prices = {18, 3, 1, 3, 7, 12, 19};
		int[] prices = {1, 4, 5, 7, 9, 22, 21};
		int money = 3;
		BestBuy b = new BestBuy();
		int[] res = b.best2ItemsToBuyWith(prices, money);
		System.out.println(res[0] + "--" + res[1]);
	}

	public int[] best2ItemsToBuyWith(int[] prices, int money) {
		int[] res = new int[2];
		Item[] items = new Item[prices.length];
		for (int i = 0; i < prices.length; i++) {
			items[i] = new Item(i, prices[i]);
		}
		
		Arrays.sort(items);
		
		int left = 0;
		int right = items.length - 1;
		int curIndex1 = -1;
		int curIndex2 = -1;
		
		while (left < right) {
			int sum = items[left].price + items[right].price;
			if (sum == money) {
				res[0] = Math.min(items[left].index, items[right].index);
				res[1] = Math.max(items[left].index, items[right].index);
				return res;
			} else if (sum < money) {
				curIndex1 = items[left].index;
				curIndex2 = items[right].index;
				left++; // Can ignore duplicate elements by using while
			} else {
				right--;
			}
		}
		
		res[0] = Math.min(curIndex1, curIndex2);
		res[1] = Math.max(curIndex1, curIndex2);
		return res;
	}
	
	class Item implements Comparable<Item> {
		int index;
		int price;
		
		Item(int index, int price) {
			this.index = index;
			this.price = price;
		}
		
		public int compareTo(Item i1) {
			return this.price - i1.price;
		}
	}
}

