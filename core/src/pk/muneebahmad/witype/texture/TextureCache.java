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


package pk.muneebahmad.witype.texture;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Disposable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author muneebahmad
 */
public class TextureCache implements Disposable {
    private TextureAtlas textureAtlas = null;

    private Map<String, TextureDefinition> definitions;

    /**
     * Construct cache.
     *
     */
    public TextureCache() {
        definitions = new HashMap<String, TextureDefinition>();
    }

    /**
     * Pack pack file textures (textures file resides in same directory).
     *
     * @param packFile
     */
    public void load(String packFile) {
        if (textureAtlas == null) {
            textureAtlas = new TextureAtlas(Gdx.files.internal(packFile));
        }
    }

    /**
     * Load predefined textures.
     *
     * This requires texture definitions to be added to the
     * {@link AppActorTextures} structure.
     * @param textureDefinitions
     */
    public void load(List<TextureDefinition> textureDefinitions) {
        if (textureAtlas == null) {
            textureAtlas = new TextureAtlas();
        } else {
            dispose();

            textureAtlas = new TextureAtlas();
        }

        for (TextureDefinition definition : textureDefinitions) {
            Texture texture = new Texture(Gdx.files.internal(definition.getPath()));
            TextureRegion textureRegion = new TextureRegion(texture);

            textureAtlas.addRegion(definition.getName(), textureRegion);
            definitions.put(definition.getName(), definition);
        }
    }

    /**
     * Fetch texture region from cache.
     *
     * @param definition
     * @param name The texture name.
     *
     * @return The texture region.
     */
    public TextureRegion getTexture(TextureDefinition definition) {
        return textureAtlas.findRegion(definition.getName());
    }

    /**
     * Fetch texture region from cache.
     *
     * @param name The texture name.
     *
     * @return The texture region.
     */
    public TextureDefinition getDefinition(String name) {
        return definitions.get(name);
    }

    /**
     * Dispose of cache data.
     *
     */
    @Override
    public void dispose() {
        definitions.clear();
        textureAtlas.dispose();
    }
}/** end class. */
