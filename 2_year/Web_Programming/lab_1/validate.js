"use strict"

const button1 = document.getElementById("submit_button").onclick = sendReq;
const button2 = document.getElementById("remove_button").onclick=sessClear;
let elemntX, elemntY, elemntsR;

async function viewTable(){
    fetch(
    'script.php',
    {
        headers: {'Content-Type':"application/json"},
        method: 'POST',
        body: JSON.stringify()
    }
).then((res) => {
   return res.json()})
   .then((data) => {
      console.log(data);
      let finalData ="";
        data.forEach((itemData) => {
            var html_data= "<tr><td>"+itemData["x"]+"</td>"+
               "<td>"+itemData["y"]+"</td>"+
               "<td>"+itemData["r"]+"</td>"+
               "<td>"+itemData["time"]+"</td>"+
               "<td>"+itemData["date"]+"</td>"+
               "<td>"+itemData["result"]+"</td>"+
               "</tr>";
               finalData+=html_data;
         
        });
    document.getElementById("table2").innerHTML = finalData;
    }).catch(err => alert("Ошибка HTTP " + err.status + ". Повторите попытку позже." + err));}


  viewTable();

async function sessClear(){
    fetch(
        'clear.php', {
               method: 'POST'    
        }
    )
     location.reload();
}

async function sendReq(){
     elemntX = document.getElementById("coordX").value;
     elemntY = document.getElementById("coordY").value;
     elemntsR = document.getElementsByName("radio_button");
    var elemntR = 0;
    for (var i=0, length = elemntsR.length; i < length; i++){
        if(elemntsR[i].checked)
            elemntR = elemntsR[i].value;
    }
    const headers ={
        'Content-Type':"application/json; charset=UTF-8"
    }
    if(checkX(elemntX)&&checkY(elemntY)&&checkR(elemntR)){
    var response = await fetch('add.php', {
        headers: headers,
        method: 'POST',
        body: JSON.stringify({
            x: elemntX, 
            y: elemntY,
            r: elemntR,
        })
    });

    const data= await response.text().then(function (serverAnswer) {
         document.getElementById("table2").innerHTML = serverAnswer;

      }).catch(err => alert("Ошибка HTTP " + err.status + ". Повторите попытку позже." + err));

    viewTable();
}
    else 
    alert("Введены некорректные данные! Пожалуйста, повторите попытку");
}
function checkX(data){
    if(!isNaN(data)){
        return true;
    }
    else{ 
        return false;}

}
function checkY(data){
    elemntY = document.getElementById("coordY").value;
    let string_y = elemntY;

    // if(!isNaN(data)&&data>-5&&data<5){
       
        if(string_y.match(/((-[0-4])|([0-4]))\.([0-9]{50,})/)){
            return false;
        }else
        return true;
    // }
    // else{
    //  return false;}
}
function checkR(data){
    if(data!== 0){
    return true;}
else{
    return false;}
}




