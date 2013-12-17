package de.croggle.controller;

import de.croggle.data.Statistic;
/**
 * 
 */
public interface StatisticsDeltaProcessor {
    /**
     * enables different classes to see what changed during one specific level and evaluate the given changes. e.g. Update database of statistics.
     */
    public void processDelta(Statistic statisticsDelta);
}