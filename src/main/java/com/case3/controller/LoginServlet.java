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

}