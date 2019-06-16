package com.example.miwoklanguage;

public class Word {
    private String def; //For default translation
    private String miw; //For Miwok translation
    //private int imageres; //For image resource id
    private int imageres = NO_IMAGE_PROVIDED;

    private static final int NO_IMAGE_PROVIDED = -1;

    private int audioresid;

   public Word(String mdef, String mmiw, int maudioresid){
        def = mdef;
        miw = mmiw;
        audioresid = maudioresid;
    }

    public Word(String mdef, String mmiw, int mimageres, int maudioresid){
        def = mdef;
        miw = mmiw;
        imageres = mimageres;
        audioresid = maudioresid;
    }


    public String getDef(){
        return def;
    }

    public String getMiw(){
        return miw;
    }

    public int getImageres() {
        return imageres;
    }

    public boolean hasImage(){
       return imageres != NO_IMAGE_PROVIDED;
    }

    public int getAudioresid() {
        return audioresid;
    }
}
