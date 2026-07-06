// https://github.com/qii/weiciyuan/tree/b8f4de854b12c4160a850c2217c779609c5eb445/src/org/qii/weiciyuan/ui/userinfo/EditMyProfileActivity.java#L80-L87
public class TempClass {
    private void initLayout() {
        layout = new Layout();
        layout.avatar = (ImageView) findViewById(R.id.avatar);
        layout.avatar.setOnClickListener(avatarOnClickListener);
        layout.nickname = (EditText) findViewById(R.id.nickname);
        layout.website = (EditText) findViewById(R.id.website);
        layout.info = (EditText) findViewById(R.id.info);
    }

}