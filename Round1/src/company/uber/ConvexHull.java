package company.uber;

import java.util.Collections;
import java.util.List;
/**
 * http://www.gregbugaj.com/?p=499
 * http://www.algorithmist.com/index.php/Gift_Wrapping
 *  找到最左下角的点，这个点是root, 之后创建一个指针，寻找下一个最外侧的点
 */
public class ConvexHull {

	public static void main(String[] args) {

	}

	void giftWrapping(List<Point> list) {
		Collections.sort(list);
		
		// findLeftBottomPoint
		Point root = list.get(0);
		Point cur = root;
		do {
			System.out.println(cur);

			int leftMost = 0;// Sentinel. Default could be anyone
			for (int i = 1; i < list.size(); i++) { // Compare with all others
				// If line current->leftmost is to the left of current->point[i], wrong?
				if (isLeft(cur, list.get(i), list.get(leftMost))) {
					leftMost = i;
				}
			}
			cur = list.get(leftMost);
		} while (cur != root);
	}
	
	// This is the magic
    private boolean isLeft(Point cur, Point p, Point q)
    {
        final int x1 = (p.x - cur.x) * (q.y - cur.y);
        final int x2 = (q.x - cur.x) * (p.y - cur.y);
        final int anotherInteger = x1 - x2;
        return anotherInteger > 0;
    }

	static class Point implements Comparable<Point> {
		int x, y;
		
		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public int compareTo(Point that) {
			if (this.x != that.x) {
				return this.x - that.x;
			}
			return this.y - that.y;
		}
		
		public String toString() {
			return this.x + "==" + this.y;
		}
	}
}
