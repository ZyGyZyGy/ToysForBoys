<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@taglib uri='http://java.sun.com/jsp/jstl/fmt' prefix='fmt'%>
<%@taglib uri='http://vdab.be/tags' prefix='v'%>
<!doctype html>
<html lang='nl'>
<head>
	<v:head title='Unshipped orders' />
</head>
<body>
	<v:menu />
	<h1>Unshipped orders</h1>
	<table>
		<thead>
			<tr>
				<th>ID</th><th>Ordered</th><th>Required</th><th>Customer</th><th>Comments</th><th>Status</th><th>Ship</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${orders}" var="order">
				<c:url value="/orders/detail.htm" var="detailURL">
					<c:param name="id" value="${order.id}"/>
				</c:url>
				<c:if test="${order.status != 'SHIPPED' and order.status != 'CANCELLED'}">
					<tr>
						<td><a href="${detailURL}">${order.id}</a></td>
						<td><fmt:formatDate value="${order.orderDate}" type="date" dateStyle="short"/></td>
						<td><fmt:formatDate value="${order.requiredDate}" type="date" dateStyle="short"/></td>
						<td>${order.customer.name}</td>
						<td>${order.comments}</td>
						<td class="status"><img src="<c:url value='/images/${order.status}.png'/>">&nbsp;
							<c:choose>
								<c:when test="${order.status == 'PROCESSING'}">
									Processing
								</c:when>
								<c:when test="${order.status == 'RESOLVED'}">
									Resolved
								</c:when>
								<c:when test="${order.status == 'DISPUTED'}">
									Disputed
								</c:when>
								<c:when test="${order.status == 'WAITING'}">
									Waiting
								</c:when>
							</c:choose>
						</td>
						<td><input type="checkbox" name="ship" value="ship"></td>
					</tr>
				</c:if>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>