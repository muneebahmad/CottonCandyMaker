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

import algorithmi.cotton.candy.maker.data.ResourcesManager;
import algorithmi.cotton.candy.maker.data.SharedData;
import algorithmi.cotton.candy.maker.ui.SettingsLayer;
import algorithmi.cotton.candy.maker.ui.GridLayer;
import algorithmi.cotton.candy.maker.ui.GridlayerInterface;
import aurelienribon.tweenengine.equations.Sine;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.vungle.sdk.VunglePub;
import pk.muneebahmad.witype.nodes.Director;
import pk.muneebahmad.witype.nodes.Scene;

/**
 *
 * @author muneebahmad
 */
public class DecorationScene extends Scene implements GridlayerInterface {
    
    private Image bg;
    private Image bg2;
    private Image cloud;
    private Image thunder;
    private Image wrapper;
    private Image addon;
    private Button rightButt;
    private Button leftButt;
    private Image stick;
    private Image candy;
    private final Skin skin = new Skin(ResourcesManager.textureAtlas);
    private final Skin addonSkin = new Skin(ResourcesManager.addonAtlas);
    
    float rightX = 256.0f;
    
    public DecorationScene() {
        SharedData.getInstance().setCurrentScene(SharedData.DECORATION_SCENE);
        setContents();
    }
    
    private void setContents() {
        setBg();
        addStick();
        addCloud();
        addCandy();
        addTopButtons();
        addHudButtons();
    }

    private void setBg() {
        this.bg = new Image(ResourcesManager.decorationBg1);
        this.bg.setWidth(getWidth());
        this.bg.setHeight(getHeight());
        addChild(this.bg);
        
        this.bg2 = new Image(ResourcesManager.decorationBg2);
        this.bg2.setWidth(getWidth());
        this.bg2.setHeight(getHeight());
        addChild(this.bg2);
        
        this.bg2.addAction(Actions.alpha(0.0f));
        this.bg2.addAction(Actions.forever(Actions.sequence(Actions.fadeIn(0.5f), Actions.fadeOut(0.5f))));
        
        Gdx.app.log("Memory", Gdx.app.getType() + " ---> " + Gdx.app.getNativeHeap() + " ---> " + Gdx.app.getJavaHeap());
    }
    
    private void addCloud() {
        this.cloud = new Image(ResourcesManager.cloud);
        this.cloud.setPosition(getWidth() / 2.0f - cloud.getWidth() / 2.0f, getHeight() - 150.0f);
        addChild(cloud);
        
        cloud.addAction(Actions.alpha(0.0f));
        cloud.addAction(Actions.forever(Actions.sequence(Actions.fadeIn(1.0f, Interpolation.swing), 
                Actions.delay(5.0f), Actions.fadeOut(1.0f), Actions.delay(5.0f))));
        
        this.thunder = new Image(ResourcesManager.thunder);
        this.thunder.setPosition(getWidth() / 2.0f - thunder.getWidth() / 2.0f, cloud.getY() - 20.0f);
        addChild(this.thunder);
        
        thunder.addAction(Actions.alpha(0.0f));
        thunder.addAction(Actions.forever(Actions.sequence(Actions.fadeIn(0.3f, Interpolation.swing), 
                Actions.delay(0.5f), Actions.fadeOut(0.3f), Actions.delay(0.5f), Actions.fadeIn(0.3f, Interpolation.swing),
                Actions.delay(0.5f), Actions.fadeOut(0.3f), Actions.delay(9.6f))));
    }
    
    private void addStick() {
        this.stick = new Image(skin.getDrawable(SharedData.getInstance().
                getUsedStickName()));
        this.stick.setPosition(getWidth() / 2.0f - stick.getWidth() / 2.0f, 120.0f);
        addChild(this.stick);
        this.stick.setScale(2.0f);
        
        SharedData.getInstance().setStickTopPos(this.stick.getTop());
    }
    
    private void addCandy() {
        String tex = "candy1";
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
        this.candy.setPosition(getWidth() / 2.0f - candy.getWidth() * 2, stick.getTop() - 50.0f);
        addChild(candy);
    }
    
