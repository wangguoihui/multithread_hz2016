package com.cn.hangzhou.model;

import java.io.Serializable;

public class Ticket implements Serializable {

	public String getCandidate() {
		return candidate;
	}

	public void setCandidate(String candidate) {
		this.candidate = candidate;
	}

	public String getVoter() {
		return voter;
	}

	public void setVoter(String voter) {
		this.voter = voter;
	}

	private static final long serialVersionUID = 1L;
	
	private String candidate;
	
	private String voter;

}
