// https://github.com/JorenSix/TarsosDSP/tree/052f429ecd0091103cdeaa495e3f3bb46542f8dd/core/src/main/java/be/tarsos/dsp/io/UniversalAudioInputStream.java#L39-L50
public class TempClass {
	@Override
	public long skip(long bytesToSkip) throws IOException {
		//the skip probably
		int bytesSkipped = 0;
		for(int i = 0 ; i < bytesToSkip ; i++){
			int theByte = underlyingStream.read();
			if(theByte!=-1){
				bytesSkipped++;
			}
		}
		return bytesSkipped;
	}

}