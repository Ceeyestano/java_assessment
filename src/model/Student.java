package model;

import model.Course;

import java.util.*;

public class Student implements Evaluation {


    // attributes for the student
    String firstName;       // first name
    String lastName;        // last name
    String email;           // email
    Date birthDate;         // DOB
    int grade;              // grade
    String id;              // id
    double average;         // average

    ArrayList<Course> enrolledCourses = new ArrayList<>();  // student's enrolled courses

    Map<String, Course> approvedCourses = new HashMap<>();     // approved courses taken by the student
    Map<String, Integer> courseGrade = new HashMap<>();

    public Student(String firstName,                        // constructor to create an instance of a student
                   String lastName,
                   String email,
                   Date birthDate,
                   String id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthDate = birthDate;
        this.grade = 0;
        this.id = id;
    }

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public int getGrade(Student student) {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean enrollToCourse(Course course){

        // TODO 1: if the course is not found in Hashmap approvedCourses
        // TODO 2: it to the student's approvedCourses
        if(!approvedCourses.containsKey(course.getId()))        // Hashmap key: value pair
            approvedCourses.put(course.getId(), course);

        return enrolledCourses.add(course);
    }

    public boolean unEnrollCourse(Course course){
        return enrolledCourses.remove(course);
    }

    public int totalEnrolledCourses(){
        return enrolledCourses.size();
    }

    public void printFullName(){
        System.out.println(this.firstName + " " + this.lastName);
    }

    public ArrayList<Course> getEnrolledCourses(){
        return enrolledCourses;
    }

    // Method to set the grade for a specific course
    public void setCourseGrade(String courseId, int grade) {
        if (isAttendingCourse(courseId)) {
            courseGrade.put(courseId, grade);
        } else {
            System.out.println("Student is not enrolled in this course.");
        }
    }
    // Method to get the grade for a specific course
    public Integer getCourseGrade(String courseId) {
        return courseGrade.get(courseId);
    }

    public String getGradeResult(){
        String result = "";
        List<Course> passedCourses = findPassedCourses();
        if (passedCourses.isEmpty()) result = "fail";
        if (!passedCourses.isEmpty()) result = "pass";
        return result;
    }

    public List<Course> findPassedCourses() {
        List<Course> passedCourses = new ArrayList<>();
        for (Course course : enrolledCourses) {
            Integer grade = courseGrade.get(course.getId());
            if (grade >= 50) { // Assuming 50 is the passing grade
                passedCourses.add(course);
            }
        }
        return passedCourses;
    }


    @Override                                              // Overriding the default toString() method
    public String toString(){                              // of a java class
        return "Student{'" +
                "firstName':'" +
                firstName + "', '" +
                "lastName':'" +
                lastName + "', '" +
                "grade':" +
                grade + ", '" +
                "email':" +
                email + ", '" +
                "birth date':" +
                birthDate + ", '" +
                "id':'" +
                id + "'}";
    }


    @Override
    public double getAverage() {
        return 0;
    }

    @Override
    public List<Course> getApprovedCourses() {

        // TODO: implement this method
        // TODO 1: return the list of courses that are found in approved courses
        ArrayList<Course> approvedCourses = new ArrayList<>();
        boolean status = approvedCourses.addAll(this.approvedCourses.values());

        if (!status){
            return null;
        }

        return approvedCourses;
    }

    public boolean isApproved (String courseId){
        return approvedCourses.containsKey(courseId);
    }

    public boolean isAttendingCourse (String courseId){
        // TODO: return true if the student is attending the course by courseId
        // TODO: use enrolledCourses to determine if the student is attending

            Course course = approvedCourses.get(courseId);
            return enrolledCourses.contains(course);
        }

//        boolean result = false;
//
//        Course course = approvedCourses.get(courseId);
//
//        for ( int i = 0; i < enrolledCourses.size(); i++) {
//            if (course == enrolledCourses.get(i)) {
//                result = true;
//                break;
//            }
//        }
//        return result;
//    }

}
