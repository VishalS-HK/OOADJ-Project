package com.jtspringproject.JtSpringProject.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jtspringproject.JtSpringProject.models.Category;

@Repository
public class categoryDao {
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Transactional
	public Category addCategory(String name) {
		Category category = new Category();
		category.setName(name);
		this.sessionFactory.getCurrentSession().saveOrUpdate(category);
		return category;
	}

	@Transactional
	public List<Category> getCategories() {
		return this.sessionFactory.getCurrentSession().createQuery("from CATEGORY").list();
	}

	@Transactional
	    public Boolean deleteCategory(int id) {
        boolean result = categoryDao.deleteCategory(id);
        if (result) {
            categoryCache.remove(id);
        }
        return result;
    }

	@Transactional
    public Category updateCategory(int id, String name) {
        Category category = categoryDao.updateCategory(id, name);
        categoryCache.put(id, category);
        return category;
    }

	@Transactional
	public Category getCategory(int id) {
		return this.sessionFactory.getCurrentSession().get(Category.class,id);
	}
}
