package chapter1.pickOne;

import java.util.ArrayList;
import java.util.List;

// How to build a tree
public class FileSystemTree {

    private GeneralFileNode root;

    public FileSystemTree() {
        root = new GeneralFileNode();
        root.children = new ArrayList<GeneralFileNode>();
    }
    
    public void tree() {
    	// Level order traversal
    }
}

class GeneralFileNode {
	String permission;
	String owner;
	FileType type;
    GeneralFileNode parent;
    List<GeneralFileNode> children;
}

enum FileType {
	DICT, EXEC, SOFT_LINK;
}

//enum FileType2 {
//	EXEC(1), SOFT_LINK(2);
//	
//	private int value;
//	private FileType2(int value) {
//		this.value = value;
//	}
//}