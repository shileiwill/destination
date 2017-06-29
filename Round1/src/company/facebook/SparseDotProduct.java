package company.facebook;
//重点
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
Dot Product，点乘 是 1行N列 *N行1列 = 1行1列， 一个数字
                   
可以用自己喜欢的数据结构，最简单的就是用一个map存啦，这样每次找value 只要O(1),
然后就问说如果有很多0怎么办，那就只存不为0的值，遍历A和B里面非0值比较少的，这样时间复杂度就是非0值的个数的较小值。
但是map存的话空间不够优化，就有说到两个指针的做法，用两个list来存。
还问如果一个很长，一个很短怎么办，极端情况就是B的非0值只有一个，A有10000个，那就从B开始乘，用二分找A的value list里面对应于B的index。
反正多沟通最重要，说出每种方法的优缺点，然后面试官让你写你再写

这两个输入如果是根据下标排序好的话应该怎么办，我说可以遍历长度较短的那一个，
然后用二分搜索的方法在另一个vector中找index相同的元素，相乘加入到结果中，这样的话复杂度就是O(M*logN)。
这时，面试官又问是否可以同时利用两个输入都是排序好这一个特性，我在这个地方有点卡住，但是在白板上写出一个test case，
试着用可视化的方法帮助我来进行思考，同时面试官给了一些提醒，最后写出了O(M + N)的双指针方法
M和N分别是第一个list和第二个list中非零元素的个数

This stackoverflow helps:
https://stackoverflow.com/questions/32753310/how-to-get-dot-product-of-two-sparsevectors-in-omn-where-m-and-n-are-the-nu
 */
public class SparseDotProduct {

	public static void main(String[] args) {
		SparseDotProduct sdp = new SparseDotProduct();
		Integer[] arr1 = {1, 3, -5};
		Integer[] arr2 = {4, -2, -1};
		
		List<Integer> A = Arrays.asList(arr1);
		List<Integer> B = Arrays.asList(arr2);
		
		Node n1 = new Node(0, 1);
		Node n2 = new Node(1, 3);
		Node n3 = new Node(2, -5);
		Node n4 = new Node(0, 4);
		Node n5 = new Node(1, -2);
		Node n6 = new Node(2, -1);
		
		Node[] arr3 = {n1, n3};
		Node[] arr4 = {n4, n5, n6};
		
		List<Node> C = Arrays.asList(arr3);
		List<Node> D = Arrays.asList(arr4);
		
		int res = sdp.multiply2(C, D);
		System.out.println(res);
	}

	// 传统解法，复杂度是O(Total Length of A, including 0s)
    public int multiply1(List<Integer> A, List<Integer> B) {
    	int res = 0;
    	for (int i = 0; i < A.size(); i++) {
    		res += A.get(i) * B.get(i);
    	}
    	
    	return res;
    }
    
    // What if you are told one of the List, e.g. A, is sparse, which means a lot of 0s in A
    // A中存的只是非零元素，所以A is far shorter. Both A and B are sorted by index of Node
    // M*log(N) M是A中非零元素个数， N是B中非零元素个数
    public int multiply2(List<Node> A, List<Node> B) {
    	int res = 0;
    	for (int i = 0; i < A.size(); i++) {
    		int index = A.get(i).index;
    		int valueA = A.get(i).value;
    		
    		Integer valueB = binarySearch(B, index);
    		
    		if (valueB != null) {
    			res += valueA * valueB;
    		}
    	}
    	
    	return res;
    }
    
    private Integer binarySearch(List<Node> list, int index) {
    	int left = 0;
    	int right = list.size() - 1;
    	
    	while (left + 1 < right) {
    		int mid = left + (right - left) / 2;
    		
    		if (list.get(mid).index == index) {
    			return list.get(mid).value;
    		} else if (list.get(mid).index < index) {
    			left = mid;
    		} else {
    			right = mid;
    		}
    	}
    	
    	if (list.get(left).index == index) {
			return list.get(left).value;
		} else if (list.get(right).index == index) {
			return list.get(right).value;
		}
    			
		return null;
	}
    
    // 是否可以同时利用两个输入都是排序好这一个特性 O(M + N)
    public int multiply3(List<Node> A, List<Node> B) {
    	int res = 0;
    	int pos1 = 0;
    	int pos2 = 0;
    	
    	while (pos1 < A.size() && pos2 < B.size()) {
    		int index = A.get(pos1).index;
    		int valueA = A.get(pos1).value;
    		
    		while (pos2 < B.size() && B.get(pos2).index < index) {
    			pos2++;
    		}
    		
    		if (pos2 < B.size()) {
    			if (B.get(pos2).index == index) {
    				res += valueA * B.get(pos2).value;
    				pos2++;
    			}
    		}
    		
    		pos1++;
    	}
    	
    	return res;
    }

	static class Node {
    	int index;
    	int value;
    	
    	Node(int index, int value) {
    		this.index = index;
    		this.value = value;
    	}
    }
}
