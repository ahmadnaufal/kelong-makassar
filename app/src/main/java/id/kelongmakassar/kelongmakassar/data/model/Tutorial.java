package id.kelongmakassar.kelongmakassar.data.model;

public class Tutorial {

    private String name;
    private int resId;
    private String notationPath;

    public Tutorial(String name, int resId, String notationPath) {
        this.name = name;
        this.resId = resId;
        this.notationPath = notationPath;
    }

    public String getName() {
        return name;
    }

    public int getResId() {
        return resId;
    }

    public String getNotationPath() {
        return notationPath;
    }
}
