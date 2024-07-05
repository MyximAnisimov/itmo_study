"use strict"
let elemntsX, elemntY, elemntsR;
var elemntX = 0;
let counter = 0;
let clickX, clickY;
elemntsX = document.getElementsByName("coordX");

document.addEventListener("DOMContentLoaded", function () {

elemntsX.forEach(function (checkbox) {
    checkbox.addEventListener("change", function () {
        if (this.checked) {
            elemntsX.forEach(function (otherCheckbox) {
                if (otherCheckbox !== checkbox) {
                    otherCheckbox.checked = false;

                }
            });
        }
    }); });});


const button1 = document.getElementById("submit_button");
const button2 = document.getElementById("remove_button").onclick=sessClear;

//По нажатию кнопки - выполнение функции SendReq и addPointBySubmit
button1.addEventListener('click', sendReq);
button1.addEventListener('click', addPointBySubmit);

async function sessClear(){
    counter = 0;
    const headers ={
        'Content-Type':"application/x-www-form-urlencoded"
    }
    await fetch(
        'controller', {
            headers: headers,
            method: 'POST',
            body: new URLSearchParams({
                "c": counter
            })
        }
    )
     localStorage.removeItem('savedPoints');
    // redrawGraph(inputR.value);
     location.reload();
}

//Функция для отправки запроса по нажатию кнопки
async function sendReq(){
    elemntX = document.querySelector('input[type="checkbox"]:checked').value;
    elemntY = document.getElementById("coordY").value.replace(/,/,'.');
    elemntsR = document.getElementById("coordR").value;
    const headers ={
        'Content-Type':"application/x-www-form-urlencoded"
    }
// alert(elemntY);
     if(checkX(elemntX)&&checkY(elemntY)&&checkR(elemntsR)){
        counter ++;
        var response = await fetch( "controller", {
            headers: headers,
            method: 'POST',
            body: new URLSearchParams({
                "x": elemntX,
                "y": elemntY.replace(/,/, '.'),
                "r": elemntsR,
                "c": counter,
                 "data": "submitButton"
            })
        });
        const data= await response.text().then(function (serverAnswer) {
            document.getElementById("table2").innerHTML = serverAnswer;
        }).catch(err => alert("Ошибка HTTP " + err.status + ". Повторите попытку позже." + err));
     }
     else{
         alert("Incorrect data! Please, try again!");
     }
      }

function checkX(data){
    if(!isNaN(data)&&data!=null){
        return true;
    }
    else {
        return false;}

}

function checkY(data){
    // elemntY = document.getElementById("coordY").value;
    // let string_y = parseFloat(elemntY.replace(/,/,'.'));
    // alert(string_y);
    if(data > -5 && data < 5 && !isNaN(data) && data != null && data !==''){
        // if(string_y.match(/((-[0-4])|([0-4]))\.([0-9]{10,})/)){
        //     return false;
        // }
        // else
        // return !!string_y.match(/((-[0-4])|([0-4]))\.([0-9]{10,})/);
        return true;
    }
    else
        return false;
}

function checkR(data){
    if(!isNaN(data)&&data!=null){
        return true;}
    else{
        return false;}}

const canvas = document.getElementById('myCanvas');
const ctx = canvas.getContext('2d');
const table = document.getElementById('table2');
const inputR = document.getElementById('coordR');
let dynamicScalingFactor;
// Задаем размеры canvas
const canvasWidth = canvas.width;
const canvasHeight = canvas.height;

// Массив для хранения точек
let points = [];

// Функция для очистки canvas
function clearCanvas() {
    ctx.clearRect(0, 0, canvasWidth, canvasHeight);

}

// Функция для перерисовки графика
function redrawGraph(r) {
    clearCanvas();
    drawCoordinateSystem(r);
    drawPoints(r);
}

