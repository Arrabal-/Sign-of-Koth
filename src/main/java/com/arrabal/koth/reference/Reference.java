package com.arrabal.koth.reference;

/**
 * Created by Arrabal on 2/19/2016.
 */
public class Reference {

    public static final String MOD_ID = "SoK";
    public static final String LOWERCASE_MOD_ID = MOD_ID.toLowerCase();
    public static final String MOD_PREFIX = LOWERCASE_MOD_ID + ":";
    public static final String MOD_NAME = "Sign of Koth";
    public static final String FINGERPRINT = "@FINGERPRINT@";
    public static final String MOD_VERSION = "@MOD_VERSION@";
    public static final String DEPENDENCIES = "required-after:Forge@[12.16.0.1826,)";
    public static final String SERVER_PROXY_CLASS = "com.arrabal.koth.proxy.ServerProxy";
    public static final String CLIENT_PROXY_CLASS = "com.arrabal.koth.proxy.ClientProxy";
    public static final String GUI_FACTORY_CLASS = "com.arrabal.koth.client.gui.GuiFactory";
    public static final String CONFIG_FOLDER = "/SignOfKoth/";
}
