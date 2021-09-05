package com.case3.controller;

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

    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {

    }

    private void showHomepage(HttpServletRequest request, HttpServletResponse response) {

    }

}