// Функция для отрисовки системы координат на canvas
function drawCoordinateSystem(r) {

    let baseScaling = canvasWidth / 4;
    dynamicScalingFactor = baseScaling / r;
    let yAxisOffset = 7;
    const headLength = 10;
    const angleX = Math.atan2(canvasHeight / 2 - canvasHeight / 2, canvasWidth / 4 - canvasWidth / 4);
    // Рисуем ось X
    ctx.beginPath();
    ctx.moveTo(canvasWidth / 8, canvasHeight / 2);
    ctx.lineTo(7*canvasWidth/8, canvasHeight / 2);
    ctx.lineTo(7*canvasWidth/8 - 10 * Math.cos(angleX - Math.PI / 6), canvasHeight / 2 - headLength * Math.sin(angleX - Math.PI / 6));
    ctx.moveTo(7 * canvasWidth / 8, canvasHeight / 2);
    ctx.lineTo(7 * canvasWidth / 8 - headLength * Math.cos(angleX + Math.PI / 6), canvasHeight/2 - headLength * Math.sin(angleX + Math.PI / 6));
    ctx.stroke();
    // Рисуем деления по оси X
    ctx.fillStyle = "black";
    // X-axis tics
    const tickLength = 10; // Length of the tick marks
    for (let tickValue = -r; tickValue <= r; tickValue += r / 2) {
        const xTickPosition = canvasWidth / 2 + tickValue * dynamicScalingFactor;
        ctx.beginPath();
        ctx.moveTo(xTickPosition, canvasHeight / 2 - tickLength / 2);
        ctx.lineTo(xTickPosition, canvasHeight / 2 + tickLength / 2);
        ctx.stroke();
    }

    // Рисуем ось Y
    const angleY = Math.atan2(canvasHeight / 8 - 7*canvasHeight / 8, canvasWidth / 4 - canvasWidth / 4);
    ctx.beginPath();
    ctx.moveTo(canvasWidth / 2, 7*canvasHeight / 8);
    ctx.lineTo(canvasWidth/2, canvasHeight / 8);
    ctx.lineTo(canvasWidth/2 - 10 * Math.cos(angleY - Math.PI / 6), canvasHeight / 8 - headLength * Math.sin(angleY - Math.PI / 6));
    ctx.moveTo(canvasWidth / 2, canvasHeight / 8);
    ctx.lineTo(canvasWidth / 2 - headLength * Math.cos(angleY + Math.PI / 6), canvasHeight/8 - headLength * Math.sin(angleY + Math.PI / 6));
    ctx.stroke();

    // Рисуем деления по оси Y
    for (let tickValue = -r; tickValue <= r; tickValue += r / 2) {
        const yTickPosition = canvasHeight / 2 - tickValue * dynamicScalingFactor;
        ctx.beginPath();
        ctx.moveTo(canvasWidth / 2 - tickLength / 2, yTickPosition);
        ctx.lineTo(canvasWidth / 2 + tickLength / 2, yTickPosition);
        ctx.stroke();
    }

    // Задаем стиль и рисуем систему координат
    ctx.strokeStyle = 'black';
    ctx.lineWidth = 1;
    ctx.stroke();

    //Отрисовываем прямоугольник
    ctx.fillStyle = "rgba(82,7,56,0.6)";
    ctx.fillRect(r * dynamicScalingFactor, canvasHeight/2-r/2*dynamicScalingFactor, canvasWidth /4, canvasHeight/8);

    //Отрисовываем треугольник
    ctx.moveTo(2*r * dynamicScalingFactor, canvasHeight/2 - r/2 * dynamicScalingFactor);
    ctx.lineTo(2*r * dynamicScalingFactor, canvasHeight/2);
    ctx.lineTo(3 * r * dynamicScalingFactor, canvasHeight/2);
    ctx.fill();

    //Отрисовываем круг
    ctx.beginPath();
    ctx.fillStyle = "rgba(82,7,56,0.6)";
    ctx.arc(canvasWidth / 2, canvasHeight / 2, r/2 * dynamicScalingFactor, 0.5 * Math.PI, Math.PI);
    ctx.lineTo(canvasWidth / 2, canvasHeight / 2);
    ctx.closePath();
    ctx.fill();

    // X-axis labels
    ctx.beginPath();
    ctx.fillStyle = "black";
    ctx.fillText("X", canvasWidth / 2 + 1.5*r*dynamicScalingFactor, canvasHeight/2 + 20);
    ctx.fillText(r, canvasWidth / 2 + r * dynamicScalingFactor, canvasHeight / 2 + 20);
    ctx.fillText(r/2, canvasWidth / 2 + (r / 2) * dynamicScalingFactor, canvasHeight / 2 + 20);
    ctx.fillText('-' + r, canvasWidth / 2 - r * dynamicScalingFactor, canvasHeight / 2 + 20);
    ctx.fillText('-' + r/2, canvasWidth / 2 - (r / 2) * dynamicScalingFactor, canvasHeight / 2 + 20);

    // Y-axis labels
    ctx.beginPath();
    ctx.fillStyle = "black";
    ctx.fillText("Y", canvasWidth / 2 + yAxisOffset, canvasHeight / 2 - 1.5 * r * dynamicScalingFactor);
    ctx.fillText(r, canvasWidth / 2 + yAxisOffset, canvasHeight / 2 - r * dynamicScalingFactor);
    ctx.fillText(r/2, canvasWidth / 2 + yAxisOffset, canvasHeight / 2 - (r / 2) * dynamicScalingFactor);
    ctx.fillText('-' + r, canvasWidth / 2 + yAxisOffset, canvasHeight / 2 + r * dynamicScalingFactor);
    ctx.fillText('-' + r/2, canvasWidth / 2 + yAxisOffset, canvasHeight / 2 + (r / 2) * dynamicScalingFactor);
}


