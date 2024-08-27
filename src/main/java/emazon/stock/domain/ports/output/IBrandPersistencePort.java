package emazon.stock.domain.ports.output;

import emazon.stock.domain.model.Brand;
import emazon.stock.domain.model.Pagination;
import emazon.stock.domain.util.PaginationUtil;

import java.util.Optional;

public interface IBrandPersistencePort {
    void createBrand(Brand brand);
    Optional<Brand> findByName(String name);
    Pagination<Brand> listBrands(PaginationUtil paginationUtil);
    boolean existsBrand(Long brandId);
}
