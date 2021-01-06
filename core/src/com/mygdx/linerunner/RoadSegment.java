package com.mygdx.linerunner;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class RoadSegment extends Actor {

    private float length;
    private TextureRegion region;

    public RoadSegment(float len) {
        this.length = len;
        region = new TextureRegion(new Texture("road_segment.png"));
        setBounds(
                region.getRegionX(),
                region.getRegionY(),
                region.getRegionWidth(),
                region.getRegionHeight()
        );
        setSize(
                length,
                length * region.getRegionHeight() / region.getRegionWidth()
        );
    }

    @Override
    public void draw (Batch batch, float parentAlpha) {
        Color color = getColor();
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
        batch.draw(region, getX(), getY(), getOriginX(), getOriginY(),
                getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
    }

    public float getLength() {
        return length;
    }
}
