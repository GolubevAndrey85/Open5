<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Index</title>
</head>
<body>
<div class="container">
    <form name='newMatch' action="/newMatch" method='POST'>
        <h2 class="form-signin-heading">Please, choose the action</h2><br>
        <input class="btn btn-lg btn-primary btn-block" name="createNewMatch" type="submit"
               value="create new match"/>
    </form>
    <form class="form-signin" name='loginForm' action="/login" method='POST'>
        <input class="form-control" type='text' name='username' placeholder="Player's name">
        <input class="btn btn-lg btn-primary btn-block" name="showPlayerInfo" type="submit"
               value="show player info"/>
    </form>

</div>
<!--p><img src="../img/ico.gif" width="581" height="117"></p-->
</body>
</html>
