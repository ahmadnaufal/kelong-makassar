package id.kelongmakassar.kelongmakassar.ui.games;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;
import id.kelongmakassar.kelongmakassar.R;

public class GamesScoreActivity extends AppCompatActivity {

    private static final String GAME_SCORE = "GAME_SCORE";

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
    }
}
