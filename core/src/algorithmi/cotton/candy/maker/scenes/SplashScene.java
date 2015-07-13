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

import aurelienribon.tweenengine.equations.Linear;
import pk.muneebahmad.witype.nodes.Director;
import pk.muneebahmad.witype.nodes.Scene;

/**
 *
 * @author muneebahmad
 */
public class SplashScene extends Scene {
    
    public SplashScene() {
        setContents();
    }
    
    private void setContents() {
        loadMainScene();
    }
    
    private void loadMainScene() {
            Director.getInstance().pushScene(Director.TRANSITION_FADE_IN, 500, 
                    this, new MainMenuScene(), Linear.INOUT);
    }
    
}/** end class. */
