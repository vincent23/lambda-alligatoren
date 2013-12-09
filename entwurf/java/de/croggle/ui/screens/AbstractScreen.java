package de.croggle.ui.screens;

/**
 * 
 */
public abstract class AbstractScreen implements Screen{

	private	AlligatorApp game;
	private Stage stage;
	private Table table;
	
	void dispose();
	void hide();
	void pause();
	void render(float delta);
	void resize(int width, int height);
	void resume();
	void show();
	
}
