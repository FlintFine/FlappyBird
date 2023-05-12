package com.nykis.flappy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

public class GameLogic {
    private Logica logica;
    private Bird bird;
    private Effects effects;
    private Pipes pipes;
    private TextureManager textureManager;
    private Song song;

    private Pontuacao pontuacaos;
    private ConfiguracoesCamera configCamera;
    private Random numeroRandomico;

    public GameLogic(Logica logica, Bird bird, Effects effects, Pipes pipes,
                     TextureManager textureManager, Song song, Pontuacao pontuacaos,
                     ConfiguracoesCamera configCamera, Random numeroRandomico) {
        configCamera = new ConfiguracoesCamera(1080,1920);
        song = new Song ();
        this.logica = logica;
        this.bird = bird;
        this.effects = effects;
        this.pipes = pipes;
        this.textureManager = textureManager;
        this.song = song;
        this.pontuacaos = pontuacaos;
        this.configCamera = configCamera;
        this.numeroRandomico = numeroRandomico;
    }

    public void updateBirdPosition() {
        bird.setVelocidadeQueda(bird.getVelocidadeQueda() + 1);
        if (bird.getPosicaoInicialVertial() > 0 || bird.getVelocidadeQueda() < 0) {
            bird.setPosicaoInicialVertial(bird.getPosicaoInicialVertial() - bird.getVelocidadeQueda());
        }
    }

    public void handleGamePlayState() {
        if (logica.getEstadoJogo() == 1) {
            effects.setHasReset(false);
            // som de colisão vira false e será tocado novamente se bate
            // song.setSomDeColisaoTocado(false); // ñ tá funcionando sei lá pq
            pipes.setPosicaoMovimentoCanoHorizontal(pipes.getPosicaoMovimentoCanoHorizontal() - bird.getDeltaTime() * 350); // faz o passaro ganhar speed

             // Se a tela foi tocada, passaro começa a cair, o
            if (Gdx.input.justTouched()) {
                long id = song.getSound().play(0.1f);
                song.getSound().setPitch(id, 1);
                song.getSound().setLooping(id, false);
                bird.setVelocidadeQueda(-15); // Passaro sobe por causa do ultimo if que é
                //tbm -= confuso, esses sinais - - formao + e de 500vai para 520 ao clicar
            }

            // Verifica se o cano saiu inteiramente da tela
            if (pipes.getPosicaoMovimentoCanoHorizontal() < -textureManager.getCanoTopo().getWidth()) { // pontua se o cano sai da tela
                pipes.setPosicaoMovimentoCanoHorizontal(configCamera.getLarguraDispositivo());
                pipes.setAlturaEntreCanosRandomica(numeroRandomico.nextInt(400) - 200);   // altura entre canos, ñ gera nenhum cano
                pontuacaos.setMarcouPonto(false);
            }

            // Verifica pontuação
            if (pipes.getPosicaoMovimentoCanoHorizontal() < -200) { // esse valor negativo resolveu o bug do primeiro pipe q n pontua sei lá pq
                if (!pontuacaos.getMarcouPonto()) {
                    pontuacaos.setPontuacao(pontuacaos.getPontuacao() + 1);
                    pontuacaos.setMarcouPonto(true);
                }
            }
        } else  {
            handleGameOverState();
        }
    }

