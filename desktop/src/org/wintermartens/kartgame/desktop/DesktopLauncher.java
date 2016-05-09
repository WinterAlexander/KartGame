package org.wintermartens.kartgame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import org.wintermartens.kartgame.KartGame;

import java.awt.*;

public class DesktopLauncher
{
	public static void main (String[] arg)
	{
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		config.width = 1280;
		config.height = 720;

		while(config.width > screenSize.width - 64 || config.height > screenSize.height - 64)
		{
			config.width -= 16;
			config.height -= 9;
		}
		new LwjglApplication(new KartGame(), config);
	}
}
