package emazon.stock.domain.ports.output;

import emazon.stock.domain.model.Brand;

import java.util.Optional;

public interface IBrandPersistencePort {
    void createBrand(Brand brand);
    Optional<Brand> findByName(String name);
}
