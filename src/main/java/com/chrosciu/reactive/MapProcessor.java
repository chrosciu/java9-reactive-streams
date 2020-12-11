package com.chrosciu.reactive;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;
import java.util.function.Function;

class MapProcessor extends SubmissionPublisher<Integer> implements Flow.Processor<Integer, Integer> {
    private final Function<Integer, Integer> mapper;
    private Flow.Subscription subscription;

    public MapProcessor(Function<Integer, Integer> mapper) {
        this.mapper = mapper;
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1);
    }

    @Override
    public void onNext(Integer item) {
        submit(mapper.apply(item));
        subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
        closeExceptionally(throwable);
    }

    @Override
    public void onComplete() {
        close();
    }
}
