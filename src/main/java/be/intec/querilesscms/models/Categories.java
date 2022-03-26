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
public class Categories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String title;

    String slug;

    @ManyToOne
    @JoinColumn(name = "beer_id")
    Beers beer;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "category")
    List<Brewers> brewers;

}
