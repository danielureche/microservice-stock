package emazon.stock.configuration.bean;

import emazon.stock.domain.model.usecase.BrandUseCase;
import emazon.stock.domain.model.usecase.CategoryUseCase;
import emazon.stock.domain.ports.input.IBrandServicePort;
import emazon.stock.domain.ports.input.ICategoryServicePort;
import emazon.stock.domain.ports.output.IBrandPersistencePort;
import emazon.stock.domain.ports.output.ICategoryPersistencePort;
import emazon.stock.ports.application.mapper.request.IBrandRequestMapper;
import emazon.stock.ports.persistence.adapter.BrandAdapter;
import emazon.stock.ports.persistence.adapter.CategoryAdapter;
import emazon.stock.ports.persistence.mapper.IBrandEntityMapper;
import emazon.stock.ports.persistence.mapper.ICategoryEntityMapper;
import emazon.stock.ports.persistence.repository.IBrandRepository;
import emazon.stock.ports.persistence.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryEntityMapper;
    private final IBrandRepository brandRepository;
    private final IBrandEntityMapper brandEntityMapper;

    @Bean
    public ICategoryPersistencePort categoryPersistencePort(){
        return new CategoryAdapter(categoryRepository, categoryEntityMapper);
    }
    @Bean
    public ICategoryServicePort categoryServicePort(){
        return new CategoryUseCase(categoryPersistencePort());
    }

    @Bean
    public IBrandPersistencePort brandPersistencePort(){
        return new BrandAdapter(brandRepository, brandRepository);
    }
    @Bean
    public IBrandServicePort brandServicePort(){
        return new BrandUseCase(brandPersistencePort());
    }
}
