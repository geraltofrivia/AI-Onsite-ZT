// https://github.com/kabouzeid/Phonograph/tree/d156872cc5ac7f5f295b5fad361974c5fea45631/app/src/main/java/com/kabouzeid/gramophone/preferences/NowPlayingScreenPreferenceDialog.java#L97-L112
public class TempClass {
        @Override
        @NonNull
        public Object instantiateItem(@NonNull ViewGroup collection, int position) {
            NowPlayingScreen nowPlayingScreen = NowPlayingScreen.values()[position];

            LayoutInflater inflater = LayoutInflater.from(context);
            ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.preference_now_playing_screen_item, collection, false);
            collection.addView(layout);

            ImageView image = layout.findViewById(R.id.image);
            TextView title = layout.findViewById(R.id.title);
            image.setImageResource(nowPlayingScreen.drawableResId);
            title.setText(nowPlayingScreen.titleRes);

            return layout;
        }

}