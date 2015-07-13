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

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.utils.Pool;

/**
 *
 * @author muneebahmad
 */
public class RepeatDelayAction extends Action {

    static final Pool<RepeatDelayAction> pool = new Pool<RepeatDelayAction>(4, 100) {
        @Override
        protected RepeatDelayAction newObject() {
            RepeatDelayAction action = new RepeatDelayAction();

            action.setPool(this);

            return action;
        }
    };

    private int count;
    private int current;
    private float taken;
    private float duration;
    private Action action;

    /**
     * Pooled constructor.
     *
     * @param count Call back N times.
     * @param duration The duration between call-backs.
     * @param action Action to execute.
     *
     * @return The pooled object.
     */
    public static RepeatDelayAction $(int count, float duration, Action action) {
        RepeatDelayAction callBackDelay = pool.obtain();
        callBackDelay.duration = duration;
        callBackDelay.action = action;
        callBackDelay.count = count;
        callBackDelay.current = count;

        return callBackDelay;
    }

    @Override
    public void reset() {
        super.reset();

        taken = 0;
    }

    /**
     * Execute action.
     *
     * @return 
     */
    @Override
    public boolean act(float delta) {
        boolean done = false;

        taken += delta;
        if (taken > duration) {
            if (count < 0 || current > 0) {
                if (current > 0) {
                    current--;
                }

                action.act(delta);
            } else {
                done = true;
            }

            // Reset delay.
            taken = 0;
        }

        return done;
    }
}/** end class. */
