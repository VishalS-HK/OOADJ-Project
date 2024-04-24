package com.jtspringproject.JtSpringProject.dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CategoryDaoProxy extends CategoryDao {
    private final Map<Integer, Category> categoryCache = new HashMap<>();

    @Autowired
    public CategoryDaoProxy(CategoryDaoInterface categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override

    public Boolean deleteCategory(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Object persistanceInstance = session.load(Category.class, id);

		if (persistanceInstance != null) {
			session.delete(persistanceInstance);
			return true;
		}
		return false;
	}

    @Override


    	public Category updateCategory(int id, String name) {
		Category category = this.sessionFactory.getCurrentSession().get(Category.class, id);
		category.setName(name);

		this.sessionFactory.getCurrentSession().update(category);
		return category;
	}
}
