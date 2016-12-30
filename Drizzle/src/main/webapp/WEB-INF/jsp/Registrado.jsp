<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="com.drizzle.model.Like"%>
<%@page import="java.text.ParseException"%>
<%@page import="java.util.Date"%>
<%@page import="com.drizzle.model.Publication"%>
<%@page import="com.drizzle.persistence.mongoTransations"%>
<%@page import="org.apache.soap.encoding.soapenc.Base64"%>
<%@page import="com.drizzle.model.Profile"%>
<%@page import="java.util.List"%>
<%@page import="java.util.*"%>
<%@page import="java.util.Objects"%>
<%@page import="com.drizzle.model.Account"%>
<%@page import="com.drizzle.persistence.hibernateTransations"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%List<Publication> lista;
      List<Like> list;
    lista = mongoTransations.ConsultarPublicationes(request.getParameter("comuna"));
    list= mongoTransations.ConsultarLikes(Integer.parseInt(session.getAttribute("usuario").toString()));
	boolean Dta=true;
    
    %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Bienvenido</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!-- Bootstrap 3.3.6 -->
  <link rel="stylesheet" href="././resources/css/bootstrap.min.css">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="././resources/css/AdminLTE.min.css">
  <!-- AdminLTE Skins. Choose a skin from the css/skins
       folder instead of downloading all of them to reduce the load. -->
  <link rel="stylesheet" href="././resources/css/_all-skins.min.css">
  <!-- iCheck -->
  <link rel="stylesheet" href="././resources/css/blue.css">
  <!-- Morris chart -->
  <link rel="stylesheet" href="././resources/css/morris.css">
  <!-- jvectormap -->
  <link rel="stylesheet" href="././resources/css/jquery-jvectormap-1.2.2.css">
  <!-- Date Picker -->
  <link rel="stylesheet" href="././resources/css/datepicker3.css">
  <!-- Daterange picker -->
  <link rel="stylesheet" href="././resources/css/daterangepicker.css">
  <!-- bootstrap wysihtml5 - text editor -->
  <link href="././resources/css/styleinput.css" rel="stylesheet">
  <link rel="stylesheet" href="././resources/css/bootstrap3-wysihtml5.min.css">
  <link href='https://fonts.googleapis.com/css?family=Source+Sans+Pro' rel='stylesheet' type='text/css'>
  <link href="././resources/css/style.css" rel="stylesheet">
   <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/1.0.2/Chart.min.js"></script>
   <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCPYXQMu5fcr5_SiKfxyjO7auJdUl4lOTM &callback=initMap"></script>
   <script src="././resources/js/validaciones.js"></script>
   
  <!-- Stylo del alert Eliminar// -->
   
	<script src="././resources/jquery/jquery-2.2.3.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.3/jquery.min.js"></script>
  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  
  <!-- SweetAlert Alertas -->
	<script  src="././resources/dist12/sweetalert-dev.js"></script>
    <link rel="stylesheet" href="././resources/dist12/sweetalert.css" /> 
    
    <script src="././resources/sources/jscharts.js"></script>  
    
    <script src="././resources/js/estadisticas.js"></script> 
    
     <!-- Jquery-confirm ventana notificacion eliminar// -->
    <link rel="stylesheet" type="text/css" href="././resources/css/jquery-confirm.css"/>
    <script type="text/javascript" src="././resources/js/jquery-confirm.js"></script>
  
  
  <![endif]-->
</head>
<body class="hold-transition skin-blue sidebar-mini" onload="ConsultaEstadiscticas('0')">

<!-- Trigger the modal with a button -->
	<button id="BtnModal" type="button" class="btn btn-info btn-lg" data-toggle="modal"
		data-target="#myModal">Publicar</button>
		
	<!-- Modal -->
	<div id="myModal" name="myModal" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button id="BtnModal" type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title" id="tituloh4">
						Publicacion del Clima 
						<br>
						
						<!--<%-->
						/*SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
						Date fecha = new Date();
						out.print(dateFormat.format(fecha));*/
						<--%>-->
					</h4>
				</div>
				
				<form id="formPublication" name="formPublication" method="post" enctype="multipart/form-data" >
				<div class="modal-body">
						<input id="Id_" Name="Id_" type="hidden" />
					
						<!--<input type="radio" name="clima1" id="clima1" value="1" onClick="cargar(this.value)" > Lluvia 
						<input type="radio" name="clima2" id="clima2" value="2" onClick="cargar(this.value)" > Soleado
						<input type="radio" name="clima3" id="clima3" value="3" onClick="cargar(this.value)" > Normal-->
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						
						
					    <div align="center" class="cc-selector">
					        <input id="rain" type="radio" name="weather" value="rain" />
					        <label class="drinkcard-cc rain" for="rain"></label>
					        
					        <input id="tempered" type="radio" name="weather"value="tempered" />
					        <label class="drinkcard-cc tempered" for="tempered"></label>
					        
					        <input id="storm" type="radio" name="weather"value="storm" />
					        <label class="drinkcard-cc storm" for="storm"></label>
					        
					        <input id="sunny" type="radio" name="weather"value="sunny" />
					        <label class="drinkcard-cc sunny" for="sunny"></label>
					    </div>
					    
						<BR><BR>
						
							  <div class="form-group">
						      	<!-- <input type="file" id="files" name="files" /> -->
								<input id="files" accept="image/*" name="files" capture="" type="file">
						      </div>
						     <i class="fa fa-location-arrow"   aria-hidden="true" ></i>
						      <h5 align="left" id="Barrio"> </h5>
						      
						      
						      <br/>
						      
						      <output height="100px" width = "150px" name="list" id="list"></output>
						      
							  <textarea name="textareaPublicacion" id="textareaPublicacion" style="overflow:auto;resize:none;min-width: 100%" placeholder="Describe tu publicación..." rows="4" cols="80" maxlength = "160"></textarea>
							  
						<BR><BR>
					
				</div>
				
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button id="BtnPub" name="BtnPub" type="button" class="btn btn-primary">Save</button>
					
				</div>

				</form>
			</div>

		</div>
	</div>
	
	
	<!-- Modal Comments cambios-->
    <div class="modal fade" id="modalComment" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                
                	<div class="modal-header" id="myModalLabelTitle">
                    
	                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
	                    
	                    <h3 class="modal-title" >Ubicacion </h3>
                    
                    </div>
                    
                    
                    <div class="modal-header" id="myModalLabelPublication">
	                    
                    
                    </div>
                    
                    <div class="modal-header" id="myModalLabelComments"  style="overflow: scroll; align:center; height:200px;width:600px; overflow-x:hidden;">
	                  
	                  
                    
                    </div>
                    
                    <div class="modal-body">
                        <textarea id="textarearDescription" name="textarearDescription" style="overflow:auto;resize:none;min-width: 100%" maxlength = "100" class="form-control col-xs-12"></textarea>
                    </div>
                    
                    
                    <div class="modal-footer">
	                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	                    <button id="BtnComment" name="BtnComment" type="button" class="btn btn-danger">Save</button>
                	</div>
            </div>
        </div>
    </div> 
	


