// https://github.com/SkyTubeTeam/SkyTube/tree/2c717ab729ce5f2fec648c93b04d933e870c71d1/app/src/main/java/free/rm/skytube/gui/fragments/YouTubePlayerV2Fragment.java#L282-L305
public class TempClass {
                @Override
                public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
                    Logger.i(this, ">> onPlayerStateChanged " + playWhenReady + " state=" + playbackState);
                    videoIsPlaying = playbackState == Player.STATE_READY && playWhenReady;

                    if (videoIsPlaying) {
                        preventDeviceSleeping(true);
                        playbackSpeedController.updateMenu();
                    } else {
                        preventDeviceSleeping(false);
                    }

                    if (playbackStateListener != null) {
                        boolean videoIsPaused = playbackState == Player.STATE_READY && !playWhenReady;

                        if (videoIsPlaying) {
                            playbackStateListener.started();
                        } else if (videoIsPaused) {
                            playbackStateListener.paused();
                        } else {
                            playbackStateListener.ended();
                        }
                    }
                }

}