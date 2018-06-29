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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.kelongmakassar.kelongmakassar.R;
import id.kelongmakassar.kelongmakassar.data.model.KaraokeQuestion;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link KaraokeGameFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class KaraokeGameFragment extends QuestionFragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_AUDIO_GUESS_QUESTION = "AUDIO_GUESS_QUESTION";

    @BindView(R.id.image_play_media) ImageView playMediaImageView;
    @BindView(R.id.recyclerview_answers) RecyclerView answersRecyclerView;

    private KaraokeQuestion mQuestion;

    private AnswersAdapter mAnswersAdapter;

    public KaraokeGameFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param question The question.
     * @return A new instance of fragment KaraokeGameFragment.
     */
    public static KaraokeGameFragment newInstance(KaraokeQuestion question) {
        KaraokeGameFragment fragment = new KaraokeGameFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_AUDIO_GUESS_QUESTION, question);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mQuestion = getArguments().getParcelable(ARG_AUDIO_GUESS_QUESTION);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_audio_guess_game, container, false);
        ButterKnife.bind(this, rootView);

        initLayoutWithQuestion();

        return rootView;
    }

    private void initLayoutWithQuestion() {
        String[] answers = mQuestion.getAnswerList();

        mAnswersAdapter = new AnswersAdapter(getActivity(), answers);
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
