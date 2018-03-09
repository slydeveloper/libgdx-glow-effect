package pl.sly.games.glow.effect.sprite.base;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;

public abstract class BaseSprite {

    protected Viewport viewport;
    protected SpriteBatch spriteBatch;

    public BaseSprite(Viewport viewport, SpriteBatch spriteBatch) {
        this.viewport = viewport;
        this.spriteBatch = spriteBatch;
    }

    public abstract void render(float delta);

    public abstract void dispose();
}
