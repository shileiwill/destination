package company.amazon;

public class Phone2Round {

}


Coding question:
config:
{/n
ke:y1: val\\{ue1/n
key2: value2/n
key3: {/n
       key4: value4/n
       … /n
      }/n
}/n

Map<String, String> parse(String config) {
    Map<String, String> map = new HashMap<String, String>();
    
    List<NestedPair> input = transform(config);
    
    //BFS
    
    
    return map;
}

List<NestedPair> transform(String config) {
    BufferedReader br = new BufferedReader(new FileReader(config));
    List<NestedPair> res = new ArrayList<NestedPair>();
    
    while (br.hasNext()) {
        String curLine = br.readLine();
        
        if (curLine.indexOf("{") == -1) {
            String[] pair = curLine.split(":");
            
            NestedPair np = new NestedPair(pair[0].trim(), pair[1]);
            np.isString = true;
            res.add(np);
        } else {
            String key = curLine.split(":")[0];
            
            List<NestedPair> list = new ArrayList<NestedPair>();
            
            StringBuilder sb = new StringBuilder("{\n" + curLine + "\n}");
            
            String nextLine = br.readLine();
            while (nextLine.indexOf("}") != -1) {
                sb.append(nextLine);
                
            }
            
            sb.append(nextLine);
            List<NestedPair> value1 = transform(sb.toString());
            
            res.add(value1);
        }
    }
    
    return res;
}


class NestedPair {
    String key;
    String value;
    NestedPair pair;
    boolean isString;
    
    
    boolean isString() {};
    
    String getString() {}
    
    List<NestedPair> getList() {}
    
    NestedPair(String key, String value) {
        this.key = key;
        this.value = value;
    }
    
    NestedPair(List<NestedPair> pair) {
        this.pair = pair;
    
    }
}


Dictory which has folders and files - all html (50000)

Find every html which has phone in a specific format
(xxx)-xxx-xxxx





