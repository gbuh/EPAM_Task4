package domain;

//import java.util.ArrayList;
//import java.util.List;

public enum Role {
    ADMIN      (0, "Администратор"),
    DISPATCHER (1, "Диспетчер"),
    DRIVER     (2, "Водитель");

    private int index;
    private String name;

    Role(int index, String name) {
        this.index = index;
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return Long.valueOf(ordinal());
    }
/*
    public static List<Role> employees() {
        List<Role> roles = new ArrayList<>();
        for(Role role : values()) {
            if(role == ADMIN) {
                roles.add(role);
            }
        }
        return roles;
    }
*/
}