package com.mygdx.linerunner;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Vehicle extends Actor {

    private TextureRegion region;
    private Body body;

    public Vehicle(World world, VehicleTypes vehicleType, Vector2 pos, Vector2 size) {
        // define Texture, Actor, and Listener
        region = new TextureRegion(new Texture(getVehicleTexturePath(vehicleType)));
        setBounds(
                region.getRegionX(), region.getRegionY(),
                region.getRegionWidth(), region.getRegionHeight()
        );
        setSize(size.x, size.y);
        setPosition(pos.x, pos.y);
        addListener(new VehicleInputListener());

        // define box2D body and add to world
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(getX() + getWidth() / 2f, getY() + getHeight() / 2f);

        body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(getWidth() / 2f, getHeight() / 2f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;

        body.createFixture(shape, 1f);

        shape.dispose();
    }

    private String getVehicleTexturePath(VehicleTypes vehicleType) {
        switch (vehicleType) {
            case RED_CAR:
                return "car_red/car_red_0002.png";
            case GREEN_CAR:
                return "car_green/car_green_0002.png";
            case BLUE_CAR:
                return "car_blue/car_blue_0002.png";
            case POLICE_CAR:
                return "policeCar/police_0002.png";
            case PICKUP_TRUCK_ORANGE:
                return "pickupTruck_orange/pickuptruck_orange_0002.png";
            case PICKUP_TRUCK_PURPLE:
                return "pickupTruck_purple/pickuptruck_purple_0002.png";
            case BUS:
                return "bus/bus_0002.png";
            default:
                throw new IllegalStateException("Unexpected value: " + vehicleType);
        }
    }

    @Override
    public void draw (Batch batch, float parentAlpha) {
        Color color = getColor();
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
        batch.draw(
                region,
                getX(), getY(),
                getOriginX(), getOriginY(),
                getWidth(), getHeight(),
                getScaleX(), getScaleY(),
                getRotation()
        );
    }

    public void sync() {
        setPosition(
                body.getPosition().x - getWidth() / 2f,
                body.getPosition().y - getHeight() / 2f
        );
    }

}
