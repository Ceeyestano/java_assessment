package service;

import model.Course;
import model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CourseServiceTest {

    private CourseService courseService;        // private instance
    private StudentService studentService;

    @BeforeEach
    void setUp() {
        courseService = new CourseService();    // initialise courseService
        studentService = new StudentService();  // initialise studentService
    }

    @Test
    void testEnrollStudent() {
        Student student = studentService.findStudent("stu01");
        courseService.enrollStudent("INTRO-CS-1", student);

        List<Student> enrolledStudents = courseService.enrolledStudents.get("INTRO-CS-1");
        assertNotNull(enrolledStudents, "List of enrolled students should not be null");
        assertEquals(1, enrolledStudents.size(), "Enrolled student size should be one");
    }

    @Test
    void testGetCourse() {
        Course course = courseService.getCourse("INTRO-CS-5");

        assertNotNull(course, "Course should be found");
        assertEquals("INTRO-CS-5", course.getId(), "Course Id should match");
        assertNotEquals("INTRO-CS-11", course.getId(), "Course Id should not match");


    }
}
