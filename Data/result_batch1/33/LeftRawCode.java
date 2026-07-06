// https://github.com/kdn251/interviews/tree/03fdcb2703ce72dc0606748733d0c13f09d41d21/uva/DigitCounting.java#L38-L60
public class TempClass {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int numberOfTestCases = input.nextInt();
		while (numberOfTestCases != 0) {
			int[] numbers = new int[10];
			int number = input.nextInt();
			for (int i = number; i > 0; i--) {
				int j = i;
				while (j != 0) {
					numbers[j % 10]++;
					j = j / 10;
				}
			}
			for (int i = 0; i < 10; i++) {
				if (i != 0) {
					System.out.print(" ");
				}
				System.out.print(numbers[i]);
			}
			System.out.println();
			numberOfTestCases--;
		}
	}

}