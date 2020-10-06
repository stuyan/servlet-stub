package ru.stuyan;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.stuyan.logic.Calculator;
import ru.stuyan.logic.Response;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/calculator")
public class ServletCalculator extends HttpServlet {

    private ObjectMapper mapper = new ObjectMapper();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        Calculator calculator = mapper.readValue(req.getReader(), Calculator.class);
        resp.setContentType("application/json;charset=utf-8");
        Response response = new Response();

        int a = calculator.getA();
        int b = calculator.getB();
        String action = calculator.getMath();
        Double result = null;
        if (action.equals("-")) {
            result = (a * 1.0) - b;
        } else if (action.equals("+")) {
            result = (a * 1.0) + b;
        } else if (action.equals("*")) {
            result = (a * 1.0) * b;
        } else if (action.equals("/") && b != 0) {
            result = (a * 1.0) / b;
        } else if (action.equals("/") && b == 0) {
            response.setMessage("На ноль делить запрещено!");
        }

        if (result != null) {
            response.setResult(result);
        }

        resp.getWriter().write(mapper.writeValueAsString(response));
    }

}
