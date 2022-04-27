package be.intec.querilesscms.repositories;

import be.intec.querilesscms.models.Brewer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BrewerRepository extends JpaRepository<Brewer, Long> {

    Optional<Brewer> findByName(String name);

    @Query(value = "select * from brewer br where (br.name like %:keyword%) or (br.turnover like %:keyword%) " +
            "or (br.city like %:keyword%) or (br.address like %:keyword%) or (br.zip like %:keyword%)", nativeQuery = true)
    List<Brewer> searchBrewerDB(@Param("keyword") String keyword);
}
