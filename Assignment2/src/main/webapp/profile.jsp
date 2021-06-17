<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Iterator" %>
<!--Template: W3layouts
Template URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->
<!DOCTYPE html>
<html lang="zxx">
<head>
    <title>Doctor Pro | Make an appointment now</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="utf-8" />
    <meta name="keywords" content="Alleviating Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, 
	SmartPhone Compatible web template, free WebDesigns for Nokia, Samsung, LG, Sony Ericsson, Motorola web design" />
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
    <!-- //Custom Theme files -->
	 <!-- online-fonts -->
	<link href="//fonts.googleapis.com/css?family=Lato:400,700" rel="stylesheet">
    <link href="//fonts.googleapis.com/css?family=Montserrat:200,300,400,500,600,700,800,900" rel="stylesheet"><!-- //online-fonts -->
</head>
<body>
<%

    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache"); // HTTP 1
    response.setHeader("Expires", "0");

    if(session.getAttribute("username") == null)
        response.sendRedirect("index.jsp");


%>
        <!-- header -->
        <header>
            <nav class="navbar navbar-expand-lg navbar-light bg-gradient-secondary">
                <h1>
                    <a class="navbar-brand text-white" href="index.jsp">
                       DOCTOR PRO
                    </a>
                </h1>
                <button class="navbar-toggler ml-md-auto" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                    aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav ml-lg-auto text-center">
                        <li class="nav-item mr-3 mt-lg-0 mt-3">
                            <a class="nav-link" href="index.jsp">Home
                                <span class="sr-only">(current)</span>
                            </a>
                        </li>
                        <li class="nav-item  mr-3 mt-lg-0 mt-3">
                            <a class="nav-link" href="services.jsp">services</a>
                        </li>
                        <li class="nav-item dropdown active mr-3 mt-lg-0 mt-3">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true"
                                aria-expanded="false">
                                Dropdown
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <a class="dropdown-item" href="about.jsp">About</a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="profile.jsp">Profile</a>
                            </div>
                        </li>
                        <li class="nav-item mr-3 mt-lg-0 mt-3">
                            <a class="nav-link" href="gallery.jsp">Gallery</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="contact.jsp">Contact</a>
                        </li>
                        <li>
                            <button type="button" class="btn  ml-lg-2 w3ls-btn" data-toggle="modal" aria-pressed="false" data-target="#exampleModal">
                                Login
                            </button>
                        </li>
                    </ul>
                </div>
            </nav>
        </header>
 <!-- main image slider container -->
        <div class="inner-banner">
         </div> 
    <!-- end of main image slider container -->
	<nav aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item">
                <a href="index.jsp">Home</a>
            </li>
            <li class="breadcrumb-item active" aria-current="page">Profile</li>
        </ol>
    </nav>
	<!-- typography -->
    <section class="wthree-row pt-sm-3  pb-sm-5 pb-3">
        <div class="container py-sm-5 py-3">
            <!-- section title -->

				<h2 class="heading-agileinfo"> Welcome <% out.println("<font color=black size=6px>"+ session.getAttribute("username")+"</font>"); %></h2>
            <!-- //section title -->
            <div class="pb-5 mt-md-5 typo-wthree">
                <h4 class="pt-4 pb-3">Profile</h4>
<%--                <div class="d-flex p-2 bg-flex mb-1">I'm a flexbox container!</div>--%>
<%--                <div class="d-inline-flex p-2 bg-flex mb-1">I'm an inline flexbox container!</div>--%>
<%--                <h5 class="pt-4 pb-3">Flex row</h5>--%>
<%--                <div class="d-flex flex-row bg-flex">--%>
<%--                    <div class="p-2 bg-flex mb-1 bg-flex-item">Flex item 1</div>--%>
<%--                    <div class="p-2 bg-flex mb-1 bg-flex-item">Flex item 2</div>--%>
<%--                    <div class="p-2 bg-flex mb-1 bg-flex-item">Flex item 3</div>--%>
<%--                </div>--%>
<%--                <h5 class="pt-4 pb-3">Flex row-reverse</h5>--%>
<%--                <div class="d-flex flex-row-reverse bg-flex">--%>
<%--                    <div class="p-2 bg-flex mb-1 bg-flex-item">Flex item 1</div>--%>
<%--                    <div class="p-2 bg-flex mb-1 bg-flex-item">Flex item 2</div>--%>
<%--                    <div class="p-2 bg-flex mb-1 bg-flex-item">Flex item 3</div>--%>
<%--                </div>--%>
<%--                <h5 class="pt-4 pb-3">Flex column</h5>--%>
                <div class="d-flex flex-column bg-flex">
                    <div class="p-2 bg-flex mb-1 bg-flex-item">AMKA: <% out.println("<font color=black size=4px>"+ session.getAttribute("amka")+"</font>"); %></div>
                    <div class="p-2 bg-flex mb-1 bg-flex-item">Firstname:  <% out.println("<font color=black size=4px>"+ session.getAttribute("firstname")+"</font>"); %></div>
                    <div class="p-2 bg-flex mb-1 bg-flex-item">Surname:  <% out.println("<font color=black size=4px>"+ session.getAttribute("surname")+"</font>"); %> </div>
                    <div class="p-2 bg-flex mb-1 bg-flex-item">Username: <% out.println("<font color=black size=4px>"+ session.getAttribute("username")+"</font>"); %></div>
                    <div class="p-2 bg-flex mb-1 bg-flex-item">Email: <% out.println("<font color=black size=4px>"+ session.getAttribute("email")+"</font>"); %> </div>
                    <div class="p-2 bg-flex mb-1 bg-flex-item">Phone Number: <% out.println("<font color=black size=4px>"+ session.getAttribute("phoneNumber")+"</font>"); %> </div>
                </div>

