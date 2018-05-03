package id.kelongmakassar.kelongmakassar.ui.tutorial;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.kelongmakassar.kelongmakassar.R;
import id.kelongmakassar.kelongmakassar.data.model.Tutorial;

public class TutorialAdapter extends RecyclerView.Adapter<TutorialAdapter.Holder> {
    private Context mContext;
    private List<Tutorial> mTutorialList;
    private OnTutorialInteractionListener mListener;

    public TutorialAdapter(Context mContext) {
        this.mContext = mContext;
        this.mTutorialList = new ArrayList<>();
    }

    public void setTutorialList(List<Tutorial> tutorialList) {
        mTutorialList = tutorialList;
    }

    public void setListener(OnTutorialInteractionListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(mContext).inflate(R.layout.item_tutorial, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Tutorial tutorial = mTutorialList.get(position);
        holder.bind(position + 1, tutorial, mContext, mListener);
    }

    @Override
    public int getItemCount() {
        return mTutorialList.size();
    }

    static class Holder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_number) TextView numberTextView;
        @BindView(R.id.text_tutorial_name) TextView tutorialNameTextView;
        @BindView(R.id.button_play) ImageView playImageView;

        Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(int position, final Tutorial tutorial, Context context, final OnTutorialInteractionListener listener) {
            numberTextView.setText(context.getString(R.string.text_numbering, position));
            tutorialNameTextView.setText(tutorial.getName());
            playImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onPlayButtonClicked(tutorial);
                }
            });
        }
    }
}
