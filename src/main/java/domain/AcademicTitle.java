package domain;

public enum AcademicTitle {
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