package com.mack.tia.model;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class SchoolSubject {

	@Id
	@Column(columnDefinition = "BIGINT(20)")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private BigInteger id;
	
	@NotEmpty(message = "{field.required}")
	private String subject;
	
	@ManyToOne
	private User teacher;
	
	@ManyToMany
	private List<User> students = new ArrayList<>();
	
	private int semester;
	
	@NotEmpty(message = "{field.required}")
	private String gradeA;
	
	@NotEmpty(message = "{field.required}")
	private String gradeB;
	
	@NotEmpty(message = "{field.required}")
	private String gradeC;
	
	@NotEmpty(message = "{field.required}")
	private String gradeD;
	
	@NotEmpty(message = "{field.required}")
	private String gradePF;
	
	@Transient
	private BigInteger studentId;

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}
	
	public String getSubjectForDisplay() {
		return "(" + semester + "º Semestre) - " + subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public User getTeacher() {
		return teacher;
	}

	public void setTeacher(User teacher) {
		this.teacher = teacher;
	}

	public List<User> getStudents() {
		return students;
	}

	public void setStudents(List<User> students) {
		this.students = students;
	}

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}

	public BigInteger getStudentId() {
		return studentId;
	}

	public void setStudentId(BigInteger studentId) {
		this.studentId = studentId;
	}

	public String getGradeA() {
		return gradeA;
	}

	public void setGradeA(String gradeA) {
		this.gradeA = gradeA;
	}

	public String getGradeB() {
		return gradeB;
	}

	public void setGradeB(String gradeB) {
		this.gradeB = gradeB;
	}

	public String getGradeC() {
		return gradeC;
	}

	public void setGradeC(String gradeC) {
		this.gradeC = gradeC;
	}

	public String getGradeD() {
		return gradeD;
	}

	public void setGradeD(String gradeD) {
		this.gradeD = gradeD;
	}

	public String getGradePF() {
		return gradePF;
	}

	public void setGradePF(String gradePF) {
		this.gradePF = gradePF;
	}
}
