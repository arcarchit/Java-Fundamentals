package threading;

import java.util.ArrayDeque;


// Synchronization block is necessary for wait notify
// Reason is you that you want to maintain atomicity for condition/wait/notify instruction
// Ref : http://stackoverflow.com/questions/2779484/why-must-wait-always-be-in-synchronized-block

// illegal monitor state exception : Thread is calling wait or notify without acquiring the monitor

// Make sure producer and consumer should run continuously inside while loop

public class WaitNotify {
	
	public static void main(String[] args) {
		
		ArrayDeque<Integer> q = new ArrayDeque<Integer>();
		int maxSize = 5;
		
		Thread producer = new Thread(new Producer(maxSize, q));
		Thread consumer = new Thread(new Consumer(maxSize, q));
		
		producer.start();
		consumer.start();
	}
	
	
}

class Producer extends Thread{
	int nextItem = 1;
	int maxSize;
	ArrayDeque<Integer> q;
	
	public Producer(int maxSize, ArrayDeque<Integer> q) {
		this.maxSize = maxSize;
		this.q = q;
	}

	@Override
	public void run() {
		
		while(nextItem <=100){	
			
			synchronized (q) {
				while(q.size()==maxSize){
					try {
						System.out.println("Producer is waiting, queue size = "+q.size());
						q.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				q.push(nextItem);
				System.out.println("Pushing in "+nextItem);
				nextItem++;
				q.notifyAll();
			}
			
		}
	}
	
}


class Consumer extends Thread{

	ArrayDeque<Integer> q;
	
	public Consumer(int maxSize, ArrayDeque<Integer> q) {
		this.q = q;
	}

	@Override
	public void run() {
		int a=-1;
		
		while(a!=100){//I had forgot to add thi
			synchronized (q) {
				while(q.size()==0){
					try {
						System.out.println("Consumer is waiting, queue size = "+q.size());
						q.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
	
				a = q.remove();
				System.out.println("Removing "+a);
				q.notifyAll();
			}
		}		
	}
	
	
}

//push remove