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

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 *
 * @author muneebahmad
 */
public class ResourcesManager {
    public static Skin skin;
    public static TextureAtlas uiAtlas;
    public static TextureAtlas textureAtlas;
    public static TextureAtlas machineAtlas;
    public static TextureAtlas addonAtlas;
    public static TextureAtlas sunAtlas;
    public static Texture menuBg;
    public static Texture menuBg2;
    public static Texture logo;
    public static Texture firstBg;
    public static Texture almirahDoor;
    public static Texture selectSugarLogo;
    public static Texture pouringBg;
    public static Texture pouringBowl;
    public static Texture bowlStand;
    public static Texture arrow;
    public static Texture stickBg;
    public static Texture stickSelectionLogo;
    public static Texture machineBg;
    public static Image machineImage;
    public static Texture machineFilter;
    public static Texture decorationBg1;
    public static Texture decorationBg2;
    public static Texture cloud;
    public static Texture thunder;
    public static Texture grid;
    public static Music bgSound;
    public static Sound btnClicked;
    public static Music machineSound;
    public static Sound eatSound;
    public static Texture privacy;
    public static Sound waoSound;
    public static Texture endBg;
    public static Texture eatingMask;
    
    public ResourcesManager() {
        
    }
    
    public static void load() {
        Gdx.app.log("Cotton Candy Maker --->", "Loading... Resources...");
        
        //ATLAS
        uiAtlas = new TextureAtlas("ui/uipack.pack");
        textureAtlas = new TextureAtlas("ui/texture.pack");
        addonAtlas = new TextureAtlas("ui/addon-texture.pack");
        machineAtlas = new TextureAtlas("ui/machine_texture.pack");
        sunAtlas = new TextureAtlas("ui/sun-texture.pack");
        
        //SKIN
        skin = new Skin(Gdx.files.internal("ui/menuSkin.json"), uiAtlas);
        
        //TEXTURES
        menuBg = new Texture("data/menu_bg_01.png");
        menuBg2 = new Texture("data/menu_bg_02.png");
        logo = new Texture("data/logo.png");
        firstBg = new Texture("data/almirah.png");
        almirahDoor = new Texture("data/almirah_door.png");
        selectSugarLogo = new Texture("data/select_sugar.png");
        pouringBg = new Texture("data/pouring_bg.png");
        pouringBowl = new Texture("data/container.png");
        bowlStand = new Texture("data/container_stand.png");
        arrow = new Texture("data/aarow.png");
        stickBg = new Texture("data/stick_scene_bg.png");
        stickSelectionLogo = new Texture("data/stick_selection_logo.png");
        machineBg = new Texture("data/machine_scene_bg.png");
        machineFilter = new Texture("data/machine_filter.png");
        decorationBg1 = new Texture("data/decoration_bg1.png");
        decorationBg2 = new Texture("data/decoration_bg2.png");
        cloud = new Texture("data/cloud.png");
        thunder = new Texture("data/thunder.png");
        grid = new Texture("data/grid_bg.png");
        endBg = new Texture("data/end_bg.png");
        privacy = new Texture("data/privacy_policy.png");
        eatingMask = new Texture("data/eating_mask.png");
        
        //IMAGES
        machineImage = new Image(new Texture("ui/machine_texture.png"));
        
        //SOUNDS
        bgSound = Gdx.audio.newMusic(Gdx.files.internal("sounds/algo_rock_03.mp3"));
        machineSound = Gdx.audio.newMusic(Gdx.files.internal("sounds/candy_mixing_scene.wav"));
        btnClicked = Gdx.audio.newSound(Gdx.files.internal("sounds/button_49.wav"));
        eatSound = Gdx.audio.newSound(Gdx.files.internal("sounds/eating_sound_effect.wav"));
        waoSound = Gdx.audio.newSound(Gdx.files.internal("sounds/eating_scene_sound.wav"));
    }
}/** end class. */
