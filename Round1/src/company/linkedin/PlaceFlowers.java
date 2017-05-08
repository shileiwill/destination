package company.linkedin;

import java.util.List;

/**
 * 如果flowerbed当中为true，说明已经栽过花了，附近两个（left and right）不能再栽花。numberToPlace代表想再栽多少花到flowerbed里。
 * 让return是不是还能栽那么多谢花进去。
 */
public class PlaceFlowers {

	public static void main(String[] args) {

	}

	/*
	 * 遇到flower,直接jump +2. 遇到空地方，检查left and right. 如果左右皆空， 放花，jump +2, 如果不能放，jump +1
	 */
	boolean canPlaceFlowers(List<Boolean> flowerbed, int count) {
		if (count == 0) {
			return true;
		}
		
		int pos = 0;
		while (pos < flowerbed.size()) {
			if (flowerbed.get(pos)) { // has flower
				pos += 2;
				continue;
			}
			
			// If left and right are available, true means available
			boolean left = false;
			boolean right = false;
			
			if (pos == 0) {
				left = true; //Assume -1 doesnt have flower
			} else {
				left = !flowerbed.get(pos - 1);
			}
			
			if (pos == flowerbed.size() - 1) {
				right = true;
			} else {
				right = !flowerbed.get(pos + 1);
			}
			
			if (left && right) { // Both left and right dont have flower
				count--; // Place flower at this position
				pos += 2; // Move to next place
			} else {
				pos += 1;
			}
		}
		
		return count <= 0;
	}

}
