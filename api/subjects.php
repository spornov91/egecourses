<?php 
header('Access-Control-Allow-Origin: *');
header("Content-Type: text/html; charset=utf-8");
header('Content-Type: application/json');

$arr = array(

array( 
"id" => 104,
"name" => "Английский",
"price" => "2500"
 ), 
array( 
"id" => 101,
"name" => "Химия",
"price" => "2500"
 ), 
array(
"id" => 102,
"name" => "Биология", 
"price" => "7500"
 ), 
array(
"id" => 103,
"name" => "Русский", 
"price" => "3700"
 )

);

$json = json_encode($arr, 
JSON_PRETTY_PRINT |
JSON_UNESCAPED_UNICODE | JSON_UNESCAPED_SLASHES | JSON_NUMERIC_CHECK);
echo $json;

?>