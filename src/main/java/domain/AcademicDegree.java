package domain;

public enum AcademicDegree {
    NONE("Без ступеня"),
    CANDIDATE("Кандидат наук"),
    DOCTOR("Доктор наук");

    private final String displayName;

    AcademicDegree(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}