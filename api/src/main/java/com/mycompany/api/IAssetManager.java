package com.mycompany.api;

import java.io.InputStream;
import java.util.Map;

/**
 * @author Emil
 */
public interface IAssetManager {

    /**
     * @param key Asset name
     */
    void loadAsset(String key, InputStream stream);

    Map<String, byte[]> getAssets();

}
