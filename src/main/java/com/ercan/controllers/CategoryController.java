package com.ercan.controllers;

import com.ercan.dtos.CategoryDto;
import com.ercan.services.CategoryService;
import com.ercan.utils.constans.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(Mappings.CATEGORY_PATH)
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping(Mappings.SAVE)
    public ResponseEntity<?> addCategory(@RequestBody CategoryDto categoryDto) {
        return ResponseEntity.ok(categoryService.save(categoryDto));
    }

    @PutMapping(Mappings.UPDATE)
    public ResponseEntity<?> updateCategory(@RequestBody CategoryDto categoryDto) {
        return ResponseEntity.ok(categoryService.update(categoryDto));
    }

    @GetMapping(Mappings.BY_ID)
    public ResponseEntity<?> getCategoryById(@PathVariable("id") Long categoryId) {
        return ResponseEntity.ok(categoryService.getCategoryById(categoryId));
    }

    @GetMapping(Mappings.ALL)
    public ResponseEntity<?> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }


    @DeleteMapping(Mappings.BY_ID)
    public ResponseEntity<?> deleteCategory(@PathVariable("id") Long categoryId) {
        categoryService.deleteById(categoryId);
        return new ResponseEntity<>(categoryId+" record deleted.", HttpStatus.OK);
    }
}
