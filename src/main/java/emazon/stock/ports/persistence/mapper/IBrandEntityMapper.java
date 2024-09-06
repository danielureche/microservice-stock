package emazon.stock.ports.persistence.mapper;

import emazon.stock.domain.model.Brand;
import emazon.stock.ports.persistence.entity.BrandEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IBrandEntityMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    BrandEntity toEntity(Brand brand);
    Brand toModel(BrandEntity brandEntity);
    List<Brand> toBrandList(List<BrandEntity> brandEntityList);
}
