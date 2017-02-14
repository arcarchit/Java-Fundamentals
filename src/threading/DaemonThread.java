package threading;

// Examples of Daemon threads are garbage collector
// Daemon thread does not prevent jvm from exiting
public class DaemonThread implements Runnable{
	
	int iterations;
	
	public DaemonThread(int iterations){
		this.iterations = iterations;
	}
	
	@Override
	public void run() {
		for(int i = 0; i<iterations;i++){
			System.out.println(Thread.currentThread().getName()+i);
		}
	}
	
	
	public static void main(String[] args) {
		Thread t1= new Thread(new DaemonThread(1000), "daemon");
		t1.setDaemon(true);
		Thread t2= new Thread(new DaemonThread(10), "regular");
		t1.start();
		t2.start();
	}


}
