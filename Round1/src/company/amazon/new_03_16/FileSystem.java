package company.amazon.new_03_16;

import java.util.ArrayList;
import java.util.List;

public class FileSystem {

}

abstract class Entry {
	String name;
	Directory parent;
	long createdAt;
	long lastModifiedAt;
	long lastAccessed;
	
	Entry(String name, Directory parent) {
		this.name = name;
		this.parent = parent;
		createdAt = System.currentTimeMillis();
		lastModifiedAt = System.currentTimeMillis();
		lastAccessed = System.currentTimeMillis();
	}
	
	String getFullPath() {
		if (parent == null) {
			return name;
		}
		
		return parent.getFullPath() + "/" + name;
	}
	
	abstract int getSize();
	
	boolean deleteEntry() {
		if (parent == null) {
			return false;
		}
		return parent.deleteEntry(this);
	}
}

class File extends Entry {
	String content = null;
	int size;
	
	File(String name, Directory parent, int size) {
		super(name, parent);
		this.size = size;
	}
	
	int getSize() {
		return size;
	}
	
	
}

class Directory extends Entry {
	List<Entry> contents = null;
	
	Directory(String name, Directory parent) {
		super(name, parent);
		contents = new ArrayList<Entry>();
	}
	
	int getSize() {
		int size = 0;
		for (Entry entry : contents) {
			size += entry.getSize();
		}
		return size;
	}
	
	int numberOfFiles() { // Directory is also considered as a File
		int count = 0;
		for (Entry entry : contents) {
			if (entry instanceof File) {
				count += 1;
			} else {
				Directory dir = (Directory)entry;
				count += dir.numberOfFiles();
			}
		}
		
		return count;
	}
	
	boolean deleteEntry(Entry entry) {
		return contents.remove(entry);
	}
	
	void addEntry(Entry entry) {
		contents.add(entry);
	}
}
