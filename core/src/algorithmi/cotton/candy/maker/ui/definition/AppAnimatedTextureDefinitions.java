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


package algorithmi.cotton.candy.maker.ui.definition;

import java.util.LinkedList;
import java.util.List;
import pk.muneebahmad.witype.texture.TextureDefinition;
import pk.muneebahmad.witype.texture.TextureDefinitions;

/**
 *
 * @author muneebahmad
 */
public class AppAnimatedTextureDefinitions implements TextureDefinitions {

    public static final String TEXTURE_PATH = "ui";
    
    public static final String TEXTURE_MACHINE = "machine_texture.png";
    
    public static final List<TextureDefinition> TEXTURES = 
            new LinkedList<TextureDefinition>() {
       {
        add(new TextureDefinition(TEXTURE_MACHINE, TEXTURE_PATH + "/" + 
                TEXTURE_MACHINE, 2, 4));
        }
    };
    
    @Override
    public List<TextureDefinition> getDefinitions() {
        return TEXTURES;
    }

}/** end class. */
