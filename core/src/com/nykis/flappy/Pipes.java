package com.nykis.flappy;

import com.badlogic.gdx.math.Rectangle;

public class Pipes {

    private float alturaEntreCanosRandomica;
    private float posicaoMovimentoCanoHorizontal;
    private float espacoEntreCanos;
    private Rectangle retangulorecanoTopo;
    private Rectangle retangulocanoBaixo;

    public float getAlturaEntreCanosRandomica() {
        return alturaEntreCanosRandomica;
    }
    public void setAlturaEntreCanosRandomica(float alturaEntreCanosRandomica) {this.alturaEntreCanosRandomica = alturaEntreCanosRandomica;}
    public float getPosicaoMovimentoCanoHorizontal() {
        return posicaoMovimentoCanoHorizontal;
    }
    public void setPosicaoMovimentoCanoHorizontal(float posicaoMovimentoCanoHorizontal) {this.posicaoMovimentoCanoHorizontal = posicaoMovimentoCanoHorizontal;}
    public float getEspacoEntreCanos() {
        return espacoEntreCanos;
    }
    public void setEspacoEntreCanos(float espacoEntreCanos) {this.espacoEntreCanos = espacoEntreCanos;}
    public Rectangle getRetangulorecanoTopo() {
        return retangulorecanoTopo;
    }
    public void setRetangulorecanoTopo(Rectangle retangulorecanoTopo) {this.retangulorecanoTopo = retangulorecanoTopo;}
    public Rectangle getRetangulocanoBaixo() {
        return retangulocanoBaixo;
    }
    public void setRetangulocanoBaixo(Rectangle retangulocanoBaixo) {this.retangulocanoBaixo = retangulocanoBaixo;}
}
