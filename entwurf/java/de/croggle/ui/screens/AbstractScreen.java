package de.croggle.ui.screens;

import de.croggle.AlligatorApp;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

/**
* Abstract screen, with all the basic things a screen needs.
*/
public abstract class AbstractScreen implements Screen{

        private AlligatorApp game;
        private Stage stage;
        private Table table;

        /**
         * Called in order to cause the screen to release all resources held.
         */
        void dispose();
        /**
         * Called when this screen should no longer be the game's current screen.
         */
        void hide();
        /**
         * Called when this screen is paused. A screen is paused before it is destroyed, when the user pressed the Home button or an incoming call happend
         */
        void pause();
        /**
         * Called when the screen should render itself.
         */
        void render(float delta);
        /**
         * Called in order to cause the screen to resize itself into the given size.
         * @param width the width, which the newly resized screen will have.
         * @param height the height, which the newly resized screen will have.
         */
        void resize(int width, int height);
        /**
         * Called in order to move the screen back from its paused state
         */
        void resume();
        /**
         * Called when this screen should be the game's current screen.
         */
        void show();
}
