package org.wintermartens.kartgame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import org.wintermartens.kartgame.KartGame;

import java.awt.*;

public class DesktopLauncher
{
	public static void main(String[] arg)
	{
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		config.width = 1280;
		config.height = 720;
		config.samples = 2;

		while(config.width > screenSize.width * 7 / 8 || config.height > screenSize.height * 7 / 8)
		{
			config.width -= 16;
			config.height -= 9;
		}
		new LwjglApplication(new KartGame(), config);
	}
}
