package id.kelongmakassar.kelongmakassar.ui.games;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.kelongmakassar.kelongmakassar.R;
import id.kelongmakassar.kelongmakassar.data.model.WordMeaningQuestion;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link WordMeaningGameFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link WordMeaningGameFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WordMeaningGameFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_WORD_MEANING_QUESTION = "WORD_MEANING_QUESTION";

    @BindView(R.id.text_question_title) TextView questionTitleTextView;

    private WordMeaningQuestion mQuestion;

    private OnFragmentInteractionListener mListener;

    public WordMeaningGameFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param question Word meaning question.
     * @return A new instance of fragment WordMeaningGameFragment.
     */
    public static WordMeaningGameFragment newInstance(WordMeaningQuestion question) {
        WordMeaningGameFragment fragment = new WordMeaningGameFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_WORD_MEANING_QUESTION, question);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mQuestion = getArguments().getParcelable(ARG_WORD_MEANING_QUESTION);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_word_meaning_game, container, false);
        ButterKnife.bind(this, rootView);

        initLayout();

        return rootView;
    }

    private void initLayout() {
        questionTitleTextView.setText(mQuestion.getQuestion());
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
