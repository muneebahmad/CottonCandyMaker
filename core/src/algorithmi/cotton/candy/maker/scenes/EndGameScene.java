/**
  * copyright ©2013-2014 ®Algorithmi™.
  *
  * @author ¶muneebahmad¶ (ahmadgallian@yahoo.com) 
  *
  * The following source - code IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
  * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
  * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
  * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
  * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
  * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
  * THE SOFTWARE.
  * **/


package algorithmi.cotton.candy.maker.scenes;

import algorithmi.cotton.candy.maker.ads.AdListener;
import algorithmi.cotton.candy.maker.data.ResourcesManager;
import algorithmi.cotton.candy.maker.data.SharedData;
import algorithmi.cotton.candy.maker.ui.ScreenshotFactory;
import algorithmi.cotton.candy.maker.ui.SettingsLayer;
import algorithmi.cotton.candy.maker.ui.Toast;
import aurelienribon.tweenengine.equations.Sine;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
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
public class EndGameScene extends Scene {
    
    private Image bg;
    private Image candy;
    private Image stick;
    private Button leftButt;
    private Button rightButt;
    private Button eatButt;
    private Button cameraButt;
    
    private Image wrapper;
    private Image addon;
    
    private Animation anim;
    private AnimationDrawable animDrawable;
    private AnimatedActor sun;
    
    boolean isEating = false;
    public String tex;
    
    static final int SCALE_TO_LEVEL_1 = 0x00;
    static final int SCALE_TO_LEVEL_2 = 0x11;
    static final int SCALE_TO_LEVEL_3 = 0x21;
    static final int SCALE_TO_LEVEL_4 = 0x23;
    static final int SCALE_TO_LEVEL_5 = 0x445578;
    static final int SCALE_TO_LEVEL_6 = 0x93;
    static final int SCALE_TO_LEVEL_7 = 0x6f;
    static final int SCALE_TO_LEVEL_8 = 0x79;
    static final int SCALE_TO_LEVEL_9 = 0x008;
    static final int SCALE_TO_LEVEL_FINAL = 0x900;
    int scaling = SCALE_TO_LEVEL_1;
    
    float rightX = 256.0f;
    
    private final Skin skin = new Skin(ResourcesManager.textureAtlas);
    private final Skin addonSkin = new Skin(ResourcesManager.addonAtlas);
    
    private FrameBuffer fbo = new FrameBuffer(Pixmap.Format.RGBA8888, Gdx.graphics.getWidth(), 
            Gdx.graphics.getHeight(), false);
    private Batch batch;
    
    private static AdListener adListener;

    public EndGameScene() {
        ResourcesManager.waoSound.play();
        SharedData.getInstance().setCurrentScene(SharedData.END_GAME_SCENE);
        setContents();
    }
    
    private void setContents() {
        setBg();
        addStick();
        addCandy();
        addButtons();
        addWrapper();
        addAddon();
        addSun();
        
        addHudButtons();
    }
    
    private void setBg() {
        this.bg = new Image(ResourcesManager.endBg);
        this.bg.setWidth(getWidth());
        this.bg.setHeight(getHeight());
        addChild(this.bg);
    }
    
    private void addStick() {
        this.stick = new Image(skin.getDrawable(SharedData.getInstance().
                getUsedStickName()));
        this.stick.setPosition(getWidth() / 2.0f - stick.getWidth() / 2.0f, 120.0f);
        addChild(this.stick);
        this.stick.setScale(2.0f);
    }
    
