<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Email Pannel</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="application/x-javascript"> 
addEventListener("load", function() {
	setTimeout(hideURLbar, 0);
	},
	false
	);
function hideURLbar(){
	window.scrollTo(0,1);
	}
</script>
<!-- bootstrap-css -->
<link rel="stylesheet" href="css/bootstrap.min.css" >
<!-- //bootstrap-css -->
<!-- Custom CSS -->
<link href="css/style.css" rel='stylesheet' type='text/css' />
<link href="css/style-responsive.css" rel="stylesheet"/>
<!-- font CSS -->
<!-- font-awesome icons -->
<link rel="stylesheet" href="css/font.css" type="text/css"/>
<link href="css/font-awesome.css" rel="stylesheet"> 
<link rel="stylesheet" href="css/morris.css" type="text/css"/>
<!-- calendar -->
<link rel="stylesheet" href="css/monthly.css">
<!-- //calendar -->
<!-- //font-awesome icons -->
<script src="js/jquery2.0.3.min.js"></script>
<script src="js/raphael-min.js"></script>
<script src="js/morris.js"></script>

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
<body>
<section id="container">
<!--header start-->
<header class="header fixed-top clearfix">
<!--logo start-->
<div class="brand">
    <a href="emailpannel" class="logo">
        <img alt="" src="images/logo3.png">
    </a>
    <div class="sidebar-toggle-box">
        <div class="fa fa-bars"></div>
    </div>
</div>
<!--logo end-->

<div class="top-nav clearfix">
    <!--search & user info start-->
    <ul class="nav pull-right top-menu">
       <!--  <li>
            <input type="text" class="form-control search" placeholder=" Search">
        </li> -->
        <!-- user login dropdown start-->
        <li class="dropdown">
            <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                <span class="username">Admin</span>
                <b class="caret"></b>
            </a>
            <ul class="dropdown-menu extended logout">
                <!-- <li><a href="#"><i class=" fa fa-suitcase"></i>Profile</a></li>
                <li><a href="#"><i class="fa fa-cog"></i> Settings</a></li> -->
                <li><a href="logOut" ><i class="fa fa-key"></i> Log Out</a></li>
            </ul>
        </li>
        <!-- user login dropdown end -->
       
    </ul>
    <!--search & user info end-->
</div>

</header>
<!--header end-->
<!--sidebar start-->
<aside>
    <div id="sidebar" class="nav-collapse">
        <!-- sidebar menu start-->
        <div class="leftside-navigation">
            <ul class="sidebar-menu" id="nav-accordion">
               <!--  <li>
                    <a class="active" href="index.html">
                        <i class="fa fa-dashboard"></i>
                        <span>Home</span>
                    </a>
                </li> -->
                
                <li class="sub-menu">
                    <a href="javascript:;">
                        <i class="fa fa-book"></i>
                        <span>Email</span>
                    </a>
                     <ul class="sub">
						<li><a href="emailpannel">Send Email</a></li>
						<li><a href="viewEmail">View Email</a></li>
                    </ul>
                </li>
                 <li class="sub-menu">
                    <a href="javascript:;">
                        <i class="fa fa-book"></i>
                        <span>SMS</span>
                    </a>
                     <ul class="sub">
						<li><a href="smspage">Send SMS</a></li>
						
                    </ul>
                </li> 
              </ul>         
         </div>
        <!-- sidebar menu end-->
    </div>
</aside>
<!--sidebar end-->
<!--main content start-->
<section id="main-content">
	<section class="wrapper">
<c:if test="${totalRow >= 100}">  
 <input type="text" value="${totalRow}"></input>
<div class="container">
    <div class="row">
    <input type="text" value="${totalRow}"></input>
    	<div class="col-md-2"></div>
    	<div class="col-md-8">
    		<h2 style=" text-transform: capitalize;font-family: QuirogaSerifPro;font-weight: 600;color:#23133a;"><center>100 LIMT ONLY</center></h2>
	<center><p style="font-family:montserrat;font-weight:400;font-size:17px;line-height:24px;color:#23133a;">
	limit
	</p></center>	
    	</div>
    	<div class="col-md-2"></div>
    </div>
    
</div>

 </c:if>
 <c:if test="${totalRow < 101}"> 
 
		<div class="row">
		
			<div class="panel-body">
			<div class="col-md-2 w3ls-graph"></div>
				<div class="col-md-6 w3ls-graph" style="background-color:#6BCFFA">
					<form action="sendemail" method="post" enctype="multipart/form-data">
					<div class="row"style="padding-left: 10px;padding-right: 10px;">
					
					To<br><label for="files" class="btn"><span class="glyphicon glyphicon-file" aria-hidden="true"></span><font color="red"><b>Please Select Excel Sheet</b></font></label>
  						<input  id="files" style="visibility:hidden;" type="file" name="file" required>
  					</div>	
					
					<hr>
					<div class="row" style="padding-left: 10px;padding-right: 10px;">
				      <span>Subject <input type="text" name="subject"class="form-control" style="width: 100%;"></span>
					</div>
					<hr>
					<div class="row" style="padding-left: 10px;padding-right: 10px;">
					<span>Message<input name="content"class="form-control" style="width: 100%;resize:none;"></input></span>
					
					</div>
					<hr>
					<div class="row" style="padding-left: 10px;padding-right: 10px;">
				      <span>User Id<input type="text" name="userId"class="form-control" style="width: 100%;"></span>
					</div>
					<hr>
					<div class="row" style="padding-left: 10px;padding-right: 10px;">
					<span>Password <input name="userPassword"class="form-control" style="width: 100%;resize:none;"></input></span>
					
					</div>
					<hr>
					<div class="row" style="padding-left: 10px;padding-right: 10px;padding-bottom:10px;">
					<div class="col-md-8">
					<input type="file" name="files" multiple>
					</div>
					<div class="col-md-4">
					<input type="submit" value="Send" class="btn btn-primary" style="padding:10px 20px 10px 20px;" >
					</div>
					</div>
					
					</form>
			</div>
		
			<div class="col-md-1 w3ls-graph"></div>
               <div class="col-md-3" style="background-color:#164354; color:white;">
			  
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
				<!-- <div class="col-md-3 w3ls-graph"></div> -->
				
				
			</div>
			
			
		</div>
		</c:if> 
</section>
 <!-- footer -->
		  <div class="footer">
			<div class="wthree-copyright">
			  <p>Design by <a href="https://gntechno.com/">GN TECHNO</a></p>
			</div>
		  </div>
  <!-- / footer -->
</section>
<!--main content end-->
</section>
<script src="js/bootstrap.js"></script>
<script src="js/jquery.dcjqaccordion.2.7.js"></script>
<script src="js/scripts.js"></script>
<script src="js/jquery.slimscroll.js"></script>
<script src="js/jquery.nicescroll.js"></script>
<!--[if lte IE 8]><script language="javascript" type="text/javascript" src="js/flot-chart/excanvas.min.js"></script><![endif]-->
<script src="js/jquery.scrollTo.js"></script>
<!-- morris JavaScript -->	

<!-- calendar -->
	
	<!-- //calendar -->
</body>
</html>
