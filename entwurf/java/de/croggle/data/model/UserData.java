package de.croggle.data.model;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.JsonValue;

public class UserData implements Serializable {

	
	private static final long serialVersionUID = 1L;
	private int time_played;
	private int hints_used;
	private int first_tries;
	private int max_level;

	
	// ...
	
	public UserData(int time_played, int hints_used, int first_tries, int max_level) {
		
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
	
	public int getMax_level() {
		return max_level;
	}

	public void setMax_level(int max_level) {
		this.max_level = max_level;
	}

	@Override
	public void write(Json json) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void read(Json json, JsonValue jsonData) {
		// TODO Auto-generated method stub
		
	}

}
