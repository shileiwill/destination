package company.tripadvisor.trialpay;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VendingMachine {
	List<Product> list = new ArrayList<Product>(); // index to product
	Map<Product, Integer> map = new HashMap<Product, Integer>(); // product to count
	int rackSize = 0;
	
	VendingMachine(int size) {
		this.rackSize = size;
	}
	
	boolean isRackFull() {
		return rackSize == list.size();
	}
	
	void addProduct(Product prod) {
		if (map.containsKey(prod)) {
			map.put(prod, map.get(prod) + 1);
		} else {
			if (isRackFull()) {
				throw new RuntimeException("Rack is full");
			}
			map.put(prod, 1);
			list.add(prod);
		}
	}
	
	boolean sellProduct(Product prod) {
		return sellProduct(prod, 1);
	}
	
	boolean sellProduct(Product prod, int count) {
		if (!checkAvailability(prod, count)) {
			return false;
		}
		
		map.put(prod, map.get(prod) - count);
		
		if (map.get(prod) == 0) {
			map.remove(prod);
			list.remove(prod); // This will make the rack shift. Or set a flag
		}
		
		return true;
	}
	
	boolean checkAvailability(Product prod, int count) {
		return map.get(prod) >= count;
	}
}

class Product {
	Category cat;
	double price;
	String brand;
}

enum Category {
	DRINK, FOOD
}