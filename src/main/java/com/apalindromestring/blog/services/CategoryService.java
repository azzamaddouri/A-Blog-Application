package com.apalindromestring.blog.services;


import com.apalindromestring.blog.domain.entities.Category;

import java.util.List;

public interface CategoryService {
    List<Category> listCategories();
}
