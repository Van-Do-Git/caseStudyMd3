package com.case3.service.category;

import com.case3.config.ConnectionJDBC;
import com.case3.model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryReService implements ICategoryService {
    private static final String SELECT_CATEGORIES_BY_USER_ID = "select * from revenue_categories join icon on revenue_categories.id_icon = icon.id_icon where id_user=?;";
    private static final String SELECT_CATEGORIES_BY_ID = "select *from revenue_categories join icon on revenue_categories.id_icon = icon.id_icon where id_rc=?;";
    private static final String II_RE_CATEGORIES = "insert into revenue_categories(name_rc,id_icon,id_user) value (?,?,?)";
    private static final String INSERT_RE_CATEGORY = "insert into revenue_categories (name_rc, id_icon, id_user) value (?,?,?)";
    private static final String UPDATE_RE_CATEGORY = "update revenue_categories set name_rc = ? where id_rc =?;";
    private static final String DELETE_RE_CATEGORY = "delete from revenue_categories where id_rc=?;";
    private static final String SELECT_RE_CATEGORY_BY_USER_ID = "select * from revenue_categories join icon on revenue_categories.id_icon = icon.id_icon where id_user=?;";


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
                int id_rc = resultSet.getInt("id_rc");
                String name = resultSet.getString("name_rc");
                String linkIcon = resultSet.getString("link_icon");
                category = new Category(id_rc, name, linkIcon);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }

    @Override
    public void save(Category category) {
        try {
            PreparedStatement statement = connection.prepareStatement(INSERT_RE_CATEGORY);
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
            PreparedStatement statement = connection.prepareStatement(UPDATE_RE_CATEGORY);
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
<<<<<<< HEAD
=======

>>>>>>> 8420a6f46d16d3c0919d0f569012fcc6759e9790
        try {
            PreparedStatement statement = connection.prepareStatement(DELETE_RE_CATEGORY);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
<<<<<<< HEAD
=======

>>>>>>> 8420a6f46d16d3c0919d0f569012fcc6759e9790
    }

    public List<Category> findByIdUser(int id) {
        List<Category> lists = new ArrayList<>();
        try {
<<<<<<< HEAD
            PreparedStatement statement = connection.prepareStatement(SELECT_CATEGORIES_BY_USER_ID);
=======


            PreparedStatement statement = connection.prepareStatement(SELECT_CATEGORIES_BY_USER_ID);




>>>>>>> 8420a6f46d16d3c0919d0f569012fcc6759e9790
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id_ec = resultSet.getInt("id_rc");
                String name = resultSet.getString("name_rc");
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
            PreparedStatement statement = connection.prepareStatement(II_RE_CATEGORIES);
            statement.setString(1, nameCate);
            statement.setInt(2, idIcon);
            statement.setInt(3, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
<<<<<<< HEAD
=======

>>>>>>> 8420a6f46d16d3c0919d0f569012fcc6759e9790
    }

    @Override
    public List<Category> findAllByUserId(int id_user) {
        List<Category> categories_Ex = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_RE_CATEGORY_BY_USER_ID);
            statement.setInt(1, id_user);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name_rc");
                String linkIcon = resultSet.getString("link_icon");
                Category category = new Category(id, name, linkIcon);
                categories_Ex.add(category);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return categories_Ex;
<<<<<<< HEAD
=======

>>>>>>> 8420a6f46d16d3c0919d0f569012fcc6759e9790
    }
}
