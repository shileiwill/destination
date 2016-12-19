package leetcode2.string;
/**
 * 273. Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 231 - 1.

For example,
123 -> "One Hundred Twenty Three"
12345 -> "Twelve Thousand Three Hundred Forty Five"
1234567 -> "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
Hint:

Did you see a pattern in dividing the number into chunk of words? For example, 123 and 123000.
Group the number by thousands (3 digits). You can write a helper function that takes a number less than 1000 and convert just that chunk to words.
There are many edge cases. What are some good test cases? Does your code work with input such as 0? Or 1000010? (middle chunk is zero and should not be printed out)
 */
public class IntegerToEnglishWords {
    public String numberToWords(int num) {
        if (num == 0) return "Zero";
        
        // Small to Large
        String[] LESS_THAN_20 = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
        String[] TENS = {"Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
        // Large to Small
        String[] HUNDREDS = {"Billion", "Million", "Thousand", "Hundred"};
        int[] RADIX = {1000000000, 1000000, 1000, 100};
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < RADIX.length; i++) {
            int times = num / RADIX[i];
            if (times == 0) {
                continue;
            }
            sb.append(numberToWords(times) + " "); // Need to recurse, it could be 13, like 13,000
            sb.append(HUNDREDS[i] + " ");
            num = num % RADIX[i];
        }
        
        if (num < 20) {
            sb.append(LESS_THAN_20[num] + " ");
        } else { // Must be < 100
            sb.append(TENS[num / 10 - 2] + " " + LESS_THAN_20[num % 10] + " ");
        }
        
        return sb.toString().trim();
    }
}