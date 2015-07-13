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


package algorithmi.cotton.candy.maker.data;

/**
 *
 * @author muneebahmad
 */
public class Settings {

    public static Settings sInstance;
    public static final int MODE_SOUND_ON = 0;
    public static final int MODE_SOUND_OFF = 1;
    public static final int MODE_CLICK_ON = 2;
    public static final int MODE_CLICK_OFF = 3;
    public static final int MODE_FPS_ON = 4;
    public static final int MODE_FPS_OFF = 5;
    private int settingsSoundMode;
    private int settingsClickMode;
    private int settingsFPSMode;
    
    public Settings() {
        this.settingsSoundMode = MODE_SOUND_ON;
        this.settingsClickMode = MODE_CLICK_ON;
        this.settingsFPSMode = MODE_FPS_ON;
    }
    
    /**
     * 
     * @param settingsFPSMode 
     */
    public void setFPSMode(int settingsFPSMode) {
        this.settingsFPSMode = settingsFPSMode;
    }
    
    /**
     * 
     * @return 
     */
    public int getFPSMode() {
        return this.settingsFPSMode;
    }
    
    /**
     * 
     * @param settingsClickMode 
     */
    public void setClickMode(int settingsClickMode) {
        this.settingsClickMode = settingsClickMode;
    }
    
    /**
     * 
     * @return 
     */
    public int getClickMode() {
        return this.settingsClickMode;
    }
    
    /**
     * 
     * @param settingsSoundmode 
     */
    public void setSoundMode(int settingsSoundmode) {
        this.settingsSoundMode = settingsSoundmode;
    }
    
    /**
     * 
     * @return 
     */
    public int getSoundMode() {
        return this.settingsSoundMode;
    }
    
    /**
     * 
     * @return Instance of Settings class. 
     */
    public static Settings getInstance() {
        if (sInstance == null) {
            sInstance = new Settings();
        }
        return sInstance;
    }
    
}/** end class. */
