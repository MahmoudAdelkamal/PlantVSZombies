package com.mygdx.game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
public class MainScreen implements Screen 
{
    private Texture sunflower;
    private Texture peashooter;
    private MyGdxGame game;
    private float elapsed;
    private Character p;
    private Character plant;
   	private final int coloumnPosition[]= {56,171,290,430,545 },rowPosition[]= {400,500,596,693,781,880,972,1067,1161};

    public MainScreen(MyGdxGame game)
    {
        this.game=game;
        p=new Character("peaSpriteSheet.png",4,6,320,250,0.1f);
        plant=new Character("sunflowersheet.png",9,6,320,350,0.05f);
        sunflower=new Texture("sunflower.png");
        peashooter=new Texture("PeashooterCard.png");
        elapsed=0;
    }
    @Override
    public void show(){
        
    }
    @Override
    public void render(float delta){
       elapsed+=delta;
       Gdx.gl.glClearColor(1, 0, 0, 1);
       Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
       game.batch.begin();
       game.batch.draw(game.img,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
       game.batch.draw(sunflower,30,600);
       game.batch.draw(peashooter,30,500,sunflower.getWidth(),sunflower.getHeight());
       game.batch.draw((TextureRegion)p.DrawPlant().getKeyFrame(elapsed,true),p.Getx(),p.Gety());
       game.batch.draw((TextureRegion)plant.DrawPlant().getKeyFrame(elapsed,true),plant.Getx(),plant.Gety());
       game.batch.end();
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
