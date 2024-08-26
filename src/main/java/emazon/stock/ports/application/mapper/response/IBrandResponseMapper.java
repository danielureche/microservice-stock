package emazon.stock.ports.application.mapper.response;

import emazon.stock.domain.model.Brand;
import emazon.stock.ports.application.dto.response.BrandResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IBrandResponseMapper {
    List<BrandResponse> toBrandResponses(List<Brand> brands);
}
