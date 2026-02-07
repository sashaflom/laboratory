package domain;

public enum StudentStatus {
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