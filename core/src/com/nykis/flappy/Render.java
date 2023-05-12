package com.nykis.flappy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
public class Render {

    // outros atributos
    // aqui o codigo fica simples pq eu basicamente só estou colocando os metodos e ñ as variaveis
    public static void renderEffects(int intervaloPontuacaoSprite, Batch batch) {
        switch (intervaloPontuacaoSprite) {
            case 1:
                Effects.getSnowEffect().update(Gdx.graphics.getDeltaTime());
                Effects.getSnowEffect().draw(batch);
                break;
            case 2:
                Effects.getRainEffect().update(Gdx.graphics.getDeltaTime());
                Effects.getRainEffect().draw(batch);
                break;
            case 3:
                Effects.getFlameEffect().update(Gdx.graphics.getDeltaTime());
                Effects.getFlameEffect().draw(batch);
                break;
            default:
                break;
        }
    }
    public void renderBackground(SpriteBatch batch, Texture fundo, Texture fundo2, int intervaloPontuacao, float larguraDispositivo, float alturaDispositivo) {
        switch (intervaloPontuacao) {
            case 0:
                batch.draw(fundo, 0, 0, larguraDispositivo, alturaDispositivo);
                break;
            case 1:
                batch.draw(fundo2, 0, 0, larguraDispositivo, alturaDispositivo);
                break;
            default:
                break;
        }
    }
        public void renderizarTudo(Batch batch, TextureManager textureManager, Pipes pipes, Bird bird, Pontuacao pontuacaos, ConfiguracoesCamera configCamera) {
            batch.setProjectionMatrix(configCamera.getCamera().combined);
            renderBackground((SpriteBatch) batch, textureManager.getFundo(), textureManager.getFundo2(), pontuacaos.getIntervaloPontuacao(), configCamera.getLarguraDispositivo(), configCamera.getAlturaDispositivo());
            batch.draw(textureManager.getCanoTopo(), pipes.getPosicaoMovimentoCanoHorizontal(), configCamera.getAlturaDispositivo() / 2 + pipes.getEspacoEntreCanos() / 2 + pipes.getAlturaEntreCanosRandomica());
            batch.draw(textureManager.getCanoBaixo(), pipes.getPosicaoMovimentoCanoHorizontal(), configCamera.getAlturaDispositivo() / 2 - textureManager.getCanoBaixo().getHeight() - +pipes.getEspacoEntreCanos() / 2 + pipes.getAlturaEntreCanosRandomica());
            batch.draw(textureManager.getPassaros()[(int) bird.getVariacao()], 30, bird.getPosicaoInicialVertial(), 115, 115);
           renderEffects(pontuacaos.getIntervaloPontuacaoSprite(), batch);
        }



    public void dispose(TextureManager textureManager) {
       textureManager.getFundo().dispose();
        textureManager.getFundo2().dispose();
        textureManager.getCanoBaixo().dispose();
        textureManager.getCanoTopo().dispose();
        textureManager.getGameOver().dispose();
        for (Texture texture : textureManager.getPassaros()) {
            texture.dispose();
        }
        // outros métodos
    }
}