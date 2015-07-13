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


package pk.muneebahmad.witype.nodes;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquation;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import pk.muneebahmad.algotype.actions.TimelineAction;
import pk.muneebahmad.witype.event.ActorEvent;
import pk.muneebahmad.witype.event.ActorEventObserver;
import pk.muneebahmad.witype.event.ActorEventSource;
import pk.muneebahmad.witype.tween.GroupAccessor;

/**
 *
 * @author muneebahmad
 */
public class Director implements Screen, TweenCallback, Disposable {

    public static Director sInstance;
    
    public static final int DEFAULT_SCENES_CAPACITY = 10;
    
    public static final int TRANSITION_RIGHT_PUSH_IN = 0;
    public static final int TRANSITION_LEFT_PUSH_IN = 1;
    public static final int TRANSITION_TOP_PUSH_IN = 2;
    public static final int TRANSITION_BOTTOM_PUSH_IN = 3;
    public static final int TRANSITION_SCALE_IN = 4;
    public static final int TRANSITION_SCALE_OUT = 5;
    public static final int TRANSITION_ROTATE = 6;
    public static final int TRANSITION_FADE_IN = 7;
    public static final int TRANSITION_FADE_OUT = 8;
    public static final int TRANSITION_LEFT_SLIDE_IN = 9;
    public static final int TRANSITION_RIGHT_SLIDE_IN =10;
    public static final int TRANSITION_TOP_SLIDE_IN = 11;
    public static final int TRANSITION_BOTTOM_SLIDE_IN = 12;
    
    private Scene currentScene;
    private Scene inScene;
    private Scene splashScene;
    private String currentSceneName;
    private final Array<IDirectorLifecycleListener> scenes;
    
    private float r;
    private float g;
    private float b;
    private float a;
    
    private int width;
    private int height;
    
    private ActorEventSource eventSource;
    private SpriteBatch spriteBatch;
    
    /**
     * CONSTRUCTOR.
     */
    public Director() {
        
        this.scenes = new Array<IDirectorLifecycleListener>(DEFAULT_SCENES_CAPACITY);
        this.r = 0.0f;
        this.g = 0.0f;
        this.b = 0.0f;
        this.a = 0.0f;
        
        //this.spriteBatch = new SpriteBatch();
        this.eventSource = new ActorEventSource();
    }
    
    /**
     * 
     * @param scene 
     */
    public void addScene(Scene scene) {
        scenes.add(scene);
    }
    
    /**
     * 
     * @param scene 
     */
    public void removeScene(Scene scene) {
        int i = scenes.indexOf(scene, false);
        if (i >= 0) {
            scenes.removeIndex(i);
        }
    }
    
    /**
     * 
     * @param splashScene 
     * @param currentScene 
     */
    public void setSplashScene(Scene splashScene, Scene currentScene) {
        this.splashScene = splashScene;
        do {
            splashScene.enter();
        } while (this.currentScene == null);
        setCurrentScene(currentScene);
    }
    
    /**
     * 
     * @return 
     */
    public Scene getSplashScene() {
        return this.splashScene;
    }
    
    /**
     * sets the incoming scene.
     * @param inScene 
     */
    public void setInScene(Scene inScene) {
        this.inScene = inScene;
    }
    
    /**
     * get the incoming scene,
     * @return 
     */
    public Scene getInScene() {
        return this.inScene;
    }
    
    /**
     * get Current active scene.
     * @return 
     */
    public synchronized Scene getCurrentScene() {
        return this.currentScene;
    }
    
    /**
     * sets the current active scene.
     * @param currentScene 
     */
    public synchronized void setCurrentScene(Scene currentScene) {
       
        if (this.currentScene != null) {
            this.currentScene.exit();
        }
        
        this.currentScene = currentScene;
        
        if (this.currentScene != null) {
            this.currentScene.enter();
            setCurrentSceneName();
            addScene(currentScene);
            Gdx.app.log("CURRENT SCENE NAME-->", currentSceneName);
            Gdx.app.log("SCENE TOTAL COUNT-->", this.scenes.size + "");
        }
    }
    
    /**
     * Initializes the current Scene.
     */
    public synchronized void initCurrentScene() {
        this.currentScene.myShow();
    }
    
    /**
     * 
     */
    public void setCurrentSceneName() {
        this.currentSceneName = this.currentScene.getClass().getName();
    }
    
