package threading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//It is used by servlets to get a pool of workeds to assign incoming request

public class ThreadPool implements Runnable{

	int start;
	
	public ThreadPool(int start){
		this.start = start;
	}
	
	@Override
	public void run() {
		for(int i = 0; i<20;i++){
			System.out.println(start++);
		}
	}

	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(5);
		for(int i=1;i<=5;i++){
			Runnable worker = new ThreadPool(i*100);
			executor.execute(worker);
		}
		executor.shutdown();
		
		while (executor.isTerminated()!=true){};
	}
}
