package com.octo.android.robospice.request.simple;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import com.octo.android.robospice.request.SpiceRequest;
import com.octo.android.robospice.utils.CharEncoding;
import com.octo.android.robospice.utils.IOUtils;

import roboguice.util.temp.Ln;

public class SimpleTextRequest extends SpiceRequest<String> {

    final String url;

    public SimpleTextRequest(final String url) {
        super(String.class);
        this.url = url;
    }

    // can't use activity here or any non serializable field
    // will be invoked in remote service
    @Override
    public String loadDataFromNetwork() throws Exception {
        try {
            Ln.d("Call web service " + url);
            return IOUtils.toString(new InputStreamReader(new URL(url).openStream(), CharEncoding.UTF_8));
        } catch (final MalformedURLException e) {
            Ln.e(e, "Unable to create URL");
            throw e;
        } catch (final IOException e) {
            Ln.e(e, "Unable to download content");
            throw e;
        }
    }

    // can't use activity here or any non serializable field
    // will be invoked in remote service
    protected final String getUrl() {
        return this.url;
    }

}
