package ru.stuyan;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.stuyan.logic.Model;
import ru.stuyan.logic.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;

@WebServlet(urlPatterns = "/add")
public class ServletAdd extends HttpServlet {

    private AtomicInteger counter = new AtomicInteger(4);

    private Model model = Model.getInstance();

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        User user = mapper.readValue(req.getReader(), User.class);
        resp.setContentType("application/json;charset=utf-8");

        int id = counter.getAndIncrement();
        user.setId(id);
        model.getUsersMap().put(id, user);

        Collection<User> allUsers = model.getUsersMap().values();
        resp.getWriter().write(mapper.writeValueAsString(allUsers));
    }
}
