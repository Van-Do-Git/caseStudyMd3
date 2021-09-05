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
    private static final String INSERT_EX_CATEGORY = "insert into expenditure_categories (name_ec, id_icon, id_user) value (?,?,?)";
    private static final String UPDATE_EX_CATEGORY = "update expenditure_categories set name_ec = ? where id_ec =?";
    private static final String DELETE_EX_CATEGORY = "delete from expenditure_categories where id_ec=?;";
    private static final String SELECT_EX_CATEGORY_BY_USER_ID = "select * from expenditure_categories join icon on expenditure_categories.id_icon = icon.id_icon where id_user=?;";

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
        try {
            PreparedStatement statement = connection.prepareStatement(INSERT_EX_CATEGORY);
            statement.setInt(1, category.getId());
            statement.setString(2, category.getName());
            statement.setString(3, category.getLinkIcon());
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void edit(Category category, int id) {
        try {
            PreparedStatement statement = connection.prepareStatement(UPDATE_EX_CATEGORY);
            statement.setInt(1, category.getId());
            statement.setString(1, category.getName());
            statement.setString(1, category.getLinkIcon());
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement(DELETE_EX_CATEGORY);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Category> findByIdUser(int id) {
        List<Category> lists = new ArrayList<>();
        try {

            PreparedStatement statement = connection.prepareStatement(SELECT_CATEGORIES_BY_USER_ID);

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id_ec = resultSet.getInt("id_ec");
                String name = resultSet.getString("name_ec");
                String linkIcon = resultSet.getString("link_icon");
                lists.add(new Category(id_ec, name, linkIcon));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return lists;
    }

    public void addNewCate(int idIcon, String nameCate, int id) {
        try {
            PreparedStatement statement = connection.prepareStatement(II_EX_CATEGORIES);
            statement.setString(1, nameCate);
            statement.setInt(2, idIcon);
            statement.setInt(3, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Category> findAllByUserId(int id_user) {
        List<Category> categories_Ex = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_EX_CATEGORY_BY_USER_ID);
            statement.setInt(1, id_user);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name_ec");
                String linkIcon = resultSet.getString("link_icon");
                Category category = new Category(id, name, linkIcon);
                categories_Ex.add(category);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return categories_Ex;
    }
}
