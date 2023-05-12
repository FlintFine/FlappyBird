package com.nykis.flappy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.Array;

public class FontManager {
    private static FreeTypeFontGenerator fontGenerator;
    private static FreeTypeFontGenerator.FreeTypeFontParameter fontParameter;
    private static BitmapFont mensagemFont;
    private static BitmapFont zemaFont;
    private static BitmapFont fonteFont;

    public static void initializeFonts() {
        mensagemFont = createFont(3F, Color.WHITE);
        zemaFont = createFont(3F, Color.FIREBRICK);
        fonteFont = createFonteFont();
    }

    public static BitmapFont createFont(float scale, Color color) {
        BitmapFont font = new BitmapFont();
        font.setColor(color);
        font.getData().setScale(scale);
        return font;
    }

    public static BitmapFont createFonteFont() {
        fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("b.ttf"));
        fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter.size = 100;
        fontParameter.borderWidth = 5;
        fontParameter.borderColor = new Color().BLACK;
        fontParameter.color = Color.PURPLE;
        BitmapFont fonte = fontGenerator.generateFont(fontParameter);
        return fonte;
    }

    public static BitmapFont getMensagemFont() {
        return mensagemFont;
    }

    public static BitmapFont getZemaFont() {
        return zemaFont;
    }

    public static BitmapFont getFonteFont() {
        return fonteFont;
    }

    public static Array<BitmapFont> getFontes() {   // não está em uso mas poderia estar
        Array<BitmapFont> fontes = new Array<BitmapFont>();
        fontes.add(mensagemFont);
        fontes.add(zemaFont);
        fontes.add(fonteFont);
        return fontes;
    }
}


//    public static BitmapFont createFonteFont() {
//        return createFont(2F, Color.WHITE);
//    }
//}



//public class FontManager {
//    private static final float DEFAULT_SCALE = 1f; // Escala padrão das fontes
//
//    public static BitmapFont createFonteFont() {
//        BitmapFont font = new BitmapFont(Gdx.files.internal("fonte.fnt")); // Cria a instância da fonte
//        font.setColor(Color.WHITE); // Define a cor da fonte
//        font.getData().setScale(DEFAULT_SCALE); // Define a escala padrão
//        return font;
//    }
//
//    public static BitmapFont createMensagemFont() {
//        BitmapFont font = new BitmapFont(Gdx.files.internal("mensagem.fnt"));
//        font.setColor(Color.GRAY);
//        font.getData().setScale(DEFAULT_SCALE * 1.5f); // Aumenta a escala em 50%
//        return font;
//    }
//
//    public static BitmapFont createZemaFont() {
//        BitmapFont font = new BitmapFont(Gdx.files.internal("zema.fnt"));
//        font.setColor(Color.FIREBRICK);
//        font.getData().setScale(DEFAULT_SCALE * 3f); // Aumenta a escala em 200%
//        return font;
//    }
//}
