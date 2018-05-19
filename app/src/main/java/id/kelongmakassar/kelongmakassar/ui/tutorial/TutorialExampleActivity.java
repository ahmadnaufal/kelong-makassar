package id.kelongmakassar.kelongmakassar.ui.tutorial;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;
import id.kelongmakassar.kelongmakassar.R;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class TutorialExampleActivity extends AppCompatActivity {

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, TutorialExampleActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial_example);
        ButterKnife.bind(this);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @OnClick(R.id.button_info_kelong)
    void onInfoKelongClick() {
        Intent intent = InfoKelongActivity.getStartIntent(this);
        startActivity(intent);
    }

    @OnClick(R.id.image_back)
    void onBackClick() {
        super.onBackPressed();
    }
}
