import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

public class Game extends JFrame{
	
	public int WIDTH = 640;
	public int HEIGHT = 640;
	public static Snake snake;
	public static Point apple;
	
	public static void main(String[] args) {
		new Game();
	}
	
	public Game() {
		
		snake = new Snake(this);
		add(snake);
		
		setSize(WIDTH, HEIGHT);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(null);
		requestFocus();
		
		addKeyListener(new KeyListen(snake));
	}
	
	public void paint(Graphics g) {
		
		g.clearRect(0, 0, WIDTH, HEIGHT);
		snake.draw(g);
		
	}
}

class KeyListen extends KeyAdapter {

	Snake snake;
	
	public KeyListen(Snake snake) {
		this.snake = snake;
	}
	
    @Override
    public void keyPressed(KeyEvent e) {

        int keycode = e.getKeyCode();

        switch (keycode) {

            case 'W':
                if (snake.direction != "down") {
                    snake.direction = "up";
                }
                break;
            case 'A':
                if (snake.direction != "right") {
                    snake.direction = "left";
                }
                break;
            case 'S':
                if (snake.direction != "up") {
                    snake.direction = "down";
                }
                break;
            case 'D':
                if (snake.direction != "left") {
                    snake.direction = "right";
                }
                break;
        }
    }
}