// Функция для отрисовки точек на графике
function drawPoints(r) {
    for (const point of points) {

            // Проверяем, есть ли у точки уже сохраненный цвет
            const color = points[point.x,point.y] || getRandomColor();

            points[point.x,point.y] = color; // Сохраняем цвет точки

            const transformedCoords = transformCoordinates(point.x, point.y, r);
            ctx.beginPath();
            ctx.arc(
                transformedCoords.x + canvasWidth / 2,
                canvasHeight / 2 - transformedCoords.y,
                3,
                0,
                2 * Math.PI
            );
            ctx.fillStyle = color; // Устанавливаем сохраненный цвет
            ctx.fill();
        // drawPoint(r, point);
        // const transformedCoords = transformCoordinates(point.x, point.y, r);
        // ctx.beginPath();
        // ctx.arc(
        //     transformedCoords.x + canvasWidth / 2,
        //     canvasHeight / 2 - transformedCoords.y,
        //     3,
        //     0,
        //     2 * Math.PI
        // );
        // ctx.fillStyle = getRandomColor();
        // ctx.fill();
    }
}
// function drawPoint(r, point){
//     const transformedCoords = transformCoordinates(point.x, point.y, r);
//     ctx.beginPath();
//     ctx.arc(
//         transformedCoords.x + canvasWidth / 2,
//         canvasHeight / 2 - transformedCoords.y,
//         3,
//         0,
//         2 * Math.PI
//     );
//     ctx.fillStyle = getRandomColor();
//     ctx.fill();
// }

// Функция для преобразования координат к новым масштабам. НЕОБХОДИМО РЕШИТЬ ПРОБЛЕМУ С НЕПРАВИЛЬНЫМ ИЗМЕНЕНИЕМ РАСПОЛОЖЕНИЯ ТОЧЕК
function transformCoordinates(x, y, r) {
    return {
        x: x / r,
        y: y / r,
    };
}

async function updatePointTable() {
    for (const point of points) {
        // const row = table.insertRow();
        // // const xCell = row.insertCell();
        // // const yCell = row.insertCell();
        // // xCell.textContent = point.x;
        // // yCell.textContent = point.y;
    clickX = point.x/112;
    clickY = point.y/112;}
        const headers ={
            'Content-Type':"application/x-www-form-urlencoded"
        }
        counter++;
    var response = await fetch("controller", {
        headers: headers,
        method: 'POST',
        body: new URLSearchParams({
            "x": clickX.toFixed(5),
            "y": clickY.toFixed(5),
            "r": inputR.value,
            "c": counter
        })
    });
    const data= await response.text().then(function (serverAnswer) {
        document.getElementById("table2").innerHTML = serverAnswer;
    }).catch(err => alert("Ошибка HTTP " + err.status + ". Повторите попытку позже." + err));
}

// Функция для добавления точки при клике на график
function addPointByClick(event) {
    const rect = canvas.getBoundingClientRect();
    let x = (event.clientX - rect.left - canvasWidth / 2)*inputR.value;
    let y = (canvasHeight / 2 - (event.clientY - rect.top))*inputR.value;
    points.push({x, y});
    localStorage.setItem('savedPoints', JSON.stringify(points));
    redrawGraph(inputR.value);
}
// Функция для добавления точки при клике на график
function addPointBySubmit() {
    const rect = canvas.getBoundingClientRect();
    let x = (elemntX*112);
    let y = (parseFloat(elemntY.replace(/,/, '.'))*112);
    if(checkX(elemntX)&&checkY(y/112)&&checkR(elemntsR)){
    points.push({x, y});
    localStorage.setItem('savedPoints', JSON.stringify(points));
    redrawGraph(elemntsR);}
}

// Обработчик клика на график
canvas.addEventListener('click', addPointByClick);
canvas.addEventListener('click', updatePointTable);

// Обработчик изменения значения R
inputR.addEventListener('input', () => {
    redrawGraph(inputR.value);
});

// Обработчик клика на кнопку добавления точки
canvas.addEventListener('mousedown', () => {
    const x = parseFloat(document.getElementById('coordX').value);
    const y = parseFloat(document.getElementById('coordY').value);
    points.push({ x, y });
    document.getElementById('coordX').value = '';
    document.getElementById('coordY').value = '';
});
// Инициализация графика
window.onload = function() {

    const savedPoints = JSON.parse(localStorage.getItem('savedPoints'));
       if(savedPoints) points = savedPoints;
        redrawGraph(inputR.value);
}
function getRandomColor() {
    const letters = '0123456789ABCDEF';
    let color = '#';
    for (let i = 0; i < 6; i++) {
        color += letters[Math.floor(Math.random() * 16)];
    }
    return color;
}