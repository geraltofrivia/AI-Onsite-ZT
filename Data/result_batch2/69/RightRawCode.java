// https://github.com/mastodon/mastodon-android/tree/8f6ea2ea054105c31a06f16c018420d0cb3df94a/mastodon/src/main/java/org/joinmastodon/android/PushNotificationReceiver.java#L126-L282
public class TempClass {
	private void doNotify(Context context, PushNotification pn, String accountID, org.joinmastodon.android.model.Notification notification, Drawable avatar){
		NotificationManager nm=context.getSystemService(NotificationManager.class);
		Account self=AccountSessionManager.getInstance().getAccount(accountID).self;
		String accountName="@"+self.username+"@"+AccountSessionManager.getInstance().getAccount(accountID).domain;
		Notification.Builder builder;
		if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
			boolean hasGroup=false;
			int version=AccountSessionManager.get(accountID).getRawLocalPreferences().getInt("notificationChannelsVersion", 1);
			List<NotificationChannelGroup> channelGroups=nm.getNotificationChannelGroups();
			for(NotificationChannelGroup group:channelGroups){
				if(group.getId().equals(accountID)){
					hasGroup=true;
					break;
				}
			}
			if(!hasGroup || version!=2){
				NotificationChannelGroup group=new NotificationChannelGroup(accountID, accountName);
				nm.createNotificationChannelGroup(group);
				List<NotificationChannel> channels=Arrays.stream(PushNotification.Type.values())
						.map(type->{
							NotificationChannel channel=new NotificationChannel(accountID+"_"+type, context.getString(type.localizedName), NotificationManager.IMPORTANCE_DEFAULT);
							channel.setLightColor(context.getColor(R.color.primary_700));
							channel.enableLights(true);
							channel.setGroup(accountID);
							return channel;
						})
						.collect(Collectors.toList());
				nm.createNotificationChannels(channels);
				AccountSessionManager.get(accountID).getRawLocalPreferences().edit().putInt("notificationChannelsVersion", 2).apply();
			}
			builder=new Notification.Builder(context, accountID+"_"+pn.notificationType);
		}else{
			builder=new Notification.Builder(context)
					.setPriority(Notification.PRIORITY_DEFAULT)
					.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE);
		}
		Intent contentIntent=new Intent(context, MainActivity.class);
		contentIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		contentIntent.putExtra("fromNotification", true);
		contentIntent.putExtra("accountID", accountID);
		if(notification!=null){
			contentIntent.putExtra("notification", Parcels.wrap(notification));
		}
		builder.setContentTitle(pn.title)
				.setContentText(pn.body)
				.setStyle(new Notification.BigTextStyle().bigText(pn.body))
				.setSmallIcon(R.drawable.ic_ntf_logo)
				.setContentIntent(PendingIntent.getActivity(context, (accountID+pn.notificationId).hashCode() & 0xFFFF, contentIntent, PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT))
				.setWhen(notification==null ? System.currentTimeMillis() : notification.createdAt.toEpochMilli())
				.setShowWhen(true)
				.setCategory(Notification.CATEGORY_SOCIAL)
				.setAutoCancel(true)
				.setOnlyAlertOnce(true)
				.setLights(context.getColor(R.color.primary_700), 500, 1000)
				.setColor(context.getColor(R.color.primary_700))
				.setGroup(accountID);
		if(avatar!=null){
			builder.setLargeIcon(UiUtils.getBitmapFromDrawable(avatar));
		}
		if(AccountSessionManager.getInstance().getLoggedInAccounts().size()>1){
			builder.setSubText(accountName);
		}
		String notificationTag=accountID+"_"+(notification==null ? 0 : notification.id);
		if(notification!=null && (notification.type==NotificationType.MENTION)){
			ArrayList<String> mentions=new ArrayList<>();
			String ownID=AccountSessionManager.getInstance().getAccount(accountID).self.id;
			if(!notification.status.account.id.equals(ownID))
				mentions.add('@'+notification.status.account.acct);
			for(Mention mention:notification.status.mentions){
				if(mention.id.equals(ownID))
					continue;
				String m='@'+mention.acct;
				if(!mentions.contains(m))
					mentions.add(m);
			}
			String replyPrefix=mentions.isEmpty() ? "" : TextUtils.join(" ", mentions)+" ";

			if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){
				Intent replyIntent=new Intent(context, NotificationActionHandlerService.class);
				replyIntent.putExtra("action", "reply");
				replyIntent.putExtra("account", accountID);
				replyIntent.putExtra("post", notification.status.id);
				replyIntent.putExtra("notificationTag", notificationTag);
				replyIntent.putExtra("visibility", notification.status.visibility.toString());
				replyIntent.putExtra("replyPrefix", replyPrefix);
				builder.addAction(new Notification.Action.Builder(Icon.createWithResource(context, R.drawable.ic_reply_24px),
						context.getString(R.string.button_reply), PendingIntent.getService(context, (accountID+pn.notificationId+"reply").hashCode(), replyIntent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_MUTABLE))
								.addRemoteInput(new RemoteInput.Builder("replyText").setLabel(context.getString(R.string.button_reply)).build())
								.build());
			}

			Intent favIntent=new Intent(context, NotificationActionHandlerService.class);
			favIntent.putExtra("action", "favorite");
			favIntent.putExtra("account", accountID);
			favIntent.putExtra("post", notification.status.id);
			favIntent.putExtra("notificationTag", notificationTag);
			builder.addAction(new Notification.Action.Builder(Icon.createWithResource(context, R.drawable.ic_star_24px),
					context.getString(R.string.button_favorite), PendingIntent.getService(context, (accountID+pn.notificationId+"favorite").hashCode(), favIntent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE)).build());

			PendingIntent boostActionIntent;
			if(notification.status.visibility!=StatusPrivacy.DIRECT){
				Intent boostIntent=new Intent(context, NotificationActionHandlerService.class);
				boostIntent.putExtra("action", "boost");
				boostIntent.putExtra("account", accountID);
				boostIntent.putExtra("post", notification.status.id);
				boostIntent.putExtra("notificationTag", notificationTag);
				boostActionIntent=PendingIntent.getService(context, (accountID+pn.notificationId+"boost").hashCode(), boostIntent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
			}else{
				boostActionIntent=null;
			}
			builder.addAction(new Notification.Action.Builder(Icon.createWithResource(context, R.drawable.ic_boost_24px),
					context.getString(R.string.button_reblog), boostActionIntent).build());
		}
		nm.notify(notificationTag, NOTIFICATION_ID, builder.build());

		StatusBarNotification[] activeNotifications=nm.getActiveNotifications();
		ArrayList<String> summaryLines=new ArrayList<>();
		int notificationCount=0;
		for(StatusBarNotification sbn:activeNotifications){
			String tag=sbn.getTag();
			if(tag!=null && tag.startsWith(accountID+"_")){
				if((sbn.getNotification().flags & Notification.FLAG_GROUP_SUMMARY)==0){
					if(summaryLines.size()<5){
						summaryLines.add(sbn.getNotification().extras.getString("android.title"));
					}
					notificationCount++;
				}
			}
		}

		if(summaryLines.size()>1){
			Notification.Builder summaryBuilder;
			if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
				summaryBuilder=new Notification.Builder(context, accountID+"_"+pn.notificationType);
			}else{
				summaryBuilder=new Notification.Builder(context)
						.setPriority(Notification.PRIORITY_DEFAULT);
			}
			Notification.InboxStyle inboxStyle=new Notification.InboxStyle();
			for(String line:summaryLines){
				inboxStyle.addLine(line);
			}
			summaryBuilder.setContentTitle(context.getString(R.string.app_name))
					.setContentText(context.getResources().getQuantityString(R.plurals.x_new_notifications, notificationCount, notificationCount))
					.setSmallIcon(R.drawable.ic_ntf_logo)
					.setColor(context.getColor(R.color.primary_700))
					.setContentIntent(PendingIntent.getActivity(context, accountID.hashCode() & 0xFFFF, contentIntent, PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT))
					.setWhen(notification==null ? System.currentTimeMillis() : notification.createdAt.toEpochMilli())
					.setShowWhen(true)
					.setCategory(Notification.CATEGORY_SOCIAL)
					.setAutoCancel(true)
					.setGroup(accountID)
					.setGroupSummary(true)
					.setStyle(inboxStyle.setSummaryText(accountName));
			nm.notify(accountID+"_summary", NOTIFICATION_ID, summaryBuilder.build());
		}
	}

}