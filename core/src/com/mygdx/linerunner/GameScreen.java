package com.mygdx.linerunner;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class GameScreen implements Screen {

    private Stage stage;
    private Vehicle greenCar;

    @Override
    public void show() {
        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        Gdx.input.setInputProcessor(stage);

        greenCar = new Vehicle(10, VehicleTypes.BUS);
        greenCar.setSize(
                greenCar.getWidth() / 4f,
                greenCar.getHeight() / 4f
        );
        greenCar.setPosition(
                Gdx.graphics.getWidth() / 2f - greenCar.getWidth() / 2f,
                Gdx.graphics.getHeight() / 2f - greenCar.getHeight() / 2f
        );
        greenCar.setTouchable(Touchable.enabled);
        stage.addActor(greenCar);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.1f, 0.2f, 0.1f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
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

}
