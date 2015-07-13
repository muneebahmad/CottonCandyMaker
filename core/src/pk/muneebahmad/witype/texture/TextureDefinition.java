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
package pk.muneebahmad.witype.texture;

/**
 *
 * @author muneebahmad
 */
public class TextureDefinition {

    private String path;
    private String name;
    private int rows;
    private int cols;
    private boolean animated;

    /**
     * Construct definition.
     *
     * @param name The texture name.
     * @param path The file path to texture.
     */
    public TextureDefinition(String name, String path) {
        this.name = name;
        this.path = path;

        rows = 1;
        cols = 1;
        animated = false;
    }

    /**
     * Construct definition for animation sheet texture.
     *
     * @param name The texture name.
     * @param path The file path to texture.
     * @param rows The number of rows in texture sheet.
     * @param cols The number of columns in texture sheet.
     */
    public TextureDefinition(String name, String path, int rows, int cols) {
        this.name = name;
        this.path = path;
        this.rows = rows;
        this.cols = cols;
        this.animated = true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }

    public boolean isAnimated() {
        return animated;
    }

    public void setAnimated(boolean animated) {
        this.animated = animated;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}/** end class. */
