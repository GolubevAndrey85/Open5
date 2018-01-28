<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<script src="https://cdn.plot.ly/plotly-latest.min.js"></script>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Info</title>
</head>
<body>

<div class="container">
<h2 class="form-signin-heading">${someAttribute}</h2>
<div id="myDiv"></div>
<script>
    <c:set var="message" value="Hello"/>
    var message = '<c:out value="${someAttribute3}"/>';
    <c:set var="message" value="Hello"/>
    var message2 = '<c:out value="${someAttribute2}"/>';
    var b = message2.split(';').map(Number);
    var c = message.split(';').map(String);
    if (message.length === 0) {document.getElementById("myDiv").style.display = "none"}

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
    <button class="btn btn-lg btn-primary btn-block" onclick='location.href="/"'>Back to home</button>
</div>

</body>
</html>
