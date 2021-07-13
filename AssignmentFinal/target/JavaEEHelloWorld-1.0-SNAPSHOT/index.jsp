<%@ page import="com.WebFlexers.servlets.LoginServlet" %>
<!--Template: W3layouts
Template URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zxx">
<head>
    <title>Doctor Pro | Make an appointment now</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="utf-8" />
    <script>
        addEventListener("load", function () {
            setTimeout(hideURLbar, 0);
        }, false);

        function hideURLbar() {
            window.scrollTo(0, 1);
        }
    </script>
    <!-- Custom Theme files -->
    <link href="css/bootstrap.css" type="text/css" rel="stylesheet" media="all">
    <link href="css/style.css" type="text/css" rel="stylesheet" media="all">
    <!-- font-awesome icons -->
    <link href="css/fontawesome-all.min.css" rel="stylesheet">
    <!-- Custom Theme files -->
	<link href="css/minimal-slider.css" rel='stylesheet' type='text/css' />
	<link rel="stylesheet" href="css/flexslider.css" type="text/css" media="screen" property="" />
    <!-- online-fonts -->
	<link href="//fonts.googleapis.com/css?family=Lato:400,700" rel="stylesheet">
    <link href="//fonts.googleapis.com/css?family=Montserrat:200,300,400,500,600,700,800,900" rel="stylesheet"><!-- //online-fonts -->
</head>
<body>
<%
	if (LoginServlet.getLoggedIn() == null)
		LoginServlet.setLoggedIn(false);
	if (LoginServlet.getLoggedIn())
		request.setAttribute("whoLoggedIn",LoginServlet.getWhoLoggedIn());
	request.setAttribute("isLoggedin",LoginServlet.getLoggedIn()); //this commands runs on index.jsp loading and checks if a user is logged in
