package com.case3.service.category;

import com.case3.config.ConnectionJDBC;
import com.case3.model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryExService implements ICategoryService {
    private static final String SELECT_CATEGORIES_BY_USER_ID =
            "select * from expenditure_categories join icon on expenditure_Categories.id_icon = icon.id_icon where id_user=?;";
    private static final String SELECT_CATEGORIES_BY_ID =
            "select *from expenditure_categories join icon on expenditure_Categories.id_icon = icon.id_icon where id_ec=?;";
    private static final String II_EX_CATEGORIES = "insert into expenditure_categories(name_ec,id_icon,id_user) value (?,?,?)";

    Connection connection = ConnectionJDBC.getConnection();

    @Override
    public List<Category> findAll() {
        return null;
    }

    @Override
    public Category findById(int id) {
        Category category = null;
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_CATEGORIES_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id_ec = resultSet.getInt("id_ec");
                String name = resultSet.getString("name_ec");
                String linkIcon = resultSet.getString("link_icon");
                category = new Category(id_ec, name, linkIcon);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }

    @Override
    public void save(Category category) {

    }

    @Override
    public void edit(Category category, int id) {

    }

    @Override
    public void delete(int id) {

    }

    public List<Category> findByIdUser(int id) {
        List<Category> lists = new ArrayList<>();

        return lists;
    }

    public void addNewCate(int idIcon, String nameCate, int id) {

    }
}
