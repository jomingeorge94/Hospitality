<?php
    $con=mysqli_connect("localhost","root","password","hospitality");

    $g = $_POST["gender"];
    $c = $_POST["contact"];
    $e = $_POST["email"];
    //$l = $_POST["language"];
   // $c = $_POST["contact"];

    $statement = mysqli_prepare($con, "UPDATE `users` SET `gender` = ? WHERE `email` = ?");
    mysqli_stmt_bind_param($statement, "ss", $g, $e);
    mysqli_stmt_execute($statement);

    mysqli_stmt_store_result($statement);

       $statement = mysqli_prepare($con, "UPDATE `users` SET `contact_number` = ? WHERE `email` = ?");
    mysqli_stmt_bind_param($statement, "ss", $c, $e);
    mysqli_stmt_execute($statement);

    mysqli_stmt_store_result($statement);



    mysqli_stmt_close($statement);

    mysqli_close($con);
        echo "200";

?>