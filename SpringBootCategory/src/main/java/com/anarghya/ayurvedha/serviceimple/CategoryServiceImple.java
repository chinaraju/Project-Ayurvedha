package com.anarghya.ayurvedha.serviceimple;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anarghya.ayurvedha.model.CategoryModel;
import com.anarghya.ayurvedha.repository.CategoryRepository;
import com.anarghya.ayurvedha.service.CategoryService;

@Service
public class CategoryServiceImple implements CategoryService{
	
	@Autowired
     CategoryRepository service;	

	@Override
	public CategoryModel saveCategory(CategoryModel model) {
		// TODO Auto-generated method stub
		return service.save(model);
	}

	@Override
	public CategoryModel updateCategory(CategoryModel model,Integer id) {
		// TODO Auto-generated method stub
		Optional<CategoryModel> optional = service.findById(id);
		  CategoryModel models =optional.get();
		  models.setCategoryId(model.getCategoryId());
		  models.setCategoryName(model.getCategoryName());
		return service.save(models);
	}

	@Override
	public void deleteCategory(Integer id) {
		// TODO Auto-generated method stub
		
		   service.deleteById(id);
		
	}

	@Override
	public Optional<CategoryModel> getCategory(Integer id) {
		// TODO Auto-generated method stub
		return service.findById(id);
	}

	@Override
	public Iterable<CategoryModel> getAllCategory() {
		// TODO Auto-generated method stub
		return service.findAll();
	}
	
	

}
