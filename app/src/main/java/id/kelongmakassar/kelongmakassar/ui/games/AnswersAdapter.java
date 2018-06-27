package id.kelongmakassar.kelongmakassar.ui.games;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.kelongmakassar.kelongmakassar.R;

public class AnswersAdapter extends RecyclerView.Adapter<AnswersAdapter.AnswerHolder> {

    private Context mContext;
    private String[] mAnswers;
    private OnAnswerClickedListener mListener;

    public AnswersAdapter(Context context, String[] answers) {
        mContext = context;
        mAnswers = answers;
    }

    public void setListener(OnAnswerClickedListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public AnswerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AnswerHolder(LayoutInflater.from(mContext).inflate(R.layout.item_answer, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AnswerHolder holder, int position) {
        holder.bind(mAnswers[position], position, mListener);
    }

    @Override
    public int getItemCount() {
        return mAnswers.length;
    }

    static class AnswerHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_answer) TextView answerTextView;

        AnswerHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(String answerText, final int answerIndex, final OnAnswerClickedListener listener) {
            answerTextView.setText(answerText);
            answerTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onAnswerChosen(answerIndex);
                }
            });
        }
    }
}
