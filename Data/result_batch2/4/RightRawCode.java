// https://github.com/MewX/light-novel-library_Wenku8_Android/tree/68ea337ae0e34cec1a7a27bf351e4d0465b394aa/eclipse-android-old/LightNovelLibrary/src/org/mewx/lightnovellibrary/activity/SettingFragment.java#L380-L397
public class TempClass {
		public void updateDrawState(TextPaint paint) {
			paint.setStyle(Paint.Style.FILL);
			float width = paint.getTextSize() * colors.length;
			if (shader == null) {
				shader = new LinearGradient(0, 0, 0, width, colors, null,
						Shader.TileMode.MIRROR);
			}
			matrix.reset();
			matrix.setRotate(90);
			matrix.postTranslate(width * translateXPercentage, 0);
			shader.setLocalMatrix(matrix);
			paint.setShader(shader);
		}
	}

	@Override
	public void onResume() {
		super.onResume();

}