// https://github.com/Col-E/Recaf/tree/391ceb6307c3dd052947a1a158baa0e3e270ea71/recaf-ui/src/main/java/software/coley/recaf/ui/pane/editing/media/AudioPane.java#L118-L129
public class TempClass {
	private static String formatTime(int time) {
		int minutes = time / 60;
		int seconds = time - minutes * 60;
		String formattedTime = "";
		if (minutes < 10)
			formattedTime += "0";
		formattedTime += minutes + ":";
		if (seconds < 10)
			formattedTime += "0";
		formattedTime += seconds;
		return formattedTime;
	}

}