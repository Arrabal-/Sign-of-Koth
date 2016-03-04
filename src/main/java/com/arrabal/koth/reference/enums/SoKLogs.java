package com.arrabal.koth.reference.enums;

import net.minecraft.util.IStringSerializable;

/**
 * Created by Arrabal on 3/2/2016.
 */
public enum SoKLogs implements IStringSerializable {

    CEDAR(0, "cedar"),
    STRIPPED_CEDAR(1, "cedar_stripped");

    private static final SoKLogs[] META_LOOKUP = new SoKLogs[values().length];
    private final int meta;
    private final String name;
    private final String unlocalizedname;

    private SoKLogs(int meta, String name){
        this(meta, name, name);
    }

    private SoKLogs(int meta, String name, String unlocalizedname){
        this.meta = meta;
        this.name = name;
        this.unlocalizedname = unlocalizedname;
    }

    public int getMetaData(){
        return this.meta;
    }

    public String toString(){
        return this.name;
    }

    public static SoKLogs byMetaData(int meta){
        if (meta < 0 || meta >= META_LOOKUP.length){
            meta = 0;
        }
        return META_LOOKUP[meta];
    }

    @Override
    public String getName() {
        return this.name;
    }

    public String getUnlocalizedname(){
        return this.unlocalizedname;
    }

    static {
        for (SoKLogs soklogs : values()){
            META_LOOKUP[soklogs.getMetaData()] = soklogs;
        }
    }
}
