package de.croggle.ui.screens;

/**
 * Temporary representative of libgdx' Screen
 */
public interface Screen{

void dispose();
void hide();
void pause();
void render(float delta);
void resize(int width, int height);
void resume();
void show();
	
}
