// https://github.com/weixiansen574/HybridFileXfer/tree/7de43517d8dd5cc2ccc24ccb11060b1ee68dd952/HybridFileXfer-Android/app/src/main/java/top/weixiansen574/hybridfilexfer/TransferActivity.java#L55-L130
public class TempClass {
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_transfer);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        leftList = findViewById(R.id.rv_left_files);
        rightList = findViewById(R.id.rv_right_files);
        shadowGroupLeft = findViewById(R.id.shadow_group_left);
        shadowGroupRight = findViewById(R.id.shadow_group_right);
        shadowGroupLeft.setVisibility(View.INVISIBLE);
        Toolbar leftSelectToolbar = findViewById(R.id.toolbar_select_left);
        Toolbar rightSelectToolbar = findViewById(R.id.toolbar_select_right);

        server = HFXServer.instance;
        if (server == null) {
            Toast.makeText(this, R.string.fu_wu_wei_yun_xing, Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        //初始化左边
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        leftList.setLayoutManager(layoutManager);
        OnTouchListener onTouchListener = new OnTouchListener(this, true);
        leftRVAdapter = new LocalFileSelectAdapter(context, findViewById(R.id.loading_left),
                leftList, layoutManager, leftSelectToolbar, onTouchListener, server);
        leftList.setAdapter(leftRVAdapter);
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.list_anim);
        LayoutAnimationController layoutAnimationController = new LayoutAnimationController(animation);
        layoutAnimationController.setOrder(LayoutAnimationController.ORDER_NORMAL);
        layoutAnimationController.setDelay(0.2f);
        leftList.setLayoutAnimation(layoutAnimationController);
        leftList.setOnTouchListener(onTouchListener);

        //初始化右边
        layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        rightList.setLayoutManager(layoutManager);
        onTouchListener = new OnTouchListener(this, false);
        rightRVAdapter = new RemoteFileSelectAdapter(context, findViewById(R.id.loading_right),
                rightList, layoutManager, rightSelectToolbar, onTouchListener, server);
        rightList.setAdapter(rightRVAdapter);
        animation = AnimationUtils.loadAnimation(context, R.anim.list_anim);
        layoutAnimationController = new LayoutAnimationController(animation);
        layoutAnimationController.setOrder(LayoutAnimationController.ORDER_NORMAL);
        layoutAnimationController.setDelay(0.2f);
        rightList.setLayoutAnimation(layoutAnimationController);
        rightList.setOnTouchListener(onTouchListener);

        leftRVAdapter.setOnToTransferListener((selectedItems, dir) -> new AlertDialog.Builder(context)
                .setTitle(R.string.que_ren_chuan_shu)
                .setMessage(getString(R.string.chuan_shu_dao_dian_nao_mu_lu, selectedItems.size(), rightRVAdapter.getCurrentDir()))
                .setPositiveButton(R.string.ok, (dialog, which) -> {
                    leftRVAdapter.cancelSelect();
                    sendFilesToRemote(selectedItems, dir, rightRVAdapter.getCurrentDirectory());
                })
                .setNegativeButton(R.string.cancel, null)
                .show());
        rightRVAdapter.setOnToTransferListener((selectedItems, dir) -> new AlertDialog.Builder(context)
                .setTitle(R.string.que_ren_chuan_shu)
                .setMessage(getString(R.string.chuan_shu_dao_shou_ji_mu_lu, selectedItems.size(), leftRVAdapter.getCurrentDir()))
                .setPositiveButton(R.string.ok, (dialog, which) -> {
                    rightRVAdapter.cancelSelect();
                    sendFilesToShelf(selectedItems, leftRVAdapter.getCurrentDirectory(), dir);
                })
                .setNegativeButton(R.string.cancel, null)
                .show());
    }

    private void sendFilesToRemote(List<RemoteFile> files, Directory localDir, Directory remoteDir) {

}