package com.pb.StudentRecordMgtSys.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Student {
	 @Id
	    private Long rollNumber;
	    private String name;
	    private String grade;
	    
		public Long getRollNumber() {
			return rollNumber;
		}
		public void setRollNumber(Long rollNumber) {
			this.rollNumber = rollNumber;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getGrade() {
			return grade;
		}
		public void setGrade(String grade) {
			this.grade = grade;
		}
	    


}
