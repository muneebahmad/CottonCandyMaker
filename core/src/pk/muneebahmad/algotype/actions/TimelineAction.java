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

import aurelienribon.tweenengine.Timeline;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.utils.Pool;

/**
 *
 * @author muneebahmad
 */
public class TimelineAction extends Action {

    private static final Pool<TimelineAction> pool = new Pool<TimelineAction>(10, 100) {
        @Override
        protected TimelineAction newObject() {
            TimelineAction action = new TimelineAction();

            action.setPool(this);

            return action;
        }

        /**
         * Override the 'free to ensure the object is removed from the 'world'
         * as well.
         */
        @Override
        public void free(TimelineAction object) {
            super.free(object);

            object.getTimeline().free();
        }

    };

    private Timeline timeline;
    protected boolean done;

    /**
     * Get instance from pool.
     *
     * @param timeline The action time-line to execute.
     *
     * @return Pooled instance.
     */
    public static TimelineAction $(Timeline timeline) {
        TimelineAction action = pool.obtain();

        action.setTimeline(timeline);

        return action;
    }

    /**
     * 
     * @param delta
     * @return 
     */
    @Override
    public boolean act(float delta) {
        boolean done = timeline.isFinished();

        if (!done) {
            timeline.update((int) (delta * 1000));
        }

        return done;
    }

    private void setTimeline(Timeline timeline) {
        this.timeline = timeline;
    }

    /**
     * Fetch TimeLine reference.
     *
     * @return The TimeLine reference.
     */
    public Timeline getTimeline() {
        return timeline;
    }
}/** end class. */
