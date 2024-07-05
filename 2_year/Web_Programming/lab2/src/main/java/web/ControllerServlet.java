package web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="/controller")
public class ControllerServlet extends HttpServlet {
    @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        String coordinateX = request.getParameter("x");
        String coordinateY = request.getParameter("y");
        String coordinateR = request.getParameter("r");
        int counter = Integer.parseInt(request.getParameter("c"));
        if((coordinateX!=null&&coordinateY!=null&&coordinateR!=null)||counter == 0){
        request.getRequestDispatcher("/check").forward(request, response);
        }
        else
            request.getRequestDispatcher("/result.jsp").forward(request, response);
    }
}
