package emazon.stock.domain.model.usecase;

import emazon.stock.domain.model.Brand;
import emazon.stock.domain.ports.input.IBrandServicePort;
import emazon.stock.domain.ports.output.IBrandPersistencePort;

public class BrandUseCase implements IBrandServicePort {

    private final IBrandPersistencePort brandPersistencePort;

    public BrandUseCase(IBrandPersistencePort brandPersistencePort) {
        this.brandPersistencePort = brandPersistencePort;
    }

    @Override
    public void createBrand(Brand brand) {
        brandPersistencePort.createBrand(brand);
    }

}