    /**
     * 
     * @return 
     */
    public String getCurrentSceneName() {
        return this.currentSceneName;
    }
    
    /**
     * Exits and destroys this given scene.
     * @param scene 
     */
    public synchronized void popScene(Scene scene) {
        scene.getRoot().clear();
        scene.exit();
        this.removeScene(scene);
        //currentScene = null;
        if (currentScene == null) {
            Gdx.app.exit();
        }
    }
    
    /**
     * the width in pixels of display surface
     * @return width
     */
    public synchronized int getWindowWidth() {
        return Gdx.graphics.getWidth();
    }
    
    /**
     * The height in pixels of display surface.
     * @return height 
     */
    public synchronized int getWindowHeight() {
        return Gdx.graphics.getHeight();
    }
    
    /**
     * sets the virtual width of the window.
     * @param width int
     */
    public void setBaseWidth(int width) {
        this.width = width;
    }
    
    /**
     * returns virtual width of the window.
     * @return width 
     */
    public int getBaseWidth() {
        return this.width;
    }
    
    /**
     * sets the virtual height of the window
     * @param height 
     */
    public void setBaseHeight(int height) {
        this.height = height;
    }
    
    /**
     * returns the virtual height of the window.
     * @return 
     */
    public int getBaseHeight() {
        return this.height;
    }
    
    /**
     * By default the window color is set to Nothing. i-e black.
     * The default values are r 0, g 0, b 0, a 0.
     * You can set your own choice color by calling this method in your 
     * Constructor.
     * @param r
     * @param g
     * @param b
     * @param a 
     */
    public void setGlClearColor(float r, float g, float b, float a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }
    
    /**
     * pushes the scene from one scene to other with transition animation.
     * @param transitionType
     * @param durationMillis
     * @param currentScene
     * @param inScene 
     * @param easeEquation 
     */
    public synchronized void pushScene(int transitionType, final int durationMillis, 
            final Scene currentScene, final Scene inScene, TweenEquation easeEquation) {
        this.inScene = inScene;
        
        switch (transitionType) {
            case TRANSITION_RIGHT_PUSH_IN:
                Tween.registerAccessor(Group.class, new GroupAccessor());
                createTweenTransition(inScene, currentScene, inScene.getWidth(), 0.0f,
                        -currentScene.getWidth(), 0.0f, GroupAccessor.POSITION_XY, 
                        durationMillis, easeEquation);
                break;
            case TRANSITION_LEFT_PUSH_IN:
                Tween.registerAccessor(Group.class, new GroupAccessor());
                createTweenTransition(inScene, currentScene, -inScene.getWidth(), 0.0f, 
                        currentScene.getWidth(), 0.0f, GroupAccessor.POSITION_XY, 
                        durationMillis, easeEquation);
                break;
            case TRANSITION_TOP_PUSH_IN:
                Tween.registerAccessor(Group.class, new GroupAccessor());
                createTweenTransition(inScene, currentScene, 0.0f, inScene.getHeight(), 
                        0.0f, -currentScene.getHeight(), GroupAccessor.POSITION_XY, 
                        durationMillis, easeEquation);
                break;
            case TRANSITION_BOTTOM_PUSH_IN:
                Tween.registerAccessor(Group.class, new GroupAccessor());
                createTweenTransition(inScene, currentScene, 0.0f, -inScene.getHeight(), 
                        0.0f, currentScene.getHeight(), GroupAccessor.POSITION_XY, 
                        durationMillis, easeEquation);
                break;
            case TRANSITION_SCALE_IN:
                Tween.registerAccessor(Group.class, new GroupAccessor());
                createTweenScaleTransition(inScene, currentScene, 2.0f, 
                        2.0f, 0.0f, 
                        0.0f, GroupAccessor.SCALE_XY, 
                        durationMillis, easeEquation);
                break;
            case TRANSITION_SCALE_OUT:
                Tween.registerAccessor(Group.class, new GroupAccessor());
                createTweenScaleTransition(inScene, currentScene, 0.0f, 0.0f, 2.0f, 2.0f, 
                        GroupAccessor.SCALE_XY, durationMillis, easeEquation);
                break;
            case TRANSITION_FADE_IN:
                Tween.registerAccessor(Group.class, new GroupAccessor());
                createTweenAlphaTransition(inScene, currentScene, 0.0f, 0.0f, 
                        GroupAccessor.ALPHA, durationMillis, easeEquation);
                break;
            case TRANSITION_RIGHT_SLIDE_IN:
                Tween.registerAccessor(Group.class, new GroupAccessor());
                createSlideTweenTransition(inScene, currentScene, inScene.getWidth(), 0,
                        GroupAccessor.POSITION_XY, durationMillis, easeEquation);
                break;
            case TRANSITION_LEFT_SLIDE_IN:
                Tween.registerAccessor(Group.class, new GroupAccessor());
                createSlideTweenTransition(inScene, currentScene, -inScene.getWidth(), 0, 
                        GroupAccessor.POSITION_XY, durationMillis, easeEquation);
                break;
            case TRANSITION_BOTTOM_SLIDE_IN:
                Tween.registerAccessor(Group.class, new GroupAccessor());
                createSlideTweenTransition(inScene, currentScene, 0, -inScene.getHeight(), 
                        GroupAccessor.POSITION_XY, durationMillis, easeEquation);
                break;
            case TRANSITION_TOP_SLIDE_IN:
                Tween.registerAccessor(Group.class, new GroupAccessor());
                createSlideTweenTransition(inScene, currentScene, 0, inScene.getHeight(), 
                        GroupAccessor.POSITION_XY, durationMillis, easeEquation);
                break;
            default:
                break;
        }
    }
    
