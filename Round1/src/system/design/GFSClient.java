package system.design;
import java.util.HashMap;
import java.util.Map;
/*
GFS Client
Implement a simple client for GFS (Google File System, a distributed file system), it provides the following methods:
    read(filename). Read the file with given filename from GFS.
    write(filename, content). Write a file with given filename & content to GFS.
There are two private methods that already implemented in the base class:
    readChunk(filename, chunkIndex). Read a chunk from GFS.
    writeChunk(filename, chunkIndex, chunkData). Write a chunk to GFS.
To simplify this question, we can assume that the chunk size is chunkSize bytes. (In a real world system, it is 64M). 
The GFS Client's job is splitting a file into multiple chunks (if needed) and save to the remote GFS server. 
chunkSize will be given in the constructor. You need to call these two private methods to implement read & write methods.
 Example
GFSClient(5)
read("a.txt")
>> null
write("a.txt", "World")
>> You don't need to return anything, but you need to call writeChunk("a.txt", 0, "World") to write a 5 bytes chunk to GFS.
read("a.txt")
>> "World"
write("b.txt", "111112222233")
>> You need to save "11111" at chink 0, "22222" at chunk 1, "33" at chunk 2.
write("b.txt", "aaaaabbbbb")
read("b.txt")
>> "aaaaabbbbb"
解：
由于没有提供Master Server，需要Client自己记录每个文件名对应的所有Chunk Server。
分析Example，发现Chunk Server Index只需要将文件按照chunkSize切块，然后Index从0到块数减1就可以了。
如此，只需要HashMap只需要记录切了几块，然后从第0块写到最后一块，读的时候也从0块读到最后一块即可。
*/
class BaseGFSClient {
    private Map<String, String> chunk_list;
    public BaseGFSClient() {}
    public String readChunk(String filename, int chunkIndex) {
        // Read a chunk from GFS
        return "";
    }
    public void writeChunk(String filename, int chunkIndex, String content) {
    	// Write a chunk to GFS
    }
}

public class GFSClient extends BaseGFSClient {
    public int chunkSize;
    public Map<String, Integer> chunkNum;

    public GFSClient(int chunkSize) { // How many chunk servers
        this.chunkSize = chunkSize;
        this.chunkNum = new HashMap<String, Integer>();
    }
    
    // @param filename a file name
    // @return conetent of the file given from GFS
    public String read(String filename) {
        // Write your code here
        if (!chunkNum.containsKey(filename)) {
        	return null;
        }

        StringBuffer content = new StringBuffer();

        for (int i = 0; i < chunkNum.get(filename); ++i) { // 横跨几个chunk server
            String sub_content = readChunk(filename, i);
            if (sub_content != null) {
            	content.append(sub_content);
            }
        }
        
        return content.toString();
    }

    // @param filename a file name
    // @param content a string
    // @return void
    public void write(String filename, String content) {
        int len = content.length();

        int num = (len - 1) / chunkSize + 1; // 需要多少个server. +1 因为不足chunkSize的大小的 也要算一块
        chunkNum.put(filename, num);

        for (int i = 0; i < num; ++i) {
            int start = i * chunkSize;
            int end = (i == num - 1) ? len : (i + 1) * chunkSize; 
            String sub_content = content.substring(start, end);
            writeChunk(filename, i, sub_content);
        }
    }
}
