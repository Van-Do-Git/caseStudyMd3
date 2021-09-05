package com.case3.controller;

import com.case3.model.Expenditure;
import com.case3.model.Icon;
import com.case3.model.User;
import com.case3.service.icon.IconService;
import com.case3.service.limited.LimitedService;
import com.case3.service.ren_exp.ExpenditureService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

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
        int id_ex = Integer.parseInt(request.getParameter("idexp"));
        Expenditure expenditure = expenditureService.findById(id_ex);
        request.setAttribute("ex", expenditure);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/editexp.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        int min = Integer.parseInt(request.getParameter("min"));
        int max = Integer.parseInt(request.getParameter("max"));
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        List<Expenditure> listMoney = expenditureService.findByMoney(min, max, user.getId());
        int totalMoney = 0;
        for (int i = 0; i < listMoney.size(); i++) {
            totalMoney += listMoney.get(i).getMoney();
        }
        request.setAttribute("map",null);
        request.setAttribute("totalMoney", totalMoney);
        request.setAttribute("listEx", listMoney);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/expenditure.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
