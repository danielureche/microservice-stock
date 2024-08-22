package emazon.stock.domain.model.usecase;

import emazon.stock.domain.model.Category;
import emazon.stock.domain.ports.input.ICategoryServicePort;
import emazon.stock.domain.ports.output.ICategoryPersistencePort;

public class CategoryUseCase implements ICategoryServicePort {
    private final ICategoryPersistencePort categoryPersistencePort;

    public CategoryUseCase(ICategoryPersistencePort categoryPersistencePort) {
        this.categoryPersistencePort = categoryPersistencePort;
    }
    @Override
    public void createCategory(Category category) {
        categoryPersistencePort.createCategory(category);
    }
}
