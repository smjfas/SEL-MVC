package selab.mvc.models.entities;

import selab.mvc.models.Model;

import java.util.ArrayList;
import java.util.Objects;
import java.util.regex.Pattern;

public class Student implements Model {
    private String name;
    private String studentNo;
    private ArrayList<Course> courses = new ArrayList<>();
    private ArrayList<Float> grades = new ArrayList<>();

    public void addCourse(Course course, Float grade){
        if (this.courses.contains(course) || grade > 20.0 || grade < 0.0){
            return;
        }
        this.courses.add(course);
        this.grades.add(grade);
    }

    @Override
    public String getPrimaryKey() {
        return this.studentNo;
    }

    public void setName(String value) { this.name = value; }
    public String getName() { return this.name; }

    public void setStudentNo(String value) {
        if (!validateStudentNo(value))
            throw new IllegalArgumentException("The format is not correct");

        this.studentNo = value;
    }
    public String getStudentNo() { return this.studentNo; }

    public float getAverage() {
        // TODO: Calculate and return the average of the points
        return 0;
    }

    public String getCourses() {
        // TODO: Return a comma separated list of course names
        StringBuilder result = new StringBuilder();
        for (Course course : this.courses) {
            result.append(course.getTitle() + ",");
        }
        result.setLength(result.length()-1);
        return result.toString();
    }

    /**
     *
     * @param studentNo Student number to be checeked
     * @return true, if the format of the student number is correct
     */
    private boolean validateStudentNo(String studentNo) {
        Pattern pattern = Pattern.compile("^[8-9]\\d{7}$");
        return pattern.matcher(studentNo).find();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return studentNo.equals(student.studentNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentNo);
    }
}
