package com.nykis.flappy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

public class GameLogic {

    private Bird bird;
    private Effects effects;
    private Pipes pipes;
    private TextureManager textureManager;
    private Song song;
    private Pontuacao pontuacaos;
    private ConfiguracoesCamera configCamera;
    private Random numeroRandomico;
    private int EstadoJogo;

    public int getEstadoJogo() {
        return EstadoJogo;
    }

    public void setEstadoJogo(int estadoJogo) {
        EstadoJogo = estadoJogo;
    }

    public GameLogic(Bird bird, Effects effects, Pipes pipes,
                     TextureManager textureManager, Song song, Pontuacao pontuacaos,
                     ConfiguracoesCamera configCamera, Random numeroRandomico) {
        configCamera = new ConfiguracoesCamera(1080,1920);
        song = new Song ();
        this.bird = bird;
        this.effects = effects;
        this.pipes = pipes;
        this.textureManager = textureManager;
        this.song = song;
        this.pontuacaos = pontuacaos;
        this.configCamera = configCamera;
        this.numeroRandomico = numeroRandomico;
    }

        // dividi logica em 2,  mas é um seguimento ñ necessariamente algo diferente
    public void logica(Batch batch, SalvarRecord salvarRecord, String pontuacaoStr, Song song, GlyphLayout layout) {

        if (getEstadoJogo() == 0) {
            iniciarJogo();
            song.setSomDeColisaoTocado(false);
        }

        if (getEstadoJogo() != 0) {
            updateBirdPosition(); // esse funcionou sei lá pq, o chat gp é zi
            if (getEstadoJogo() == 1) {
                handleGamePlayState();
            } else {
                handleGameOverState();
            }
        }
        logicatwo(batch, salvarRecord, pontuacaoStr, song, layout);
        batch.end();
    }

    public void logicatwo(Batch batch, SalvarRecord salvarRecord, String pontuacaoStr, Song song, GlyphLayout layout) {
                teste(batch,layout,pontuacaoStr);
                maisDisso(batch,salvarRecord,song);
    }

    public void uPs(GlyphLayout layout, SpriteBatch batch){
        batch.begin();
        pontuacaos.atualizaTudo(); //talvez a causa do sound seja por falta de algo assim
        configCamera.getCamera().update();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT); 		//Limpar frames anteriores
        pontuacaos.setPontuacaoStr(String.valueOf(pontuacaos.getPontuacao()));  // sem  isso a pontuacao não atualiza
        layout.setText(FontManager.getFonteFont(), pontuacaos.getPontuacaoStr()); // sem isso a pontuacao não fica no meio centralizada
        pontuacaos.setLarguraPontuacao(layout.width);			 // MUITO IMPORTANTE, MANTEM O SCORE CENTRALIZADO CONFORME AUMENTA OS DIGITOS
        bird.delta();

    }

      public void iniciarJogo() {   // unico que tá funcionando

        if (getEstadoJogo() == 0) {//jogo n iniciado
            if (Gdx.input.justTouched()) {
                setEstadoJogo(getEstadoJogo()+1);
            }
        }
    }

    public void updateBirdPosition() {
            bird.setVelocidadeQueda(bird.getVelocidadeQueda() + 1);
        if (bird.getPosicaoInicialVertial() > 0 || bird.getVelocidadeQueda() < 0) {
            bird.setPosicaoInicialVertial(bird.getPosicaoInicialVertial() - bird.getVelocidadeQueda());
        }
    }

    public void handleGamePlayState() {
        if (getEstadoJogo() == 1) {
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
        }
    }
    public void teste(Batch batch, GlyphLayout layout, String pontuacaoStr){
        //REMOVER A PONTUAÇÃO DA TELA INICIAL
            if(getEstadoJogo()!=0){
            FontManager.getFonteFont().draw(batch, pontuacaoStr, configCamera.getLarguraDispositivo() / 2 - pontuacaos.getLarguraPontuacao() / 2, configCamera.getAlturaDispositivo() - 50);// ATUAL MANTEM PONTUAÇÃO CENTRALIZADO INDEPENDE DE QTS DIGITOS
        }
        // IMAGEM/MENSAGEM DE GAME OVER, tamanho, etc.
            if (getEstadoJogo() == 2) {
            batch.draw(textureManager.getGameOver(), configCamera.getLarguraDispositivo() / 2 - textureManager.getGameOver().getWidth() / 4, configCamera.getAlturaDispositivo() / 2 - textureManager.getGameOver().getHeight() / 4, textureManager.getGameOver().getWidth() / 2, textureManager.getGameOver().getHeight() / 2);

        }
        // MENSAGEM DE INICIAR O JOGO
        if(getEstadoJogo()==0){
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

    public  void maisDisso (Batch batch, SalvarRecord salvarRecord, Song song){
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
            setEstadoJogo(2);
        }
    }

    public void handleGameOverState() {
        if (Gdx.input.justTouched()) {
            if ((bird.getPosicaoInicialVertial() <= 0)) {
                setEstadoJogo(0);
                bird.setVelocidadeQueda(0);
                pontuacaos.setPontuacao(0);
                bird.setPosicaoInicialVertial(configCamera.getAlturaDispositivo() / 2);
                pipes.setPosicaoMovimentoCanoHorizontal(configCamera.getLarguraDispositivo());

            }
        }
    }
 }