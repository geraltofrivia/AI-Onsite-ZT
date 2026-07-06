// https://github.com/CeuiLiSA/Pixiv-Shaft/tree/95ce81191beee56f4f66fa964a7c6c8dd9d23646/app/src/main/java/ceui/lisa/fragments/FragmentMultiDownload.java#L51-L163
public class TempClass {
    @Override
    public void initView() {
        super.initView();
        baseBind.toolbar.inflateMenu(R.menu.download_menu);
        baseBind.toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.action_1) {
                    for (int i = 0; i < allItems.size(); i++) {
                        if (!allItems.get(i).isChecked()) {
                            allItems.get(i).setChecked(true);
                        }
                    }
                    mAdapter.notifyDataSetChanged();
                } else if (item.getItemId() == R.id.action_2) {
                    for (int i = 0; i < allItems.size(); i++) {
                        if (allItems.get(i).isChecked()) {
                            allItems.get(i).setChecked(false);
                        }
                    }
                    mAdapter.notifyDataSetChanged();
                } else if (item.getItemId() == R.id.action_3) {
                    StringBuilder content = new StringBuilder();
                    for (IllustsBean illustsBean : allItems) {
                        if (illustsBean.isChecked()) {
                            if (illustsBean.getPage_count() == 1) {
                                content.append(illustsBean.getMeta_single_page().getOriginal_image_url());
                                content.append("\n");
                            } else {
                                for (int i = 0; i < illustsBean.getPage_count(); i++) {
                                    content.append(illustsBean.getMeta_pages().get(i).getImage_urls().getOriginal());
                                    content.append("\n");
                                }
                            }
                        }
                    }
                    String result = content.toString();
                    if (TextUtils.isEmpty(result)) {
                        Common.showToast("没有选择任何作品");
                    } else {
                        //不需要下载txt文件
                        IllustDownload.downloadFile((BaseActivity<?>) mContext,
                                System.currentTimeMillis() + "_download_tasks.txt", result,
                                new Callback<Uri>() {
                                    @Override
                                    public void doSomething(Uri t) {
                                        new Share2.Builder(mActivity)
                                                .setContentType(ShareContentType.FILE)
                                                .setShareFileUri(t)
                                                .setTitle("Share File")
                                                .build()
                                                .shareBySystem();
                                    }
                                });
                    }
                } else if (item.getItemId() == R.id.action_4) {
                    for (IllustsBean allItem : allItems) {
                        BatchStarTask task = new BatchStarTask(allItem.getUser().getName(),
                                allItem.getId(), 0);
                        Worker.get().addTask(task);
                    }
                    Worker.get().setFinalFeedBack(new FeedBack() {
                        @Override
                        public void doSomething() {
                            Common.showToast("全部收藏成功");
                        }
                    });
                    Worker.get().start();
                } else if (item.getItemId() == R.id.action_5) {
                    for (IllustsBean allItem : allItems) {
                        BatchStarTask task = new BatchStarTask(allItem.getUser().getName(),
                                allItem.getId(), 1);
                        Worker.get().addTask(task);
                    }
                    Worker.get().setFinalFeedBack(new FeedBack() {
                        @Override
                        public void doSomething() {
                            Common.showToast("全部取消收藏成功");
                        }
                    });
                    Worker.get().start();
                } else if (item.getItemId() == R.id.action_6) {
                    for (int i = 0; i < allItems.size(); i++) {
                        allItems.get(i).setChecked(allItems.get(i).isIs_bookmarked());
                    }
                    mAdapter.notifyDataSetChanged();
                } else if (item.getItemId() == R.id.action_7) {
                    for (int i = 0; i < allItems.size(); i++) {
                        allItems.get(i).setChecked(!Common.isIllustDownloaded(allItems.get(i)));
                    }
                    mAdapter.notifyDataSetChanged();
                }
                return false;
            }
        });
        baseBind.startDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IllustDownload.downloadCheckedIllustAllPages(allItems, (BaseActivity<?>) mContext);
            }
        });

        MyReceiver receiver = new MyReceiver();
        dragSelectTouchListener = DragSelectTouchListener.Companion.create(
                mContext, receiver, null);
        baseBind.recyclerView.addOnItemTouchListener(dragSelectTouchListener);

        RecyclerView.ItemAnimator itemAnimator = mRecyclerView.getItemAnimator();
        if (itemAnimator != null) {
            itemAnimator.setChangeDuration(0);
            ((SimpleItemAnimator) itemAnimator).setSupportsChangeAnimations(false);
        }
    }

}