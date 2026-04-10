package com.apalindromestring.blog.controllers;

import com.apalindromestring.blog.domain.dtos.CategoryDto;
import com.apalindromestring.blog.domain.dtos.CreateCategoryRequest;
import com.apalindromestring.blog.domain.entities.Category;
import com.apalindromestring.blog.mappers.CategoryMapper;
import com.apalindromestring.blog.services.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @GetMapping
    public ResponseEntity<List<CategoryDto>> listCategories() {
          List<CategoryDto> categories = categoryService.listCategories()
                  .stream().map(categoryMapper::ToDto)
                  .toList();

          return ResponseEntity.ok(categories);
    }


    @PostMapping
    public ResponseEntity<CategoryDto> addCategory(@Valid @RequestBody CreateCategoryRequest createCategoryRequest) {
        Category categoryToCreate = categoryMapper.toEntity(createCategoryRequest);
        Category savedCategory = categoryService.createCategory(categoryToCreate);
        return new ResponseEntity<>(categoryMapper.ToDto(savedCategory), HttpStatus.CREATED);
    }
}
