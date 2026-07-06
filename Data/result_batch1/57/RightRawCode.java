// https://github.com/yeriomin/YalpStore/tree/37f24c2fc2078cefc141d03c1379a650298ee2a8/app/src/main/java/com/github/yeriomin/yalpstore/task/LoadImageTask.java#L107-L116
public class TempClass {
                @Override
                public void onClick(View v) {
                    LoadImageTask task = new LoadImageTask();
                    task.setImageView(imageView);
                    task.setFadeInMillis(fadeInMillis);
                    task.setPlaceholder(placeholder);
                    task.setForceLoadImage(true);
                    task.setImageSource(imageSource);
                    task.executeOnExecutorIfPossible();
                }

}