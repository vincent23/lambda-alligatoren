package de.croggle.test;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import de.croggle.backends.LocalizationBackend;

public class TestLocalizationBackend implements LocalizationBackend {

	private Locale c;
	
	private Map<String, String[]> arrays;
	private Map<String, String> strings;
	private Map<String, Map<Integer, String>> quantities;
	
	public TestLocalizationBackend() {
		this.c = Locale.getDefault();
		arrays = new HashMap<String, String[]>();
		strings = new HashMap<String, String>();
		quantities = new HashMap<String, Map<Integer,String>>();
	}
	
	public void putString(String key, String val) {
		strings.put(key, val);
	}
	
	public void putArray(String key, String[] val) {
		arrays.put(key, val);
	}
	
	public void putQuantities(String key, Map<Integer, String> val) {
		quantities.put(key, val);
	}
	
	@Override
	public String translate(String s) {
		return strings.get(s);
	}

	@Override
	public String translate(String s, int multiplicity) {
		return quantities.get(s).get(multiplicity);
	}

	@Override
	public String[] getLocalizedStringList(String identifier) {
		return arrays.get(identifier);
	}

	@Override
	public void setApplicationLocale(Locale locale) {
		this.c = locale;
	}

	@Override
	public Locale getApplicationLocale() {
		return c;
	}

	@Override
	public Locale getSystemLocale() {
		return Locale.getDefault();
	}

}
