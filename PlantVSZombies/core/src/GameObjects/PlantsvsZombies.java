package GameObjects;
import Screens.GameOverScreen;
import Screens.GameLevel;
import Screens.MainMenu;
import Screens.WinScreen;
import com.badlogic.gdx.*;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
public class PlantsvsZombies extends Game
{
	public SpriteBatch batch;
	public Texture img;
	@Override
	public void create() 
	{
	    batch = new SpriteBatch();
	    img = new Texture("Frontyard.png");
            setScreen(new MainMenu(this));
	}
    public void SurvivalMode()
	{
		setScreen(new GameLevel(this) );
	}
    public void Gameover()
    {
        setScreen(new GameOverScreen(this));
    }
    public void Win()
	{
		setScreen(new WinScreen(this));
	}
    public void Menu()
		{
			setScreen(new MainMenu(this));
		}
	@Override
	public void render () {
	    super.render();
	}
	@Override
	public void dispose () {
           batch.dispose();
	   img.dispose();
	}
}
