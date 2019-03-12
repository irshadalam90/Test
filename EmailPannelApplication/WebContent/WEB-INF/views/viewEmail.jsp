<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
 
<!DOCTYPE html>
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
<link rel="stylesheet" href="css/jquery.dataTables.min.css">
<link rel="stylesheet" href="css/jquery.dataTables.css">
<link rel="stylesheet" href="css/bootstrap.min.css" >
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
                <!-- <li>
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
              </ul>         
         </div>
        <!-- sidebar menu end-->
    </div>
</aside>
<!--sidebar end-->
<!--main content start-->
<section id="main-content">
	<section class="wrapper">
		<div class="row">
		
			<div class="panel-body">
			
				<div class=" w3ls-graph">
					
		  <%--  <table class="table table-hover">
			
			   <c:forEach var = "email" items="${viewEmail}">
			<tr>
				<td>${email}</td>
			</tr>
			<tr>
				<td>${date}</td>
			</tr>
			 </c:forEach>
			 
		</table border="solid 1px;" > --%>
		
		<table id="example" class="table table-striped table-bordered" cellspacing="0" width="100%">
		
		<%int sno = 0; %>
										<thead>
											<tr>
												<!-- <th>
													Category Id
												</th> -->
												<th style="padding:5px">
													S.NO
												</th>
												<th style="padding:5px">
													Mail From
												</th>
												<th style="padding:5px">
													Send To 
												</th>
												<th style="padding:5px">
													Date And Time
												</th>
											</tr>
									     </thead>
										 <tbody>
							              <c:forEach var="view"  items="${viewEmail}">
										   <tr>
										             <% sno = sno+1;%> 
 		                                           <td style="padding:5px"><%=sno%></td> 
										             <td style="padding:5px">
													 ${view.mailFrom} 
													</td>
										   	        <td style="padding:5px">
													 ${view.email} 
													</td>
													<td style="padding:5px">
													 ${view.date} 
													</td>
													</tr>
												</c:forEach>
										</tbody>
								</table>
		
		</div> 
				
				</div>
				<!-- <div class="col-md-3 w3ls-graph"></div> -->
				
				
			</div>
			
		</div>
		
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
<script>  
$(document).ready(function() {
    $('#example').DataTable();
} );
</script>

<script src="js/js.js"></script>
  <script src="js/jquery.dataTables.min.js"></script>
  <script src="js/jquery-1.12.4.js.js"></script>
  
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
