<?php 
header('Access-Control-Allow-Origin: *');
header("Content-Type: text/html; charset=utf-8");
header('Content-Type: application/json');

$arr = array(

array( 
"id" => 301,
"name" => "Александр", 
"course" => "Информатика" 
), 
array( 
"id" => 302,
"name" => "Василий", 
"course" => "Биология"
), 
array( 
"id" => 303,
"name" => "Оксана", 
"course" => "Химия" 
) 

);

$json = json_encode($arr, 
JSON_PRETTY_PRINT |
JSON_UNESCAPED_UNICODE | JSON_UNESCAPED_SLASHES | JSON_NUMERIC_CHECK);
echo $json;

?>