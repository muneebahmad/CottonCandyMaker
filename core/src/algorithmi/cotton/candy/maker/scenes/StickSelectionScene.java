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
import algorithmi.cotton.candy.maker.ui.SettingsLayer;
import algorithmi.cotton.candy.maker.ui.Toast;
import aurelienribon.tweenengine.equations.Sine;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import pk.muneebahmad.witype.nodes.Director;
import pk.muneebahmad.witype.nodes.Layer;
import pk.muneebahmad.witype.nodes.Scene;

/**
 *
 * @author muneebahmad
 */
public class StickSelectionScene extends Scene {
    
    private Image bg;
    private Image stickSelectionLogo;
    private Button leftButt;
    private Button rightButt;
    private ScrollPane scrollPane;
    private Layer stickLayer;
    private Table table;
    private final SharedData sharedData;
    
    public StickSelectionScene() {
        sharedData = SharedData.getInstance();
        sharedData.setCurrentScene(SharedData.STICK_SELECTION_SCENE);
        setContents();
    }
    
    private void setContents() {
        setBg();
        addSticks();
        addStickLogo();
        addHudButtons();
    }
    
    private void setBg() {
        this.bg = new Image(ResourcesManager.stickBg);
        this.bg.setWidth(getWidth());
        this.bg.setHeight(getHeight());
        addChild(this.bg);
    }
    
