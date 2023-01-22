package com.simplilearn.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.simplilearn.entity.Student;
import com.simplilearn.util.HibernateUtil;

/**
 * Servlet implementation class ReadStudentServlet
 */
@WebServlet("/read-student")
public class ReadStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReadStudentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter pw = response.getWriter();
		pw.println("<html><body>");
		pw.println("<h1> :- Student Information :- </h1>");
		pw.println("<style> table, td, th {border:2px solid green} </style>");
		pw.println("<table");
		pw.println("<tr>");
		pw.println("<th> Student ID </th>");
		pw.println("<th> Student First Name </th>");
		pw.println("<th> Student Last Name </th>");
		pw.println("<th> Student Phone Numbers </th>");
		pw.println("<th> Student Courses </th>");
		pw.println("<th> Student Address </th>");
		pw.println("</tr>");
		
		// STEP 1: Gets Session Factory
		SessionFactory sf = HibernateUtil.buildSessionFactory();
		Session session = sf.openSession();
		
		List<Student> students = session.createQuery("from Student").list();
		for(Student student : students) {
			pw.println("<tr>");
			pw.println("<td>"+student.getStudent_id()+"</td>");
			pw.println("<td>"+student.getFname()+"</td>");
			pw.println("<td>"+student.getLname()+"</td>");
			pw.println("<td>"+student.getPhoneNumbers()+"</td>");
			pw.println("<td>"+student.getCourses()+"</td>");
			pw.println("<td>"+student.getAddress()+"</td>");
			pw.println("</tr>");
		}
		
		session.close();
		
		pw.println("</body></html>");
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
