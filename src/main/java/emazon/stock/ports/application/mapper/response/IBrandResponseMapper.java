package emazon.stock.ports.application.mapper.response;

import emazon.stock.domain.model.Brand;
import emazon.stock.ports.application.dto.response.BrandResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
@Mapper(componentModel = "spring")
public interface IBrandResponseMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "name")
    List<BrandResponse> toBrandResponses(List<Brand> brands);
}
