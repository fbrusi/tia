package com.mack.tia.constant;

public enum Profile {

	ROLE_ALUNO("Aluno"),
	ROLE_PROFESSOR("Professor");

	private String profile;
	
	private Profile(String profile) {
		this.profile = profile;
	}
	
	public String getProfile() {
		return profile;
	}
}
