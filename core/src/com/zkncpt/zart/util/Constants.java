package com.zkncpt.zart.util;

import com.badlogic.gdx.Gdx;

public abstract class Constants {
	private static final String tag = "Constants";

	public static void setGraphicSizes(int width, int height) {
		WIDTH = width;
		HEIGHT = height;
		MENU_WIDTH = WIDTH * 0.05f;
		SUBMENU_WIDTH = WIDTH * 0.15f;

		MENU_WIDTH = WIDTH > 800 ? MENU_WIDTH : (800 / WIDTH) * 30;
		SUBMENU_WIDTH = WIDTH > 800 ? SUBMENU_WIDTH : (800 / WIDTH) * 160;

		// MENU_WIDTH = WIDTH < 1200 ? MENU_WIDTH : (1200 / WIDTH) * 30;
		// SUBMENU_WIDTH = WIDTH < 1200 ? SUBMENU_WIDTH : (1200 / WIDTH) * 180;

		IMAGE_STACK_WIDTH = WIDTH - MENU_WIDTH - SUBMENU_WIDTH;
		Gdx.app.debug(tag, " resizing width : " + width + " height : " + height + " MENU_WIDTH:" + MENU_WIDTH
				+ " SUBMENU_WIDTH : " + SUBMENU_WIDTH);
	}

	public static float WIDTH = 800;
	public static float HEIGHT = 480;
	public static float MENU_WIDTH = 40;
	public static float SUBMENU_WIDTH = 120;
	public static float IMAGE_STACK_WIDTH = 320;

	public static final String TEXTURE_ATLAS_LIBGDX_UI = "skin\\sgx\\skin\\sgx-ui.atlas";
	public static final String SKIN_LIBGDX_UI = "skin\\sgx\\skin\\sgx-ui.json";

	public static String VertexShader = "uniform mat4 u_projTrans;\n" + "\n" + "attribute vec4 a_position;\n"
			+ "attribute vec2 a_texCoord0;\n" + "attribute vec4 a_color;\n" + "\n" + "varying vec4 v_color;\n"
			+ "varying vec2 v_texCoord;\n" + "\n" + "void main() {\n" + "    gl_Position = u_projTrans * a_position;\n"
			+ "    v_texCoord = a_texCoord0;\n" + "    v_color = a_color;\n" + "}\n";


	public static String FragmentShader = "#ifdef GL_ES                                                            \n"
			+ "precision mediump float;                                                \n"
			+ "precision mediump int;                                                  \n"
			+ "#else                                                                   \n"
			+ "		#define highp;                                                       \n"
			+ "#endif                                                                  \n"
			+ "uniform sampler2D u_texture;                                            \n"
			+ "varying vec4 v_color;                                                   \n"
			+ "varying vec2 v_texCoord;                                                \n"
			+ "uniform vec4 u_colors[10];                                              \n"
			+ "uniform float u_colorIntensity[10];                                     \n"
			+ "const vec3 grayScaleMultiplier = vec3(0.299, 0.587, 0.114);             \n"
			+ "void main() {                                                           \n"
			+ "		vec4 texColor = texture2D(u_texture, v_texCoord);                    \n"
			+ "		float gray = dot(texColor.rgb, grayScaleMultiplier);                 \n"
			+ "		vec4 color = u_colors[0];                                            \n"
			+ "		for(int i = 0;i<10;i++){                                             \n"
			+ "			if(gray<u_colorIntensity[i])                                     \n"
			+ "				color = u_colors[i];                                         \n"
			+ "			else{                                                            \n"
			+ "				break;                                                           \n"
			+ "			}                                                                \n"
			+ "		}                                                                    \n"
			+ "		gl_FragColor = color;                                            \n"
			+ "}                                                                       \n";

}
