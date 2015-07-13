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


package pk.muneebahmad.witype.sprite;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;

/**
 *
 * @author muneebahmad
 */
public class AnimatedSprite extends Group {
    private FrameSprite frameSprite;
    
    /**
     * 
     * @param textureRegion
     * @param rows
     * @param cols
     * @param frameDuration
     * @param looping 
     */
    public AnimatedSprite(TextureRegion textureRegion, int rows, int cols,
            float frameDuration, boolean looping) {
       frameSprite = new FrameSprite(textureRegion, rows, cols, 
               frameDuration, looping);
       
        setWidth(frameSprite.getWidth());
        setHeight(frameSprite.getHeight());
        
        addActor(frameSprite);
    }
    
    /**
     * 
     * @param textureRegion
     * @param rows
     * @param cols
     * @param frameDuration 
     */
    public AnimatedSprite(TextureRegion textureRegion, int rows, int cols,
            float frameDuration) {
       this(textureRegion, rows, cols, frameDuration, true);
    }
}/** end class. */
