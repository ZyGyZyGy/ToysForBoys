package be.vdab.servlets.orders;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.services.OrderService;
import be.vdab.utils.StringUtils;

@WebServlet("/orders/detail.htm")
public class OrderDetailServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final String VIEW = "/WEB-INF/JSP/orders/orderdetail.jsp";
    private static transient OrderService orderService = new OrderService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	if (request.getQueryString() != null) {
	    String idString = request.getParameter("id");
	    if (StringUtils.isLong(idString)) {
		orderService.read(Long.parseLong(idString))
			.ifPresent(order -> request.setAttribute("order", order));
	    } else {
		request.setAttribute("fouten", Collections.singletonMap("id", "Ongeldige id waarde"));
	    }
	} 
	request.getRequestDispatcher(VIEW).forward(request, response);
    }

}
