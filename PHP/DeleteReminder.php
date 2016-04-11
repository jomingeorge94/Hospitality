<?php
    $con=mysqli_connect("localhost","root","password","hospitality");

    $i = $_POST["id"];


    $statement = mysqli_prepare($con, "DELETE FROM `reminder` WHERE `reminder_id` = ?");
    mysqli_stmt_bind_param($statement, "s", $i);
    mysqli_stmt_execute($statement);

    mysqli_stmt_store_result($statement);

     



    mysqli_stmt_close($statement);

    mysqli_close($con);
        echo "200";

?>