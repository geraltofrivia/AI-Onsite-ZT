// https://github.com/JZ-Darkal/AndroidHttpCapture/tree/e0aa4f6fcda60adf4f1c77d2ecadc6c383ba1a86/app/src/main/java/cn/darkal/networkdiagnosis/Fragment/PreviewFragment.java#L172-L190
public class TempClass {
                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {
                    harEntryList.clear();//清除原始数据
                    if(results.values instanceof List){
                        harEntryList.addAll((List<HarEntry>) results.values);//将过滤结果添加到这个对象
                    }
                    if (results.count > 0) {
                        previewAdapter.notifyDataSetChanged();//有关键字的时候刷新数据
                    } else {
                        //关键字不为零但是过滤结果为空刷新数据
                        if (constraint.length() != 0) {
                            previewAdapter.notifyDataSetChanged();
                            return;
                        }
                        //加载复制的数据，即为最初的数据
                        harEntryList.addAll(harLog.getEntries());
                        previewAdapter.notifyDataSetChanged();
                    }
                }

}