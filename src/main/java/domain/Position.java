package domain;

public enum Position {
    TEACHER("Викладач"),
    DEAN("Декан факультету"),
    HEAD("Завідувач кафедри");

    private final String displayName;

    Position(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

}
