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

    public Vehicle(float engineTorque, VehicleTypes vehicleType) {
        torque = engineTorque;
        region = new TextureRegion(new Texture(getVehicleTexturePath(vehicleType)));
        setBounds(region.getRegionX(), region.getRegionY(),
                region.getRegionWidth(), region.getRegionHeight());
        addListener(new VehicleInputListener());
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
        batch.draw(region, getX(), getY(), getOriginX(), getOriginY(),
                getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
    }

}
