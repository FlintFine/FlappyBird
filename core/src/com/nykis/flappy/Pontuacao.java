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
            if (pontuacao >= 0 && pontuacao < 2) {          //tentar botar um switch
                intervaloPontuacao = 0;
            } else if (pontuacao >= 2) {
                intervaloPontuacao = 1;
            }
        }
        public void updateSprite () {
            if (pontuacao >= 1 && pontuacao < 10) {    //tentar botar um switch
                intervaloPontuacaoSprite = 1;
            } if (pontuacao >= 10 && pontuacao < 20) {
                intervaloPontuacaoSprite = 2;
            }
            if (pontuacao >= 20 && pontuacao < 30) {
                intervaloPontuacaoSprite = 3;
            } if (pontuacao==0){
                intervaloPontuacaoSprite=0;
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
        public int getIntervaloPontuacaoSprite() {
            return intervaloPontuacaoSprite;
        }}

    //Anteriormente

//        public int update(int intervaloPontuacao) {
//            if (pontuacao >= 0 && pontuacao < 2) {
//                intervaloPontuacao = 0;
//            } else if (pontuacao >= 2) {
//                intervaloPontuacao = 1;
//            }
//            return intervaloPontuacao;
//        }
//        public int updateSprite (int intervaloPontuacaoSprite) {
//            if (pontuacao >= 1 && pontuacao < 10) {
//                intervaloPontuacaoSprite = 1;
//            } if (pontuacao >= 10 && pontuacao < 20) {
//                intervaloPontuacaoSprite = 2;
//            }
//            if (pontuacao >= 20 && pontuacao < 30) {
//                intervaloPontuacaoSprite = 3;
//            } if (pontuacao==0){
//                intervaloPontuacaoSprite=0;
//            }
//            return intervaloPontuacaoSprite;
//        }

//no lugar de atualiza tudo
//		intervaloPontuacao = pontuacaos.update(intervaloPontuacao);
//		intervaloPontuacaoSprite=pontuacaos.updateSprite(intervaloPontuacaoSprite);
//		pontuacaos.setPontuacao(pontuacaos.getPontuacao()); // >>Set Atualiza o valor da pontuação, se ñ fizer isso a pontuação n atualiza para cair na condição do IF

