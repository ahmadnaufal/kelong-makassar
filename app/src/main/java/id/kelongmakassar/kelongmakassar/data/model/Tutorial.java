package id.kelongmakassar.kelongmakassar.data.model;

public class Tutorial {

    private String name;
    private String description;
    private int resId;
    private String explanation;

    public Tutorial(String name, String description, int resId, String explanation) {
        this.name = name;
        this.resId = resId;
        this.description = description;
        this.explanation = explanation;
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

    public String getExplanation() {
        return explanation;
    }
}
