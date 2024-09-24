package service;

import model.Course;
import model.Evaluation;
import model.Student;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;


public class StudentService
{
    private final Map<String, Student> students = new HashMap<>();


    public StudentService() {
        subscribeStudent( new Student( "Adrian", "Tan", "adriantan@gmail.com", new Date(101,0,17),"stu01")); // 2001 Jan 17
        subscribeStudent( new Student( "Goh", "Hui Xin", "gohhuixin@hotmail.com", new Date(102,8,4), "stu02") ); // 2002 Sep 4
        subscribeStudent( new Student( "Alex", "Lim", "alexlim@gmail.com", new Date(103,4,30), "stu03") ); // 2003 May 30
    }


    public void displayAverageGrade(String courseId) {
        int totalGrade = 0;
        int count = 0;
        for (Student student : students.values()) {
            Integer grade = student.getCourseGrade(courseId);
            if (grade != null) {
                totalGrade += grade;
                count++;
            }
        }
        if (count > 0) {
            Integer average = (Integer) totalGrade / count;
            System.out.println("Average grade for course " + courseId + ": " + average);
        } else {
            System.out.println("No students enrolled in course " + courseId + " or no grades available.");
        }
    }




    public void subscribeStudent( Student student )

    {
        students.put( student.getId(), student );
    }




    public Student findStudent(String studentId )
    {
        if ( students.containsKey( studentId ) )
        {
            return students.get( studentId );
        }
        return null;
    }

    public boolean isSubscribed( String studentId )
    {
        //TODO implement this method
        return students.containsKey(studentId);
    }

    public void showSummary()
    {
        //TODO implement
        //TODO 1. display a title for this feature
        //TODO 2. list each student AND the course(s) the student is enrolled into approvedCourses
        System.out.println("Students and the courses enrolled into");
        for (String key: students.keySet()){
            Student student = students.get(key);
            student.printFullName();

            List<Course> courses = student.getApprovedCourses();

            if(courses == null) {
                System.out.println("\t\t No Course Found.");
            }else{
                for(Course course: courses){
                    System.out.println("\t\t" + course);
                }
            }


        }
    }

    public void enrollToCourse( String studentId, Course course )
    {
        if ( students.containsKey( studentId ) )
        {
            Student student = students.get( studentId );
            student.enrollToCourse( course );

            // students.get(studentId).enrollToCourse(course);
        }
    }

    public void setStudentScore(String courseId, String studentId, int grade) {
        students.get(studentId).setCourseGrade(courseId, grade);
        students.get(studentId).setGrade(grade);
    }

    public String getStudentResult(String studentId) {
        return students.get(studentId).getGradeResult();
    }

    public double calAverage(String courseId) {
        double sum = 0, count = 0, average;
        for (Student student : students.values()){
            Integer grade = student.getCourseGrade(courseId);
            if (grade != null) {
                sum += grade;
                count++;
            }
        }
        average = sum/count;
        return average;
    }
}
