package com.anarghya.ayurvedha.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anarghya.ayurvedha.model.CategoryModel;
import com.anarghya.ayurvedha.service.CategoryService;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/category")
public class CategotyController {
	
	@Autowired
	public CategoryService service;
	
	@PostMapping("/save")
	public CategoryModel  saveCategory(@RequestBody CategoryModel model) {
		return service.saveCategory(model);
	}
	
	@PutMapping("/update/{id}")
	public CategoryModel  updateCategory(@RequestBody CategoryModel model ,@PathVariable Integer id) {
		 return service.updateCategory(model, id);
		
	}
 
	 @DeleteMapping("/delete/{id}")
	 public String deleteById(@PathVariable Integer id) {
		 String ids=Integer.toString(id);
		 service.deleteCategory(id);
		return "delete sucessfull by id : "+ids;
	 }
	 
	 @GetMapping("get/{id}")
	 public Optional<CategoryModel> getCategoryById(@PathVariable Integer id){
		 return service.getCategory(id);
	 }
	 
	 @GetMapping("/getAll")
	 public Iterable<CategoryModel> getAllCaIterable(){
		 return service.getAllCategory();
	 }
	
}
