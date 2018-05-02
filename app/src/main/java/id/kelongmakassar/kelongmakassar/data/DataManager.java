package id.kelongmakassar.kelongmakassar.data;

import java.util.ArrayList;
import java.util.List;

import id.kelongmakassar.kelongmakassar.R;
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
        tracks.add(new Track(R.raw.ammaq_ciang, "Ammaq Ciang", "lyrics/AmmaqCiang.txt", "meanings/AmmaqCiang.txt"));
        tracks.add(new Track(R.raw.ati_raja, "Ati Raja", "lyrics/AtiRaja.txt", "meanings/AtiRaja.txt"));
        tracks.add(new Track(R.raw.ilang_kebo, "Ilang Kebo", "lyrics/IlangKebo.txt", "meanings/IlangKebo.txt"));
        tracks.add(new Track(R.raw.mas_bangun, "Mas Bangun", "lyrics/MasBangun.txt", "meanings/MasBangun.txt"));

        return tracks;
    }
}
