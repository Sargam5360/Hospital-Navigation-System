/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hai.controller;

import com.hai.bean.DataBean;
import com.hai.dao.ContentDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Nisarg
 */
public class UserDataController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            if (request.getRequestURI().contains("userData")) {

                int age = Integer.parseInt(request.getParameter("age"));
                // int sex = Integer.parseInt(request.getParameter("sex"));
                String d_code = request.getParameter("d_code");

                DataBean dataBean = new DataBean();

                dataBean.setAge(age);
                //dataBean.setSex(sex);
                dataBean.setD_code(d_code);

               List<DataBean> content = ContentDao.retriveContent(dataBean);
               List<DataBean> content1 = ContentDao.retriveContent1(dataBean);
               List<DataBean> content2 = ContentDao.retriveContent2(dataBean);
                request.setAttribute("content", content);
               request.setAttribute("content1", content1);
               request.setAttribute("content2", content2);
               
                RequestDispatcher rd = request.getRequestDispatcher("/data.jsp");
              
                request.setAttribute("age", age);
                request.setAttribute("d_code", d_code);
                request.setAttribute("msg", "headerChange");
                rd.forward(request, response);
            } else if (request.getRequestURI().contains("overall")) {
                int age = Integer.parseInt(request.getParameter("age"));
                // int sex = Integer.parseInt(request.getParameter("sex"));
                String d_code = request.getParameter("d_code");
                
                DataBean dataBean = new DataBean();

                dataBean.setAge(age);
                //dataBean.setSex(sex);
                dataBean.setD_code(d_code);
                List<DataBean> content = ContentDao.retriveContent(dataBean);
                RequestDispatcher rd = request.getRequestDispatcher("/data.jsp");
                request.setAttribute("content", content);
                request.setAttribute("age", age);
                request.setAttribute("d_code", d_code);
                request.setAttribute("msg", "headerChange");
                request.setAttribute("tableName", "overall");
                rd.forward(request, response);
            }
            else if (request.getRequestURI().contains("stay")) {
                int age = Integer.parseInt(request.getParameter("age1"));
                // int sex = Integer.parseInt(request.getParameter("sex"));
                String d_code = request.getParameter("d_code1");
                
                DataBean dataBean = new DataBean();

                dataBean.setAge(age);
                //dataBean.setSex(sex);
                dataBean.setD_code(d_code);
                List<DataBean> content1 = ContentDao.retriveContent1(dataBean);
                RequestDispatcher rd = request.getRequestDispatcher("/data.jsp");
                request.setAttribute("content1", content1);
                request.setAttribute("age", age);
                request.setAttribute("d_code", d_code);
                request.setAttribute("msg", "headerChange");
                request.setAttribute("tableName", "stay");
                rd.forward(request, response);
            }
            else if (request.getRequestURI().contains("cost")) {
                int age = Integer.parseInt(request.getParameter("age2"));
                // int sex = Integer.parseInt(request.getParameter("sex"));
                String d_code = request.getParameter("d_code2");
                
                DataBean dataBean = new DataBean();

                dataBean.setAge(age);
                //dataBean.setSex(sex);
                dataBean.setD_code(d_code);
                List<DataBean> content2 = ContentDao.retriveContent2(dataBean);
                request.setAttribute("content2", content2);
                request.setAttribute("age", age);
                request.setAttribute("d_code", d_code);
                request.setAttribute("msg", "headerChange");
                
                RequestDispatcher rd = request.getRequestDispatcher("/data.jsp");
                request.setAttribute("tableName", "cost");
                rd.forward(request, response);
            }

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
