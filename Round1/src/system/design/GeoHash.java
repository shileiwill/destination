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

2.1. 根据经纬度计算GeoHash二进制编码

地球纬度区间是[-90,90]， 北海公园的纬度是39.928167，可以通过下面算法对纬度39.928167进行逼近编码:

1）区间[-90,90]进行二分为[-90,0),[0,90]，称为左右区间，可以确定39.928167属于右区间[0,90]，给标记为1；

2）接着将区间[0,90]进行二分为 [0,45),[45,90]，可以确定39.928167属于左区间 [0,45)，给标记为0；

3）递归上述过程39.928167总是属于某个区间[a,b]。随着每次迭代区间[a,b]总在缩小，并越来越逼近39.928167；

4）如果给定的纬度x（39.928167）属于左区间，则记录0，如果属于右区间则记录1，这样随着算法的进行会产生一个序列1011100，序列的长度跟给定的区间划分次数有关。

同理，地球经度区间是[-180,180]，可以对经度116.389550进行编码。

2.2. 组码
通过上述计算，纬度产生的编码为10111 00011，经度产生的编码为11010 01011。偶数位放经度，奇数位放纬度，把2串编码组合生成新串：11100 11101 00100 01111。
最后使用用0-9、b-z（去掉a, i, l, o）这32个字母进行base32编码，首先将11100 11101 00100 01111转成十进制，对应着28、29、4、15，十进制对应的编码就是wx4g。
同理，将编码转换成经纬度的解码算法与之相反

当geohash base32编码长度为8时，精度在19米左右，而当编码长度为9时，精度在2米左右，编码长度需要根据数据情况进行选择。
 */
public class GeoHash {
    /**
     * @param latitude(纬度), longitude(经度) a location coordinate pair 
     * @param precision an integer between 1 to 12
     * @return a base32 string
     */
    public String encode(double latitude, double longitude, int precision) {
        String _base32 = "0123456789bcdefghjkmnpqrstuvwxyz";
        String lat_bin = getBin(latitude, -90, 90);
        String lng_bin = getBin(longitude, -180, 180); 
        
        StringBuffer hash_code = new StringBuffer();
        StringBuffer sb = new StringBuffer();
        
        for (int i = 0; i < 30; ++i) {// 长度为30
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
