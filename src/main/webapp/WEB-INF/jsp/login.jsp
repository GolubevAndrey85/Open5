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
<h2 class="form-signin-heading">${someAttribute}</h2><br>
    <h4 id="winsNum" class="text-success">Your total number of WINS is: ${someAttribute4}</h4>
    <h4 id="loosNum" class="text-danger">Your total number of LOOSES is: ${someAttribute5}</h4>
<div id="myDiv"></div>
<script>
    <c:set var="message" value="Hello"/>
    var message = '<c:out value="${someAttribute3}"/>';
    <c:set var="message" value="Hello"/>
    var message2 = '<c:out value="${someAttribute2}"/>';
    var b = message2.split(';').map(Number);
    var c = message.split(';').map(String);
    if (message.length === 0) {document.getElementById("myDiv").style.display = "none";
        document.getElementById("winsNum").style.display = "none";
        document.getElementById("loosNum").style.display = "none";}

    var data = [
        {   x: c,
            y: b,
            type: 'scatter'
        }
    ];
    Plotly.newPlot('myDiv', data);
</script>
    <button class="btn btn-lg btn-primary btn-block" onclick='location.href="/"'>Back home</button>
</div>
</body>
</html>
