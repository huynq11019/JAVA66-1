package com.TTS.maper;

import java.util.List;
import java.util.stream.Collectors;

import com.TTS.Entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.TTS.DTO.ProductDTO;
import com.TTS.Entity.Product;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ProductMapper {
	@Mappings({ @Mapping(source = "category.name", target = "cateGoryofPro"),
			@Mapping(source = "category.id", target = "category")
	})
	ProductDTO toDTO(Product entity);

	@Mappings({
			@Mapping(source = "dto", target = "category",qualifiedByName = "integerToCate"),
//	@Mapping(target = "category", ignore = true)
	})
	Product toEntity(ProductDTO dto);

	default List<ProductDTO> toListDto(List<Product> entityList) {

		return entityList.stream().map(this::toDTO).collect(Collectors.toList());
	}

	default List<Product> toListEntity(List<ProductDTO> dtoList) {
		return dtoList.stream().map(this::toEntity).collect(Collectors.toList());
	}
	@Named("integerToCate")
	default Category toCate(ProductDTO dto){
		if (dto == null ){
			return null;
		}
		if(dto.getCategory() == null){
			return null;
		}
		Category cate = new Category();
		cate.setId(dto.getId());
		return cate;
	}
}
