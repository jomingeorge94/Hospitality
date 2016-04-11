<?php
    $con=mysqli_connect("localhost","root","password","hospitality");

    $emaiaddress = $_GET["email"];

    $statement = mysqli_prepare($con, "SELECT * FROM Users WHERE email = ?");
    mysqli_stmt_bind_param($statement, "s", $emaiaddress);
    mysqli_stmt_execute($statement);

    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $ID, $fullname, $email, $contact, $password, $gender, $created_at);

    $user = array();
    
    while(mysqli_stmt_fetch($statement)){
        $user["name"] = $fullname;
        $user["emailaddress"] = $email;
        $user["password"] = $password;
        $user["gender"] = $gender;
        $user["contact"] = $contact;
        $created_at = date("F, Y", strtotime($created_at));
        $user["created"] = $created_at;

    }

    

    echo json_encode($user);
    mysqli_stmt_close($statement);

    mysqli_close($con);
?>