<%--                <h5 class="pt-4 pb-3">Flex column-reverse</h5>--%>
<%--                <div class="d-flex flex-column-reverse bg-flex">--%>
<%--                    <div class="p-2 bg-flex mb-1 bg-flex-item">Flex item 1</div>--%>
<%--                    <div class="p-2 bg-flex mb-1 bg-flex-item">Flex item 2</div>--%>
<%--                    <div class="p-2 bg-flex mb-1 bg-flex-item">Flex item 3</div>--%>
<%--                </div>--%>
<%--                <h4 class="pt-5 pb-3">Justify Content</h4>--%>
<%--                <h5 class="pt-4 pb-3">Justify Content Start</h5>--%>
<%--                <div class="d-flex justify-content-start bg-flex mb-3">--%>
<%--                    <div class="p-2 bg-flex mb-1 bg-flex-item">Flex item 1</div>--%>
<%--                    <div class="p-2 bg-flex mb-1 bg-flex-item">Flex item 2</div>--%>
<%--                    <div class="p-2 bg-flex mb-1 bg-flex-item">Flex item 3</div>--%>
<%--                </div>--%>
<%--                <h5 class="pt-4 pb-3">Justify Content End</h5>--%>
<%--                <div class="d-flex justify-content-end bg-flex mb-3">--%>
<%--                    <div class="p-2 bg-flex mb-1 bg-flex-item">Flex item 1</div>--%>
<%--                    <div class="p-2 bg-flex mb-1 bg-flex-item">Flex item 2</div>--%>
<%--                    <div class="p-2 bg-flex mb-1 bg-flex-item">Flex item 3</div>--%>
<%--                </div>--%>
<%--                <h5 class="pt-4 pb-3">Justify Content Center</h5>--%>
<%--                <div class="d-flex justify-content-center bg-flex mb-3">--%>
<%--                    <div class="p-2 bg-flex mb-1 bg-flex-item">Flex item 1</div>--%>
<%--                    <div class="p-2 bg-flex mb-1 bg-flex-item">Flex item 2</div>--%>
<%--                    <div class="p-2 bg-flex mb-1 bg-flex-item">Flex item 3</div>--%>
<%--                </div>--%>
<%--                <h5 class="pt-4 pb-3">Justify Content Between</h5>--%>
<%--                <div class="d-flex justify-content-between bg-flex mb-3">--%>
<%--                    <div class="p-2 bg-flex mb-1 bg-flex-item">Flex item 1</div>--%>
<%--                    <div class="p-2 bg-flex mb-1 bg-flex-item">Flex item 2</div>--%>
<%--                    <div class="p-2 bg-flex mb-1 bg-flex-item">Flex item 3</div>--%>
<%--                </div>--%>
<%--                <h5 class="pt-4 pb-3">Justify Content Around</h5>--%>
<%--                <div class="d-flex justify-content-around bg-flex mb-3">--%>
<%--                    <div class="p-2 bg-flex mb-1 bg-flex-item">Flex item 1</div>--%>
<%--                    <div class="p-2 bg-flex mb-1 bg-flex-item">Flex item 2</div>--%>
<%--                    <div class="p-2 bg-flex mb-1 bg-flex-item">Flex item 3</div>--%>
<%--                </div>--%>
<%--                <h4 class="pt-4 pb-3"> Align items</h4>--%>
<%--                <h5 class="pt-4 pb-3">Align Items Start</h5>--%>

