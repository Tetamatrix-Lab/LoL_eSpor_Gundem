package com.erdem.kurumi.volley.Youtube;
import android.os.Bundle;
import android.widget.Toast;

import com.erdem.kurumi.volley.R;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

/**
 * Created by Kurumi on 13.3.2015.
 */
public class Izle extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener{

    public static final String API_KEY = ApiKey.YOUTUBE_API_KEY;
    private static String id;
    YouTubePlayerView youTubePlayerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_izle);
        String videoID = getIntent().getStringExtra("videoID");
        id=videoID;

        youTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtube_view);
        youTubePlayerView.initialize(API_KEY, this);
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult result) {
        Toast.makeText(this, "Bu videoyu izleyebilmeniz icin youtube player gereklidir...", Toast.LENGTH_LONG).show();
        youTubePlayerView.initialize(API_KEY, this);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {

        player.setPlayerStateChangeListener(playerStateChangeListener);
        player.setPlaybackEventListener(playbackEventListener);

        if (!wasRestored)
            player.cueVideo(id);
    }

    private YouTubePlayer.PlaybackEventListener playbackEventListener = new YouTubePlayer.PlaybackEventListener() {

        @Override
        public void onBuffering(boolean arg0) {}

        @Override
        public void onPaused() {}

        @Override
        public void onPlaying() {}

        @Override
        public void onSeekTo(int arg0) {}

        @Override
        public void onStopped() {}

    };

    private YouTubePlayer.PlayerStateChangeListener playerStateChangeListener = new YouTubePlayer.PlayerStateChangeListener() {

        @Override
        public void onAdStarted() {}

        @Override
        public void onError(YouTubePlayer.ErrorReason arg0) {}

        @Override
        public void onLoaded(String arg0) {}

        @Override
        public void onLoading() {}

        @Override
        public void onVideoEnded() {}

        @Override
        public void onVideoStarted() {}
    };



}