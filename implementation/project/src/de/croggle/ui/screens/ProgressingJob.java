package de.croggle.ui.screens;

public interface ProgressingJob {
	/**
	 * Make progress on the {@link ProgressingJob}'s task.
	 * 
	 * @return true, if all work was completed
	 */
	boolean update();
	
	float getProgress();
	
	/**
	 * Complete the job. Blocks the thread until finished.
	 */
	void finish();
}
