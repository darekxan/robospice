package com.octo.android.robospice.stub;

import com.octo.android.robospice.request.listener.RequestProgress;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RequestListenerWithProgressHistoryStub<T> extends RequestListenerWithProgressStub<T> {

    List<RequestProgress> requestProgressesHistory = Collections.synchronizedList(new ArrayList<RequestProgress>());

    @Override
    public void onRequestProgressUpdate(RequestProgress progress) {
        requestProgressesHistory.add(progress);
        super.onRequestProgressUpdate(progress);
    }

    public List<RequestProgress> getRequestProgressesHistory() {
        return requestProgressesHistory;
    }
}
