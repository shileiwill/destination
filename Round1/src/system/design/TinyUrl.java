package system.design;
import java.util.*;

//version 1: Random
public class TinyUrl {
	 private Map<String, String> long2Short;
	 private Map<String, String> short2Long;
	 
	 public TinyUrl() {
	     long2Short = new HashMap<String, String>();
	     short2Long = new HashMap<String, String>();
	 }
	 
	 /**
	  * @param url a long url
	  * @return a short url starts with http://tiny.url/
	  */
	 public String longToShort(String url) {
	     if (long2Short.containsKey(url)) {
	         return long2Short.get(url);
	     }
	     
	     while (true) { // 因为是random所以死循环 直到发现
	         String shortURL = generateShortURL();
	         if (!short2Long.containsKey(shortURL)) {
	             short2Long.put(shortURL, url);
	             long2Short.put(url, shortURL);
	             return shortURL;
	         }
	     }
	 }
	
	 /**
	  * @param url a short url starts with http://tiny.url/
	  * @return a long url
	  */
	 public String shortToLong(String url) {
	     if (!short2Long.containsKey(url)) {
	         return null;
	     }
	     
	     return short2Long.get(url);
	 }
	 
	 private String generateShortURL() {
	     String allowedChars = 
	         "0123456789" +
	         "abcdefghijklmnopqrstuvwxyz" + 
	         "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	     
	     Random rand = new Random();
	     StringBuilder shortURL = new StringBuilder("http://tiny.url/");
	     
	     for (int i = 0; i < 6; i++) { // 6位
	         int index = rand.nextInt(62);
	         shortURL.append(allowedChars.charAt(index));
	     }
	     
	     return shortURL.toString();
	 }
}


//version 2: base62
class TinyUrlBase62 {
	 public static int GLOBAL_ID = 0;
	 private HashMap<Integer, String> id2url = new HashMap<Integer, String>(); //省空间，只存一个Integer就行
	 private HashMap<String, Integer> url2id = new HashMap<String, Integer>();
	 
	 private String getShortKey(String url) {
	     int startIndex = "http://tiny.url/".length();
		 return url.substring(startIndex);
	 }
	
	 private int toBase62(char c) { // 这个其实可以直接用indexOf()
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
	
	 /**
	  * @param url a long url
	  * @return a short url starts with http://tiny.url/
	  */
	 public String longToShort(String url) {
	     if (url2id.containsKey(url)) {
	         return "http://tiny.url/" + idToShortKey(url2id.get(url));
	     }
	     
	     GLOBAL_ID++; // Auto increment
	     url2id.put(url, GLOBAL_ID);
	     id2url.put(GLOBAL_ID, url);
	     
	     return "http://tiny.url/" + idToShortKey(GLOBAL_ID);
	 }
	
	 /**
	  * @param url a short url starts with http://tiny.url/
	  * @return a long url
	  */
	 public String shortToLong(String url) {
	     String short_key = getShortKey(url);
	     int id = shortKeytoID(short_key);
	     
	     return id2url.get(id);
	 }
}