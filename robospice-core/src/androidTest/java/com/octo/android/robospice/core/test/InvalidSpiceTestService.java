package com.octo.android.robospice.core.test;

import com.octo.android.robospice.SpiceService;
import com.octo.android.robospice.persistence.CacheManager;
import com.octo.android.robospice.persistence.exception.CacheCreationException;

import android.app.Application;

public class InvalidSpiceTestService extends SpiceService {

    @Override
    public CacheManager createCacheManager(Application application) throws CacheCreationException {
        return null;
    }

}