    private void addTopButtons() {
        Button wrapperButt = new Button(ResourcesManager.skin, "wrapper");
        wrapperButt.setOrigin(wrapperButt.getWidth() / 2.0f, wrapperButt.getHeight() / 2.0f);
        wrapperButt.setPosition(100.0f, getHeight() - 100.0f);
        addChild(wrapperButt);
        wrapperButt.addAction(Actions.forever(Actions.sequence(Actions.moveTo(100.0f, getHeight() - 95.0f, 0.2f), 
                Actions.moveTo(100.0f, getHeight() - 100.0f, 00.2f))));
        wrapperButt.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                ResourcesManager.btnClicked.play();
                SharedData.getInstance().setGridMode(SharedData.GRID_MODE_WRAPPER);
                GridLayer.make(DecorationScene.this, "Select Wrappers", DecorationScene.this);
            }
            
        });
        
        Button addOnButt = new Button(ResourcesManager.skin, "addon");
        addOnButt.setOrigin(addOnButt.getWidth() / 2.0f, addOnButt.getHeight() / 2.0f);
        addOnButt.setPosition(getWidth() - 100.0f - addOnButt.getWidth(), getHeight() - 100.0f);
        addChild(addOnButt);
        addOnButt.addAction(Actions.forever(Actions.sequence(Actions.moveTo(getWidth() - 100.0f - addOnButt.getWidth(), 
                getHeight() - 100.0f, 0.2f),
                Actions.moveTo(getWidth() - 100.0f - addOnButt.getWidth(), getHeight() - 95.0f, 0.2f))));
        addOnButt.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                ResourcesManager.btnClicked.play();
                SharedData.getInstance().setGridMode(SharedData.GRID_MODE_ADDON);
                GridLayer.make(DecorationScene.this, "Select  Toys", DecorationScene.this);
            }
            
        });
    }
    
    /**
     * 
     */
    
    
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
                        DecorationScene.this, new MachineScene(), Sine.INOUT);
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
                        DecorationScene.this, new EndScene(), Sine.INOUT);
                if (Gdx.app.getType() == ApplicationType.Android) {
                    VunglePub.displayAdvert();
                }
            }
            
        });
    }
    
    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Keys.BACKSPACE || keycode == Keys.BACK) {
            Director.getInstance().pushScene(Director.TRANSITION_LEFT_PUSH_IN, 500, 
                        DecorationScene.this, new MachineScene(), Sine.INOUT);
        } else if (keycode == Keys.MENU) {
            SettingsLayer.make(DecorationScene.this, 0.0f, 0.0f);
        }
        return true;
    }

    @Override
    public void loadWrapper() {
        addWrapper();
    }

    @Override
    public void loadAddon() {
        addAddon();
    }

    private void addAddon() {
        if (this.addon == null) {
            this.addon = new Image(addonSkin.getDrawable(SharedData.getInstance().getUsedAddonName()));
        } else {
            this.addon.setDrawable(addonSkin.getDrawable(SharedData.getInstance().getUsedAddonName()));
        }
        this.addon.setPosition(getWidth() / 2.0f - this.addon.getWidth() / 2.0f, getHeight()/ 2.0f - addon.getHeight() / 2.0f);
        addChild(this.addon);
        this.addon.setZIndex(8);
        SharedData.getInstance().setAddonPosition(addon.getX(), addon.getY());
        
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
                    SharedData.getInstance().setAddonPosition(addon.getX(), addon.getY());
                }
            });
        }
        
        if ((this.wrapper != null) && (this.addon != null)) {
            runRightButtFinalActions();
        }
    }
    
    private void addWrapper() {
        if (this.wrapper == null) {
            this.wrapper = new Image(skin.getDrawable(SharedData.getInstance().getUsedWrapperName()));
        } else {
            this.wrapper.setDrawable(skin.getDrawable(SharedData.getInstance().getUsedWrapperName()));
        }
        this.wrapper.setScale(2.3f);
        this.wrapper.setPosition(this.candy.getX() - 25.0f, 
                this.candy.getY() - 70.0f);
        addChild(this.wrapper);
        this.wrapper.setZIndex(7);
        
        if ((this.wrapper != null) && (this.addon != null)) {
            runRightButtFinalActions();
        }
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
    
}/** end class. */
