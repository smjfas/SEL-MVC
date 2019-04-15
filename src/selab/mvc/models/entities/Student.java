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

    public void deleteCourse(Course course){
        if (!this.courses.contains(course)){
            return;
        }
        int id = this.courses.indexOf(course);
        this.courses.remove(id);
        this.grades.remove(id);
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
        if (this.grades.size()==0){
            return 0;
        }
        float average = (float) 0.0;
        for (Float grade:this.grades
        ) {
            average += grade;
        }
        average /= this.grades.size();
        return average;
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
