package threading;

public class One implements Runnable{

	int start;
	
	public One(int start){
		this.start = start;
	}
	
	@Override
	public void run() {
		for(int i = 0; i<20;i++){
			System.out.println(start++);
		}
	}
	
	public static void main(String[] args) {
		Thread t1 = new Thread(new One(1000));
		Thread t2 = new Thread(new One(2000));
		t1.start();t2.start();
	}

	
}
