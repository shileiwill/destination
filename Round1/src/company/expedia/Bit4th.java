package company.expedia;

public class Bit4th {

	public static void main(String[] args) {
		Bit4th b4 = new Bit4th();
		int bit = b4.get4thBit(77);
		System.out.println(bit);
	}

	int get4thBit(int val) {
		int pivot = 8;
		
		return (val & pivot) == 0 ? 0 : 1;
	}
}
