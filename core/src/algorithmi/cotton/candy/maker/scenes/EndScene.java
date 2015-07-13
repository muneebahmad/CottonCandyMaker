/**
  * copyright ©2013-2014 ®Algorithmi™.
  *
  * @author ¶muneebahmad¶ (ahmadgallian@yahoo.com) 
  * NetBeans IDE http://www.netbeans.org
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
import algorithmi.cotton.candy.maker.layers.EndSceneLayer;
import algorithmi.cotton.candy.maker.ui.SettingsLayer;
import aurelienribon.tweenengine.equations.Sine;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import pk.muneebahmad.witype.nodes.Director;
import pk.muneebahmad.witype.nodes.Scene;

/**
 *
 * @author muneebahmad
 */
public class EndScene extends Scene {

    private Image bg;
    
    public EndScene() {
        setContents();
    }

    private void setContents() {
        addBackground();
        makeEndLayer();
    }

    private void addBackground() {
        this.bg = new Image(ResourcesManager.endBg);
        this.bg.setWidth(getWidth());
        this.bg.setHeight(getHeight());
        this.bg.setPosition(0.0f, 0.0f);
        addChild(bg);
    }
    
    private void makeEndLayer() {
        EndSceneLayer endLayer = new EndSceneLayer();
        endLayer.setPosition(0.0f, 0.0f);
        addChild(endLayer);
    }
    
     @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.BACK || keycode == Input.Keys.BACKSPACE) {
            Director.getInstance().pushScene(Director.TRANSITION_LEFT_PUSH_IN, 500,
                    this, new DecorationScene(), Sine.INOUT);
        } else if (keycode == Input.Keys.MENU) {
            SettingsLayer.make(EndScene.this, 0.0f, 0.0f);
        }
        return true;
    }
    
}/** end class. */
