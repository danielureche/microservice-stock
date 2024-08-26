package emazon.stock.ports.persistence.adapter;

import emazon.stock.domain.model.Brand;
import emazon.stock.domain.model.Category;
import emazon.stock.domain.model.Pagination;
import emazon.stock.domain.ports.output.IBrandPersistencePort;
import emazon.stock.domain.util.PaginationUtil;
import emazon.stock.ports.persistence.entity.BrandEntity;
import emazon.stock.ports.persistence.entity.CategoryEntity;
import emazon.stock.ports.persistence.mapper.IBrandEntityMapper;
import emazon.stock.ports.persistence.repository.IBrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
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

    @Override
    public Pagination<Brand> listBrands(PaginationUtil paginationUtil) {
        Sort.Direction sortDirection = paginationUtil.isAscending() ? Sort.Direction.ASC : Sort.Direction.DESC;
        PageRequest pageRequest = PageRequest.of( paginationUtil.getPageNumber(), paginationUtil.getPageSize(), Sort.by(sortDirection, paginationUtil.getNameFilter()));
        Page<BrandEntity> brandPage = brandRepository.findAll(pageRequest);
        List<Brand> brands = brandEntityMapper.toBrandList(brandPage.getContent());

        return new Pagination<>(
                paginationUtil.isAscending(),
                paginationUtil.getPageNumber(),
                brandPage.getTotalPages(),
                brandPage.getTotalElements(),
                brands
        );
    }
}
