/**
  * copyright ©2013-2014 Algorithmi®.
  *
  * @author muneebahmad ß(ahmadgallian@yahoo.com) ß
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
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;

/**
 *
 * @author muneebahmad
 */
public class Scene extends Stage implements Node, Director.IDirectorLifecycleListener {

    public static final int DEFAULT_LAYER_CAPACITY = 10;
    private Array<Node> nodes;
    
    private final Director director = Director.getInstance();
    boolean visible = false;
    
    public Scene() {
        this(Director.getInstance().getBaseWidth(), Director.getInstance().getBaseHeight());
        this.nodes = new Array<Node>(DEFAULT_LAYER_CAPACITY);
        Gdx.input.setInputProcessor(this);
        Gdx.app.log("WINDOW WIDTH--->", director.getWindowWidth() + ".");
        Gdx.app.log("WINDOW HEIGHT--->", director.getWindowHeight() + ".");
    }
    
    public Scene(int width, int height) {
        super(new ScalingViewport(Scaling.stretch, width, height));
        visible = true;
    }
    
    /**
     * Adds child to the scene.
     * @param actor 
     */
    public void addChild(Actor actor) {
        super.addActor(actor);
    }
    
    @Override
    public void enter() {
        if (director.getCurrentScene() != null) {
         director.initCurrentScene();
        }
         Gdx.app.log("INFORMATION-->SCENE CLASS--> DIRECTOR INSTANTIATED", "");
    }

    @Override
    public void exit() {
        this.myDispose();
        director.removeScene(director.getCurrentScene());
    }
    
    /**
     * 
     * @return 
     */
    public boolean isVisible() {
        return visible;
    }

    @Override
    public void myRender(float delta) {
        
    }

    @Override
    public void myResize(int width, int height) {
        //super.setViewport(new ScalingViewport(Scaling.fit, width, height));
    }

    @Override
    public void myShow() {
       // director.getCurrentScene().myShow();
    }

    @Override
    public void myHide() {
    }

    @Override
    public void myPause() {
    }

    @Override
    public void myResume() {
    }

    @Override
    public void myDispose() {
        super.dispose();
    }
    
    @Override
    public void draw() {
        super.draw();
    }
}/** end class. */