   /**
    * 
    * @param inScene
    * @param currentScene
    * @param inTargetX
    * @param inTargetY
    * @param outTargetX
    * @param outTargetY
    * @param tweenAccessor
    * @param durationMillis
    * @param easeEquation 
    */
    private void createTweenTransition(Scene inScene, Scene currentScene, float inTargetX, float inTargetY, 
            float outTargetX, float outTargetY,int tweenAccessor, int durationMillis, TweenEquation easeEquation) {
                //IN SCENE TIMELINE
                Timeline inTimeline = Timeline.createSequence().
                        beginSequence().
                        push(Tween.to(inScene.getRoot(), tweenAccessor, 0).
                                target(inTargetX, inTargetY).ease(easeEquation)).
                        push(Tween.to(inScene.getRoot(), tweenAccessor, 
                                durationMillis).target(0, 0).ease(easeEquation)).
                        end().start();
                //IN SCENE TIMELINE ACTION.
                TimelineAction inTimelineAction = TimelineAction.$(inTimeline);
                inScene.getRoot().addAction(inTimelineAction);
                
                //OUT SCENE TIMELINE
                Timeline outTimeline = Timeline.createSequence().
                        beginSequence().
                        push(Tween.to(currentScene.getRoot(), tweenAccessor, 0).
                                target(0, 0).ease(easeEquation)).
                        push(Tween.to(currentScene.getRoot(), tweenAccessor, durationMillis).
                                target(outTargetX, outTargetY).ease(easeEquation)).
                                setCallbackTriggers(TweenCallback.COMPLETE).
                                setCallback(this).end().start();
                //OUT SCENE TIMELINE ACTION
                TimelineAction outTimelineAction = TimelineAction.$(outTimeline);
                currentScene.getRoot().addAction(outTimelineAction);
    }
    
    /**
     * 
     * @param inScene
     * @param currentScene
     * @param inTargetX
     * @param inTargetY
     * @param outTargetX
     * @param outTargetY
     * @param tweenAccessor
     * @param durationMillis
     * @param easeEquation 
     */
    private void createTweenScaleTransition(Scene inScene, Scene currentScene, float inTargetX, float inTargetY, 
            float outTargetX, float outTargetY,int tweenAccessor, int durationMillis, TweenEquation easeEquation) {
        this.getCurrentScene().getRoot().setOriginX(getWindowWidth() / 2.0f);
        this.getCurrentScene().getRoot().setOriginY(getWindowHeight() / 2.0f);
        this.getInScene().getRoot().setOriginX(getWindowWidth() / 2.0f);
        this.getInScene().getRoot().setOriginY(getWindowHeight() / 2.0f);
                //IN SCENE TIMELINE
                Timeline inTimeline = Timeline.createSequence().
                        beginSequence().
                        push(Tween.to(inScene.getRoot(), tweenAccessor, 0).
                                target(inTargetX, inTargetY).ease(easeEquation)).
                        push(Tween.to(inScene.getRoot(), tweenAccessor, 
                                durationMillis).target(1.0f, 1.0f).ease(easeEquation)).
                        end().start();
                //IN SCENE TIMELINE ACTION.
                TimelineAction inTimelineAction = TimelineAction.$(inTimeline);
                inScene.getRoot().addAction(inTimelineAction);
                
                //OUT SCENE TIMELINE
                Timeline outTimeline = Timeline.createSequence().
                        beginSequence().
                        push(Tween.to(currentScene.getRoot(), tweenAccessor, 0).
                                target(1.0f, 1.0f).ease(easeEquation)).
                        push(Tween.to(currentScene.getRoot(), tweenAccessor, durationMillis).
                                target(outTargetX, outTargetY).ease(easeEquation)).
                                setCallbackTriggers(TweenCallback.COMPLETE).
                                setCallback(this).end().start();
                //OUT SCENE TIMELINE ACTION
                TimelineAction outTimelineAction = TimelineAction.$(outTimeline);
                currentScene.getRoot().addAction(outTimelineAction);
    }
    
