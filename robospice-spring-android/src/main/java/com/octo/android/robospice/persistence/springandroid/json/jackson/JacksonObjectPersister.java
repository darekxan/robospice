package com.octo.android.robospice.persistence.springandroid.json.jackson;

import java.io.File;
import java.io.IOException;

import android.app.Application;
import android.text.TextUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.octo.android.robospice.persistence.exception.CacheCreationException;
import com.octo.android.robospice.persistence.exception.CacheLoadingException;
import com.octo.android.robospice.persistence.exception.CacheSavingException;
import com.octo.android.robospice.persistence.springandroid.SpringAndroidObjectPersister;
import com.octo.android.robospice.utils.CharEncoding;
import com.octo.android.robospice.utils.FileUtils;

public final class JacksonObjectPersister<T> extends SpringAndroidObjectPersister<T> {

    // ============================================================================================
    // ATTRIBUTES
    // ============================================================================================

    final ObjectMapper mJsonMapper;

    // ============================================================================================
    // CONSTRUCTOR
    // ============================================================================================

    public JacksonObjectPersister(Application application, Class<T> clazz, File cacheFolder)
        throws CacheCreationException {
        super(application, clazz, cacheFolder);
        this.mJsonMapper = new ObjectMapper();
    }

    public JacksonObjectPersister(Application application, Class<T> clazz) throws CacheCreationException {
        this(application, clazz, null);
    }

    // ============================================================================================
    // METHODS
    // ============================================================================================

    @Override
    protected T deserializeData(String json) throws CacheLoadingException {
        try {
            return mJsonMapper.readValue(json, getHandledClass());
        } catch (Exception e) {
            throw new CacheLoadingException(e);
        }
    }

    @Override
    protected void saveData(T data, Object cacheKey) throws IOException, CacheSavingException {
        String resultJson;
        // transform the content in json to store it in the cache
        resultJson = mJsonMapper.writeValueAsString(data);

        // finally store the json in the cache
        if (!TextUtils.isEmpty(resultJson)) {
            FileUtils.writeStringToFile(getCacheFile(cacheKey), resultJson, CharEncoding.UTF_8);
        } else {
            throw new CacheSavingException("Data was null and could not be serialized in json");
        }
    }

}
