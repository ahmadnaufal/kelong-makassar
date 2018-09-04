package id.kelongmakassar.kelongmakassar.ui.tutorial;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.pierfrancescosoffritti.androidyoutubeplayer.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.YouTubePlayerView;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.listeners.YouTubePlayerInitListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.kelongmakassar.kelongmakassar.R;
import id.kelongmakassar.kelongmakassar.ui.LandingActivity;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class InfoKelongActivity extends AppCompatActivity {

    @BindView(R.id.youtube_player) YouTubePlayerView youTubePlayerView;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, InfoKelongActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_kelong);
        ButterKnife.bind(this);

        getLifecycle().addObserver(youTubePlayerView);

        youTubePlayerView.initialize(new YouTubePlayerInitListener() {
            @Override
            public void onInitSuccess(@NonNull final YouTubePlayer youTubePlayer) {
                youTubePlayer.addListener(new AbstractYouTubePlayerListener() {
                    @Override
                    public void onReady() {
                        String videoId = "pyLDjPTq87Q";
                        youTubePlayer.loadVideo(videoId, 0);
                    }
                });
            }
        }, true);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @OnClick(R.id.image_back)
    void onBackClick() {
        super.onBackPressed();
    }

    @OnClick(R.id.button_back_menu)
    void onBackToMenuClick() {
        Intent intent = LandingActivity.getStartIntent(this);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        youTubePlayerView.release();
    }
}
