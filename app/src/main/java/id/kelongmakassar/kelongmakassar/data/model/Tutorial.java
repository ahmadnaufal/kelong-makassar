package id.kelongmakassar.kelongmakassar.data.model;

public class Tutorial {

    private String name;
    private String description;
    private int resId;
    private String notationPath;

    public Tutorial(String name, String description, int resId, String notationPath) {
        this.name = name;
        this.resId = resId;
        this.description = description;
        this.notationPath = notationPath;
    }

    public String getDescription() {
        return description;
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
