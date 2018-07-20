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
        tutorials.add(new Tutorial("Besoq-Besoq Na", "Istilah dalam menarik atau menyeret nada", R.raw.tut_1, "Besoq-besoqna berasal dari kata besoq artinya tarik. Jika dikaitkan dengan musik/kelong maka bermakna bernyanyi dengan menyeret atau menarik nada secepat mungkin dari nada satu ke nada yang berikutnya."));
        tutorials.add(new Tutorial("Lekko-Lekko Na", "Istilah Cengkok daerah Makassar", R.raw.tut_2, "Lekko-lekkona berasal dari kata lekko artinya liuk. Jika dikaitkan dengan musik/kelong maka bermakna seperti meliuk-liukan suara bagaikan gelombang atau biasa disebut cengkok daerah Makassar."));
        tutorials.add(new Tutorial("Mebbereq Saqra", "Istilah vibra", R.raw.tut_3, "Mebbereq Saqra berasal dari kata mebbereq artinya getaran dan saqra artinya suara. Jika dikaitkan dengan musik/kelong maka bermakna getaran pada suara yang biasa disebut dengan vibra."));
        tutorials.add(new Tutorial("Belo-Belona", "Hiasan kata pada lagu", R.raw.tut_4, "Belo-belona berasal dari kata belo yang artinya hias. Jika dikaitkan dengan musik/kelong maka bermakna hiasan kata pada lirik yang bertujuan untuk memperindah arti lagu."));

        return tutorials;
    }
}