<%--                <div class="d-flex align-items-start  bg-flex mb-3" style="height: 100px">--%>
<%--                    <div class="p-2  bg-flex-item">Flex item</div>--%>
<%--                    <div class="p-2  bg-flex-item">Flex item</div>--%>
<%--                    <div class="p-2  bg-flex-item">Flex item</div>--%>
<%--                </div>--%>
<%--                <h5 class="pt-4 pb-3">Align Items End</h5>--%>

<%--                <div class="d-flex align-items-end  bg-flex mb-3" style="height: 100px">--%>
<%--                    <div class="p-2  bg-flex-item">Flex item</div>--%>
<%--                    <div class="p-2  bg-flex-item">Flex item</div>--%>
<%--                    <div class="p-2  bg-flex-item">Flex item</div>--%>
<%--                </div>--%>
<%--                <h5 class="pt-4 pb-3">Align Items Center</h5>--%>

<%--                <div class="d-flex align-items-center  bg-flex mb-3" style="height: 100px">--%>
<%--                    <div class="p-2  bg-flex-item">Flex item</div>--%>
<%--                    <div class="p-2  bg-flex-item">Flex item</div>--%>
<%--                    <div class="p-2  bg-flex-item">Flex item</div>--%>
<%--                </div>--%>
<%--                <h5 class="pt-4 pb-3">Align Items Baseline</h5>--%>

<%--                <div class="d-flex align-items-baseline  bg-flex mb-3" style="height: 100px">--%>
<%--                    <div class="p-2  bg-flex-item">Flex item</div>--%>
<%--                    <div class="p-2  bg-flex-item">Flex item</div>--%>
<%--                    <div class="p-2  bg-flex-item">Flex item</div>--%>
<%--                </div>--%>
<%--                <h5 class="pt-4 pb-3">Align Items Strech</h5>--%>

<%--                <div class="d-flex align-items-stretch  bg-flex" style="height: 100px">--%>
<%--                    <div class="p-2  bg-flex-item">Flex item</div>--%>
<%--                    <div class="p-2  bg-flex-item">Flex item</div>--%>
<%--                    <div class="p-2  bg-flex-item">Flex item</div>--%>
<%--                </div>--%>
                <h4 class="mt-5 mb-3"></h4>
                <div class="row">
                    <div class="col-md-6">
                        <h5 class="pt-4 pb-3">Your appointments</h5>
                        <ul class="list-group">
                            <%
                                ArrayList<String[]> appointment_list = (ArrayList<String[]>) session.getAttribute("appointment_list");
                                if(appointment_list != null)
                                {
                                    for (String[] appointDetails : appointment_list)
                                    {
                                        out.println("<li class='list-group-item'>"
                                                + appointDetails[0] + " " + appointDetails[1] + " "
                                                + appointDetails[2] + " " + appointDetails[3] + " "
                                                + appointDetails[4] + " " + appointDetails[5] + " "
                                                + "</li>");
                                    }
                                }
                            %>
                            <li class="list-group-item">Mixali ton pairneis</li>
                        </ul>
                    </div>
