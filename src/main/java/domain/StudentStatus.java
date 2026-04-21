package domain;

import java.io.Serializable;

public enum StudentStatus implements Serializable {
    STUDYING("Навчається"),
    ACADEMIC_LEAVE("Академічна відпустка"),
    EXPELLED("Відрахований");
    private final String displayName;

    StudentStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}