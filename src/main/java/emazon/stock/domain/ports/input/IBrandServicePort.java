package emazon.stock.domain.ports.input;

import emazon.stock.domain.model.Brand;
import emazon.stock.domain.model.Pagination;
import emazon.stock.domain.util.PaginationUtil;

public interface IBrandServicePort {
    void createBrand(Brand brand);
    Pagination<Brand> listBrands(PaginationUtil paginationUtil);
}
