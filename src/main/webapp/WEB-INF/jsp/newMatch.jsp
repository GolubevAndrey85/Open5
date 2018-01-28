<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>New Match</title>
</head>

<body>
<div class="container">
<form action="/saveMatch" method="post">
<div>
    <h4 class="form-signin-heading">Data and time of the Match</h4>
    <input class="form-control" id=MyDate name="date" type=text readonly>
    <script>
        var dt=new Date();
        var month = dt.getMonth()+1;
        if (month<10) month='0'+month;
        var day = dt.getDate();
        if (day<10) day='0'+day;
        var year = dt.getFullYear();
        var hour = dt.getHours();
        if (hour<10) hour='0'+hour;
        var minute = dt.getMinutes();
        if (minute<10) minute='0'+minute;
        var sec = dt.getSeconds();
        if (sec<10) sec='0'+sec;
        MyDate.value=year+'-'+month+'-'+day+' '+hour+':'+minute+':'+sec;
    </script>

</div>
    <p><b>${message}</b></p>
    <p><b>TEAM 1:</b></p>
    <p><textarea class="form-control" name="team1" placeholder="name1; name2; ...">${team1Attribute}</textarea></p>

    <p><b>TEAM 2:</b></p>
    <p><textarea class="form-control" name="team2" placeholder="name1; name2; ...">${team2Attribute}</textarea></p>

    <p><b>TEAM 1 Score:</b></p>
    <p><textarea class="form-control" name="team1score">${team1scoreAttribute}</textarea></p>

    <p><b>TEAM 2 Score:</b></p>
    <p><textarea class="form-control" name="team2score">${team2scoreAttribute}</textarea></p>

    <p><b>Match's details:</b></p>
    <p><textarea class="form-control" name="matchDetails" placeholder="It was amazing match!">${matchDetails}</textarea></p>
    <p><input class="btn btn-lg btn-primary btn-block" type="submit" value="Save the Match"></p>
</form>
</div>
</body>
</html>
