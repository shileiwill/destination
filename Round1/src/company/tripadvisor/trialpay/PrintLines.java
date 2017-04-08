package company.tripadvisor.trialpay;

public class PrintLines {

	public static void main(String[] args) {

	}

	void printLines(int x, int y) {
		int rowStart = x - 1;
		int rowEnd = x + 1;
		int colStart = y - 1;
		int colEnd = y + 1;
		
		while (rowStart >= rowEnd && colStart >= colEnd) {
			for (int i = colStart; i <= colEnd; i++) {
				System.out.println(rowStart + ":" + i + "->");
			}
			rowStart--;
		}
	}
}
