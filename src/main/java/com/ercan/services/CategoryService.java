package com.ercan.services;

import com.ercan.dtos.CategoryDto;

import java.util.List;
import java.util.Set;

public interface CategoryService {
    CategoryDto save(CategoryDto categoryDto);

    CategoryDto update(CategoryDto categoryDto);

    List<CategoryDto> getAllCategories();

    CategoryDto getCategoryById(Long categoryId);

    void deleteById(Long categoryId);
}
