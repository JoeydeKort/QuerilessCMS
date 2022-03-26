package be.intec.querilesscms.models;


import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Collection;


@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Users {

    /*
one user can have many roles
one role can have many user
many to many -> done
---------------------------------------------------
one user can not have many brewers
one brewer can have many users
one to many -> done
---------------------------------------------------
one beer cannot have many categories
one category can have many beers
one to many -> done
---------------------------------------------------
one beer cannot have many brewers
one brewer can have many beers
one to many -> done
---------------------------------------------------
one category can have many brewers
one brewer can have many categories
many to many -> done
*/


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String firstName;

    String lastName;

    String username;

    String email;

    String passCode;

    String city;

    String address;

    String zip;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    Collection<Roles> roles;


}
