/**
  * copyright (c)2013-2014 Algorithmi.
  *
  * @author muneebahmad (ahmadgallian@yahoo.com) 
  *
  * The following source - code IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
  * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
  * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
  * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
  * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
  * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
  * THE SOFTWARE.
  * **/


package pk.muneebahmad.witype.tween;

import aurelienribon.tweenengine.TweenAccessor;
import com.badlogic.gdx.scenes.scene2d.Group;

/**
 *
 * @author muneebahmad
 */
public class GroupAccessor implements TweenAccessor<Group> {
    
    public static final int POSITION_XY = 1;
    public static final int SCALE_XY = 2;
    public static final int ROTATION = 3;
    public static final int ALPHA = 4;
    
    /**
     * 
     * @param target
     * @param tweenType
     * @param returnValues
     * @return 
     */
    @Override
    public int getValues(Group target, int tweenType, float[] returnValues) {
        switch(tweenType) {
            case POSITION_XY:
                returnValues[0] = target.getX();
                returnValues[1] = target.getY();
                return 2;
            case SCALE_XY:
                returnValues[0] = target.getScaleX();
                returnValues[1] = target.getScaleY();
                return 2;
            case ROTATION:
                returnValues[0] = target.getRotation();
                return 1;
            case ALPHA:
                returnValues[0] = target.getColor().a;
                return 1;
            default:
                assert false;
                return -1;
        }
    }

    /**
     * 
     * @param target
     * @param tweenType
     * @param newValues 
     */
    @Override
    public void setValues(Group target, int tweenType, float[] newValues) {
        switch(tweenType) {
            case POSITION_XY:
                target.setX(newValues[0]);
                target.setY(newValues[1]);
                break;
            case SCALE_XY:
                target.setScaleX(newValues[0]);
                target.setScaleY(newValues[1]);
                break;
            case ROTATION:
                target.setRotation(newValues[0]);
                break;
            case ALPHA:
                target.setColor(target.getColor().r, target.getColor().g, 
                        target.getColor().b, newValues[0]);
                break;
            default:
                assert false;
        }
    }

}/** end class. */
