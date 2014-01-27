package de.croggle.ui.renderer;

import java.util.Map;

import de.croggle.game.board.BoardObject;
import de.croggle.game.board.operations.CreateHeightMap;
import de.croggle.game.board.operations.CreateWidthMap;

public class ActorLayoutStatistics {
	private Map<BoardObject, Float> widthMap;
	private Map<BoardObject, Float> heightMap;
	private final ActorLayout layout;

	public ActorLayoutStatistics(ActorLayout l) {
		layout = l;
	}

	public Map<BoardObject, Float> getWidthMap() {
		return widthMap;
	}

	public Map<BoardObject, Float> getHeightMap() {
		return heightMap;
	}

	void setWidthMap(Map<BoardObject, Float> widthMap) {
		this.widthMap = widthMap;
	}

	void setHeightMap(Map<BoardObject, Float> heightMap) {
		this.heightMap = heightMap;
	}

	public void rebuild() {
		ActorLayoutConfiguration config = layout.getLayoutConfiguration();
		widthMap = CreateWidthMap.create(layout.getBoard(),
				config.getUniformObjectWidth(),
				config.getVerticalScaleFactor(), config.getHorizontalPadding());
		heightMap = CreateHeightMap.create(layout.getBoard(),
				config.getUniformObjectHeight(),
				config.getVerticalScaleFactor(), config.getVerticalPadding());
	}
}
