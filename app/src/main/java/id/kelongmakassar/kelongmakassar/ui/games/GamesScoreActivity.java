package id.kelongmakassar.kelongmakassar.ui.games;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.kelongmakassar.kelongmakassar.R;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class GamesScoreActivity extends AppCompatActivity {

    private static final String GAME_SCORE = "GAME_SCORE";

    @BindView(R.id.text_score) TextView mScoreText;
    @BindView(R.id.image_emotion) ImageView mEmotionImageView;

    public static Intent getStartIntent(Context context, int score) {
        Intent intent = new Intent(context, GamesScoreActivity.class);
        intent.putExtra(GAME_SCORE, score);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games_score);
        ButterKnife.bind(this);

        int score = getIntent().getIntExtra(GAME_SCORE, 0);
        initLayout(score);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    private void initLayout(int score) {
        mScoreText.setText(String.valueOf(score));

        int emoticonResId;
        if (score >= 100) {
            emoticonResId = R.drawable.emoticon_happy;
        } else if (score >= 75) {
            emoticonResId = R.drawable.emoticon_smile;
        } else if (score >= 50) {
            emoticonResId = R.drawable.emoticon_normal;
        } else if (score >= 25) {
            emoticonResId = R.drawable.emoticon_sad;
        } else {
            emoticonResId = R.drawable.emoticon_cry;
        }

        mEmotionImageView.setImageResource(emoticonResId);
    }

    @OnClick(R.id.button_menu)
    void onMenuClick() {
        finish();
    }

    @OnClick(R.id.button_play_again)
    void onPlayAgainClick() {
        Intent intent = GamesActivity.getStartIntent(this);
        startActivity(intent);

        finish();
    }
}
