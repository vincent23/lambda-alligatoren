package de.croggle.ui.screens;

import de.croggle.AlligatorApp;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

/**
*
*/
public abstract class AbstractScreen implements Screen{

        private        AlligatorApp game;
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
