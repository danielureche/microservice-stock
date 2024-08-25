package emazon.stock.domain.ports.input;

import emazon.stock.domain.model.Brand;

public interface IBrandServicePort {
    void createBrand(Brand brand);
}
