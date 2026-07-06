// https://github.com/gyoogle/tech-interview-for-developer/tree/41a7e91356adae7ee598d5ee3c27438449ce55d8/Algorithm/code/Heap.java#L22-L55
public class TempClass {
	static int remove(int[] arr) {
		if(heapSize == 0) return 0;
		
		int rm = arr[1];
		arr[1] = arr[heapSize];
		arr[heapSize--] = 0;
		
		for (int i = 1; i*2 <= heapSize;) {
			
			if(i*2+1 <= heapSize) {
			
				if(arr[i] < arr[i*2] && arr[i] < arr[i*2+1]) break;
				
				else if(arr[i*2] < arr[i*2+1]) {
					swap(i, i*2);
					i = i*2;
				}
				else {
					swap(i, i*2+1);
					i = i*2+1;
				}
			}
			else {
				if(arr[i] > arr[i*2]) {
					swap(i, i*2);
					i = i*2;
				}
				else
					break;
			}
		}
		
		return rm;
	}

}