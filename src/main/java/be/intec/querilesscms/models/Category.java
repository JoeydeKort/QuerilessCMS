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
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false, unique = true)
    String title;

    String slug;

    @ManyToOne
    @JoinColumn(name = "beer_id")
    Beer beer;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "category")
    List<Brewer> brewer;

}
