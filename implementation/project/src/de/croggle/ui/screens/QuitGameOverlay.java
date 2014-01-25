package de.croggle.ui.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import de.croggle.AlligatorApp;

public class QuitGameOverlay implements Screen {
	private ShapeRenderer shapes;
	private final Color shade;
	private final Screen screenBelow;
	private final Stage stage;
	private final Table table;
	private final OrthographicCamera camera;
	private final AlligatorApp game;
	private InputMultiplexer inputMediator;
	
	public QuitGameOverlay(AlligatorApp game, Screen screenBelow) {
		if (screenBelow == null) {
			throw new IllegalArgumentException("Cannot overlay no screen");
		}
		stage = new Stage();
		this.game = game;
		this.screenBelow = screenBelow;
		shade = new Color(0, 0, 0, .5f);
		table = new Table();
		table.setFillParent(true);
		stage.addActor(table);
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1024, 600);
		// make the screen as well as the stage an input processor
		inputMediator = new InputMultiplexer(stage, new BackButtonHandler());
	}
	
	@Override
	public void render(float delta) {
		screenBelow.render(delta);
		
		Gdx.gl.glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
	    shapes.setProjectionMatrix(camera.combined);
		shapes.begin(ShapeType.Filled);
		shapes.setColor(shade);
		shapes.rect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		shapes.end();
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		//stage = new Stage();
		shapes = new ShapeRenderer();
		Gdx.input.setInputProcessor(inputMediator);
		camera.update();
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		//stage.dispose();
		shapes.dispose();
	}
	
	private class BackButtonHandler extends InputAdapter {
		@Override
		public boolean keyUp(int keycode) {
			if (keycode == Keys.BACK) {
				game.setScreen(screenBelow);
				return true;
			}
			return false;
		}

	}
	
}
