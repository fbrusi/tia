package com.mack.tia.model;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class Grade implements Comparable<Grade> {

	@Id
	@Column(columnDefinition = "BIGINT(20)")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private BigInteger id;
	
	@ManyToOne
	private User student;
	
	@ManyToOne
	private SchoolSubject schoolSubject;
	
	private String gradeA;
	
	private String gradeB;
	
	private String gradeC;
	
	private String gradeD;
	
	private String gradePF;
	
	@Transient
	private boolean hasErrorGradeA;
	
	@Transient
	private boolean hasErrorGradeB;
	
	@Transient
	private boolean hasErrorGradeC;
	
	@Transient
	private boolean hasErrorGradeD;
	
	@Transient
	private boolean hasErrorGradePF;

	@Override
	public int compareTo(Grade grade) {
		return student.compareTo(grade.student);
	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public User getStudent() {
		return student;
	}

	public void setStudent(User student) {
		this.student = student;
	}

	public SchoolSubject getSchoolSubject() {
		return schoolSubject;
	}

	public void setSchoolSubject(SchoolSubject schoolSubject) {
		this.schoolSubject = schoolSubject;
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

	public boolean isHasErrorGradeA() {
		return hasErrorGradeA;
	}

	public void setHasErrorGradeA(boolean hasErrorGradeA) {
		this.hasErrorGradeA = hasErrorGradeA;
	}

	public boolean isHasErrorGradeB() {
		return hasErrorGradeB;
	}

	public void setHasErrorGradeB(boolean hasErrorGradeB) {
		this.hasErrorGradeB = hasErrorGradeB;
	}

	public boolean isHasErrorGradeC() {
		return hasErrorGradeC;
	}

	public void setHasErrorGradeC(boolean hasErrorGradeC) {
		this.hasErrorGradeC = hasErrorGradeC;
	}

	public boolean isHasErrorGradeD() {
		return hasErrorGradeD;
	}

	public void setHasErrorGradeD(boolean hasErrorGradeD) {
		this.hasErrorGradeD = hasErrorGradeD;
	}

	public boolean isHasErrorGradePF() {
		return hasErrorGradePF;
	}

	public void setHasErrorGradePF(boolean hasErrorGradePF) {
		this.hasErrorGradePF = hasErrorGradePF;
	}
}
