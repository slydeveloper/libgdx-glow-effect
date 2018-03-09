package pl.sly.games.glow.effect.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import pl.sly.games.glow.effect.GlowEffect;
import pl.sly.games.glow.effect.config.Config;

public class DesktopLauncher {

	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Config.WIDTH;
		config.height = Config.HEIGHT;
		config.title = Config.WINDOW_TITLE;
		config.resizable = Config.WINDOW_RESIZABLE;
		config.fullscreen = Config.WINDOW_FULLSCREEN;
		new LwjglApplication(new GlowEffect(), config);
	}
}
