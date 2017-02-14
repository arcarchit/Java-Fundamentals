package threading;

public class MyThread extends Thread{

	int start;
	
	public MyThread(int start){
		this.start = start;
	}
	
	public void run(){
		for(int i = 0; i<20;i++){
			System.out.println(start++);
		}
	}
	
	public static void main(String[] args) {
		Thread t1 = new MyThread(1000);
		Thread t2 = new MyThread(2000);
		t1.start();
		t2.start();
	}

}
