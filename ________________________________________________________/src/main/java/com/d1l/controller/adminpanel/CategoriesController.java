package com.d1l.controller.adminpanel;

import com.d1l.dao.CategoryDao;
import com.d1l.model.Category;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CategoriesController extends ActionSupport {

    private Category category;
    private List<Category> categoriesList;
    private int id;

    @Override
    public String execute() throws Exception {try{
        categoriesList = CategoryDao.getCategoriesList();
        return Action.SUCCESS;}
    catch (Exception exp) {}
    return Action.SUCCESS;
    }

    public String update() {try{
        if (!validate(getCategory())) return Action.SUCCESS;
        CategoryDao.addOrUpdateCategory(getCategory());
        return Action.SUCCESS;}
    catch (Exception exp) {}
        return Action.SUCCESS;
    }

    public String delete() {try{
        CategoryDao.deleteCategory(getId());
        return Action.SUCCESS;}
    catch (Exception exp) {}
        return Action.SUCCESS;
    }

    public String add() {try{
        if (!validate(getCategory())) return Action.SUCCESS;
        CategoryDao.addOrUpdateCategory(getCategory());
        return Action.SUCCESS;}
    catch (Exception exp) {}
    return Action.SUCCESS;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Category> getCategoriesList() {
        return categoriesList;
    }

    public void setCategoriesList(List<Category> categoriesList) {
        this.categoriesList = categoriesList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String errorString;

    public String getErrorString() {
        return errorString;
    }

    public void setErrorString(String errorString) {
        this.errorString = errorString;
    }

    private boolean validate(Category category)
    {try{
        Pattern namePattern = Pattern.compile("^[A-Za-z\\s]{1,100}$");
        Matcher m = namePattern.matcher(category.getName());
        if (!m.matches())
        {
            errorString = "The name is invalid";
            return false;
        }
        return true;}
    catch (Exception exp) {
        return false;}
    }

}
