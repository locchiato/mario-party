package entities;

public class EsperarParaRedibujarThread extends Thread{
	@Override
	public void run() {
		try {
			sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
