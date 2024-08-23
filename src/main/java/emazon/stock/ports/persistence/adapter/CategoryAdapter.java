package emazon.stock.ports.persistence.adapter;

import emazon.stock.domain.model.Category;
import emazon.stock.domain.ports.output.ICategoryPersistencePort;
import emazon.stock.ports.persistence.entity.CategoryEntity;
import emazon.stock.ports.persistence.mapper.ICategoryEntityMapper;
import emazon.stock.ports.persistence.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
@RequiredArgsConstructor
public class CategoryAdapter implements ICategoryPersistencePort {
    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryEntityMapper;
    @Override
    public void createCategory(Category category) {
        categoryRepository.save(categoryEntityMapper.toEntity(category));
    }

    @Override
    public Optional<Category> findByName(String name) {
        CategoryEntity categoryEntity = categoryRepository.findByName(name);
        if (categoryEntity == null){
            return Optional.empty();
        }
        Category category = categoryEntityMapper.toModel(categoryEntity);
        return Optional.of(category);
    }
}
