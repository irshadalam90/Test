<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

 <%-- <link rel="stylesheet"  href="<c:url value="css/bootstrap.min.css"/>"/>  --%>

<style>

.main{
	margin: 0px auto;
	width:600px; 
	height:500px;
	background-color: #f0f0f0;
}

</style>

<script>
var selDiv = "";
var selDiv2 = "";

document.addEventListener("DOMContentLoaded", init, false);

function init() {
    //document.querySelector('#files').addEventListener('change', handleFileSelect, false);
    document.querySelector('#filess').addEventListener('change', handleFileSelect, false);

    //selDiv = document.querySelector("#selectedFiles");
    selDiv2 = document.querySelector("#selectedFiles2");
}
    
function handleFileSelect(e) {
    
    if(!e.target.files) return;
    
    //selDiv.innerHTML = "";
    selDiv2.innerHTML = "";
    var files = e.target.files;
    for(var i=0; i<files.length; i++) {
        var f = files[i];
        
        //selDiv.innerHTML += f.name + "<br/>";
        selDiv2.innerHTML += f.name + "<br/>";

    }
    
}

</script>
</head>
<body style="background-color:#EBF4FA">
	<br><br>
	<%-- <div class="main">
	<br>
	<form action="sendemail" method="post" enctype="multipart/form-data">
	<div><center><input type="text" placeholder="To:  Select Excel Sheet of Email Id to click on choose file button below." name="subject" style="padding:10px; width:550px; border:none" readonly></center></div>
	<hr>
	<div><center><input type="text" placeholder="Subject" name="subject" style="padding:10px; width:550px; border:none"></center></div>
	<hr>
	<div><center><textarea rows="20" name="content" style="padding:10px; width:550px; border:none"></textarea></center></div>
	<hr>
	<div><button type="submit" style="padding:5px; width:70px; margin-left:20px; border:none; background-color:#FF00FF; color:white; float:left;"><b>Send</b></button>
	<!-- <input type="file" name="file" style="float:left;width:200px;margin-left:10px;"> -->
	<!-- <input type="file" name="files" multiple style="float:left;width:200px;"> -->
	<div style="float:left;width:200px;margin-left:10px;">
  	<label for="files" class="btn"><span class="glyphicon glyphicon-file" aria-hidden="true"></span> Select Excel sheet</label>
  	<input id="files" style="visibility:hidden;" type="file" name="file">
	</div>	
	<div style="float:left">
  	<label for="files" class="btn"><span class="glyphicon glyphicon-paperclip" aria-hidden="true"></span> Attach files</label>
  	<input id="files" style="visibility:hidden;" type="file" name="files" multiple>
	</div>	
	</form>
	
	
	</div> --%>
	
	<%-- <div> ${emailSend} </div> --%>
	
	<div class="fluid-cotainer" >
		<div class="row">
			<div></div>
			<div class="col-xs-3"></div>
			
			<div class="col-xs-6" style="background-color:#6BCFFA">
			 <div class="row">
				<form action="sendemail" method="post" enctype="multipart/form-data">
					<div style="margin-top:20px;"><center><input type="text" placeholder="To:  Select Excel Sheet of Email Id to click on excel sheet file below." style="padding:8px; width:600px; border:none" readonly></center></div>
					<hr>
					<div><center><input type="text" placeholder="Subject" name="subject" style="padding:8px; width:600px; border:none"></center></div>
					<hr>
					<div><center><textarea rows="15" name="content" style="padding:8px; width:600px; border:none"></textarea></center></div>
			        
			</div>
			<hr>
					
	        <div class="row">
	        <div class="col-xs-6"></div>
	        <div class="col-xs-6" id="selectedFiles2"></div> 
	        	<div class="col-xs-1"></div>
	        	<div class="col-xs-2"><button type="submit" style="padding:5px; width:70px; margin-left:20px; border:none; background-color:#FF00FF; color:white; float:left;"><b>Send</b></button></div>
					<!-- <input type="file" name="file" style="float:left;width:200px;margin-left:10px;">
					<input type="file" name="files" multiple style="float:left;width:200px;"> -->
					 <div class="col-xs-2" style="float:left;width:200px;margin-left:10px;">
  						<label for="files" class="btn"><span class="glyphicon glyphicon-file" aria-hidden="true"></span> Select Excel sheet</label>
  						<input id="files" style="visibility:hidden;" type="file" name="file">
  						<!-- <div id="selectedFiles"></div> -->
					</div>	
					<div class="col-xs-2">
  						 <!-- <label for="filess" class="btn"><span class="glyphicon glyphicon-paperclip" aria-hidden="true"></span> Attach files</label>
  						<input id="filess" style="visibility:hidden;" type="file" name="files" multiple> -->
  						<input type="file" name="files" multiple>
					</div>	 
					<div class="col-xs-5"></div>
					 
				</form>
			</div>
			</div> 
			
			 <div class="col-xs-3">
			 
				<div class="col-xs-1"></div>
				<div class="col-xs-11" style="background-color:grey; color:white;height:586px;">
		<table class="table table-hover">
			<c:if test="${empty emailCount}">
			<tr>
   			 <th>Total number of email sent (0)</th>
			</tr>
			</c:if>
			
			<c:if test="${not empty emailCount}">
			<tr> <th>Total number of email sent (${emailCount})</th></tr>
			 <c:forEach var = "email" items="${emailSend}">
			
         	<tr>
				<td>${email}</td>
			</tr>
			 </c:forEach>
			 </c:if>
		</table>
		
	</div>
			</div> 
			
		
		</div>
	</div>
	
</body>
</html>