<div class="wrapper">

  <header class="main-header">
    <!-- Logo -->
    <a href="index2.html" class="logo">
      <!-- mini logo for sidebar mini 50x50 pixels -->
      <span class="logo-mini"><b>D</b>Web</span>
      <!-- logo for regular state and mobile devices -->
      <span class="logo-lg"><b>DrizzleWeb</b></span>
    </a>
    <!-- Header Navbar: style can be found in header.less -->
    <nav class="navbar navbar-static-top">
      <!-- Sidebar toggle button-->
      <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
        <span class="sr-only">Toggle navigation</span>
      </a>

      <div class="navbar-custom-menu">
        <ul class="nav navbar-nav">
        
        		        <li >
							<form id="formComuna" name="formComuna">
								<select id="comuna" name="comuna" class="form-control" onchange="Filtro(this.value)">
									<option value="0">TODAS</option>
									<option value="1">COMUNA 1</option>
									<option value="2">COMUNA 2</option>
									<option value="3">COMUNA 3</option>
									<option value="4">COMUNA 4</option>
									<option value="5">COMUNA 5</option>
									<option value="6">COMUNA 6</option>
									<option value="7">COMUNA 7</option>
									<option value="8">COMUNA 8</option>
									<option value="9">COMUNA 9</option>
									<option value="10">COMUNA 10</option>
									<option value="11">COMUNA 11</option>
									<option value="12">COMUNA 12</option>
									<option value="13">COMUNA 13</option>
									<option value="14">COMUNA 14</option>
									<option value="15">COMUNA 15</option>
									<option value="16">COMUNA 16</option>
									<option value="17">COMUNA 17</option>
									<option value="18">COMUNA 18</option>
									<option value="19">COMUNA 19</option>
									<option value="20">COMUNA 20</option>
									<option value="21">COMUNA 21</option>
									<option value="22">COMUNA 22</option>
									
									
								</select>
							</form>
						</li>
        
        
          <!-- Messages: style can be found in dropdown.less-->
          <li class="dropdown messages-menu">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <i class="fa fa-envelope-o"></i>
              <span class="label label-success">4</span>
            </a>
            <ul class="dropdown-menu">
              <li class="header">You have 4 messages</li>
              <li>
                <!-- inner menu: contains the actual data -->
                <ul class="menu">
                  <li><!-- start message -->
                    <a href="#">
                      <div class="pull-left">
                        <img  id="imagen" src="data:image/jpg;base64,<% out.print(session.getAttribute("photo")); %>" class="img-circle" alt="User Image">
                      </div>
                      <h4>
                        Support Team
                        <small><i class="fa fa-clock-o"></i> 5 mins</small>
                      </h4>
                      <p>Why not buy a new awesome theme?</p>
                    </a>
                  </li>
                  <!-- end message -->
                  <li>
                    <a href="#">
                      <div class="pull-left">
                        <img src="././resources/img/perfil/avatar.png" class="img-circle" alt="User Image">
                      </div>
                      <h4>
                        AdminLTE Design Team
                        <small><i class="fa fa-clock-o"></i> 2 hours</small>
                      </h4>
                      <p>Why not buy a new awesome theme?</p>
                    </a>
                  </li>
                  <li>
                    <a href="#">
                      <div class="pull-left">
                        <img src="././resources/img/perfil/avatar.png" class="img-circle" alt="User Image">
                      </div>
                      <h4>
                        Developers
                        <small><i class="fa fa-clock-o"></i> Today</small>
                      </h4>
                      <p>Why not buy a new awesome theme?</p>
                    </a>
                  </li>
                  <li>
                    <a href="#">
                      <div class="pull-left">
                        <img src="././resources/img/perfil/avatar.png" class="img-circle" alt="User Image">
                      </div>
                      <h4>
                        Sales Department
                        <small><i class="fa fa-clock-o"></i> Yesterday</small>
                      </h4>
                      <p>Why not buy a new awesome theme?</p>
                    </a>
                  </li>
                  <li>
                    <a href="#">
                      <div class="pull-left">
                        <img src="././resources/img/perfil/avatar.png" class="img-circle" alt="User Image">
                      </div>
                      <h4>
                        Reviewers
                        <small><i class="fa fa-clock-o"></i> 2 days</small>
                      </h4>
                      <p>Why not buy a new awesome theme?</p>
                    </a>
                  </li>
                </ul>
              </li>
              <li class="footer"><a href="#">See All Messages</a></li>
            </ul>
          </li>
          <!-- Notifications: style can be found in dropdown.less -->
          <li class="dropdown notifications-menu">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <i class="fa fa-bell-o"></i>
              <span class="label label-warning">10</span>
            </a>
            <ul class="dropdown-menu">
              <li class="header">You have 10 notifications</li>
              <li>
                <!-- inner menu: contains the actual data -->
                <ul class="menu">
                  <li>
                    <a href="#">
                      <i class="fa fa-users text-aqua"></i> 5 new members joined today
                    </a>
                  </li>
                  <li>
                    <a href="#">
                      <i class="fa fa-warning text-yellow"></i> Very long description here that may not fit into the
                      page and may cause design problems
                    </a>
                  </li>
                  <li>
                    <a href="#">
                      <i class="fa fa-users text-red"></i> 5 new members joined
                    </a>
                  </li>
                  <li>
                    <a href="#">
                      <i class="fa fa-shopping-cart text-green"></i> 25 sales made
                    </a>
                  </li>
                  <li>
                    <a href="#">
                      <i class="fa fa-user text-red"></i> You changed your username
                    </a>
                  </li>
                </ul>
              </li>
              <li class="footer"><a href="#">View all</a></li>
            </ul>
          </li>
          <!-- Tasks: style can be found in dropdown.less -->
          <li class="dropdown tasks-menu">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <i class="fa fa-flag-o"></i>
              <span class="label label-danger">9</span>
            </a>
            <ul class="dropdown-menu">
              <li class="header">You have 9 tasks</li>
              <li>
                <!-- inner menu: contains the actual data -->
                <ul class="menu">
                  <li><!-- Task item -->
                    <a href="#">
                      <h3>
                        Design some buttons
                        <small class="pull-right">20%</small>
                      </h3>
                      <div class="progress xs">
                        <div class="progress-bar progress-bar-aqua" style="width: 20%" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
                          <span class="sr-only">20% Complete</span>
                        </div>
                      </div>
                    </a>
                  </li>
                  <!-- end task item -->
                  <li><!-- Task item -->
                    <a href="#">
                      <h3>
                        Create a nice theme
                        <small class="pull-right">40%</small>
                      </h3>
                      <div class="progress xs">
                        <div class="progress-bar progress-bar-green" style="width: 40%" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
                          <span class="sr-only">40% Complete</span>
                        </div>
                      </div>
                    </a>
                  </li>
                  <!-- end task item -->
                  <li><!-- Task item -->
                    <a href="#">
                      <h3>
                        Some task I need to do
                        <small class="pull-right">60%</small>
                      </h3>
                      <div class="progress xs">
                        <div class="progress-bar progress-bar-red" style="width: 60%" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
                          <span class="sr-only">60% Complete</span>
                        </div>
                      </div>
                    </a>
                  </li>
                  <!-- end task item -->
                  <li><!-- Task item -->
                    <a href="#">
                      <h3>
                        Make beautiful transitions
                        <small class="pull-right">80%</small>
                      </h3>
                      <div class="progress xs">
                        <div class="progress-bar progress-bar-yellow" style="width: 80%" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
                          <span class="sr-only">80% Complete</span>
                        </div>
                      </div>
                    </a>
                  </li>
                  <!-- end task item -->
                </ul>
              </li>
              <li class="footer">
                <a href="#">View all tasks</a>
              </li>
            </ul>
          </li>
          <!-- User Account: style can be found in dropdown.less -->
          <li class="dropdown user user-menu">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <img src="data:image/jpg;base64,<% out.print(session.getAttribute("photo")); %>" class="user-image" alt="User Image" id="image0">
              <span class="hidden-xs">
				<% out.println(session.getAttribute("nombres")); %>
			</span>
            </a>
            <ul class="dropdown-menu">
              <!-- User image -->
              <li class="user-header">
                <img src="data:image/jpg;base64,<% out.print(session.getAttribute("photo")); %>" class="img-circle" alt="User Image" id="image1">

                <p>
                  <% out.println(session.getAttribute("nombres")); %>
                  <small>Member since Nov. 2012</small>
                </p>
              </li>
              <!-- Menu Body -->
              <li class="user-body">
                <div class="row">
                  <div class="col-xs-4 text-center">
                    <a href="#">Followers</a>
                  </div>
                  <div class="col-xs-4 text-center">
                    <a href="#">Sales</a>
                  </div>
                  <div class="col-xs-4 text-center">
                    <a href="#">Friends</a>
                  </div>
                </div>
                <!-- /.row -->
              </li>
              <!-- Menu Footer-->
              <li class="user-footer">
                <div class="pull-left"id="leftbar">
                    <button id="myBtn" class="btn btn-default btn-flat" ><i class="fa fa-picture-o"></i> Subir foto</button>
                    <div class="hideform" >
                    <form enctype="multipart/form-data" id="formuploadajax" method="post">
                    <input id="changeprofile1" type="file" name="imageprofile" >
                    <input type="button" id="Btn" name="botonperfil" value="Btnperfil"/>
                    </form>
                    </div> 
                   
                </div>
                
                <div class="pull-right">
                		<form action="index.html" method="post">
                		<span id="salida"></span>
                		<input type="submit" class="btn btn-default btn-flat" name="Sign_out" id="Sing_out" value="Sign out"></input>
                		<!-- Alert Para salir -->
                		<!--<a href="#" class="btn btn-default btn-flat"></a>-->
                		</form>
                
                
                  <!--<a href="#" class="btn btn-default btn-flat">Sign out</a>-->
                </div>
              </li>
            </ul>
          </li>
          <!-- Control Sidebar Toggle Button -->
          <li>
            <a href="#" id="ControlAjustes" name="ControlAjustes" data-toggle="control-sidebar" onclick="showSettings()"><i class="fa fa-gears"></i></a>
          </li>
        </ul>
      </div>
    </nav>
  </header>
  <!-- Left side column. contains the logo and sidebar -->
  <aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
      <!-- Sidebar user panel -->
      <div class="user-panel">
        <div class="pull-left image">
          <img src="data:image/jpg;base64,<% out.print(session.getAttribute("photo")); %>" class="img-circle" alt="User Image" id="image2">
        </div>
        <div class="pull-left info">
          <p>
			<% out.println(session.getAttribute("nombres")); %>
		  </p>
          <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
        </div>
      </div>
      <!-- search form -->
      <form action="#" method="get" class="sidebar-form">
        <div class="input-group">
          <input type="text" name="q" class="form-control" placeholder="Search...">
              <span class="input-group-btn">
                <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>
                </button>
              </span>
        </div>
      </form>
      <!-- /.search form -->
      <!-- sidebar menu: : style can be found in sidebar.less -->
      <ul class="sidebar-menu">
        <li class="header">MAIN NAVIGATION</li>
        <li class="active treeview">
          <a href="#">
            <i class="fa fa-dashboard"></i> <span>Dashboard</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li class="active"><a href="index.html"><i class="fa fa-circle-o"></i> Dashboard v1</a></li>
            <li><a href="index2.html"><i class="fa fa-circle-o"></i> Dashboard v2</a></li>
          </ul>
        </li>
        <li class="treeview">
          <a href="#">
            <i class="fa fa-files-o"></i>
            <span>Layout Options</span>
            <span class="pull-right-container">
              <span class="label label-primary pull-right">4</span>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="././resources/pages/layout/top-nav.html"><i class="fa fa-circle-o"></i> Top Navigation</a></li>
            <li><a href="././resources/pages/layout/boxed.html"><i class="fa fa-circle-o"></i> Boxed</a></li>
            <li><a href="././resources/pages/layout/fixed.html"><i class="fa fa-circle-o"></i> Fixed</a></li>
            <li><a href="././resources/pages/layout/collapsed-sidebar.html"><i class="fa fa-circle-o"></i> Collapsed Sidebar</a></li>
          </ul>
        </li>
        <li>
          <a href="././resources/pages/widgets.html">
            <i class="fa fa-th"></i> <span>Widgets</span>
            <span class="pull-right-container">
              <small class="label pull-right bg-green">new</small>
            </span>
          </a>
        </li>
        <li class="treeview">
          <a href="#">
            <i class="fa fa-pie-chart"></i>
            <span>Charts</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="././resources/pages/charts/chartjs.html"><i class="fa fa-circle-o"></i> ChartJS</a></li>
            <li><a href="././resources/pages/charts/morris.html"><i class="fa fa-circle-o"></i> Morris</a></li>
            <li><a href="././resources/pages/charts/flot.html"><i class="fa fa-circle-o"></i> Flot</a></li>
            <li><a href="././resources/pages/charts/inline.html"><i class="fa fa-circle-o"></i> Inline charts</a></li>
          </ul>
        </li>
        <li class="treeview">
          <a href="#">
            <i class="fa fa-laptop"></i>
            <span>UI Elements</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="././resources/pages/UI/general.html"><i class="fa fa-circle-o"></i> General</a></li>
            <li><a href="././resources/pages/UI/icons.html"><i class="fa fa-circle-o"></i> Icons</a></li>
            <li><a href="././resources/pages/UI/buttons.html"><i class="fa fa-circle-o"></i> Buttons</a></li>
            <li><a href="././resources/pages/UI/sliders.html"><i class="fa fa-circle-o"></i> Sliders</a></li>
            <li><a href="././resources/pages/UI/timeline.html"><i class="fa fa-circle-o"></i> Timeline</a></li>
            <li><a href="././resources/pages/UI/modals.html"><i class="fa fa-circle-o"></i> Modals</a></li>
          </ul>
        </li>
        <li class="treeview">
          <a href="#">
            <i class="fa fa-edit"></i> <span>Forms</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="././resources/pages/forms/general.html"><i class="fa fa-circle-o"></i> General Elements</a></li>
            <li><a href="././resources/pages/forms/advanced.html"><i class="fa fa-circle-o"></i> Advanced Elements</a></li>
            <li><a href="././resources/pages/forms/editors.html"><i class="fa fa-circle-o"></i> Editors</a></li>
          </ul>
        </li>
        <li class="treeview">
          <a href="#">
            <i class="fa fa-table"></i> <span>Tables</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="././resources/pages/tables/simple.html"><i class="fa fa-circle-o"></i> Simple tables</a></li>
            <li><a href="././resources/pages/tables/data.html"><i class="fa fa-circle-o"></i> Data tables</a></li>
          </ul>
        </li>
        <li>
          <a href="././resources/pages/calendar.html">
            <i class="fa fa-calendar"></i> <span>Calendar</span>
            <span class="pull-right-container">
              <small class="label pull-right bg-red">3</small>
              <small class="label pull-right bg-blue">17</small>
            </span>
          </a>
        </li>
        <li>
          <a href="././resources/pages/mailbox/mailbox.html">
            <i class="fa fa-envelope"></i> <span>Mailbox</span>
            <span class="pull-right-container">
              <small class="label pull-right bg-yellow">12</small>
              <small class="label pull-right bg-green">16</small>
              <small class="label pull-right bg-red">5</small>
            </span>
          </a>
        </li>
        <li class="treeview">
          <a href="#">
            <i class="fa fa-folder"></i> <span>Examples</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="././resources/pages/examples/invoice.html"><i class="fa fa-circle-o"></i> Invoice</a></li>
            <li><a href="././resources/pages/examples/profile.html"><i class="fa fa-circle-o"></i> Profile</a></li>
            <li><a href="././resources/pages/examples/login.html"><i class="fa fa-circle-o"></i> Login</a></li>
            <li><a href="././resources/pages/examples/register.html"><i class="fa fa-circle-o"></i> Register</a></li>
            <li><a href="././resources/pages/examples/lockscreen.html"><i class="fa fa-circle-o"></i> Lockscreen</a></li>
            <li><a href="././resources/pages/examples/404.html"><i class="fa fa-circle-o"></i> 404 Error</a></li>
            <li><a href="././resources/pages/examples/500.html"><i class="fa fa-circle-o"></i> 500 Error</a></li>
            <li><a href="././resources/pages/examples/blank.html"><i class="fa fa-circle-o"></i> Blank Page</a></li>
            <li><a href="././resources/pages/examples/pace.html"><i class="fa fa-circle-o"></i> Pace Page</a></li>
          </ul>
        </li>
        <li class="treeview">
          <a href="#">
            <i class="fa fa-share"></i> <span>Multilevel</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="#"><i class="fa fa-circle-o"></i> Level One</a></li>
            <li>
              <a href="#"><i class="fa fa-circle-o"></i> Level One
                <span class="pull-right-container">
                  <i class="fa fa-angle-left pull-right"></i>
                </span>
              </a>
              <ul class="treeview-menu">
                <li><a href="#"><i class="fa fa-circle-o"></i> Level Two</a></li>
                <li>
                  <a href="#"><i class="fa fa-circle-o"></i> Level Two
                    <span class="pull-right-container">
                      <i class="fa fa-angle-left pull-right"></i>
                    </span>
                  </a>
                  <ul class="treeview-menu">
                    <li><a href="#"><i class="fa fa-circle-o"></i> Level Three</a></li>
                    <li><a href="#"><i class="fa fa-circle-o"></i> Level Three</a></li>
                  </ul>
                </li>
              </ul>
            </li>
            <li><a href="#"><i class="fa fa-circle-o"></i> Level One</a></li>
          </ul>
        </li>
        <li><a href="documentation/index.html"><i class="fa fa-book"></i> <span>Documentation</span></a></li>
        <li class="header">LABELS</li>
        <li><a href="#"><i class="fa fa-circle-o text-red"></i> <span>Important</span></a></li>
        <li><a href="#"><i class="fa fa-circle-o text-yellow"></i> <span>Warning</span></a></li>
        <li><a href="#"><i class="fa fa-circle-o text-aqua"></i> <span>Information</span></a></li>
      </ul>
    </section>
    <!-- /.sidebar -->
  </aside>
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        Dashboard
        <small>Control panel</small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li class="active">Dashboard</li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">
      
      <!-- /.row -->
      <!-- Main row -->
      <div class="row">
        <!-- Left col -->
        <section class="col-lg-7 connectedSortable">
          <!-- Custom tabs (Charts with tabs)-->
          <div class="nav-tabs-custom">
            <!-- Tabs within a box -->
            <ul class="nav nav-tabs pull-right">
              <li class="active"><a href="#revenue-chart" data-toggle="tab">Area</a></li>
              <li><a href="#sales-chart" data-toggle="tab">Donut</a></li>
              <li class="pull-left header"><i class="fa fa-inbox"></i> Estado del clima</li>
            </ul>
            <div class="tab-content no-padding">
              <!-- Morris chart - Sales -->
              
              <!--<canvas id="lineChart" height="300px" width="560px"></canvas>-->
              <div id="graph" height="300px" width="560px" >Loading graph...</div>
              
            </div>
          </div>
          <!-- /.nav-tabs-custom -->

          <!-- Chat box -->
          <div class="box box-success">
            <div class="box-header">
              <i class="fa fa-comments-o"></i>

              <h3 class="box-title">Publicaciones</h3>

            </div>
            <div class="box-body chat" id="chat-box">
       
            
              <!-- chat item -->
              <%
              		
              
              	if(lista.isEmpty()){
              		Dta=false;
              	%>
              		<div id="TextBoxDiv-1" name="divprmero"class="alert alert-info alert-dismissible fade in" role="alert"> <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true"></span></button> <i class="fa fa-files-o"></i> <strong>Aun no hay publicaciones por aqui..</strong></div>
              	<% 	
              	}else{
              		for (int i=0;i<lista.size();i++)
    				{
    					Object[] Datos=hibernateTransations.consultarDatos(lista.get(i).getAuthor());
    					Date date= new Date();
    					date=lista.get(i).getDate();
    			%>
    					 <div class="item" id="TextBoxDiv<%out.print(lista.get(i).getId_publication());%>" name="divttsg">
    					 <img src="data:image/jpg;base64,<%out.print(Base64.encode((byte[])Datos[2]));%>"  alt="user image" class="online" style="cursor: pointer" id="popoveUser<%out.print(lista.get(i).getId_publication());%>" name="<%out.print(lista.get(i).getAuthor());%>" onclick="consultaPerfil(this.name,this.id)">
                      	<p class="message">
                      	<a class="name">
                        <!-- pulido -->
                        <small class="text-muted pull-right"><i class="fa fa-clock-o"></i><% out.print(((date.getHours()<10)?"0"+date.getHours():date.getHours()) +":"+ ((date.getMinutes()<10)?"0"+date.getMinutes():date.getMinutes()) +":"+((date.getSeconds()<10)?"0"+date.getSeconds():date.getSeconds()));%></small>
                        <%out.print(Datos[0]+" "+Datos[1]);%>
                      </a>
    					<img src="././resources/img/perfil/
						<%out.print(lista.get(i).getWeather());%>-weather.png" class="img-responsive"/>
						<!-- pulido -->
						<%if(lista.get(i).getDescripcion()!=null){ out.print(lista.get(i).getDescripcion()+"");}%>
                    	</p>
                    
                    <!-- pulido -->
                    	<% if(lista.get(i).getPhoto()!=null){ %>
                    		<div class="attachment">
                      		<img src="data:image/png;base64,<%out.print(Base64.encode((byte[])lista.get(i).getPhoto()));%>" class="img-responsive">
                      		</div>
                      	<% } %>
                    <% 
                    if (Integer.parseInt(session.getAttribute("usuario").toString())==(lista.get(i).getAuthor())){
                    %>
                     <div class="attachment">
	                    <button id="<%out.print(lista.get(i).getId_publication());%>" class="btn btn-default" onclick="AlertDrop(this.id)" >
							 <span class="glyphicon glyphicon-trash"></span>
						</button>
						
												<!-- pulido -->
						<button id="<%out.print(lista.get(i).getId_publication());%>" class="btn btn-default"  data-toggle="modal" onclick="DisplayComments(this.id)">
							 <span class="glyphicon glyphicon-pencil"></span>
						</button>
						
						<small> <b> <i> <%out.print(mongoTransations.consultarUbication(Integer.parseInt(lista.get(i).getId_Barrio())).replace("\"","").replaceAll("(,barrio|,pto_cardinal|Oriente|Occidente|Nororiente|Centro|Suroriente|Norte|Centro Nororiente|Suroccidente|Sur)(\\W|$)", "").replace(","," (").replace(":", " ")+")");%> </i> </b></small>
						
                    </div>
                    <%}else{
                    	
                    	boolean islike = false; 
                    	
                    	for (Like like : list) {
                			
                    		if (lista.get(i).getId_publication() == like.getId_publicacion()){%>
                    			<div class="attachment">
	                    		<button id="<%out.print(lista.get(i).getId_publication());%>"class="btn btn-default"  onclick="Like(this.id)">
							 	<span class="glyphicon glyphicon-heart"></span>
								</button>
								
								<!-- cambios -->
								<button id="<%out.print(lista.get(i).getId_publication());%>" class="btn btn-default"  data-toggle="modal" onclick="DisplayComments(this.id)">
								 <span class="glyphicon glyphicon-pencil"></span>
								</button>
								
								<small> <b> <i> <%out.print(mongoTransations.consultarUbication(Integer.parseInt(lista.get(i).getId_Barrio())).replace("\"","").replaceAll("(,barrio|,pto_cardinal|Oriente|Occidente|Nororiente|Centro|Suroriente|Norte|Centro Nororiente|Suroccidente|Sur)(\\W|$)", "").replace(","," (").replace(":", " ")+")");%> </i> </b> </small>
								
								</div>
                    			</div>
                    			
                    			<%islike = true;
                    			break;
                    		}
                    		}
                    	if(islike)continue;
                    	%>
	                   	<div class="attachment">
		                    <button id="<%out.print(lista.get(i).getId_publication());%>"class="btn btn-default"  onclick="Like(this.id)">
								 <span class="glyphicon glyphicon-heart-empty"></span>
							</button>
							
							<!-- cambios -->
							<button id="<%out.print(lista.get(i).getId_publication());%>" class="btn btn-default"  data-toggle="modal" onclick="DisplayComments(this.id)">
								 <span class="glyphicon glyphicon-pencil"></span>
							</button>
							
							<!-- pulido -->
							<small> <b> <i> <%out.print(mongoTransations.consultarUbication(Integer.parseInt(lista.get(i).getId_Barrio())).replace("\"","").replaceAll("(,barrio|,pto_cardinal|Oriente|Occidente|Nororiente|Centro|Suroriente|Norte|Centro Nororiente|Suroccidente|Sur)(\\W|$)", "").replace(","," (").replace(":", " ")+")");%> </i> </b> </small>
							
	                    </div>
                    <%}
                    %>
                    
                    <!-- /.attachment -->
                  	</div>
              	<%}
				}
              	if(Dta==true){
              	%>
              	<input id="Ult_Pb" name="Ult_Pb" type="hidden" value="<%out.print(lista.get(0).getId_publication());%>">
              	<%}else{
              	%>
              	<input id="Ult_Pb" name="Ult_Pb" type="hidden" value="0">
              	<%}
              	%>
              
              	
             
          </div>
          <!-- /.box (chat box) -->

          <!-- TO DO List -->
          <div class="box box-primary">
           
          </div>
          <!-- /.box -->

          <!-- quick email widget -->
          <div class="box box-info">
            <div class="box-header">
              <i class="fa fa-envelope"></i>

              <h3 class="box-title">Quick Email</h3>
              <!-- tools box -->
              <div class="pull-right box-tools">
                <button type="button" class="btn btn-info btn-sm" data-widget="remove" data-toggle="tooltip" title="Remove">
                  <i class="fa fa-times"></i></button>
              </div>
              <!-- /. tools -->
            </div>
            <div class="box-body">
              <form action="#" method="post">
                <div class="form-group">
                  <input type="email" class="form-control" name="emailto" placeholder="Email to:">
                </div>
                <div class="form-group">
                  <input type="text" class="form-control" name="subject" placeholder="Subject">
                </div>
                <div>
                  <textarea class="textarea" placeholder="Message" style="width: 100%; height: 125px; font-size: 14px; line-height: 18px; border: 1px solid #dddddd; padding: 10px;"></textarea>
                </div>
              </form>
            </div>
            <div class="box-footer clearfix">
              <button type="button" class="pull-right btn btn-default" id="sendEmail">Send
                <i class="fa fa-arrow-circle-right"></i></button>
            </div>
          </div>

        </section>
        <!-- /.Left col -->
        <!-- right col (We are only adding the ID to make the widgets sortable)-->
        <section class="col-lg-5 connectedSortable">

          <!-- Map box -->
          <div class="box box-solid bg-light-blue-gradient">
            <div class="box-header">
              <!-- tools box -->
              <div class="pull-right box-tools">
                <button type="button" class="btn btn-primary btn-sm daterange pull-right" data-toggle="tooltip" title="Date range">
                  <i class="fa fa-calendar"></i></button>
                <button type="button" class="btn btn-primary btn-sm pull-right" data-widget="collapse" data-toggle="tooltip" title="Collapse" style="margin-right: 5px;">
                  <i class="fa fa-minus"></i></button>
              </div>
              <!-- /. tools -->

              <i class="fa fa-map-marker"></i>

              <h3 class="box-title">
                Estado Del Clima
              </h3>
            </div>
            <div class="box-body">
              <div id="world-map" style="height: 400px; width: 100%;"></div>
              
            </div>
            <!-- /.box-body-->
            <div class="box-footer no-border">
              <div class="row">
                <div class="col-xs-4 text-center" style="border-right: 1px solid #f4f4f4">
                  <div id="sparkline-1"></div>
                  <div class="knob-label" >Ciudad</div>
                </div>
                <!-- ./col -->
                <div class="col-xs-4 text-center" style="border-right: 1px solid #f4f4f4">
                  <div id="sparkline-2"></div>
                  <div class="knob-label">Comuna</div>
                </div>
                <!-- ./col -->
                <div class="col-xs-4 text-center">
                  <div id="sparkline-3"></div>
                  <div class="knob-label">Barrio</div>
                </div>
                <!-- ./col -->
              </div>
              <!-- /.row -->
            </div>
          </div>
          <!-- /.box -->
          
          
          

          <!-- solid sales graph -->
          <div class="box box-solid bg-teal-gradient">
            <div class="box-header">
              <i class="fa fa-th"></i>

              <h3 class="box-title">Sales Graph</h3>

              <div class="box-tools pull-right">
                <button type="button" class="btn bg-teal btn-sm" data-widget="collapse"><i class="fa fa-minus"></i>
                </button>
                <button type="button" class="btn bg-teal btn-sm" data-widget="remove"><i class="fa fa-times"></i>
                </button>
              </div>
            </div>
            <div class="box-body border-radius-none">
              <div class="chart" id="line-chart" style="height: 250px;"></div>
            </div>
           
            <!-- /.box-body -->
            <div class="box-footer no-border">
              <div class="row">
                <div class="col-xs-4 text-center" style="border-right: 1px solid #f4f4f4">
                  <input type="text" class="knob" data-readonly="true" value="20" data-width="60" data-height="60" data-fgColor="#39CCCC">

                  <div class="knob-label">Mail-Orders</div>
                </div>
                <!-- ./col -->
                <div class="col-xs-4 text-center" style="border-right: 1px solid #f4f4f4">
                  <input type="text" class="knob" data-readonly="true" value="50" data-width="60" data-height="60" data-fgColor="#39CCCC">

                  <div class="knob-label">Online</div>
                </div>
                <!-- ./col -->
                <div class="col-xs-4 text-center">
                  <input type="text" class="knob" data-readonly="true" value="30" data-width="60" data-height="60" data-fgColor="#39CCCC">

                  <div class="knob-label">In-Store</div>
                </div>
                <!-- ./col -->
              </div>
              <!-- /.row -->
            </div>
            <!-- /.box-footer -->
          </div>
          <!-- /.box -->

          <!-- Calendar -->
          <div class="box box-solid bg-green-gradient">
            
          </div>
          <!-- /.box -->

        </section>
        <!-- right col -->
      </div>
      <!-- /.row (main row) -->

    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
  <footer class="main-footer">
    <div class="pull-right hidden-xs">
      <b>Version</b> 2.3.6
    </div>
    <strong>Copyright &copy; 2014-2016 <a href="http://Spring.com">Drizzle Web</a>.</strong> All rights
    reserved.
  </footer>

  <!-- Control Sidebar -->
  <aside class="control-sidebar control-sidebar-dark">
    <!-- Create the tabs -->
    <ul class="nav nav-tabs nav-justified control-sidebar-tabs">
      <li><a href="#control-sidebar-home-tab" data-toggle="tab"><i class="fa fa-home"></i></a></li>
      <li><a href="#control-sidebar-settings-tab" data-toggle="tab"><i class="fa fa-gears"></i></a></li>
    </ul>
    <!-- Tab panes -->
    <div class="tab-content">
      <!-- Home tab content -->
      <div class="tab-pane" id="control-sidebar-home-tab">
        <h3 class="control-sidebar-heading">Recent Activity</h3>
        <ul class="control-sidebar-menu">
          <li>
            <a href="javascript:void(0)">
              <i class="menu-icon fa fa-birthday-cake bg-red"></i>

              <div class="menu-info">
                <h4 class="control-sidebar-subheading">Langdon's Birthday</h4>

                <p>Will be 23 on April 24th</p>
              </div>
            </a>
          </li>
          <li>
            <a href="javascript:void(0)">
              <i class="menu-icon fa fa-user bg-yellow"></i>

              <div class="menu-info">
                <h4 class="control-sidebar-subheading">Frodo Updated His Profile</h4>

                <p>New phone +1(800)555-1234</p>
              </div>
            </a>
          </li>
          <li>
            <a href="javascript:void(0)">
              <i class="menu-icon fa fa-envelope-o bg-light-blue"></i>

              <div class="menu-info">
                <h4 class="control-sidebar-subheading">Nora Joined Mailing List</h4>

                <p>nora@example.com</p>
              </div>
            </a>
          </li>
          <li>
            <a href="javascript:void(0)">
              <i class="menu-icon fa fa-file-code-o bg-green"></i>

              <div class="menu-info">
                <h4 class="control-sidebar-subheading">Cron Job 254 Executed</h4>

                <p>Execution time 5 seconds</p>
              </div>
            </a>
          </li>
        </ul>
        <!-- /.control-sidebar-menu -->

        <h3 class="control-sidebar-heading">Tasks Progress</h3>
        <ul class="control-sidebar-menu">
          <li>
            <a href="javascript:void(0)">
              <h4 class="control-sidebar-subheading">
                Custom Template Design
                <span class="label label-danger pull-right">70%</span>
              </h4>

              <div class="progress progress-xxs">
                <div class="progress-bar progress-bar-danger" style="width: 70%"></div>
              </div>
            </a>
          </li>
          <li>
            <a href="javascript:void(0)">
              <h4 class="control-sidebar-subheading">
                Update Resume
                <span class="label label-success pull-right">95%</span>
              </h4>

              <div class="progress progress-xxs">
                <div class="progress-bar progress-bar-success" style="width: 95%"></div>
              </div>
            </a>
          </li>
          <li>
            <a href="javascript:void(0)">
              <h4 class="control-sidebar-subheading">
                Laravel Integration
                <span class="label label-warning pull-right">50%</span>
              </h4>

              <div class="progress progress-xxs">
                <div class="progress-bar progress-bar-warning" style="width: 50%"></div>
              </div>
            </a>
          </li>
          <li>
            <a href="javascript:void(0)">
              <h4 class="control-sidebar-subheading">
                Back End Framework
                <span class="label label-primary pull-right">68%</span>
              </h4>

              <div class="progress progress-xxs">
                <div class="progress-bar progress-bar-primary" style="width: 68%"></div>
              </div>
            </a>
          </li>
        </ul>
        <!-- /.control-sidebar-menu -->

      </div>
      <!-- /.tab-pane -->
      <!-- Stats tab content -->
      <div class="tab-pane" id="control-sidebar-stats-tab">Stats Tab Content</div>
      <!-- /.tab-pane -->
      <!-- Settings tab content -->
      <div class="tab-pane" id="control-sidebar-settings-tab">
        <form id="formularioAjustes" name="formularioAjustes" method="post" enctype="multipart/form-data" >
          <h3 class="control-sidebar-heading">Ajustes Generales</h3>


		<div class="form-group">
            <label class="control-sidebar-subheading">
            <p>
              Modificar Nombre:
            </p>
               <div class="input-group">
          			<input type="text" id="nombreModificar" name="nombreModificar" placeholder="Nombre..." style="color: #000000">
        		</div>
              
            </label>

          </div>
          
          <div class="form-group">
            <label class="control-sidebar-subheading">
            <p>
              Modificar Apellido:
            </p>
               <div class="input-group">
          			<input type="text"  id="apellidoModificar"  name="apellidoModificar" placeholder="Apellido..." style="color: #000000">
        		</div>
              
            </label>

          </div>
          
          <div class="form-group">
            <label class="control-sidebar-subheading">
            <p>
              Modificar Contraseña:
            </p>
               <div class="input-group">
          			<input type="password" id="contrasenaModificar" name="contrasenaModificar" style="color: #000000">
        		</div>
              
            </label>

          </div>

          <div class="form-group">
            <label class="control-sidebar-subheading">
            <p>
              Modificar telefono:
            </p>
               <div class="input-group">
          			<input type="number" id="telefonoModificar" name="telefonoModificar" placeholder="Telefono..." style="color: #000000">
              		<span class="input-group-btn">
                		<input name = "checkTelefono" id = "checkTelefono" type="checkbox" value="off" class="pull-right">
              		</span>
        		</div>
              
            </label>

            <p align="right" style="font-size:11px;">
              Hacer visible
            </p>
          </div>
          
          <div class="form-group">
            <label class="control-sidebar-subheading">            
            <p>
              Correo:
            </p>
             <div class="input-group">
		             <input type="text" id="correoModificar"  name="correoModificar" value="garmbest@hotmail.com" style="color: #000000" readonly>
             		<span class="input-group-btn">
               		<input name = "checkCorreo" id = "checkCorreo" type="checkbox" value="off" class="pull-right">
             		</span>
       		</div>
              
            </label>

            <p align="right" style="font-size:11px;">
              Hacer visible
            </p>
          </div>
          
          <br/>
          <br/>
          <div>
          <button id="BtnAjustes" name="BtnAjustes" type="button" onclick="establecerAjustes(this.form)" class="btn btn-info btn-lg">Guardar</button>
          </div>
          <!-- /.form-group -->

        </form>
      </div>
      <!-- /.tab-pane -->
    </div>
  </aside>
  <!-- /.control-sidebar -->
  <!-- Add the sidebar's background. This div must be placed
       immediately after the control sidebar -->
  <div class="control-sidebar-bg"></div>
