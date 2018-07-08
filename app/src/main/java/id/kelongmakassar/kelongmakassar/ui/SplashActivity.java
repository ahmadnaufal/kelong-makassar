package id.kelongmakassar.kelongmakassar.ui;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;
import id.kelongmakassar.kelongmakassar.R;

public class SplashActivity extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startOnboarding();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

    private void startOnboarding() {
        Intent intent = OnboardingActivity.getStartIntent(this);
        startActivity(intent);

        finish();
    }
}
