package ru.geekbrains.controller;

import ru.geekbrains.persist.CategoryRepository;
import ru.geekbrains.persist.Category;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class CategoryController implements Serializable {

    @EJB
    private CategoryRepository categoryRepository;

    private Category category;

    private List<Category> categories;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void preloadData(ComponentSystemEvent componentSystemEvent) {

        categories = categoryRepository.findAllCategories();
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public String createCategory() {
        this.category = new Category();
        return "/category_form.xhtml?faces-redirect=true";
    }


    public String editCategory(Category category) {
        this.category = category;
        return "/category_form.xhtml?faces-redirect=true";
    }

    public void deleteCategory(Category category) {
        categoryRepository.deleteById(category.getId());
    }

    public String saveCategory() {
        categoryRepository.saveOrUpdate(category);
        return "/category.xhtml?faces-redirect=true";
    }
}
