package com.nykis.flappy;

import com.badlogic.gdx.graphics.Texture;

public class TextureManager {
//    private Texture[] passaros;
//    private Texture fundo;
//    private Texture fundo2;
//    private Texture canoBaixo;
//    private Texture canoTopo;
//    private Texture gameOver;
    private Texture[] passaros = { new Texture("passaro1111.png"), new Texture("passaro 1112.png"), new Texture("passaro3.png") };
    private Texture fundo = new Texture("fundo.png");
    private Texture  fundo2 = new Texture("fundo2.png");
    private Texture  fundo3 = new Texture("fundo3.png");
    private Texture  fundo4 = new Texture("fundo4.png");
    private Texture   canoBaixo = new Texture("cano_baixo.png");
    private Texture canoTopo = new Texture("cano_topo.png");
    private Texture  gameOver = new Texture("game_over.png");

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
    public void setFundo3(Texture fundo3) {this.fundo3 = fundo3;}
    public void setFundo4(Texture fundo4) {this.fundo4 = fundo4;}

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
    public Texture getFundo4() {return fundo4;}
    public Texture getFundo3() {return fundo3;} // atenção com return no ctrl d

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
        getFundo3().dispose();
        getCanoBaixo().dispose();
        getCanoTopo().dispose();
        getGameOver().dispose();
        for (Texture texture : getPassaros()) {
            texture.dispose();
        }


    }

}


