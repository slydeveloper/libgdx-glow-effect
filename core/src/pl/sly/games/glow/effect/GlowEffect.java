package pl.sly.games.glow.effect;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import pl.sly.games.glow.effect.screen.GameScreen;

public class GlowEffect extends Game {

	@Override
	public void create () {
		setScreen(new GameScreen());
	}

	@Override
	public void render () {
		super.render();

		if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
			Gdx.app.exit();
		}
	}
}
