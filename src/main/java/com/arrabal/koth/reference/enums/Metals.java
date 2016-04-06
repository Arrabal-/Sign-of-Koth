package com.arrabal.koth.reference.enums;

import net.minecraft.util.IStringSerializable;

/**
 * Created by Arrabal on 4/5/2016.
 */
public enum Metals implements IStringSerializable {
    PURE_SILVER_NUGGET(0, "puresilver_nugget"),
    PURE_SILVER_INGOT(1, "puresilver_ingot");

    private static final Metals[] META_LOOKUP = new Metals[values().length];
    private final int meta;
    private final String name;

    private Metals(int meta, String name){
        this.meta = meta;
        this.name = name;
    }

    public int getMetaData(){ return this.meta; }

    public static Metals byMetaData(int meta){
        if (meta < 0 || meta >= META_LOOKUP.length){
            meta = 0;
        }
        return META_LOOKUP[meta];
    }

    @Override
    public String getName() {
        return this.name.toLowerCase();
    }

    @Override
    public String toString(){
        return this.getName();
    }

    static{
        for (Metals metals : values()){
            META_LOOKUP[metals.getMetaData()] = metals;
        }
    }
}