%>
        <!-- header -->
        <header>
            <nav class="navbar navbar-expand-lg navbar-light bg-gradient-secondary">
                <h1>
                    <a class="navbar-brand text-white" href="index.jsp">
                       Doctor Pro
                    </a>
                </h1>
                <button class="navbar-toggler ml-md-auto" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                    aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav ml-lg-auto text-center">

                        <li class="nav-item active  mr-3 mt-lg-0 mt-3">
                            <a class="nav-link" href="index.jsp">Home
                                <span class="sr-only">(current)</span>
                            </a>
                        </li>

                        <li class="nav-item mr-3 mt-lg-0 mt-3">
                            <a class="nav-link" href="gallery.jsp">Gallery</a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link" href="contact.jsp">Contact</a>
                        </li>

						<li class="nav-item">
							<a class="nav-link" href="about.jsp">About</a>
						</li>
						//
						<%
							boolean isLoggedin = (boolean)request.getAttribute("isLoggedin");
							if(!isLoggedin){
								out.println("<li>");
								out.println("<button type=\"submit\" name=\"loginButton\"  data-toggle=\"modal\" value=\"login\"  data-target=\"#loginModal\"class=\"btn  ml-lg-2 w3ls-btn\">");
								out.println("Login");
								out.println("</button>");
								out.println("</li>");}
							else {
								out.println("<li class=\"nav-item dropdown mr-3 mt-lg-0 mt-3\">");
								out.println("<a class=\"nav-link dropdown-toggle\" href=\"#\" id=\"navbarDropdown\" role=\"button\" data-toggle=\"dropdown\" aria-haspopup=\"true\"\n" +
										"\t\t\t\t\t\t\t   aria-expanded=\"false\">");
								out.println("Account");
								out.println("</a>");
								out.println("\t<div class=\"dropdown-menu\" aria-labelledby=\"navbarDropdown\">");
								out.println("<div class=\"dropdown-divider\"></div>");
								String whoLoggedIn = (String)request.getAttribute("whoLoggedIn");
								if(whoLoggedIn.equals("admin"))
								{
									if(session.getAttribute("IsSuperUser").equals("true"))
										out.println("<a class=\"dropdown-item\" href=\"profile_admin_superuser.jsp\">Profile</a>");
									else
										out.println("<a class=\"dropdown-item\" href=\"profile_admin.jsp\">Profile</a>");
								}
								else if(whoLoggedIn.equals("doctor"))
									out.println("<a class=\"dropdown-item\" href=\"profile_doctor.jsp\">Profile</a>");
								else
									out.println("<a class=\"dropdown-item\" href=\"profile_patient.jsp\">Profile</a>");
								out.println("\t<div class=\"dropdown-divider\"></div>");
								out.println("<a class=\"dropdown-item\" href=\"logout-servlet\">Logout</a>");
								out.println("</div>");
								out.println("</li>");
							}

						%>

                    </ul>
                </div>
            </nav>
        </header>

 <!-- main image slider container -->
        <div class="slide-window">
            <div class="slide-wrapper" style="width:300%;">
				<!-- Slide 1 -->
                <div class="slide">
                    <div class="slide-caption text-center">
                       <h3 class="text-uppercase">Welcome to Doctor Pro <br><span style>Login to make an appointment</span></h3>
                    </div>
					<!-- Login button -->
					<div class="row">
						<div class="col text-center">
							<button class="btn btn-agile w-25" data-toggle="modal" aria-pressed="false" data-target="#loginModal">Login</button>
						</div>
					</div>
                </div>
				<!-- Slide 2 -->
                <div class="slide slide2">
                    <div class="slide-caption text-center">
                        <h3 class="text-uppercase">Individual approach to <span>Each Patient </span></h3>
                    </div>
					<!-- Login button -->
					<div class="row">
						<div class="col text-center">
							<button class="btn btn-agile w-25" data-toggle="modal" aria-pressed="false" data-target="#loginModal">Login</button>
						</div>
					</div>
                </div>
				<!-- Slide 3 -->
                <div class="slide slide3">
                    <div class="slide-caption text-center">
                        <h3 class="text-uppercase">Committed to excellence <span>Medical Clinic</span></h3>
                    </div>
					<!-- Login button -->
					<div class="row">
						<div class="col text-center">
							<button class="btn btn-agile w-25" data-toggle="modal" aria-pressed="false" data-target="#loginModal">Login</button>
						</div>
					</div>
                </div>
            </div>



            <div class="slide-controller">
                <div class="slide-control-left">
                    <div class="slide-control-line"></div>
                    <div class="slide-control-line"></div>
                </div>
                <div class="slide-control-right">
                    <div class="slide-control-line"></div>
                    <div class="slide-control-line"></div>
                </div>
            </div>
        </div>
        <!-- main image slider container -->
    <!-- end of main image slider container -->
		<!--about-->
	<div class="about">
	<div class="abt-layer">
		<div class="container">
			<div class="about-main">
				<div class="about-right">
					<h3 class="subheading-w3-agile">Make an Appointment</h3>
					<!-- stats -->
					<div class="stats">
						<div class="stats_inner">
							<form action="#" method="post">
								 <input class="form-control mb-3" type="text" placeholder="Name" name="name" required="" disabled>
								<select class="form-control mb-3" disabled>
									<option value="0">Gender</option>
									<option value="1">Male</option>
									<option value="2">Female</option>
								</select>
								<input class="form-control mb-3" type="text" placeholder="Phone" name="phone" required="" disabled>
								<input class="form-control mb-3" type="email" placeholder="E-mail" name="email" required="" disabled>
								<select name="selectDepartment" class="form-control mb-3">
									<option value="0">Select Department</option>
									<option value="1">Pediatrics</option>
									<option value="2">Ophthalmology</option>
									<option value="3">General Internal Medicine</option>
								</select>
								<input class="form-control date mb-3" id="datepicker" name="Text" placeholder="Select Date"  type="text" required="">
								<button name="buttonMakeAppointment" type="submit" class="btn btn-agile btn-block w-100" disabled>Make An Appointment</button>
							</form>
						</div>
					</div>
					<!-- //stats -->

				</div>
			</div>
			
		</div>
		</div>
	</div>
	<!--//about-->
	<section class="departments">
		<div class="departments-1 py-lg-5">
			<div class="container py-5">
				<div class="text-center wthree-title pb-sm-5 pb-3">
					<h3 class="w3l-sub text-white">Departments</h3>
				</div>
					<div class="row py-lg-5">
								<div class="col-sm-4 mt-4 w3-ab">
									<div class="w3-ab-grid text-center">
										<div class="w3-aicon  p-4">
											<i class="fas fa-stethoscope" aria-hidden="true"></i>
											<h4 class="my-3 text-capitalize text-white">General Internal Medicine</h4>
										</div>
									</div>
								</div>
								<div class="col-sm-4 mt-4 w3-ab">
									<div class="w3-ab-grid text-center">
										<div class="w3-aicon p-4">
											<i class="fas fa-eye" aria-hidden="true"></i>
											<h4 class="my-3 text-capitalize text-white">Ophthalmology</h4>
										</div>
									</div>
								</div>
								<div class="col-sm-4 mt-4 w3-ab">
									<div class="w3-ab-grid text-center">
										<div class="w3-aicon p-4">
											<i class="fa fa-ambulance" aria-hidden="true"></i>
											<h4 class="my-3 text-capitalize text-white">Pediatrics</h4>
										</div>
									</div>
								</div>
					</div>
			</div>
		</div>
	</section>

	<!-- testimonials -->
	<div class="testimonials py-lg-5">
		<div class="container py-5">
			<div class="text-center wthree-title pb-sm-5 pb-3">
				<h3 class="w3l-sub">What Our Clients Say</h3>
			</div>
			<div class="w3_testimonials_grids">
				<section class="slider">
					<div class="flexslider">
						<ul class="slides">
							<li>
								<div class="w3_testimonials_grid">
									<p>Doctor Kontouris is one of the best doctors I have ever visited! He is very friendly
									   and he knows his stuff. The only thing that I didn't like is that he kept promoting
									   a game called Dark Souls, but in general a great doctor. I highly recommend him</p>
									<ul class="testi-w3ls-rate mt-4">
										<li>
											<i class="fas fa-star"></i>
										</li>
										<li class="mx-2">
											<i class="fas fa-star"></i>
										</li>
										<li>
											<i class="fas fa-star"></i>
										</li>
										<li class="mx-2">
											<i class="fas fa-star"></i>
										</li>
									</ul>
									<div class="row person-w3ls-testi mt-5">
										<div class="col-6 agile-left-test text-right">
											<img src="images/doctorLefteris.png" alt=" " class="img-fluid rounded-circle" />
										</div>
										<div class="col-6 agile-right-test text-left mt-4">
											<h5>Lefteris Kontouris</h5>
											<p>Internist</p>
										</div>
									</div>
								</div>
							</li>
							<li>
								<div class="w3_testimonials_grid">
									<p>"Dr. Michalis Stylianidis is a great ophthalmologist.
										He made me feel very comfortable during the examination!
										The only downside was that he couldn't stop talking about revolut payment app
										and why I should use his link to register so I can get a free card delivery"</p>
									<ul class="testi-w3ls-rate mt-4">
										<li>
											<i class="fas fa-star"></i>
										</li>
										<li class="mx-2">
											<i class="fas fa-star"></i>
										</li>
										<li>
											<i class="fas fa-star"></i>
										</li>
										<li class="mx-2">
											<i class="fas fa-star"></i>
										</li>
										<li>
											<i class="fas fa-star"></i>
										</li>
									</ul>
									<div class="row person-w3ls-testi mt-5">
										<div class="col-6 agile-left-test text-right">
											<img src="images/doctorMichalis.jpg" alt=" " class="img-fluid rounded-circle" />
										</div>
										<div class="col-6 agile-right-test text-left mt-4">
											<h5>Michail Stylianidis</h5>
											<p>Ophthalmologist</p>
										</div>
									</div>
								</div>
							</li>
							<li>
								<div class="w3_testimonials_grid">
									<p> Doctor Kalogeropoulos is a very friendly doctor and he caught my attention instantly!
										He is always up to date with the latest technologies and he is ready to use them when necessary.
										However I was caught off guard, when he said he is only accepting payments in bitcoin or ethereum!
										Thankfully I had my wallet address ready to go, but since not everyone has crypto, maybe he should
										mention that beforehand.
									</p>
									<ul class="testi-w3ls-rate mt-4">
										<li>
											<i class="fas fa-star"></i>
										</li>
										<li class="mx-2">
											<i class="fas fa-star"></i>
										</li>
										<li>
											<i class="fas fa-star"></i>
										</li>
										<li class="mx-2">
											<i class="fas fa-star"></i>
										</li>
									</ul>
									<div class="row person-w3ls-testi mt-5">
										<div class="col-6 agile-left-test text-right">
											<img src="images/doctorKostas.png" alt=" " class="img-fluid rounded-circle" />
										</div>
										<div class="col-6 agile-right-test text-left mt-4">
											<h5>Kostas Kalogeropoulos</h5>
											<p>Pediatric</p>
										</div>
									</div>
								</div>
							</li>
						</ul>
					</div>
				</section>

			</div>
		</div>
	</div>
	<!-- //testimonials -->

