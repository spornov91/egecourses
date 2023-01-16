<?php 
header('Access-Control-Allow-Origin: *');
header("Content-Type: text/html; charset=utf-8");
header('Content-Type: application/json');

$arr = array(
array( 
"id" => 0001,
"idsubject" => 104,
"subject" => "english",
"name" => "Английский 1", 
"price" => 2500, 
"pay" => true
 ), 
array( 
"id" => 0002,
"idsubject" => 104,
"subject" => "english",
"name" => "Английский 2",
 "price" => "7500", 
"pay" => false 
), 
array( 
"id" => 0003,
"idsubject" => 104,
"subject" => "english",
"name" => "Английский 3", 
"price" => "4500" , 
"pay" => true
) ,
array(
"id" => 0004,
"idsubject" => 101,
"subject" => "himija",
 "name" => "Общая химия", 
"price" => "2500", 
"pay" => true
 ), 
array( 
"id" => 0005,
"idsubject" => 101,
"subject" => "himija",
"name" => "Неорганическая химия",
 "price" => "7500", 
"pay" => false
 ), 
array( 
"id" => 0006,
"idsubject" => 101,
"subject" => "himija",
"name" => "Органическая химия",
 "price" => "4500" , 
"pay" => true
)
);

$json = json_encode($arr, 
JSON_PRETTY_PRINT |
JSON_UNESCAPED_UNICODE | JSON_UNESCAPED_SLASHES | JSON_NUMERIC_CHECK);
echo $json;

?>