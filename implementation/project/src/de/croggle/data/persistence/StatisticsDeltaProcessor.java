package de.croggle.data.persistence;

/**
 * A listener that is to be implemented by all classes that need to process data
 * from the statistic values that occur during a level.
 */
public interface StatisticsDeltaProcessor {
	/**
	 * Evaluates and processes the statistic changes that occurred during a
	 * level, e.g. updates the database. The changes are packed as a Statistic
	 * object. The statisticsDelta is changed afterwards, so no references to it
	 * should be held.
	 * 
	 * @param statisticsDelta
	 *            the packed statistic changes
	 */
	public void processDelta(Statistic statisticsDelta);
}
