package de.croggle.data.model;

import java.io.Serializable;

/**
 * 
 */
public class UserData implements Serializable {

	
	private static final long serialVersionUID = 1L;
	private int time_played;
	private int hints_used;
	private int first_tries;
	
	// ...
	
	public UserData(int time_played, int hints_used, int first_tries) {
		
	}

	public int getTime_played() {
		return time_played;
	}

	public void setTime_played(int time_played) {
		this.time_played = time_played;
	}

	public int getHints_used() {
		return hints_used;
	}

	public void setHints_used(int hints_used) {
		this.hints_used = hints_used;
	}

	public int getFirst_tries() {
		return first_tries;
	}

	public void setFirst_tries(int first_tries) {
		this.first_tries = first_tries;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
