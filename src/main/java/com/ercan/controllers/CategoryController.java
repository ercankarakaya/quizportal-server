package com.ercan.controllers;

import com.ercan.dtos.CategoryDto;
import com.ercan.enums.ResponseStatusEnum;
import com.ercan.dtos.responses.Response;
import com.ercan.services.CategoryService;
import com.ercan.utils.constans.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Mappings.CATEGORY_PATH)
@CrossOrigin("*")
public class CategoryController {

    /**
     * SERVICES
     */
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

    @GetMapping(Mappings.ALL)
    public ResponseEntity<?> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping(Mappings.BY_ID)
    public ResponseEntity<?> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    @DeleteMapping(Mappings.BY_ID)
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        categoryService.deleteById(id);
        return ResponseEntity.ok(new Response(id + " Deleted.", ResponseStatusEnum.SUCCESS, null));
    }
}
