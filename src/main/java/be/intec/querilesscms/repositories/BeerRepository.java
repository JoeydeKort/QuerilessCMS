package be.intec.querilesscms.repositories;

import be.intec.querilesscms.models.Beer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BeerRepository extends JpaRepository<Beer, Long> {

    Optional<Beer> findByName(String name);

    @Query(value = "select * from beer b where (b.name like %:keyword%) or (b.alcohol like %:keyword%) " +
            "or (b.stock like %:keyword%) or (b.price like %:keyword%) ", nativeQuery = true)
    List<Beer> searchBeerDB(@Param("keyword") String keyword);

}
