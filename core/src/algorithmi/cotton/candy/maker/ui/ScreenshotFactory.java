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
package algorithmi.cotton.candy.maker.ui;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;
import java.nio.ByteBuffer;

/**
 *
 * @author muneebahmad
 */
public class ScreenshotFactory {

    private static int counter;

    public static void saveScreenShot() {
        try {
            FileHandle fh;
            do {
                if (Gdx.app.getType() == ApplicationType.Android) {
                    fh = new FileHandle(Gdx.files.getExternalStoragePath() + "/AglorithmiApps/CottonCandy"
                            + counter++ + ".png");
                } else {
                    fh = new FileHandle(Gdx.files.getExternalStoragePath() + "/AglorithmiApps/CottonCandy"
                            + counter++ + ".png");
                }
            } while (fh.exists());

            Pixmap pixmap = getScreenshot(0, 0, Gdx.graphics.getWidth(),
                    Gdx.graphics.getHeight(), true);
            PixmapIO.writePNG(fh, pixmap);
            pixmap.dispose();
            Gdx.app.log("Screenshot----> ", Gdx.files.getExternalStoragePath() + counter++
                    + "CottonCandy.png");
        } catch (Exception e) {
        }
    }

    private static Pixmap getScreenshot(int x, int y, int w, int h, boolean flipY) {

        Gdx.gl.glPixelStorei(GL20.GL_PACK_ALIGNMENT, 1);
        final Pixmap pixmap = new Pixmap(w, h, Pixmap.Format.RGBA8888);
        ByteBuffer pixels = pixmap.getPixels();
        Gdx.gl.glReadPixels(x, y, w, h, GL20.GL_RGBA, GL20.GL_UNSIGNED_BYTE, pixels);

        final int numBytes = w * h * 4;
        byte[] lines = new byte[numBytes];
        if (flipY) {
            final int numBytesPerLine = w * 4;
            for (int i = 0; i < h; i++) {
                pixels.position((h - i - 1) * numBytesPerLine);
                pixels.get(lines, i * numBytesPerLine, numBytesPerLine);
            }
            pixels.clear();
            pixels.put(lines);
        } else {
            pixels.clear();
            pixels.get(lines);
        }

        return pixmap;
    }
}
/**
 * [end class]
 */
