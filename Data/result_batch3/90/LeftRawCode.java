// https://github.com/daimajia/AndroidSwipeLayout/tree/5f8678b04751fb8510a88586b22e07ccf64bca99/demo/src/main/java/com/daimajia/swipedemo/RecyclerViewExample.java#L94-L110
public class TempClass {
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_listview) {
            startActivity(new Intent(this, ListViewExample.class));
            finish();
            return true;
        } else if (id == R.id.action_gridview) {
            startActivity(new Intent(this, GridViewExample.class));
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}