package com.arrabal.koth.proxy;

/**
 * Created by Arrabal on 2/20/2016.
 */
public interface IProxy {

    public abstract ClientProxy getClientProxy();

    public abstract void registerKeyBindings();

    public abstract void initRenderingAndTextures();

    public abstract void registerEventHandlers();


}
