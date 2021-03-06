<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Drizzle Web</title>
    
    <!-- Icono de Pestaña -->
	<link rel="shortcut icon" type="image/png" href="././resources/img/perfil/weather-9.png"/>
    <!-- Bootstrap Core CSS -->
    <link href="././resources/css/bootstrap.min.css" rel="stylesheet">

    <!-- Theme CSS -->
    <link href="././resources/css/freelancer.min.css" rel="stylesheet">

    <!-- Theme CSS Login -->
    <link rel="stylesheet" type="text/css" href="././resources/css/Login.css">
    <link href="././resources/css/styleinput.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="././resources/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic" rel="stylesheet" type="text/css">


	<link href="//oss.maxcdn.com/jquery.bootstrapvalidator/0.5.2/css/bootstrapValidator.min.css" rel="stylesheet"></link
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    
    <!-- SweetAlert Alertas -->
	<script  src="././resources/dist12/sweetalert-dev.js"></script>
    <link rel="stylesheet" href="././resources/dist12/sweetalert.css" /> 
   
   <script src="././resources/js/publicacion.js"></script> 
   <script src="//oss.maxcdn.com/jquery/1.11.1/jquery.min.js"></script>
  	<script src="././resources/js/validator.js"></script>
 	<!-- jQuery -->
    <script src="././resources/jquery/jquery.min.js"></script>
    
    
	<!-- Jquery-confirm ventana para recuparar password// -->
    <link rel="stylesheet" type="text/css" href="././resources/css/jquery-confirm.css"/>
    <script type="text/javascript" src="././resources/js/jquery-confirm.js"></script>

   
</head>

