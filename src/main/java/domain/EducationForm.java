package domain;

public enum EducationForm {
    BUDGET("Бюджет"),
    CONTRACT("Контракт");

    private final String displayName;

    EducationForm(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
