<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@taglib uri='http://java.sun.com/jsp/jstl/fmt' prefix='fmt'%>
<%@taglib uri='http://vdab.be/tags' prefix='v'%>
<!doctype html>
<html lang='nl'>
<head>
	<v:head title='Order detail'/>
</head>
<body>
	<v:menu />
	<h2>Order ${order.id}<span>${fouten.id}</span></h2>
	<dl>
		<dt>Ordered:</dt>
		<dd><fmt:formatDate value="${order.orderDate}" type="date" dateStyle="short" pattern="dd/MM/yy"/></dd>
		<dt>Required:</dt>
		<dd><fmt:formatDate value="${order.requiredDate}" type="date" dateStyle="short" pattern="dd/MM/yy"/></dd>
		<dt>Customer:</dt>
		<dd>${order.customer.name}<br>
			${order.customer.adres.streetAndNumber}<br>
			${order.customer.adres.postalCode} ${order.customer.adres.city} ${order.customer.adres.state}<br>
			${order.customer.country.name}
		</dd>
		<dt>Comments:</dt>
		<dd>${not empty order.comments ? order.comments : 'N/A'}</dd>
		<dt>Details:</dt>
		<dd>
			<table class="detailTabel">
				<thead>
					<tr>
						<th>Product</th><th>Price each</th><th>Quantity</th><th>Value</th><th>Deliverable</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${order.orderDetails}" var="orderDetail">
						<tr>
							<td>${orderDetail.product.name}</td>
							<td>${orderDetail.priceEach}</td>
							<td>${orderDetail.quantityOrdered}</td>
							<td>${orderDetail.value}</td>
							<td class="deliverable">
								<c:choose>
									<c:when test="${orderDetail.quantityOrdered <= orderDetail.product.quantityInStock}">
										<span class="groen">&check;</span>
									</c:when>
									<c:otherwise>
										<span class="rood">&cross;</span>
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</dd>
		<dt>Value:</dt>
		<dd><fmt:formatNumber value="${order.totalValue}"/></dd>
	</dl>
</body>
</html>