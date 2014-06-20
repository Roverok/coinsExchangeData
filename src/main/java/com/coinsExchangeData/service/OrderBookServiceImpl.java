package com.coinsExchangeData.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coinsExchangeData.contract.IOrderBookService;
import com.coinsExchangeData.task.orderbook.BtcEOrderBookTask;
import com.coinsExchangeData.task.orderbook.BterOrderBookTask;


@Service("orderBookService")
public class OrderBookServiceImpl implements IOrderBookService {
	
	@Autowired
	private BtcEOrderBookTask btcEOrderBookTask;
	@Autowired
	private BterOrderBookTask bterOrderBookTask;
	
	@Override
	public void fetch() {
		List<Future<Void>> futures = null;
		List<Callable<Void>> tasks = new ArrayList<>();
		
		tasks.add(btcEOrderBookTask);
		tasks.add(bterOrderBookTask);
		
		ExecutorService executor = Executors.newFixedThreadPool(tasks.size());
		
		try {
			futures = executor.invokeAll(tasks);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		for (Future<Void> f : futures) {
			try {
				f.get();
			} catch (InterruptedException e) {
				System.out.println("InterruptedException");
				e.printStackTrace();
			} catch (ExecutionException e) {
				System.out.println("ExecutionException");
				e.printStackTrace();
			} catch (CancellationException e) {
				System.out.println("CancellationException");
				e.printStackTrace();
			}
		}
		executor.shutdown();
	}
}
