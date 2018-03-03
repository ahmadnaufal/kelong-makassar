package id.kelongmakassar.kelongmakassar.ui.track.list;

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
import id.kelongmakassar.kelongmakassar.data.model.Track;

/**
 * Created by ahmadnaufal on 03/03/18.
 */

public class TrackAdapter extends RecyclerView.Adapter<TrackAdapter.Holder> {
    private Context mContext;
    private List<Track> mTrackList;
    private OnTrackSelectedListener mListener;

    TrackAdapter(Context context) {
        mContext = context;
        mTrackList = new ArrayList<>();
    }

    public void setTrackList(List<Track> trackList) {
        mTrackList = trackList;
    }

    public void setListener(OnTrackSelectedListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TrackAdapter.Holder(LayoutInflater.from(mContext).inflate(R.layout.item_track, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.bind(mTrackList.get(position), mListener);
    }

    @Override
    public int getItemCount() {
        return mTrackList.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_track_title) TextView mTrackTitleText;
        @BindView(R.id.image_next) ImageView mNextImageView;

        private Track mTrack;

        Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(Track track, final OnTrackSelectedListener listener) {
            mTrack = track;

            mTrackTitleText.setText(mTrack.getTitle());
            mNextImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onTrackSelected(mTrack);
                }
            });
        }
    }
}
