package com.ercan.services;

import com.ercan.dtos.CategoryDto;

import java.util.Set;

public interface CategoryService {
    CategoryDto save(CategoryDto categoryDto);

    CategoryDto update(CategoryDto categoryDto);

    Set<CategoryDto> getAllCategories();

    CategoryDto getCategoryById(Long categoryId);

    void deleteById(Long categoryId);
}
