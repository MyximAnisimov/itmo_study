<%@page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style>
        <%@include file="/WEB-INF/static/styles/style.css"%>
        body {
            /* Градиент */
            background: linear-gradient(to bottom left, #420588bb, #d4091aa6);
        }
        div {
            width: 70%; /* Ширина */
            margin: auto; /* Выравниваем по центру */
            min-height: 2000px; /* Минимальная высота */
            background: #fff; /* Белый цвет фона */

        }
    </style>
</head>

<body>
<header>
    <h1>Веб-программирование, Лабораторная работа №2, Вариант 15551
        <p>Анисимов Максим Дмитриевич P3233</p>
    </h1>
</header>
<table class="table1">
    <tbody>
    <td rowspan="3" colspan="3" width="70%">
<jsp:include page="graph.jsp"/>
    </td>
    <form action="" method="POST" id="html_form">
        <td class="column1" colspan="3">
            <p lang="chooseCoord">Введите Х</p>
            <input type="checkbox" value=-5 id="coordX-5" name="coordX" required>
            <label for=coordX-5>-5</label>
            <input type="checkbox" value=-4 id="coordX-4" name="coordX" required>
            <label for=coordX-4>-4</label>
            <input type="checkbox" value=-3 id="coordX-3" name="coordX" required>
            <label for=coordX-3>-3</label>
            <input type="checkbox" value=-2 id="coordX-2" name="coordX" required>
            <label for=coordX-2>-2</label>
            <input type="checkbox" value=-1 id="coordX-1" name="coordX" required>
            <label for=coordX-1>-1</label>
            <input type="checkbox" value=0 id="coordX0" name="coordX" required>
            <label for=coordX0>0</label>
            <input type="checkbox" value=1 id="coordX1" name="coordX" required>
            <label for=coordX1>1</label>
            <input type="checkbox" value=2 id="coordX2" name="coordX" required>
            <label for=coordX2>2</label>
            <input type="checkbox" value=3 id="coordX3" name="coordX" required>
            <label for=coordX3>3</label>

        </td>
        <tr>
            <td class="column1" colspan="3"><p lang="chooseCoord">Введите Y<p>
                <input placeholder="Введите Y от -5 до 5" id="coordY" name="coordY" type="text" required>
            </td>
        </tr>
        <tr>
            <td class="column1" colspan="3">
                <p lang="chooseCoord">Введите R</p>
                <select id="coordR" name="coordR">
                    <option>1</option>
                    <option>2</option>
                    <option>3</option>
                    <option>4</option>
                    <option>5</option>
                </select>

            </td>
        </tr>
        <tr>
            <td colspan="3">
                <button id="submit_button" type='button' >Отправить</button>
            </td>
            <td colspan="3">
                <button id="remove_button" type="button">Очистить</button>
            </td>
        </tr>
    </form>
    </tbody>
    <tr class="header_table2">
        <th>X</th>
        <th>Y</th>
        <th>R</th>
        <th>Время выполнения</th>
        <th>Дата</th>
        <th>Результат выполнения ОДЗ</th>

    </tr>
    <tbody class="table2" id="table2">
    <jsp:include page="table.jsp"/>
    </tbody>


</table>
<ul>

</ul>
<script type="text/javascript">
        <%@include file="/WEB-INF/validate.js"%>
</script>
</body>
</html>