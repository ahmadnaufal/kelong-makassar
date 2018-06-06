package id.kelongmakassar.kelongmakassar.data;

import java.util.ArrayList;
import java.util.List;

import id.kelongmakassar.kelongmakassar.R;
import id.kelongmakassar.kelongmakassar.data.model.Track;
import id.kelongmakassar.kelongmakassar.data.model.Tutorial;

/**
 * Created by ahmadnaufal on 03/03/18.
 */

public class DataManager {

    public DataManager() {

    }

    public List<Track> getTracks() {
        ArrayList<Track> tracks = new ArrayList<>();
        tracks.add(new Track(R.raw.ammaq_ciang, "Ammaq Ciang", "lyrics/AmmaqCiang.txt", "meanings/AmmaqCiang.txt", R.drawable.ammaq_ciang));
        tracks.add(new Track(R.raw.ati_raja, "Ati Raja", "lyrics/AtiRaja.txt", "meanings/AtiRaja.txt", R.drawable.ati_raja));
        tracks.add(new Track(R.raw.ilang_kebo, "Ilang Kebo", "lyrics/IlangKebo.txt", "meanings/IlangKebo.txt", R.drawable.ilang_kebo));
        tracks.add(new Track(R.raw.mas_bangun, "Mas Bangun", "lyrics/MasBangun.txt", "meanings/MasBangun.txt", R.drawable.mas_bangun));

        // TODO soon to be added
        tracks.add(new Track(R.raw.dongang_dongang, "Dongang-Dongang", "lyrics/DongangDongang.txt", "meanings/DongangDongang.txt", R.drawable.dongang_dongang));
        tracks.add(new Track(R.raw.subang_kacayya, "Subang Kacayya", "lyrics/SubangKacayya.txt", "meanings/SubangKacayya.txt", R.drawable.subang_kacayya));
        tracks.add(new Track(R.raw.sailong, "Sailong", "lyrics/Sailong.txt", "meanings/Sailong.txt", R.drawable.sailong));
        tracks.add(new Track(R.raw.marencong_rencong, "Ma'rencong Rencong", "lyrics/MarencongRencong.txt", "meanings/MarencongRencong.txt", R.drawable.marencong_rencong));

        return tracks;
    }

    public List<Tutorial> getTutorials() {
        ArrayList<Tutorial> tutorials = new ArrayList<>();
        tutorials.add(new Tutorial("Besoq-Besoq Na", "Istilah dalam menyeret nada", R.raw.tut_1, "notations/tut_1.txt"));
        tutorials.add(new Tutorial("Lekko-Lekko Na", "Istilah Cengkok daerah Makassar", R.raw.tut_2, "notations/tut_2.txt"));
        tutorials.add(new Tutorial("Mebbereq Saqra", "Istilah vibra Kelong Makasssar", R.raw.tut_3, "notations/tut_3.txt"));
        tutorials.add(new Tutorial("Belo-Belona", "Penambahan kata dalam kalimat lagu", R.raw.tut_4, "notations/tut_4.txt"));

        return tutorials;
    }
}
