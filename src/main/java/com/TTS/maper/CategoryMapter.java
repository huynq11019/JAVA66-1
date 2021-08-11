package com.TTS.maper;

import com.TTS.DTO.CategoryDTO;
import com.TTS.Entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapter  extends  EntityMaper<CategoryDTO, Category>{

}
