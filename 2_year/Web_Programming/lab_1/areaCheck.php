<?php
@session_start();
class areaCheck{
    
public static function check($x, $y, $r){
    if($x>=0&&$y>=0){
            return ($x <= $r / 2) && ($y <= $r);
    }
     if($x<=0&&$y>=0){
        return (-$x <= $r / 2) && ($y <= $r) && (2 * (-$x) + $y <= $r);
    }
     if($x<=0&&$y<0){
        return ($x * $x + $y * $y) <= ($r * $r);
    }
     return false;

}}
// class coordinatesValidator{
//     public static function validateCoord($x, $y, $r){
    
//     }
    
// }
?>