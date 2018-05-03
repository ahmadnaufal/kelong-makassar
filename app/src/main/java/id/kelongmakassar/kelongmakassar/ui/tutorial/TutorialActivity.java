package id.kelongmakassar.kelongmakassar.ui.tutorial;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.kelongmakassar.kelongmakassar.R;
import id.kelongmakassar.kelongmakassar.data.model.Tutorial;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class TutorialActivity extends AppCompatActivity implements TutorialMvpView {

    @BindView(R.id.recyclerview_tutorial_list) RecyclerView tutorialListRecyclerView;

    private TutorialAdapter mTutorialAdapter;
    private TutorialPresenter mPresenter;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, TutorialActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
        ButterKnife.bind(this);

        initLayout();

        mPresenter = new TutorialPresenter(this);
        mPresenter.loadTutorials();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @OnClick(R.id.image_back)
    void onBackClick() {
        super.onBackPressed();
    }

    private void initLayout() {
        mTutorialAdapter = new TutorialAdapter(this);
        tutorialListRecyclerView.setAdapter(mTutorialAdapter);
        tutorialListRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        tutorialListRecyclerView.setNestedScrollingEnabled(false);
        mTutorialAdapter.setListener(new OnTutorialInteractionListener() {
            @Override
            public void onPlayButtonClicked(Tutorial tutorial) {

            }
        });
    }

    @Override
    public void onTutorialListLoaded(List<Tutorial> tutorialList) {
        mTutorialAdapter.setTutorialList(tutorialList);
        mTutorialAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDismiss() {

    }

    @Override
    public void onLoading() {

    }
}
