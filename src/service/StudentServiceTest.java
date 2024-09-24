package service;

import model.Course;
import model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {

    private StudentService studentService;
    private CourseService courseService;

    @BeforeEach
    void setUp() {
        studentService = new StudentService();      // create a new instance of StudentService before the test is run
        courseService = new CourseService();        // create a new instance of CourseService before the test is run
    }

    @Test
    void testFindStudent() {
        Student student = studentService.findStudent("stu01");  // find the student stu01 created by StudentService
        assertNotNull(student, "Student should be found");  // Check whether the student exists through assertions to det. we have found the Student
        assertEquals("Adrian", student.getFirstName(), "First name should be Adrian");  // first name should be "Adrian"
        assertEquals("Tan", student.getLastName(), "Last name should be Tan");  // last name should be "Tan"
        assertEquals("adriantan@gmail.com", student.getEmail(), "email should be adriantan@gmail.com"); // email should be adriantan@gmail.com

    }

    @Test
    void testNotFoundStudent(){
        Student student = studentService.findStudent("stu09");
        assertNull(student, "Student should NOT be found");
    }

    @Test
    void testIsSubscribed() {
        assertTrue(studentService.isSubscribed("stu01"));   // Test that the student exists
        assertFalse(studentService.isSubscribed("stu00"));  // Test that the student does NOT exist

    }

    @Test
    void testIsAttendingCourse(){
        Course courseJava = new Course("Introduction to Java", "INTRO-CS-J", 100);
        Student student = new Student("Tony", "Stark", "tonystark@gmail.com", new Date(101, 0, 17), "stu0X");
        student.enrollToCourse(courseJava);
        assertTrue(student.isAttendingCourse("INTRO-CS-J"), "Student should be attending course");
        assertFalse(student.isAttendingCourse("INTRO-CS-X"), "Student should NOT be attending this course");
    }
}