    private void addSticks() {
        Skin skin = new Skin(ResourcesManager.textureAtlas);
        this.stickLayer = Layer.make();
        this.stickLayer.setSize(480.0f, 800.0f);
        this.stickLayer.setPosition(-getWidth(), 
                getHeight() / 2.0f - this.stickLayer.getHeight() / 2.0f);
        
        //Stick 1
        Image stick1 = new Image(skin.getDrawable("stick1"));
        stick1.setScale(2.0f);
        stick1.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                sharedData.setUsedStick(SharedData.STICK1, "stick1");
                Director.getInstance().pushScene(Director.TRANSITION_RIGHT_PUSH_IN, 500,
                        StickSelectionScene.this, new MachineScene(), Sine.INOUT);
            }
            
        });
        //Stick 2
        Image stick2 = new Image(skin.getDrawable("stick2"));
        stick2.setScale(2.0f);
        stick2.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                sharedData.setUsedStick(SharedData.STICK2, "stick2");
                Director.getInstance().pushScene(Director.TRANSITION_RIGHT_PUSH_IN, 500,
                        StickSelectionScene.this, new MachineScene(), Sine.INOUT);
            }
            
        });
        //Stick 3
        Image stick3 = new Image(skin.getDrawable("stick3"));
        stick3.setScale(2.0f);
        stick3.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                sharedData.setUsedStick(SharedData.STICK3, "stick3");
                Director.getInstance().pushScene(Director.TRANSITION_RIGHT_PUSH_IN, 500,
                        StickSelectionScene.this, new MachineScene(), Sine.INOUT);
            }
            
        });
        //Stick 4
        Image stick4 = new Image(skin.getDrawable("stick4"));
        stick4.setScale(2.0f);
        stick4.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                sharedData.setUsedStick(SharedData.STICK4, "stick4");
                Director.getInstance().pushScene(Director.TRANSITION_RIGHT_PUSH_IN, 500,
                        StickSelectionScene.this, new MachineScene(), Sine.INOUT);
            }
            
        });
        //Stick 5
        Image stick5 = new Image(skin.getDrawable("stick5"));
        stick5.setScale(2.0f);
        stick5.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                sharedData.setUsedStick(SharedData.STICK5, "stick5");
                Director.getInstance().pushScene(Director.TRANSITION_RIGHT_PUSH_IN, 500,
                        StickSelectionScene.this, new MachineScene(), Sine.INOUT);
            }
            
        });
        //Stick 6
        Image stick6 = new Image(skin.getDrawable("stick6"));
        stick6.setScale(2.0f);
        stick6.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                sharedData.setUsedStick(SharedData.STICK6, "stick6");
                Director.getInstance().pushScene(Director.TRANSITION_RIGHT_PUSH_IN, 500,
                        StickSelectionScene.this, new MachineScene(), Sine.INOUT);
            }
            
        });
        //Stick 7
        Image stick7 = new Image(skin.getDrawable("stick7"));
        stick7.setScale(2.0f);
        stick7.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                sharedData.setUsedStick(SharedData.STICK7, "stick7");
                Director.getInstance().pushScene(Director.TRANSITION_RIGHT_PUSH_IN, 500,
                        StickSelectionScene.this, new MachineScene(), Sine.INOUT);
            }
            
        });
        //Stick 8
        Image stick8 = new Image(skin.getDrawable("stick8"));
        stick8.setScale(2.0f);
        stick8.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                sharedData.setUsedStick(SharedData.STICK8, "stick8");
                Director.getInstance().pushScene(Director.TRANSITION_RIGHT_PUSH_IN, 500,
                        StickSelectionScene.this, new MachineScene(), Sine.INOUT);
            }
            
        });
        //Stick 9
        Image stick9 = new Image(skin.getDrawable("stick9"));
        stick9.setScale(2.0f);
        stick9.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                sharedData.setUsedStick(SharedData.STICK9, "stick9");
                Director.getInstance().pushScene(Director.TRANSITION_RIGHT_PUSH_IN, 500,
                        StickSelectionScene.this, new MachineScene(), Sine.INOUT);
            }
            
        });
        
        this.table = new Table();
        
        table.row();
        table.add(stick1).fillX().padLeft(50.0f).padRight(50.0f).padBottom(20.0f);
        table.add(stick2).fillX().padLeft(50.0f).padRight(50.0f).padBottom(20.0f);
        table.add(stick3).fillX().padLeft(50.0f).padRight(50.0f).padBottom(20.0f);
        table.add(stick4).fillX().padLeft(50.0f).padRight(50.0f).padBottom(20.0f);
        table.add(stick5).fillX().padLeft(50.0f).padRight(50.0f).padBottom(20.0f);
        table.add(stick6).fillX().padLeft(50.0f).padRight(50.0f).padBottom(20.0f);
        table.add(stick7).fillX().padLeft(50.0f).padRight(50.0f).padBottom(20.0f);
        table.add(stick8).fillX().padLeft(50.0f).padRight(50.0f).padBottom(20.0f);
        table.add(stick9).fillX().padLeft(50.0f).padRight(50.0f).padBottom(20.0f);
 
        table.bottom();
        table.pack();
        
        this.scrollPane = new ScrollPane(table, ResourcesManager.skin);
        this.stickLayer.addChild(scrollPane);
        this.scrollPane.setWidth(400.0f);
        this.scrollPane.setHeight(450.0f);
        this.scrollPane.setPosition(stickLayer.getWidth() / 2.0f - scrollPane.getWidth() / 2.0f, 
                stickLayer.getHeight() / 2.0f - scrollPane.getHeight() / 2.0f - 50.0f);
        this.scrollPane.setScrollingDisabled(false, true);
        
        addChild(stickLayer);
        this.stickLayer.addAction(Actions.sequence(Actions.delay(1.0f), 
                Actions.moveTo(getWidth() / 2.0f - this.stickLayer.getWidth() / 2.0f, 
                getHeight() / 2.0f - this.stickLayer.getHeight() / 2.0f, 1.5f, Interpolation.swingOut)));
    }
    
    private void addStickLogo() {
        this.stickSelectionLogo = new Image(ResourcesManager.stickSelectionLogo);
        this.stickSelectionLogo.setPosition(getWidth() / 2.0f - stickSelectionLogo.getWidth() / 2.0f, 
                getHeight() - stickSelectionLogo.getHeight() - 30.0f );
        addChild(stickSelectionLogo);
        
        this.stickSelectionLogo.setOrigin(stickSelectionLogo.getWidth() / 2.0f, 
                stickSelectionLogo.getHeight() / 2.0f);
        this.stickSelectionLogo.addAction(Actions.forever(Actions.sequence(Actions.scaleTo(0.9f, 0.9f, 0.5f), 
                Actions.scaleTo(1.0f, 1.0f, 0.5f))));
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
                        StickSelectionScene.this, new PouringScene(), Sine.INOUT);
            }
            
        });
        
        //RIGHT BUTTON
        this.rightButt = new Button(ResourcesManager.skin, "right");
        this.rightButt.setPosition((getWidth() - rightButt.getWidth()) - 50.0f, 10.0f);
        addChild(this.rightButt);
        this.rightButt.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                ResourcesManager.btnClicked.play();
                Toast.make(StickSelectionScene.this, "Please Select Stick to Continue.", 
                        Toast.POSITION_CENTER, 5.0f);
            }
            
        });
    }
    
    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Keys.BACK || keycode == Keys.BACKSPACE) {
            Director.getInstance().pushScene(Director.TRANSITION_LEFT_PUSH_IN, 500, 
                    this, new PouringScene(), Sine.INOUT);
        } else if (keycode == Keys.MENU) {
            SettingsLayer.make(StickSelectionScene.this, 0.0f, 0.0f);
        }
        return true;
    }
    
}/** end class. */
