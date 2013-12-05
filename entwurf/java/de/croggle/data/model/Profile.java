package de.croggle.data.model;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.JsonValue;

/**
 * @navassoc 1 Has 1 de.croggle.data.model.UserData
 * @navassoc 1 Has 1 de.croggle.data.model.Settings
 */

public class Profile implements Serializable {
	
	private String 	name;
	private String 	picture_path;
	private long 	profile_id;
	
	public Profile(String name, String picture_path, long id) {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPicture_path() {
		return picture_path;
	}

	public void setPicture_path(String picture_path) {
		this.picture_path = picture_path;
	}

	

	public long getProfile_id() {
		return profile_id;
	}

	public void setProfile_id(long profile_id) {
		this.profile_id = profile_id;
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

