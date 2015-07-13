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
public class CallBackAction extends Action {

    static final Pool<CallBackAction> pool = new Pool<CallBackAction>(4, 100) {
        @Override
        protected CallBackAction newObject() {
            CallBackAction action = new CallBackAction();

            action.setPool(this);

            return action;
        }
    };

    private ActionCallBack callBack;

    /**
     * Pooled constructor.
     *
     * @param count Call back N times.
     * @param duration The duration between call-backs.
     * @param callBack The call back object reference.
     *
     * @return The pooled object.
     */
    public static CallBackAction $(ActionCallBack callBack) {
        CallBackAction callBackDelay = pool.obtain();
        callBackDelay.callBack = callBack;

        return callBackDelay;
    }

    /**
     * Execute action.
     *
     * @return 
     */
    @Override
    public boolean act(float delta) {
        boolean done = true;

        callBack.onCallBack();

        return done;
    }
}/** end class. */
