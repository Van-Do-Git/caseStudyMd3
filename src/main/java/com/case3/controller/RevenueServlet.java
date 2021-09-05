package com.case3.controller;


import com.case3.model.*;
import com.case3.service.category.CategoryReService;
import com.case3.service.icon.IconService;

import com.case3.service.ren_exp.RevenueService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@WebServlet(name = "Revenue", value = "/revenue")
public class RevenueServlet extends HttpServlet {
    RevenueService revenueService = new RevenueService();
    CategoryReService categoryReService = new CategoryReService();
    IconService iconService = new IconService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void showFormAddCategory(HttpServletRequest request, HttpServletResponse response) {

    }

    private void showFormEditRevenue(HttpServletRequest request, HttpServletResponse response) {

    }

    private void showRevenue(HttpServletRequest request, HttpServletResponse response) {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
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
            case "editre":
                editRevenue(request, response);
                break;
            case "addre":
                addNewRevenue(request, response);
                break;
            case "addCate":
                addNewCategory(request, response);
                break;
            default:
                if (session.getAttribute("user") == null) {
                    response.sendRedirect("/homepage");
                } else {
                    showRevenue(request, response);
                }
                break;
        }
    }

    private void showListByMonth(HttpServletRequest request, HttpServletResponse response) {

    }

    private void showListByWeek(HttpServletRequest request, HttpServletResponse response) {

    }

    private void showListByDay(HttpServletRequest request, HttpServletResponse response) {

    }

    private void showListByMoney(HttpServletRequest request, HttpServletResponse response) {

    }

    private void addNewCategory(HttpServletRequest request, HttpServletResponse response) {

    }

    private void editRevenue(HttpServletRequest request, HttpServletResponse response) {

    }

    private void addNewRevenue(HttpServletRequest request, HttpServletResponse response) {

    }
}
