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

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 *
 * @author muneebahmad
 */
public class FrameSprite extends Actor {
    private final TextureRegion[] frames;
    private final Animation animation;
    private TextureRegion currentFrame;
    
    private float stateTime;
    private boolean looping;
    
    /**
     * 
     * @param texture
     * @param rows
     * @param cols
     * @param frameDuration
     * @param looping 
     */
    public FrameSprite(TextureRegion texture, int rows, int cols,
            float frameDuration, boolean looping) {
       this.looping = looping;
       int tileWidth = texture.getRegionWidth() / cols;
       int tileHeight = texture.getRegionHeight() / rows;
       
       TextureRegion[][] tmp = texture.split(tileWidth, tileHeight);
       frames = new TextureRegion[cols * rows];
       
       int index = 0;
       for (int i = 0; i < rows; i++) {
           for (int j = 0; j < cols; j++) {
               frames[index++] = tmp[i][j];
           }
       }
       
        setWidth(tileWidth);
        setHeight(tileHeight);
        
        animation = new Animation(frameDuration, frames);
        stateTime = 0;
    }
    
    /**
     * 
     * @param batch
     * @param parentAlpha 
     */
    @Override
    public void draw(Batch batch, float parentAlpha) {
        stateTime += Gdx.graphics.getDeltaTime();
        currentFrame = animation.getKeyFrame(stateTime, looping);
        
        batch.draw(currentFrame, getX(), getY());
    }
    
    /**
     * 
     * @param x
     * @param y
     * @param touchable
     * @return 
     */
    @Override
    public Actor hit(float x, float y, boolean touchable) {
        return x > 0 && x < getWidth() && y > 0 && y < getHeight() ? this : null;
    }
    
    public void resetAnimation() {
        stateTime = 0;
    }
    
    /**
     * 
     * @return 
     */
    public boolean isAnimationFinished() {
        return animation.isAnimationFinished(stateTime);
    }
    
}/** end class. */
