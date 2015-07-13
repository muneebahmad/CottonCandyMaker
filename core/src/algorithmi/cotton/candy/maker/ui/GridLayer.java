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


package algorithmi.cotton.candy.maker.ui;

import algorithmi.cotton.candy.maker.data.ResourcesManager;
import algorithmi.cotton.candy.maker.data.SharedData;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import pk.muneebahmad.witype.nodes.Layer;
import pk.muneebahmad.witype.nodes.Scene;

/**
 *
 * @author muneebahmad
 */
public class GridLayer {

    private Layer wGridLayer;
    private Button closeButt;
    private Image bg;
    private Skin skin;
    private Label titleLabel;
    private Image divider;
    private ScrollPane scrollPane;
    private final SharedData sharedData = SharedData.getInstance();
    private GridlayerInterface gridlayerInterface;
    private final Skin addonSkin = new Skin(ResourcesManager.addonAtlas);
    
    public GridLayer() { }
    
    public GridLayer(Scene scene, String title, GridlayerInterface gridLayerInterface) {
        this.gridlayerInterface = gridLayerInterface;
        makeWrapperGridLayer(scene, title);
        populateGrid();
    }
    
    private void makeWrapperGridLayer(final Scene scene, String title) {
        this.wGridLayer = Layer.make();
        this.wGridLayer.setWidth(scene.getWidth());
        this.wGridLayer.setHeight(scene.getHeight());
        this.wGridLayer.setPosition(0.0f, scene.getHeight() + 1000.0f);
        //SKIN
        this.skin = new Skin(ResourcesManager.uiAtlas);
        
        //BG
        this.bg = new Image(ResourcesManager.grid);
        this.bg.setWidth(430.0f);
        this.bg.setHeight(520.0f);
        this.bg.setPosition(wGridLayer.getWidth() / 2.0f - bg.getWidth() / 2.0f, 
                wGridLayer.getHeight() / 2.0f - bg.getHeight() / 2.0f);
        this.wGridLayer.addChild(bg);
        
        //TITLE LABLE
        this.titleLabel = new Label(title, ResourcesManager.skin);
        this.titleLabel.setPosition(this.wGridLayer.getWidth() / 2.0f - titleLabel.getWidth() / 2.0f, 
                this.bg.getY() + this.bg.getHeight() - this.titleLabel.getHeight() * 2);
        this.wGridLayer.addChild(titleLabel);
        
        //DIVIDER
        this.divider = new Image(skin.getDrawable("divider"));
        this.divider.setWidth(this.bg.getWidth());
        this.divider.setHeight(4.0f);
        this.divider.setPosition(this.bg.getX(), 
                this.titleLabel.getY() - this.titleLabel.getHeight() + 10.0f);
        //this.wGridLayer.addChild(this.divider);
        
        //CLOSE BUTTON
        this.closeButt = new Button(ResourcesManager.skin, "close");
        this.closeButt.setPosition(bg.getX() + bg.getWidth() - 100.0f, 
                bg.getY() + bg.getHeight() - 50.0f);
        this.wGridLayer.addChild(closeButt);
        this.closeButt.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                SharedData.getInstance().setGridMode(SharedData.GRID_MODE_NONE);
                closeGridAction();
            }
            
        });
        
        
        scene.addChild(this.wGridLayer);
        this.wGridLayer.addAction(Actions.moveTo(0.0f, 0.0f, 3.0f, Interpolation.swingOut));
    } 
    
    private void closeGridAction() {
        SharedData.getInstance().setGridMode(SharedData.GRID_MODE_NONE);
        wGridLayer.addAction(Actions.sequence(Actions.moveTo(0.0f, 1480.0f, 
                        3.0f, Interpolation.swingIn), Actions.removeActor()));
    }
    
    private void populateGrid() {
        
        if (SharedData.getInstance().getGridMode() == SharedData.GRID_MODE_WRAPPER) {
            Skin texSkin = new Skin(ResourcesManager.textureAtlas);
            Layer wrapperLayer = Layer.make();
            wrapperLayer.setWidth(410.0f);
            wrapperLayer.setHeight(1000.0f);
            wrapperLayer.setPosition(this.wGridLayer.getWidth() / 2.0f - wrapperLayer.getWidth() / 2.0f, 
                    this.bg.getY() + 20.0f);
        
            //1
            Image wrap1 = new Image(texSkin.getDrawable("wrapper1"));
            wrap1.setScale(0.5f);
            wrap1.setPosition(wrapperLayer.getX() + 10.0f, wrapperLayer.getHeight() - 130.0f);
            wrapperLayer.addChild(wrap1);
            wrap1.addListener(new ClickListener() {

                @Override
                public void clicked(InputEvent event, float x, float y) {
                    SharedData.getInstance().setUsedWrapperName("wrapper1");
                    gridlayerInterface.loadWrapper();
                    closeGridAction();
                }
                
            });
            
            //2
            Image wrap2 = new Image(texSkin.getDrawable("wrapper2"));
            wrap2.setScale(0.5f);
            wrap2.setPosition(wrapperLayer.getWidth() - wrap2.getWidth() - 30.0f, 
                    wrapperLayer.getHeight() - 130.0f);
            wrapperLayer.addChild(wrap2);
            wrap2.addListener(new ClickListener() {

                @Override
                public void clicked(InputEvent event, float x, float y) {
                    SharedData.getInstance().setUsedWrapperName("wrapper2");
                    gridlayerInterface.loadWrapper();
                    closeGridAction();
                }
                
            });
            
            //3
            Image wrap3 = new Image(texSkin.getDrawable("wrapper3"));
            wrap3.setScale(0.5f);
            wrap3.setPosition(wrap1.getX(), 
                    wrap1.getY() - 180.0f);
            wrapperLayer.addChild(wrap3);
            wrap3.addListener(new ClickListener() {

                @Override
                public void clicked(InputEvent event, float x, float y) {
                    SharedData.getInstance().setUsedWrapperName("wrapper3");
                    gridlayerInterface.loadWrapper();
                    closeGridAction();
                }
                
            });
            
            //4
            Image wrap4 = new Image(texSkin.getDrawable("wrapper4"));
            wrap4.setScale(0.5f);
            wrap4.setPosition(wrap2.getX(), 
                    wrap1.getY() - 180.0f);
            wrapperLayer.addChild(wrap4);
            wrap4.addListener(new ClickListener() {

                @Override
                public void clicked(InputEvent event, float x, float y) {
                    SharedData.getInstance().setUsedWrapperName("wrapper4");
                    gridlayerInterface.loadWrapper();
                    closeGridAction();
                }
                
            });
            
            //5
            Image wrap5 = new Image(texSkin.getDrawable("wrapper5"));
            wrap5.setScale(0.5f);
            wrap5.setPosition(wrap1.getX(), wrap3.getY() - 180.0f);
            wrapperLayer.addChild(wrap5);
            wrap5.addListener(new ClickListener() {

                @Override
                public void clicked(InputEvent event, float x, float y) {
                    SharedData.getInstance().setUsedWrapperName("wrapper5");
                    gridlayerInterface.loadWrapper();
                    closeGridAction();
                }
                
            });
            
            //6
            Image wrap6 = new Image(texSkin.getDrawable("wrapper6"));
            wrap6.setScale(0.5f);
            wrap6.setPosition(wrap2.getX(), wrap5.getY());
            wrapperLayer.addChild(wrap6);
            wrap6.addListener(new ClickListener() {

                @Override
                public void clicked(InputEvent event, float x, float y) {
                    SharedData.getInstance().setUsedWrapperName("wrapper6");
                    gridlayerInterface.loadWrapper();
                    closeGridAction();
                }
                
            });
            
            //7
            Image wrap7 = new Image(texSkin.getDrawable("wrapper7"));
            wrap7.setScale(0.5f);
            wrap7.setPosition(wrap5.getX(), wrap5.getY() - 180.0f);
            wrapperLayer.addChild(wrap7);
            wrap7.addListener(new ClickListener() {

                @Override
                public void clicked(InputEvent event, float x, float y) {
                    SharedData.getInstance().setUsedWrapperName("wrapper7");
                    gridlayerInterface.loadWrapper();
                    closeGridAction();
                }
                
            });
            
            //8
            Image wrap8 = new Image(texSkin.getDrawable("wrapper8"));
            wrap8.setScale(0.5f);
            wrap8.setPosition(wrap6.getX(), wrap7.getY());
            wrapperLayer.addChild(wrap8);
            wrap8.addListener(new ClickListener() {

                @Override
                public void clicked(InputEvent event, float x, float y) {
                    SharedData.getInstance().setUsedWrapperName("wrapper8");
                    gridlayerInterface.loadWrapper();
                    closeGridAction();
                }
                
            });
            
            //9
            Image wrap9 = new Image(texSkin.getDrawable("wrapper9"));
            wrap9.setScale(0.5f);
            wrap9.setPosition(wrapperLayer.getWidth() / 2.0f - wrap9.getWidth() / 2.0f, wrap8.getY() - 180.0f);
            wrapperLayer.addChild(wrap9);
            wrap9.addListener(new ClickListener() {

                @Override
                public void clicked(InputEvent event, float x, float y) {
                    SharedData.getInstance().setUsedWrapperName("wrapper9");
                    gridlayerInterface.loadWrapper();
                    closeGridAction();
                }
                
            });
            
            scrollPane = new ScrollPane(wrapperLayer, ResourcesManager.skin);
            scrollPane.setWidth(350.0f);
            scrollPane.setHeight(350.0f);
            wGridLayer.addChild(scrollPane);
            scrollPane.setPosition(wGridLayer.getWidth() / 2.0f - scrollPane.getWidth() / 2.0f, bg.getY() + 100.0f);
            scrollPane.setScrollingDisabled(true, false);
            
        } else if (SharedData.getInstance().getGridMode() == SharedData.GRID_MODE_ADDON) {
            
            Layer wrapperLayer = Layer.make();
            wrapperLayer.setWidth(410.0f);
            wrapperLayer.setHeight(1000.0f);
            wrapperLayer.setPosition(this.wGridLayer.getWidth() / 2.0f - wrapperLayer.getWidth() / 2.0f, 
                    this.bg.getY() + 20.0f);
        
            //1
            Image toy1 = new Image(addonSkin.getDrawable("toy1"));
            toy1.setScale(0.5f);
            toy1.setPosition(wrapperLayer.getX() + 10.0f, wrapperLayer.getHeight() - 130.0f);
            wrapperLayer.addChild(toy1);
            toy1.addListener(new ClickListener() {

                @Override
                public void clicked(InputEvent event, float x, float y) {
                    SharedData.getInstance().setUsedAddonName("toy1");
                    gridlayerInterface.loadAddon();
                    closeGridAction();
                }
                
            });
            
            //2
            Image toy2 = new Image(addonSkin.getDrawable("toy2"));
            toy2.setScale(0.5f);
            toy2.setPosition(wrapperLayer.getWidth() - toy2.getWidth() - 30.0f, 
                    wrapperLayer.getHeight() - 130.0f);
            wrapperLayer.addChild(toy2);
            toy2.addListener(new ClickListener() {

                @Override
                public void clicked(InputEvent event, float x, float y) {
                    SharedData.getInstance().setUsedAddonName("toy2");
                    gridlayerInterface.loadAddon();
                    closeGridAction();
                }
                
            });
            
            //3
            Image toy3 = new Image(addonSkin.getDrawable("toy3"));
            toy3.setScale(0.5f);
            toy3.setPosition(toy1.getX(), 
                    toy1.getY() - 180.0f);
            wrapperLayer.addChild(toy3);
            toy3.addListener(new ClickListener() {

                @Override
                public void clicked(InputEvent event, float x, float y) {
                    SharedData.getInstance().setUsedAddonName("toy3");
                    gridlayerInterface.loadAddon();
                    closeGridAction();
                }
                
            });
            
            //4
            Image toy4 = new Image(addonSkin.getDrawable("toy4"));
            toy4.setScale(0.5f);
            toy4.setPosition(toy2.getX(), 
                    toy1.getY() - 180.0f);
            wrapperLayer.addChild(toy4);
            toy4.addListener(new ClickListener() {

                @Override
                public void clicked(InputEvent event, float x, float y) {
                    SharedData.getInstance().setUsedAddonName("toy4");
                    gridlayerInterface.loadAddon();
                    closeGridAction();
                }
                
            });
            
            //5
            Image toy5 = new Image(addonSkin.getDrawable("toy5"));
            toy5.setScale(0.5f);
            toy5.setPosition(toy1.getX(), toy3.getY() - 180.0f);
            wrapperLayer.addChild(toy5);
            toy5.addListener(new ClickListener() {

                @Override
                public void clicked(InputEvent event, float x, float y) {
                    SharedData.getInstance().setUsedAddonName("toy5");
                    gridlayerInterface.loadAddon();
                    closeGridAction();
                }
                
            });
            
            //6
            Image toy6 = new Image(addonSkin.getDrawable("toy6"));
            toy6.setScale(0.5f);
            toy6.setPosition(toy2.getX(), toy5.getY());
            wrapperLayer.addChild(toy6);
            toy6.addListener(new ClickListener() {

                @Override
                public void clicked(InputEvent event, float x, float y) {
                    SharedData.getInstance().setUsedAddonName("toy6");
                    gridlayerInterface.loadAddon();
                    closeGridAction();
                }
                
            });
            
            //7
            Image toy7 = new Image(addonSkin.getDrawable("toy7"));
            toy7.setScale(0.5f);
            toy7.setPosition(toy5.getX(), toy5.getY() - 180.0f);
            wrapperLayer.addChild(toy7);
            toy7.addListener(new ClickListener() {

                @Override
                public void clicked(InputEvent event, float x, float y) {
                    SharedData.getInstance().setUsedAddonName("toy7");
                    gridlayerInterface.loadAddon();
                    closeGridAction();
                }
                
            });
            
            //8
            Image toy8 = new Image(addonSkin.getDrawable("toy8"));
            toy8.setScale(0.5f);
            toy8.setPosition(toy6.getX(), toy7.getY());
            wrapperLayer.addChild(toy8);
            toy8.addListener(new ClickListener() {

                @Override
                public void clicked(InputEvent event, float x, float y) {
                    SharedData.getInstance().setUsedAddonName("toy8");
                    gridlayerInterface.loadAddon();
                    closeGridAction();
                }
                
            });
            
            //9
            Image toy9 = new Image(addonSkin.getDrawable("toy9"));
            toy9.setScale(0.5f);
            toy9.setPosition(wrapperLayer.getWidth() / 2.0f - toy9.getWidth() / 2.0f, toy8.getY() - 180.0f);
            wrapperLayer.addChild(toy9);
            toy9.addListener(new ClickListener() {

                @Override
                public void clicked(InputEvent event, float x, float y) {
                    SharedData.getInstance().setUsedAddonName("toy9");
                    gridlayerInterface.loadAddon();
                    closeGridAction();
                }
                
            });
            
            scrollPane = new ScrollPane(wrapperLayer, ResourcesManager.skin);
            scrollPane.setWidth(350.0f);
            scrollPane.setHeight(350.0f);
            wGridLayer.addChild(scrollPane);
            scrollPane.setPosition(wGridLayer.getWidth() / 2.0f - scrollPane.getWidth() / 2.0f, bg.getY() + 100.0f);
            scrollPane.setScrollingDisabled(true, false);
        } else if (SharedData.getInstance().getGridMode() == SharedData.GRID_MODE_PRIVACY) {
            Image privacy = new Image(ResourcesManager.privacy);
            
            scrollPane = new ScrollPane(privacy, ResourcesManager.skin);
            scrollPane.setWidth(350.0f);
            scrollPane.setHeight(350.0f);
            wGridLayer.addChild(scrollPane);
            scrollPane.setPosition(wGridLayer.getWidth() / 2.0f - scrollPane.getWidth() / 2.0f, bg.getY() + 100.0f);
            scrollPane.setScrollingDisabled(true, false);
            
        }
        
    }
    
    /**
     * 
     * @param scene {@link Scene}
     * @param title {@link String}
     * @param gridLayerInterface {@link GridlayerInterface}
     * @return {@link GridLayer}
     */
    public static GridLayer make(Scene scene, String title, GridlayerInterface gridLayerInterface) {
        return new GridLayer(scene, title, gridLayerInterface);
    }
    
}/** end class. */
