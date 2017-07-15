package system.design;
public class GeoHashII {
    /**
     * 给一个encoded的String, 返回经纬度
     * @param geohash a base32 string
     * @return latitude and longitude a location coordinate pair
     */
    public double[] decode(String geohash) {
        // Write your code here
        String _base32 = "0123456789bcdefghjkmnpqrstuvwxyz";
        int[] mask = {16, 8, 4, 2, 1}; // 2^0, 2^1, .., 2^4
        double[] lon = {-180, 180};
        double[] lat = {-90, 90};
        boolean is_even = true;

        for (int i = 0; i < geohash.length(); i++) {
            int val = _base32.indexOf(geohash.charAt(i));
            for (int j = 0; j < 5; j++) {
                if (is_even) {
                    refine_interval(lon, val, mask[j]);
                } else {
                    refine_interval(lat, val, mask[j]);
                }
                is_even = !is_even;
            }
        }
        double[] location = {(lat[0] + lat[1]) / 2.0, (lon[0] + lon[1]) / 2.0};
        return location;
    }

    public void refine_interval(double[] interval, int val, int mask) {
        if ((val & mask) > 0) { // 看看那一位是0还是1
            interval[0] = (interval[0] + interval[1]) / 2.0; // left增大
        } else {
            interval[1] = (interval[0] + interval[1]) / 2.0; // right减小
        }
    }
}