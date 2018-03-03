package id.kelongmakassar.kelongmakassar.ui.track.list;

import java.util.List;

import id.kelongmakassar.kelongmakassar.data.model.Track;
import id.kelongmakassar.kelongmakassar.ui.base.MvpView;

/**
 * Created by ahmadnaufal on 03/03/18.
 */

public interface TrackListMvpView extends MvpView {
    void onTrackListLoaded(List<Track> trackList);
}
