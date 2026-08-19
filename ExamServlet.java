package de;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ExamServlet")
public class ExamServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        System.out.println();
        System.out.println("========================================");
        System.out.println("          EXAM SUBMISSION");
        System.out.println("========================================");

        // ==========================================
        // GET STUDENT NAME
        // ==========================================

        String studentName =
                request.getParameter("studentName");

        if (studentName == null ||
                studentName.trim().isEmpty()) {

            studentName = "Unknown Student";
        }

        System.out.println(
                "Student Name : " + studentName
        );

        // ==========================================
        // GET ANSWERS
        // ==========================================

        String[] answers = new String[25];

        for (int i = 0; i < 25; i++) {

            answers[i] =
                    request.getParameter(
                            "q" + (i + 1)
                    );

            System.out.println(
                    "Q" + (i + 1) +
                    " : " +
                    answers[i]
            );
        }

        // ==========================================
        // CORRECT ANSWERS
        // ==========================================

        String[] correctAnswers = {

            "B",   // Q1
            "B",   // Q2
            "A",   // Q3
            "B",   // Q4
            "A",   // Q5
            "B",   // Q6
            "B",   // Q7
            "B",   // Q8
            "A",   // Q9
            "A",   // Q10
            "A",   // Q11
            "B",   // Q12
            "A",   // Q13
            "B",   // Q14
            "B",   // Q15
            "A",   // Q16
            "A",   // Q17
            "A",   // Q18
            "A",   // Q19
            "B",   // Q20
            "A",   // Q21
            "A",   // Q22
            "B",   // Q23
            "A",   // Q24
            "B"    // Q25
        };

        // ==========================================
        // CALCULATE SCORE
        // ==========================================

        int score = 0;

        for (int i = 0; i < 25; i++) {

            if (answers[i] != null &&
                    correctAnswers[i].equals(
                            answers[i])) {

                score++;
            }
        }

        // ==========================================
        // CALCULATE PERCENTAGE
        // ==========================================

        double percentage =
                (score / 25.0) * 100;

        // ==========================================
        // CALCULATE GRADE
        // ==========================================

        String grade;

        if (score >= 23) {

            grade = "A+";

        } else if (score >= 20) {

            grade = "A";

        } else if (score >= 17) {

            grade = "B";

        } else if (score >= 13) {

            grade = "C";

        } else {

            grade = "D";
        }

        // ==========================================
        // PRINT RESULT IN GLASSFISH OUTPUT
        // ==========================================

        System.out.println();
        System.out.println("----------------------------------------");
        System.out.println("             EXAM RESULT");
        System.out.println("----------------------------------------");

        System.out.println(
                "Student   : " + studentName
        );

        System.out.println(
                "Score     : " +
                score +
                " / 25"
        );

        System.out.println(
                "Percentage: " +
                String.format("%.2f", percentage) +
                "%"
        );

        System.out.println(
                "Grade     : " +
                grade
        );

        System.out.println("----------------------------------------");

        // ==========================================
        // SAVE RESULT
        // ==========================================

        boolean saved =
                saveResult(
                        studentName,
                        score,
                        percentage,
                        grade
                );

        // ==========================================
        // RESULT PAGE
        // ==========================================

        response.setContentType(
                "text/html;charset=UTF-8"
        );

        PrintWriter out =
                response.getWriter();

        out.println("<!DOCTYPE html>");

        out.println("<html lang='en'>");

        out.println("<head>");

        out.println("<meta charset='UTF-8'>");

        out.println(
                "<meta name='viewport' " +
                "content='width=device-width, initial-scale=1.0'>"
        );

        out.println(
                "<title>Exam Result</title>"
        );

        // ==========================================
        // CSS
        // ==========================================

        out.println("<style>");

        out.println(
                "* {" +
                "margin:0;" +
                "padding:0;" +
                "box-sizing:border-box;" +
                "}"
        );

        out.println(
                "body {" +
                "font-family:Arial,sans-serif;" +
                "background:linear-gradient(135deg,#4f46e5,#7c3aed);" +
                "min-height:100vh;" +
                "display:flex;" +
                "align-items:center;" +
                "justify-content:center;" +
                "padding:20px;" +
                "}"
        );

        out.println(
                ".card {" +
                "background:white;" +
                "width:100%;" +
                "max-width:550px;" +
                "padding:40px;" +
                "border-radius:25px;" +
                "text-align:center;" +
                "box-shadow:0 20px 50px rgba(0,0,0,.25);" +
                "}"
        );

        out.println(
                ".icon {" +
                "font-size:60px;" +
                "margin-bottom:15px;" +
                "}"
        );

        out.println(
                "h1 {" +
                "color:#312e81;" +
                "margin-bottom:15px;" +
                "}"
        );

        out.println(
                ".student {" +
                "font-size:18px;" +
                "color:#4b5563;" +
                "margin-bottom:20px;" +
                "}"
        );

        out.println(
                ".score {" +
                "font-size:55px;" +
                "font-weight:bold;" +
                "color:#4f46e5;" +
                "margin:20px;" +
                "}"
        );

        out.println(
                ".box {" +
                "background:#f5f3ff;" +
                "padding:20px;" +
                "border-radius:15px;" +
                "margin:15px 0;" +
                "font-size:18px;" +
                "}"
        );

        out.println(
                ".success {" +
                "color:#16a34a;" +
                "font-weight:bold;" +
                "}"
        );

        out.println(
                ".error {" +
                "color:#dc2626;" +
                "font-weight:bold;" +
                "}"
        );

        out.println(
                ".btn {" +
                "display:inline-block;" +
                "padding:14px 30px;" +
                "background:#4f46e5;" +
                "color:white;" +
                "text-decoration:none;" +
                "border-radius:30px;" +
                "margin-top:20px;" +
                "}"
        );

        out.println(
                ".btn:hover {" +
                "background:#3730a3;" +
                "}"
        );

        out.println("</style>");

        out.println("</head>");

        // ==========================================
        // BODY
        // ==========================================

        out.println("<body>");

        out.println("<div class='card'>");

        out.println(
                "<div class='icon'>🏆</div>"
        );

        out.println(
                "<h1>Examination Completed!</h1>"
        );

        out.println(
                "<div class='student'>" +
                "Well done, <b>" +
                escapeHtml(studentName) +
                "</b>!" +
                "</div>"
        );

        out.println(
                "<div class='score'>" +
                score +
                " / 25" +
                "</div>"
        );

        out.println(
                "<div class='box'>" +
                "<b>Percentage:</b> " +
                String.format("%.2f", percentage) +
                "%" +
                "</div>"
        );

        out.println(
                "<div class='box'>" +
                "<b>Grade:</b> " +
                grade +
                "</div>"
        );

        if (saved) {

            out.println(
                    "<div class='box success'>" +
                    "✓ Result saved successfully!" +
                    "</div>"
            );

        } else {

            out.println(
                    "<div class='box error'>" +
                    "⚠ Result could not be saved to database." +
                    "</div>"
            );
        }

        out.println(
                "<a class='btn' href='index.html'>" +
                "Take Exam Again 🔄" +
                "</a>"
        );

        out.println("</div>");

        out.println("</body>");

        out.println("</html>");
    }

    // ==========================================
    // SAVE RESULT TO DATABASE
    // ==========================================

    private boolean saveResult(
            String studentName,
            int score,
            double percentage,
            String grade) {

        System.out.println();
        System.out.println("========================================");
        System.out.println("       SAVING RESULT TO DATABASE");
        System.out.println("========================================");

        String sql =
                "INSERT INTO results " +
                "(student_name, score, percentage, grade) " +
                "VALUES (?, ?, ?, ?)";

        Connection con = null;
        PreparedStatement ps = null;

        try {

            System.out.println(
                    "Connecting to MySQL..."
            );

            con =
                    DBConnection.getConnection();

            System.out.println(
                    "Connection: SUCCESS"
            );

            ps =
                    con.prepareStatement(sql);

            System.out.println(
                    "PreparedStatement: SUCCESS"
            );

            ps.setString(
                    1,
                    studentName
            );

            ps.setInt(
                    2,
                    score
            );

            ps.setDouble(
                    3,
                    percentage
            );

            ps.setString(
                    4,
                    grade
            );

            System.out.println(
                    "Parameters: SUCCESS"
            );

            int rows =
                    ps.executeUpdate();

            System.out.println(
                    "Rows inserted: " +
                    rows
            );

            if (rows > 0) {

                System.out.println(
                        "DATABASE INSERT: SUCCESS"
                );

                System.out.println(
                        "RESULT SAVED SUCCESSFULLY"
                );

                System.out.println(
                        "========================================"
                );

                return true;
            }

            System.out.println(
                    "DATABASE INSERT: FAILED"
            );

            return false;

        } catch (Exception e) {

            System.out.println(
                    "DATABASE INSERT: FAILED"
            );

            System.out.println(
                    "ERROR: " +
                    e.getMessage()
            );

            e.printStackTrace();

            System.out.println(
                    "========================================"
            );

            return false;

        } finally {

            try {

                if (ps != null) {
                    ps.close();
                }

            } catch (Exception e) {

                System.out.println(
                        "Error closing PreparedStatement: " +
                        e.getMessage()
                );
            }

            try {

                if (con != null) {
                    con.close();
                }

            } catch (Exception e) {

                System.out.println(
                        "Error closing Connection: " +
                        e.getMessage()
                );
            }
        }
    }

    // ==========================================
    // HTML ESCAPE
    // ==========================================

    private String escapeHtml(String text) {

        if (text == null) {
            return "";
        }

        return text
                .replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#39;");
    }
}
