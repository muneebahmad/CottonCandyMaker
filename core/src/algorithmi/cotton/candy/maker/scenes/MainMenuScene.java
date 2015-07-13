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

import algorithmi.cotton.candy.maker.ads.MarketListener;
import algorithmi.cotton.candy.maker.data.ResourcesManager;
import algorithmi.cotton.candy.maker.data.Settings;
import algorithmi.cotton.candy.maker.data.SharedData;
import algorithmi.cotton.candy.maker.ui.GridLayer;
import algorithmi.cotton.candy.maker.ui.GridlayerInterface;
import algorithmi.cotton.candy.maker.ui.SettingsLayer;
import algorithmi.cotton.candy.maker.ui.Toast;
import aurelienribon.tweenengine.equations.Sine;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import pk.muneebahmad.witype.actor.AnimatedActor;
import pk.muneebahmad.witype.actor.AnimationDrawable;
import pk.muneebahmad.witype.nodes.Director;
import pk.muneebahmad.witype.nodes.Scene;
import pk.muneebahmad.witype.ui.Dialog;
import pk.muneebahmad.witype.ui.DialogClickListener;

/**
 *
 * @author muneebahmad
 */
public class MainMenuScene extends Scene implements DialogClickListener, GridlayerInterface {

    private Image bg;
    private Image bg2;
    private Image logo;
    private Button playButt;
    private Button funButt;
    private Button rateButt;
    private Button exitButt;
    private Button settingsButt;
    private Button privacyButt;
    private final Dialog dialog;
    
    private Animation anim;
    private AnimationDrawable animDrawable;
    private AnimatedActor sun;
    
    private static MarketListener marketListener;

    public MainMenuScene() {
        Gdx.input.setCatchBackKey(true);
        SharedData.getInstance().setCurrentScene(SharedData.MAIN_MENU_SCENE);
        this.dialog = new Dialog(this);
        setContents();
    }

    private void setContents() {
        setBg();
        addSun();
        addLogo();
        setButtons();
        addSettingsButt();
        addPrivacyButt();
    }

    private void setBg() {
        this.bg = new Image(ResourcesManager.menuBg);
        this.bg.setWidth(getWidth());
        this.bg.setHeight(getHeight());
        addChild(this.bg);
        
        this.bg2 = new Image(ResourcesManager.menuBg2);
        this.bg2.setWidth(getWidth());
        this.bg2.setHeight(getHeight());
        addChild(this.bg2);
        this.bg2.addAction(Actions.alpha(0.0f));
        this.bg2.addAction(Actions.forever(Actions.sequence(Actions.delay(2.0f), Actions.fadeIn(1.0f, 
                Interpolation.pow5), Actions.delay(0.5f), Actions.fadeOut(1.0f, Interpolation.pow5))));
    }
    
    private void addLogo() {
        this.logo = new Image(ResourcesManager.logo);
        this.logo.setPosition(0.0f, getHeight() - 250.0f);
        addChild(logo);
        
        logo.setOrigin(logo.getWidth() / 2.0f, logo.getHeight() / 2.0f);
        logo.addAction(Actions.forever(Actions.sequence(Actions.scaleTo(0.7f, 0.7f, 1.0f, Interpolation.swing), 
                Actions.scaleTo(0.8f, 0.8f, 1.0f, Interpolation.swing))));
    }
    
    private void addSun() {
        this.anim = new Animation(0.3f, ResourcesManager.sunAtlas.getRegions());
        this.animDrawable = new AnimationDrawable(anim, true);
        this.sun = new AnimatedActor(animDrawable);
        addChild(this.sun);
        this.sun.setPosition(getWidth() - sun.getWidth() - 20.0f, getHeight() -sun.getHeight() - 20.0f);
    }
    
