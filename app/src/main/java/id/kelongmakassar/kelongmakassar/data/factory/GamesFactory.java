package id.kelongmakassar.kelongmakassar.data.factory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import id.kelongmakassar.kelongmakassar.R;
import id.kelongmakassar.kelongmakassar.data.model.KaraokeQuestion;
import id.kelongmakassar.kelongmakassar.data.model.Question;
import id.kelongmakassar.kelongmakassar.data.model.TextQuestion;
import id.kelongmakassar.kelongmakassar.data.model.WordMeaningQuestion;
import id.kelongmakassar.kelongmakassar.util.Utility;

public class GamesFactory {

    private TextQuestion[] textQuestions = {

            new TextQuestion("Yang bukan makna lagu \"Mas Bangun\" adalah...", new String[]{
                    "Menciptakan perdamaian", "Jangan mengadu domba", "Meyakini Tuhan hanya satu", "Jangan berbuat zina"
            }, 1),

            new TextQuestion("Yang bukan fungsi Kelong Makassar adalah...", new String[]{
                    "Sebagai media komunikasi", "Sebagai media hiburan", "Sebagai panduan untuk bercocok tanam",
                    "Berisi doa dan harapan"
            }, 3),

            new TextQuestion("Yang bukan nilai-nilai Kelong Makassar adalah...", new String[]{
                    "Nilai sosial", "Nilai moral", "Nilai religius", "Nasihat-nasihat tentang kehidupan"
            }, 3),

            new TextQuestion("\"Mengakui keberadaan Allah SWT, Nabi Muhammad SAW, dan Nabi Adam\"\nMakna lagu di atas terdapat pada kelong?", new String[]{
                    "Ati Raja", "Maqrencong-rencong", "Ammaq Ciang", "Ilang Kebo"
            }, 3),

            new TextQuestion("\"Takkan kembali sebelum cita-cita tercapai\"\nArti lagu di atas terdapat pada kelong?", new String[]{
                    "Ammaq ciang", "Ilang Kebo", "Dongang-dongang", "Sailong"
            }, 3),

    };

    private WordMeaningQuestion[] wordMeaningQuestions = {

            new WordMeaningQuestion("Kata dari \"Takkunjunga bangunturu\" artinya...", new String[]{
                    "Hati-hatilah berlayar", "Sekali layar berkembang", "Mendayung dari atas bukit", "Perahu layar"
            }, 1),

            new WordMeaningQuestion("\"Tuhan\" dalam lagu Ati Raja bahasa Makassarnya adalah...", new String[]{
                    "Batara", "Baule", "Lino", "Tangkellai"
            }, 1),

            new WordMeaningQuestion("Cara penulisan kata yang benar adalah...", new String[]{
                    "Seqre-seqre-ji", "Ati-Raja", "Bunganna Ilangkebo", "Yamas Bangun"
            }, 3),

            new WordMeaningQuestion("Ilang Kebo artinya...", new String[]{
                    "Wanita", "Bunga Melati", "Perhiasan", "Sanggul"
            }, 2),

            new WordMeaningQuestion("Judul kelong di atas adalah...", new String[]{
                    "Maqseqre-seqreki", "Maq-sombalak-i", "Teakiq", "Si-sa-lasi"
            }, 3),

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
            }, 2),

            new KaraokeQuestion(R.raw.dongang_dongang_karaoke, new String[]{
                    "Ammaq Ciang", "Dongang-dongang", "Ilang Kebo", "Sailong"
            }, 1),

            new KaraokeQuestion(R.raw.ati_raja_karaoke, new String[]{
                    "Ati Raja", "Subang Kacayya", "Maqrencong-rencong", "Mas Bangun"
            }, 0),

    };

    public List<Question> generateRandomFourQuestions() {
        List<Question> questions = new ArrayList<>();

        questions.add(getQuestion(1));
        questions.add(getQuestion(2));
        questions.add(getQuestion(3));
        Collections.shuffle(questions);

        return questions;
    }

    private Question getQuestion(int type) {
        switch (type) {
            case 1:
                return getRandomTextQuestion();
            case 2:
                return getRandomWordMeaningQuestion();
            case 3:
                return getRandomKaraokeQuestion();
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
}
