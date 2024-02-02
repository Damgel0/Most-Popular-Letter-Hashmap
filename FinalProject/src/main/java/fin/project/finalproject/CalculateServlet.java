package fin.project.finalproject;

// Imports
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/CalculateServlet") // Getting action
public class CalculateServlet extends HttpServlet {

    @Override
    public void init() {
        System.out.println("Starting..."); // Message of starting host
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter(); // Make that I can output any message on the site
        System.out.println("Initialized!"); // Message that host is Initialized

        List<Student> studentList = new ArrayList<>(); // Creating arraylist
        // Adding students
        studentList.add(new Student("Mate", "Math, page 64 exercise 12"));
        studentList.add(new Student("Mirian", "History, learn about NATO paragraph World war 2"));
        studentList.add(new Student("Timothy", "Georgian language, learn poem on page 78"));
        studentList.add(new Student("Mark", "English language, make and learn speaking task on page 33 ex. 5"));
        studentList.add(new Student("Salome", "Physics, make project about Isaac newton"));

        // Creating Map (HashMap)
        Map<String, Character> result = calculateMostUsedSymbol(studentList);

        // Debugging. Shows Results in console, if it shows in console and not in site it is problem of site.
        System.out.println("Result Map: " + result);

        // Printing result
        out.println("<html><body>");
        for (Map.Entry<String, Character> entry : result.entrySet()) {
            out.println("Name: " + entry.getKey() + ", Most used symbol: " + entry.getValue() + "<br>");
        }
        out.println("</body></html>");
    }

    private Map<String, Character> calculateMostUsedSymbol(List<Student> students) {
        Map<String, Character> result = new HashMap<>();

        for (Student student : students) {
            String homework = student.getHomework(); // Getting homework value
            char mostUsedSymbol = findMostUsedSymbol(homework); // Calling method findMostUsedSymbol with value of homework
            result.put(student.getName(), mostUsedSymbol); // Putting results
        }
        return result;
    }

    private char findMostUsedSymbol(String homework) {
        Map<Character, Integer> frequencyMap = new HashMap<>();

        for (char c : homework.toCharArray()) { // Splits homework for letter
            if (c != ' ') { // This needs to ignore spaces
                frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
            }
        }

        // Creating variables
        char mostUsedSymbol = ' ';
        int maxCount = 0;

        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() > maxCount) { // Checking if homework is more than zero or nothing
                maxCount = entry.getValue();
                mostUsedSymbol = entry.getKey();
            }
        }

        return mostUsedSymbol; // Returning result
    }


    @Override
    public void destroy() {
        System.out.println("Closing!"); // Message about closing host
    }
}