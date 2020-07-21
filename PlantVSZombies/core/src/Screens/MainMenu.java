package Screens;
import Utils.Constants;
import GameObjects.PlantsvsZombies;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Polygon;

public class MainMenu implements Screen {

    private PlantsvsZombies game;
    private Texture background = new Texture(Constants.MenuBackground);
    private Polygon SurvivalButton;
    public MainMenu(PlantsvsZombies game)
    {
        this.game=game;
        SetSurvivalButton();
    }
    private void SetSurvivalButton () {
        float [] vertices;
        vertices= new float[]{655, 215,661,130,733,126,762,97,915,101,950,140,1042,153,1031,260};
        SurvivalButton= new Polygon(vertices);
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(background,0,0,1280,720);
        game.batch.end();
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && SurvivalButton.contains(Gdx.input.getX(),Gdx.input.getY())){
            game.SurvivalMode();
        }
    }

    @Override
    public void resize(int width, int height) {

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

    @Override
    public void dispose() {

    }
}
