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
 * DEALINGS IN THE SOFTWARE. *
 */
package pk.muneebahmad.witype.ui;

import com.badlogic.gdx.graphics.Color;
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
public class Dialog {

    private Layer dialogLayer;
    private Layer bgLayer;
    private Skin skin;
    private Label titleLabel;
    private Label msgLabel;
    private Image layerBg;
    private Image dialogBg;
    private Image divider;
    private String msg;
    private ScrollPane scrollPane;

    int dialogPosition;
    int pos;

    private DialogClickListener dialogClickListener;

    /**
     * sets the dialog in and out animation of pop up which is XYScale and
     * alpha.
     */
    public static final int ANIMATION_POPUP = 0;

    /**
     * sets the dialog in and out animation of scale on Y-axis.
     */
    public static final int ANIMATION_SCALE_Y = 1;

    /**
     * sets the dialog in and out animation of scale on X-axis.
     */
    public static final int ANIMATION_SCALE_X = 2;

    public static final int DIALOG_CANCEL = 3;

    int animMode = ANIMATION_POPUP;

    /**
     * default constructor.
     */
    public Dialog() {
    }

    /**
     * constructor.
     *
     * @param dialogClickListener
     */
    public Dialog(DialogClickListener dialogClickListener) {
        this.dialogClickListener = dialogClickListener;
    }

    /**
     *
     * @param scene
     * @param skin
     * @param dialogBg
     * @param layerBg
     * @param divider
     * @param title
     * @param msg
     * @param positiveButton
     * @param negativeButton CONSTRUCTOR.
     * @param animMode
     */
    public Dialog(Scene scene, Skin skin, Image dialogBg, Image layerBg, Image divider,
            String title, String msg, Button positiveButton, Button negativeButton, int animMode) {
        makeDialog(scene, skin, dialogBg, layerBg, divider, title, msg,
                positiveButton, negativeButton, animMode);
    }

