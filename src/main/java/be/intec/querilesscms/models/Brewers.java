package be.intec.querilesscms.models;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Collection;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Brewers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    String city;

    String address;

    String zip;

    Long turnover;

    @ManyToOne
    @JoinColumn(name = "user_id")
    Users user;

    @ManyToOne
    @JoinColumn(name = "beer_id")
    Beers beer;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Brewer_category", joinColumns = @JoinColumn(name = "brewer_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    Collection<Categories> category;

}
