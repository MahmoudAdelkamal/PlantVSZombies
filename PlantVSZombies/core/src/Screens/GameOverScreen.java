package Screens;
import GameObjects.PlantsvsZombies;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
public class GameOverScreen implements Screen 
{
    private PlantsvsZombies game;
    public Texture GameOverScreen;
    public GameOverScreen(PlantsvsZombies game)
    {
        this.game=game;
        GameOverScreen = new Texture("ZombiesAteYourBrains.png");
    }
    @Override
    public void show()
    {
        
    }
    @Override
    public void render(float delta) 
    {   
        Gdx.gl.glClearColor(0,0,0,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(GameOverScreen,220,150,850,500);
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
