package id.kelongmakassar.kelongmakassar.ui.games;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;
import id.kelongmakassar.kelongmakassar.R;
import id.kelongmakassar.kelongmakassar.data.model.AudioGuessQuestion;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AudioGuessGameFragment.OnAudioGuessListener} interface
 * to handle interaction events.
 * Use the {@link AudioGuessGameFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AudioGuessGameFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_AUDIO_GUESS_QUESTION = "AUDIO_GUESS_QUESTION";

    private AudioGuessQuestion mQuestion;

    private OnAudioGuessListener mListener;

    public AudioGuessGameFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param question The question.
     * @return A new instance of fragment AudioGuessGameFragment.
     */
    public static AudioGuessGameFragment newInstance(AudioGuessQuestion question) {
        AudioGuessGameFragment fragment = new AudioGuessGameFragment();
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
    }

    @OnClick(R.id.image_play_media)
    void onButtonPressed() {
        if (mListener != null) {
            mListener.onPlayButtonPressed(mQuestion.getResId());
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnAudioGuessListener) {
            mListener = (OnAudioGuessListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnAudioGuessListener");
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
    public interface OnAudioGuessListener {
        void onPlayButtonPressed(int resId);
    }
}