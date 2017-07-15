package system.design;
/**
GeoHashing: http://www.cnblogs.com/LBSer/p/3310455.html

When you have some binary data that you want to ship across a network, you generally don't do it by just streaming the bits and bytes 
over the wire in a raw format. Why? because some media are made for streaming text. You never know -- 
some protocols may interpret your binary data as control characters (like a modem), or your binary data could be screwed up 
because the underlying protocol might think that you've entered a special character combination (like how FTP translates line endings).

So to get around this, people encode the binary data into characters. Base64 is one of these types of encodings.

Why 64?
Because you can generally rely on the same 64 characters being present in many character sets, and you can be reasonably confident that 
your data's going to end up on the other side of the wire uncorrupted.
It's basically a way of encoding arbitrary binary data in ASCII text. It takes 4 characters per 3 bytes of data, plus potentially 
a bit of padding at the end.

Essentially each 6 bits of the input is encoded in a 64-character alphabet. The "standard" alphabet uses A-Z, a-z, 0-9 and + and /, 
with = as a padding character. There are URL-safe variants.

base64取6 bits, base32 取 5 bits 
The main advantage of b32 over b64 is that it is much more human-readable. 
That's not much of an advantage because the data will typically be processed by computers, 
hence the relative rarity of b32 versus b64 (which is more efficient space-wise).
 */
public class GeoHash {
    /**
     * @param latitude, longitude a location coordinate pair 
     * @param precision an integer between 1 to 12
     * @return a base32 string
     */
    public String encode(double latitude, double longitude, int precision) {
        String _base32 = "0123456789bcdefghjkmnpqrstuvwxyz";
        String lat_bin = getBin(latitude, -90, 90);
        String lng_bin = getBin(longitude, -180, 180); // 长度为30
        
        StringBuffer hash_code = new StringBuffer();
        StringBuffer sb = new StringBuffer();
        
        for (int i = 0; i < 30; ++i) {
            sb.append(lng_bin.charAt(i));
            sb.append(lat_bin.charAt(i)); // latitude与longitude交叉 mix
        }

        for (int i = 0; i < 60; i += 5) { // 因为是base32, 所以每5位是一个section
            int index = binaryToInteger(sb.substring(i, i + 5));
            hash_code.append(_base32.charAt(index));
        }
        return hash_code.substring(0, precision); // 根据精度，截取前边的部分
    }

    public int binaryToInteger(String bin) {
        return Integer.parseInt(bin, 2); // radix is 2, 返回一个10进制的数
    }

    public String getBin(double value, double left, double right) {
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < 30; i++) {
            double mid = (left + right) / 2.0;
            if (value > mid) {
                left = mid;
                sb.append("1"); // 结果是一堆0,1
            } else {
                right = mid;
                sb.append("0");
            }
        }
        
        return sb.toString();
    }
}
