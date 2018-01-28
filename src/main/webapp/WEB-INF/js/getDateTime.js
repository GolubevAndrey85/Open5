function getDataTime() {
    var data= '';
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
    data = year+'-'+month+'-'+day+' '+hour+':'+minute+':'+sec;
    return data;
}

function checkUserNames() {
    var team1 = document.getElementById("team1ID").value.split(";");//.replace(/^\s*!/,'').replace(/\s*$/,'');
    var team2 = document.getElementById("team2ID").value.split(";");
    var namesEquality = false;

    for (var i = 0; i < team1.length-1; i++) {
        for (var j = 0; j < team2.length-1; j++) {
            if ( (team1[i].replace(/^\s*!/, '').replace(/\s*$/, '').localeCompare(team2[j].replace(/^\s*/, '').replace(/\s*$/, ''))) === 0 ) {
                namesEquality = true;
            }
        }
    }
    if (namesEquality) {
        document.getElementById("warning").style.display = 'block';
    } else document.getElementById("warning").style.display = 'none';
    return !namesEquality;
}