/**
 * copyright ©2013-2014 ®Algorithmi™.
 *
 * @author ¶muneebahmad¶ (ahmadgallian@yahoo.com)
 *
 * The following source - code IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY
 * KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO
 * EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES
 * OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
  * *
 */
package pk.muneebahmad.witype.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.utils.BaseDrawable;

/**
 *
 * @author muneebahmad
 */
public class AnimationDrawable extends BaseDrawable {

    public final Animation anim;
    private float stateTime = 0;
    private boolean looping = false;
    private boolean stop = false;
    private float time = 0.0f;

    public AnimationDrawable(Animation anim) {
        this.anim = anim;
        setMinWidth(anim.getKeyFrame(0).getRegionWidth());
        setMinHeight(anim.getKeyFrame(0).getRegionHeight());
    }
    
    public AnimationDrawable(Animation anim, boolean looping) {
        this.anim = anim;
        this.looping = looping;
        setMinWidth(anim.getKeyFrame(0).getRegionWidth());
        setMinHeight(anim.getKeyFrame(0).getRegionHeight());
    }
    
    public AnimationDrawable(Animation anim, float time) {
        this.anim = anim;
        this.time = time;
        setMinWidth(anim.getKeyFrame(0).getRegionWidth());
        setMinHeight(anim.getKeyFrame(0).getRegionHeight());
    }
    
    public void stop(boolean stop) {
        this.stop = stop;
    }

    public void act(float delta) {
        if (stop) {
            stateTime = 0;
        } else {
        stateTime += delta;
        }
    }

    public void reset() {
        stateTime = 0;
    }

    @Override
    public void draw(Batch batch, float x, float y,
            float width, float height) {
        
        if (looping) {
            batch.draw(anim.getKeyFrame(stateTime, looping), x, y, width, height);
        } else {
            batch.draw(anim.getKeyFrame(stateTime), x, y, width, height);
        }
    }
}
