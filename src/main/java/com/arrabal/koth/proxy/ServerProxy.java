package com.arrabal.koth.proxy;

/**
 * Created by Arrabal on 2/20/2016.
 */
public class ServerProxy extends CommonProxy {

    @Override
    public ClientProxy getClientProxy() {
        return null;
    }

    @Override
    public void registerKeyBindings() {
        // do nothing
    }

    @Override
    public void initRenderingAndTextures() {
        //do nothing
    }
}
