package com.arrabal.koth.world;

import com.arrabal.koth.util.LogHelper;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.WorldSavedData;

import java.util.ArrayList;

/**
 * Created by Arrabal on 2/22/2016.
 */
public class ChunkGenerationLogger extends WorldSavedData {

    private final ArrayList<Integer> xCoords;
    private final ArrayList<Integer> zCoords;

    public ChunkGenerationLogger(String name){
        super(name);
        this.xCoords = new ArrayList<Integer>();
        this.zCoords = new ArrayList<Integer>();
    }


    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        int[] xload = nbt.getIntArray("xcoords");
        LogHelper.debug("Chunks logged: " + xload.length);
        if (xload.length > 0)
        {
            this.xCoords.clear();
            for (int i : xload)
            {
                this.xCoords.add(i);
            }
        }
        int[] zload = nbt.getIntArray("zcoords");
        if (zload.length > 0)
        {
            this.zCoords.clear();
            for (int i : zload)
            {
                this.zCoords.add(i);
            }
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        int[] xsave = new int[this.xCoords.size()];
        int[] zsave = new int[this.zCoords.size()];
        for (int i = 0; i < xsave.length; i++)
        {
            xsave[i] = this.xCoords.get(i);
            zsave[i] = this.zCoords.get(i);
        }
        nbt.setIntArray("xcoords", xsave);
        nbt.setIntArray("zcoords", zsave);
    }

    public boolean catchChunkBug(int chunkX, int chunkZ)
    {
        for (int i = 0; i < this.xCoords.size(); i++)
        {
            if (chunkX == this.xCoords.get(i) && chunkZ == this.zCoords.get(i))
            {
                try
                {
                    throw new Exception("SoK caught IWorldGenerator.generate on already generated coordinates: [" + chunkX + ", " + chunkZ + "]");
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }

                return true;
            }
        }

        this.xCoords.add(chunkX);
        this.zCoords.add(chunkZ);
        this.markDirty();

        return false;
    }
}
