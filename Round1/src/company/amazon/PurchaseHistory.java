package company.amazon;

import java.sql.Time;
import java.util.Map;

/**
 * 有一堆时间连续的purchase records (id, timestamp, userId, productId)，找出最常被同一个用户连续购买的三个商品
 */
public class PurchaseHistory {

	public static void main(String[] args) {

	}

	void find() {
		Map<User, TreeMap<Time, Product>> map1 = null;
		Map<Product, Integer> map2 = null; // When adding to map1, if User exists, check the product bought last time, and last last time, if same, then add to map2, and visited 
		Map<Product, User> visited = null; // This user keeps buying this product, but we should not duplicate the count in map2
		
	}
}