    private void createSlideTweenTransition(Scene inScene, Scene currentScene, float inTargetX, float inTargetY, 
                        int tweenAccessor, int durationMillis, TweenEquation easeEquation) {
                //IN SCENE TIMELINE
                Timeline inTimeline = Timeline.createSequence().
                        beginSequence().
                        push(Tween.to(inScene.getRoot(), tweenAccessor, 0).
                                target(inTargetX, inTargetY).ease(easeEquation)).
                        push(Tween.to(inScene.getRoot(), tweenAccessor, 
                                durationMillis).target(0, 0).ease(easeEquation)).
                        end().start();
                //IN SCENE TIMELINE ACTION.
                TimelineAction inTimelineAction = TimelineAction.$(inTimeline);
                inScene.getRoot().addAction(inTimelineAction);
                
                //OUT SCENE TIMELINE
                Timeline outTimeline = Timeline.createSequence().
                        beginSequence().
                        push(Tween.to(currentScene.getRoot(), tweenAccessor, 0).
                                target(0, 0).ease(easeEquation)).
                        push(Tween.to(currentScene.getRoot(), tweenAccessor, durationMillis).
                                target(0, 0).ease(easeEquation)).
                                setCallbackTriggers(TweenCallback.COMPLETE).
                                setCallback(this).end().start();
                //OUT SCENE TIMELINE ACTION
                TimelineAction outTimelineAction = TimelineAction.$(outTimeline);
                currentScene.getRoot().addAction(outTimelineAction);
    }
    
    /**
     * 
     * @param inScene
     * @param currentScene
     * @param inTarget
     * @param outTarget
     * @param tweenAccessor
     * @param durationMillis
     * @param easeEquation 
     */
    private void createTweenAlphaTransition(Scene inScene, Scene currentScene, 
            float inTarget, float outTarget, 
            int tweenAccessor, int durationMillis, TweenEquation easeEquation) {
                //IN SCENE TIMELINE
                Timeline inTimeline = Timeline.createSequence().
                        beginSequence().
                        push(Tween.to(inScene.getRoot(), tweenAccessor, 0).
                                target(inTarget).ease(easeEquation)).
                        push(Tween.to(inScene.getRoot(), tweenAccessor, 
                                durationMillis).target(1.0f).ease(easeEquation)).
                        end().start();
                //IN SCENE TIMELINE ACTION.
                TimelineAction inTimelineAction = TimelineAction.$(inTimeline);
                inScene.getRoot().addAction(inTimelineAction);
                
                //OUT SCENE TIMELINE
                Timeline outTimeline = Timeline.createSequence().
                        beginSequence().
                        push(Tween.to(currentScene.getRoot(), tweenAccessor, 0).
                                target(1.0f).ease(easeEquation)).
                        push(Tween.to(currentScene.getRoot(), tweenAccessor, durationMillis).
                                target(outTarget).ease(easeEquation)).
                                setCallbackTriggers(TweenCallback.COMPLETE).
                                setCallback(this).end().start();
                //OUT SCENE TIMELINE ACTION
                TimelineAction outTimelineAction = TimelineAction.$(outTimeline);
                currentScene.getRoot().addAction(outTimelineAction);
    }
    
