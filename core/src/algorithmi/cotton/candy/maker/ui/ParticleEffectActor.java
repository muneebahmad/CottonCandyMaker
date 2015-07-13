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

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 *
 * @author muneebahmad
 */
public class ParticleEffectActor extends Actor {
    
    ParticleEffect effect;
    float x;
    float y;
    public static final int START = 1;
    public static final int STOP = 2;
    public int start = 0;
    
    public ParticleEffectActor(ParticleEffect effect, float x, float y) {
        this.x = x;
        this.y = y;
        this.effect = effect;
    }
    
    @Override
    public void draw(Batch batch, float parentAlpha) {
        effect.draw(batch);
    }
    
    @Override
    public void act(float delta) {
        super.act(delta);
        effect.setPosition(x, y);
        effect.update(delta);
        switch(start) {
            case START:
                effect.start();
                break;
            case STOP:
                effect.setDuration(1);
                break;
            default:
                break;
        }
    }
    
    public ParticleEffect getEffect() {
        return this.effect;
    }
    
    public void setStart(int start) {
        this.start = start;
    }
    
}/** end class. */
