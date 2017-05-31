package company.uber;

import java.util.ArrayList;
import java.util.List;

/**
 * 412. Write a program that outputs the string representation of numbers from 1 to n.

But for multiples of three it should output “Fizz” instead of the number and for the multiples of five output “Buzz”. For numbers which are multiples of both three and five output “FizzBuzz”.

Example:

n = 15,

Return:
[
    "1",
    "2",
    "Fizz",
    "4",
    "Buzz",
    "Fizz",
    "7",
    "8",
    "Fizz",
    "Buzz",
    "11",
    "Fizz",
    "13",
    "14",
    "FizzBuzz"
]
 */
public class FizzBuzz {

	public static void main(String[] args) {
		new FizzBuzz().fizzBuzz(15);
	}

	public List<String> fizzBuzz(int N) {
		List<String> res = new ArrayList<String>();
		
		for (int i = 1; i <= N; i++) {
			boolean is3 = (i >= 3) && (i % 3 == 0); // i >= 3 is not required. 1 % 3 == 1
			boolean is5 = (i >= 5) && (i % 5 == 0);
			
			if (is3 && is5) {
				res.add("FizzBuzz");
			} else if (is3) {
				res.add("Fizz");
			} else if (is5) {
				res.add("Buzz");
			} else {
				res.add(i + "");
			}
		}
		for (String s : res) {
			System.out.println(s);
		}
		return res;
	}
}