<!-- choose -->
	<section class="choose">
		<div class="choose-1 py-5">
			<div class="container py-md-4 mt-md-3"> 
				<div class="row inner_w3l_agile_grids-1 ">
					<div class="col-lg-7 w3layouts_choose_left_grid1">
						<div class="choose_top">
							<h3 class="w3l_header text-white">Still Have Questions?</h3>
							<h4 class="mb-3 mt-3 text-white">Feel Free to Contact About Clinic Directly</h4>
							<p class="text-white">We are happy to help you with any question or issue you may have.
												  Just contact us by sending a mail and we will get back at you as soon as we can!</p>
							<a href="about.jsp" class="btn btn-primary mt-3">Read More</a>
						</div>
					</div>
				</div>
			</div> 
		</div>
</section>
<!-- //choose -->

<!-- case studies -->
	<section class="case_w3ls  py-lg-5">
		<div class="container py-5">
			<div class="text-center wthree-title pb-sm-5 pb-3">
				<h3 class="w3l-sub">Medical Services</h3>
				<h4 class="sub-title py-3">Because health is above all!</h4>
			</div>
			<!-- case studies row -->
			<div class="row">
				<div class="col-lg-4 col-md-6">
					<div class="img1 img-grid  d-flex align-items-end justify-content-center p-3">
						<div class="img_text_w3ls">
							<h4>Emergency Help</h4>
							<span> </span>
							<p>We are ready to provide help in times of need </p>
						</div>
					</div>
				</div>
				<div class="col-lg-4 col-md-6 my-md-0 mt-4">
					<div class="img1 img2 img-grid  d-flex align-items-end justify-content-center p-3">
						<div class="img_text_w3ls">
							<h4>Heart Surgery</h4>
							<span> </span>
							<p>Performed by the most qualified doctors in the globe </p>
						</div>
					</div>
				</div>
				<div class="col-lg-4 col-md-6 my-lg-0 mt-4">
					<div class="img1 img3 img-grid  d-flex align-items-end justify-content-center p-3">
						<div class="img_text_w3ls">
							<h4>Emergency Care</h4>
							<span> </span>
							<p>Our stuff is always on vigilant and ready to settle emergencies </p>
						</div>
					</div>
				</div>
				<div class="col-lg-4 col-md-6 mt-4">
					<div class="img1 img5 img-grid  d-flex align-items-end justify-content-center p-3">
						<div class="img_text_w3ls">
							<h4>Dental Care</h4>
							<span> </span>
							<p>For a healthy and shinny smile </p>
						</div>
					</div>
				</div>
				<div class="col-lg-4 col-md-6  mt-4">
					<div class="img1 img4 img-grid  d-flex align-items-end justify-content-center p-3">
						<div class="img_text_w3ls">
							<h4>Individual Approach</h4>
							<span> </span>
							<p>We make sure each patient is treated for as long as they need to </p>
						</div>
					</div>
				</div>
				<div class="col-lg-4 col-md-6 mt-4">
					<div class="img1 img6 img-grid  d-flex align-items-end justify-content-center p-3">
						<div class="img_text_w3ls">
							<h4>Qualified Doctors</h4>
							<span> </span>
							<p>Our doctors are carefully selected by very strict criteria </p>
						</div>
					</div>
				</div>
			</div>
			<!-- //case studies row -->
		</div>
	</section>
	<!-- //case studies -->
