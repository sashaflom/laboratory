package domain;

import java.io.Serializable;

public class Access implements Serializable {

    public static final int BLOCKED = 1; // 0001
    public static final int READ = 1 << 1; // 0010
    public static final int WRITE_DOMAIN = 1 << 2; // 0100
    public static final int WRITE_ALL = 1 << 3; // 1000

    public static final int ADMIN_MASK = Access.READ | Access.WRITE_DOMAIN | Access.WRITE_ALL;
    public static final int MANAGER_MASK = Access.READ | Access.WRITE_DOMAIN;
    public static final int USER_MASK = Access.READ;

    public static boolean has (int mask, int flag){
        return (mask & flag) == flag;
    }

    public static String roleString (int role){
        if ((role & ADMIN_MASK) == ADMIN_MASK) return "Адміністратор";
        if ((role & MANAGER_MASK) == MANAGER_MASK) return "Менеджер";
        return "Користувач";
    }

}
