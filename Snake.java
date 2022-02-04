import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Snake extends JPanel {
	
	public Game frame;
	public Color playerCol = new Color(200, 150, 100);
	public static final int playerSize = 32;
	
	int offX, offY;
	public static int gridW = 17;
	public static int gridH = 17;
	
	public ArrayList<Point> snake = new ArrayList<>(1);
	public String direction = "right";
	
	public boolean gameOver = false;
	
	public Snake(Game frame) {
		this.frame = frame;
		new SnakeThread(frame, this).start();
		snake.add(new Point(gridH/2, gridH/2));
		Game.apple = new Point((int)(Math.random() * gridW), (int)(Math.random() * gridH));
	}
	
	public void draw(Graphics g) {
		
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, frame.WIDTH, frame.HEIGHT);
		makeGrid(g);
		makeSnake(g);
		makeApple(g);
		if(!checkSnake()) {
			gameOver = true;
			gameOver(g);
		}
		checkApple();
		
	}
	
	public void checkApple() {
		for(int i = 0; i < snake.size(); i++) {
			Point check = snake.get(i);
			if(check.equals(Game.apple)) {
				snake.add(Game.apple);
				Game.apple = new Point((int)(Math.random() * gridW), (int)(Math.random() * gridH));
				return;
			}
		}
	}
	
	public void makeApple(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(Game.apple.x * playerSize + offX + 1, Game.apple.y * playerSize + offY + 1, playerSize-2, playerSize-2);
	}
	
	public boolean checkSnake() {
		for(int i = 0; i < snake.size(); i++) {
			Point check = snake.get(i);
			for(int j = i + 1; j < snake.size(); j++) {
				if(check.equals(snake.get(j))) {
					System.out.println("Hello");
					return false;
				}
			}
		}
		return true;
	}
	
	public void move() {
		int xChange = 0;
		int yChange = 0;
		switch(direction) {
		case "up":
			yChange = -1;
			break;
		case "down":
			yChange = 1;
			break;
		case "left":
			xChange = -1;
			break;
		case "right":
			xChange = 1;
			break;
		}
		Point p = snake.get(0);
		for(int i = snake.size() - 1; i > 0; i--) {
			snake.set(i, snake.get(i-1));
		}
		Point newP = new Point(p.x + xChange, p.y + yChange);
		if(newP.x >= gridW) {
			newP.setLocation(0, newP.y);
		}
		if(newP.x < 0) {
			newP.setLocation(gridW-1, newP.y);
		}
		if(newP.y >= gridH) {
			newP.setLocation(newP.x, 0);
		}
		if(newP.y < 0) {
			newP.setLocation(newP.x, gridH-1);
		}
		snake.set(0, newP);
	}
	
	public void makeSnake(Graphics g) {
		g.setColor(playerCol);
		for(int i = 0; i < snake.size(); i++) {
			Point p = snake.get(i);
			g.fillRect(p.x * playerSize + offX + 1, p.y * playerSize + offY + 1, playerSize-2, playerSize-2);
		}
	}
	
	public void makeGrid(Graphics g) {
		g.setColor(Color.WHITE);
		offX = (frame.WIDTH - (gridW * playerSize)) / 2;
		offY = (frame.HEIGHT - (gridH * playerSize - 15)) / 2;
		for (int i = 0; i < gridW; i++) {
			for (int j = 0; j < gridH; j++) {
				g.drawRect(i * playerSize + offX, j * playerSize + offY, playerSize, playerSize);
			}
		}
	}
	
	public void gameOver(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, frame.WIDTH, frame.HEIGHT);
		g.setFont(new Font("Arial", Font.BOLD, 42));
		g.setColor(Color.WHITE);
		g.drawString("You died! Your score was " + snake.size(), offX, frame.HEIGHT/2);
	}
	
}
