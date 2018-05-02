package id.kelongmakassar.kelongmakassar.ui.track.list;

import java.util.List;

import id.kelongmakassar.kelongmakassar.data.DataManager;
import id.kelongmakassar.kelongmakassar.data.model.Track;
import id.kelongmakassar.kelongmakassar.ui.base.Presenter;

/**
 * Created by ahmadnaufal on 03/03/18.
 */

public class TrackListPresenter extends Presenter<TrackListMvpView> {

    private final DataManager mDataManager;

    TrackListPresenter(TrackListMvpView view) {
        super(view);
        mDataManager = new DataManager();
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    void loadTracks() {
        List<Track> trackList = mDataManager.getTracks();
        getView().onTrackListLoaded(trackList);
    }
}
