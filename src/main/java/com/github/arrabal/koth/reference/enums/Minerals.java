package com.github.arrabal.koth.reference.enums;

import net.minecraft.util.IStringSerializable;

/**
 * Created by Arrabal on 3/25/2016.
 */
public enum Minerals implements IStringSerializable {
    ELECTRUM_FRAGMENT(0, "electrum_shard"),
    GREEN_VITRIOL(1, "green_vitriol"),
    SILVER_CHLORIDE(2, "silver_chloride"),
    BRICK_DUST(3, "brick_dust");

    private static final Minerals[] META_LOOKUP = new Minerals[values().length];
    private final int meta;
    private final String name;

    private Minerals(int meta, String name){
        this.meta = meta;
        this.name = name;
    }

    public int getMetaData(){ return this.meta; }

    public static Minerals byMetaData(int meta){
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
        for (Minerals minerals : values()){
            META_LOOKUP[minerals.getMetaData()] = minerals;
        }
    }
}
