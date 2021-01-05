package com.mygdx.linerunner;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Vehicle extends Actor {

    private float torque;
    private TextureRegion region;
    private Vector2 savedPosition;

    public boolean started;

    public Vehicle(float engineTorque, String internalTexturePath) {
        torque = engineTorque;
        region = new TextureRegion(new Texture(internalTexturePath));
        setBounds(region.getRegionX(), region.getRegionY(),
                region.getRegionWidth(), region.getRegionHeight());
        started = false;
        addListener(new VehicleInputListener());
    }

    @Override
    public void draw (Batch batch, float parentAlpha) {
        Color color = getColor();
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
        batch.draw(region, getX(), getY(), getOriginX(), getOriginY(),
                getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
    }

    @Override
    public void act(float delta) {
        if (started) {
            if (savedPosition == null) {
                savedPosition = new Vector2(this.getX(), this.getY());
            }
            this.moveBy(5f, 0f);
        }
        else {
            if (savedPosition != null) {
                if (this.getX() == savedPosition.x && this.getY() == savedPosition.y) {
                    savedPosition = null;
                }
                else {
                    Vector2 move = new Vector2(
                            savedPosition.x - this.getX(),
                            savedPosition.y - this.getY()
                    );
                    move.nor();
                    move.scl(5);
                    this.moveBy(move.x, move.y);
                }
            }
        }
    }
}
