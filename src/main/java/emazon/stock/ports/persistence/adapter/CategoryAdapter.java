package emazon.stock.ports.persistence.adapter;

import emazon.stock.domain.model.Category;
import emazon.stock.domain.ports.output.ICategoryPersistencePort;
import emazon.stock.ports.application.mapper.ICategoryRequestMapper;
import emazon.stock.ports.persistence.mapper.ICategoryEntityMapper;
import emazon.stock.ports.persistence.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CategoryAdapter implements ICategoryPersistencePort {
    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryEntityMapper;
    @Override
    public void createCategory(Category category) {
        categoryRepository.save(categoryEntityMapper.toEntity(category));
    }
}
