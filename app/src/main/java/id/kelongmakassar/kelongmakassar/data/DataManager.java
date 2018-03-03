package id.kelongmakassar.kelongmakassar.data;

import java.util.ArrayList;
import java.util.List;

import id.kelongmakassar.kelongmakassar.data.model.Track;

/**
 * Created by ahmadnaufal on 03/03/18.
 */

public class DataManager {

    public DataManager() {

    }

    public List<Track> getTracks() {
        // TODO add dummy tracks only for testing
        ArrayList<Track> tracks = new ArrayList<>();
        tracks.add(new Track("Track 1"));
        tracks.add(new Track("Track 2"));

        return tracks;
    }
}
