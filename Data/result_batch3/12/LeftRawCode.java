// https://github.com/zfdang/Android-Touch-Helper/tree/9a131ceda364e39ad283b848fdbdff7c03d54d9f/app/src/main/java/com/zfdang/touchhelper/TouchHelperServiceImpl.java#L790-L820
public class TempClass {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        btAddPosition.setEnabled(true);
                        targetParams.alpha = 0.9f;
                        windowManager.updateViewLayout(imageTarget, targetParams);
                        x = Math.round(event.getRawX());
                        y = Math.round(event.getRawY());
                        break;
                    case MotionEvent.ACTION_MOVE:
                        targetParams.x = Math.round(targetParams.x + (event.getRawX() - x));
                        targetParams.y = Math.round(targetParams.y + (event.getRawY() - y));
                        x = Math.round(event.getRawX());
                        y = Math.round(event.getRawY());
                        windowManager.updateViewLayout(imageTarget, targetParams);
                        positionDescription.packageName = currentPackageName;
                        positionDescription.activityName = currentActivityName;
                        positionDescription.x = targetParams.x + width;
                        positionDescription.y = targetParams.y + height;
                        tvPackageName.setText(positionDescription.packageName);
                        tvActivityName.setText(positionDescription.activityName);
                        tvPositionInfo.setText("X轴：" + positionDescription.x + "    " + "Y轴：" + positionDescription.y + "    " + "(其他参数默认)");
                        break;
                    case MotionEvent.ACTION_UP:
                        targetParams.alpha = 0.5f;
                        windowManager.updateViewLayout(imageTarget, targetParams);
                        break;
                }
                return true;
            }

}