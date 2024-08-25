package emazon.stock.domain.model.usecase;

import emazon.stock.configuration.exception.BrandAlreadyExistsException;
import emazon.stock.domain.model.Brand;
import emazon.stock.domain.ports.input.IBrandServicePort;
import emazon.stock.domain.ports.output.IBrandPersistencePort;

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
}
