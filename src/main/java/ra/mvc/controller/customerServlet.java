package ra.mvc.controller;

import ra.mvc.model.Customer;
import ra.mvc.service.customer.CustomerService;
import ra.mvc.service.customer.ICustomerService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "customerServlet", value = "/customers")
public class customerServlet extends HttpServlet {
    private ICustomerService customerService = new CustomerService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "GETALL":
                    request.setAttribute("customers", customerService.findAll());
                    request.getRequestDispatcher("/views/list-customer.jsp").forward(request, response);
                    break;
                case "DELETE":
                    Integer id = Integer.valueOf(request.getParameter("id"));
                    customerService.delete(id);
                    response.sendRedirect("/customers?action=GETALL");
                    break;
                case "DETAIL":
                    Integer idel = Integer.valueOf(request.getParameter("id"));
                    request.setAttribute("cus", customerService.findById(idel));
                    request.getRequestDispatcher("/views/detail-customer.jsp").forward(request, response);
                    break;
                case  "EDIT":
                    Integer idEdit = Integer.valueOf(request.getParameter("id"));
                    request.setAttribute("cus", customerService.findById(idEdit));
                    request.getRequestDispatcher("/views/edit-customer.jsp").forward(request, response);
                    break;


            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "ADD":
                    Customer newCustomer = new Customer(0,
                            request.getParameter("name"),
                            request.getParameter("address"),
                            request.getParameter("email"));
                    customerService.save(newCustomer);
                    response.sendRedirect("/customers?action=GETALL");
                    break;
                case "UPDATE":
                    Customer updateCustomer = new Customer(
                            Integer.parseInt(request.getParameter("id")),
                            request.getParameter("name"),
                            request.getParameter("address"),
                            request.getParameter("email"));
                    customerService.save(updateCustomer);
                    response.sendRedirect("/customers?action=GETALL");
                    break;
            }
        }
    }
}