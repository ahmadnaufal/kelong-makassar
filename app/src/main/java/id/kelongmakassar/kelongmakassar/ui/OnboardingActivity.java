package id.kelongmakassar.kelongmakassar.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.kelongmakassar.kelongmakassar.R;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class OnboardingActivity extends AppCompatActivity {

    @BindView(R.id.carousel_onboarding) RollPagerView carouselOnboardingView;
    @BindView(R.id.button_start) Button startButton;

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
        carouselOnboardingView.setHintView(new ColorPointHintView(this, ContextCompat.getColor(this, R.color.colorPrimary), ContextCompat.getColor(this, R.color.colorAccent)));

        carouselOnboardingView.getViewPager().addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == 3) {
                    startButton.setVisibility(View.VISIBLE);
                } else {
                    startButton.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
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
