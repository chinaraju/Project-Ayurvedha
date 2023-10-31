package com.anarghya.ayurvedha.service;

import java.util.Optional;

import com.anarghya.ayurvedha.model.CategoryModel;

public interface CategoryService  {
	
	public CategoryModel  saveCategory(CategoryModel model);
	public CategoryModel  updateCategory(CategoryModel model, Integer id);
	public void deleteCategory(Integer id);
	public Optional<CategoryModel>  getCategory(Integer id);
	public Iterable<CategoryModel>  getAllCategory();

}
