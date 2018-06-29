package id.kelongmakassar.kelongmakassar.ui.games;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.kelongmakassar.kelongmakassar.R;
import id.kelongmakassar.kelongmakassar.data.model.TextQuestion;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TextGameFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TextGameFragment extends QuestionFragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_TEXT_QUESTION = "TEXT_QUESTION";

    @BindView(R.id.text_question_title) TextView questionTitleTextView;
    @BindView(R.id.recyclerview_answers) RecyclerView answersRecyclerView;

    private TextQuestion mQuestion;

    private AnswersAdapter mAnswersAdapter;

    public TextGameFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param question The text question.
     * @return A new instance of fragment TextGameFragment.
     */
    public static TextGameFragment newInstance(TextQuestion question) {
        TextGameFragment fragment = new TextGameFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_TEXT_QUESTION, question);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mQuestion = getArguments().getParcelable(ARG_TEXT_QUESTION);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_text_game, container, false);
        ButterKnife.bind(this, rootView);

        initLayout();

        return rootView;
    }

    private void initLayout() {
        questionTitleTextView.setText(mQuestion.getQuestion());String[] answers = mQuestion.getAnswerList();

        mAnswersAdapter = new AnswersAdapter(getActivity(), answers);
        answersRecyclerView.setAdapter(mAnswersAdapter);
        answersRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mAnswersAdapter.setListener(new OnAnswerClickedListener() {
            @Override
            public void onAnswerChosen(int index) {
                if (mAnswerClickedListener != null) {
                    mAnswerClickedListener.onAnswer(index);
                }
            }
        });
    }
}
