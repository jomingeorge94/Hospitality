<?php
    $con=mysqli_connect("localhost","root","password","hospitality");

    $e = $_GET["email"];

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


    $statement = mysqli_prepare($con, "SELECT * FROM reminder WHERE user_id = ?");
    mysqli_stmt_bind_param($statement, "s", $uid);
    mysqli_stmt_execute($statement);

    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $rid, $time, $type, $u, $date);

    $rem = array();

    while(mysqli_stmt_fetch($statement)){
        $temp = array();

        $temp["time"] = $time;
        $temp["id"] = $rid;
        $temp["type"] = $type;
        $temp["date"] = $date;
        $rem[] = $temp;
    }


    echo json_encode($rem);
    mysqli_stmt_close($statement);

    mysqli_close($con);





?>