    /**
     *
     * @param scene
     * @param skin
     * @param dialogBg
     * @param layerBg
     * @param divider
     * @param title
     * @param msg
     * @param positiveButton
     * @param negativeButton
     */
    private void makeDialog(Scene scene, Skin skin, Image dialogBg, Image layerBg, Image divider,
            String title, String msg, Button positiveButton, Button negativeButton, int animMode) {
        this.animMode = animMode;
        this.dialogLayer = Layer.make();
        this.dialogLayer.setWidth(scene.getWidth());
        this.dialogLayer.setHeight(scene.getHeight());

        this.bgLayer = Layer.make();
        this.bgLayer.setWidth(scene.getWidth());
        this.bgLayer.setHeight(scene.getHeight());

        this.msg = msg;
        this.skin = skin;
        //MSG LABEL
        this.msgLabel = new Label(msg, skin);
        this.msgLabel.setPosition(40.0f,
                this.dialogLayer.getHeight() / 2.0f - msgLabel.getHeight() / 2.0f);
        this.msgLabel.setWidth(380.0f);
        this.msgLabel.setWrap(true);
        this.msgLabel.setHeight(msgLabel.getHeight());

        //LAYER BG
        this.layerBg = layerBg;
        this.layerBg.setWidth(bgLayer.getWidth());
        this.layerBg.setHeight(bgLayer.getHeight());
        this.bgLayer.addChild(this.layerBg);

        //DIALOG BG
        this.dialogBg = dialogBg;
        this.dialogBg.setWidth(dialogLayer.getWidth() - 60.0f);
        this.dialogBg.setHeight(this.msgLabel.getPrefHeight() * 8.0f);
        if (this.dialogBg.getHeight() > this.dialogLayer.getHeight()) {
            this.dialogBg.setHeight(this.dialogLayer.getHeight() - 40.0f);
        }
        this.dialogBg.setPosition(dialogLayer.getWidth() / 2.0f - dialogBg.getWidth() / 2.0f,
                dialogLayer.getHeight() / 2.0f - dialogBg.getHeight() / 2.0f);
        this.dialogLayer.addChild(this.dialogBg);
        this.dialogLayer.addChild(msgLabel);

        //TITLE LABLE
        this.titleLabel = new Label(title, skin);
        this.titleLabel.setPosition(dialogBg.getX() + 15.0f, dialogBg.getTop()
                - titleLabel.getHeight() - 30.0f);
        this.dialogLayer.addChild(this.titleLabel);
        this.titleLabel.setEllipse(true);
        this.titleLabel.setColor(Color.YELLOW);

        //DIVIDER
        this.divider = divider;
        this.divider.setWidth(dialogBg.getWidth());
        this.divider.setHeight(4.0f);
        this.divider.setPosition(30.0f, titleLabel.getY() - titleLabel.getHeight() + 10.0f);
        this.dialogLayer.addChild(divider);

        /**
         * POSITIVE BUTTON
         */
        this.dialogLayer.addChild(positiveButton);
        positiveButton.setSize(dialogBg.getWidth() / 2.0f, positiveButton.getHeight());
        positiveButton.setPosition(dialogBg.getX() + dialogBg.getWidth() / 2.0f - positiveButton.getWidth(),
                dialogBg.getY());
        /**
         * *
         * positiveButton.addListener(new ClickListener() {
         *
         * @Override public void clicked(InputEvent event, float x, float y) {
         * Gdx.app.exit(); }
         *
         * }); *
         */

        /**
         * NEGATIVE BUTTON
         */
        this.dialogLayer.addChild(negativeButton);
        negativeButton.setSize(dialogBg.getWidth() / 2.0f, negativeButton.getHeight());
        negativeButton.setPosition(dialogBg.getX() + dialogBg.getWidth() / 2.0f, positiveButton.getY());
        negativeButton.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                dialogCancel();
            }

        });

        scene.addChild(this.bgLayer);
        scene.addChild(this.dialogLayer);

        this.bgLayer.addAction(Actions.alpha(0.0f));
        this.bgLayer.addAction(Actions.fadeIn(0.2f));
        if (animMode == ANIMATION_POPUP) {
            this.dialogLayer.setScale(0.0f);
            this.dialogLayer.setOrigin(dialogLayer.getWidth() / 2.0f, dialogLayer.getHeight() / 2.0f);
            this.dialogLayer.addAction(Actions.scaleTo(1.0f, 1.0f, 1.0f, Interpolation.swingOut));
        } else if (animMode == ANIMATION_SCALE_Y) {
            this.dialogLayer.setOrigin(dialogLayer.getWidth() / 2.0f, dialogLayer.getHeight() / 2.0f + 
                    dialogBg.getHeight() / 2.0f);
            this.dialogLayer.addAction(Actions.scaleTo(1.0f, 0.0f));
            this.dialogLayer.addAction(Actions.scaleTo(1.0f, 1.0f, 1.0f, Interpolation.swingOut));
        } else if (animMode == ANIMATION_SCALE_X) {
            this.dialogLayer.setOrigin(dialogLayer.getWidth() / 2.0f - dialogBg.getWidth() / 2.0f, 
                    dialogLayer.getHeight() / 2.0f);
            this.dialogLayer.addAction(Actions.scaleTo(0.0f, 1.0f));
            this.dialogLayer.addAction(Actions.scaleTo(1.0f, 1.0f, 1.0f, Interpolation.swingOut));
        }
    }

    public void dialogCancel() {
        if (animMode == ANIMATION_POPUP) {
            dialogLayer.addAction(Actions.sequence(Actions.scaleTo(0.0f, 0.0f, 1.0f, Interpolation.swingIn),
                Actions.removeActor()));
        } else if (animMode == ANIMATION_SCALE_Y) {
            dialogLayer.addAction(Actions.sequence(Actions.scaleTo(1.0f, 0.0f, 1.0f, Interpolation.swingIn),
                Actions.removeActor()));
        } else if (animMode == ANIMATION_SCALE_X) {
            dialogLayer.addAction(Actions.sequence(Actions.scaleTo(0.0f, 1.0f, 1.0f, Interpolation.swingIn),
                Actions.removeActor()));
        }
        bgLayer.addAction(Actions.sequence(Actions.fadeOut(1.0f), Actions.removeActor()));
    }

    /**
     * This method creates a new dialog with provided parameters, Initialize
     * your Objects before adding them into this method.
     *
     * @param scene
     * @param skin
     * @param dialogBg
     * @param layerBg
     * @param divider
     * @param title
     * @param msg
     * @param positiveButton
     * @param negativeButton
     * @param animMode
     * @return
     */
    public static Dialog make(Scene scene, Skin skin, Image dialogBg, Image layerBg, Image divider,
            String title, String msg, Button positiveButton, Button negativeButton, int animMode) {
        return new Dialog(scene, skin, dialogBg, layerBg, divider, title, msg,
                positiveButton, negativeButton, animMode);
    }

}
/**
 * end class.
 */