    private void addCandy() {
        tex = "candy1";
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
        this.candy = new Image(skin.getDrawable(tex));
        this.candy.setOrigin(candy.getWidth() / 2.0f, candy.getHeight() / 2.0f);
        this.candy.setScale(4.0f);
        this.candy.setPosition(getWidth() / 2.0f - candy.getWidth() * 2, SharedData.getInstance().getStickTop() - 50.0f);
        addChild(candy);
        
        this.candy.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //eatCandy();
                return true;
            }

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                if (isEating) {
                    eatCandy();
                }
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                /**
                if (isEating) {
                    fbo.begin();
                        Batch batch = new SpriteBatch(3);
                        batch.begin();
                        batch.draw(ResourcesManager.eatingMask, event.getStageX(), event.getStageY());
                        candy.draw(batch, 1.0f);
                        batch.end();
                    fbo.end();
                }
                * **/
                if (isEating && scaling == SCALE_TO_LEVEL_FINAL) {
                    isEating = false;
                    runRightButtFinalActions();
                }
            }
            
        });
    }
    
    private void eatFboCandy() {
        
    }
    
    private void eatCandy() {
        Timer timer = new Timer();
        if (scaling == SCALE_TO_LEVEL_1) {
            timer.scheduleTask(new Timer.Task() {
                @Override
                public void run() {
                    ResourcesManager.eatSound.play();
                    candy.setScale(4.0f, 3.6f);
                    scaling = SCALE_TO_LEVEL_2;
                }
            }, 0.5f);
        }
        if (scaling == SCALE_TO_LEVEL_2) {
            timer.scheduleTask(new Timer.Task() {
                @Override
                public void run() {
                    //ResourcesManager.eatSound.play();
                    candy.setScale(4.0f, 3.2f);
                    scaling = SCALE_TO_LEVEL_3;
                }
            }, 0.5f);
        }
        if (scaling == SCALE_TO_LEVEL_3) {
            timer.scheduleTask(new Timer.Task() {
                @Override
                public void run() {
                    //ResourcesManager.eatSound.play();
                    candy.setScale(4.0f, 2.8f);
                    scaling = SCALE_TO_LEVEL_4;
                }
            }, 0.5f);
        }
        if (scaling == SCALE_TO_LEVEL_4) {
            timer.scheduleTask(new Timer.Task() {
                @Override
                public void run() {
                    ResourcesManager.eatSound.play();
                    candy.setScale(4.0f, 2.4f);
                    scaling = SCALE_TO_LEVEL_5;
                }
            }, 0.5f);
        }
        if (scaling == SCALE_TO_LEVEL_5) {
            timer.scheduleTask(new Timer.Task() {
                @Override
                public void run() {
                    //ResourcesManager.eatSound.play();
                    candy.setScale(4.0f, 2.0f);
                    scaling = SCALE_TO_LEVEL_6;
                }
            }, 0.5f);
        }
        if (scaling == SCALE_TO_LEVEL_6) {
            timer.scheduleTask(new Timer.Task() {
                @Override
                public void run() {
                    //ResourcesManager.eatSound.play();
                    candy.setScale(4.0f, 1.6f);
                    scaling = SCALE_TO_LEVEL_7;
                }
            }, 0.5f);
        }
        if (scaling == SCALE_TO_LEVEL_7) {
            timer.scheduleTask(new Timer.Task() {
                @Override
                public void run() {
                    ResourcesManager.eatSound.play();
                    candy.setScale(4.0f, 1.2f);
                    scaling = SCALE_TO_LEVEL_8;
                }
            }, 0.5f);
        }
        if (scaling == SCALE_TO_LEVEL_8) {
            timer.scheduleTask(new Timer.Task() {
                @Override
                public void run() {
                    //ResourcesManager.eatSound.play();
                    candy.setScale(4.0f, 0.8f);
                    scaling = SCALE_TO_LEVEL_9;
                }
            }, 0.5f);
        }
        if (scaling == SCALE_TO_LEVEL_9) {
            timer.scheduleTask(new Timer.Task() {
                @Override
                public void run() {
                    //ResourcesManager.eatSound.play();
                    candy.setScale(4.0f, 0.4f);
                    scaling = SCALE_TO_LEVEL_FINAL;
                }
            }, 0.5f);
        }
        if (scaling == SCALE_TO_LEVEL_FINAL) {
            timer.scheduleTask(new Timer.Task() {
                @Override
                public void run() {
                    ResourcesManager.eatSound.play();
                    candy.setScale(4.0f, 0.0f);
                }
            }, 0.5f);
        }
    }
    
    private void addButtons() {
        this.eatButt = new TextButton("EAT", ResourcesManager.skin);
        this.eatButt.setPosition(getWidth() / 3.5f - this.eatButt.getWidth() / 2.0f,
                getHeight() - this.eatButt.getHeight() * 1.2f);
        addChild(this.eatButt);
        this.eatButt.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                ResourcesManager.btnClicked.play();
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                ResourcesManager.btnClicked.play();
                eatButt.addAction(Actions.sequence(Actions.moveTo(getWidth() / 3.5f - eatButt.getWidth() / 2.0f, 
                        getHeight() + 200.0f, 1.0f, Interpolation.swingIn)));
                isEating = true;
                Toast.make(EndGameScene.this, "Drag your finger on \ncandy to eat.", Toast.POSITION_CENTER, 8.0f);
            }
            
        });
        
        this.cameraButt = new TextButton("Capture", ResourcesManager.skin);
        this.cameraButt.setPosition(getWidth() / 1.5f - this.cameraButt.getWidth() / 2.0f, 
                this.eatButt.getY());
        addChild(this.cameraButt);
        this.cameraButt.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                ResourcesManager.btnClicked.play();
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                ResourcesManager.btnClicked.play();
                ScreenshotFactory.saveScreenShot();
                if (Gdx.app.getType() == ApplicationType.Android) {
                    Toast.make(EndGameScene.this, Gdx.files.getExternalStoragePath() + "\n/AlgorithmiApps/CottonCandy.png", 
                        Toast.POSITION_CENTER, 10.0f);
                }  else {
                    Toast.make(EndGameScene.this, Gdx.files.getExternalStoragePath() + "\n/AlgorithmiApps/CottonCandy.png", 
                        Toast.POSITION_CENTER, 10.0f);
                }
                cameraButt.addAction(Actions.sequence(Actions.moveTo(getWidth() / 1.5f - cameraButt.getWidth() / 2.0f, 
                        getHeight() + 200.0f, 1.0f, Interpolation.swingIn)));
            }
            
        });
    }
    
    private void addWrapper() {
        this.wrapper = new Image(skin.getDrawable(SharedData.getInstance().getUsedWrapperName()));
        this.wrapper.setScale(2.3f);
        this.wrapper.setPosition(this.candy.getX() - 25.0f, 
                this.candy.getY() - 70.0f);
        addChild(this.wrapper);
        this.wrapper.setZIndex(7);
        this.wrapper.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (isEating) {
                wrapper.addAction(Actions.sequence(Actions.moveTo(candy.getX() - 25.0f, getHeight() + 500.0f, 1.0f),
                        Actions.removeActor()));
                }
                return true;
            }
            
        });
    }
    
    private void addAddon() {
        this.addon = new Image(addonSkin.getDrawable(SharedData.getInstance().getUsedAddonName()));
        this.addon.setPosition(SharedData.getInstance().getAddonPositionX(), SharedData.getInstance().getAddonPositionY());
        addChild(this.addon);
        this.addon.setZIndex(8);
        
        if (this.addon != null) {
            this.addon.setOrigin(addon.getWidth() / 2.0f, addon.getHeight() / 2.0f);
            this.addon.addAction(Actions.forever(Actions.sequence(Actions.scaleTo(0.9f, 0.8f, 0.3f), 
                    Actions.scaleTo(1.0f, 1.0f, 0.3f))));
            
            this.addon.addListener(new InputListener() {

                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }

                @Override
                public void touchDragged(InputEvent event, float x, float y, int pointer) {
                    addon.setX(event.getStageX() - addon.getWidth() / 2.0f);
                    addon.setY(event.getStageY() - addon.getHeight() / 2.0f);
                }

                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                }
            });
        }
    }
    
    private void addSun() {
        this.anim = new Animation(0.3f, ResourcesManager.sunAtlas.getRegions());
        this.animDrawable = new AnimationDrawable(anim, true);
        this.sun = new AnimatedActor(animDrawable);
        addChild(this.sun);
        this.sun.setPosition(-300.0f, getHeight() -sun.getHeight() - 170.0f);
        
        this.sun.addAction(Actions.forever(Actions.sequence(Actions.moveTo(getWidth() + 300.0f, 
                getHeight() - 10.0f, 50.5f), 
                Actions.moveTo(-300.0f, getHeight() -sun.getHeight() - 170.0f))));
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
                Director.getInstance().pushScene(Director.TRANSITION_LEFT_PUSH_IN, 500,
                        EndGameScene.this, new DecorationScene(), Sine.INOUT);
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
                Director.getInstance().pushScene(Director.TRANSITION_RIGHT_PUSH_IN, 500, 
                        EndGameScene.this, new MainMenuScene(), Sine.INOUT);
                if (Gdx.app.getType() == ApplicationType.Android) {
                    adListener.showHeyzapInterstital();
                }
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
    
    /**
     * 
     * @param adListen 
     */
    public static void addAddListener(AdListener adListen) {
        adListener = adListen;
    }
    
    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.BACK || keycode == Input.Keys.BACKSPACE) {
            Director.getInstance().pushScene(Director.TRANSITION_LEFT_PUSH_IN, 500,
                    this, new DecorationScene(), Sine.INOUT);
        } else if (keycode == Input.Keys.MENU) {
            SettingsLayer.make(EndGameScene.this, 0.0f, 0.0f);
        }
        return true;
    }
    
}/** end class. */
