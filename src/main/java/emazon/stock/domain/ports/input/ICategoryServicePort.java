package emazon.stock.domain.ports.input;

import emazon.stock.domain.model.Category;

public interface ICategoryServicePort {
    void createCategory(Category category);
}
