package domain;

public enum UserStatus {
    ACTIVE("Активний"),
    PERMITTED("Дозволений"),
    BLOCKED("Заблокований");

    private final String displayName;

    UserStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
