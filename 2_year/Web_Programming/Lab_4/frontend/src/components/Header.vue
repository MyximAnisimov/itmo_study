<template>
  <header class="header">
      <div>
        <span class="lab-name">Лабораторная работа №4</span>
      </div>
      <div>
        <span>
          Анисимов Максим Дмитриевич P3233
        </span>
        <span>
          Вариант 667
        </span>
      </div>
  </header>
</template>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Bebas+Neue&family=MuseoModerno&display=swap");
@font-face {
  font-family: "ubuntu";
  src: url("../../public/static/fonts/ubuntu.ttf");
}
header{
  margin: 0;
  padding: 0;
}

.header {
  height: 30%;
  min-width: 440px;
  display: flex;
  //background-color: black;
  background: linear-gradient(to bottom left, rgb(220, 63, 7, 0.8), rgb(130, 30, 145, 0.8));

  justify-content: space-between;
  //box-shadow: 0 0 20px 5px darkred;
  box-shadow: 0 0 10px 1px black;
  font-family: "ubuntu";
}

/*
  Название проекта:
*/

.header .lab-name{
  color: whitesmoke;
  font-size: 1.5em;
  font-weight: bold;
  margin-top: 10px;
  font-family: "ubuntu";
}

/*
  Имена, Фамилии, вариант лабы:
*/

.header > div {
  display: flex;
  align-items: center;
  margin: 5px 20px 5px;
  flex-direction: column;
}

.header span, .header a{
  color: white;
  font-size: 0.9em;
  font-weight: bold;
  text-decoration: none;
  font-family: "ubuntu";
}
</style>