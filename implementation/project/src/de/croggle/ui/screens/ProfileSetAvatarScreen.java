package de.croggle.ui.screens;

import static de.croggle.data.LocalizationHelper._;

import android.util.Log;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;

import de.croggle.AlligatorApp;
import de.croggle.game.profile.Profile;
import de.croggle.game.profile.ProfileController;
import de.croggle.game.profile.ProfileOverflowException;
import de.croggle.ui.StyleHelper;

/**
 * Screen which is used for both creating a new account with a given avatar as
 * well as changing the avatar of an existing account. For reference see
 * ``Pflichtenheft 10.5.14 / Abbildung 23''.
 */
public class ProfileSetAvatarScreen extends AbstractScreen {

	private static final int AVATARS_PER_ROW = 3;

	private ProfileController profileController;

	private String profileName = "";
	private String picturePath;
	private ImageButton lastClicked;
	private ImageButton defaultButton = null;
	private boolean isInEditMode = false;

	/**
	 * Creates the screen that is shown to the player while changing his player
	 * avatar.
	 * 
	 * @param game
	 *            the backreference to the central game
	 * @param controller
	 *            the profile controller, which is responsible for the currently
	 *            selected profile
	 */
	public ProfileSetAvatarScreen(AlligatorApp game) {
		super(game);
		profileController = game.getProfileController();
	}

	@Override
	protected void initializeWidgets() {
		super.initializeWidgets();
		fillTable();
	}

	private void fillTable() {
		StyleHelper helper = StyleHelper.getInstance();
		Table innerTable = new Table();
		Table leftTable = new Table();
		Label chooseAvatar = new Label(_("screen_title_set_avatar"),
				helper.getBlackLabelStyle());
		ImageButton confirm = new ImageButton(
				helper.getImageButtonStyleRound("widgets/icon-check"));

		Array<AtlasRegion> list = helper.getSkin().getAtlas().getRegions();

		// TODO fit this to freely add profile pics (by scrollpane etc)
		leftTable.add(chooseAvatar).expandX().left().top().padLeft(100)
				.space(30).height(50).colspan(AVATARS_PER_ROW);
		leftTable.row();
		for (AtlasRegion region : list) {
			String path = region.name;
			if (region.name.matches("avatar/.*")) {
				ImageButton avatarButton = new ImageButton(
						helper.getImageButtonStyle(path));
				avatarButton.setName(path);
				avatarButton.setColor(Color.GREEN);
				avatarButton.addListener(new ClickListener() {
					@Override
					public void clicked(InputEvent event, float x, float y) {
						ImageButton button = (ImageButton) event
								.getListenerActor();
						picturePath = button.getName();
						// "Highlight" the clicked button. Should be changed.
						lastClicked.setColor(Color.GREEN);
						button.setColor(Color.GRAY);
						lastClicked = button;
					};
				});
				if (defaultButton == null) {
					avatarButton.setColor(Color.GRAY);
					path = avatarButton.getName();
					lastClicked = avatarButton;
					defaultButton = avatarButton;
				}
				leftTable.add(avatarButton).uniform().size(150);
			}
		}

		innerTable.setBackground(helper
				.getDrawable("widgets/dialog-background"));
		innerTable.pad(30);
		innerTable.add(leftTable).expand().fill();
		innerTable.add(confirm).size(100).expandY().bottom();

		confirm.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {

				if (isInEditMode) {
					profileController.editCurrentProfile(
							profileController.getCurrentProfileName(),
							picturePath);
					isInEditMode = false;
					game.showSettingsScreen(false);
				} else {
					try {
						profileController.createNewProfile(profileName,
								picturePath);
						Log.d("statistic trace", "Statistic in ProfileSetAvatar: Playtime:" + profileController.getCurrentProfile().getStatistic().getPlaytime()); // TODO remove debug code
						Log.d("statistic trace", "Statistic in ProfileSetAvatar: Alligators Eaten " + profileController.getCurrentProfile().getStatistic().getAlligatorsEaten() );
						Log.d("statistic trace", "Statistic in ProfileSetAvatar: Alligators placed " + profileController.getCurrentProfile().getStatistic().getAlligatorsPlaced() );
						Log.d("statistic trace", "Statistic in ProfileSetAvatar: LevelsCompleted " + profileController.getCurrentProfile().getStatistic().getLevelsComplete() );
						game.showMainMenuScreen(false);
					} catch (ProfileOverflowException p) {
						// TODO
					}
				}

			}
		});

		table.add(innerTable).width(700).height(350);
	}

	@Override
	public void onShow() {
		lastClicked.setColor(Color.GREEN);
		defaultButton.setColor(Color.GRAY);
		lastClicked = defaultButton;
		picturePath = defaultButton.getName();
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

	public void setInEditMode(boolean isInEditMode) {
		this.isInEditMode = isInEditMode;
	}

}
