function getDataTime() {
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
MyDate.value=year+'-'+month+'-'+day+' '+hour+':'+minute+':'+sec;}
