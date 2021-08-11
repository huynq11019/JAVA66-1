package com.TTS.Rest;

import com.TTS.DTO.CategoryDTO;
import com.TTS.Entity.Category;
import com.TTS.Service.CategoriesService;
import com.TTS.maper.CategoryMapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;
@CrossOrigin("*")
@RestController
public class RestCategory {

    @Autowired
    private CategoriesService categoriesService;
    @Autowired
    private CategoryMapter categoryMapter;

    @GetMapping("/api/categories")
    public List<CategoryDTO> loadAllCate() {

        List<Category> listEnityt = categoriesService.loadAll();
        List<CategoryDTO> listCateDto = categoryMapter.toDto(listEnityt);
        return listCateDto;
    }

    @PostMapping("/api/category")
    public CategoryDTO CreateCate(CategoryDTO dto) {

        Category cate = categoryMapter.toEntity(dto);

        try {
            Category category = categoriesService.create(cate);
            return categoryMapter.toDto(category);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException(throwables);
        }

    }
}
;


