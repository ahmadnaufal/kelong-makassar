package id.kelongmakassar.kelongmakassar.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jude.rollviewpager.RollPagerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.kelongmakassar.kelongmakassar.R;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class OnboardingActivity extends AppCompatActivity {

    @BindView(R.id.carousel_onboarding) RollPagerView carouselOnboardingView;

    private static final int NUM_PAGES = 4;

    private PagerAdapter mPagerAdapter;
    private String[] descArray;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, OnboardingActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);
        ButterKnife.bind(this);

        descArray = getResources().getStringArray(R.array.text_array_kelong_makassar_desc);

        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());

        carouselOnboardingView.setAdapter(mPagerAdapter);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @OnClick(R.id.button_start)
    void onStartClick() {
        Intent intent = LandingActivity.getStartIntent(this);
        startActivity(intent);

        finish();
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return OnboardingFragment.newInstance(descArray[position]);
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}
