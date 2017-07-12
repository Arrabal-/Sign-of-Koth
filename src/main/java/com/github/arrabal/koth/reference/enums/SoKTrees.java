package com.github.arrabal.koth.reference.enums;

import net.minecraft.util.IStringSerializable;

/**
 * Created by Arrabal on 3/2/2016.
 */
public enum SoKTrees implements IStringSerializable {
    BEECH(0, "beech"),
    CEDAR(1, "cedar"),
    HEMLOCK(2, "hemlock"),
    SUGAR_MAPLE(3, "sugarmaple");

    private static final SoKTrees[] META_LOOKUP = new SoKTrees[values().length];
    private final int meta;
    private final String name;
    //private final String unlocalizedname;

    private SoKTrees(int meta, String name){
        this.meta = meta;
        this.name = name;
    }

    public int getMetaData(){
        return this.meta;
    }

    public static SoKTrees byMetaData(int meta){
        if (meta < 0 || meta >= META_LOOKUP.length){
            meta = 0;
        }
        return META_LOOKUP[meta];
    }

    @Override
    public String getName(){
        return this.name.toLowerCase();
    }

    @Override
    public String toString(){
        return this.getName();
    }

    static{
        for (SoKTrees soktrees : values()){
            META_LOOKUP[soktrees.getMetaData()] = soktrees;
        }
    }
}
