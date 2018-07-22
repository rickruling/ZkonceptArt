package com.zkncpt.zart.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.zkncpt.zart.ZkonceptArt;

public class DesktopLauncher {
	public static void main(String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = 480;
		config.width = 800;
		new LwjglApplication(new ZkonceptArt(new UploadHandlerAppImpl()), config);
//		new LwjglApplication(new CustomWidgetSample(), config);
	}
}
