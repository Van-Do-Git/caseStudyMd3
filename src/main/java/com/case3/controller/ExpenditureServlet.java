package com.case3.controller;

import com.case3.model.Category;
import com.case3.model.Expenditure;
import com.case3.model.Icon;
import com.case3.model.Limited;
import com.case3.model.User;
import com.case3.service.category.CategoryExService;
import com.case3.service.icon.IconService;
import com.case3.service.limited.LimitedService;
import com.case3.service.ren_exp.ExpenditureService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@WebServlet(name = "ExpenditureServlet", value = "/expenditure")
public class ExpenditureServlet extends HttpServlet {
    ExpenditureService expenditureService = new ExpenditureService();
    CategoryExService categoryExService = new CategoryExService();
    LimitedService limitedService = new LimitedService();
    IconService iconService = new IconService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "addCate":
                showFormAddCategory(request, response);
                break;
            case "logout":
                session.removeAttribute("user");
                response.sendRedirect("/expenditure?action=");
                break;
            case "addexp":
                response.sendRedirect("/addexp.jsp");
                break;
            case "editexp":
                showFormEditExpenditure(request, response);
                break;
            default:
                if (session.getAttribute("user") == null) {
                    response.sendRedirect("/homepage");
                } else {
                    showExpenditure(request, response);
                }
                break;

        }
    }

    private void showFormAddCategory(HttpServletRequest request, HttpServletResponse response) {
        String type = "exp";
        List<Icon> iconList = iconService.findAll();
        request.setAttribute("icon", iconList);
        request.setAttribute("type", type);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/addcate.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showFormEditExpenditure(HttpServletRequest request, HttpServletResponse response) {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "money":
                showListByMoney(request, response);
                break;
            case "day":
                showListByDay(request, response);
                break;
            case "week":
                showListByWeek(request, response);
                break;
            case "month":
                showListByMonth(request, response);
                break;
            case "editday":
                editLimitDay(request, response);
                break;
            case "editmonth":
                editLimitMonth(request, response);
                break;
            case "editexp":
                editExpenditure(request, response);
                break;
            case "addexp":
                addNewExpenditure(request, response);
                break;
            case "addCate":
                addNewCategory(request, response);
                break;
            default:
                showExpenditure(request, response);
        }
    }

    private void showListByMoney(HttpServletRequest request, HttpServletResponse response) {

    }

    private void addNewCategory(HttpServletRequest request, HttpServletResponse response) {

    }

    private void showListByMonth(HttpServletRequest request, HttpServletResponse response) {

    }

    private void showListByWeek(HttpServletRequest request, HttpServletResponse response) {

    }

    private void showListByDay(HttpServletRequest request, HttpServletResponse response) {

    }

    private void editLimitMonth(HttpServletRequest request, HttpServletResponse response) {

    }

    private void editLimitDay(HttpServletRequest request, HttpServletResponse response) {

    }

    private void editExpenditure(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("");
    }

    private void addNewExpenditure(HttpServletRequest request, HttpServletResponse response) {

    }

    private void showExpenditure(HttpServletRequest request, HttpServletResponse response) {

    }

}
