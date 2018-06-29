package id.kelongmakassar.kelongmakassar.data.factory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import id.kelongmakassar.kelongmakassar.R;
import id.kelongmakassar.kelongmakassar.data.model.KaraokeQuestion;
import id.kelongmakassar.kelongmakassar.data.model.Question;
import id.kelongmakassar.kelongmakassar.data.model.SongContinueQuestion;
import id.kelongmakassar.kelongmakassar.data.model.SongPartQuestion;
import id.kelongmakassar.kelongmakassar.data.model.TextQuestion;
import id.kelongmakassar.kelongmakassar.data.model.WordMeaningQuestion;
import id.kelongmakassar.kelongmakassar.util.Utility;

public class GamesFactory {

    private TextQuestion[] textQuestions = {

            new TextQuestion("Yang bukan makna lagu \"Mas Bangun\" adalah...", new String[]{
                    "Menciptakan perdamaian", "Jangan mengadu domba", "Meyakini Tuhan hanya satu", "Jangan berbuat zina"
            }, 2),

            new TextQuestion("Yang bukan fungsi Kelong Makassar adalah...", new String[]{
                    "Sebagai media komunikasi", "Sebagai media hiburan", "Sebagai panduan untuk bercocok tanam",
                    "Berisi doa dan harapan"
            }, 2),

            new TextQuestion("Yang bukan nilai-nilai Kelong Makassar adalah...", new String[]{
                    "Nilai benda", "Nilai moral", "Nilai religius", "Nasihat-nasihat tentang kehidupan"
            }, 0),

            new TextQuestion("\"Mengakui keberadaan Allah SWT, Nabi Muhammad SAW, dan Nabi Adam\"\nMakna lagu di atas terdapat pada kelong?", new String[]{
                    "Ati Raja", "Maqrencong-rencong", "Ammaq Ciang", "Ilang Kebo"
            }, 2),

            new TextQuestion("\"Takkan kembali sebelum cita-cita tercapai\"\nArti lagu di atas terdapat pada kelong?", new String[]{
                    "Ammaq ciang", "Ilang Kebo", "Dongang-dongang", "Sailong"
            }, 2),

    };

    private WordMeaningQuestion[] wordMeaningQuestions = {

            new WordMeaningQuestion("Kata dari \"Takkunjunga bangunturu\" artinya...", new String[]{
                    "Hati-hatilah berlayar", "Sekali layar berkembang", "Mendayung dari atas bukit", "Perahu layar"
            }, 1),

            new WordMeaningQuestion("\"Tuhan\" dalam lagu Ati Raja bahasa Makassarnya adalah...", new String[]{
                    "Batara", "Baule", "Lino", "Tangkellai"
            }, 0),

            new WordMeaningQuestion("Cara penulisan kata yang benar adalah...", new String[]{
                    "Seqre-seqre-ji", "Ati-Raja", "Bunganna Ilangkebo", "Yamas Bangun"
            }, 0),

            new WordMeaningQuestion("Ilang Kebo artinya...", new String[]{
                    "Wanita", "Bunga Melati", "Perhiasan", "Sanggul"
            }, 1),

            new WordMeaningQuestion("Cara penulisan yang benar adalah...", new String[]{
                    "Maqseqre-seqreki", "Maq-sombalak-i", "Teakiq", "Si-sa-lasi"
            }, 1),

    };

    private KaraokeQuestion[] karaokeQuestions = {

            new KaraokeQuestion(R.raw.ammaq_ciang_karaoke, new String[]{
                    "Dongang-dongang", "Ammaq Ciang", "Ilang Kebo", "Sailong"
            }, 1),

            new KaraokeQuestion(R.raw.marencong_rencong_karaoke, new String[]{
                    "Ati Raja", "Subang Kacayya", "Maqrencong-rencong", "Mas Bangun"
            }, 2),

            new KaraokeQuestion(R.raw.sailong_karaoke, new String[]{
                    "Ati Raja", "Maqrencong-rencong", "Sailong", "Ilang Kebo"
            }, 3),

            new KaraokeQuestion(R.raw.dongang_dongang_karaoke, new String[]{
                    "Ammaq Ciang", "Dongang-dongang", "Ilang Kebo", "Sailong"
            }, 1),

            new KaraokeQuestion(R.raw.ati_raja_karaoke, new String[]{
                    "Ati Raja", "Subang Kacayya", "Maqrencong-rencong", "Mas Bangun"
            }, 0),

    };

