package com.arrabal.koth.reference.enums;

import net.minecraft.util.IStringSerializable;

/**
 * Created by Arrabal on 3/28/2016.
 */
public enum BowlType implements IStringSerializable {

    ANDESITE(0, "andesite"),
    DIORITE(1, "diorite"),
    GRANITE(2, "granite");

    private static final BowlType[] META_LOOKUP = new BowlType[values().length];
    private final int meta;
    private final String name;

    private BowlType(int meta, String name){
        this.meta = meta;
        this.name = name;
    }

    public int getMetaData(){ return this.meta; }

    public static BowlType byMetaData(int meta){
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
        for (BowlType bowlType : values()){
            META_LOOKUP[bowlType.getMetaData()] = bowlType;
        }
    }
}
