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


package pk.muneebahmad.witype.nodes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

/**
 *
 * @author muneebahmad
 */
public class Layer extends Group implements Node {
    
    /**
     * creates a layer
     * @return 
     */
    public static Layer make() {
        return new Layer();
    }
    
    protected Layer() {
        Gdx.app.log("Layer-->", "CREATED A NEW LAYER");
    }

    @Override
    public void enter() {
        super.act(Gdx.graphics.getDeltaTime());
    }

    @Override
    public void exit() {
        super.clear();
    }
    
    /**
     * 
     * @param actor 
     */
    public void addChild(Actor actor) {
        super.addActor(actor);
    }
    
    /**
     * 
     * @param index
     * @param actor 
     */
    public void addChildAt(int index, Actor actor) {
        super.addActorAt(index, actor);
    }
    
    /**
     * 
     * @param actorBefore
     * @param actor 
     */
    public void addChildBefore(Actor actorBefore, Actor actor) {
        super.addActorBefore(actorBefore, actor);
    }
    
    /**
     * 
     * @param actorAfter
     * @param actor 
     */
    public void addChildAfter(Actor actorAfter, Actor actor) {
        super.addActorAfter(actorAfter, actor);
    }
    
    /**
     * 
     * @param actor 
     */
    public void removeChild(Actor actor) {
        super.removeActor(actor);
    }
    
    /**
     * Removes all children from the layer.
     */
    public void removeAllChildren() {
        super.clearChildren();
    }
    
    /**
     * 
     * @param batch
     * @param parentAlpha 
     */
    public void drawBatch(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }
    
    /**
     * 
     * @param batch
     * @param parentAlpha 
     */
    public void drawChildrens(Batch batch, float parentAlpha) {
        super.drawChildren(batch, parentAlpha);
    }
    
    /**
     * 
     * @param width
     * @param height 
     */
    public void setActorSize(float width, float height) {
        super.setSize(width, height);
    }

}/** end class. */
