package pl.sly.games.glow.effect.sprite;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.Random;

import pl.sly.games.glow.effect.sprite.base.BaseSprite;

public class RingSprite extends BaseSprite {

    public enum Direction {
        TOP_LEFT, TOP_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT
    }

    private static final String TEXTURE_RING = "ring.png";
    private static final String TEXTURE_GLOW = "ring_glow.png";
    private static final float GLOW_LIFE_TIME = 0.15f;
    private static final float GLOW_DEFAULT = 0.75f;
    private static final float GLOW_SINE_PERIOD = 2.0f;
    private static final int RING_MARGIN = 24;
    private static final int MIN_SPEED = 150;
    private static final int MAX_SPEED = 250;

    private Texture ringTexture;
    private Texture glowTexture;
    private Vector2 position;
    private Vector2 velocity;

    private float alpha = 0.0f;
    private float elapsedTime = 0.0f;
    private float glowProgress = 0.0f;
    private boolean isGlowing = false;

    private Direction direction = Direction.TOP_LEFT;
    private Color color = Color.RED;
    private float speedX = 150;
    private float speedY = 150;

    public RingSprite(Viewport viewport, SpriteBatch spriteBatch) {
        super(viewport, spriteBatch);
        ringTexture = new Texture(TEXTURE_RING);
        glowTexture = new Texture(TEXTURE_GLOW);
        position = new Vector2(
                viewport.getWorldWidth() / 2 - glowTexture.getWidth() / 2,
                viewport.getWorldHeight() / 2 - glowTexture.getHeight() / 2);
        velocity = new Vector2(0, 0);
    }

    @Override
    public void render(float delta) {
        interpolateAlpha(delta);

        // draw extended glow texture with color filter
        spriteBatch.begin();
        spriteBatch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE);
        spriteBatch.setColor(color.r, color.g, color.b, alpha);
        spriteBatch.draw(glowTexture, position.x, position.y);
        spriteBatch.end();

        // draw default glow texture with color filter
        spriteBatch.begin();
        spriteBatch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE);
        spriteBatch.setColor(color.r, color.g, color.b, GLOW_DEFAULT);
        spriteBatch.draw(glowTexture, position.x, position.y);
        spriteBatch.end();

        //draw white ring
        spriteBatch.begin();
        spriteBatch.setColor(Color.WHITE);
        spriteBatch.draw(ringTexture, position.x, position.y);
        spriteBatch.end();

        move(delta);
    }

    @Override
    public void dispose() {
        ringTexture.dispose();
        glowTexture.dispose();
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void showGlowEffect() {
        isGlowing = true;
        elapsedTime = 0;
    }

    public void randomSpeed() {
        speedX = generateRandomSpeed();
        speedY = generateRandomSpeed();
    }

    public int generateRandomSpeed() {
        return new Random().nextInt((MAX_SPEED - MIN_SPEED) + 1) + MIN_SPEED;
    }

    private void interpolateAlpha(float delta) {
        if (isGlowing) {
            glowProgress = Math.min(GLOW_SINE_PERIOD, elapsedTime / GLOW_LIFE_TIME);

            if (glowProgress != GLOW_SINE_PERIOD) {
                elapsedTime += delta;
                alpha = Interpolation.sine.apply(glowProgress);
            } else {
                isGlowing = false;
            }
        }
    }

    private void move(float delta) {
        switch (direction) {
            case TOP_LEFT:
                velocity.add(-speedX, speedY);
                break;
            case TOP_RIGHT:
                velocity.add(speedX, speedY);
                break;
            case BOTTOM_LEFT:
                velocity.add(-speedX, -speedY);
                break;
            case BOTTOM_RIGHT:
                velocity.add(speedX, -speedY);
                break;
        }

        if (position.x < -RING_MARGIN) {
            showGlowEffect();
            if (direction == Direction.TOP_LEFT) {
                direction = Direction.TOP_RIGHT;
            }
            if (direction == Direction.BOTTOM_LEFT) {
                direction = Direction.BOTTOM_RIGHT;
            }
        }

        if (position.x >= viewport.getWorldWidth() - ringTexture.getWidth() + RING_MARGIN) {
            showGlowEffect();
            if (direction == Direction.BOTTOM_RIGHT) {
                direction = Direction.BOTTOM_LEFT;
            }
            if (direction == Direction.TOP_RIGHT) {
                direction = Direction.TOP_LEFT;
            }
        }

        if (position.y >= viewport.getWorldHeight() - ringTexture.getHeight() + RING_MARGIN) {
            showGlowEffect();
            if (direction == Direction.TOP_RIGHT) {
                direction = Direction.BOTTOM_RIGHT;
            }
            if (direction == Direction.TOP_LEFT) {
                direction = Direction.BOTTOM_LEFT;
            }
        }

        if (position.y <= -RING_MARGIN) {
            showGlowEffect();
            if (direction == Direction.BOTTOM_LEFT) {
                direction = Direction.TOP_LEFT;
            }
            if (direction == Direction.BOTTOM_RIGHT) {
                direction = Direction.TOP_RIGHT;
            }
        }

        velocity.scl(delta);
        position.add(velocity.x, velocity.y);
    }
}
