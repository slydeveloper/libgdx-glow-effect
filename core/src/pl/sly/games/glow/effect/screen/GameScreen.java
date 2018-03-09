package pl.sly.games.glow.effect.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

import pl.sly.games.glow.effect.screen.base.BaseScreen;
import pl.sly.games.glow.effect.sprite.RingSprite;

public class GameScreen extends BaseScreen {

    private List<RingSprite> ringSprites = new ArrayList<RingSprite>();
    private SpriteBatch spriteBatch;

    public GameScreen() {
        spriteBatch = new SpriteBatch();

        RingSprite ringSpriteRed = new RingSprite(viewport, spriteBatch);
        ringSpriteRed.randomSpeed();

        RingSprite ringSpriteGreen = new RingSprite(viewport, spriteBatch);
        ringSpriteGreen.setColor(Color.GREEN);
        ringSpriteGreen.setDirection(RingSprite.Direction.TOP_RIGHT);
        ringSpriteGreen.randomSpeed();

        RingSprite ringSpriteBlue = new RingSprite(viewport, spriteBatch);
        ringSpriteBlue.setColor(Color.BLUE);
        ringSpriteBlue.setDirection(RingSprite.Direction.BOTTOM_RIGHT);
        ringSpriteBlue.randomSpeed();

        RingSprite ringSpriteYellow = new RingSprite(viewport, spriteBatch);
        ringSpriteYellow.setColor(Color.YELLOW);
        ringSpriteYellow.setDirection(RingSprite.Direction.BOTTOM_LEFT);
        ringSpriteYellow.randomSpeed();

        ringSprites.add(ringSpriteRed);
        ringSprites.add(ringSpriteGreen);
        ringSprites.add(ringSpriteBlue);
        ringSprites.add(ringSpriteYellow);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        for (RingSprite ringSprite : ringSprites) {
            ringSprite.render(delta);
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        spriteBatch.dispose();

        for (RingSprite ringSprite : ringSprites) {
            ringSprite.dispose();
        }
    }
}
