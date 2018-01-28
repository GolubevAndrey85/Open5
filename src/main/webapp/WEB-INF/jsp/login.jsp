<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 26/01/2018
  Time: 12:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<script src="https://cdn.plot.ly/plotly-latest.min.js"></script>

<html>
<head>
    <title>Login</title>
</head>
<body>
<h1>${someAttribute}</h1>
<div>HI!</div>



<div id="myDiv"><!-- Plotly chart will be drawn inside this DIV --></div>
<script>
    <c:set var="message" value="Hello"/>
    var message = '<c:out value="${someAttribute3}"/>';
    <c:set var="message" value="Hello"/>
    var message2 = '<c:out value="${someAttribute2}"/>';
    var b = message2.split(';').map(Number);
    var c = message.split(';').map(String);
    //alert(message2.type);
    //alert(message2);
    //alert(message);

    var data = [
        {
            //x: ['2013-10-04 22:23:00', '2013-11-04 22:23:00', '2013-12-04 22:23:00'],
            x: c,
            y: b,
            //y: [1, 3, 6],
            type: 'scatter'
        }
    ];

    Plotly.newPlot('myDiv', data);
</script>


</body>
</html>
