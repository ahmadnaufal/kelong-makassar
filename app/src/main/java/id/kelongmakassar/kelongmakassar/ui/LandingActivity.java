package id.kelongmakassar.kelongmakassar.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.kelongmakassar.kelongmakassar.R;
import id.kelongmakassar.kelongmakassar.ui.track.list.TrackListActivity;
import id.kelongmakassar.kelongmakassar.ui.tutorial.TutorialActivity;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class LandingActivity extends AppCompatActivity {

    @BindView(R.id.scrollview_help) ScrollView mHelpScrollView;
    @BindView(R.id.image_show_help) ImageView mShowHelpImageView;
    @BindView(R.id.layout_buttons) LinearLayout mButtonsLayout;

    private boolean isHelpShown;
    private boolean isMenuShown;

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
        isHelpShown = false;
        isMenuShown = false;

        mHelpScrollView.setVisibility(View.GONE);
        mButtonsLayout.setVisibility(View.GONE);
    }

    @OnClick(R.id.image_show_help)
    void onHelpClick() {
        isHelpShown = !isHelpShown;
        mHelpScrollView.setVisibility(isHelpShown ? View.VISIBLE : View.GONE);
        mShowHelpImageView.setImageResource(isHelpShown ? R.drawable.ic_info_kelong_selected : R.drawable.ic_info_kelong);
    }

    @OnClick(R.id.button_toggle_menu)
    void onToggleMenu() {
        isMenuShown = !isMenuShown;
        mButtonsLayout.setVisibility(isMenuShown ? View.VISIBLE : View.GONE);
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
        // TODO create intent to games activity
    }
}
