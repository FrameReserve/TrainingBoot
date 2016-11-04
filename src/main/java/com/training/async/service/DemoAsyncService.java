package com.training.async.service;

import java.util.concurrent.Future;

import org.springframework.scheduling.annotation.Async;

public interface DemoAsyncService {

	@Async
    public Future<String> doTaskOne() throws Exception;

	@Async
    public Future<String> doTaskTwo() throws Exception;

	@Async
    public Future<String> doTaskThree() throws Exception;
}
