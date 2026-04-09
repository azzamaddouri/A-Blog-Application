package com.apalindromestring.blog.services.impl;

import com.apalindromestring.blog.domain.dtos.CategoryDto;
import com.apalindromestring.blog.domain.entities.Category;
import com.apalindromestring.blog.repositories.CategoryRepository;
import com.apalindromestring.blog.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> listCategories() {
        return categoryRepository.findAllWithPostCount();
    }
}
