package id.kelongmakassar.kelongmakassar.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.kelongmakassar.kelongmakassar.R;
import id.kelongmakassar.kelongmakassar.ui.games.GamesActivity;
import id.kelongmakassar.kelongmakassar.ui.track.list.TrackListActivity;
import id.kelongmakassar.kelongmakassar.ui.tutorial.TutorialActivity;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class LandingActivity extends AppCompatActivity {

    @BindView(R.id.layout_buttons) LinearLayout mButtonsLayout;
    @BindView(R.id.layout_container) LinearLayout mContainerLayout;
    @BindView(R.id.image_pull_down) ImageView mPullDownImageView;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, LandingActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        ButterKnife.bind(this);

        initLayout();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    private void initLayout() {
        mButtonsLayout.setVisibility(View.VISIBLE);

        mContainerLayout.setVisibility(View.GONE);
    }

    @OnClick(R.id.image_pull_down)
    void onPullDownClick() {
        mContainerLayout.setVisibility(View.VISIBLE);
        mPullDownImageView.setVisibility(View.GONE);
    }

    @OnClick(R.id.button_kelong)
    void onKelongButtonClick() {
        Intent intent = TrackListActivity.getStartIntent(this);
        startActivity(intent);
    }

    @OnClick(R.id.button_tutorial)
    void onTutorialButtonClick() {
        Intent intent = TutorialActivity.getStartIntent(this);
        startActivity(intent);
    }

    @OnClick(R.id.button_games)
    void onGamesButtonClick() {
        Intent intent = GamesActivity.getStartIntent(this);
        startActivity(intent);
    }
}