<%--                    <div class="col-md-6">--%>
<%--                        <h5 class="pt-4 pb-3">Active items</h5>--%>
<%--                        <ul class="list-group">--%>
<%--                            <li class="list-group-item active">Cras justo odio</li>--%>
<%--                            <li class="list-group-item">Dapibus ac facilisis in</li>--%>
<%--                            <li class="list-group-item">Morbi leo risus</li>--%>
<%--                            <li class="list-group-item">Porta ac consectetur ac</li>--%>
<%--                            <li class="list-group-item">Vestibulum at eros</li>--%>
<%--                        </ul>--%>
<%--                    </div>--%>
<%--                    <div class="col-md-6 my-3">--%>
<%--                        <h5 class="pt-4 pb-3">Disabled items</h5>--%>
<%--                        <ul class="list-group">--%>
<%--                            <li class="list-group-item disabled">Cras justo odio</li>--%>
<%--                            <li class="list-group-item">Dapibus ac facilisis in</li>--%>
<%--                            <li class="list-group-item">Morbi leo risus</li>--%>
<%--                            <li class="list-group-item">Porta ac consectetur ac</li>--%>
<%--                            <li class="list-group-item">Vestibulum at eros</li>--%>
<%--                        </ul>--%>
<%--                    </div>--%>
<%--                    <div class="col-md-6 my-3">--%>
<%--                        <h5 class="pt-4 pb-3">Flush</h5>--%>
<%--                        <ul class="list-group list-group-flush">--%>
<%--                            <li class="list-group-item">Cras justo odio</li>--%>
<%--                            <li class="list-group-item">Dapibus ac facilisis in</li>--%>
<%--                            <li class="list-group-item">Morbi leo risus</li>--%>
<%--                            <li class="list-group-item">Porta ac consectetur ac</li>--%>
<%--                            <li class="list-group-item">Vestibulum at eros</li>--%>
<%--                        </ul>--%>
<%--                    </div>--%>
<%--                    <div class="col-md-6">--%>
<%--                        <h5 class="pt-4 pb-3">Contextual Classes</h5>--%>
<%--                        <ul class="list-group">--%>
<%--                            <li class="list-group-item">Dapibus ac facilisis in</li>--%>
<%--                            <li class="list-group-item list-group-item-primary">This is a primary list group item</li>--%>
<%--                            <li class="list-group-item list-group-item-secondary">This is a secondary list group item</li>--%>
<%--                            <li class="list-group-item list-group-item-success">This is a success list group item</li>--%>
<%--                            <li class="list-group-item list-group-item-danger">This is a danger list group item</li>--%>
<%--                            <li class="list-group-item list-group-item-warning">This is a warning list group item</li>--%>
<%--                            <li class="list-group-item list-group-item-info">This is a info list group item</li>--%>
<%--                            <li class="list-group-item list-group-item-light">This is a light list group item</li>--%>
<%--                            <li class="list-group-item list-group-item-dark">This is a dark list group item</li>--%>
<%--                        </ul>--%>
<%--                    </div>--%>
<%--                    <div class="col-md-6 mt-md-0 mt-4">--%>
<%--                        <h5 class="pt-4 pb-3">With Badges</h5>--%>
<%--                        <ul class="list-group">--%>
<%--                            <li class="list-group-item d-flex justify-content-between align-items-center">--%>
<%--                                Cras justo odio--%>
<%--                                <span class="badge badge-primary badge-pill">14</span>--%>
<%--                            </li>--%>
<%--                            <li class="list-group-item d-flex justify-content-between align-items-center">--%>
<%--                                Dapibus ac facilisis in--%>
<%--                                <span class="badge badge-primary badge-pill">2</span>--%>
<%--                            </li>--%>
<%--                            <li class="list-group-item d-flex justify-content-between align-items-center">--%>
<%--                                Morbi leo risus--%>
<%--                                <span class="badge badge-primary badge-pill">1</span>--%>
<%--                            </li>--%>
<%--                        </ul>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--                <h4 class="pt-4 pb-3 mt-sm-5 mt-3">Color</h4>--%>
<%--                <div class="row">--%>
<%--                    <div class="col-md-6">--%>
<%--                        <h5 class="my-4">Text color</h5>--%>
<%--                        <p class="text-primary mb-2">.text-primary</p>--%>
<%--                        <p class="text-secondary mb-2">.text-secondary</p>--%>
<%--                        <p class="text-success mb-2">.text-success</p>--%>
<%--                        <p class="text-danger mb-2">.text-danger</p>--%>
<%--                        <p class="text-warning mb-2">.text-warning</p>--%>
<%--                        <p class="text-info mb-2">.text-info</p>--%>
<%--                        <p class="text-light bg-dark mb-2">.text-light</p>--%>
<%--                        <p class="text-dark mb-2">.text-dark</p>--%>
<%--                        <p class="text-muted mb-2">.text-muted</p>--%>
<%--                        <p class="text-white bg-dark">.text-white</p>--%>
<%--                    </div>--%>
<%--                    <div class="col-md-6">--%>
<%--                        <h5 class="my-4">Anchors with the provided hover and focus states.</h5>--%>
<%--                        <p>--%>
<%--                            <a href="#" class="text-primary">Primary link</a>--%>
<%--                        </p>--%>
<%--                        <p class="mb-2">--%>
<%--                            <a href="#" class="text-secondary">Secondary link</a>--%>
<%--                        </p>--%>
<%--                        <p class="mb-2">--%>
<%--                            <a href="#" class="text-success">Success link</a>--%>
<%--                        </p>--%>
<%--                        <p class="mb-2">--%>
<%--                            <a href="#" class="text-danger">Danger link</a>--%>
<%--                        </p>--%>
<%--                        <p class="mb-2">--%>
<%--                            <a href="#" class="text-warning">Warning link</a>--%>
<%--                        </p>--%>
<%--                        <p class="mb-2">--%>
<%--                            <a href="#" class="text-info">Info link</a>--%>
<%--                        </p>--%>
<%--                        <p>--%>
<%--                            <a href="#" class="text-light bg-dark">Light link</a>--%>
<%--                        </p>--%>
<%--                        <p class="mb-2">--%>
<%--                            <a href="#" class="text-dark">Dark link</a>--%>
<%--                        </p>--%>
<%--                        <p class="mb-2">--%>
<%--                            <a href="#" class="text-muted">Muted link</a>--%>
<%--                        </p>--%>
<%--                        <p>--%>
<%--                            <a href="#" class="text-white bg-dark">White link</a>--%>
<%--                        </p>--%>
<%--                    </div>--%>
<%--                </div>--%>
            </div>
        </div>
        <!-- //typo container -->
    </section>
    <!-- //typography -->

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
						<a href="mailto:example@email.com" class="text-secondary">info@example.com</a>
					</div>
				</div>
				<!-- //footer grid1 -->
				<!-- footer grid2 -->
				<div class="col-lg-3  col-sm-6 footv3-left text-lg-center my-sm-0 mt-5">
					<h3 class="mb-4">Opening Hours</h3>
					<ul class="list-agileits">
						<li>
							Mon – Fri ------- 09:00-17:00
						</li>
						<li class="my-3">
							Saturday -------- 09:30-17:00</a>
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
					<p class="text-secondary">© 2018 Alleviating. All rights reserved | Design by
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
    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Login</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form action="#" method="post">
                        <div class="form-group">
                            <label for="recipient-name" class="col-form-label">Username</label>
                            <input type="text" class="form-control" placeholder=" " name="Name" id="recipient-name" required="">
                        </div>
                        <div class="form-group">
                            <label for="password" class="col-form-label">Password</label>
                            <input type="password" class="form-control" placeholder=" " name="Password" id="password" required="">
                        </div>
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
                            <a href="#" data-toggle="modal" data-target="#exampleModalCenter2" class="text-dark font-weight-bold">
                                Register Now</a>
								
                        </p>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <!-- //login -->
	 <!--/Register-->
    <div class="modal fade" id="exampleModalCenter2" tabindex="-1" role="dialog" aria-hidden="true">
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
                        <form action="#" method="post">
                            <div class="form-group">
                                <label class="col-form-label">First name</label>

                                <input type="text" class="form-control" id="validationDefault01" placeholder="" required="">
                            </div>
                            <div class="form-group">
                                <label class="col-form-label">Last name</label>
                                <input type="text" class="form-control" id="validationDefault02" placeholder="" required="">
                            </div>

                            <div class="form-group">
                                <label class="mb-2 col-form-label">Password</label>
                                <input type="password" class="form-control" id="password1" placeholder="" required="">
                            </div>
                            <div class="form-group">
                                <label class="col-form-label">Confirm Password</label>
                                <input type="password" class="form-control" id="password2" placeholder="" required="">
                            </div>
							<div class="reg-w3l">
								<button type="submit" class="form-control submit mb-4">Register</button>
                           </div>
						   <p class="text-center pb-4">
                                <a href="#" class="text-secondary">By clicking Register, I agree to your terms</a>
                            </p>
                        </form>

                    </div>
                </div>

            </div>
        </div>
    </div>
    <!--//Register-->

    <!-- //footer -->
    <!-- js -->
    <script src="js/jquery-2.2.3.min.js"></script>
    <!-- //js -->
    <!-- script for password match -->
    <script>
        window.onload = function () {
            document.getElementById("password1").onchange = validatePassword;
            document.getElementById("password2").onchange = validatePassword;
        }

        function validatePassword() {
            var pass2 = document.getElementById("password2").value;
            var pass1 = document.getElementById("password1").value;
            if (pass1 != pass2)
                document.getElementById("password2").setCustomValidity("Passwords Don't Match");
            else
                document.getElementById("password2").setCustomValidity('');
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
            /*
            var defaults = {
                containerID: 'toTop', // fading element id
                containerHoverID: 'toTopHover', // fading element hover id
                scrollSpeed: 1200,
                easingType: 'linear' 
            };
            */

            $().UItoTop({
                easingType: 'easeOutQuart'
            });

        });
    </script>
    <script src="js/SmoothScroll.min.js"></script>
    <!-- //smooth-scrolling-of-move-up -->
    <!-- Bootstrap core JavaScript
================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="js/bootstrap.js"></script>
</body>
</html>