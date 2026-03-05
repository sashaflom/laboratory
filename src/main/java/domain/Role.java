package domain;

public enum Role {
    USER("Користувач"),
    MANAGER("Менеджер"),
    ADMINISTRATOR("Адміністратор");

    private final String displayName;

    Role(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

}
