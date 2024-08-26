package emazon.stock.domain.model.usecase;

import emazon.stock.configuration.exception.BrandAlreadyExistsException;
import emazon.stock.configuration.exception.InvalidPageIndexException;
import emazon.stock.domain.model.Brand;
import emazon.stock.domain.model.Pagination;
import emazon.stock.domain.ports.input.IBrandServicePort;
import emazon.stock.domain.ports.output.IBrandPersistencePort;
import emazon.stock.domain.util.PaginationUtil;

import java.util.Optional;

public class BrandUseCase implements IBrandServicePort {

    private final IBrandPersistencePort brandPersistencePort;

    public BrandUseCase(IBrandPersistencePort brandPersistencePort) {
        this.brandPersistencePort = brandPersistencePort;
    }

    @Override
    public void createBrand(Brand brand) {
        Optional<Brand> brandByName = brandPersistencePort.findByName(brand.getName());
        if (brandByName.isPresent()){
            throw new BrandAlreadyExistsException();
        }
        brandPersistencePort.createBrand(brand);
    }

    @Override
    public Pagination<Brand> listBrands(PaginationUtil paginationUtil) {
        if (paginationUtil.getPageNumber() < 0 || paginationUtil.getPageSize() < 0) {
            throw new InvalidPageIndexException("Page index is out of range ");
        }
        return brandPersistencePort.listBrands(paginationUtil);
    }
}
