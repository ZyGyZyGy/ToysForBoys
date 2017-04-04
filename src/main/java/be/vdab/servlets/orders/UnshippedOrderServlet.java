package be.vdab.servlets.orders;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.entities.Order;
import be.vdab.services.OrderDetailService;
import be.vdab.services.OrderService;

@WebServlet("/orders/unshippedorders.htm")
public class UnshippedOrderServlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    private static final String VIEW = "/WEB-INF/JSP/orders/unshippedorders.jsp";
    private static transient OrderService orderService = new OrderService();
    private static transient OrderDetailService orderDetailService = new OrderDetailService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	request.setAttribute("orders", orderService.findAll());
	request.getRequestDispatcher(VIEW).forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	String[] orderIds =  request.getParameterValues("id");
	if (orderIds != null) {
	    orderService.ship(orderIds);
	}
    }

}