    private void setButtons() {
        /**
         * Play Button
         */
        this.playButt = new TextButton("Play", ResourcesManager.skin, "blue");
        this.playButt.setPosition(-1000.0f, getHeight() - 300.0f);
        addChild(this.playButt);
        this.playButt.addAction(Actions.moveTo(10.0f, getHeight() - 300.0f, 1.5f, Interpolation.swing));
        this.playButt.addListener(new ClickListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                ResourcesManager.btnClicked.play();
                return true;
            }
            
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                ResourcesManager.btnClicked.play();
                Director.getInstance().pushScene(Director.TRANSITION_RIGHT_PUSH_IN, 500, MainMenuScene.this, 
                        new SugarSelectionScene(), Sine.OUT);
            }
        });
        
        /**
         * Rate Button
         */
        this.rateButt = new TextButton("Rate", ResourcesManager.skin, "blue");
        this.rateButt.setPosition(-1000.0f, this.playButt.getY() - 100.0f);
        addChild(this.rateButt);
        this.rateButt.addAction(Actions.moveTo(10.0f, this.playButt.getY() - 100.0f, 2.0f, Interpolation.swing));
        this.rateButt.addListener(new ClickListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                ResourcesManager.btnClicked.play();
                return true;
            }
            
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                ResourcesManager.btnClicked.play();
                if (Gdx.app.getType() == ApplicationType.Android) {
                    marketListener.rateUsClicked();
                } else if (Gdx.app.getType() == ApplicationType.Desktop) {
                    Toast.make(MainMenuScene.this, "Not available in Desktop Version!",
                            Toast.POSITION_CENTER, 5.0f);
                }
            }
        });
        
        /**
         * Fun Button
         */
        this.funButt = new TextButton("More Fun", ResourcesManager.skin, "blue");
        this.funButt.setPosition(-1000.0f, this.rateButt.getY() - 100.0f);
        addChild(this.funButt);
        this.funButt.addAction(Actions.moveTo(10.0f, this.rateButt.getY() - 100.0f, 2.5f, Interpolation.swing));
        this.funButt.addListener(new ClickListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                ResourcesManager.btnClicked.play();
                return true;
            }
            
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                ResourcesManager.btnClicked.play();
                if (Gdx.app.getType() == ApplicationType.Android) {
                    marketListener.moreFunClicked();
                } else if (Gdx.app.getType() == ApplicationType.Desktop) {
                    Toast.make(MainMenuScene.this, "Not availabe in Desktop Version!", 
                            Toast.POSITION_CENTER, 5.0f);
                }
            }
        });
        
        /**
         * Exit Button
         */
        this.exitButt = new TextButton("Exit", ResourcesManager.skin, "blue");
        this.exitButt.setPosition(-1000.0f, this.funButt.getY() - 100.0f);
        addChild(this.exitButt);
        this.exitButt.addAction(Actions.moveTo(10.0f, this.funButt.getY() - 100.0f, 3.0f, Interpolation.swing));
        this.exitButt.addListener(new ClickListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                ResourcesManager.btnClicked.play();
                return true;
            }
            
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                ResourcesManager.btnClicked.play();
                makeDialog();
            }
        });
    }
    
    private void addSettingsButt() {
        this.settingsButt = new Button(ResourcesManager.skin, "settings");
        this.settingsButt.setPosition(10.0f, -100.0f);
        addChild(this.settingsButt);
        this.settingsButt.addAction(Actions.sequence(Actions.delay(2.0f), 
                Actions.moveTo(10.0f, 5.0f, 
                        1.0f, Interpolation.swing)));
        this.settingsButt.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ResourcesManager.btnClicked.play();
                SettingsLayer.make(MainMenuScene.this, 0.0f, 0.0f);
            }
        });
    }
    
    private void addPrivacyButt() {
        this.privacyButt = new Button(ResourcesManager.skin, "privacy");
        this.privacyButt.setPosition(getWidth() - privacyButt.getWidth() - 10.0f, -100.0f);
        addChild(privacyButt);
        this.privacyButt.addAction(Actions.sequence(Actions.delay(3.0f), 
                Actions.moveTo(getWidth() - privacyButt.getWidth() - 10.0f, 5.0f, 1.0f,
                        Interpolation.swing)));
        
        this.privacyButt.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                ResourcesManager.btnClicked.play();
                SharedData.getInstance().setGridMode(SharedData.GRID_MODE_PRIVACY);
                GridLayer.make(MainMenuScene.this, "Privacy Policy", MainMenuScene.this);
            }
            
        });
    }
    
    private void addFps() {
        Label fpsLabel = new Label(Gdx.graphics.getFramesPerSecond() + " :FPS\n" + Gdx.app.getJavaHeap() + ": byte(s)", 
                ResourcesManager.skin);
        fpsLabel.setPosition(0.0f, getHeight() - 60.0f);
        addChild(fpsLabel);
    }
    
    public static void addMarketListener(MarketListener marketListen) {
        marketListener = marketListen;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Keys.BACK || keycode == Keys.ESCAPE || keycode == Keys.HOME) {
            makeDialog();
        } else if (keycode == Keys.MENU) {
            SettingsLayer.make(MainMenuScene.this, 0.0f, 0.0f);
        }
        return true;
    }
    
    private void makeDialog() {
        Skin skin = new Skin(ResourcesManager.uiAtlas);
        TextButton posButt = new TextButton("Yes", ResourcesManager.skin);
        TextButton negButt = new TextButton("No", ResourcesManager.skin);
        Dialog.make(this, ResourcesManager.skin, new Image(skin.getDrawable("dialog_bg")), 
                new Image(skin.getDrawable("list_button_up")), 
                new Image(skin.getDrawable("divider")), "Confirm", "Do  you  really  want  to  exit ?", 
                posButt, negButt, Dialog.ANIMATION_POPUP);
        
        posButt.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
            
        });
        
    }

    @Override
    public void onPositiveButtonClicked() {
       Gdx.app.exit();
    }

    @Override
    public void onNegativeButtonClicked() {
       dialog.dialogCancel();
    }

    @Override
    public void loadWrapper() {
    }

    @Override
    public void loadAddon() {
    }

    /**
    private void makeDialog() {
        new Dialog("\nConfirm", ResourcesManager.skin, "dialog_dark") {
            {
                text(new Label("\n\n\nDo  you  really  want  to  exit?\n\n", ResourcesManager.skin));
                button("Yes", "Yes");
                button("No", "No");
            }

            @Override
            protected void result(Object object) {
                if (object.equals("Yes")) {
                    //ResourcesManager.btnClicked.play(1.0f);
                    Gdx.app.exit();
                } else if (object.equals("No")) {
                    //ResourcesManager.btnClicked.play(1.0f);
                }
            }
        }.show(MainMenuScene.this).center();
    }
    * **/

}
/**
 * end class.
 */
