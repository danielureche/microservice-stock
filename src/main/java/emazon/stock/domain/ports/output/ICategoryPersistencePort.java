package emazon.stock.domain.ports.output;

import emazon.stock.domain.model.Category;

import java.util.Optional;

public interface ICategoryPersistencePort {
    void createCategory(Category category);
    Optional<Category> findByName(String name);
}
