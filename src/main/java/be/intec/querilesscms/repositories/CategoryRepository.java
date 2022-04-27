package be.intec.querilesscms.repositories;

import be.intec.querilesscms.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByTitle(String title);

    @Query(value = "select * from category c where (c.title like %:keyword%) or (c.slug like %:keyword%)", nativeQuery = true)
    List<Category> searchCategoryDB(@Param("keyword") String keyword);

}
