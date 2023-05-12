package com.nykis.flappy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Circle;

public class Bird {
    private Circle passarocirculo;
    private float variacao = 0;
    private float velocidadeQueda = 0;
    private float posicaoInicialVertial;
    private float deltaTime;

    public void delta(){
        setDeltaTime(Gdx.graphics.getDeltaTime()); // sem isso a asa do passaro n dabe e os canos nem aparecem
        setVariacao(getVariacao()+getDeltaTime()*8);// sem isso o jogo anda, mas a asa do passaro n se mexe

        if (getVariacao() > 2) {
            setVariacao(0);
        }

    }





    //vou conseguir tirar aquele monte de if da classe principal fazendo igual na classe pontuacao com aqueles metodos doidos
    public Circle getPassarocirculo() {
        return passarocirculo;
    }
    public void setPassarocirculo(Circle passarocirculo) {
        this.passarocirculo = passarocirculo;
    }
    public float getVariacao() {
        return variacao;
    }
    public void setVariacao(float variacao) {
        this.variacao = variacao;
    }
    public float getVelocidadeQueda() {
        return velocidadeQueda;
    }
    public void setVelocidadeQueda(float velocidadeQueda) {this.velocidadeQueda = velocidadeQueda;}
    public float getPosicaoInicialVertial() {
        return posicaoInicialVertial;
    }
    public void setPosicaoInicialVertial(float posicaoInicialVertial) {this.posicaoInicialVertial = posicaoInicialVertial;}
    public float getDeltaTime() {
        return deltaTime;
    }
    public void setDeltaTime(float deltaTime) {
        this.deltaTime = deltaTime;
    }




}
