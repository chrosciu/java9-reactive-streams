package com.chrosciu.reactive;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class ReactiveDemo {
    public static void main(String[] args) {
        SubmissionPublisher<Integer> publisher = new SubmissionPublisher<>();
        SimpleSubscriber subscriber = new SimpleSubscriber();
        MapProcessor mapProcessor = new MapProcessor(i -> i * 2);
        publisher.subscribe(mapProcessor);
        mapProcessor.subscribe(subscriber);
        IntStream.range(0, 10).forEach(publisher::submit);
        publisher.close();
        ForkJoinPool.commonPool().awaitQuiescence(10,TimeUnit.SECONDS);
    }
}
