/**
  * copyright ©2013-2014 ®Algorithmi™.
  *
  * @author ¶muneebahmad¶ (ahmadgallian@yahoo.com) 
  * NetBeans IDE http://www.netbeans.org
  *
  * The following source - code IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
  * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
  * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
  * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
  * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
  * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
  * THE SOFTWARE.
  * **/

package algorithmi.cotton.candy.maker.layers;

import algorithmi.cotton.candy.maker.data.ResourcesManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import pk.muneebahmad.witype.nodes.Layer;

/**
 *
 * @author muneebahmad
 */
public class EndSceneLayer extends Layer {
    
    private static final float WIDTH = 480.0f;
    private static final float HEIGHT = 800.0f;
    
    private FrameBuffer fbo;
    
    public String tex;
    
    private Texture candyTex;
    private Sprite candySprite;
    
    private Rectangle candyRect;
    
    private boolean isEating = false;
    
    private final Image mask = new Image(ResourcesManager.eatingMask);
    
    private float x;
    private float y;
    
    public EndSceneLayer() {
        this.setWidth(WIDTH);
        this.setHeight(HEIGHT);
        
        fbo = new FrameBuffer(Pixmap.Format.RGBA8888, 
                ResourcesManager.eatingMask.getWidth(), 
                ResourcesManager.eatingMask.getHeight(), false);
        
        loadUsedCandy();
    }
    
    /**
     * GET CANDY TEX NAME.
     * @return 
     */
    private String getCandySourceName() {
        tex = "candy2";
        /**
        if (SharedData.getInstance().usedSugarName.equals("bottle1")) {
            tex = "candy1";
        } else if (SharedData.getInstance().usedSugarName.equals("bottle2")) {
            tex = "candy2";
        } else if (SharedData.getInstance().usedSugarName.equals("bottle3")) {
            tex = "candy3";
        } else if (SharedData.getInstance().usedSugarName.equals("bottle4")) {
            tex = "candy4";
        } else if (SharedData.getInstance().usedSugarName.equals("bottle5")) {
            tex = "candy5";
        } else if (SharedData.getInstance().usedSugarName.equals("bottle6")) {
            tex = "candy6";
        } else if (SharedData.getInstance().usedSugarName.equals("bottle7")) {
            tex = "candy7";
        } else if (SharedData.getInstance().usedSugarName.equals("bottle8")) {
            tex = "candy8";
        }
        * **/
        return tex;
    }
    
    private void loadUsedCandy() {
        this.candyTex = new Texture("data/" + getCandySourceName() + ".png");
        this.candySprite = new Sprite(candyTex);
        this.candyRect = candySprite.getBoundingRectangle();
        this.candyRect.setPosition(getWidth() / 2.0f - candyRect.getWidth() / 2.0f,
                getHeight() / 2.0f - candyRect.getHeight() / 2.0f); 
    }
    
    private void updateWorld(float delta) {
        if (Gdx.input.justTouched()) {
            if ((Gdx.input.getX() >= candyRect.x) && (Gdx.input.getX() <= (candyRect.x + candyRect.getWidth())) &&
                    (Gdx.input.getY() >= candyRect.y) && (Gdx.input.getY() <= (candyRect.y + candyRect.getHeight()))) {
                Gdx.app.log("CLICKED <=======>", " Candy touched " + " x-> " + Gdx.input.getX() +
                       " y-> " + Gdx.input.getY());
                this.isEating = true;
                this.x = (float) Gdx.input.getX();
                this.y = (float) Gdx.input.getY();
                
                Gdx.app.log("Y POS OF MASK --> ", " [" + y + "]");
            }
            //this.isEating = false;
        }
    }

    @Override
    public void act(float delta) {
        updateWorld(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        //Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //Gdx.gl20.glBlendFunc(GL20.GL_NONE, GL20.GL_NONE);
        
        batch.draw(candySprite, getWidth() / 2.0f - candyTex.getWidth() / 2.0f, 
                    getHeight() / 2.0f - candyTex.getHeight() / 2.0f);
        
        if (isEating) {
           // Gdx.gl20.glBlendFunc(GL20.GL_ONE, GL20.GL_ONE_MINUS_DST_ALPHA);
            
            Gdx.gl20.glEnable(GL20.GL_SCISSOR_TEST);
            batch.draw(ResourcesManager.endBg, 0, 0);
            Gdx.gl20.glScissor((int)x, (int)y, ResourcesManager.eatingMask.getWidth(), 
                    ResourcesManager.eatingMask.getHeight());
            
            
            Gdx.gl20.glDisable(GL20.GL_SCISSOR_TEST);
        }
        
    }
    
}/** end class. */
