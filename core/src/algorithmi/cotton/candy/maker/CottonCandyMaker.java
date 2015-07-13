package algorithmi.cotton.candy.maker;

import algorithmi.cotton.candy.maker.data.ResourcesManager;
import algorithmi.cotton.candy.maker.data.Settings;
import algorithmi.cotton.candy.maker.scenes.SplashScene;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import pk.muneebahmad.witype.nodes.Director;

public class CottonCandyMaker extends Game {

    public static final String NAME = "Cotton Candy Maker ";
    public static final String VERSION = "[v-0x01]";

    private final Director director = Director.getInstance();

    @Override
    public void create() {
        director.setBaseWidth(480);
        director.setBaseHeight(800);
        director.setGlClearColor(1.0f, 0.0f, 0.0f, 1.0f);
        
        ResourcesManager.load();
        Director.getInstance().setCurrentScene(new SplashScene());
        if (Settings.getInstance().getSoundMode() == Settings.MODE_SOUND_ON) {
            ResourcesManager.bgSound.setLooping(true);
            ResourcesManager.bgSound.play();
        }
    }

    @Override
    public void render() {
        Gdx.graphics.setTitle(NAME + " " + VERSION + " FPS-- " + Gdx.graphics.getFramesPerSecond());
        Gdx.gl20.glClear(GL20.GL_STENCIL_BUFFER_BIT);
        director.upadate();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void resume() {
        super.resume();
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
/**
 * end class.
 */
