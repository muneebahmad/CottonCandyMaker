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
 * DEALINGS IN THE SOFTWARE. *
 */
package algorithmi.cotton.candy.maker.scenes;

import algorithmi.cotton.candy.maker.data.ResourcesManager;
import algorithmi.cotton.candy.maker.data.Settings;
import algorithmi.cotton.candy.maker.data.SharedData;
import algorithmi.cotton.candy.maker.ui.SettingsLayer;
import aurelienribon.tweenengine.equations.Sine;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Timer;
import pk.muneebahmad.witype.actor.AnimatedActor;
import pk.muneebahmad.witype.actor.AnimationDrawable;
import pk.muneebahmad.witype.nodes.Director;
import pk.muneebahmad.witype.nodes.Scene;

/**
 *
 * @author muneebahmad
 */
public class MachineScene extends Scene {

    private Image bg;
    private Stick stick;
    private Image candy;
    private Image machineFilter;
    private Image bowl;
    private Image bowlStand;
    private Button leftButt;
    private Button rightButt;
    private Animation anim;
    private AnimationDrawable animDraw;
    private AnimatedActor machineCandy;
    
    private Image arrow;

    static final int SCALE_TO_LEVEL_1 = 0;
    static final int SCALE_TO_LEVEL_2 = 1;
    static final int SCALE_TO_LEVEL_3 = 2;
    static final int SCALE_TO_LEVEL_4 = 3;
    static final int SCALE_TO_LEVEL_5 = 4;
    static final int SCALE_TO_LEVEL_6 = 5;
    static final int SCALE_TO_LEVEL_7 = 6;
    static final int SCALE_TO_LEVEL_8 = 7;
    static final int SCALE_TO_LEVEL_9 = 8;
    static final int SCALE_TO_LEVEL_FINAL = 9;
    boolean visible = false;
    int scaling = SCALE_TO_LEVEL_1;
    float rightX = 256.0f;
    
    String tex = "candy1";

    public MachineScene() {
        SharedData.getInstance().setCurrentScene(SharedData.MACHINE_SCENE);
        setContents();
    }

    private void setContents() {
        setBg();
        addBowl();
        
        addStick();
        addArrow();
        makeMachine();
        makeMachineSound();
        addCandy();
       //addMachineFilter();
        addHudButtons();
    }

    private void setBg() {
        this.bg = new Image(ResourcesManager.machineBg);
        this.bg.setWidth(getWidth());
        this.bg.setHeight(getHeight());
        addChild(this.bg);
    }
    
    private void addBowl() {
        this.bowlStand = new Image(ResourcesManager.bowlStand);
        this.bowlStand.setPosition(getWidth() / 2.0f - bowlStand.getWidth() / 2.0f, 100.0f);
        addChild(this.bowlStand);
        
        this.bowl = new Image(ResourcesManager.pouringBowl);
        float x = getWidth() / 2.0f - this.bowl.getWidth() / 2.0f;
        float y = 150.0f;
        this.bowl.setPosition(x, y);

        addChild(this.bowl);
    }

    private void makeMachine() {
        anim = new Animation(0.3f, ResourcesManager.machineAtlas.getRegions());
        animDraw = new AnimationDrawable(anim, true);
        machineCandy = new AnimatedActor(animDraw);
        addChild(machineCandy);
        machineCandy.setPosition(getWidth() / 2.0f - machineCandy.getWidth() / 2.0f, this.bowl.getTop() - 60.0f);
        if (SharedData.getInstance().usedSugarName.equals("bottle1")) {
            machineCandy.setColor(0.5f, 0.5f, 1.0f, 1.0f);
        } else if (SharedData.getInstance().usedSugarName.equals("bottle2")) {
            machineCandy.setColor(0.0f, 1.0f, 0.0f, 1.0f);
        } else if (SharedData.getInstance().usedSugarName.equals("bottle3") || 
                SharedData.getInstance().usedSugarName.equals("bottle6")) {
            machineCandy.setColor(1.0f, 0.0f, 0.0f, 1.0f);
        } else if (SharedData.getInstance().usedSugarName.equals("bottle4")) {
            machineCandy.setColor(0.0f, 1.0f, 0.8f, 1.0f);
        } else if (SharedData.getInstance().usedSugarName.equals("bottle5")) {
            machineCandy.setColor(0.3f, 0.5f, 0.9f, 1.0f);
        } else if (SharedData.getInstance().usedSugarName.equals("bottle7")) {
            machineCandy.setColor(0.0f, 0.0f, 0.7f, 1.0f);
        } else if (SharedData.getInstance().usedSugarName.equals("bottle8")) {
            machineCandy.setColor(1.0f, 1.0f, 0.0f, 1.0f);
        }
    }
    
