package ru.stuyan;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.stuyan.logic.Model;
import ru.stuyan.logic.Response;
import ru.stuyan.logic.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/put")
public class ServletPut extends HttpServlet {

    private Model model = Model.getInstance();

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        User user = mapper.readValue(req.getReader(), User.class);
        resp.setContentType("application/json;charset=utf-8");
        Response response = new Response();

        if (model.getUsersMap().containsKey(user.getId())) {
            model.getUsersMap().put(user.getId(), user);
            response.setMessage("Пользователь успешно обновлён");
        } else {
            response.setMessage("Пользователь с таким id не найден");
        }
        resp.getWriter().write(mapper.writeValueAsString(response));
    }
}
