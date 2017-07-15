package system.design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

/*
 * Cassandra is a NoSQL storage. The structure has two-level keys.

Level 1: raw_key. The same as hash_key or shard_key.
Level 2: column_key.
Level 3: column_value

raw_key is used to hash and can not support range query. let's simplify this to a string.
column_key is sorted and support range query. let's simplify this to integer.
column_value is a string. you can serialize any data into a string and store it in column value.

implement the following methods:

insert(raw_key, column_key, column_value)
query(raw_key, column_start, column_end) // return a list of entries

Example
insert("google", 1, "haha")
query("google", 0, 1)
>> [(1, "haha")]

使用HashMap和TreeMap

这个是二维的
 * */
class Column { 
	public int key; 
	public String value;
	
	public Column(int key, String value) {
		this.key = key; 
		this.value = value; 
	}
}

public class MiniCassandra {
    private Map<String, TreeMap<Integer, String>> map;
    
    public MiniCassandra() {
        map = new HashMap<String, TreeMap<Integer, String>>();
    }
    
    /**
     * @param raw_key a string
     * @param column_start an integer
     * @param column_end an integer
     * @return void
     */
    public void insert(String raw_key, int column_key, String column_value) {
        if (!map.containsKey(raw_key)) {
        	map.put(raw_key, new TreeMap<Integer, String>());
        }
        
        map.get(raw_key).put(column_key, column_value);
    }

    /**
     * @param raw_key a string
     * @param column_start an integer
     * @param column_end an integer
     * @return a list of Columns
     */
    public List<Column> query(String raw_key, int column_start, int column_end) {
        List<Column> list = new ArrayList<Column>();
        
        if (!map.containsKey(raw_key)) {
            return list; // Empty
        }
        
        // getRange(), fromInclusive, toInclusive
        NavigableMap<Integer, String> subMap = map.get(raw_key).subMap(column_start, true, column_end, true);
		for (Map.Entry<Integer, String> entry : subMap.entrySet()) {
            list.add(new Column(entry.getKey(), entry.getValue()));
        }
		
        return list;
    }
}
