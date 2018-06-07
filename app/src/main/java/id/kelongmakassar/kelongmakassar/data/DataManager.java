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
        tracks.add(new Track(R.raw.dongang_dongang, "Dongang-Dongang", "lyrics/DongangDongang.txt", "meanings/DongangDongang.txt", R.drawable.dongang_dongang));
        tracks.add(new Track(R.raw.subang_kacayya, "Subang Kacayya", "lyrics/SubangKacayya.txt", "meanings/SubangKacayya.txt", R.drawable.subang_kacayya));
        tracks.add(new Track(R.raw.sailong, "Sailong", "lyrics/Sailong.txt", "meanings/Sailong.txt", R.drawable.sailong));
        tracks.add(new Track(R.raw.marencong_rencong, "Ma'rencong Rencong", "lyrics/MarencongRencong.txt", "meanings/MarencongRencong.txt", R.drawable.marencong_rencong));

        return tracks;
    }

    public List<Tutorial> getTutorials() {
        ArrayList<Tutorial> tutorials = new ArrayList<>();
        tutorials.add(new Tutorial("Besoq-Besoq Na", "Istilah dalam menyeret nada", R.raw.tut_1, "Jika diartikan kata perkata dalam bahasa sehari-hari kata besoq adalah gait, tarik. Ketika dikaitkan dengan kelong atau lagu maka bermakna menyanyikan nada secepat mungkin dari satu nada ke nada berikutnya."));
        tutorials.add(new Tutorial("Lekko-Lekko Na", "Istilah Cengkok daerah Makassar", R.raw.tut_2, "Jika diartikan kata perkata dalam bahasa sehari-hari kata lekko adalah belok, liukan. Ketika dikaitkan dengan kelong atau lagu maka bermakna suara yang berliku-liuk bagaikan gelombang."));
        tutorials.add(new Tutorial("Mebbereq Saqra", "Istilah vibra Kelong Makasssar", R.raw.tut_3, "Jika diartikan kata perkata dalam bahasa sehari-hari mebbereq saqra adalah getaran suara. Ketika dikaitkan dengan kelong atau lagu maka bermakna getaran suara."));
        tutorials.add(new Tutorial("Belo-Belona", "Penambahan kata dalam kalimat lagu", R.raw.tut_4, "Jika diartikan kata perkata dalam bahasa sehari-hari kata belo adalah hias. Ketika dikaitkan dengan kelong atau lagu maka bermakna hiasan kata pada lagu."));

        return tutorials;
    }
}
