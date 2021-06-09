<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 6/9/2021
  Time: 6:09 PM
  To change this template use File | Settings | File Templates.
--%>

<html>
<head>
    <title>Login Page</title>
</head>
<style>
  <%@include file="/WEB-INF/loginPage.css"%>
</style>


<body>
<div class="container" id="container">
  <div class="form-container sign-up-container">
    <!-- admin sign in form -->
    <form action="#" method="">
      <h1>Sign in</h1>

      <input type="text" name="adminName" placeholder="Name" />
      <input type="password" name="adminPassword" placeholder="Password" />
      <button>Sign In</button>
    </form>
  </div>

<!-- sign in form -->
  <div class="form-container sign-in-container">
    <form action="loginPage" method="post">
      <h1>Sign in</h1>

      <input type="text" name="username" placeholder="Username" />
      <input type="password" name="password" placeholder="Password" />

      <button type="submit">Sign In</button>
    </form>
  </div>

  <div class="overlay-container">
    <div class="overlay">
      <div class="overlay-panel overlay-left">
        <h1>Welcome Administrator!</h1>
        <p>To use the administrator panel, please login with your personal info</p>
        <button class="ghost" id="signIn">Just kidding! I am a patient</button>
      </div>
      <div class="overlay-panel overlay-right">
        <h1>Are you an Administrator?</h1>
        <p>Login into the administrator page</p>
        <button class="ghost" id="signUp">Administrator Sign in</button>
      </div>
    </div>
  </div>
</div>


</body>
<script >
  const signUpButton = document.getElementById('signUp');
  const signInButton = document.getElementById('signIn');
  const container = document.getElementById('container');

  signUpButton.addEventListener('click', () => {
    container.classList.add("right-panel-active");
  });

  signInButton.addEventListener('click', () => {
    container.classList.remove("right-panel-active");
  });
</script>
</html>
