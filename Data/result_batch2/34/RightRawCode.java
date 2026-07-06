// https://github.com/Etar-Group/Etar-Calendar/tree/b428755a98ddd03e7ed87d3645faaa2486e03bf4/app/src/main/java/com/android/calendar/event/EventLocationAdapter.java#L466-L475
public class TempClass {
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mResultList.clear();
            if (results != null && results.count > 0) {
                mResultList.addAll((ArrayList<Result>) results.values);
                notifyDataSetChanged();
            } else {
                notifyDataSetInvalidated();
            }
        }

}