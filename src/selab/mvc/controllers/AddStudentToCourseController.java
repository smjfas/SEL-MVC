package selab.mvc.controllers;

import org.json.JSONObject;
import selab.mvc.models.DataContext;
import selab.mvc.models.DataSet;
import selab.mvc.models.entities.Course;
import selab.mvc.models.entities.Student;
import selab.mvc.views.JsonView;
import selab.mvc.views.View;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class AddStudentToCourseController extends Controller {

    DataSet<Student> studentDataSet;
    DataSet<Course> courseDataSet;

    public AddStudentToCourseController(DataContext dataContext) {
        super(dataContext);
        this.studentDataSet = dataContext.getStudents();
        this.courseDataSet = dataContext.getCourses();
    }

    @Override
    public View exec(String method, InputStream body) throws IOException {
        if (!method.equals("POST"))
            throw new IOException("Method not supported");

        JSONObject input = readJson(body);
        String studentNo = input.getString("studentNo");
        String courseNo = input.getString("courseNo");
        String points = input.getString("points");

        Student student = this.studentDataSet.get(studentNo);
        Course course = this.courseDataSet.get(courseNo);
        Float grade = Float.parseFloat(points);
        student.addCourse(course, grade);
        course.addStudent(student, grade);

        Map<String, String> result = new HashMap<>();
        result.put("success", "true");
        return new JsonView(new JSONObject(result));
    }
}
