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

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 *
 * @author muneebahmad
 */
public class Sprite extends Actor {
    private final TextureRegion textureRegion;
    
    /**
     * Create Sprite
     * @param textureRegion 
     */
    public Sprite(TextureRegion textureRegion) {
        this.textureRegion = textureRegion;
        
        setWidth(textureRegion.getRegionWidth());
        setHeight(textureRegion.getRegionHeight());
    }
    
    /**
     * 
     * @param batch
     * @param parentAlpha 
     */
    @Override
    public void draw(Batch batch, float parentAlpha) {
       batch.draw(textureRegion, getX(), getY());
    }
    
    @Override
    public Actor hit(float x, float y, boolean touchable) {
        return x > 0 && x < getWidth() && y > 0 && y < getHeight() ? this : null;
    }
    
}/** end class. */
