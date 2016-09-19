<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Market service</title>

        <!-- CSS -->
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,600">        
        <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/font-awesome/css/font-awesome.min.css">
        <link rel="stylesheet" href="assets/css/animate.css">
		<link rel="stylesheet" href="assets/css/form-elements.css">
        <link rel="stylesheet" href="assets/css/style.css">
        <link rel="stylesheet" href="assets/css/media-queries.css">

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->

        <!-- Favicon and touch icons -->
        <link rel="shortcut icon" href="assets/ico/favicon.png">
        <link rel="apple-touch-icon-precomposed" sizes="144x144" href="assets/ico/apple-touch-icon-144-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="114x114" href="assets/ico/apple-touch-icon-114-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="72x72" href="assets/ico/apple-touch-icon-72-precomposed.png">
        <link rel="apple-touch-icon-precomposed" href="assets/ico/apple-touch-icon-57-precomposed.png">

    </head>

    <body>
    
        <!-- Loader -->
    	<div class="loader">
    		<div class="loader-img"></div>
    	</div>

		<!-- Top menu -->
		<nav class="navbar navbar-inverse navbar-fixed-top navbar-no-bg" role="navigation">
			<div class="container">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#top-navbar-1">
						<span class="sr-only">Toggle navigation</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
				</div>
				<!-- Collect the nav links, forms, and other content for toggling -->
				<div class="collapse navbar-collapse" id="top-navbar-1">
					<ul class="nav navbar-nav">
						<li><a class="scroll-link" href="#top-content">Top</a></li>
						<li><a class="scroll-link" href="#services">Services</a></li>
						<li><a class="scroll-link" href="#testimonials">Clients</a></li>
						<li><a class="scroll-link" href="#footer">Contact</a></li>
					</ul>
					<div class="navbar-text navbar-right">
						<a href="#"><i class="fa fa-facebook"></i></a>
						<a href="#"><i class="fa fa-dribbble"></i></a>
						<a href="#"><i class="fa fa-twitter"></i></a>
						<a href="#"><i class="fa fa-instagram"></i></a>
						<a href="#"><i class="fa fa-pinterest"></i></a>
					</div>
				</div>
			</div>
		</nav>

        <!-- Top content -->
        <div class="top-content">
        	
            <div class="inner-bg">
                <div class="container">
                	
                    <div class="row">
                        <div class="col-sm-8 col-sm-offset-2 text">
                        	<div class="logo wow fadeInDown">
                        		<a href="index.jsp"></a>
                        	</div>
                            <h1 class="wow fadeInLeftBig">Try our market service</h1>
                            <div class="description wow fadeInLeftBig">
                            	<p>
									The online store is the website which sells goods on the Internet.
									The online store gives to users the chance to create the order,
									to choose a convenient payment method and deliveries of the order.
									Online stores can be specialized (to sell only specific type of goods or services) or universal.
                            	</p>
                            </div>
                            <div class="top-big-link wow fadeInUp">
                            	<a class="btn btn-link-1" href="#">Sign up</a>
                            	<a class="btn btn-link-2 scroll-link" href="#services">Learn more</a>
                            </div>
                        </div>
                    </div>
                    
                </div>
            </div>
            
        </div>
        
        <!-- Services -->
        <div class="services-container section-container">
	        <div class="container">
	            <div class="row">
	                <div class="col-sm-12 services section-description wow fadeIn">
	                    <h2>Our services</h2>
	                    <div class="divider-1 wow fadeInUp"><span></span></div>
	                </div>
	            </div>
	            <div class="row">
                	<div class="col-sm-4 services-box wow fadeInUp">
                		<div class="row">
                			<div class="col-sm-4">
			                	<div class="services-box-icon">
			                		<i class="fa fa-magic"></i>
			                	</div>
		                	</div>
	                		<div class="col-sm-8">
	                    		<h3>Web-Service</h3>
	                    		<p>The audience of online stores constantly extends.
									It is connected with fixed increase in Internet users,
									and also with increase in number of online stores
									that gives the chance to purchase on the Internet practically any goods or service.</p>
	                    	</div>
	                    </div>
                    </div>
                    <div class="col-sm-4 services-box wow fadeInDown">
	                	<div class="row">
                			<div class="col-sm-4">
			                	<div class="services-box-icon">
			                		<i class="fa fa-cog"></i>
			                	</div>
		                	</div>
	                		<div class="col-sm-8">
	                    		<h3>Audience</h3>
	                    		<p>The audience of online stores constantly extends also
									thanks to benefits which shop online the most convenient.</p>
	                    	</div>
	                    </div>
                    </div>
                    <div class="col-sm-4 services-box wow fadeInUp">
	                	<div class="row">
                			<div class="col-sm-4">
			                	<div class="services-box-icon">
			                		<i class="fa fa-twitter"></i>
			                	</div>
		                	</div>
	                		<div class="col-sm-8">
	                    		<h3>Social media</h3>
	                    		<p>Dear clients and partners since recent time we appeared
									on social networks. This step is proved by desire of the
									Company to become more and more transparent and to
									conduct online dialogue with all who needs our professional help.</p>
	                    	</div>
	                    </div>
                    </div>
	            </div>
	        </div>
        </div>

		<!-- Call to action -->
		<div class="call-to-action-container section-container section-container-image-bg">
			<div class="container">
				<div class="row">
					<div class="col-sm-12 call-to-action section-description wow fadeInLeftBig">
						<p>
							We work only with reliable suppliers.
							We value the reputation and we offer our clients only really quality goods.
						</p>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-12 section-bottom-button wow fadeInUp">
						<a class="btn btn-link-1 scroll-link" href="#top-content">Try it!</a>
					</div>
				</div>
			</div>
		</div>

		<!-- More services -->
        <div class="more-services-container section-container">
	        <div class="container">
	        	
	            <div class="row">
	                <div class="col-sm-12 more-services section-description wow fadeIn">
	                    <h2>Why us?</h2>
	                    <div class="divider-1 wow fadeInUp"><span></span></div>
	                </div>
	            </div>
	            
	            <div class="row">
	                <div class="col-sm-6 more-services-box wow fadeInLeft">
	                	<div class="row">
	                		<div class="col-sm-3">
	                			<div class="more-services-box-icon">
	                				<i class="fa fa-paperclip"></i>
	                			</div>
	                		</div>
	                		<div class="col-sm-9">
	                			<h3>Convenient for customers</h3>
		                    	<p>
		                    		Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et.
		                    		Ut wisi enim ad minim veniam, quis nostrud.
		                    	</p>
	                		</div>
	                	</div>
	                </div>
	                <div class="col-sm-6 more-services-box wow fadeInLeft">
	                	<div class="row">
	                		<div class="col-sm-3">
	                			<div class="more-services-box-icon">
	                				<i class="fa fa-pencil"></i>
	                			</div>
	                		</div>
	                		<div class="col-sm-9">
	                			<h3>Convenient for suppliers</h3>
		                    	<p>
		                    		Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et.
		                    		Ut wisi enim ad minim veniam, quis nostrud.
		                    	</p>
	                		</div>
	                	</div>
	                </div>
	            </div>
	            
	            <div class="row">
	                <div class="col-sm-6 more-services-box wow fadeInLeft">
	                	<div class="row">
	                		<div class="col-sm-3">
	                			<div class="more-services-box-icon">
	                				<i class="fa fa-cloud"></i>
	                			</div>
	                		</div>
	                		<div class="col-sm-9">
	                			<h3>Securely</h3>
		                    	<p>
		                    		Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et.
		                    		Ut wisi enim ad minim veniam, quis nostrud.
		                    	</p>
	                		</div>
	                	</div>
	                </div>
	                <div class="col-sm-6 more-services-box wow fadeInLeft">
	                	<div class="row">
	                		<div class="col-sm-3">
	                			<div class="more-services-box-icon">
	                				<i class="fa fa-google"></i>
	                			</div>
	                		</div>
	                		<div class="col-sm-9">
	                			<h3>Simple</h3>
		                    	<p>
		                    		Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et.
		                    		Ut wisi enim ad minim veniam, quis nostrud.
		                    	</p>
	                		</div>
	                	</div>
	                </div>
	            </div>

	        </div>
        </div>

        <!-- Testimonials -->
        <div class="testimonials-container section-container section-container-image-bg">
	        <div class="container">
	            <div class="row">
	                <div class="col-sm-12 testimonials section-description wow fadeIn">
	                </div>
	            </div>
	            <div class="row">
	                <div class="col-sm-10 col-sm-offset-1 testimonial-list wow fadeInUp">
	                	<div role="tabpanel">
	                		<!-- Tab panes -->
	                		<div class="tab-content">
	                			<div role="tabpanel" class="tab-pane fade in active" id="tab1">
	                				<div class="testimonial-image">
	                					<img src="assets/img/testimonials/1.jpg" alt="" data-at2x="assets/img/testimonials/1.jpg">
	                				</div>
	                				<div class="testimonial-text">
		                                <p>
		                                	"Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et. 
		                                	Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et. 
		                                	Lorem ipsum dolor sit amet, consectetur..."<br>
		                                	<a href="#">marketservice</a>
		                                </p>
	                                </div>
	                			</div>
	                			<div role="tabpanel" class="tab-pane fade" id="tab2">
	                				<div class="testimonial-image">
	                					<img src="assets/img/testimonials/2.jpg" alt="" data-at2x="assets/img/testimonials/2.jpg">
	                				</div>
	                				<div class="testimonial-text">
		                                <p>
		                                	"Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip 
		                                	ex ea commodo consequat. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit 
		                                	lobortis nisl ut aliquip ex ea commodo consequat..."<br>
		                                	<a href="#">marketservice</a>
		                                </p>
	                                </div>
	                			</div>
	                			<div role="tabpanel" class="tab-pane fade" id="tab3">
	                				<div class="testimonial-image">
	                					<img src="assets/img/testimonials/3.jpg" alt="" data-at2x="assets/img/testimonials/3.jpg">
	                				</div>
	                				<div class="testimonial-text">
		                                <p>
		                                	"Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et. 
		                                	Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et. 
		                                	Lorem ipsum dolor sit amet, consectetur..."<br>
		                                	<a href="#">marketservice</a>
		                                </p>
	                                </div>
	                			</div>
	                			<div role="tabpanel" class="tab-pane fade" id="tab4">
	                				<div class="testimonial-image">
	                					<img src="assets/img/testimonials/4.jpg" alt="" data-at2x="assets/img/testimonials/4.jpg">
	                				</div>
	                				<div class="testimonial-text">
		                                <p>
		                                	"Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip 
		                                	ex ea commodo consequat. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit 
		                                	lobortis nisl ut aliquip ex ea commodo consequat..."<br>
		                                	<a href="#">marketservice</a>
		                                </p>
	                                </div>
	                			</div>
	                		</div>
	                		<!-- Nav tabs -->
	                		<ul class="nav nav-tabs" role="tablist">
	                			<li role="presentation" class="active">
	                				<a href="#tab1" aria-controls="tab1" role="tab" data-toggle="tab"></a>
	                			</li>
	                			<li role="presentation">
	                				<a href="#tab2" aria-controls="tab2" role="tab" data-toggle="tab"></a>
	                			</li>
	                			<li role="presentation">
	                				<a href="#tab3" aria-controls="tab3" role="tab" data-toggle="tab"></a>
	                			</li>
	                			<li role="presentation">
	                				<a href="#tab4" aria-controls="tab4" role="tab" data-toggle="tab"></a>
	                			</li>
	                		</ul>
	                	</div>
	                </div>
	            </div>
	        </div>
        </div>

        <!-- Footer -->
        <footer class="footer-container">
	        <div class="container">
	        	<div class="row">

                    Developed by Pushnov team. All rights reserved.
                    
                </div>
	        </div>
	        
	        <div class="container-fluid">
	        	<div class="row">
                	<div class="col-sm-12 footer-bottom">
                		<a class="scroll-link" href="#top-content"><i class="fa fa-chevron-up"></i></a>
                	</div>
                </div>
	        </div>
        </footer>


        <!-- Javascript -->
        <script src="assets/js/jquery-1.11.1.min.js"></script>
        <script src="assets/bootstrap/js/bootstrap.min.js"></script>
        <script src="assets/js/jquery.backstretch.min.js"></script>
        <script src="assets/js/wow.min.js"></script>
        <script src="assets/js/retina-1.1.0.min.js"></script>
        <script src="assets/js/waypoints.min.js"></script>
        <script src="assets/js/scripts.js"></script>
        
        <!--[if lt IE 10]>
            <script src="assets/js/placeholder.js"></script>
        <![endif]-->

    </body>

</html>