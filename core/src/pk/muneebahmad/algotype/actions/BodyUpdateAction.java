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

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.utils.Pool;

/**
 *
 * @author muneebahmad
 */
public class BodyUpdateAction extends Action {

    private static final Pool<BodyUpdateAction> pool = 
            new Pool<BodyUpdateAction>(10, 100) {
        @Override
        protected BodyUpdateAction newObject() {
            BodyUpdateAction action = new BodyUpdateAction();

            action.setPool(this);

            return action;
        }

        /**
         * Override the 'free to ensure the object is removed from the 'world'
         * as well.
         */
        @Override
        public void free(BodyUpdateAction object) {
            super.free(object);

            object.getWorld().destroyBody(object.getBody());
        }
    };

    private World world;
    private Body body;
    private float pixelsPerMetre;
    private boolean centred;

    /**
     * Get instance from pool.
     *
     * @param world The Box2D world reference.
     * @param body The Box2D body reference.
     * @param pixelsPerMetre The conversion ratio.
     * @param centred If true the actor positioned at centre of body.
     *
     * @return The pooled instance.
     */
    public static BodyUpdateAction $(World world, Body body, 
            float pixelsPerMetre, boolean centred) {
        BodyUpdateAction action = pool.obtain();

        action.setWorld(world);
        action.setBody(body);
        action.setPixelsPerMetre(pixelsPerMetre);
        action.setCentre(centred);

        return action;
    }

    /**
     * Action.
     *
     * @return 
     */
    @Override
    public boolean act(float delta) {
        // Centre body.
        Vector2 pos = body.getPosition();

        if (centred) {
            // Adjust the actor to centre
            getActor().setX((pos.x * pixelsPerMetre) - getActor().getWidth() / 2);
            getActor().setY((pos.y * pixelsPerMetre) - getActor().getHeight() / 2);
        } else {
            getActor().setX(pos.x * pixelsPerMetre);
            getActor().setY(pos.y * pixelsPerMetre);
        }

        float angleDeg = body.getAngle() * MathUtils.radiansToDegrees;

        getActor().setRotation(angleDeg);

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

    /**
     * Return ppm conversion factor.
     *
     * @return
     */
    public float getPixelsPerMetre() {
        return pixelsPerMetre;
    }

    /**
     * Set PPM conversion factor.
     *
     * @param pixelsPerMetre
     */
    public void setPixelsPerMetre(float pixelsPerMetre) {
        this.pixelsPerMetre = pixelsPerMetre;
    }

    public boolean isCentre() {
        return centred;
    }

    public void setCentre(boolean centre) {
        this.centred = centre;
    }
}/** end class. */
