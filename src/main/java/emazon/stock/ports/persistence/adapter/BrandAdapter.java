package emazon.stock.ports.persistence.adapter;

import emazon.stock.domain.model.Brand;
import emazon.stock.domain.model.Category;
import emazon.stock.domain.ports.output.IBrandPersistencePort;
import emazon.stock.ports.persistence.entity.BrandEntity;
import emazon.stock.ports.persistence.entity.CategoryEntity;
import emazon.stock.ports.persistence.mapper.IBrandEntityMapper;
import emazon.stock.ports.persistence.repository.IBrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BrandAdapter implements IBrandPersistencePort {

    private final IBrandRepository brandRepository;
    private final IBrandEntityMapper brandEntityMapper;

    @Override
    public void createBrand(Brand brand) {
        brandRepository.save(brandEntityMapper.toEntity(brand));
    }

    @Override
    public Optional<Brand> findByName(String name) {
        BrandEntity brandEntity = brandRepository.findByName(name);
        if (brandEntity == null){
            return Optional.empty();
        }
        Brand brand = brandEntityMapper.toModel(brandEntity);
        return Optional.of(brand);
    }
}
