<?php
    $con=mysqli_connect("localhost","root","password","hospitality");

    $t= $_POST["type"];
    $d= $_POST["date"];
    $time= $_POST["time"];
    $e= $_POST["email"];
    
  
    $statement = mysqli_prepare($con, "SELECT * FROM Users WHERE email = ?");
    mysqli_stmt_bind_param($statement, "s", $e);
    mysqli_stmt_execute($statement);

    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $ID, $fullname, $email, $contact, $password, $gender, $created_at);

    $uid;

    while(mysqli_stmt_fetch($statement)){

        $uid = $ID;
    }

        mysqli_stmt_close($statement);


        

    $statement = mysqli_prepare($con, "INSERT INTO reminder (time, type, user_id, date) VALUES (?, ?, ?, ?)");
    mysqli_stmt_bind_param($statement, "ssss", $time, $t, $uid, $d);
    mysqli_stmt_execute($statement);

    mysqli_stmt_close($statement);




    mysqli_close($con);
        echo "200";

?>