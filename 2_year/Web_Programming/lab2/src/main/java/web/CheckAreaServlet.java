package web;

import com.google.gson.Gson;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@WebServlet(name="/check")
public class CheckAreaServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String x = request.getParameter("x");
        String y = request.getParameter("y");
        String r = request.getParameter("r");
        int c = Integer.parseInt(request.getParameter("c"));
        ServletContext context = getServletContext();
        LocalDateTime time = LocalDateTime.now();
        LocalDateTime dateTime = LocalDateTime.now();
        //Объект класса, который проверяет попадание точки на графике
        AreaChecker areaChecker = new AreaChecker();

        // Получаем данные из контекста (если они уже там есть) или создаем новый список
        List<Object[]> data = (List<Object[]>) context.getAttribute("tableData");
        if (data == null||data.size()==0) {
            data = new ArrayList<>();
        }

        if(c == 0){
            data.clear();
            context.removeAttribute("tableData");
            context.getRequestDispatcher("/table.jsp").include(request, response);
        }
        else {
             CoordinatesValidator coordinatesValidator = new CoordinatesValidator(Double.parseDouble(x), Double.parseDouble(y), Integer.parseInt(r));
        if(Objects.equals(request.getParameter("data"), "submitButton")&&!coordinatesValidator.checkCoordinates()){
            var json = new Gson();
            System.out.println("Program has received incorrect data!");
            response.setContentType("application/json");
            response.getWriter().write(json.toJson("Program has received incorrect data!"));
            response.setStatus(422);
        }
            // Необходимо исправить ошибку с выводом сообщений о попадании (Скорее всего, проблема заключается в NullPointerException)
            else {
                Object[] newData = {x, y, r, Duration.between(time, LocalDateTime.now()).toMillis(), getDate(dateTime), areaChecker.getMessage(x, y, r)};
            data.add(newData);
            // Сохраняем обновленные данные в контексте приложения
            context.setAttribute("tableData", data);
            context.getRequestDispatcher("/table.jsp").include(request, response);
        }}
    }
    //Метод для задания формата даты
public String getDate(LocalDateTime dateTime) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    String str = dateTime.format(formatter);
    return str;
}
}

