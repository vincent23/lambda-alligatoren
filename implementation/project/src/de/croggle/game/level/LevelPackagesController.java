package de.croggle.game.level;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;

import android.content.res.AssetManager;
import de.croggle.AlligatorApp;

/**
 * Controls the overview over the different level packages.
 */
public class LevelPackagesController {

	private AlligatorApp game;
	private List <LevelPackage> levelPackages;
	/**
	 * Creates a new controller with no packages attached.
	 * 
	 * @param game
	 *            the backreference to the central game object
	 * @throws IOException 
	 */
	public LevelPackagesController(AlligatorApp game) throws IOException {
		this.game = game;
		this.initialiseLevelPackages();
	}
	
	/**
	 * Gets the level controller which is responsible for handling the levels
	 * within the level package.
	 * 
	 * @param packageId the Id of the chosen LevelPackage
	 * @return the level controller one must use to handle the levels within the
	 *         level package
	 */
	public LevelController getLevelController(int packageId) {
		return new LevelController(packageId, game);
	}
	
	/**
	 * Gets the levelPackages of the game.
	 * @return a List of levelPackages
	 */
	public List<LevelPackage> getLevelPackages(){
		return this.levelPackages;
	}
	
	
	/**
	 * Method to initialize the levelPackages from the assets.
	 * @throws IOException 
	 */
	private void initialiseLevelPackages() throws IOException{
		AssetManager manager  = game.getContext().getAssets();
		String[] packageNames = null;
		try {
			packageNames = manager.list("json/levels/");
		} catch (IOException e) {
			// TODO 
		}
		int numberOfPackages = packageNames.length-1;
		levelPackages = new ArrayList<LevelPackage>();
		for(int i = 0; i < numberOfPackages; i++ ){
			levelPackages.add(this.loadPackage(i));
		}
		
	}
	
	/**
	 * @param PackageIndex of the Level Package which should be loaded.
	 * @return the Level Package belonging to the given index. 
	 * @throws IOException 
	 */
	private LevelPackage loadPackage(int packageIndex) throws IOException{
		AssetManager manager = game.getContext().getAssets();
		InputStream stream = manager.open("json/levels/"+ String.format("%02d", packageIndex) + "/package.json");
		
		
		JsonReader reader = new JsonReader();
	return null;	
	}

}