    private void makeMachineSound() {
        if (Settings.getInstance().getSoundMode() == Settings.MODE_SOUND_ON) {
            ResourcesManager.machineSound.setLooping(true);
            ResourcesManager.machineSound.play();
        }
    }

    private void addStick() {
        this.stick = new Stick();
        this.stick.setPosition(300.0f, 500.0f);

        addChild(this.stick);
        this.stick.setColor(this.stick.getColor().r, this.stick.getColor().g, this.stick.getColor().b, 1.0f);
        this.stick.setScale(1.5f);
        this.stick.setOriginX(this.stick.getWidth() / 2.0f);
        this.stick.setOriginY(this.stick.getHeight() / 2.0f);
        this.stick.addAction(Actions.scaleTo(0.0f, 0.0f));
        this.stick.addAction(Actions.parallel(Actions.rotateTo(360.0f, 1.5f, Interpolation.swing),
                Actions.scaleTo(1.5f, 1.5f, 1.5f, Interpolation.swing)));

        this.stick.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                stick.setX(event.getStageX() - stick.getWidth() / 2.0f);
                stick.setY(event.getStageY() - stick.getHeight());

                candy.setOrigin(candy.getWidth() / 2.0f, candy.getHeight() / 2.0f);
                stick.setOrigin(stick.getWidth() / 2.0f, stick.getHeight() / 2.0f);
                candy.setX(stick.getX() - candy.getWidth() / 2.0f - 10.0f);
                candy.setY(stick.getY() - stick.getHeight() / 2.0f);
                candyMake();
                
                    arrow.setVisible(false);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (scaling == SCALE_TO_LEVEL_FINAL) {
                    runRightButtFinalActions();
                    arrow.clearActions();
                    arrow.addAction(Actions.sequence(Actions.alpha(0.0f), Actions.removeActor()));
                } else {
                    arrow.setVisible(true);
                    arrow.setPosition(stick.getX() -80.0f, stick.getTop() - 50.0f);
                }
            }
            
        });

    }
    
    private void addArrow() {
        this.arrow = new Image(ResourcesManager.arrow);
        float x = stick.getX() - 80.0f;
        float y = stick.getTop() - 50.0f;
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
        this.machineFilter.setPosition(getWidth() / 2.0f - machineFilter.getWidth() / 2.0f, 80.0f);
        addChild(this.machineFilter);
        
        if (tex.equals("candy1")) {
            this.machineFilter.setColor(0.5f, 0.5f, 1.0f, 1.0f);
        } else if (tex.equals("candy2")) {
            this.machineFilter.setColor(0.0f, 1.0f, 0.0f, 1.0f);
        } else if (tex.equals("candy3") || tex.equals("candy6")) {
            this.machineFilter.setColor(1.0f, 0.0f, 0.0f, 1.0f);
        } else if (tex.equals("candy4")) {
            this.machineFilter.setColor(0.0f, 1.0f, 0.2f, 1.0f);
        } else if (tex.equals("candy5")) {
            this.machineFilter.setColor(0.3f, 0.5f, 0.9f, 1.0f);
        } else if (tex.equals("candy7")) {
            this.machineFilter.setColor(0.0f, 0.0f, 0.7f, 1.0f);
        } else if (tex.equals("candy8")) {
            this.machineFilter.setColor(1.0f, 1.0f, 0.0f, 1.0f);
        }
    }

    private void candyMake() {
        Timer timer = new Timer();

        if (scaling == SCALE_TO_LEVEL_1) {
            timer.scheduleTask(new Timer.Task() {
                @Override
                public void run() {
                    candy.setColor(candy.getColor().r, candy.getColor().g, candy.getColor().b, 0.1f);
                    candy.setScale(1.5f, 1.1f);
                    scaling = SCALE_TO_LEVEL_2;
                }
            }, 2.0f);
        }
        if (scaling == SCALE_TO_LEVEL_2) {
            timer.scheduleTask(new Timer.Task() {
                @Override
                public void run() {
                    candy.setColor(candy.getColor().r, candy.getColor().g, candy.getColor().b, 0.2f);
                    candy.setScale(1.5f, 1.2f);
                    scaling = SCALE_TO_LEVEL_3;
                }
            }, 2.0f);
        }
        if (scaling == SCALE_TO_LEVEL_3) {
            timer.scheduleTask(new Timer.Task() {
                @Override
                public void run() {
                    candy.setColor(candy.getColor().r, candy.getColor().g, candy.getColor().b, 0.3f);
                    candy.setScale(1.5f, 1.3f);
                    scaling = SCALE_TO_LEVEL_4;
                }
            }, 2.0f);
        }
        if (scaling == SCALE_TO_LEVEL_4) {
            timer.scheduleTask(new Timer.Task() {
                @Override
                public void run() {
                    candy.setColor(candy.getColor().r, candy.getColor().g, candy.getColor().b, 0.4f);
                    candy.setScale(1.5f, 1.4f);
                    scaling = SCALE_TO_LEVEL_5;
                }
            }, 2.0f);
        }
        if (scaling == SCALE_TO_LEVEL_5) {
            timer.scheduleTask(new Timer.Task() {
                @Override
                public void run() {
                    candy.setColor(candy.getColor().r, candy.getColor().g, candy.getColor().b, 0.5f);
                    candy.setScale(1.5f, 1.5f);
                    scaling = SCALE_TO_LEVEL_6;
                }
            }, 2.0f);
        }
        if (scaling == SCALE_TO_LEVEL_6) {
            timer.scheduleTask(new Timer.Task() {
                @Override
                public void run() {
                    candy.setColor(candy.getColor().r, candy.getColor().g, candy.getColor().b, 0.6f);
                    candy.setScale(1.5f, 1.6f);
                    scaling = SCALE_TO_LEVEL_7;
                }
            }, 2.0f);
        }
        if (scaling == SCALE_TO_LEVEL_7) {
            timer.scheduleTask(new Timer.Task() {
                @Override
                public void run() {
                    candy.setColor(candy.getColor().r, candy.getColor().g, candy.getColor().b, 0.7f);
                    candy.setScale(1.5f, 1.7f);
                    scaling = SCALE_TO_LEVEL_8;
                }
            }, 2.0f);
        }
        if (scaling == SCALE_TO_LEVEL_8) {
            timer.scheduleTask(new Timer.Task() {
                @Override
                public void run() {
                    candy.setColor(candy.getColor().r, candy.getColor().g, candy.getColor().b, 0.8f);
                    candy.setScale(1.5f, 1.8f);
                    scaling = SCALE_TO_LEVEL_9;
                }
            }, 2.0f);
        }
        if (scaling == SCALE_TO_LEVEL_9) {
            timer.scheduleTask(new Timer.Task() {
                @Override
                public void run() {
                    candy.setColor(candy.getColor().r, candy.getColor().g, candy.getColor().b, 0.9f);
                    candy.setScale(1.5f, 1.9f);
                    scaling = SCALE_TO_LEVEL_FINAL;
                }
            }, 2.0f);
        }
        if (scaling == SCALE_TO_LEVEL_FINAL) {
            timer.scheduleTask(new Timer.Task() {
                @Override
                public void run() {
                    candy.setColor(candy.getColor().r, candy.getColor().g, candy.getColor().b, 1.0f);
                    candy.setScale(1.5f, 2.0f);
                }
            }, 2.0f);
        }
    }

    private void addCandy() {
        
        if (SharedData.getInstance().usedSugarName.equals("bottle1")) {
            tex = "candy1";
        } else if (SharedData.getInstance().usedSugarName.equals("bottle2")) {
            tex = "candy2";
        } else if (SharedData.getInstance().usedSugarName.equals("bottle3")) {
            tex = "candy3";
        } else if (SharedData.getInstance().usedSugarName.equals("bottle4")) {
            tex = "candy4";
        } else if (SharedData.getInstance().usedSugarName.equals("bottle5")) {
            tex = "candy5";
        } else if (SharedData.getInstance().usedSugarName.equals("bottle6")) {
            tex = "candy6";
        } else if (SharedData.getInstance().usedSugarName.equals("bottle7")) {
            tex = "candy7";
        } else if (SharedData.getInstance().usedSugarName.equals("bottle8")) {
            tex = "candy8";
        }

        Skin skin = new Skin(ResourcesManager.textureAtlas);
        this.candy = new Image(skin.getDrawable(tex));
        this.candy.setOrigin(candy.getWidth() / 2.0f, candy.getHeight() / 2.0f);
        this.candy.setPosition((this.stick.getX()),
                this.stick.getY() - this.stick.getHeight() / 2.0f);
        addChild(this.candy);

        this.candy.addAction(Actions.rotateTo(330.0f));
        this.candy.addAction(Actions.alpha(0.0f));
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
                ResourcesManager.machineSound.setLooping(false);
                ResourcesManager.machineSound.stop();
                Director.getInstance().pushScene(Director.TRANSITION_LEFT_PUSH_IN, 500,
                        MachineScene.this, new StickSelectionScene(), Sine.INOUT);
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
            public void clicked(InputEvent event, float x, float y) {
                ResourcesManager.btnClicked.play();
                ResourcesManager.machineSound.setLooping(false);
                ResourcesManager.machineSound.stop();
                animDraw.stop(true);
                Director.getInstance().pushScene(Director.TRANSITION_RIGHT_PUSH_IN, 500, 
                        MachineScene.this, new DecorationScene(), Sine.INOUT);
            }

        });
    }
    
    private void runRightButtFinalActions() {
        rightButt.setDisabled(false);
            rightButt.clearActions();
            rightButt.addAction(Actions.sequence(Actions.delay(2.0f),
                Actions.parallel(Actions.fadeIn(1.2f, Interpolation.linear),
                Actions.scaleTo(1.0f, 1.0f, 1.5f, Interpolation.swing)),
                Actions.forever(Actions.sequence(Actions.moveTo(rightX - 20.0f, 10.0f, 0.5f, Interpolation.sine),
                Actions.moveTo(rightX, 10.0f, 0.5f, Interpolation.sine)))));
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.BACK || keycode == Input.Keys.BACKSPACE) {
            Director.getInstance().pushScene(Director.TRANSITION_LEFT_PUSH_IN, 500,
                    this, new StickSelectionScene(), Sine.INOUT);
        } else if (keycode == Input.Keys.MENU) {
            SettingsLayer.make(MachineScene.this, 0.0f, 0.0f);
        }
        return true;
    }

    /**
     * Class Stick.
     */
    public class Stick extends Actor {

        TextureRegion tex;

        public Stick() {
            tex = new TextureRegion(ResourcesManager.textureAtlas.
                    findRegion(SharedData.getInstance().usedStickName));
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
    public class Candy extends Actor {

        TextureRegion tex;

        public Candy(String textureRegion) {
            tex = new TextureRegion(ResourcesManager.textureAtlas.
                    findRegion(textureRegion));
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