    /**
     * as a replacement for the text questions.
     */
    private SongPartQuestion[] newSongPartQuestions = {

            new SongPartQuestion(R.raw.tut_1, "Ciri khas dalam bernyanyi Kelong Makassar pada potongan lagu di atas menggunakan istilah...", new String[]{
                    "Besoq-besoqna", "Mebbereq Saqra", "Lekko-lekkona", "Belo-belona"
            }, 0),

            new SongPartQuestion(R.raw.tut_4, "Ciri khas dalam bernyanyi Kelong Makassar pada potongan lagu di atas menggunakan istilah...", new String[]{
                    "Besoq-besoqna", "Mebbereq Saqra", "Lekko-lekkona", "Belo-belona"
            }, 3),

    };

    private SongPartQuestion[] songPartQuestions = {

            new SongPartQuestion(R.raw.mas_bangun_section, "Judul potongan lagu di atas adalah...", new String[]{
                    "Ati Raja", "Mas Bangun", "Sailong", "Subang Kacayya"
            }, 1),

            new SongPartQuestion(R.raw.subang_kacayya_section, "Judul potongan lagu di atas adalah...", new String[]{
                    "Ati Raja", "Mas Bangun", "Sailong", "Subang Kacayya"
            }, 3),

            new SongPartQuestion(R.raw.ati_raja_section, "Istilah Belo-Belo na pada potongan kelong di atas terdapat pada kata...", new String[]{
                    "Seqre-seqre", "Baule", "Batara", "Ati Raja"
            }, 1),

    };

    private SongContinueQuestion[] songContinueQuestions = {

            new SongContinueQuestion(R.raw.dongang_question, "Lanjutan potongan lagu di atas adalah...", new int[]{
                    R.raw.the_a_answer, R.raw.ammaq_ciang_short, R.raw.dongang_short, R.raw.ilang_kebo_short
            }, 2),

            new SongContinueQuestion(R.raw.ilang_kebo_question, "Lanjutan potongan lagu di atas adalah...", new int[]{
                    R.raw.the_a_answer, R.raw.ammaq_ciang_short, R.raw.dongang_short, R.raw.ilang_kebo_short
            }, 3),

    };

    public List<Question> generateRandomFourQuestions() {
        List<Question> questions = new ArrayList<>();

        questions.add(getQuestion(1));
        questions.add(getQuestion(2));
        questions.add(getQuestion(3));
        questions.add(getQuestion(4));
        Collections.shuffle(questions);

        return questions;
    }

    private Question getQuestion(int type) {
        switch (type) {
            case 1:
                return getRandomNewSongPartQuestion();
            case 2:
                return getRandomWordMeaningQuestion();
            case 3:
                return getRandomKaraokeQuestion();
            case 4:
                return (Utility.generateRandomInteger(0, 4) > 2) ? getRandomSongPartQuestion() : getRandomSongContinueQuestion();
            default:
                return null;
        }
    }

    private TextQuestion getRandomTextQuestion() {
        return textQuestions[Utility.generateRandomInteger(0, textQuestions.length - 1)];
    }

    private WordMeaningQuestion getRandomWordMeaningQuestion() {
        return wordMeaningQuestions[Utility.generateRandomInteger(0, wordMeaningQuestions.length - 1)];
    }

    private KaraokeQuestion getRandomKaraokeQuestion() {
        return karaokeQuestions[Utility.generateRandomInteger(0, karaokeQuestions.length - 1)];
    }

    private SongPartQuestion getRandomSongPartQuestion() {
        return songPartQuestions[Utility.generateRandomInteger(0, songPartQuestions.length - 1)];
    }

    private SongPartQuestion getRandomNewSongPartQuestion() {
        return newSongPartQuestions[Utility.generateRandomInteger(0, newSongPartQuestions.length - 1)];
    }

    private SongContinueQuestion getRandomSongContinueQuestion() {
        return songContinueQuestions[Utility.generateRandomInteger(0, songContinueQuestions.length - 1)];
    }
}
