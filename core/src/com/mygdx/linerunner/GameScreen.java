package com.mygdx.linerunner;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameScreen implements Screen {

    private Viewport viewport;
    private Stage stage;
    private World world;
    private Box2DDebugRenderer debugRenderer;
    private Vehicle vehicle;
    private Road road;

    @Override
    public void show() {
        // set up Viewport, Stage, and InputProcessor
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);

        // set up World and Box2DDebugRenderer
        world = new World(new Vector2(0f, -10f), true);
        debugRenderer = new Box2DDebugRenderer();

        // set up Vehicle
        float vehicleWidth = 256f, vehicleHeight = 128f;
        vehicle = new Vehicle(world, VehicleTypes.BUS,
                new Vector2(
                        Gdx.graphics.getWidth() / 4f - vehicleWidth / 2f,
                        Gdx.graphics.getHeight() * 3f / 4f - vehicleHeight / 2f
                ),
                new Vector2(vehicleWidth, vehicleHeight)
        );
        vehicle.setTouchable(Touchable.enabled);
        stage.addActor(vehicle);

        // set up Road
        road = new Road(
                new Vector2(0f, Gdx.graphics.getHeight() / 2f), 
                8, 
                50f
        );
        for (int i = 0; i < road.getSegmentCount(); i++) {
            //stage.addActor(road.getSegment(i));
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.1f, 0.2f, 0.1f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        vehicle.sync();

        stage.act(delta);
        stage.draw();

        world.step(delta, 2, 3);
        debugRenderer.render(world, viewport.getCamera().combined);
    }

    @Override
    public void dispose() {
        stage.dispose();
        world.dispose();
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
