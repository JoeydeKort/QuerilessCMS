package be.intec.querilesscms.models;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Role {

    /*
    USER
    EDITOR
    ADMIN
    GUEST
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String title;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    Role parent;

    public Role(String title) {
        this.title = title;
    }

    public static Role user(){

        return new Role("ROLE_USER");
    }

    public static Role editor(){

        return new Role("ROLE_EDITOR");
    }

    public static Role admin(){

        return new Role("ROLE_ADMIN");
    }

    public static Role guest(){

        return new Role("ROLE_GUEST");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role)) return false;

        Role role = (Role) o;

        return getTitle().equals(role.getTitle());
    }

    @Override
    public int hashCode() {
        return getTitle().hashCode();
    }

    @Override
    public String toString() {
        return this.getTitle();
    }
}