    public void teste(Batch batch, GlyphLayout layout, String pontuacaoStr){
        //REMOVER A PONTUAÇÃO DA TELA INICIAL
        if(logica.getEstadoJogo()!=0){
            FontManager.getFonteFont().draw(batch, pontuacaoStr, configCamera.getLarguraDispositivo() / 2 - pontuacaos.getLarguraPontuacao() / 2, configCamera.getAlturaDispositivo() - 50);// ATUAL MANTEM PONTUAÇÃO CENTRALIZADO INDEPENDE DE QTS DIGITOS
        }
        // IMAGEM/MENSAGEM DE GAME OVER, tamanho, etc.
        if (logica.getEstadoJogo() == 2) {
            batch.draw(textureManager.getGameOver(), configCamera.getLarguraDispositivo() / 2 - textureManager.getGameOver().getWidth() / 4, configCamera.getAlturaDispositivo() / 2 - textureManager.getGameOver().getHeight() / 4, textureManager.getGameOver().getWidth() / 2, textureManager.getGameOver().getHeight() / 2);

        }
        // MENSAGEM DE INICIAR O JOGO
        if(logica.getEstadoJogo()==0){
            if (!effects.isHasReset()) { //
                effects.reseta();
                effects.setHasReset(true);
            }
            FontManager.getZemaFont().draw(batch, "Maior Pontuação: "+pontuacaos.getRecorde(), configCamera.getLarguraDispositivo() / 2 - 505, configCamera.getAlturaDispositivo()-55  );
            FontManager.getFonteFont().getData().setScale(0.6F);
            layout.setText(FontManager.getFonteFont(), "TOCAR PARA COMEÇAR");
            FontManager.getFonteFont().draw(batch, layout, (configCamera.getLarguraDispositivo() - layout.width) / 2, (configCamera.getAlturaDispositivo() + layout.height) / 2-100);

        //SCORE VOLTAR PARA SEU TAMAHO PADRÃO
        }else {
            FontManager.getFonteFont().getData().setScale(1F);
        }

        bird.setPassarocirculo(new Circle(30 + textureManager.getPassaros()[0].getWidth() / 10, bird.getPosicaoInicialVertial() + textureManager.getPassaros()[0].getHeight() / 10, textureManager.getPassaros()[0].getWidth() / 14));
        pipes.setRetangulocanoBaixo( new Rectangle(
                pipes.getPosicaoMovimentoCanoHorizontal(), configCamera.getAlturaDispositivo() / 2 - textureManager.getCanoBaixo().getHeight() - +pipes.getEspacoEntreCanos() / 2 + pipes.getAlturaEntreCanosRandomica(), textureManager.getCanoBaixo().getWidth(), textureManager.getCanoBaixo().getHeight()
        ));

        pipes.setRetangulorecanoTopo(new Rectangle(
                pipes.getPosicaoMovimentoCanoHorizontal(), configCamera.getAlturaDispositivo() / 2 + pipes.getEspacoEntreCanos() / 2 + pipes.getAlturaEntreCanosRandomica(),
                textureManager.getCanoTopo().getWidth(), textureManager.getCanoTopo().getHeight()
        ));
    }
    public void maisDisso (Batch batch, SalvarRecord salvarRecord, Song song){
        //teste de colisão
        if (Intersector.overlaps(bird.getPassarocirculo(), pipes.getRetangulocanoBaixo()) || Intersector.overlaps(bird.getPassarocirculo(), pipes.getRetangulorecanoTopo()) || (bird.getPosicaoInicialVertial() <= 0) || (bird.getPosicaoInicialVertial() >= configCamera.getAlturaDispositivo())) {
            FontManager.getMensagemFont().draw(batch, "Toque na tela para reiniciar", configCamera.getLarguraDispositivo() / 2 - 270, configCamera.getAlturaDispositivo() / 2 - textureManager.getGameOver().getHeight() / 4);
            //SOUND
            if (!song.isSomDeColisaoTocado()) { // verifica se o som de colisão já foi tocado ou não
                long id=song.getSoundColisao().play(0.1f); // toca o som de colisão
                song.getSoundColisao().setLooping(id,false); // define a propriedade de looping como "false"
                song.setSomDeColisaoTocado(true);  // define a variável como "true" para indicar que o som já foi tocado
            }
            if (pontuacaos.getPontuacao() > pontuacaos.getRecorde()) {
                pontuacaos.setRecorde(pontuacaos.getPontuacao());
                salvarRecord.salvarRecorde(pontuacaos.getRecorde());
            }
            logica.setEstadoJogo(2);
        }
    }

    public void handleGameOverState() {
        if (Gdx.input.justTouched()) {
            if ((bird.getPosicaoInicialVertial() <= 0)) {
                logica.setEstadoJogo(0);
                bird.setVelocidadeQueda(0);
                pontuacaos.setPontuacao(0);
                bird.setPosicaoInicialVertial(configCamera.getAlturaDispositivo() / 2);
                pipes.setPosicaoMovimentoCanoHorizontal(configCamera.getLarguraDispositivo());

            }
        }
    }
 }
