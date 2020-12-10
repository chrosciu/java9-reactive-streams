package com.chrosciu.reactive;

import java.util.concurrent.Flow;

public class SimpleSubscriber implements Flow.Subscriber<Integer> {
    private Flow.Subscription subscription;
    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        System.out.println("onSubscribe");
        this.subscription = subscription;
        subscription.request(1);
    }
    @Override
    public void onNext(Integer item) {
        System.out.println("onNext " + item);
        subscription.request(1);
    }
    @Override
    public void onError(Throwable throwable) {
        System.out.println("onError " + throwable);
    }
    @Override
    public void onComplete() {
        System.out.println("onComplete");
    }
}
