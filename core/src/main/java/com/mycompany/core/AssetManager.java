package com.mycompany.core;

import com.mycompany.api.IAssetManager;
import org.springframework.stereotype.Service;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Emil
 */
@Service
public class AssetManager implements IAssetManager {

    private final Map<String, byte[]> assets = new HashMap<>();

    @Override
    public void loadAsset(String key, InputStream stream) {
        try (DataInputStream dataStream = new DataInputStream(stream)) {
            byte[] bytes = new byte[dataStream.available()];
            dataStream.readFully(bytes);
            assets.put(key, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Map<String, byte[]> getAssets() {
        return assets;
    }

}
