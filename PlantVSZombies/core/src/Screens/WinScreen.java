package Screens;

import GameObjects.PlantsvsZombies;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

public class WinScreen implements Screen {
    private PlantsvsZombies game;
    private float elapsed;
    public Texture WinTexture;
    public WinScreen(PlantsvsZombies game)
    {
        this.game=game;
        WinTexture = new Texture("You-Win.png");
    }
    @Override
    public void show()
    {

    }
    @Override
    public void render(float delta)
    {
        elapsed+=delta;
        if (elapsed>5)
            game.Menu();
        Gdx.gl.glClearColor(0,0,0,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(WinTexture, 0, 0, 1254, 756);
        game.batch.end();
    }
    @Override
    public void resize(int width, int height)
    {

    }
    @Override
    public void pause()
    {

    }
    @Override
    public void resume()
    {

    }
    @Override
    public void hide()
    {

    }
    @Override
    public void dispose()
    {

    }
}
