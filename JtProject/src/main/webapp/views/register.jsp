<!doctype html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css"
          integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
    <title>Sign Up</title>
    <style>
        body {
            background-color: #f8f9fa;
        }
        .signup-container {
            max-width: 500px;
            margin: 50px auto;
            background-color: white;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
        }
        .signup-title {
            text-align: center;
            margin-bottom: 30px;
            font-weight: bold;
        }
        .form-group {
            margin-bottom: 20px;
        }
        .form-control {
            border-radius: 5px;
        }
        .btn-primary {
            border-radius: 5px;
        }
        .linkControl {
            color: #007bff;
            text-decoration: none;
        }
        .linkControl:hover {
            color: #0056b3;
            text-decoration: underline;
        }
    </style>
</head>
<body>

<div class="signup-container">
    <h2 class="signup-title">Sign Up Now</h2>
    <p>Please fill out this form to register</p>
    <form action="newuserregister" method="post">
        <div class="form-group">
            <label for="username">User Name</label>
            <input type="text" name="username" id="username" required class="form-control form-control-lg">
        </div>
        <div class="form-group">
            <label for="email">Email address</label>
            <input type="email" class="form-control form-control-lg" required minlength="6" name="email" id="email" aria-describedby="emailHelp">
            <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" class="form-control form-control-lg" required name="password" id="password">
        </div>
        <div class="form-group">
            <label for="address">Address</label>
            <textarea class="form-control form-control-lg" rows="3" name="address"></textarea>
        </div>
        <span style="margin-top: 10px">Already have an account <a class="linkControl" href="/">Login here</a></span>
        <br><br>
        <input type="submit" value="Register" class="btn btn-primary btn-block">
        <br>
        <h3 style="color:red;">${msg}</h3>
    </form>
</div>

<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>
</html>