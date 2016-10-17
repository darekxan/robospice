package com.octo.android.robospice.retrofit2;

import com.octo.android.robospice.request.SpiceRequest;

public abstract class RetrofitSpiceRequest<Response, Service> extends SpiceRequest<Response> {

    final Class<Service> serviceClass;
    Service service;

    public RetrofitSpiceRequest(Class<Response> clazz, Class<Service> serviceClass) {
        super(clazz);
        this.serviceClass = serviceClass;
    }

    void setService(Service service) {
        this.service = service;
    }

    public Service getService() {
        return service;
    }

    Class<Service> getServiceClass() {
        return serviceClass;
    }
}
