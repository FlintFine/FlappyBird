package com.nykis.flappy;

import com.badlogic.gdx.graphics.Texture;

public class TextureManager {
//    private Texture[] passaros;
//    private Texture fundo;
//    private Texture fundo2;
//    private Texture canoBaixo;
//    private Texture canoTopo;
//    private Texture gameOver;
    private Texture[] passaros = { new Texture("passaro1.png"), new Texture("passaro2.png"), new Texture("passaro3.png") };
    private Texture fundo = new Texture("fundo.png");
    private Texture  fundo2 = new Texture("fundo2.png");
    private Texture   canoBaixo = new Texture("cano_baixo.png");
    private Texture canoTopo = new Texture("cano_topo.png");
    private Texture  gameOver = new Texture("game_over.png");

//    public void TexturaLoader() {
//        passaros = new Texture[3];
//        passaros[0] = new Texture("passaro1.png");
//        passaros[1] = new Texture("passaro2.png");
//        passaros[2] = new Texture("passaro3.png");
//        fundo = new Texture("fundo.png");
//        fundo2 = new Texture("fundo2.png");
//        canoBaixo = new Texture("cano_baixo.png");
//        canoTopo = new Texture("cano_topo.png");
//        gameOver = new Texture("game_over.png");
//    }
    public Texture[] getPassaros() {
        return passaros;
    }

    public void setPassaros(Texture[] passaros) {
        this.passaros = passaros;
    }

    public void setFundo(Texture fundo) {
        this.fundo = fundo;
    }

    public void setFundo2(Texture fundo2) {
        this.fundo2 = fundo2;
    }

    public void setCanoBaixo(Texture canoBaixo) {
        this.canoBaixo = canoBaixo;
    }

    public void setCanoTopo(Texture canoTopo) {
        this.canoTopo = canoTopo;
    }

    public void setGameOver(Texture gameOver) {
        this.gameOver = gameOver;
    }

    public Texture getFundo() {
        return fundo;
    }

    public Texture getFundo2() {
        return fundo2;
    }

    public Texture getCanoBaixo() {
        return canoBaixo;
    }

    public Texture getCanoTopo() {
        return canoTopo;
    }

    public Texture getGameOver() {
        return gameOver;
    }

    public void dispose() {
        getFundo().dispose();
        getFundo2().dispose();
        getCanoBaixo().dispose();
        getCanoTopo().dispose();
        getGameOver().dispose();
        for (Texture texture : getPassaros()) {
            texture.dispose();
        }


    }

}


