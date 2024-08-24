package emazon.stock.domain.ports.input;

import emazon.stock.domain.model.Category;
import emazon.stock.domain.model.Pagination;
import emazon.stock.domain.util.PaginationUtil;

public interface ICategoryServicePort {
    void createCategory(Category category);
    Pagination<Category> listCategories(PaginationUtil paginationUtil);
}
