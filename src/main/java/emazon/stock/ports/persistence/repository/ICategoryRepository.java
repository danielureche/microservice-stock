package emazon.stock.ports.persistence.repository;

import emazon.stock.ports.persistence.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryRepository extends JpaRepository<CategoryEntity, Long> {
    @Query("SELECT c FROM category c WHERE c.name = :name")
    CategoryEntity findByName(String name);

}
