package com.jtspringproject.JtSpringProject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jtspringproject.JtSpringProject.dao.categoryDao;
import com.jtspringproject.JtSpringProject.models.Category;

import net.bytebuddy.dynamic.DynamicType.Builder.InnerTypeDefinition;

public class categoryService {
	private categoryDao categoryDao;
	private categoryDaoProxy categoryDaoProxy;

	public Category addCategory(String name) {
		return this.categoryDao.addCategory(name);
	}

	public List<Category> getCategories(){
		return this.categoryDao.getCategories();
	}

	public Boolean deleteCategory(int id) {
		return this.categoryDaoProxy.deleteCategory(id);
	}

	public Category updateCategory(int id,String name) {
		return this.categoryDaoProxy.updateCategory(id, name);
	}

	public Category getCategory(int id) {
		return this.categoryDao.getCategory(id);
	}
}
