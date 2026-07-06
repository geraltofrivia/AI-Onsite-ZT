// https://github.com/QuantumBadger/RedReader/tree/ea0b596c5107dfd997bbb6b84951ff86a05992de/src/main/java/com/github/lzyzsd/circleprogress/DonutProgress.java#L132-L145
public class TempClass {
	private int measure(int measureSpec){
		int result;
		int mode = MeasureSpec.getMode(measureSpec);
		int size = MeasureSpec.getSize(measureSpec);
		if(mode == MeasureSpec.EXACTLY){
			result = size;
		}else{
			result = min_size;
			if(mode == MeasureSpec.AT_MOST){
				result = Math.min(result, size);
			}
		}
		return result;
	}

}