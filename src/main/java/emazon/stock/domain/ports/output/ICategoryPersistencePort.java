package emazon.stock.domain.ports.output;

import emazon.stock.domain.model.Category;
import emazon.stock.domain.model.Pagination;
import emazon.stock.domain.util.PaginationUtil;

import java.util.Optional;

public interface ICategoryPersistencePort {
    void createCategory(Category category);
    Optional<Category> findByName(String name);
    Pagination<Category> listCategories(PaginationUtil paginationUtil);
}
