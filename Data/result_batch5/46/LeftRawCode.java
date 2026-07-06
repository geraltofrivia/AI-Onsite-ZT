// https://github.com/kdn251/interviews/tree/03fdcb2703ce72dc0606748733d0c13f09d41d21/uva/BackToIntermediateMath.java#L39-L58
public class TempClass {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int numberOfTestCases = input.nextInt();
		DecimalFormat formatter = new DecimalFormat("#0.000");
		for (int i = 0; i < numberOfTestCases; i++) {
			double distance = input.nextDouble();
			double riverSpeed = input.nextDouble();
			double boatSpeed = input.nextDouble();
			if (riverSpeed == 0 || boatSpeed == 0 || boatSpeed <= riverSpeed) {
				System.out.println("Case " + (i + 1) + ": can't determine");
			} else {
				double P1 = distance / boatSpeed;
				double P2 = distance
						/ Math.sqrt(boatSpeed * boatSpeed - riverSpeed
								* riverSpeed);
				System.out.print("Case " + (i + 1) + ": "
						+ formatter.format(Math.abs(P1 - P2)) + "\n");
			}
		}
	}

}