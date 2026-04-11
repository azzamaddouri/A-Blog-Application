package com.apalindromestring.blog.services.impl;

import com.apalindromestring.blog.domain.dtos.CategoryDto;
import com.apalindromestring.blog.domain.entities.Category;
import com.apalindromestring.blog.repositories.CategoryRepository;
import com.apalindromestring.blog.services.CategoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> listCategories() {
        return categoryRepository.findAllWithPostCount();
    }

    @Override
    @Transactional
    public Category createCategory(Category category) {
        if (categoryRepository.existsByNameIgnoreCase(category.getName())) {
            throw new IllegalArgumentException("Category with name " + category.getName() + " already exists");
        }
        return categoryRepository.save(category);
        }

    @Override
    public void deleteCategory(UUID id) {
        Optional<Category> category = categoryRepository.findById(id);

        if (category.isPresent()) {
            if (!category.get().getPosts().isEmpty()) {
                throw new IllegalArgumentException("Category with id " + id + " already has posts");

            }
            categoryRepository.delete(category.get());
        }


    }
}
