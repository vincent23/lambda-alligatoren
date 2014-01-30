package de.croggle.data.persistence;

/**
 * A listener that is to be implemented by all classes that need to be updated if the setting is changed.
 */
public interface SettingChangeListener {
	
	/**
	 * Process the new setting.
	 * @param setting the new setting
	 */
	public void onSettingChange(Setting setting);

}
