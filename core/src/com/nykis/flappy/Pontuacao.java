package com.nykis.flappy;


    public class Pontuacao {
        private int pontuacao;
        private boolean marcouponto = false;
        private float larguraPontuacao; // manter pontuação centralizada independente do numero de digitos
        private int recorde=0;
        private int intervaloPontuacao=0;
        private int intervaloPontuacaoSprite=0;

        private String pontuacaoStr;

        public String getPontuacaoStr() {
            return pontuacaoStr;
        }

        public void setPontuacaoStr(String pontuacaoStr) {
            this.pontuacaoStr = pontuacaoStr;
        }

        public void update() {
            if (getPontuacao() >= 0 && getPontuacao() < 5) {          //tentar botar um switch
                setIntervaloPontuacao(0);

            } if (getPontuacao() >=5 && getPontuacao() <=10 ) {
                setIntervaloPontuacao(1);
            } if (getPontuacao() >=10 && getPontuacao() <=20 ) {
                setIntervaloPontuacao(3);
            } if (getPontuacao() > 20 && getPontuacao() <=40 ){
                setIntervaloPontuacao(2);
            }if (getPontuacao() > 40 ){
                setIntervaloPontuacao(0);
            }
        }
        public void updateSprite () {
         if (getPontuacao()>=5 && getPontuacao()<10){
             setIntervaloPontuacaoSprite(9);
         }
            if (getPontuacao() >= 10 && getPontuacao() < 20) {    //tentar botar um switch
                setIntervaloPontuacaoSprite(1);
            } if (getPontuacao() >= 20 && getPontuacao() < 30) {
                setIntervaloPontuacaoSprite(2);
            }
            if (getPontuacao() >= 40 && getPontuacao() < 50) {
                setIntervaloPontuacaoSprite(3);
            } if (getPontuacao()==0 || getPontuacao()>50){
                setIntervaloPontuacaoSprite(0);
            }
        }
        public void atualizaTudo() {
           update();
           updateSprite();
          //  pontuacaos.setPontuacao(pontuacaos.getPontuacao()); // >>Set Atualiza o valor da pontuação, se ñ fizer isso a pontuação n atualiza para cair na condição do IF
        }
        public int getPontuacao() { return pontuacao;    }
        public void setPontuacao(int pontuacao) {
            this.pontuacao = pontuacao;
        }
        public boolean getMarcouPonto() {return marcouponto;}
        public void setMarcouPonto(boolean marcouponto) {this.marcouponto = marcouponto;}
        public int getRecorde() {
            return recorde;
        }
        public void setRecorde(int recorde) {
            this.recorde = recorde;
        }
        public float getLarguraPontuacao() {
            return larguraPontuacao;
        }
        public void setLarguraPontuacao(float larguraPontuacao) {this.larguraPontuacao = larguraPontuacao;}
        public int getIntervaloPontuacao() {
            return intervaloPontuacao;
        }

        public void setIntervaloPontuacao(int intervaloPontuacao) {this.intervaloPontuacao = intervaloPontuacao;}

        public void setIntervaloPontuacaoSprite(int intervaloPontuacaoSprite) {this.intervaloPontuacaoSprite = intervaloPontuacaoSprite;}

        public int getIntervaloPontuacaoSprite() {
            return intervaloPontuacaoSprite;
        }}

