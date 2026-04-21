package domain;

import java.io.Serializable;

public enum AcademicTitle implements Serializable {
    NONE("Без звання"),
    DOCENT("Доцент"),
    PROFESSOR("Професор");

    private final String displayName;

    AcademicTitle(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}