package com.octo.android.robospice.retrofit2;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.octo.android.robospice.SpiceService;
import com.octo.android.robospice.request.CachedSpiceRequest;
import com.octo.android.robospice.request.listener.RequestListener;

public abstract class RetrofitSpiceService extends SpiceService {

    private final Map<Class<?>, Object> serviceClassToService = new HashMap<>();

    @SuppressWarnings("unchecked")
    @Override
    public void addRequest(CachedSpiceRequest<?> request, Set<RequestListener<?>> listRequestListener) {
        if (request.getSpiceRequest() instanceof RetrofitSpiceRequest) {
            final RetrofitSpiceRequest retrofitSpiceRequest = (RetrofitSpiceRequest) request.getSpiceRequest();
            final Object retrofitService = getRetrofitService(retrofitSpiceRequest.getServiceClass());
            retrofitSpiceRequest.setService(retrofitService);
        }
        super.addRequest(request, listRequestListener);
    }

    @SuppressWarnings("unchecked")
    private <T> T getRetrofitService(Class<T> serviceClass) {
        T service = (T) serviceClassToService.get(serviceClass);
        if (service == null) {
            synchronized (serviceClassToService) {
                service = (T) serviceClassToService.get(serviceClass);
                if (service == null) {
                    service = createService(serviceClass);
                    serviceClassToService.put(serviceClass, service);
                }
            }
        }
        return service;
    }

    protected abstract <T> T createService(Class<T> serviceClass);
}
