package emazon.stock.ports.persistence.repository;

import emazon.stock.ports.persistence.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository extends JpaRepository<CategoryEntity, Long> {
}
