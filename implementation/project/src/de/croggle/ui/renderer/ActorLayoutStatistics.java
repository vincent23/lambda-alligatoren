package de.croggle.ui.renderer;

import java.util.Map;

import de.croggle.game.board.BoardObject;

public class ActorLayoutStatistics {
	private final Map<BoardObject, Float> widthMap;
	private final Map<BoardObject, Float> heightMap;
	
	public ActorLayoutStatistics(Map<BoardObject, Float> widthMap, Map<BoardObject, Float> heightMap) {
		this.widthMap = widthMap;
		this.heightMap = heightMap;
	}

	public Map<BoardObject, Float> getWidthMap() {
		return widthMap;
	}

	public Map<BoardObject, Float> getHeightMap() {
		return heightMap;
	}
}
