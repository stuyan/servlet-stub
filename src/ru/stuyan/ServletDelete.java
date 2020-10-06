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

@WebServlet(urlPatterns = "/delete")
public class ServletDelete extends HttpServlet {

    private Model model = Model.getInstance();

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        String id = req.getQueryString().replace("id=", "");
        User deleted = model.getUsersMap().remove(Integer.parseInt(id));
        resp.setContentType("application/json;charset=utf-8");
        Response response = new Response();
        if (deleted != null) {
            response.setMessage("Пользователь с id=" + deleted.getId() + " успешно удален");
        } else {
            response.setMessage("Пользователь с таким id не найден");
        }
        resp.getWriter().write(mapper.writeValueAsString(response));
    }
}
