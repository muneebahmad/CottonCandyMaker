/**
 * copyright ©2013-2014 ®Algorithmi™.
 *
 * @author ¶muneebahmad¶ (ahmadgallian@yahoo.com)
 *
 * The following source - code IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY
 * KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO
 * EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES
 * OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
  * *
 */
package algorithmi.cotton.candy.maker.scenes;

import algorithmi.cotton.candy.maker.data.ResourcesManager;
import algorithmi.cotton.candy.maker.data.SharedData;
import algorithmi.cotton.candy.maker.ui.ParticleEffectActor;
import algorithmi.cotton.candy.maker.ui.SettingsLayer;
import algorithmi.cotton.candy.maker.ui.Toast;
import aurelienribon.tweenengine.equations.Sine;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.vungle.sdk.VunglePub;
import pk.muneebahmad.witype.nodes.Director;
import pk.muneebahmad.witype.nodes.Scene;

/**
 *
 * @author muneebahmad
 */
public class PouringScene extends Scene {

    private Image bg;
    private Image bowl;
    private Image bowlStand;
    private Bottle bottle;
    private Image cloud;
    private Image arrow;
    private Image machineFilter;
    private Button leftButt;
    private Button rightButt;
    private ParticleEffectActor particleEffect;
    private ParticleEffect effect;
    float rightX = 256.0f;

    private String tex;
    private String tex2 = "java";
    

    public PouringScene() {
        SharedData.getInstance().setCurrentScene(SharedData.POURING_SCENE);
        setContents();
    }

    private void setContents() {
        addBg();
        addBowl();
        addParticles();
        addCloud();
        addBottle();
        addArrow();
        //addMachineFilter();
        addHudButtons();
    }

    private void addBg() {
        this.bg = new Image(ResourcesManager.pouringBg);
        this.bg.setWidth(getWidth());
        this.bg.setHeight(getHeight());
        addChild(bg);
    }

    private void addBowl() {
        this.bowlStand = new Image(ResourcesManager.bowlStand);
        this.bowlStand.setPosition(getWidth() / 2.0f - bowlStand.getWidth() / 2.0f, -800.0f);
        addChild(this.bowlStand);
        
        this.bowlStand.addAction(Actions.moveTo(getWidth() / 2.0f - bowlStand.getWidth() / 2.0f, 
                70.0f, 1.5f, Interpolation.swing));
        
        this.bowl = new Image(ResourcesManager.pouringBowl);
        float x = getWidth() / 2.0f - this.bowl.getWidth() / 2.0f;
        float y = 120.0f;
        this.bowl.setPosition(x, -500.0f);

        addChild(this.bowl);

        this.bowl.addAction(Actions.sequence(Actions.moveTo(x, y, 1.5f, Interpolation.swing),
                Actions.forever(Actions.sequence(Actions.moveTo(x + 1.5f, y, 0.15f, Interpolation.bounce),
                                Actions.moveTo(x - 1.5f, y, 0.15f, Interpolation.bounce)))));
        
    }

