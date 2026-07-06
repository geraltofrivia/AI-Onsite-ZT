// https://github.com/qii/weiciyuan/tree/b8f4de854b12c4160a850c2217c779609c5eb445/src/org/qii/weiciyuan/ui/adapter/CommentListAdapter.java#L82-L165
public class TempClass {
    @Override
    protected void bindViewData(final ViewHolder holder, int position) {
        Drawable drawable = bg.get(holder);
        if (drawable != null) {
            holder.listview_root.setBackgroundDrawable(drawable);
        } else {
            drawable = holder.listview_root.getBackground();
            bg.put(holder, drawable);
        }

        if (listView.getCheckedItemPosition() == position + 1) {
            holder.listview_root.setBackgroundColor(checkedBG);
        }

        final CommentBean comment = getList().get(position);

        UserBean user = comment.getUser();
        if (user != null) {
            holder.username.setVisibility(View.VISIBLE);
            if (!TextUtils.isEmpty(user.getRemark())) {
                holder.username.setText(new StringBuilder(user.getScreen_name()).append("(")
                        .append(user.getRemark()).append(")").toString());
            } else {
                holder.username.setText(user.getScreen_name());
            }
            if (!showOriStatus && !SettingUtility.getEnableCommentRepostListAvatar()) {
                holder.avatar.setLayoutParams(new RelativeLayout.LayoutParams(0, 0));
            } else {
                buildAvatar(holder.avatar, position, user);
            }
        } else {
            holder.username.setVisibility(View.INVISIBLE);
            holder.avatar.setVisibility(View.INVISIBLE);
        }

        holder.content.setText(comment.getListViewSpannableString());

        holder.time.setTime(comment.getMills());
        if (holder.source != null) {
            holder.source.setText(comment.getSourceString());
        }

        holder.repost_content.setVisibility(View.GONE);
        holder.repost_content_pic.setVisibility(View.GONE);

        CommentBean reply = comment.getReply_comment();
        if (holder.replyIV != null) {
            holder.replyIV.setVisibility(View.GONE);
        }
        if (reply != null && showOriStatus) {
            if (holder.repost_layout != null) {
                holder.repost_layout.setVisibility(View.VISIBLE);
            }
            holder.repost_flag.setVisibility(View.VISIBLE);
            holder.repost_content.setVisibility(View.VISIBLE);
            holder.repost_content.setText(reply.getListViewSpannableString());
            holder.repost_content.setTag(reply.getId());
        } else {

            MessageBean repost_msg = comment.getStatus();

            if (repost_msg != null && showOriStatus) {
                buildOriWeiboContent(repost_msg, holder, position);
            } else {
                if (holder.repost_layout != null) {
                    holder.repost_layout.setVisibility(View.GONE);
                }
                holder.repost_flag.setVisibility(View.GONE);
                if (holder.replyIV != null) {
                    holder.replyIV.setVisibility(View.VISIBLE);
                    holder.replyIV.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(),
                                    WriteReplyToCommentActivity.class);
                            intent.putExtra("token", GlobalContext.getInstance().getSpecialToken());
                            intent.putExtra("msg", comment);
                            getActivity().startActivity(intent);
                        }
                    });
                }
            }
        }
    }

}