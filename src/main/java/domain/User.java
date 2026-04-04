package domain;

public record User (String login, String password, Role role, UserStatus status){}
