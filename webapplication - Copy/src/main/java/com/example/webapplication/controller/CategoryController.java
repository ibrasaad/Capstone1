package com.example.webapplication.controller;

import com.example.webapplication.modle.Category;
import com.example.webapplication.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/get")
    public ResponseEntity<?>getallcatgories(){
        return ResponseEntity.status(200).body(categoryService.getCategorieslist());
    }
    @PostMapping("/add")
    public ResponseEntity<?>addcatgories(@RequestBody @Valid Category category , Errors errors){
    if(errors.hasErrors()){
        String message = errors.getFieldError().getDefaultMessage();
        return ResponseEntity.status(400).body(message);
    }
    categoryService.addcatgory(category);
    return ResponseEntity.status(200).body("add succfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updatecatgoriges(@PathVariable String id , @RequestBody @Valid Category category ){
    boolean updated = categoryService.updatecatgory(id,category);
    if(updated == false){
        return ResponseEntity.status(400).body("ID Not Found ");
    }
    categoryService.updatecatgory(id, category);
    return ResponseEntity.status(200).body("updated succfully");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletecatgories(@PathVariable String id ){
    boolean deleted = categoryService.deletecatgory(id);
    if(deleted == false){
        return ResponseEntity.status(400).body("ID Not found ");
    }
    categoryService.deletecatgory(id);
    return ResponseEntity.status(200).body("deleted succfully");
    }


}
