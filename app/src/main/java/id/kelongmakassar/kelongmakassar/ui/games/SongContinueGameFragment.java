package id.kelongmakassar.kelongmakassar.ui.games;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.kelongmakassar.kelongmakassar.R;
import id.kelongmakassar.kelongmakassar.data.model.SongContinueQuestion;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SongContinueGameFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SongContinueGameFragment extends QuestionFragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String SONG_CONTINUE_FRAGMENT = "SongContinueGameFragment.SONG_CONTINUE_FRAGMENT";

    @BindView(R.id.text_kelong_question) TextView kelongQuestionTextView;
    @BindView(R.id.recyclerview_answers) RecyclerView answersRecyclerView;
    @BindView(R.id.image_play_media) ImageView playMediaImageView;

    private SongContinueQuestion mQuestion;

    private AnswersSongAdapter mAnswersSongAdapter;

    public SongContinueGameFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param question Parameter 1.
     * @return A new instance of fragment SongContinueGameFragment.
     */
    public static SongContinueGameFragment newInstance(SongContinueQuestion question) {
        SongContinueGameFragment fragment = new SongContinueGameFragment();
        Bundle args = new Bundle();
        args.putParcelable(SONG_CONTINUE_FRAGMENT, question);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mQuestion = getArguments().getParcelable(SONG_CONTINUE_FRAGMENT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_song_continue_game, container, false);
        ButterKnife.bind(this, rootView);

        initLayout();

        return rootView;
    }

    private void initLayout() {
        kelongQuestionTextView.setText(mQuestion.getQuestion());

        mAnswersSongAdapter = new AnswersSongAdapter(getActivity(), mQuestion.getAnswerSongList());
        answersRecyclerView.setAdapter(mAnswersSongAdapter);
        answersRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2, LinearLayoutManager.VERTICAL, false));
        mAnswersSongAdapter.setListener(new OnSongAnswerClickedListener() {
            @Override
            public boolean onPlayClickListener(int resId) {
                return mAnswerClickedListener != null && mAnswerClickedListener.onPlayButtonPressed(resId);
            }

            @Override
            public void onAnswerChosen(int index) {
                if (mAnswerClickedListener != null) {
                    mAnswerClickedListener.onAnswer(index);
                }
            }
        });
    }

    @OnClick(R.id.image_play_media)
    void onButtonPressed() {
        if (mAnswerClickedListener != null) {
            boolean isPlayed = mAnswerClickedListener.onPlayButtonPressed(mQuestion.getResId());
            if (isPlayed) {
                changeButtonToPause();
            } else {
                changeButtonToPlay();
            }
        }
    }

    private void changeButtonToPause() {
        playMediaImageView.setImageResource(R.drawable.ic_pause);
    }

    private void changeButtonToPlay() {
        playMediaImageView.setImageResource(R.drawable.ic_play);
    }
}
