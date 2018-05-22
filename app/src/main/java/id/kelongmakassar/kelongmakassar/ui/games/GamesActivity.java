package id.kelongmakassar.kelongmakassar.ui.games;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;
import id.kelongmakassar.kelongmakassar.R;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class GamesActivity extends AppCompatActivity {

    private int mScore;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, GamesActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games);
        ButterKnife.bind(this);

        mScore = 0;
        // generate quiz by factory
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @OnClick(R.id.image_back)
    void onBackClick() {
        super.onBackPressed();
    }

    // called if user has finished the quiz
    private void onFinish() {
        Intent intent = GamesScoreActivity.getStartIntent(this, mScore);
        startActivity(intent);

        finish();
    }
}
