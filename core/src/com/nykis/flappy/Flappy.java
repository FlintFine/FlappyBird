package com.nykis.flappy;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import java.util.Random;

public class Flappy extends ApplicationAdapter {
	private SpriteBatch batch; // Criada para utilizar animações renderizar
	private Random numeroRandomico;
	private SalvarRecord salvarRecord;
	private Pontuacao pontuacaos;
	private Render renderer; // renderer foi criada para armazenar uma instância da classe Render e assim poder chamá-la posteriormente para renderizar os efeitos, sem precisar criar uma nova instância toda vez que precisar utilizar seus métodos
	private ConfiguracoesCamera configCamera; // mesma coisa da variavel acima
	private Song song;// mesma coisa
	private Pipes pipes; // same
	private Effects effects;
	private GlyphLayout layout;
	private TextureManager textureManager;
	private Bird bird;
	private GameLogic gameLogic;

	@Override
	public void create() {
		textureManager = new TextureManager();
		bird = new Bird();
		pipes = new Pipes(); //leia atentamente as linhas de codigo, aqui hj, eu instancias o "pipe.setEspaçoCano..." antes do pipe=new pipe e ñ funfã. A ordem importa!!
		renderer = new Render();
		batch = new SpriteBatch();
		effects = new Effects();
		pontuacaos = new Pontuacao();
		salvarRecord = new SalvarRecord();
		layout = new GlyphLayout();
		numeroRandomico = new Random();
		song = new Song();
		song.playMusic();
		FontManager.initializeFonts();  			// libGDX Quick Tips #1 - Free Type Fonts youutube
		configCamera = new ConfiguracoesCamera(1080, 1920);
		configCamera.configurarCamera();
		bird.setPosicaoInicialVertial(configCamera.getAlturaDispositivo() / 2);
		pipes.setPosicaoMovimentoCanoHorizontal(configCamera.getLarguraDispositivo());
		pipes.setEspacoEntreCanos(300);
		bird.setPassarocirculo(new Circle());
		gameLogic = new GameLogic(bird, effects, pipes, textureManager, song, pontuacaos, configCamera, numeroRandomico);
		salvarRecord.carregaeSalva(pontuacaos);
	}

	@Override
		public void render() {
		gameLogic.uPs(layout, batch); //configurações essenciais para execução do jogo
		renderer.renderizarTudo(batch, textureManager, pipes, bird, pontuacaos, configCamera);  	// Renderiza a coisa toda dos objetivos do jogo !
		gameLogic.logica(batch,salvarRecord,pontuacaos.getPontuacaoStr(),song,layout);
	}

	@Override
	public void resize(int width, int height) { // importante, muito
		configCamera.getViewport().update(width, height);
	}

	@Override
	public void dispose() {
		try {
			for (Texture t : textureManager.getPassaros()) {
				t.dispose();
			}
			textureManager.getGameOver().dispose();
			FontManager.getMensagemFont().dispose();
			song.getSoundColisao().dispose();
			effects.dispose();
			batch.dispose();
			song.getSound().dispose();
			song.getMusic().dispose();
			textureManager.getCanoBaixo().dispose();
			textureManager.getCanoTopo().dispose();
			FontManager.getZemaFont().dispose();
			textureManager.getFundo().dispose();
			textureManager.getFundo2().dispose();
			textureManager.getFundo3().dispose();
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.dispose();
	}
}
