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
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import pk.muneebahmad.witype.nodes.Layer;
import pk.muneebahmad.witype.nodes.Scene;

/**
 * 
 * @author muneebahmad
 */
public class Toast {
    private Image img;
    private Skin skin;
    private Label label;
    
    public static final int POSITION_TOP = 0;
    public static final int POSITION_CENTER = 1;
    public static final int POSITION_BOTTOM = 2;
    private int toastPostion;
    private float pos;
    
    public Toast() { }
    
    public Toast(Scene scene, String msg, int toastPosition, float duration) {
        makeToast(scene, msg, toastPosition, duration);
    }
    
    private void makeToast(Scene scene, String msg, int toastPosition, float duration) {
        this.toastPostion = toastPosition;
        Layer toastLayer = Layer.make();
        this.skin = new Skin(ResourcesManager.uiAtlas);
        label = new Label(msg, ResourcesManager.skin);
        
        img = new Image(skin.getDrawable("list_button_up"));
        img.setWidth(label.getWidth() + 50.0f);
        img.setHeight(label.getHeight() + 50.0f);
        
        toastLayer.setWidth(img.getWidth());
        toastLayer.setHeight(img.getHeight());
        
        img.setPosition(toastLayer.getWidth() / 2.0f - img.getWidth() / 2.0f, 
                toastLayer.getHeight() / 2.0f - img.getHeight() / 2.0f);
        
        label.setPosition(toastLayer.getWidth() / 2.0f - label.getWidth() / 2.0f, 
                toastLayer.getHeight() / 2.0f - label.getHeight() / 2.0f);
        
        toastLayer.addChild(img);
        toastLayer.addChild(label);
        
        switch (toastPosition) {
            case POSITION_TOP:
                pos = scene.getHeight() / 1.2f - toastLayer.getHeight() / 2.0f;
                break;
            case POSITION_CENTER:
                pos = scene.getHeight() / 2.0f - toastLayer.getHeight() / 2.0f;
                break;
            case POSITION_BOTTOM:
                pos = scene.getHeight() / 4.0f - toastLayer.getHeight() / 2.0f;
                break;
            default:
                break;
        }
        
        toastLayer.setPosition(scene.getWidth() / 2.0f - toastLayer.getWidth() / 2.0f, pos);
        scene.addChild(toastLayer);
        
        toastLayer.setOriginX(toastLayer.getWidth() / 2.0f);
        toastLayer.setOriginY(toastLayer.getHeight() / 2.0f);
        toastLayer.addAction(Actions.alpha(0.0f));
        toastLayer.addAction(Actions.scaleTo(0.0f, 0.0f));
        toastLayer.addAction(Actions.sequence(Actions.parallel(Actions.fadeIn(1.5f, 
                Interpolation.pow5), Actions.scaleTo(1.0f, 1.0f, 1.5f, Interpolation.swing)), 
                Actions.delay(duration), Actions.parallel(Actions.fadeOut(1.5f, Interpolation.pow5), 
                        Actions.scaleTo(0.0f, 0.0f, 1.5f, Interpolation.swing)), Actions.removeActor(toastLayer)));
        
    }
    
    public static Toast make(Scene scene, String msg, int toastPosition, float duration) {
        return new Toast(scene, msg, toastPosition, duration);
    }
}/** end class. */