    private void addBottle() {
        bottle = new Bottle();
        float x = getWidth() - 100.0f;
        float y = getHeight() - 250.0f;

        this.bottle.setPosition(x, getHeight() + 300.0f);

        addChild(this.bottle);
        this.bottle.setScale(2.0f);

        this.bottle.addAction(Actions.sequence(Actions.delay(0.5f), Actions.
                moveTo(x, y, 1.5f, Interpolation.swing)));

        Toast.make(this, "Tap Sugar Bottle!", Toast.POSITION_CENTER, 3.0f);

        this.bottle.addListener(new ClickListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                arrow.clearActions();
                arrow.addAction(Actions.sequence(Actions.alpha(0.0f), Actions.removeActor()));

                bottle.addAction(Actions.sequence(Actions.rotateTo(120.0f, 0.3f, Interpolation.swing),
                        Actions.delay(11.0f), Actions.rotateTo(0.0f, 1.2f, Interpolation.swing),
                        Actions.moveTo(getWidth() - 100.0f, getHeight() + 400.0f, 1.5f,
                                Interpolation.swing), Actions.removeActor()));

                particleEffect.setColor(1.0f, 0.0f, 0.0f, 1.0f);
                particleEffect.setStart(ParticleEffectActor.START);
                particleEffect.addAction(Actions.fadeOut(0.1f));
                particleEffect.addAction(Actions.sequence(Actions.delay(2.0f), Actions.alpha(1.0f),
                        Actions.delay(9.5f), Actions.alpha(0.0f), Actions.removeActor()));
                
                //machineFilter.addAction(Actions.fadeIn(8.0f));

                rightButt.setDisabled(false);
                rightButt.addAction(Actions.sequence(Actions.delay(13.0f),
                        Actions.parallel(Actions.fadeIn(1.2f, Interpolation.linear),
                                Actions.scaleTo(1.0f, 1.0f, 1.5f, Interpolation.swing)),
                        Actions.forever(Actions.sequence(Actions.moveTo(rightX - 20.0f, 10.0f, 0.5f, Interpolation.sine),
                                        Actions.moveTo(rightX, 10.0f, 0.5f, Interpolation.sine)))));

                return true;
            }

        });
    }
    
    private void addCloud() {
        this.cloud = new Image(ResourcesManager.cloud);
        this.cloud.setPosition(getWidth() + 310.0f, getHeight() - cloud.getHeight() - 30.0f);
        addChild(cloud);
        this.cloud.addAction(Actions.forever(Actions.sequence(Actions.moveTo(-310.0f, 
                getHeight() - cloud.getHeight() - 30.0f, 25.0f), 
                Actions.moveTo(getWidth() + 310.0f, getHeight() - cloud.getHeight() - 30.0f))));
    }

    private void addParticles() {
        this.effect = new ParticleEffect();

        if (SharedData.getInstance().getUsedSugarName().equals("bottle1")) {
            effect.load(Gdx.files.internal("data/pour-particle.p"), Gdx.files.internal("particle/bottle1"));
            this.tex = "candy1";
        } else if (SharedData.getInstance().getUsedSugarName().equals("bottle2")) {
            effect.load(Gdx.files.internal("data/pour-particle.p"), Gdx.files.internal("particle/bottle2"));
            this.tex = "candy2";
        } else if (SharedData.getInstance().getUsedSugarName().equals("bottle3")
                || SharedData.getInstance().getUsedSugarName().equals("bottle6")) {
            effect.load(Gdx.files.internal("data/pour-particle.p"), Gdx.files.internal("particle/bottle3"));
            this.tex = "candy3";
            this.tex2 = "candy6";
        } else if (SharedData.getInstance().getUsedSugarName().equals("bottle4")
                || SharedData.getInstance().getUsedSugarName().equals("bottle5")) {
            effect.load(Gdx.files.internal("data/pour-particle.p"), Gdx.files.internal("particle/bottle5"));
            this.tex = "candy4";
            this.tex2 = "candy5";
        } else if (SharedData.getInstance().getUsedSugarName().equals("bottle7")) {
            effect.load(Gdx.files.internal("data/pour-particle.p"), Gdx.files.internal("particle/bottle6"));
            this.tex = "candy7";
        } else if (SharedData.getInstance().getUsedSugarName().equals("bottle8")) {
            effect.load(Gdx.files.internal("data/pour-particle.p"), Gdx.files.internal("particle/bottle4"));
            this.tex = "candy8";
        }

        this.particleEffect = new ParticleEffectActor(effect, getWidth() / 2.0f + 10.5f,
                getHeight() - 280.0f);
        addChild(this.particleEffect);
    }

    private void addArrow() {
        this.arrow = new Image(ResourcesManager.arrow);
        float x = getWidth() - 170.0f;
        float y = (getHeight() - 190.0f) - arrow.getHeight() / 2.0f;
        this.arrow.setPosition(x, y);
        addChild(arrow);

        arrow.addAction(Actions.alpha(0.0f));
        arrow.addAction(Actions.scaleTo(0.0f, 0.0f));
        arrow.setOriginX(arrow.getWidth() / 2.0f);
        arrow.setOriginY(arrow.getHeight() / 2.0f);
        arrow.addAction(Actions.forever(Actions.sequence(Actions.parallel(Actions.delay(0.5f), Actions.fadeIn(1.0f),
                Actions.scaleTo(1.0f, 1.0f, 1.0f, Interpolation.swing)), Actions.delay(1.0f),
                Actions.parallel(Actions.fadeOut(1.0f),
                        Actions.scaleTo(0.0f, 0.0f, 1.0f, Interpolation.swing)))));
    }

    private void addMachineFilter() {
        this.machineFilter = new Image(ResourcesManager.machineFilter);
        this.machineFilter.setPosition(getWidth() / 2.0f - machineFilter.getWidth() / 2.0f, -500.0f);
        addChild(this.machineFilter);

        if (tex.equals("candy1")) {
            this.machineFilter.setColor(0.5f, 0.5f, 1.0f, 1.0f);
        } else if (tex.equals("candy2")) {
            this.machineFilter.setColor(0.0f, 1.0f, 0.0f, 1.0f);
        } else if (tex.equals("candy3")) {
            this.machineFilter.setColor(1.0f, 0.0f, 0.0f, 1.0f);
        } else if (tex.equals("candy4")) {
            this.machineFilter.setColor(0.0f, 1.0f, 0.2f, 1.0f);
        } else if (tex.equals("candy7")) {
            this.machineFilter.setColor(0.0f, 0.0f, 0.7f, 1.0f);
        } else if (tex.equals("candy8")) {
            this.machineFilter.setColor(1.0f, 1.0f, 0.0f, 1.0f);
        }

        if (tex2.equals("candy6")) {
            this.machineFilter.setColor(1.0f, 0.0f, 0.0f, 1.0f);
        } else if (tex2.equals("candy5")) {
            this.machineFilter.setColor(0.3f, 0.5f, 0.9f, 1.0f);
        }

        machineFilter.addAction(Actions.fadeOut(0.1f));
        machineFilter.addAction(Actions.sequence(Actions.moveTo(getWidth() / 2.0f - machineFilter.getWidth() / 2.0f - 40.0f,
                95.0f, 1.5f, Interpolation.swing),
                Actions.forever(Actions.sequence(Actions.moveTo(machineFilter.getWidth() / 2.0f - 40.0f + 1.5f, 95.0f,
                                        0.15f, Interpolation.bounce),
                                Actions.moveTo(machineFilter.getWidth() / 2.0f - 40.0f - 1.5f, 95.0f, 0.15f, Interpolation.bounce)))));
    }

    private void addHudButtons() {
        //LEFT BUTTON
        this.leftButt = new Button(ResourcesManager.skin);
        this.leftButt.setPosition(50.0f, 10.0f);
        addChild(this.leftButt);
        this.leftButt.addListener(new ClickListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                ResourcesManager.btnClicked.play();
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                ResourcesManager.btnClicked.play();
                Director.getInstance().pushScene(Director.TRANSITION_TOP_PUSH_IN, 500,
                        PouringScene.this, new SugarSelectionScene(), Sine.INOUT);
            }

        });

        //RIGHT BUTTON
        this.rightButt = new Button(ResourcesManager.skin, "right");
        this.rightButt.setPosition((getWidth() - rightButt.getWidth()) - 50.0f, 10.0f);
        addChild(this.rightButt);
        this.rightButt.setDisabled(true);
        this.rightButt.addAction(Actions.alpha(0.0f));
        this.rightButt.addAction(Actions.scaleTo(0.0f, 0.0f));
        this.rightButt.addListener(new ClickListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                ResourcesManager.btnClicked.play();
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                ResourcesManager.btnClicked.play();
                Director.getInstance().pushScene(Director.TRANSITION_RIGHT_PUSH_IN, 500,
                        PouringScene.this, new StickSelectionScene(), Sine.INOUT);
                if (Gdx.app.getType() == ApplicationType.Android) {
                    VunglePub.displayAdvert();
                }
            }
        });
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.BACK || keycode == Input.Keys.BACKSPACE) {
            Director.getInstance().pushScene(Director.TRANSITION_TOP_PUSH_IN, 500, this,
                    new SugarSelectionScene(), Sine.INOUT);
        } else if (keycode == Input.Keys.MENU) {
            SettingsLayer.make(PouringScene.this, 0.0f, 0.0f);
        }
        return true;
    }

    /**
     * Class Bottle.
     */
    public class Bottle extends Actor {

        TextureRegion tex;

        public Bottle() {
            tex = new TextureRegion(ResourcesManager.textureAtlas.
                    findRegion(SharedData.getInstance().usedSugarName));
            setBounds(getX(), getY(), tex.getRegionWidth(), tex.getRegionHeight());
        }

        @Override
        public void draw(Batch batch, float alpha) {
            batch.draw(tex, this.getX(), this.getY(), this.getOriginX(), this.getOriginY(), this.getWidth(),
                    this.getHeight(), this.getScaleX(), this.getScaleY(), this.getRotation());
        }
    }
    /**
     * end inner class.
     */

}
/**
 * end class.
 */
