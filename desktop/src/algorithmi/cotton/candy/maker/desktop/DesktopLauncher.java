package algorithmi.cotton.candy.maker.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import algorithmi.cotton.candy.maker.CottonCandyMaker;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new CottonCandyMaker(), config);
                config.width = 480;
                config.height = 800;
                config.title = CottonCandyMaker.NAME + CottonCandyMaker.VERSION;
                config.resizable = false;
	}
}
