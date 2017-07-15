package system.design;

import java.util.HashMap;

/*
createCustom("http://www.lintcode.com/", "lccode")
>> http://tiny.url/lccode
createCustom("http://www.lintcode.com/", "lc")
>> error
longToShort("http://www.lintcode.com/problem/")
>> http://tiny.url/1Ab38c   // this is just an example, you can have you own 6 characters.
shortToLong("http://tiny.url/lccode")
>> http://www.lintcode.com/
shortToLong("http://tiny.url/1Ab38c")
>> http://www.lintcode.com/problem/
shortToLong("http://tiny.url/1Ab38d")
>> null
 * */
public class TinyUrl2 {
    private HashMap<Integer, String> id2url = new HashMap<Integer, String>();
    private HashMap<String, Integer> url2id = new HashMap<String, Integer>();

    private HashMap<String, String> customShort2Long = new HashMap<String, String>();
    private HashMap<String, String> customLong2Short = new HashMap<String, String>();

    private final String tinyUrl = "http://tiny.url/";
    private static Integer GLOBAL_ID = 0;

    private String getShortKey(String short_url) {
        return short_url.substring(tinyUrl.length());
    }

	 private int toBase62(char c) {
	     if (c >= '0' && c <= '9') {
	         return c - '0';
	     }
	     
	     if (c >= 'a' && c <= 'z') {
	         return c - 'a' + 10;
	     }
	     
	     return c - 'A' + 36; // Return the index
	 }
	 
	 private int shortKeytoID(String short_key) {
	     int id = 0;
	     for (int i = 0; i < short_key.length(); ++i) {
	         id = id * 62 + toBase62(short_key.charAt(i));
	     }
	     
	     return id;
	 }

	 private String idToShortKey(int id) {
	     String chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	     String short_url = "";
	     
	     while (id > 0) {
	         short_url = chars.charAt(id % 62) + short_url;
	         id = id / 62;
	     }
	     
	     while (short_url.length() < 6) {
	         short_url = "0" + short_url; // 不到6位，前边补零
	     }
	     
	     return short_url;
	 }
    
    private boolean isNormalKey(String short_key) {
        if (short_key.length() != 6) {
            return false;
        }
        
        for (int i = 0; i < 6; i++) {
            char c = short_key.charAt(i);
            
            if ((c >= '0' && c <= '9') || (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                continue;
            }
            
            return false;
        }
        
        return true;
    }
    
    /**
     * @param long_url a long url
     * @param a short key
     * @return a short url starts with http://tiny.url/
     */
    String createCustom(String long_url, String short_key) {
        String short_url = tinyUrl + short_key;
        
        if (isNormalKey(short_key)) { // 如果是Normal key的话，就有可能跟自动生成的conflict
            long id = shortKeytoID(short_key);
            
            if (id2url.containsKey(id) && !id2url.get(id).equals(long_url)) {
                return "error";
            }

            if (url2id.containsKey(long_url) && url2id.get(long_url) != id) {
                return "error";
            }
            
            if (id2url.containsKey(id) || url2id.containsKey(long_url)) {
            	return short_url;
            }
        }
        
        if (customShort2Long.containsKey(short_url) && 
                !customShort2Long.get(short_url).equals(long_url)) {
            return "error";
        }
        
        if (customLong2Short.containsKey(long_url) && 
                !customLong2Short.get(long_url).equals(short_url)) {
            return "error";
        }
        
        customShort2Long.put(short_url, long_url); // If comes here, 表示没有任何问题
        customLong2Short.put(long_url, short_url);
        
        return short_url;
    }

    /**
     * @param long_url a long url
     * @return a short url starts with http://tiny.url/
     */
    public String longToShort(String long_url) {
        if (customLong2Short.containsKey(long_url)) {
            return customLong2Short.get(long_url);
        }
        
        if (url2id.containsKey(long_url)) {
            int id = url2id.get(long_url);
            return tinyUrl + idToShortKey(id);
        }
        
        GLOBAL_ID++;
        id2url.put(GLOBAL_ID, long_url);
        url2id.put(long_url, GLOBAL_ID);
        return tinyUrl + idToShortKey(GLOBAL_ID);
    }

    /**
     * @param short_url a short url starts with http://tiny.url/
     * @return a long url
     */
    public String shortToLong(String short_url) {
        if (customShort2Long.containsKey(short_url)) {
        	return customShort2Long.get(short_url);
        }

        String short_key = getShortKey(short_url);
        long id = shortKeytoID(short_key);
        
        return id2url.get(id);
    }
}