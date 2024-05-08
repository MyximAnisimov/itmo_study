<template>
  <section class="main">
    <div class="container">
      <div class="row">
        <div class="text-center">
          <div class="graph">
            <div class="svg-wrapper">
              <svg height="360" width="360" xmlns="http://www.w3.org/2000/svg" id="graph" @click="validateFromGraph">
                <!-- Стерлки и оси -->
                <line stroke="black" x1="0" x2="360" y1="180" y2="180"></line>
                <line stroke="black" x1="180" x2="180" y1="0" y2="360"></line>
                <polygon fill="black" points="180,0 174,15 186,15" stroke="black"></polygon>
                <polygon fill="black" points="360,180 345,186 345,174" stroke="black"></polygon>

                <!-- Деления -->
                <line stroke="black" x1="240" x2="240" y1="185" y2="175"></line>
                <line stroke="black" x1="300" x2="300" y1="185" y2="175"></line>

                <line stroke="black" x1="60" x2="60" y1="185" y2="175"></line>
                <line stroke="black" x1="120" x2="120" y1="185" y2="175"></line>

                <line stroke="black" x1="175" x2="185" y1="120" y2="120"></line>
                <line stroke="black" x1="175" x2="185" y1="60" y2="60"></line>

                <line stroke="black" x1="175" x2="185" y1="240" y2="240"></line>
                <line stroke="black" x1="175" x2="185" y1="300" y2="300"></line>

                <!-- Подписи к осям -->
                <text fill="black" x="345" y="170">X</text>
                <text fill="black" x="160" y="15">Y</text>

                <!-- Первая фигура (1 четверть) -->
                <path id="circle"
                      fill="red"
                      fill-opacity="0.2"
                      stroke="red"
                      d="M 180 60 A 120 120 90 0 0 60 180 L 180 180 Z"></path>

                <!-- Вторая фигура (2 четверть) -->
                <polygon id="rectangle"
                         fill="red"
                         fill-opacity="0.2"
                         stroke="red"
                         points="300,120 300,180 180,180 180,120"></polygon>

                <!-- Третья фигура (4 четверть) -->
                <polygon id="triangle"
                         fill="red"
                         fill-opacity="0.2"
                         stroke="red"
                         points="180,180 300,180 180,300"></polygon>

                <!-- Точки на графике -->

              </svg>
            </div>
          </div>
        </div>

        <div class="text-center">

          <div class="inputs">
            <p>Выберите R:</p>
            <div class="row">
              <label class="text-label">
                <input id="input-r" class="text-input" type="number" step="0.1" name="r" placeholder="[-5; 3]" maxlength="14" v-model="r"/>
              </label>
            </div>

            <p>Выберите Х:</p>
            <div class="row">
              <label class="text-label">
                <input id="input-x" class="text-input" type="text" name="x" placeholder="[-5; 3]" maxlength="14" v-model="x"/>
              </label>
            </div>

            <p>Выберите Y:</p>
            <div class="row">
              <label class="text-label">
                <input id="input-y" class="text-input" type="text" name="y" placeholder="[-3; 5]" maxlength="14" v-model="y"/>
              </label>
            </div>
          </div>
          <div class="special-button">
            <button id="submit-button" @click="validateForm" type="submit">Отправить на проверку</button>
            <button id="remove-button" @click="deleteDots" >Удалить все точки</button>
          </div>

          <div class="special-button">
            <Button id="logout" color="blue" label="Выйти из аккаунта" @click.native="logout"/>
          </div>
        </div>
      </div>
    </div>

    <div class="container-table">
      <div class="text-center">
        <p style="font-size: 20px" v-if="dots.length === 0">Точек нет :(</p>
        <table v-else class="table-check">
          <thead class="thead">
          <tr class="table-header">
            <th scope="col">X</th>
            <th scope="col">Y</th>
            <th scope="col">R</th>
            <th scope="col">Текущее время</th>
            <th scope="col">Результат попадания</th>
          </tr>
          </thead>
          <tbody>
          <tr v-for="dot in dots" :key="dot" class="table-row">
            <td>{{ dot.x }}</td>
            <td>{{ dot.y }}</td>
            <td>{{ dot.r }}</td>
            <td>{{ dot.time }}</td>
            <td>{{ dot.result ? "\u2713" : "\u2717" }}</td>
          </tr>
          </tbody>
        </table>
      </div>
    </div>
  </section>
</template>

<script>
import Button from "@/components/Button";

export default {
  name: "Main",
  components: {
    Button,
  },
  data(){
    return {
      x: "",
      y: "",
      r: "3", // максимальный размер графика
      xGraph: "", // Х из графика
      yGraph: "", // Y из графика
      dots: new Array(0), // Список всех точек пользователя
    }
  },
  watch: {
    x(val){
      this.validateInput(-5, 3, 'input-x', val);
    },
    y(val){
      this.validateInput(-3, 5, 'input-y', val);
    },
    r(val){
      if(this.validateInput(-5, 3, 'input-r', val)) {
        // let r = parseFloat(this.r);
        // let circle = document.getElementById("circle");
        // let rectangle = document.getElementById("rectangle");
        // let triangle = document.getElementById("triangle");
        // circle.setAttribute("d", `M 180 ${180 - 40 * r} A ${r * 40} ${r * 40}, 90, 0, 0, ${180 - 40 * r} 180  L 180 180 Z`);
        // rectangle.setAttribute("points", `${180 + 40 * r},${180 - 20 * r} ${180 + 40 * r},180 180,180 180,${180 - 20 * r}`);
        // triangle.setAttribute("points", `180,180 ${180 + 40 * r},180 180,${180 + 40 * r}`);
        this.drawDots();
      }
      //M 180 60 A 120 120 90 0 0 60 180 L 180 180 Z
    }
  },
  methods: {
    // Взаимодействие с формой и графиком
    addDots(x, y){
      this.$axios.put("http://localhost:8080/api/point",
          {x: x, y: y, r: this.r},
          {headers: {"Authorization": "Bearer " + localStorage.getItem("jwt")}
      }).then(() => {
        this.loadDots();
        this.$notify({
          group: 'success',
          title: 'Добавление точки',
          text: 'Успешно',
          type: 'success'
        });
      }).catch(() => {
        this.AxiosErrorHandler("Точку не удалось добавить")
      });
    },
    deleteDots(){
      this.$axios.delete("http://localhost:8080/api/point",
          {headers: {Authorization: "Bearer " + localStorage.getItem("jwt")}
      }).then(() => {
        this.loadDots();
        this.$notify({
          group: 'success',
          title: 'Удаление точек',
          text: 'Успешно',
          type: 'success'
        });
      }).catch(() => {
        this.AxiosErrorHandler("Точки не удалось удалить");
      });
    },
    logout(){
      this.$notify({
        group: 'success',
        title: 'Выход из аккаунта',
        text: 'Успешно',
        type: 'success'
      });
      this.$router.push({name: "auth-page"}, () => localStorage.clear());
    },

    // Загрузка и прорисовка точек на графике
    loadDots(){
      this.$axios.get("http://localhost:8080/api/point", {
        headers: {Authorization: "Bearer " + localStorage.getItem("jwt")}
      }).then(response => {
        this.dots = response.data;
        this.drawDots();
      }).catch(() => {
        this.AxiosErrorHandler("Точки не удалось загрузить");
      });
    },
    drawDots(){
      let r = parseFloat(this.r);
      let svg = document.getElementById("graph")
      let oldDots = document.querySelectorAll("circle");
      oldDots.forEach(oldDot => oldDot.parentNode.removeChild(oldDot));

      if(this.dots.length !== 0){
        this.dots.forEach(dot => {
          let newDot = document.createElementNS("http://www.w3.org/2000/svg", "circle");
          newDot.setAttribute("id", "target-dot");
          newDot.setAttribute("r", "3.5");
          if (r >= 0) {
            newDot.setAttribute("cx", `${dot.x / r * 120 + 180}`);
            newDot.setAttribute("cy", `${180 - dot.y / r * 120}`);
          } else {
            newDot.setAttribute("cx", `${(dot.x) / r * 120 + 180}`);
            newDot.setAttribute("cy", `${180 - (dot.y) / r * 120}`);
          }
          if (dot.r === r) {
            newDot.setAttribute("fill", dot.result === true ? "green" : "red");
            newDot.setAttribute("r", "3.5");
          } else {
            newDot.setAttribute("fill", "black");
            newDot.setAttribute("opacity", `${((r - 0.5 < dot.r) && (r + 0.5 > dot.r)) ? "0.5" : "0.1"}`);
          }
          svg.appendChild(newDot);
        })
      }
    },

    // Различные виды валидации
    validateForm(){
      if ((this.x >= -5 && this.x <= 3) &&
          (this.y >= -3 && this.y <= 5) &&
          (this.r >= -5 && this.r <= 3) &&
          (!isNaN(this.x) && !isNaN(this.y) && !isNaN(this.r)) &&
          (this.x.trim() !== '' && this.y.trim() !== '' && this.r.trim() !== '')){
        this.addDots(this.x, this.y);
      } else {
        this.AxiosErrorHandler("Проверте введенные данные");
      }
    },
    validateFromGraph(){
      let position = getMousePosition(document.getElementById("graph"), event);
      this.xGraph = ((position.x - 180) / 120 * this.r).toFixed(2);
      this.yGraph = ((180 - position.y) / 120 * this.r).toFixed(2);
      if (this.r >= -5 && this.r <= 3) {
        this.addDots(this.xGraph, this.yGraph);
      } else {
        this.AxiosErrorHandler("Проверте значение R");
      }
      function getMousePosition(element, event) {
        let rect = element.getBoundingClientRect();
        return {
          x: event.clientX - rect.left,
          y: event.clientY - rect.top
        };
      }
    },
    validateInput(firstNumber, secondNumber, className, val){
      let element = document.getElementById(className);
      let buttonSubmit = document.getElementById("submit-button")
      if (parseFloat(val) < firstNumber || parseFloat(val) > secondNumber || isNaN(val)){
        element.classList.add("red");
        element.setAttribute("style", "color: red");
        buttonSubmit.setAttribute("disabled", "true");
        console.log(buttonSubmit)
        return false;
      } else {
        element.classList.remove("red");
        element.removeAttribute("style");
        buttonSubmit.removeAttribute("disabled");
        return true;
      }
    },

    // Вывод сообщений ошибок
    AxiosErrorHandler(errorText){
      this.$notify({
        group: 'error',
        title: 'Error',
        text: errorText,
        type: 'error'
      })
    }
  },
  mounted() {
    this.loadDots();
    this.drawDots();
  }
}
</script>

<style scoped>
.container{
  margin: 30px auto auto;
  background: linear-gradient(to bottom left, rgb(220, 63, 7, 0.8), rgb(130, 30, 145, 0.8));

  padding: 20px;
  display: table;
  text-align: center;
  box-shadow: 0 0 10px 1px black;
  border-radius: 20px;
}

/*
    Отвечает за график (левая часть контейнера)
*/
.svg-wrapper{
  background-color: white;
  border: 2px solid black;
  box-shadow: 0 0 5px 0 black;
  border-radius: 10px;
  padding: 15px;
  margin-bottom: 10px;
}

/*
    Ввод данных (Правая часть)
*/
.inputs{
  background-color: pink;
  padding: 1px 50px 15px 50px;
  border-radius: 20px;
}

.text-center{
  margin: auto 30px;
  display: inline-block;
}

.text-input{
  text-align: center;
  border-radius: 5px;
}

/*
    Для кнопок отправки и очистки таблицы
*/
.special-button button{
  color: white;
  margin: 20px 5px 0;
  letter-spacing: 1px;
  display: inline-block;
  text-decoration: none;
  background-color: black;
  border: 2px solid black;
  border-radius: 6px;
}

.special-button button:hover{
  color: black;
  transition: 0.5s;
  border: 2px solid red;
  background-color: ghostwhite;
}

/*
    Формат таблицы на планшетах/компьютерах
 */

@media(min-width: 800px) {
  .table-check {
    color: white;
    margin: 20px auto 0;
    border: 1px solid black;
    background-color: black;
  }

  .table-header th {
    padding: 5px 25px 5px 25px;
    font-size: 1.2em;
    font-weight: lighter;
  }

  .table-row td {
    font-size: 1.2em;
    text-align: center;
    font-weight: lighter;
  }

  .table-row:nth-of-type(odd) {
    margin: 0;
    background-color: #1f1f1f;
  }

  .container-table {
    margin: 30px auto 0 auto;
    padding: 20px 10px 10px 5px;
    display: table;
    text-align: center;
  }
}

/*
    Формат таблицы на телефонах
 */

@media(max-width: 799px) {
  .table-check {
    color: white;
    margin: 20px auto 0;
    border: 1px solid black;
    background-color: black;
  }

  .table-header th {
    padding: 5px 10px 5px 10px;
    font-size: 1.2em;
    font-weight: lighter;
  }

  .table-row td {
    font-size: 1.2em;
    text-align: center;
    font-weight: lighter;
  }

  .table-row:nth-of-type(odd) {
    margin: 0;
    background-color: #1f1f1f;
  }

  .container-table {
    margin: 30px auto 0 auto;
    padding: 20px 10px 10px 5px;
    display: table;
    text-align: center;
  }
}

.red{
  border: 2px solid red;
}
</style>