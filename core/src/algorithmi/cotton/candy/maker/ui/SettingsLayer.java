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


package algorithmi.cotton.candy.maker.ui;

import algorithmi.cotton.candy.maker.data.ResourcesManager;
import algorithmi.cotton.candy.maker.data.Settings;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import pk.muneebahmad.witype.nodes.Layer;
import pk.muneebahmad.witype.nodes.Scene;

/**
 *
 * @author muneebahmad
 */
public class SettingsLayer {
    
    private Image bg;
    private Button soundButt;
    private Button closeButt;
    private Skin skin;
    private Label title;
    private Layer settingsLayer;
    
    public SettingsLayer() { }
    
    /**
     * 
     * @param scene
     * @param x
     * @param y 
     */
    public SettingsLayer(Scene scene, float x, float y) {
        makeSettingsLayer(scene, x, y);
    }
    
    /**
     * 
     * @param scene
     * @param x
     * @param y 
     */
    private void makeSettingsLayer(final Scene scene, float x, float y) {
        settingsLayer = Layer.make();
        this.skin = new Skin(ResourcesManager.uiAtlas);
        this.title = new Label("Settings", ResourcesManager.skin);
        
        this.bg = new Image(skin.getDrawable("dialog_bg2"));
        this.bg.setWidth(440.0f);
        this.bg.setHeight(100.0f);
        
        settingsLayer.setWidth(480.0f);
        settingsLayer.setHeight(200.0f);
        
        this.bg.setPosition(0.0f, 0.0f);
        title.setPosition(settingsLayer.getWidth() / 2.0f - title.getWidth() / 2.0f, bg.getHeight() - 30.0f);
        
        this.soundButt = new Button(ResourcesManager.skin, "sound");
        this.soundButt.setPosition(bg.getX() + bg.getWidth() / 4.0f - soundButt.getWidth() / 2.0f, 5.0f);
        if (Settings.getInstance().getSoundMode() == Settings.MODE_SOUND_ON) {
            soundButt.setChecked(false);
        } else if (Settings.getInstance().getSoundMode() == Settings.MODE_SOUND_OFF) {
            soundButt.setChecked(true);
        }
        this.soundButt.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (Settings.getInstance().getSoundMode() == Settings.MODE_SOUND_ON) {
                    Settings.getInstance().setSoundMode(Settings.MODE_SOUND_OFF);
                    ResourcesManager.bgSound.setLooping(false);
                    ResourcesManager.bgSound.stop();
                    ResourcesManager.machineSound.setLooping(false);
                    ResourcesManager.machineSound.stop();
                } else if (Settings.getInstance().getSoundMode() == Settings.MODE_SOUND_OFF) {
                    Settings.getInstance().setSoundMode(Settings.MODE_SOUND_ON);
                    ResourcesManager.bgSound.setLooping(true);
                    ResourcesManager.bgSound.play();
                }
            }
        });
        
        this.closeButt = new Button(ResourcesManager.skin, "close");
        this.closeButt.setPosition(settingsLayer.getWidth() - 80.0f, bg.getHeight() - 40.0f);
        closeButt.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                settingsLayer.addAction(Actions.sequence(Actions.moveTo(-1000.0f, 0.0f, 1.2f), 
                        Actions.removeActor(settingsLayer)));
            }
        });
        
        settingsLayer.addChild(bg);
        settingsLayer.addChild(title);
        settingsLayer.addChild(soundButt);
        settingsLayer.addChild(closeButt);
        
        settingsLayer.setPosition(-1000.0f, 0.0f);
        scene.addChild(settingsLayer);
        
        settingsLayer.addAction(Actions.moveTo(0.0f, 0.0f, 1.2f));
        
    }
    
    /**
     * 
     * @param scene
     * @param x
     * @param y
     * @return 
     */
    public static SettingsLayer make(final Scene scene, float x , float y) {
        return new SettingsLayer(scene, x, y);
    }
    
}/** end class. */
