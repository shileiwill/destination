package company.linkedin;

public class PhoneInterview {

}


/**
 * Given two words as Strings, determine if they are isomorphic. Two words are called isomorphic
 * if there exists a 1:1 mapping of characters from the first to the second word. No two letters
 * may map to the same letter, but a letter may map to itself.
 *
 * Example:
 *   given "foo", "app"; returns true
 *     we can map 'f' -> 'a' and 'o' -> 'p'
 *
 *   given "foo", "boa"; returns false
 *     we can map 'f' -> 'b', 'o' -> 'o', we can't map 'o' -> 'a'
 *
 *   given "bar", "foo"; returns false
 *     we can't map both 'a' and 'r' to 'o'
 *
 *   given "turtle", "tletur"; returns true
 *     we can map 't' -> 't', 'u' -> 'l', 'r' -> 'e', 'l' -> 'u', 'e' ->'r'
 *
 *   given "ab", "ca"; returns true
 *     we can map 'a' -> 'c', 'b' -> 'a'
 */
public boolean isIsomorphic(String firstWord, String secondWord) {
  // Null check
  if (firstWord == null || secondWord == null) {
      return false;
  }
  
  // Length check
  if (firstWord.length() != secondWord.length()) {
      return false;
  }
  
  Map<Character, Character> map1 = HashMap<Character, Character>(); // firstWord -> secondWord
  Map<Character, Character> map2 = HashMap<Character, Character>(); // secondWord -> firstWord
  
  int pos = 0; // Since they share same length, one pos is enough
  while (pos < firstWord.length) {
      char firstChar = firstWord.charAt(pos);
      char secondChar = secondWord.charAt(pos);
      
      // The 1st way match
      if (map1.containsKey(firstChar)) {
          if (map1.get(firstChar) != secondChar) {
              return false;
          }
      } else {
          map1.put(firstChar, secondChar);
      }
      
      // The 2nd way match
      if (map2.containsKey(secondChar)) {
          if (map2.get(secondChar) != firstChar) {
              return false;
          }
      } else {
          map2.put(secondChar, firstChar);
      }
      
      pos++;
  }
  
  return true; // Everything perfectly match if reached here
}

// "foo", "app", "ded"
// "abb", "abb", "aba"
public boolean isIsomorphic(String firstWord, String secondWord, String thirdWord) {
      String transformed1 = transform(firstWord);
      String transformed2 = transform(secondWord);
      String transformed3 = transform(thirdWord);
  
      if (!transformed1.equals(transformed2) || !transformed2.equals(transformed3)) {
          return false;
      }
      
      return true;
}

public boolean isIsomorphic(String... words) {
  // implementation...
  if (words == null || words.length() == 0) {
      return true;
  }
  
  String pattern = transform(words[0]);
  for (int i = 1; i < words.length(); i++) {
      String transformed = transform(words[i]);
      if (!pattern.equals(transformed)) {
          return false;
      }
  }
  
  return true;
}

String transform(String word) {
    char now = 'a';
    Map<Character, Character> map = new HashMap<Character, Character>();
    StringBuilder sb = new StringBuilder();
    
    for (int i = 0; i < word.length(); i++) {
        char c = word.charAt(i);
        
        if (map.containsKey(c)) {
            sb.append(map.get(c));
        } else {
            sb.append(now);
            map.put(c, now);
            now++;
        }
    }
    
    return sb.toString();
}

public interface FirstCommonAncestor {
 
    /**
     * Given two nodes of a tree,
     * method should return the deepest common ancestor of those nodes.
     *
     *          A
     *         / \
     *        B   C
     *       / \
     *      D   E
     *         / \
     *        G   F
     *
     *  commonAncestor(D, F) = B
     *  commonAncestor(C, G) = A
     *  commonAncestor(E, B) = B
     */
    Node commonAncestor(Node one, Node two);
}

class FirstCommonAncestorImpl implements FirstCommonAncestor {
    Node commonAncestor(Node one, Node two) {
        // Basic validation
        if (one == null || two == null) {
            return null;
        }
        
        // Get the depth of 2 nodes
        int depth1 = getDepth(one); // O(depth of node) N
        int depth2 = getDepth(two); // N
        
        // Move 2 nodes to same level
        while (depth1 > depth2) { // If node one is deeper
            depth1--;
            one = one.parent;
        }
        
        while (depth2 > depth1) { // If node two is deeper
            depth2--;
            two = two.parent;
        }
        
        // At this point, 2 nodes should have same depth. Move up step by step and compare
        while (one != null && two != null) {
            if (one == two) { // Found
                return one;
            }
            one = one.parent;
            two = two.parent;
        }
        
        // If reached here, means at least one of the 2 nodes exausted. Then no common ancestor
        return null;
    }
    
    int getDepth(Node node) {
        int depth = 0;
        while (node != null) {
            depth++;
            node = node.parent;
        }
        return depth;
    }
}
 
class Node {
 
    final Node parent;
    final Node left;
    final Node right;
 
 
    public Node(Node parent, Node left, Node right) {
        this.parent = parent;
        this.left = left;
        this.right = right;
    }
 
    boolean isRoot() {
        return parent == null;
    }
}
