package com.simplilearn.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.simplilearn.entity.Address;
import com.simplilearn.entity.Courses;
import com.simplilearn.entity.PhoneNumber;
import com.simplilearn.entity.Student;
import com.simplilearn.util.HibernateUtil;

/**
 * Servlet implementation class AddStudentServlet
 */
@WebServlet("/add-student")
public class AddStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddStudentServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("add-student.html").include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Step 1: Read data and prepare object
		Student student = populateStudent(request);

		// Step 2: Use hibernate to store data in db

		SessionFactory sf = HibernateUtil.buildSessionFactory();

		Session session = sf.openSession();

		Transaction txn = session.beginTransaction();
		session.save(student);
		txn.commit();
		request.getRequestDispatcher("/read-student").include(request, response);
	}

	private Student populateStudent(HttpServletRequest request) {
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");

		String number1 = request.getParameter("phone_1");
		String type1 = request.getParameter("type_1");

		String number2 = request.getParameter("phone_2");
		String type2 = request.getParameter("type_2");

		String number3 = request.getParameter("phone_3");
		String type3 = request.getParameter("type_3");
		
		String course_text1 = request.getParameter("course_1");
		String course_type_1 = request.getParameter("course_type_1");
		
		String course_text2 = request.getParameter("course_2");
		String course_type_2 = request.getParameter("course_type_2");
		
		String course_text3 = request.getParameter("course_3");
		String course_type_3 = request.getParameter("course_type_3");
		
		String street = request.getParameter("street");
		String city = request.getParameter("city");
		String state = request.getParameter("state");

		Student student = new Student();

		PhoneNumber phone1 = new PhoneNumber();
		phone1.setPhoneNumber(number1);
		phone1.setPhoneType(type1);	
		phone1.setStudent(student);

		PhoneNumber phone2 = new PhoneNumber();
		phone2.setPhoneNumber(number2);
		phone2.setPhoneType(type2);
		phone2.setStudent(student);

		PhoneNumber phone3 = new PhoneNumber();
		phone3.setPhoneNumber(number3);
		phone3.setPhoneType(type3);
		phone3.setStudent(student);

		List<PhoneNumber> phones = new ArrayList<PhoneNumber>();
		phones.add(phone1);
		phones.add(phone2);
		phones.add(phone3);

		student.setFname(fname);
		student.setLname(lname);
		student.setPhoneNumbers(phones);
		
		// Adding logic to add Courses
		List<Student> students = new ArrayList<Student>();
		students.add(student);
		
		List<Courses> courses = new ArrayList<Courses>();
		Courses course1 = new Courses();
		course1.setCourseName(course_text1);
		course1.setCourseType(course_type_1);
		course1.setStudents(students);
		courses.add(course1);
		
		Courses course2 = new Courses();
		course2.setCourseName(course_text2);
		course2.setCourseType(course_type_2);
		course2.setStudents(students);
		courses.add(course2);
		
		
		Courses course3 = new Courses();
		course3.setCourseName(course_text3);
		course3.setCourseType(course_type_3);
		course3.setStudents(students);
		courses.add(course3);
		
		student.setCourses(courses);
		
		// Populate Address
		Address address = new Address();
		address.setCity(city);
		address.setState(state);
		address.setStreet(street);
		
		student.setAddress(address);
		
		return student;
	}

}
