<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<div class="row">
		
			<div class="panel-body">
			<div class="col-md-2 w3ls-graph"></div>
				<div class="col-md-6 w3ls-graph" style="background-color:#6BCFFA">
					<form action="userSendMail" method="post" enctype="multipart/form-data">
					<div class="row"style="padding-left: 10px;padding-right: 10px;">
					
					To<br><label for="files" class="btn"><span class="glyphicon glyphicon-file" aria-hidden="true"></span><font color="red"><b>Please Select Excel Sheet</b></font></label>
  						<input  id="files" style="visibility:hidden;" type="file" name="file" required>
  					</div>	
					
					<hr>
					<div style="padding-left: 10px;padding-right: 10px;">
				      <span>User Email <input type="text" name="UserMail"class="form-control" style="width: 100%;"></span>
					</div>
					<hr>
					<div class="row" style="padding-left: 10px;padding-right: 10px;">
					 <span>User Password <input type="text" name="userPassWord"class="form-control" style="width: 100%;"></span>
					
					</div>
					<hr>
					<div class="row" style="padding-left: 10px;padding-right: 10px;padding-bottom:10px;">
					<div class="col-md-4"></div>
					<div class="col-md-4">
					<input type="submit" value="Send" class="btn btn-primary" style="padding:10px 20px 10px 20px;" >
					</div>
					</div>
					
					</form>
			</div>
			
			
				</div>
			</div>

</body>
</html>