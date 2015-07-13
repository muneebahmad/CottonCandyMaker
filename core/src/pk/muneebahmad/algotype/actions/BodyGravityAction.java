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

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.utils.Pool;

/**
 *
 * @author muneebahmad
 */
public class BodyGravityAction extends Action {

    private World world;
    private Body body;
    private float gravityOffset;
    private boolean randomise;

    private static final Pool<BodyGravityAction> pool = new Pool<BodyGravityAction>(10, 100) {
        @Override
        protected BodyGravityAction newObject() {
            BodyGravityAction action = new BodyGravityAction();

            action.setPool(this);

            return action;
        }
    };

    /**
     * Get instance from pool.
     *
     * @param world The Box2D world reference.
     * @param body The Box2D body reference.
     * @param gravityOffset The gravity increment from the world setting to
     * apply.
     * @param randomise Whether to randomise across the offset value to get more
     * realistic behaviour.
     * @return
     */
    public static BodyGravityAction $(World world, Body body, float gravityOffset, boolean randomise) {
        BodyGravityAction action = pool.obtain();

        action.setWorld(world);
        action.setBody(body);
        action.setGravityOffset(gravityOffset);
        action.setRandomise(randomise);

        return action;
    }

    /**
     * Action.
     *
     */
    @Override
    public boolean act(float delta) {
        float gravityToApply = world.getGravity().y;

        if (randomise) {
            gravityToApply = gravityToApply + ((float) Math.random() * gravityOffset);
        } else {
            gravityToApply = gravityToApply + gravityOffset;
        }

        body.applyForceToCenter(0, gravityToApply, true);

        return false;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    /**
     * Set Body reference.
     *
     * @param body
     */
    private void setBody(Body body) {
        this.body = body;
    }

    /**
     * Fetch Body reference.
     *
     * @return The Body reference.
     */
    public Body getBody() {
        return body;
    }

    public float getGravityOffset() {
        return gravityOffset;
    }

    public void setGravityOffset(float gravityOffset) {
        this.gravityOffset = gravityOffset;
    }

    public boolean isRandomise() {
        return randomise;
    }

    public void setRandomise(boolean randomise) {
        this.randomise = randomise;
    }

}/** end class. */
