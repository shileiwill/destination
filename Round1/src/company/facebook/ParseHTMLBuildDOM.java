package company.facebook;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * parse HTML and build a DOM tree
 * 这个题实际上就是serialize and deserialize N-ary tree
 */
public class ParseHTMLBuildDOM {

	public static void main(String[] args) {
		String html = "<html><head><title>Test</title></head><body>It works!</body></html>";
		ParseHTMLBuildDOM dom = new ParseHTMLBuildDOM();
		
		Node root = dom.parse(html);
		System.out.println(root);
	}

	static class Node {
		String tag;
		String content;
		List<Node> children = new ArrayList<Node>();
		
		Node(String tag, String content) {
			this.tag = tag;
			this.content = content;
		}
	}
	
	Node parse(String s) {
		int pos = 0;
		Node root = null;
		Node parent = null;
		Stack<Node> stack = new Stack<Node>();
		
		while (pos < s.length() - 1) {
			char c1 = s.charAt(pos);
			char c2 = s.charAt(pos + 1);
			
			if (c1 == '<' && c2 != '/') { // open tag
				pos++;
				StringBuilder sb = new StringBuilder();
				
				while (pos < s.length() && s.charAt(pos) != '>') {
					sb.append(s.charAt(pos));
					pos++;
				}
				
				Node node = new Node(sb.toString(), null);
				
				if (root == null) {
					root = node;
				}
				
				if (parent != null) {
					parent.children.add(node);
				}
				
				parent = node;
				stack.push(node);
			} else if (c1 == '<' && c2 == '/') { // close tag
				stack.pop();
				if (!stack.isEmpty()) {
					parent = stack.peek();
				}
				
				while (pos < s.length() && s.charAt(pos) != '>') {
					pos++; // Just to move the cursor
				}
			} else { // Text tag
				StringBuilder sb = new StringBuilder();
				
				while (pos < s.length() && s.charAt(pos) != '<') {
					sb.append(s.charAt(pos));
					pos++;
				}
				pos--;
				
				Node node = new Node("Text", sb.toString());
				parent.children.add(node);
			}
			
			pos++;
		}
		
		return root;
	}
}
