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
public class SharedData {
    
    public static SharedData sInstance;
    
    /**
     * SUGAR
     */
    public static final int SUGAR_ONE = 0;
    public static final int SUGAR_TWO = 1;
    public static final int SUGAR_THREE = 2;
    public static final int SUGAR_FOUR = 3;
    public static final int SUGAR_FIVE = 4;
    public static final int SUGAR_SIX = 5;
    public static final int SUGAR_SEVEN = 6;
    public static final int SUGAR_EIGHT = 7;
    public int usedSugar;
    public String usedSugarName;
    
    public static final int GRID_MODE_WRAPPER = 8;
    public static final int GRID_MODE_ADDON = 10;
    public static final int GRID_MODE_PRIVACY = 9;
    public static final int GRID_MODE_NONE = 11;
    private int gridMode;
    private String usedWrapperName;
    private String usedAddonName;
    
    private float addonPositionX;
    private float addonPositionY;
    
    private float stickTop;
    
    /**
     * STICKS
     */
    public static final int STICK1 = 8;
    public static final int STICK2 = 9;
    public static final int STICK3 = 10;
    public static final int STICK4 = 11;
    public static final int STICK5 = 12;
    public static final int STICK6 = 13;
    public static final int STICK7 = 14;
    public static final int STICK8 = 15;
    public static final int STICK9 = 16;
    public int usedStick;
    public String usedStickName;
    
    /**
     * SCENES
     */
    public static final int MAIN_MENU_SCENE = 0x5e7b;
    public static final int SUGAR_SELECTION_SCENE = 0x69fb;
    public static final int POURING_SCENE = 0x1b9ff;
    public static final int STICK_SELECTION_SCENE = 0x5ab3ef;
    public static final int MACHINE_SCENE = 0x114a;
    public static final int DECORATION_SCENE = 0x8e8fa;
    public static final int END_GAME_SCENE = 0x12ffa;
    public int currentScene;
    
    /**
     * 
     */
    public SharedData() {
        this.gridMode = GRID_MODE_NONE;
    }
    
    /**
     * 
     * @param usedSugar
     * @param usedSugarName 
     */
    public void setUsedSugar(int usedSugar, String usedSugarName) {
        this.usedSugar = usedSugar;
        this.usedSugarName = usedSugarName;
    }
    
    /**
     * 
     * @return 
     */
    public String getUsedSugarName() {
        return this.usedSugarName;
    }
    
    /**
     * 
     * @return 
     */
    public int getUsedSugarId() {
        return this.usedSugar;
    }
    
    /**
     * 
     * @param usedStick
     * @param usedStickName 
     */
    public void setUsedStick(int usedStick, String usedStickName) {
        this.usedStick = usedStick;
        this.usedStickName = usedStickName;
    }
    
    /**
     * 
     * @return 
     */
    public String getUsedStickName() {
        return this.usedStickName;
    }
    
    /**
     * 
     * @return 
     */
    public int getUsedStickId() {
        return this.usedStick;
    }
    
    /**
     * 
     * @param currentScene 
     */
    public void setCurrentScene(int currentScene) {
        this.currentScene = currentScene;
    }
    
    /**
     * 
     * @return 
     */
    public int getCurrentScene() {
        return this.currentScene;
    }
    
    /**
     * 
     * @param gridMode 
     */
    public void setGridMode(int gridMode) {
        this.gridMode = gridMode;
    }
    
    /**
     * 
     * @return 
     */
    public int getGridMode() {
        return this.gridMode;
    }
    
    /**
     * 
     * @param usedWrapperName String
     */
    public void setUsedWrapperName(String usedWrapperName) {
        this.usedWrapperName = usedWrapperName;
    }
    
    /**
     * 
     * @return String
     *          usedWrapperName.
     */
    public String getUsedWrapperName() {
        return this.usedWrapperName;
    }
    
    /**
     * 
     * @param usedAddonName String
     */
    public void setUsedAddonName(String usedAddonName) {
        this.usedAddonName = usedAddonName;
    }
    
    /**
     * 
     * @return String
     *          usedAddonName {@link algorithmi.cotton.candy.maker.scenes.DecorationScene}
     */
    public String getUsedAddonName() {
        return this.usedAddonName;
    }
    
    /**
     * 
     * @param addonPositionX
     * @param addonPositionY 
     * Sets add-on dragged final position for later use.
     * see {@link algorithmi.cotton.candy.maker.scenes.DecorationScene}
     */
    public void setAddonPosition(float addonPositionX, float addonPositionY) {
        this.addonPositionX = addonPositionX;
        this.addonPositionY = addonPositionY;
    }
    
    /**
     * 
     * @return
     *          addonPosition on X-axis.
     * see {@link algorithmi.cotton.candy.maker.scenes.DecorationScene}
     */
    public float getAddonPositionX() {
        return this.addonPositionX;
    }
    
    /**
     * 
     * @return addonPosition on Y-axis.
     * see {@link algorithmi.cotton.candy.maker.scenes.DecorationScene.java}
     */
    public float getAddonPositionY() {
        return this.addonPositionY;
    }
    
    
    /**
     * 
     * @param stickTop float
     *  {@link algorithmi.cotton.candy.maker.scenes.DecorationScene}
     */
    public void setStickTopPos(float stickTop) {
        this.stickTop = stickTop;
    }
    
    /**
     * 
     * @return stickTop float
     *      {@link algorithmi.cotton.candy.maker.scenes.DecorationScene}
     */
    public float getStickTop() {
        return this.stickTop;
    }
    
    /**
     * creates new instance of {@link algorithmi.cotton.candy.maker.data.SharedData} class.
     * @return new {@link algorithmi.cotton.candy.maker.data.SharedData}
     * 
     * 
     */
    public static SharedData getInstance() {
        synchronized(SharedData.class) {
            if (sInstance == null) {
                sInstance = new SharedData();
            }
        }
        return sInstance;
    }
    
}/** end class. */
