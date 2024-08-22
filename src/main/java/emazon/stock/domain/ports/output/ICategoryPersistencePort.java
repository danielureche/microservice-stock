package emazon.stock.domain.ports.output;

import emazon.stock.domain.model.Category;

public interface ICategoryPersistencePort {
    void createCategory(Category category);
}
