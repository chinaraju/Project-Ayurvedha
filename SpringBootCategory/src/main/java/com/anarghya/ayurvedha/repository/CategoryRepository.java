package com.anarghya.ayurvedha.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.anarghya.ayurvedha.model.CategoryModel;

@Repository
public interface CategoryRepository extends CrudRepository<CategoryModel, Integer> {

}

