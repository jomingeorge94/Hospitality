<?php
    $con=mysqli_connect("localhost","root","password","hospitality");

    $emaiaddress = $_POST["email"];
    $password = $_POST["password"];

    $statement = mysqli_prepare($con, "SELECT * FROM Users WHERE email = ? AND password = ?");
    mysqli_stmt_bind_param($statement, "ss", $emaiaddress, $password);
    mysqli_stmt_execute($statement);

    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $ID, $fullname, $email, $password, $created_at);

    $user = array();

    while(mysqli_stmt_fetch($statement)){
        $user["name"] = $fullname;
        $user["emailaddress"] = $email;
        $user["password"] = $password;
    }

    echo json_encode($user);
    mysqli_stmt_close($statement);

    mysqli_close($con);
?>