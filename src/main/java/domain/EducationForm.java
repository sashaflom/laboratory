package domain;

import java.io.Serializable;

public enum EducationForm implements Serializable {
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
