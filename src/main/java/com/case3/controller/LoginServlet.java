package com.case3.controller;

import com.case3.model.Icon;
import com.case3.model.User;
import com.case3.service.icon.IconService;
import com.case3.service.user.UserService;
import com.case3.validate.Validate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

@WebServlet(name = "login", value = "/homepage")
public class LoginServlet extends HttpServlet {
    private UserService userService = new UserService();
    private IconService iconService = new IconService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "editStatus":
                editStatus(request, response);
                break;
            default:
                showHomepage(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "login":
                login(request, response);
                break;
            case "signUp":
                singUp(request, response);
                break;
            case "editIcon":
                editIcon(request, response);
            case "addIcon":
                addIcon(request, response);
            default:
                showHomepage(request, response);
        }
    }

    private void addIcon(HttpServletRequest request, HttpServletResponse response) {
        String linkIcon = request.getParameter("linkIcon");
        iconService.addIcon(linkIcon);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin.jsp?action=");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void editIcon(HttpServletRequest request, HttpServletResponse response) {
        int idIcon = Integer.parseInt(request.getParameter("idIcon"));
        String linkIcon = request.getParameter("linkIcon");
        iconService.editIcon(idIcon, linkIcon);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin.jsp?action=");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void editStatus(HttpServletRequest request, HttpServletResponse response) {

    }

    private void singUp(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/homepage?action=&username=&password=");
        String fullName = request.getParameter("fullName");
        String username = request.getParameter("userName");
        String phone = request.getParameter("phoneNumber");
        String password = request.getParameter("password");
        String role = "other";
        boolean status = true;
        User user = new User(fullName, phone, username, password, role, status);
        String message;
        Validate validate = Validate.getInstance();
        if (validate.validate(username, validate.regexUsername)
                && validate.validate(phone, validate.regexPhone)
                && validate.validate(password, validate.regexPassword)) {
            userService.save(user);
            message = "Đăng ký thành công!";
            request.setAttribute("message", message);
        } else {
            message = "Đăng ký không thành công - Vui lòng nhập lại!";
            request.setAttribute("message", message);
            request.setAttribute("user", user);
        }
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        String username = request.getParameter("userName");
        String password = request.getParameter("password");
        User user = userService.findByUsernameAndPassword(username, password);
        String destPage;
        if (user != null) {
            if (user.isStatus() && user.getRole().equals("other")) {
                HttpSession session = request.getSession();
                request.setAttribute("message", null);
                session.setAttribute("user", user);
                destPage = "/expenditure";
            } else if (user.isStatus() && user.getRole().equals("admin")) {
                HttpSession session = request.getSession();
                List<Icon> iconList = iconService.findAll();
                session.setAttribute("icon", iconList );
                request.setAttribute("message", null);
                session.setAttribute("user", user);
                List<User> userList = userService.findAll();
                request.setAttribute("userList", userList);
                destPage = "/admin.jsp";
            } else {
                destPage = "/homepage?action=&username=&password=";
                request.setAttribute("message", "Tài khoản của bạn đã bị khóa (vì lướt Facebook có nội dung cấm!)");
            }
        } else {
            destPage = "/homepage?action=&username=&password=";
            request.setAttribute("message", "Tài khoản không đúng, vui lòng đăng nhập lại hoặc đăng ký!");
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showHomepage(HttpServletRequest request, HttpServletResponse response) {

    }
}
