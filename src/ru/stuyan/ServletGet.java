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
import java.util.Collection;

@WebServlet(urlPatterns = "/get")
public class ServletGet extends HttpServlet {

    private Model model = Model.getInstance();

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        String idS = req.getQueryString();
        resp.setContentType("application/json;charset=utf-8");
        Response response = new Response();

        // Если не запросе вообще не прилетает id - выдается список их всех пользователей
        if (idS == null) {
            Collection<User> allUsers = model.getUsersMap().values();
            resp.getWriter().write(mapper.writeValueAsString(allUsers));
        // Если прилетает id - проверяем есть ли пользователь с таким id и выводим только его
        } else {
            Integer id = Integer.parseInt(idS.replace("id=", ""));
            User user = model.getUsersMap().get(id);
            if (user == null) {
                response.setMessage("Пользователь с таким id не найден");
                resp.getWriter().write(mapper.writeValueAsString(response));
            } else {
                resp.getWriter().write(mapper.writeValueAsString(user));
            }
        }
    }
}
