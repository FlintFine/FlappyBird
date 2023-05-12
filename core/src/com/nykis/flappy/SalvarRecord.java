package com.nykis.flappy;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.Preferences;


public class SalvarRecord {
    Pontuacao pontuacaos= new Pontuacao();

    public static void salvarRecorde(int recorde) {
        Preferences prefs = Gdx.app.getPreferences("flappy-bird-prefs");
        if (recorde > prefs.getInteger("recorde2")) {
            prefs.putInteger("recorde2", recorde);
            prefs.flush(); // grava as preferências no arquivo
        }
    }
// ainda ñ está em uso, poderia excluir
        public void atualizaRecord(){
            if (pontuacaos.getPontuacao() > pontuacaos.getRecorde()) {
					pontuacaos.setRecorde(pontuacaos.getPontuacao());
					salvarRecorde(pontuacaos.getRecorde());
				}
        }

        public void carregaeSalva(Pontuacao pontuacaos){
             Preferences prefs = Gdx.app.getPreferences("flappy-bird-prefs"); //SALVAR PONTUAÇÃO APÓS FECHAMENTO// cria um objeto Preferences com o nome "flappy-bird-prefs"
            pontuacaos.setRecorde(prefs.getInteger("recorde2"));} 		// carrega o recorde salvo (ou 0, se não houver nenhum)

}

