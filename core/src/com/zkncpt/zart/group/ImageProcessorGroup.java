package com.zkncpt.zart.group;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net.HttpMethods;
import com.badlogic.gdx.Net.HttpRequest;
import com.badlogic.gdx.Net.HttpResponse;
import com.badlogic.gdx.Net.HttpResponseListener;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.ScreenUtils;
import com.zkncpt.zart.screen.MainScreen;
import com.zkncpt.zart.util.Constants;

public class ImageProcessorGroup extends Group {

	private static final String tag = "ImageProcessorGroup";

	MainScreen mainScreen;
	// ImageStack imageStack;
	boolean shaderActivated = false;
	private Texture initialTexture;
	private Texture decomposedTexture;
	private Pixmap decomposedPixmap;
	private Image image;

	private ShaderProgram shader;
	private FrameBuffer frameBuffer;

	public ImageProcessorGroup(MainScreen mainScreen) {
		super();
		ShaderProgram.pedantic = false;
		this.mainScreen = mainScreen;
		initialise();

		shader = new ShaderProgram(Gdx.files.internal("zart.vsh"), Gdx.files.internal("zart.fsh"));

		frameBuffer = new FrameBuffer(Format.RGB888, (int) this.getWidth(), (int) this.getHeight(), false);

		if (shader.isCompiled()) {
			Gdx.app.log(tag, "shader is compiled");
		} else {
			Gdx.app.log(tag, "shader is not compiled : " + shader.getLog());
		}
	}

	private void initialise() {
		Gdx.app.log(tag, "initialising");
		setSize(Constants.IMAGE_STACK_WIDTH, Constants.HEIGHT);
		setPosition(Constants.MENU_WIDTH + Constants.SUBMENU_WIDTH, 0);
	}

	@Override
	public void act(float delta) {
		super.act(delta);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {

		super.draw(batch, parentAlpha);

		if (mainScreen.uploadHandler.changed && mainScreen.uploadHandler.getPixmap() != null) {
			Gdx.app.debug(tag, "uploading image");
			initialTexture = new Texture(mainScreen.uploadHandler.getPixmap());
			image = new Image(initialTexture);
			image.setSize(this.getWidth(), this.getHeight());
			this.addActor(image);
			mainScreen.uploadHandler.changed = false;
		}
	}

	public ShaderProgram getShader() {
		return shader;
	}

	public void decompose() {
		if (initialTexture != null) {
			Gdx.app.debug(tag, "decompose image");
			shaderActivated = true;
			frameBuffer.begin();
			mainScreen.getBatch().begin();
			mainScreen.getBatch().setShader(shader);
			Gdx.gl.glClearColor(0, 0, 0, 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			mainScreen.getBatch().draw(initialTexture, 0f, 0f, this.getWidth(), this.getHeight(), 0, 0,
					(int) initialTexture.getWidth(), (int) initialTexture.getHeight(), false, true);
			mainScreen.getBatch().setShader(null);
			mainScreen.getBatch().flush();
			mainScreen.getBatch().end();
			decomposedPixmap = ScreenUtils.getFrameBufferPixmap(0, 0, (int) this.getWidth(), (int) this.getHeight());
			frameBuffer.end();
			if (decomposedTexture != null)
				decomposedTexture.dispose();
			decomposedTexture = new Texture(decomposedPixmap);
			this.removeActor(image);
			image = new Image(decomposedTexture);
			image.setSize(this.getWidth(), this.getHeight());
			this.addActor(image);
			// mainScreen.uploadHandler.save(decomposedPixmap);
			Gdx.app.debug(tag, "decomposedTexture instantiated");
		}
	}

	public void sendToserver() {
		Gdx.app.log(tag, "sending to server");
		String dataURL = mainScreen.uploadHandler.getDataURL(decomposedPixmap);
		String json = "{\"data\": \"" + dataURL + "\"}";
		HttpRequest httpPost = new HttpRequest(HttpMethods.POST);
		httpPost.setUrl("http://127.0.0.1/Zkoncept/imageServer/index");
		httpPost.setHeader("Content-Type", "application/json");
		// Json json = new Json();
		// Gdx.app.debug(tag, "json : " + json + " dataURL : " + dataURL);
		// json.writeValue("dataURL", dataURL);
		Gdx.app.log(tag, "sending to server : " + json.substring(0, 100));
		httpPost.setContent(json);

		Gdx.net.sendHttpRequest(httpPost, new HttpResponseListener() {
			public void handleHttpResponse(HttpResponse httpResponse) {
				String status = httpResponse.getResultAsString();
				Gdx.app.log(tag, "statts : " + status);
			}

			public void failed(Throwable t) {
				String status = "failed";
				Gdx.app.log(tag, "status : " + status);
			}

			@Override
			public void cancelled() {

			}
		});

	}

}
