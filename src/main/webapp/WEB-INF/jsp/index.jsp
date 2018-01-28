<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Index</title>
</head>
<body>

<div class="container">
<div>HI!</div>


<form name='loginForm'
      action="/login" method='POST'>
    <table>
        <tr>
            <td>User:</td>
            <td><input type='text' name='username'></td>
        </tr>
            <td colspan='2'><input class="btn btn-lg btn-primary btn-block" name="showPlayerInfo" type="submit"
                                   value="show player info" /></td>

    </table>

</form>

<form name='newMatch' action="/newMatch" method='POST'>
    <input class="btn btn-lg btn-primary btn-block" name="createNewMatch" type="submit"
           value="create new match" />
</form>
</div>
</body>
</html>
