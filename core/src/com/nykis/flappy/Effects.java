package com.nykis.flappy;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.addAction;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

import java.util.ArrayList;
import java.util.List;


public class Effects {
    private static ParticleEffect snowEffect;
    private static ParticleEffect rainEffect;
    private static ParticleEffect flameEffect;

    private boolean hasReset = false;

    private static List<ParticleEffect> effects = new ArrayList<>();



    public static ParticleEffect createSnowEffect() {
        ParticleEffect effect = new ParticleEffect();
        effect.load(Gdx.files.internal("particles/Particle Park Snow Flakes.p"), Gdx.files.internal("particles"));
        effect.scaleEffect(2, 0, 2);
        effect.setPosition(Gdx.graphics.getWidth() / 32, Gdx.graphics.getHeight()+158);
        effect.start();
        effects.add(effect); // Adiciona o efeito na lista

        return effect;

    }

    
    public static ParticleEffect createRainEffect() {
        ParticleEffect effect = new ParticleEffect();
        effect.load(Gdx.files.internal("particles/Particle Park Rain.p"), Gdx.files.internal("particles"));
        effect.scaleEffect(2, 0, 2);
        effect.setPosition(Gdx.graphics.getWidth() / 32, Gdx.graphics.getHeight() + 150);
        effect.start();
        effects.add(effect); // Adiciona o efeito na lista

        return effect; // Antes n tinha return qnd tava tudo na mesma classe
    }

    public static ParticleEffect createFlameEffect() {
        ParticleEffect effect = new ParticleEffect();
        effect.load(Gdx.files.internal("particles/Flame.p"), Gdx.files.internal("particles"));
        effect.scaleEffect(1, 2, 2);
        effect.setPosition(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        effect.scaleEffect(2, 2);
        effect.start();
        effects.add(effect); // Adiciona o efeito na lista

        return effect;
    }

    public static void resetAllEffects() {
        for (ParticleEffect effect : effects) {
            effect.reset();
        }
    }
    public void reseta () {

        snowEffect = Effects.createSnowEffect();
        rainEffect = Effects.createRainEffect();
        flameEffect = Effects.createFlameEffect();
    }

    public static ParticleEffect getSnowEffect() {
        return snowEffect;
    }
    public static ParticleEffect getRainEffect() {return rainEffect;}
    public static ParticleEffect getFlameEffect() {
        return  flameEffect;
    }

    public boolean isHasReset() {
        return hasReset;
    }

    public void setHasReset(boolean hasReset) {
        this.hasReset = hasReset;
    }

//suavizar transicao

    public void dispose () {
snowEffect.dispose();
rainEffect.dispose();
flameEffect.dispose();
createFlameEffect().dispose();
createSnowEffect().dispose();
createSnowEffect().dispose();
    }

}


