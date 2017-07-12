package com.github.arrabal.koth.reference.enums;

import net.minecraft.util.IStringSerializable;

/**
 * Created by Arrabal on 3/27/2016.
 */
public enum SidingType implements IStringSerializable {

    CEDAR_SIDING(0, "cedar_siding"),
    WEATHERED_SIDING(1, "weathered_cedar_siding"),
    CEDAR_SHAKE(2, "cedar_shake_siding"),
    WEATHERED_SHAKE(3, "weathered_cedar_shake_siding");

    private static final SidingType[] META_LOOKUP = new SidingType[values().length];
    private final int meta;
    private final String name;

    private SidingType(int meta, String name){
        this.meta = meta;
        this.name = name;
    }

    public int getMetaData() { return this.meta; }

    public String toString() { return this.getName(); }

    public static SidingType byMetaData(int meta){
        if (meta < 0 || meta >= META_LOOKUP.length){
            meta = 0;
        }
        return META_LOOKUP[meta];
    }

    @Override
    public String getName() { return this.name.toLowerCase(); }

    static {
        for (SidingType sidingType : values()){
            META_LOOKUP[sidingType.getMetaData()] = sidingType;
        }
    }
}
