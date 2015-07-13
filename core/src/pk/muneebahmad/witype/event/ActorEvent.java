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
package pk.muneebahmad.witype.event;

import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 *
 * @author muneebahmad
 */
public class ActorEvent implements Comparable<ActorEvent> {

    public static final int HIGH_PRIORITY = 1;
    public static final int DEFAULT_PRIORITY = -1;

    private int id;
    private long time;
    private long priority;
    private Actor actor;

    /**
     * Populate event with default priority.
     *
     * @param id
     * @param node
     * @param time
     */
    public void populate(int id, Actor node, long time) {
        // When no priority is specified we adopt the time-stamp.
        populate(id, node, time, time);
    }

    /**
     * Populate data event from system event.
     *
     * @param id Event id.
     * @param node Associated event actor.
     * @param time Event time.
     */
    public void populate(int id, Actor node, long time, long priority) {
        // Copy fields.
        this.id = id;

        this.actor = node;

        this.time = time;

        if (priority == DEFAULT_PRIORITY) {
            this.priority = time;
        } else {
            this.priority = priority;
        }

    }

    /**
     * Get event id.
     *
     * @return The id.
     */
    public int getId() {
        return id;
    }

    /**
     * Get actor.
     *
     * @return The Actor.
     */
    public Actor getActor() {
        return actor;
    }

    /**
     * Get timestamp.
     *
     * @return
     */
    public long getTime() {
        return time;
    }

    /**
     * Return priority value.
     *
     * @return The value.
     */
    public long getPriority() {
        return priority;
    }

    /**
     * Set priority.
     *
     * @param priority
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }

    /**
     * Implements comparison.
     *
     * The lower the priority value the higher the
     */
    @Override
    public int compareTo(ActorEvent o) {
        if (priority < o.getPriority()) {
            return -1;
        } else if (priority > o.getPriority()) {
            return 1;
        }

        return 0;
    }
}
