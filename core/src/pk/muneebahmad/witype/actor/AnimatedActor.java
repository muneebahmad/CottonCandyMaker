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


package pk.muneebahmad.witype.actor;

import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 *
 * @author muneebahmad
 */
public class AnimatedActor extends Image {
    
    private final AnimationDrawable drawable;
    
    public AnimatedActor(AnimationDrawable drawable) {
        super(drawable);
        this.drawable = drawable;
    }
    
    @Override
    public void act(float delta) {
        drawable.act(delta);
        super.act(delta);
    }
   
}/** end class. */
