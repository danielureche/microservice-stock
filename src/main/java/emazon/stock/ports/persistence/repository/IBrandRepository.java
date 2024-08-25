package emazon.stock.ports.persistence.repository;

import emazon.stock.ports.persistence.entity.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBrandRepository extends JpaRepository<BrandEntity, Long> {
    BrandEntity findByName(String name);
}
