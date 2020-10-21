package example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@WebServlet(name = "PageServlet", urlPatterns = {"/", "home"})
public class PageServlet extends HttpServlet {

    private Connection connection;

    @Override
    public void init() throws ServletException {
        connection = DbConnector.openConnection();
    }

    @Override
    public void destroy() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) { }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Question> questions = queryRandomQuestions();
            request.setAttribute("questions", questions);
            request.getRequestDispatcher("/page.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Question> questions = queryQuestions(Collections.list(request.getParameterNames()));
            int right = 0;
            for (Question question : questions) {
                String answerIndexStr = request.getParameter(String.valueOf(question.getId()));
                int answerIndex = Integer.parseInt(answerIndexStr);
                if (answerIndex == question.getCorrectIndex()) {
                    right++;
                }
            }

            request.setAttribute("answer", String.format("Got %d out of %d", right, questions.size()));
            request.getRequestDispatcher("/page.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private List<Question> queryRandomQuestions() throws SQLException {
        String sql = "SELECT * FROM questions ORDER BY RAND() LIMIT 3";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                List<Question> questions = new ArrayList<>();
                while (resultSet.next()) {
                    Question question = new Question();
                    question.setId(resultSet.getLong("id"));
                    question.setQuestion(resultSet.getString("question"));
                    question.setChoices(resultSet.getString("choices").split(","));
                    question.setCorrectIndex(resultSet.getInt("correct"));
                    questions.add(question);
                }

                return questions;
            }
        }
    }

    private List<Question> queryQuestions(List<String> ids) throws SQLException {
        StringBuilder stringBuilder = new StringBuilder("SELECT * FROM questions WHERE id IN (");
        for (int i = 0; i < ids.size(); i++) {
            stringBuilder.append(ids.get(i));
            if (i + 1 < ids.size()) {
                stringBuilder.append(',');
            }
        }
        stringBuilder.append(')');

        try (PreparedStatement statement = connection.prepareStatement(stringBuilder.toString())) {
            try (ResultSet resultSet = statement.executeQuery()) {
                List<Question> questions = new ArrayList<>();
                while (resultSet.next()) {
                    Question question = new Question();
                    question.setId(resultSet.getLong("id"));
                    question.setQuestion(resultSet.getString("question"));
                    question.setChoices(resultSet.getString("choices").split(","));
                    question.setCorrectIndex(resultSet.getInt("correct"));
                    questions.add(question);
                }

                return questions;
            }
        }
    }
}
