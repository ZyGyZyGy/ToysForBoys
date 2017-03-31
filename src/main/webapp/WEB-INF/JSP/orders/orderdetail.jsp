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
	<h2>${order.id}</h2>
	<dl>
		<dt>Ordered:</dt>
		<dd>${order.orderDate}</dd>
		<dt>Required:</dt>
		<dd>${order.requiredDate}</dd>
		<dt>Customer</dt>
		<dd>${order.name}<br>
			${order.adres.street}<br>
			${order.adres.postalCode} ${order.adres.city} ${order.adres.state}<br>
		</dd>
	</dl>
	
</body>
</html>