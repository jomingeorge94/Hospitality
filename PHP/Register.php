<?php
    $con=mysqli_connect("localhost","root","password","hospitality");

    $fullname = $_POST["fullname"];
    $emailaddress = $_POST["email_address"];
    $user_password = $_POST["user_password"];

    $statement = mysqli_prepare($con, "INSERT INTO Users (fullname, email, password) VALUES (?, ?, ?)");
    mysqli_stmt_bind_param($statement, "sss", $fullname, $emailaddress, $user_password);
    mysqli_stmt_execute($statement);

    mysqli_stmt_close($statement);

    mysqli_close($con);
?>