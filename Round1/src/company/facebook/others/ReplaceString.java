package Facebook;

/**
 * Created by weixwu on 7/9/2017.
 */
public class ReplaceString {
    public String replaceSpace(String s){
        int rIndex = 0, wIndex = 0;
        char[] array = s.toCharArray();
        while (rIndex < s.length()){
            if (rIndex < s.length() - 2 && array[rIndex] == '%' && array[rIndex + 1] == '2' && array[rIndex + 2] == '0'){
                while (rIndex < s.length() - 2 && array[rIndex] == '%' && array[rIndex + 1] == '2' && array[rIndex + 2] == '0'){
                    rIndex += 3;
                }
                array[wIndex ++ ] = ' ';
            }else
                array[wIndex ++] = array[rIndex ++];
        }
        return new String(array).substring(0, wIndex);
    }

    public static void main(String args[]){
        ReplaceString rs = new ReplaceString();
        System.out.print(rs.replaceSpace("%20fhkj%2dgjt%20%20%20dgti%20"));
    }
}