<body id="page-top" class="index" onload="getPublicationRecent()">



    <!-- Navigation -->
    <nav id="mainNav" class="navbar navbar-default navbar-fixed-top navbar-custom">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header page-scroll">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span> Menu <i class="fa fa-bars"></i>
                </button>
                <a class="navbar-brand" href="#page-top">Drizzle Web</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
                    <li class="hidden">
                        <a href="#page-top"></a>
                    </li>
                    <li class="page-scroll">
                        <a href="#portfolio">Estados</a>
                    </li>
                    <li class="page-scroll">
                        <a href="#about">Acerca</a>
                    </li>
                    <li class="page-scroll">
                        <a href="#contact">Registro</a>
                    </li>
                    <li class="page-scroll">
                        <a href="#" data-toggle="modal" data-target="#modal1" class="btn btn-blue">Iniciar Sesion</a>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container-fluid -->
    </nav>

    <!-- Header -->
    <header>
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <img class="img-responsive" src="././resources/img/perfil/iconIndex.png" alt="">
                    <div class="intro-text">
                        <span class="name">Comparte el Clima</span>
                        <hr class="star-light">
                        <span class="skills">Web Developer - Graphic Artist - User Experience Designer</span>
                    </div>
                </div>
            </div>
        </div>
    </header>

    <!-- Portfolio Grid Section -->
    <section id="portfolio">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <h2>Estados del Clima</h2>
                    <hr class="star-primary">
                </div>
            </div>
            <div class="row">
                <div class="col-sm-4 portfolio-item">
                    <a href="#portfolioModal1" class="portfolio-link" data-toggle="modal">
                        <div class="caption">
                            <div class="caption-content">
                                <i class="fa fa-search-plus fa-3x"></i>
                            </div>
                        </div>
                        <!-- pulido -->
                        <img id="imagenIndex0" src="././resources/img/portfolio/umbrella.png" class="img-responsive" alt="">
                    </a>
                </div>
                <div class="col-sm-4 portfolio-item">
                    <a href="#portfolioModal2" class="portfolio-link" data-toggle="modal">
                        <div class="caption">
                            <div class="caption-content">
                                <i class="fa fa-search-plus fa-3x"></i>
                            </div>
                        </div>
                        <!-- pulido -->
                        <img id="imagenIndex1" src="././resources/img/portfolio/umbrella.png" class="img-responsive" alt="">
                    </a>
                </div>
                <div class="col-sm-4 portfolio-item">
                    <a href="#portfolioModal3" class="portfolio-link" data-toggle="modal">
                        <div class="caption">
                            <div class="caption-content">
                                <i class="fa fa-search-plus fa-3x"></i>
                            </div>
                        </div>
                        <!-- pulido -->
                        <img id="imagenIndex2" src="././resources/img/portfolio/umbrella.png" class="img-responsive" alt="">
                    </a>
                </div>
                <div class="col-sm-4 portfolio-item">
                    <a href="#portfolioModal4" class="portfolio-link" data-toggle="modal">
                        <div class="caption">
                            <div class="caption-content">
                                <i class="fa fa-search-plus fa-3x"></i>
                            </div>
                        </div>
                        <!-- pulido -->
                        <img id="imagenIndex3" src="././resources/img/portfolio/umbrella.png" class="img-responsive" alt="">
                    </a>
                </div>
                <div class="col-sm-4 portfolio-item">
                    <a href="#portfolioModal5" class="portfolio-link" data-toggle="modal">
                        <div class="caption">
                            <div class="caption-content">
                                <i class="fa fa-search-plus fa-3x"></i>
                            </div>
                        </div>
                        <!-- pulido -->
                        <img id="imagenIndex4" src="././resources/img/portfolio/umbrella.png" class="img-responsive" alt="">
                    </a>
                </div>
                <div class="col-sm-4 portfolio-item">
                    <a href="#portfolioModal6" class="portfolio-link" data-toggle="modal">
                        <div class="caption">
                            <div class="caption-content">
                                <i class="fa fa-search-plus fa-3x"></i>
                            </div>
                        </div>
                        <!-- pulido -->
                        <img id="imagenIndex5" src="././resources/img/portfolio/umbrella.png" class="img-responsive" alt="">
                    </a>
                </div>
            </div>
        </div>
    </section>
    
    <!-- About Section -->
    <section class="success" id="about">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <h2>Acerca</h2>
                    <hr class="star-light">
                </div>
            </div>
            <div class="row">
                <div class="col-lg-4 col-lg-offset-2">
                    <p style="text-align: justify;">Drizzle Social entrega a las personas de la ciudad de Cali, datos en tiempo real de la situacion climatica presentada en puntos especificos de la ciudad con el fin de estar al tanto de lo que sucede para tomar decisiones mas acertadas teniendo en cuenta el estado del clima, dicha informacion sera suministrada por los mismos usuarios ya que son los mismos que conocen y saben cual es el estado del clima en su ubicacion y su beneficio sera el de compartir e informar a las demas personas que utilicen el servicio.</p>
                </div>
                <div class="col-lg-4">
                    <p style="text-align: justify;">Es de gran importancia conocer lo ocurrido y lo que actualmente sucede en cuanto al estado del clima, saber si esta lloviendo, va a llover o por el contrario es un dia soleado siempre sera un tema de interes para la sociedad,</p>
                </div>
                <div class="col-lg-8 col-lg-offset-2 text-center">
                    <a onclick="Manual('././resources/img/Manual de Usuario.pdf')" class="btn btn-lg btn-outline">
                        <i class="fa fa-download"></i> Descargar Manual
                    </a>
                </div>
            </div>
        </div>
    </section>

    <!-- Contact Section -->
    <section id="contact">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <h2>Sign Up</h2>
                    <hr class="star-primary">
                </div>
            </div>
            <div class="row">
                <div class="col-lg-8 col-lg-offset-2">
                    <!-- To configure the contact form email address, go to mail/contact_me.php and update the email address in the PHP file on line 19. -->
                    <!-- The form should work on most web servers, but if the form is not working you may need to configure your web server differently. -->
                  <form method="POST" id="formularioReg" name="formularioReg" enctype="multipart/form-data" onsubmit="return registroUsuario();">
                        <div class="row control-group">
                            <div class="form-group col-xs-12 floating-label-form-group controls">
                                <div class="col-xs-6">
                               		<label  path="name">Name</label>
                          			<input type="text"  class="form-control" path="name" placeholder="Name" id="name" name="name" required="required"/>
                                	<p class="help-block text-danger"></p>
                                </div>
                                <div class="col-xs-6">
                                	<label path="Last Name">Last Name</label>
                                	<input type="text" class="form-control" path="Last Name" placeholder="Last Name" id="lastname" name="lastname" required="required"/>
                                	<p class="help-block text-danger"></p>
                                </div>
                            </div>
                        </div>
                         <div class="row control-group">
                            <div class="form-group col-xs-12 floating-label-form-group controls">
                            	<div class="col-xs-6">
                                	<label>Email Address</label>
                                	<input type="email" class="form-control" placeholder="Email Address" id="email" name="email" required="required"/>
                                	<p class="help-block text-danger"></p>
                            	</div>
                            	<div class="col-xs-6">
                                	<label>Birth Date</label>
                                	<input type="date" class="form-control" placeholder="Birth Date" id="birth" name="birth" required="required"/>
                                	<p class="help-block text-danger"></p>
                            	</div>
                            </div>
                        </div>
                        <div class="row control-group">
                            <div class="form-group col-xs-12 floating-label-form-group controls">
                                <div class="col-xs-11">
                                	<label>Phone Number</label>
                                	<!-- pulido3 quitar maxlength -->
                                	<input type="number" class="form-control" placeholder="Phone Number" id="phone" name="phone" required="required"/>
                                	<p class="help-block text-danger"></p>
                                </div>
                            </div>
                            
                        </div>
                        <div class="row control-group">
                            <div class="form-group col-xs-12 floating-label-form-group controls">
                            	<div class="col-xs-11">
                                	<label >Password</label>
                                	<input type="password" class="form-control" placeholder="Password" id="password" name="password" required="required"/>
                                	<p class="help-block text-danger"></p>
                                	
                              	</div>
                            </div>
                        </div>
                        <div class="row control-group">
                            <div class="form-group col-xs-12 floating-label-form-group controls">
                            	<div class="col-xs-11">
                                	<label>Confirm Password</label>
                                	<input type="password" class="form-control" placeholder="Confirmar password" id="password2" name="password2" required="required"/>
                                	<p class="help-block text-danger"></p>
                            	</div>
                            </div>
                        </div>
                        <br>
                        <div id="success"></div>
                        <div class="row">
                            <div class="form-group col-xs-12">
                            	<div class="col-xs-11">
                                <button type="submit" class="btn btn-success btn-lg" name="botonregistro" id="botonregistro" onclick="registroUsuario()">Registrarme</button>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
      <div class="modal fade" id="modal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  		<div class="modal-dialog">
  			<div class="modal-content modal-popup">
  				<a href="#" class="close-link"><i class="icon_close_alt2"></i></a>
  				<h3 class="white">Login</h3>
  				<form class="popup-form" method="post" enctype="multipart/form-data" id="logger" name="logger" >
  				<!-- pulido3 -->
  					<input type="email" id="user_name" name="user_name" class="form-control form-white" placeholder="correo" required="required">
  					<input type="password" id="pass" name="pass" class="form-control form-white" placeholder="contrase�a" required="required">
  					<div class="checkbox-holder text-left">
  						<div>
  							<label id="demo" class="modalconfirm" style="cursor: pointer" onclick="olvCont()"><span>Olvidaste Tu Contrase&ntilde;a&#63;</span></label>
  						</div>
  					</div>
  					<input type="button" name="botonLogin" value="Iniciar Sesion" class="btn btn-submit" onclick="Login()"></input>
  				</form>
  			</div>
  		</div>
  	</div>

    <!-- Footer -->
    <footer class="text-center">
        <div class="footer-above">
            <div class="container">
                <div class="row">
                    <div class="footer-col col-md-4">
                        <h3>Localizacion</h3>
                        <p>Santiago de Cali 
                            <br>Valle del Cauca, COL 760001</p>
                    </div>
                    <div class="footer-col col-md-4">
                        <h3>Sitios Web</h3>
                        <ul class="list-inline">
                            <li>
                                <a href="https://www.facebook.com/profile.php?id=100015473926041" target="_blank"  class="btn-social btn-outline"><i class="fa fa-fw fa-facebook"></i></a>
                            </li>
                            
                            <li>
                                <a href="https://twitter.com/DrizzleCali"  target="_blank" class="btn-social btn-outline"><i class="fa fa-fw fa-twitter"></i></a>
                            </li>
                            <li>
                                <a href="https://www.linkedin.com/in/drizzle-web-820050139/" target="_blank"  class="btn-social btn-outline"><i class="fa fa-fw fa-linkedin"></i></a>
                            </li>
                            <li>
                                <a href="https://www.instagram.com/drizzlewebcali/" target="_blank"  class="btn-social btn-outline"><i class="fa fa-fw fa-instagram"></i></a>
                            </li>
                        </ul>
                    </div>
                    <div class="footer-col col-md-4">
                        <h3>Autores</h3>
                        <p>Creadores. Mario Garcia, Gustavo Ruiz! <a>DrizzleWeb@hotmail.com</a></p>
                    </div>
                </div>
            </div>
        </div>
        <div class="footer-below">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        Copyright &copy;Drizzle Web 2017
                    </div>
                </div>
            </div>
        </div>
    </footer>

    <!-- Scroll to Top Button (Only visible on small and extra-small screen sizes) -->
    <div class="scroll-top page-scroll hidden-sm hidden-xs hidden-lg hidden-md">
        <a class="btn btn-primary" href="#page-top">
            <i class="fa fa-chevron-up"></i>
        </a>
    </div>

    <!-- Portfolio Modals -->
    <div class="portfolio-modal modal fade" id="portfolioModal1"  tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-content">
            <div class="close-modal" data-dismiss="modal">
                <div class="lr">
                    <div class="rl">
                    </div>
                </div>
            </div>
            <div class="container">
                <div class="row">
                    <div class="col-lg-8 col-lg-offset-2">
                        <div id = "modalIndexImagen0" class="modal-body">
                            <h2>Comparte el estado del clima de Cali!</h2>
                            <hr class="star-primary">
                            <!-- pulido -->
                            <img src="././resources/img/portfolio/umbrella.png" class="img-responsive img-centered" alt="">
                            <p>�C�mo se encuentra el estado climatico de tu ubicaci�n?</p>
                            <img id = "imagenClima0" class="img-responsive img-centered" src="././resources/img/perfil/no-weather.png" alt="clima">
                            <ul class="list-inline item-details">
                            
                            	<strong>Autor:</strong>
                            	<li>                            	
                                 t� (regi�strate)
                                </li>
                                 
                                <strong>Fecha:</strong>
                                <li>
                                hoy 
                                </li>
                                
                            </ul>
                            <button type="button" class="btn btn-default" data-dismiss="modal"><i class="fa fa-times"></i> Close</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="portfolio-modal modal fade" id="portfolioModal2" tabindex="-1" role="dialog" aria-hidden="true">
        <div  class="modal-content">
            <div class="close-modal" data-dismiss="modal">
                <div class="lr">
                    <div class="rl">
                    </div>
                </div>
            </div>
            <div  class="container">
                <div class="row">
                    <div class="col-lg-8 col-lg-offset-2">
                        <div id = "modalIndexImagen1" class="modal-body">
                            <h2>Comparte el estado del clima de Cali!</h2>
                            <hr class="star-primary">
                            <!-- pulido -->
                            <img src="././resources/img/portfolio/umbrella.png" class="img-responsive img-centered" alt="">
                            <p>�C�mo se encuentra el estado climatico de tu ubicaci�n?</p>
                            <img id = "imagenClima1" class="img-responsive img-centered" src="././resources/img/perfil/no-weather.png" alt="clima">
                            <ul class="list-inline item-details">
                                <strong>Autor:</strong>
                            	<li>
                            	t� (regi�strate)
                                </li>
                                
                                <strong>Fecha:</strong>
                                <li>
                                hoy
                                </li>
                            </ul>
                            <button type="button" class="btn btn-default" data-dismiss="modal"><i class="fa fa-times"></i> Close</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="portfolio-modal modal fade" id="portfolioModal3" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-content">
            <div class="close-modal" data-dismiss="modal">
                <div class="lr">
                    <div class="rl">
                    </div>
                </div>
            </div>
            <div class="container">
                <div class="row">
                    <div class="col-lg-8 col-lg-offset-2">
                        <div id = "modalIndexImagen2" class="modal-body">
                            <h2>Comparte el estado del clima de Cali!</h2>
                            <hr class="star-primary">
                            <!-- pulido -->
                            <img src="././resources/img/portfolio/umbrella.png" class="img-responsive img-centered" alt="">
                            <p>�C�mo se encuentra el estado climatico de tu ubicaci�n?</p>
                            <img id = "imagenClima2" class="img-responsive img-centered" src="././resources/img/perfil/no-weather.png" alt="clima">
                            <ul class="list-inline item-details">
                                <strong>Autor:</strong>
                            	<li>
                            	t� (regi�strate)
                                </li>
                                
                                 
                                <strong>Fecha:</strong>
                                <li>
                                hoy
                                </li>
                            </ul>
                            <button type="button" class="btn btn-default" data-dismiss="modal"><i class="fa fa-times"></i> Close</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="portfolio-modal modal fade" id="portfolioModal4" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-content">
            <div class="close-modal" data-dismiss="modal">
                <div class="lr">
                    <div class="rl">
                    </div>
                </div>
            </div>
            <div class="container">
                <div class="row">
                    <div class="col-lg-8 col-lg-offset-2">
                        <div id = "modalIndexImagen3" class="modal-body">
                            <h2>Comparte el estado del clima de Cali!</h2>
                            <hr class="star-primary">
                            <!-- pulido -->
                            <img src="././resources/img/portfolio/umbrella.png" class="img-responsive img-centered" alt="">
                            <p>�C�mo se encuentra el estado climatico de tu ubicaci�n?</p>
                            <img id = "imagenClima3" class="img-responsive img-centered" src="././resources/img/perfil/no-weather.png" alt="clima">
                            <ul class="list-inline item-details">
                                <strong>Autor:</strong>
                            	<li>
                            	t� (regi�strate)
                                </li>
                                
                                 
                                <strong>Fecha:</strong>
                                <li>
                                hoy
                                </li>
                            </ul>
                            <button type="button" class="btn btn-default" data-dismiss="modal"><i class="fa fa-times"></i> Close</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="portfolio-modal modal fade" id="portfolioModal5" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-content">
            <div class="close-modal" data-dismiss="modal">
                <div class="lr">
                    <div class="rl">
                    </div>
                </div>
            </div>
            <div class="container">
                <div class="row">
                    <div class="col-lg-8 col-lg-offset-2">
                        <div id = "modalIndexImagen4" class="modal-body">
                            <h2>Comparte el estado del clima de Cali!</h2>
                            <hr class="star-primary">
                            <!-- pulido -->
                            <img src="././resources/img/portfolio/umbrella.png" class="img-responsive img-centered" alt="">
                            <p>�C�mo se encuentra el estado climatico de tu ubicaci�n?</p>
                            <img id = "imagenClima4" class="img-responsive img-centered" src="././resources/img/perfil/no-weather.png" alt="clima">
                            <ul class="list-inline item-details">
                                <strong>Autor:</strong>
                            	<li>
                            	t� (regi�strate)
                                </li>
                                
                                 
                                <strong>Fecha:</strong>
                                <li>
                                hoy
                                </li>
                            </ul>
                            <button type="button" class="btn btn-default" data-dismiss="modal"><i class="fa fa-times"></i> Close</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="portfolio-modal modal fade" id="portfolioModal6" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-content">
            <div class="close-modal" data-dismiss="modal">
                <div class="lr">
                    <div class="rl">
                    </div>
                </div>
            </div>
            <div class="container">
                <div class="row">
                    <div id = "modalIndexImagen5" class="col-lg-8 col-lg-offset-2">
                        <div class="modal-body">
                            <h2>Comparte el estado del clima de Cali!</h2>
                            <hr class="star-primary">
                            <!-- pulido -->
                            <img src="././resources/img/portfolio/umbrella.png" class="img-responsive img-centered" alt="">
                            <p>�C�mo se encuentra el estado climatico de tu ubicaci�n?</p>
                            <img id = "imagenClima5" class="img-responsive img-centered" src="././resources/img/perfil/no-weather.png" alt="clima">
                            <ul class="list-inline item-details">
                                <strong>Autor:</strong>
                            	<li>
                            	t� (regi�strate)
                                </li>
                                
                                 
                                <strong>Fecha:</strong>
                                <li>
                                hoy
                                </li>
                            </ul>
                            <button type="button" class="btn btn-default" data-dismiss="modal"><i class="fa fa-times"></i> Close</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <!-- jQuery -->
    <script src="././resources/jquery/jquery.min.js"></script>    
    

    <!-- Bootstrap Core JavaScript -->
    <script src="././resources/js/bootstrap.min.js"></script>
   <!-- <script src="././resources/js/validator.js"></script> -->
   
   
    <!--bootstrapValidator validador campos! // -->
	   <script src="//oss.maxcdn.com/jquery.bootstrapvalidator/0.5.3/js/bootstrapValidator.min.js"></script>
       <script src="././resources/js/jqBootstrapValidation.js"></script>
   
   

    <!-- Plugin JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>

    <!-- Contact Form JavaScript -->
    
    <script src="././resources/js/contact_me.js"></script>

    <!-- Theme JavaScript -->
    <script src="././resources/js/freelancer.min.js"></script>
    
    <!-- <script src="././resources/js/validaciones.js"></script>-->

</body>
</html>