/**
 * copyright (c)2013-2014 Algorithmi.
 *
 * @author muneebahmad (ahmadgallian@yahoo.com)
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
package pk.muneebahmad.algotype.actions;

import aurelienribon.tweenengine.Tween;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.utils.Pool;

/**
 *
 * @author muneebahmad
 */
public class TweenAction extends Action {

    private static final Pool<TweenAction> pool = new Pool<TweenAction>(10, 100) {
        @Override
        protected TweenAction newObject() {
            TweenAction action = new TweenAction();

            action.setPool(this);

            return action;
        }

        @Override
        public void free(TweenAction object) {
            super.free(object);

            object.getTween().free();
        }

    };

    private Tween tween;
    protected boolean done;

    /**
     * Get instance from pool.
     *
     * @param tween The associated tween.
     *
     * @return Pooled instance.
     */
    public static TweenAction $(Tween tween) {
        TweenAction action = pool.obtain();

        action.setTween(tween);

        return action;
    }

    @Override
    public boolean act(float delta) {
        boolean done = tween.isFinished();

        if (!done) {
            tween.update((int) (delta * 1000));
        }

        return done;
    }

    private void setTween(Tween tween) {
        this.tween = tween;
    }

    public Tween getTween() {
        return tween;
    }

    /**
     * Set done status.
     *
     * @param done
     */
    public void setDone(boolean done) {
        this.done = done;
    }
}/** end class. */