    /**
     * creates the instance of Director.
     * @return sInstance 
     */
    public static Director getInstance() {
           synchronized(Director.class) {
               if (sInstance == null) {
                   sInstance = new Director();
               }
           }
        return sInstance;
    }

    @Override
    public void render(float delta) {
        if (this.currentScene != null) {
            this.currentScene.myRender(delta);
            Gdx.app.log(getCurrentSceneName() + "-->", "Render called");
        }
    }

    @Override
    public void resize(int width, int height) {
        if (this.currentScene != null) {
            this.currentScene.myResize(width, height);
        }
    }

    @Override
    public void show() {
        if (this.currentScene != null) {
            this.currentScene.myShow();
        }
        Gdx.app.log(getCurrentSceneName() + "INFORMATION-->", "Show called");
    }

    @Override
    public void hide() {
        if (this.currentScene != null) {
            this.currentScene.myHide();
        }
    }

    @Override
    public void pause() {
        if (this.currentScene != null) {
            this.currentScene.myPause();
        }
    }

    @Override
    public void resume() {
        if (this.currentScene != null) {
            this.currentScene.myResume();
        }
    }

    @Override
    public void dispose() {
        if (this.currentScene != null) {
            this.currentScene.myDispose();
        }
        if (spriteBatch != null) {
            this.spriteBatch.dispose();
        }
    }

    /**
     * 
     * @param type
     * @param source 
     */
    @Override
    public void onEvent(int type, BaseTween<?> source) {
        switch(type) {
            case COMPLETE:
                this.setCurrentScene(inScene);
                break;
            default:
                break;
        }
    }
    
    /**
     * IDirectorLifecycleListener interface.
     */
    public static interface IDirectorLifecycleListener {
        
        /**
         * @param delta 
         */
        public void myRender(float delta);
        
        /**
         * 
         * @param width
         * @param height 
         */
        public void myResize(int width, int height);
        
        /**
         * 
         */
        public void myShow();
        
        /**
         * 
         */
        public void myHide();
        
        /**
         * 
         */
        public void myPause();
        
        /**
         * 
         */
        public void myResume();
        
        /**
         * 
         */
        public void myDispose();
    }
    
    /**
     * <=========================================================================>
     */
    
    public void upadate() {
        // Update events.
        eventSource.update();

        // Update View
        Gdx.gl.glClearColor(this.r, this.g, this.b, this.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (currentScene != null) {
            float delta = Gdx.graphics.getDeltaTime();
            currentScene.act(Gdx.graphics.getDeltaTime());
            currentScene.draw();
        }
        if (inScene != null) {
            inScene.act(Gdx.graphics.getDeltaTime());
            inScene.draw();
        } else {
            Gdx.app.log("Director", "WTF! - No scene");
        }
        
    }
    
    public void sendEvent(int id, Actor actor) {
        eventSource.sendEvent(id, actor, ActorEvent.DEFAULT_PRIORITY);
    }

    /**
     * Send event to observers.
     *
     * @param id The event id.
     * @param actor The associated actor.
     * @param priority Adopt a priority value. Must be > 0.
     */
    public void sendEvent(int id, Actor actor, int priority) {
        eventSource.sendEvent(id, actor, priority);
    }

    /**
     * Add event observer event handler.
     *
     * DO NOT PUT THIS INTO THE CONSTRUCTOR. IT MUST GO INTO THE "ENTER"
     * HANDLER.
     *
     * @param observer The event observer.
     */
    public void registerEventHandler(ActorEventObserver observer) {
        eventSource.addObserver(observer);
    }

    /**
     * Remove event observer event handler.
     *
     * DO NOT FORGET TO PUT THIS INTO THE "EXIT" HANDLER IF YOU HAVE MATCHING
     * "REGISTER" IN THE ENTER HANDLER.
     *
     * @param observer The event observer.
     */
    public void deregisterEventHandler(ActorEventObserver observer) {
        eventSource.removeObserver(observer);
    }

    /**
     * Clear all handlers.
     *
     */
    public void clearEventHandlers() {
        // Clear all event subscriptions.
        eventSource.clear();
    }
    
    public SpriteBatch getSpriteBatch() {
        return spriteBatch;
    }

    public void setSpriteBatch(SpriteBatch spriteBatch) {
        this.spriteBatch = spriteBatch;
    }

    public void setEventSource(ActorEventSource eventSource) {
        this.eventSource = eventSource;
    }
}/** end class. */
