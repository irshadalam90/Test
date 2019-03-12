<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="sendsms" method="post">
<!-- <p>App Key: <input type="text"></p> -->
<p>Mobile No.: <input type="text" name="number"></p>
<p>SMS: <textarea rows="3" name="sms"></textarea></p>
<!-- <p>Sender: <input type="text"></p> -->

<p><input type="submit" value="send"></p>
</form>
</body>
</html>