<!-- footer -->
	<footer class="py-sm-5">
		<div class="container">
			<div class="row py-5">
				<!-- footer grid1 -->
				<div class="col-lg-3 col-sm-6 fv5-contact">
					<h2 class="mb-4">Contact Us</h2>
					<div class="fv3-contact mt-3">
					
						<span class="fas fa-home mr-2"></span>
						<p>90 Street, City, State 34789.</p>
					</div>
					<div class="fv3-contact mt-3">
						<span class="fas fa-phone-volume mr-2"></span>
						<p>+456 123 7890</p>
					</div>
					<div class="fv3-contact mt-3">
						<span class="fas fa-envelope-open mr-2"></span>
						<a href="mailto:doctorpro@protonmail.com" class="text-secondary">doctorpro@protonmail.com</a>
					</div>
				</div>
				<!-- //footer grid1 -->
				<!-- footer grid2 -->
				<div class="col-lg-3  col-sm-6 footv3-left text-lg-center my-sm-0 mt-5">
					<h3 class="mb-4">Opening Hours</h3>
					<ul class="list-agileits">
						<li>
							Mon to Fri ------ 09:00-17:00
						</li>
						<li class="my-3">
							Saturday -------- 09:30-17:00
						</li>
						<li class="mb-3">
							Sunday ---------- 10:30-18:00
						</li>
						
					</ul>
				</div>
				<!-- //footer grid2 -->
				<!-- footer grid3 -->
				<div class="col-lg-3  col-sm-6 footv3-left text-lg-center my-lg-0 mt-sm-5 mt-5">
					<h3 class="mb-4">Navigation</h3>
					<ul class="list-agileits">
						<li>
							<a href="index.jsp">
								Home
							</a>
						</li>
						<li class="my-3">
							<a href="about.jsp">
								About Us
							</a>
						</li>
						<li class="mb-3">
							<a href="services.jsp">
								Services
							</a>
						</li>
						<li>
							<a href="contact.jsp">
								Contact Us
							</a>
						</li>
					</ul>
				</div>
				<!-- //footer grid3 -->
				<!-- footer grid4  -->
				<div class="col-lg-3  col-sm-6 footv3-left my-lg-0 mt-5">
					<h3 class="mb-4">subscribe to newsletter</h3>
					<form action="#" method="post">
						<div class="form-group">
							<input type="email" class="form-control  bg-dark border-0" id="email" placeholder="Enter email" name="email" required>
						</div>
						<div class="form-group">
							<input type="Submit" class="form-control bg-secondary text-white border-0" id="sub" value="submit" name="sub">
						</div>
					</form>
				</div>
				<!-- //footer grid4 -->
			</div>
			<div class="cpy-right text-center  pt-3">
				
				<div class="copyrightbottom">
					<p class="text-secondary">© 2021 WebFlexers. All rights reserved | Design by
						<a href="http://w3layouts.com" class="text-white"> W3layouts.</a>
					</p>
				</div>
				<div class="copyrighttop">
					<ul>
						<li>
							<h4>Follow us on:</h4>
						</li>
						<li>
							<a class="facebook" href="#">
								<i class="fab fa-facebook-f"></i>
							</a>
						</li>
						<li>
							<a class="facebook" href="#">
								<i class="fab fa-twitter"></i>
							</a>
						</li>
						<li>
							<a class="facebook" href="#">
								<i class="fab fa-google-plus-g"></i>
							</a>
						</li>
						<li>
							<a class="facebook" href="#">
								<i class="fab fa-pinterest-p"></i>
							</a>
						</li>
					</ul>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
		<!-- //footer container -->
	</footer>
	<!-- //footer -->

	<!-- login  -->
    <div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Login</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form action="login-servlet" method="post">
                        <div class="form-group">
                            <label for="recipient-name" class="col-form-label">Username</label>
                            <input type="text" class="form-control" placeholder=" " name="username" id="recipient-name" required="">
                        </div>
                        <div class="form-group">
                            <label for="password" class="col-form-label">Password</label>
                            <input type="password" class="form-control" placeholder=" " name="password" id="password" required="">
                        </div>
						<%
							String login_msg = (String)request.getAttribute("error");
							if(login_msg != null) {
								out.println("<font color=red size=4px>"+login_msg+"</font>");
							}
						%>
                        <div class="right-w3l">
                            <input type="submit" class="form-control" value="Login">
                        </div>
                        <div class="row sub-w3l my-3">
                            <div class="col sub-agile">
                                <input type="checkbox" id="brand1" value="">
                                <label for="brand1" class="text-secondary">
                                    <span></span>Remember me?</label>
                            </div>
                            <div class="col forgot-w3l text-right">
                                <a href="#" class="text-secondary">Forgot Password?</a>
                            </div>
                        </div>
                        <p class="text-center dont-do">Don't have an account?
                            <a href="#" data-toggle="modal" data-target="#registerPage" class="text-dark font-weight-bold">
                                Register Now</a>
                        </p>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <!-- //login -->

	<!--/Register Page-->
	<div class="modal fade" id="registerPage" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header text-center">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>

				<div class="modal-body">
					<div class="login px-4 mx-auto mw-100">
						<h5 class="modal-title text-center text-dark mb-4">Choose your account type</h5>
							<div class="form-group">
								<label class="col-form-label">I am a</label>
								<select class="form-control" id="accountType" required>
									<option value="Patient"> Patient </option>
								</select>
							</div>

							<div class="reg-w3l">
								<button id="registerUser" class="form-control submit mb-4">Continue</button>
							</div>
					</div>
				</div>

			</div>
		</div>
	</div>
	<!--//Register page-->

	<!--/Register Patient-->
	<div class="modal fade" id="registerPatientModal" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header text-center">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="login px-4 mx-auto mw-100">
						<h5 class="modal-title text-center text-dark mb-4">REGISTER NOW</h5>
						<form action="register-patient-servlet" method="post">
							<div class="form-group">
								<label class="col-form-label">Username</label>
								<input type="text" class="form-control" name="username" id="usernamePatient" required>
							</div>

							<div class="form-group">
								<label class="mb-2 col-form-label">Password</label>
								<input type="password" class="form-control" name="password" id="passwordPatient" required>
							</div>

							<div class="form-group">
								<label class="col-form-label">Confirm Password</label>
								<input type="password" class="form-control" name="confirmPassword" id="passwordConfirmPatient" required>
							</div>

							<div class="form-group">
								<label class="col-form-label">First name</label>
								<input type="text" class="form-control" name="firstName" id="firstNamePatient" required>
							</div>

							<div class="form-group">
								<label class="col-form-label">Last name</label>
								<input type="text" class="form-control" name="lastName" id="lastNamePatient" required>
							</div>

							<div class="form-group">
								<label class="col-form-label">Amka</label>
								<input type="text" pattern="^(\d{11})$" class="form-control" name="amka" id="amkaPatient" required>
							</div>

							<div class="form-group">
								<label class="col-form-label">Email</label>
								<input type="email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$" class="form-control" name="email" id="emailPatient" required>
							</div>

							<div class="form-group">
								<label class="col-form-label">Phone number</label>
								<input type="text" pattern="[0-9]{3}-[0-9]{3}-[0-9]{4}" class="form-control" name="phoneNum" id="phoneNumPatient" required>
							</div>

							<div class="reg-w3l">
								<button type="submit" class="form-control submit mb-4">Register</button>
							</div>

