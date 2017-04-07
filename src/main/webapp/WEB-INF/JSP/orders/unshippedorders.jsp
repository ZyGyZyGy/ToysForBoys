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
	<c:if test="${not empty mislukteOrders}">
		<div class="foutmelding">
			Shipping failed for order(s) 
			<c:forEach items="${mislukteOrders}" var="mislukteOrder" varStatus="status">
				${mislukteOrder.id}
				<c:if test="${not status.last}">
					,&nbsp;
				</c:if>
			</c:forEach>
			<ul>
				<c:if test="${not empty fouten.reden1}">
					<li>
						${fouten.reden1}
					</li>
				</c:if>
				<c:if test="${not empty fouten.reden2}">
					<li>
						${fouten.reden2}
					</li>
				</c:if>
			</ul>
		</div>
	</c:if>
	<form method="post" id="shipform">
		<table>
			<thead>
				<tr>
					<th>ID</th><th>Ordered</th><th>Required</th><th>Customer</th><th>Comments</th><th>Status</th><th>Ship</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${orders}" var="order">
					<c:url value="/orders/detail.htm" var="detailURL">
						<c:param name="id" value="${order.id}" />
					</c:url>
					<tr>
						<td><a href="${detailURL}">${order.id}</a></td>
						<td><fmt:formatDate value="${order.orderDate}" type="date"
								dateStyle="short" /></td>
						<td><fmt:formatDate value="${order.requiredDate}" type="date"
								dateStyle="short" /></td>
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
						<td><input type="checkbox" name="ship" id="ship" value="${order.id}"></td>
					</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="5">
						<div class="pijltjes">
							<c:if test="${vanafRij != 0}">
								<c:url value="" var="vorigePaginaURL">
									<c:param name="vanafRij" value="${vanafRij - aantalRijen}" />
								</c:url>
							</c:if>
							<a href="<c:out value='${vorigePaginaURL}'/>"
								title="vorige pagina" class="pagineren">&larr;</a>

							<c:if test="${empty laatstePagina}">
								<c:url value="" var="volgendePaginaURL">
									<c:param name="vanafRij" value="${vanafRij + aantalRijen}" />
								</c:url>
								<a href="<c:out value='${volgendePaginaURL}'/>"
									title="volgende pagina" class="pagineren">&rarr;</a>
							</c:if>
						</div>
					</td>
					<td colspan="2" align="right"><input type="submit" value="Set as shipped" id="shipknop"></td>
				</tr>
			</tfoot>
		</table>				
	</form>
	<script>
		document.getElementById('shipform').onsubmit = function() {
			document.getElementById('shipknop').disabled = true;
		};
	</script>
</body>
</html>











