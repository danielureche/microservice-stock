package emazon.stock.domain.model.usecase;

import emazon.stock.configuration.exception.InvalidPageIndexException;
import emazon.stock.domain.model.Category;
import emazon.stock.domain.model.Pagination;
import emazon.stock.domain.ports.input.ICategoryServicePort;
import emazon.stock.domain.ports.output.ICategoryPersistencePort;
import emazon.stock.configuration.exception.CategoryAlreadyExistsException;
import emazon.stock.domain.util.PaginationUtil;

import java.util.Optional;

public class CategoryUseCase implements ICategoryServicePort {
    private final ICategoryPersistencePort categoryPersistencePort;

    public CategoryUseCase(ICategoryPersistencePort categoryPersistencePort) {
        this.categoryPersistencePort = categoryPersistencePort;
    }
    @Override
    public void createCategory(Category category) {
        Optional<Category> categoryByName = categoryPersistencePort.findByName(category.getName());
        if (categoryByName.isPresent()){
            throw new CategoryAlreadyExistsException();
        }
        categoryPersistencePort.createCategory(category);
    }

    @Override
    public Pagination<Category> listCategories(PaginationUtil paginationUtil) {
        if (paginationUtil.getPageNumber() < 0 || paginationUtil.getPageSize() < 0) {
            throw new InvalidPageIndexException("Page index is out of range ");
        }

        return categoryPersistencePort.listCategories(paginationUtil);
    }
}
