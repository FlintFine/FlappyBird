package com.nykis.flappy;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class ConfiguracoesCamera {

    //camera
    private OrthographicCamera camera;
    private StretchViewport viewport;
    //
    private float   larguraDispositivo;
    private float   alturaDispositivo;
    private final float VIRTUAL_WIDH = 1080;
    private final float VIRTUAL_HEIGH = 1920;

    public ConfiguracoesCamera(float virtualWidth, float virtualHeight) {
        camera = new OrthographicCamera();
        viewport = new StretchViewport(virtualWidth, virtualHeight, camera);
        larguraDispositivo = virtualWidth;
        alturaDispositivo = virtualHeight;
    }

    public void configurarCamera() {
        camera.position.set(larguraDispositivo / 2, alturaDispositivo / 2, 0);
        camera.update();
        viewport.update((int) larguraDispositivo, (int) alturaDispositivo, true);
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public Viewport getViewport() {
        return viewport;
    }

    public float getLarguraDispositivo() {
        return larguraDispositivo;
    }

    public float getAlturaDispositivo() {
        return alturaDispositivo;
    }

    public float getVIRTUAL_WIDH() {
        return VIRTUAL_WIDH;
    }

    public float getVIRTUAL_HEIGH() {
        return VIRTUAL_HEIGH;
    }
}
