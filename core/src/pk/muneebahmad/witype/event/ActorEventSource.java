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

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 *
 * @author muneebahmad
 */
public class ActorEventSource {

    private final static int OBSERVERS_CAPACITY = 10;

    private final static int MAX_EVENTS = 100;

    private ArrayList<ActorEventObserver> observers;
    private LinkedList<ActorEvent> pool;
    private PriorityQueue<ActorEvent> events;

    /**
     * Create observer list.
     *
     */
    public ActorEventSource() {
        observers = new ArrayList<ActorEventObserver>(OBSERVERS_CAPACITY);

        pool = new LinkedList<ActorEvent>();
        events = new PriorityQueue<ActorEvent>(MAX_EVENTS);

        // Initialise the event pool.
        for (int index = 0; index < MAX_EVENTS; index++) {
            pool.add(new ActorEvent());
        }
    }

    /**
     * Update any observers.
     *
     */
    public void update() {
        if (events.size() > 0) {
            ActorEvent event = events.poll();

            if (event != null) {
                boolean handled = false;

                int size = observers.size();
                for (int index = 0; index < size; index++) {
                    handled = observers.get(index).handleEvent(event);

                    if (handled) {
                        break;
                    }
                }

                // Place data event back onto pool.
                pool.add(event);
            }
        }
    }

    /**
     * Add observer.
     *
     * @param observer The target observer.
     */
    public synchronized void addObserver(ActorEventObserver observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    /**
     * Remove observer.
     *
     * @param observer Target event observer.
     */
    public synchronized void removeObserver(ActorEventObserver observer) {
        if (observers.contains(observer)) {
            observers.remove(observer);
        }
    }

    /**
     * Enqueue event
     *
     * @param id The event id.
     * @param actor The associated actor.
     * @param priority
     */
    public void sendEvent(int id, Actor actor, int priority) {
        processEvent(id, actor, priority);
    }

    /**
     * Map event data to pooled event.
     *
     * Place pooled event on to broadcast queue.
     *
     * @param id The event id.
     * @param actor The associated actor.
     */
    private void processEvent(int id, Actor actor, int priority) {
        ActorEvent event = pool.poll();

        if (event == null) {
            event = new ActorEvent();

            Gdx.app.log("ActorEventSource", "Warning, having to create pooled event, consider making the inital pool size larger, " + pool.size());
        }

        event.populate(id, actor, System.currentTimeMillis(), priority);

        events.add(event);
    }

    /**
     * Clear structures.
     *
     */
    public void clear() {
        observers.clear();
        events.clear();
    }
}