</div>
<!-- ./wrapper -->

<!-- jQuery 2.2.3 -->
<!-- jQuery UI 1.11.4 -->
<script src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
<script>
  $.widget.bridge('uibutton', $.ui.button);
</script>
<!-- Bootstrap 3.3.6 -->
<script src="././resources/js/bootstrap.min.js"></script>
<!-- notification pulido -->
<script src="././resources/js/jquery.bootstrap-growl.js"></script>
<!-- Morris.js charts -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js"></script>
<script src="././resources/js/morris.min.js"></script>
<!-- Sparkline -->
<script src="././resources/js/jquery.sparkline.min.js"></script>
<!-- jvectormap -->
<script src="././resources/js/jquery-jvectormap-1.2.2.min.js"></script>
<script src="././resources/js/jquery-jvectormap-world-mill-en.js"></script>
<!-- jQuery Knob Chart -->
<script src="././resources/js/jquery.knob.js"></script>
<!-- daterangepicker -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.11.2/moment.min.js"></script>
<script src="././resources/js/daterangepicker.js"></script>
<!-- datepicker -->
<script src="././resources/js/bootstrap-datepicker.js"></script>
<!-- Bootstrap WYSIHTML5 -->
<script src="././resources/js/bootstrap3-wysihtml5.all.min.js"></script>
<!-- Slimscroll -->
<script src="././resources/js/jquery.slimscroll.min.js"></script>
<!-- FastClick -->
<script src="././resources/js/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="././resources/js/app.min.js"></script>
<!-- AdminLTE dashboard demo (This is only for demo purposes) -->
<script src="././resources/dashboard.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="././resources/js/demo.js"></script>
<script src="././resources/js/sau3member.js"></script>
 <script src="././resources/js/validaciones.js"></script>
<script src="././resources/js/publicacion.js"></script>
<script src="././resources/js/mapa.js"></script>

<script src="././resources/js/bootstrap-confirmation.min.js" type="text/javascript"></script>

<script src="././resources/chartjs/Chart.min.js"></script>


<!-- Api Google maps JavaScritp -->
 




<%--hibernateTransations.registrar(new Account("fabian","ruiz","fabia.an@hotmail.com","dr1ssl3","05/02/1999",4458745));--%>

Registro:${registro};
	
	
</body>
</html>