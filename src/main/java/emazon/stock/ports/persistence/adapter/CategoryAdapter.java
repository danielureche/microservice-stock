package emazon.stock.ports.persistence.adapter;

import emazon.stock.configuration.exception.InvalidPageIndexException;
import emazon.stock.domain.model.Category;
import emazon.stock.domain.model.Pagination;
import emazon.stock.domain.ports.output.ICategoryPersistencePort;
import emazon.stock.domain.util.PaginationUtil;
import emazon.stock.ports.persistence.entity.CategoryEntity;
import emazon.stock.ports.persistence.mapper.ICategoryEntityMapper;
import emazon.stock.ports.persistence.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
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
        return categoryRepository.findByName(name).map(categoryEntityMapper::toModel);
    }

    @Override
    public Pagination<Category> listCategories(PaginationUtil paginationUtil) {
        Sort.Direction sortDirection = paginationUtil.isAscending() ? Sort.Direction.ASC : Sort.Direction.DESC;
        PageRequest pageRequest = PageRequest.of( paginationUtil.getPageNumber(), paginationUtil.getPageSize(), Sort.by(sortDirection, paginationUtil.getNameFilter()));
        Page<CategoryEntity> categoryPage = categoryRepository.findAll(pageRequest);
        List<Category> categories = categoryEntityMapper.toCategoryList(categoryPage.getContent());

        return new Pagination<>(
                paginationUtil.isAscending(),
                paginationUtil.getPageNumber(),
                categoryPage.getTotalPages(),
                categoryPage.getTotalElements(),
                categories
        );
    }

    @Override
    public boolean existsCategory(Long categoryId) {
        return categoryRepository.existsById(categoryId);
    }
}
