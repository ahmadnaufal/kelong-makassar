package id.kelongmakassar.kelongmakassar.ui.games;

import android.content.Context;
import android.support.v4.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnAnswerListener} interface
 * to handle interaction events.
 */
public class QuestionFragment extends Fragment {

    protected OnAnswerListener mAnswerClickedListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnAnswerListener) {
            mAnswerClickedListener = (OnAnswerListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnAnswerListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mAnswerClickedListener = null;
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
    public interface OnAnswerListener {
        void onAnswer(int index);
        boolean onPlayButtonPressed(int resId);
    }
}
