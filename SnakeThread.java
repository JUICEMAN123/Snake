import java.awt.Point;

public class SnakeThread extends Thread{
	
	public Game frame;
	public Snake snake;
	
	public SnakeThread(Game frame, Snake snake) {
		this.frame = frame;
		this.snake = snake;
	}
	
	public void run() {
		while(true) {
			try {
				if(snake.gameOver) {
					break;
				}
				if(frame.getWidth() < snake.gridW * snake.playerSize + 20) {
					frame.setSize(snake.gridW * snake.playerSize + 20, frame.getHeight());
				}
				if(frame.getHeight() < snake.gridH * snake.playerSize + 50) {
					frame.setSize(frame.getWidth(), snake.gridH * snake.playerSize + 50);
				}
				frame.WIDTH = frame.getWidth();
				frame.HEIGHT = frame.getHeight();
				snake.move();
				frame.repaint();
				Thread.sleep(100);
			} 
			catch(InterruptedException e) {
				
			}
		}
		
		try {
			Thread.sleep(3000);
			frame.dispose();
			new Game();
		} 
		catch (InterruptedException e) {
			
		}
	}
	
}
