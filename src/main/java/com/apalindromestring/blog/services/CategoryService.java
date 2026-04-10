package com.apalindromestring.blog.services;


import com.apalindromestring.blog.domain.dtos.CreateCategoryRequest;
import com.apalindromestring.blog.domain.entities.Category;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface CategoryService {
    List<Category> listCategories();
    Category createCategory(Category category);
}