<%--							<%--%>
<%--								String register_mg = (String)request.getAttribute("registerError");--%>
<%--								if(register_mg!=null)--%>
<%--									out.println("<font color=red size=4px>"+register_msg+"</font>");--%>
<%--							%>--%>
							<p class="text-center pb-4">
								<a href="#" class="text-secondary">By clicking Register, I agree to your terms</a>
							</p>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- //Register patient -->

    <!-- //footer -->
    <!-- js -->
    <script src="js/jquery-2.2.3.min.js"></script>
    <!-- //js -->
	<script src="js/minimal-slider.js"></script>
<!-- flexSlider (for testimonials) -->
		<!-- Calendar -->
				<link rel="stylesheet" href="css/jquery-ui.css" />
				<script src="js/jquery-ui.js"></script>
				  <script>
						  $(function() {
							$( "#datepicker" ).datepicker();
						  });
				  </script>
				<!-- //Calendar -->

	<script defer src="js/jquery.flexslider.js"></script>

	<script>
		$(window).load(function () {
			$('.flexslider').flexslider({
				animation: "slide",
				start: function (slider) {
					$('body').removeClass('loading');
				}
			});
		});
	</script>
	<!-- //flexSlider (for testimonials) -->

    <!-- script for form validation (and login error message) -->
    <script>
        window.onload = function () {

        	// Print login message error
			<%
				if (login_msg != null)
					out.println("alert('"+ login_msg +"');");
			%>

			// Check for password match
			document.getElementById("passwordAdmin").onchange = validatePasswordAdmin;
			document.getElementById("passwordConfirmAdmin").onchange = validatePasswordAdmin;
			document.getElementById("passwordPatient").onchange = validatePasswordPatient;
			document.getElementById("passwordConfirmPatient").onchange = validatePasswordPatient;
        }

		function validatePasswordPatient() {
			var passwordField = document.getElementById("passwordPatient");
			var passwordConfirmField = document.getElementById("passwordConfirmPatient");
			if (passwordField.value != passwordConfirmField.value) {
				passwordConfirmField.setCustomValidity("Passwords Don't Match");
			}
			else
				passwordConfirmField.setCustomValidity('');
			//empty string means no validation error
		}

		function validatePasswordAdmin() {
			var passwordField = document.getElementById("passwordAdmin");
			var passwordConfirmField = document.getElementById("passwordConfirmAdmin");
			if (passwordField.value != passwordConfirmField.value)
				passwordConfirmField.setCustomValidity("Passwords Don't Match");
			else
				passwordConfirmField.setCustomValidity('');
			//empty string means no validation error
		}
    </script>
    <!-- script for password match -->

    <!-- start-smooth-scrolling -->
    <script src="js/move-top.js"></script>
    <script src="js/easing.js"></script>
    <script>
        jQuery(document).ready(function ($) {
            $(".scroll").click(function (event) {
                event.preventDefault();

                $('html,body').animate({
                    scrollTop: $(this.hash).offset().top
                }, 1000);
            });
        });
    </script>
    <!-- //end-smooth-scrolling -->
    <!-- smooth-scrolling-of-move-up -->
    <script>
        $(document).ready(function () {
            var defaults = {
                containerID: 'toTop', // fading element id
                containerHoverID: 'toTopHover', // fading element hover id
                scrollSpeed: 1200,
                easingType: 'linear' 
            };

            $().UItoTop({
                easingType: 'easeOutQuart'
            });

        });
    </script>
    <script src="js/SmoothScroll.min.js"></script>
    <!-- //smooth-scrolling-of-move-up -->

	<!-- Script for managing register windows -->
	<script>
		$(document).ready(function(){
			$("#registerUser").click(function(){
				if (document.getElementById("accountType").value == "Patient") {
					$("#registerPatientModal").modal();
				}
				else {
					$("#registerAdminModal").modal();
				}
			});
		});
	</script>
	<!-- Script for managing register windows -->

    <!-- Bootstrap core JavaScript
================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="js/bootstrap.js"></script>
</body>
</html>