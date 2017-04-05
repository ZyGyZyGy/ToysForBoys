package be.vdab.servlets.orders;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.entities.Order;
import be.vdab.exceptions.VoorraadException;
import be.vdab.services.OrderService;

@WebServlet("/orders/unshippedorders.htm")
public class UnshippedOrderServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final String VIEW = "/WEB-INF/JSP/orders/unshippedorders.jsp";
    private static transient OrderService orderService = new OrderService();
    private static final int AANTAL_RIJEN = 50;

    private void pagineren(HttpServletRequest request, HttpServletResponse response) 
	    throws ServletException, IOException {
	int vanafRij = request.getParameter("vanafRij") == null ? 0
		: Integer.parseInt(request.getParameter("vanafRij"));
	request.setAttribute("vanafRij", vanafRij);
	request.setAttribute("aantalRijen", AANTAL_RIJEN);
	List<Order> orders = orderService.findAll(vanafRij, AANTAL_RIJEN + 1);
	if (orders.size() <= AANTAL_RIJEN) {
	    request.setAttribute("laatstePagina", true);
	} else {
	    orders.remove(AANTAL_RIJEN);
	}
	request.setAttribute("orders", orders);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	pagineren(request, response);
	request.getRequestDispatcher(VIEW).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	String[] orderIds = request.getParameterValues("ship");
	List<Order> mislukteOrders = new ArrayList<>();
	if (orderIds != null) {
	    for (String orderId : orderIds) {
		try {
		    orderService.read(Long.parseLong(orderId)).ifPresent(order -> orderService.ship(order));
		} catch (VoorraadException ex) {
		    orderService.read(Long.parseLong(orderId)).ifPresent(order -> mislukteOrders.add(order));
		}
	    }
	}
	if (mislukteOrders.isEmpty()) {
	    response.sendRedirect(request.getRequestURI());
	} else {
	    pagineren(request, response);
	    request.setAttribute("mislukteOrders", mislukteOrders);
	    request.getRequestDispatcher(VIEW).forward(request, response);
	}
    }

}
