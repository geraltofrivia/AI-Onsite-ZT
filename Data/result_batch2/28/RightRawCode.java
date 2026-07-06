// https://github.com/NeoApplications/Neo-Launcher/tree/bae394fac15c0eea0eb3d202e1272a504ee2b76b/src/com/android/launcher3/util/FlingAnimation.java#L87-L97
public class TempClass {
            @Override
            public float getInterpolation(float t) {
                if (mCount < 0) {
                    mCount++;
                } else if (mCount == 0) {
                    mOffset = Math.min(0.5f, (float) (AnimationUtils.currentAnimationTimeMillis() -
                            startTime) / duration);
                    mCount++;
                }
                return Math.min(1f, mOffset + t);
            }

}