package com.nykis.flappy;

import static com.sun.org.apache.xerces.internal.util.DOMUtil.setVisible;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.profiling.GLInterceptor;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.Preferences;
import java.awt.Font;
import java.util.Random;
import javax.swing.JLabel;
import jdk.nashorn.internal.runtime.JSONFunctions;
public class Flappy extends ApplicationAdapter {
	private SpriteBatch batch; // Criada para utilizar animações renderizar
	private Random numeroRandomico;

//	private ShapeRenderer shapeRenderer;
	private int estadoJogo = 0; //0>jogo n iniciado. 1:jogo iniciado
	SalvarRecord salvarRecord = new SalvarRecord();
	Pontuacao pontuacaos = new Pontuacao();
	private Render renderer; // renderer foi criada para armazenar uma instância da classe Render e assim poder chamá-la posteriormente para renderizar os efeitos, sem precisar criar uma nova instância toda vez que precisar utilizar seus métodos
	private ConfiguracoesCamera configCamera; // mesma coisa da variavel acima
	private Song song;// mesma coisa
	private Pipes pipes; // same
	Effects effects= new Effects();
	private String pontuacaoStr;
	GlyphLayout layout = new GlyphLayout();

	public int getEstadoJogo() {
		return estadoJogo;
	}

	public void setEstadoJogo(int estadoJogo) {
		this.estadoJogo = estadoJogo;
	}
	private TextureManager textureManager;
	private Bird bird = new Bird();
	private Logica logica;
	private GameLogic gameLogic;
	@Override
	public void create() {  // DECLARAÇÃO DE CLASSES TEM PRIORIDADE OU O JOGO CRASHA
		textureManager = new TextureManager();
		bird = new Bird();
		pipes = new Pipes();//leia atentamente as linhas de codigo, aqui hj, eu instancias o "pipe.setEspaçoCano..." antes do pipe=new pipe e ñ funfã. A ordem importa!!!
		renderer = new Render(); // instacia classe render
		batch = new SpriteBatch();
		numeroRandomico = new Random();
		bird.setPassarocirculo(new Circle());
		logica = new Logica();
		gameLogic = new GameLogic(logica,bird,effects,pipes,textureManager,song,pontuacaos,configCamera,numeroRandomico);
//		retangulocanoBaixo= new Rectangle();
//		retangulorecanoTopo= new Rectangle();
//		shapeRenderer= new ShapeRenderer();
		configCamera = new ConfiguracoesCamera(1080, 1920);
		configCamera.configurarCamera();
		bird.setPosicaoInicialVertial(configCamera.getAlturaDispositivo() / 2);
		pipes.setPosicaoMovimentoCanoHorizontal(configCamera.getLarguraDispositivo())  ;
		pipes.setEspacoEntreCanos(300);
		// libGDX Quick Tips #1 - Free Type Fonts youutube
		FontManager.initializeFonts(); // buguei com fonts e fonte... funfou, mas manter o olho em caso de bug
		song = new Song();
		song.playMusic();
//SALVAR PONTUAÇÃO APÓS FECHAMENTO// cria um objeto Preferences com o nome "flappy-bird-prefs"
		Preferences prefs = Gdx.app.getPreferences("flappy-bird-prefs");
		// carrega o recorde salvo (ou 0, se não houver nenhum)
		pontuacaos.setRecorde(prefs.getInteger("recorde2"))   ;
  		}

	@Override
		public void render() {
		batch.begin();
		pontuacaos.atualizaTudo(); //talvez a causa do sound seja por falta de algo assim
		configCamera.getCamera().update();
		//Limpar frames anteriores
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		pontuacaoStr = String.valueOf(pontuacaos.getPontuacao()); // sem  isso a pontuacao não atualiza
		layout.setText(FontManager.getFonteFont(), pontuacaoStr); // sem isso a pontuacao não fica no meio centralizada
		pontuacaos.setLarguraPontuacao(layout.width);			 // MUITO IMPORTANTE, MANTEM O SCORE CENTRALIZADO CONFORME AUMENTA OS DIGITOS
		bird.delta();

		if(logica.getEstadoJogo()==0) {
			logica.iniciarJogo(0);
			song.setSomDeColisaoTocado(false);
		}
		if(logica.getEstadoJogo()!=0) {
		    //   logica.iniciarJogo(1);
				gameLogic.updateBirdPosition(); // esse funcionou sei lá pq, o chat gp é zi
				if(logica.getEstadoJogo()==1){
				 gameLogic.handleGamePlayState();
				}else{
				gameLogic.handleGameOverState();
			}
		}
		// Configuração dados de projeção da câmera
		// Renderiza a coisa toda dos objetivos do jogo !
		renderer.renderizarTudo(batch, textureManager, pipes, bird, pontuacaos, configCamera);
		// Mais metodos pra tirar do render
		gameLogic.teste(batch,layout,pontuacaoStr);
		gameLogic.maisDisso(batch,salvarRecord,song);
 			batch.end();
	}
public void onCompletion(long id) {
	song.getSoundColisao().stop();
}
	@Override
	public void resize(int width, int height) {
		configCamera.getViewport().update(width, height);
		super.resize(width, height);
	}

	@Override
	public void dispose () {
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
Effects.getSnowEffect().dispose();
Effects.getRainEffect().dispose();
Effects.getFlameEffect().dispose();
textureManager.getCanoBaixo().dispose();
textureManager.getCanoBaixo().dispose();
FontManager.getZemaFont().dispose();
textureManager.getFundo().dispose();
textureManager.getFundo2().dispose();
	}
}
