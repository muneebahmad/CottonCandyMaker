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
import algorithmi.cotton.candy.maker.ui.Toast;
import aurelienribon.tweenengine.equations.Sine;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import pk.muneebahmad.witype.nodes.Director;
import pk.muneebahmad.witype.nodes.Scene;

/**
 *
 * @author muneebahmad
 */
public class SugarSelectionScene extends Scene {
    
    private Image bg;
    private Image almirahDoor;
    private Image selectSugarLogo;
    private Button leftButt;
    private Button rightButt;
    private final SharedData sharedData;
    
    public SugarSelectionScene() {
        sharedData = SharedData.getInstance();
        sharedData.setCurrentScene(SharedData.SUGAR_SELECTION_SCENE);
        setContents();
    }
    
    private void setContents() {
        setBg();
        addBottles();
        addAlmirah();
        addLogo();
        addHudButtons();
    }
    
    private void setBg() {
        this.bg = new Image(ResourcesManager.firstBg);
        this.bg.setWidth(getWidth());
        this.bg.setHeight(getHeight());
        addChild(this.bg);
    }
    
    private void addAlmirah() {
        this.almirahDoor = new Image(ResourcesManager.almirahDoor);
        this.almirahDoor.setPosition(0.0f, 0.0f);
        addChild(this.almirahDoor);
        
        this.almirahDoor.addListener(new ClickListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (selectSugarLogo != null) {
                selectSugarLogo.addAction(Actions.sequence(Actions.fadeOut(3.0f, Interpolation.pow5), 
                        Actions.removeActor()));
                }
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (almirahDoor.getX() == 0) {
                    almirahDoor.addAction(Actions.moveTo(-420, 0.0f, 2.0f, Interpolation.sine));
                } else if (almirahDoor.getX() < 0) {
                    almirahDoor.addAction(Actions.moveTo(0.0f, 0.0f, 2.0f, Interpolation.sine));
                }
            }
        });
    }
    
    private void addLogo() {
        this.selectSugarLogo = new Image(ResourcesManager.selectSugarLogo);
        this.selectSugarLogo.setPosition(getWidth() / 2.0f - selectSugarLogo.getWidth() / 2.0f, 
                getHeight() / 2.0f - selectSugarLogo.getHeight() / 2.0f);
        addChild(this.selectSugarLogo);
        
        this.selectSugarLogo.addAction(Actions.alpha(0.0f));
        this.selectSugarLogo.addAction(Actions.sequence(Actions.fadeIn(3.0f, Interpolation.pow5), 
                Actions.delay(4.0f), Actions.fadeOut(3.0f, Interpolation.pow5), Actions.removeActor()));
    }
    
    private void addBottles() {
        Skin skin = new Skin(ResourcesManager.textureAtlas);
        //BOTTLE 1
        Image bottle01 = new Image(skin.getDrawable("bottle1"));
        bottle01.setPosition(getWidth() / 3.5f, getHeight() - 145.0f);
        bottle01.setScale(1.3f);
        addChild(bottle01);
        bottle01.addAction(Actions.forever(Actions.
                sequence(Actions.moveTo(getWidth() / 3.5f - 10.0f, getHeight() - 145.0f, 3.0f, 
                        Interpolation.swing), 
                        Actions.moveTo(getWidth() / 3.5f, getHeight() - 145.0f, 3.0f, Interpolation.swing))));
        bottle01.addListener(new ClickListener() { 

            @Override
            public void clicked(InputEvent event, float x, float y) {
                sharedData.setUsedSugar(SharedData.SUGAR_ONE, "bottle1");
                Director.getInstance().pushScene(Director.TRANSITION_BOTTOM_PUSH_IN, 500, 
                        SugarSelectionScene.this, new PouringScene(), Sine.INOUT);
            }
            
        });
        
        //BOTTLE 2
        Image bottle02 = new Image(skin.getDrawable("bottle2"));
        bottle02.setPosition(getWidth() / 1.5f, getHeight() - 145.0f);
        bottle02.setScale(1.3f);
        addChild(bottle02);
        bottle02.addAction(Actions.forever(Actions.
                sequence(Actions.moveTo(getWidth() / 1.5f + 10.0f, getHeight() - 145.0f, 2.5f, 
                        Interpolation.swing), 
                        Actions.moveTo(getWidth() / 1.5f, getHeight() - 145.0f, 2.5f, Interpolation.swing))));
        bottle02.addListener(new ClickListener() { 

            @Override
            public void clicked(InputEvent event, float x, float y) {
                sharedData.setUsedSugar(SharedData.SUGAR_TWO, "bottle2");
                Director.getInstance().pushScene(Director.TRANSITION_BOTTOM_PUSH_IN, 500, 
                        SugarSelectionScene.this, new PouringScene(), Sine.INOUT);
            }
            
        });
        
        //BOTTLE 3
        Image bottle03 = new Image(skin.getDrawable("bottle3"));
        bottle03.setPosition(getWidth() / 3.5f, bottle01.getY() - 145.0f);
        bottle03.setScale(1.3f);
        addChild(bottle03);
        bottle03.addAction(Actions.forever(Actions.
                sequence(Actions.moveTo(getWidth() / 3.5f - 10.0f, bottle01.getY() - 145.0f, 2.5f, 
                        Interpolation.swing), 
                        Actions.moveTo(getWidth() / 3.5f, bottle01.getY() - 145.0f, 2.5f, Interpolation.swing))));
        bottle03.addListener(new ClickListener() { 

            @Override
            public void clicked(InputEvent event, float x, float y) {
                sharedData.setUsedSugar(SharedData.SUGAR_THREE, "bottle3");
                Director.getInstance().pushScene(Director.TRANSITION_BOTTOM_PUSH_IN, 500, 
                        SugarSelectionScene.this, new PouringScene(), Sine.INOUT);
            }
            
        });
        
        //BOTTLE 4
        Image bottle04 = new Image(skin.getDrawable("bottle4"));
        bottle04.setPosition(getWidth() / 1.5f, bottle03.getY());
        bottle04.setScale(1.3f);
        addChild(bottle04);
        bottle04.addAction(Actions.forever(Actions.
                sequence(Actions.moveTo(getWidth() / 1.5f + 10.0f, bottle01.getY() - 145.0f, 3.0f, 
                        Interpolation.swing), 
                        Actions.moveTo(getWidth() / 1.5f, bottle01.getY() - 145.0f, 3.0f, Interpolation.swing))));
        bottle04.addListener(new ClickListener() { 

            @Override
            public void clicked(InputEvent event, float x, float y) {
                sharedData.setUsedSugar(SharedData.SUGAR_FOUR, "bottle4");
                Director.getInstance().pushScene(Director.TRANSITION_BOTTOM_PUSH_IN, 500, 
                        SugarSelectionScene.this, new PouringScene(), Sine.INOUT);
            }
            
        });
        
        //BOTTLE 5
        Image bottle05 = new Image(skin.getDrawable("bottle5"));
        bottle05.setPosition(getWidth() / 3.5f, bottle03.getY() - 145.0f);
        bottle05.setScale(1.3f);
        addChild(bottle05);
        bottle05.addAction(Actions.forever(Actions.
                sequence(Actions.moveTo(getWidth() / 3.5f - 10.0f, bottle03.getY() - 145.0f, 3.0f, 
                        Interpolation.swing), 
                        Actions.moveTo(getWidth() / 3.5f, bottle03.getY() - 145.0f, 3.0f, Interpolation.swing))));
        bottle05.addListener(new ClickListener() { 

            @Override
            public void clicked(InputEvent event, float x, float y) {
                sharedData.setUsedSugar(SharedData.SUGAR_FIVE, "bottle5");
                Director.getInstance().pushScene(Director.TRANSITION_BOTTOM_PUSH_IN, 500, 
                        SugarSelectionScene.this, new PouringScene(), Sine.INOUT);
            }
            
        });
        
        //BOTTLE 6
        Image bottle06 = new Image(skin.getDrawable("bottle6"));
        bottle06.setPosition(getWidth() / 1.5f, bottle05.getY());
        bottle06.setScale(1.3f);
        addChild(bottle06);
        bottle06.addAction(Actions.forever(Actions.
                sequence(Actions.moveTo(getWidth() / 1.5f + 10.0f, bottle03.getY() - 145.0f, 2.5f, 
                        Interpolation.swing), 
                        Actions.moveTo(getWidth() / 1.5f, bottle03.getY() - 145.0f, 2.5f, Interpolation.swing))));
        bottle06.addListener(new ClickListener() { 

            @Override
            public void clicked(InputEvent event, float x, float y) {
                sharedData.setUsedSugar(SharedData.SUGAR_SIX, "bottle6");
                Director.getInstance().pushScene(Director.TRANSITION_BOTTOM_PUSH_IN, 500, 
                        SugarSelectionScene.this, new PouringScene(), Sine.INOUT);
            }
            
        });
        
        //BOTTLE 7
        Image bottle07 = new Image(skin.getDrawable("bottle7"));
        bottle07.setPosition(getWidth() / 3.5f, bottle06.getY() - 145.0f);
        bottle07.setScale(1.3f);
        addChild(bottle07);
        bottle07.addAction(Actions.forever(Actions.
                sequence(Actions.moveTo(getWidth() / 3.5f - 10.0f, bottle06.getY() - 145.0f, 2.5f, 
                        Interpolation.swing), 
                        Actions.moveTo(getWidth() / 3.5f, bottle06.getY() - 145.0f, 2.5f, Interpolation.swing))));
        bottle07.addListener(new ClickListener() { 

            @Override
            public void clicked(InputEvent event, float x, float y) {
                sharedData.setUsedSugar(SharedData.SUGAR_SEVEN, "bottle7");
                Director.getInstance().pushScene(Director.TRANSITION_BOTTOM_PUSH_IN, 500, 
                        SugarSelectionScene.this, new PouringScene(), Sine.INOUT);
            }
            
        });
        
        //BOTTLE 8
        Image bottle08 = new Image(skin.getDrawable("bottle8"));
        bottle08.setPosition(getWidth() / 1.5f, bottle07.getY());
        bottle08.setScale(1.3f);
        addChild(bottle08);
        bottle08.addAction(Actions.forever(Actions.
                sequence(Actions.moveTo(getWidth() / 1.5f + 10.0f, bottle06.getY() - 145.0f, 3.0f, 
                        Interpolation.swing), 
                        Actions.moveTo(getWidth() / 1.5f, bottle06.getY() - 145.0f, 3.0f, Interpolation.swing))));
        bottle08.addListener(new ClickListener() { 

            @Override
            public void clicked(InputEvent event, float x, float y) {
                sharedData.setUsedSugar(SharedData.SUGAR_EIGHT, "bottle8");
                Director.getInstance().pushScene(Director.TRANSITION_BOTTOM_PUSH_IN, 500, 
                        SugarSelectionScene.this, new PouringScene(), Sine.INOUT);
            }
            
        });
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
                        SugarSelectionScene.this, new MainMenuScene(), Sine.INOUT);
            }
            
        });
        
        //RIGHT BUTTON
        this.rightButt = new Button(ResourcesManager.skin, "right");
        this.rightButt.setPosition((getWidth() - rightButt.getWidth()) - 50.0f, 10.0f);
        addChild(this.rightButt);
        this.rightButt.addListener(new ClickListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                ResourcesManager.btnClicked.play();
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                ResourcesManager.btnClicked.play();
                Toast.make(SugarSelectionScene.this, "Please select sugar\n" + "and proceed!", 
                        Toast.POSITION_CENTER, 5.0f);
            }
        });
    }
    
    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Keys.BACK || keycode == Keys.BACKSPACE) {
            Director.getInstance().pushScene(Director.TRANSITION_LEFT_PUSH_IN, 500, this, 
                    new MainMenuScene(), Sine.INOUT);
        } else if (keycode == Keys.MENU) {
            SettingsLayer.make(SugarSelectionScene.this, 0.0f, 0.0f);
        }
        return true;
    }
    
}/** end class. */
