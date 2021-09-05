package com.case3.service.user;

import com.case3.config.ConnectionJDBC;
import com.case3.model.Category;
import com.case3.model.Limited;
import com.case3.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserService implements IUserService<User> {
    private static final String UPDATE_STATUS = "update users set status = ? where id_user = ?;";
    Connection connection = ConnectionJDBC.getConnection();
    private static final String ADD_EX_CATE = "insert into expenditure_categories(name_ec,id_icon,id_user) value (?,?,?)";
    private static final String ADD_RE_CATE = "insert into revenue_categories(name_rc,id_icon,id_user) value (?,?,?)";
    private static final String FIND_USER = "select * from users join role on users.id_role = role.id_role join limited on users.id_user = limited.id_user where username = ? and password = ?;";
    private static final String ADD_USER = "insert into users(fullName, phone, username, password, id_role) VALUE (?,?,?,?,?);";
    private static final String ADD_LIMITED = "insert into limited(id_user) VALUE (?);";
    private static final String SELECT_ALL_USERS = "select * from role join users on role.id_role = users.id_role;";
    private static List<Category> listExCate = new ArrayList<>();
    private static List<Category> listReCate = new ArrayList<>();

    static {
        listExCate.add(new Category("Tien dien"));
        listExCate.add(new Category("Tien nuoc"));
        listExCate.add(new Category("Tien xang"));
        listReCate.add(new Category("Tien luong"));
        listReCate.add(new Category("Tien thuong"));
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();

        return users;

    }

    @Override
    public User findById(int id) {
        return null;
    }

    @Override
    public void save(User user) {

    }

    @Override
    public void edit(User user, int id) {

    }

    public void updateStatus(int id_user, boolean status) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        User user = null;

        return user;
    }
}
