package id.kelongmakassar.kelongmakassar.ui.games;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.kelongmakassar.kelongmakassar.R;

public class AnswersSongAdapter extends RecyclerView.Adapter<AnswersSongAdapter.AnswerSongHolder> {

    private Context mContext;
    private int[] mAnswers;
    private OnSongAnswerClickedListener mListener;

    AnswersSongAdapter(Context context, int[] answers) {
        mContext = context;
        mAnswers = answers;
    }

    public void setListener(OnSongAnswerClickedListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public AnswerSongHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AnswerSongHolder(LayoutInflater.from(mContext).inflate(R.layout.item_answer_song, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AnswerSongHolder holder, int position) {
        holder.bind(mAnswers[position], position, mListener, mContext);
    }

    @Override
    public int getItemCount() {
        return mAnswers.length;
    }

    static class AnswerSongHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_answer) TextView answerTextView;
        @BindView(R.id.image_play_media) ImageView playMediaImageView;

        AnswerSongHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(final int answerResId, final int answerIndex, final OnSongAnswerClickedListener listener, Context context) {
            char letter = (char) ((int) 'A' + answerIndex);
            answerTextView.setText(String.valueOf(letter));
            answerTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onAnswerChosen(answerIndex);
                }
            });

            playMediaImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onPlayClickListener(answerResId);
                }
            });

            int colorResId;
            switch (answerIndex) {
                case 0:
                    colorResId = R.color.colorAnswer1; break;
                case 1:
                    colorResId = R.color.colorAnswer2; break;
                case 2:
                    colorResId = R.color.colorAnswer3; break;
                case 3:
                    colorResId = R.color.colorAnswer4; break;
                default:
                    colorResId = R.color.colorAccent;
            }

            answerTextView.setBackgroundColor(ContextCompat.getColor(context, colorResId));
        }
    }
}
