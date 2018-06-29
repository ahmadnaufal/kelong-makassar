package id.kelongmakassar.kelongmakassar.ui.games;

import android.os.Bundle;
import android.support.annotation.NonNull;
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
import id.kelongmakassar.kelongmakassar.data.model.SongPartQuestion;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SongPartGameFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SongPartGameFragment extends QuestionFragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String SONG_PART_QUESTION = "SongPartGameFragment.SONG_PART_QUESTION";

    @BindView(R.id.text_kelong_question) TextView kelongQuestionTextView;
    @BindView(R.id.recyclerview_answers) RecyclerView answersRecyclerView;
    @BindView(R.id.image_play_media) ImageView playMediaImageView;

    private SongPartQuestion mQuestion;

    private AnswersAdapter mAnswersAdapter;

    public SongPartGameFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param question The question for this fragment.
     * @return A new instance of fragment SongPartGameFragment.
     */
    public static SongPartGameFragment newInstance(SongPartQuestion question) {
        SongPartGameFragment fragment = new SongPartGameFragment();
        Bundle args = new Bundle();
        args.putParcelable(SONG_PART_QUESTION, question);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mQuestion = getArguments().getParcelable(SONG_PART_QUESTION);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_song_part_game, container, false);
        ButterKnife.bind(this, rootView);

        initLayout();

        return rootView;
    }

    private void initLayout() {
        kelongQuestionTextView.setText(mQuestion.getQuestion());

        mAnswersAdapter = new AnswersAdapter(getActivity(), mQuestion.getAnswerList());
        answersRecyclerView.setAdapter(mAnswersAdapter);
        answersRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2, LinearLayoutManager.VERTICAL, false));
        mAnswersAdapter.setListener(new OnAnswerClickedListener() {
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
