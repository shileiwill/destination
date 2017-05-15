package company.linkedin;

import java.util.BitSet;
/**
 * BitSet class implements a vector of bits that grows as needed. Each component of the bit set has a boolean value. 
 * The bits of a BitSet are indexed by nonnegative integers. Individual indexed bits can be examined, set, or cleared. 
 * One BitSet may be used to modify the contents of another BitSet through logical AND, logical inclusive OR, and logical exclusive OR operations.
 * By default, all bits in the set initially have the value false.
 * Every bit set has a current size, which is the number of bits of space currently in use by the bit set. 
 * Note that the size is related to the implementation of a bit set, so it may change with implementation. 
 * The length of a bit set relates to logical length of a bit set and is defined independently of implementation.
 * Unless otherwise noted, passing a null parameter to any of the methods in a BitSet will result in a NullPointerException. 
 * A BitSet is not safe for multithreaded use without external synchronization.
 * 
 * Initial pattern in bits1:
{0, 2, 4, 6, 8, 10, 12, 14}

Initial pattern in bits2:
{1, 2, 3, 4, 6, 7, 8, 9, 11, 12, 13, 14}

bits2 AND bits1:
{2, 4, 6, 8, 12, 14}

bits2 OR bits1:
{0, 2, 4, 6, 8, 10, 12, 14}

bits2 XOR bits1:
{}
 */
public class TryBitSet {

	public static void main(String[] args) {
	      BitSet bits1 = new BitSet(16);
	      BitSet bits2 = new BitSet(16);
	      
	      // set that position to true
	      for(int i = 0; i < 16; i++) {
	         if((i % 2) == 0) bits1.set(i);
	         if((i % 5) != 0) bits2.set(i);
	      }
	     
	      System.out.println("Initial pattern in bits1: ");
	      System.out.println(bits1);
	      System.out.println("\nInitial pattern in bits2: ");
	      System.out.println(bits2.toString()); // Print all TRUEs

	      // AND bits
	      bits2.and(bits1);
	      System.out.println("\nbits2 AND bits1: ");
	      System.out.println(bits2);

	      // OR bits
	      bits2.or(bits1);
	      System.out.println("\nbits2 OR bits1: ");
	      System.out.println(bits2);

	      // XOR bits
	      bits2.xor(bits1);
	      System.out.println("\nbits2 XOR bits1: ");
	      System.out.println(bits2);
	   }
}
