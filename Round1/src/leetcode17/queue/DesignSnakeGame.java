package leetcode17.queue;

import java.util.LinkedList;

/**
 * 353. Design a Snake game that is played on a device with screen size = width x height. Play the game online if you are not familiar with the game.

The snake is initially positioned at the top left corner (0,0) with length = 1 unit.

You are given a list of food's positions in row-column order. When a snake eats the food, its length and the game's score both increase by 1.

Each food appears one by one on the screen. For example, the second food will not appear until the first food was eaten by the snake.

When a food does appear on the screen, it is guaranteed that it will not appear on a block occupied by the snake.

Example:
Given width = 3, height = 2, and food = [[1,2],[0,1]].

Snake snake = new Snake(width, height, food);

Initially the snake appears at position (0,0) and the food at (1,2).

|S| | |
| | |F|

snake.move("R"); -> Returns 0

| |S| |
| | |F|

snake.move("D"); -> Returns 0

| | | |
| |S|F|

snake.move("R"); -> Returns 1 (Snake eats the first food and right after that, the second food appears at (0,1) )

| |F| |
| |S|S|

snake.move("U"); -> Returns 1

| |F|S|
| | |S|

snake.move("L"); -> Returns 2 (Snake eats the second food)

| |S|S|
| | |S|

snake.move("U"); -> Returns -1 (Game over because snake collides with border)
 */
// https://discuss.leetcode.com/topic/47173/share-my-easy-java-solution
public class DesignSnakeGame {

	    int len = 0;
	    int width = 0;
	    int height = 0;
	    int[][] food = null;
	    LinkedList<Position> snake = null;
	    
	    /** Initialize your data structure here.
	        @param width - screen width
	        @param height - screen height 
	        @param food - A list of food positions
	        E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0]. */
	    public DesignSnakeGame(int width, int height, int[][] food) {
	        this.width = width;
	        this.height = height;
	        this.food = food;
	        
	        snake = new LinkedList<Position>();
	        snake.addFirst(new Position(0, 0)); // The beginning point is 0,0, not actually start yet, wait for move()
	        len = 0; // not started yet. This will also be the index of next food
	    }
	    
	    /** Moves the snake.
	        @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down 
	        @return The game's score after the move. Return -1 if game over. 
	        Game over when snake crosses the screen boundary or bites its body. */
	    public int move(String direction) {
	        Position cur = new Position(snake.get(0).x, snake.get(0).y);
	        
	        switch (direction) {
	            case "U":
	                cur.x--;
	                break;
	            case "D":
	                cur.x++;
	                break;
	            case "L":
	                cur.y--;
	                break;
	            case "R":
	                cur.y++;
	                break;
	        } // The new Position
	        
	        // If hit wall 
	        if (cur.x < 0 || cur.x >= height || cur.y < 0 || cur.y >= width) {
	            return -1;
	        }
	        
	        // If hit itself, not include head or tail(will move)
	        for (int i = 1; i < snake.size() - 1; i++) {
	            Position p = snake.get(i);
	            if (p.isEqual(cur)) {
	                return -1;
	            }
	        }
	        
	        snake.addFirst(cur); // 甭管吃没吃到food，都得往前挪动. 这边只需add一个头
	        // If hit food, increase length
	        if (len < food.length) {
	            if (cur.x == food[len][0] && cur.y == food[len][1]) {
	                Position foodPos = new Position(food[len][0], food[len][1]);
	                if (foodPos.isEqual(cur)) { // Move到的下一个点正好是food的那个点
	                    len++;
	                }
	            }
	        }
	        
	        // 如果没吃到food, snake.size() 就比较大了, Remove the tail
	        if (snake.size() > len + 1) { // why need to add 1 here?
	            snake.removeLast();
	        }
	        
	        return len;
	    }
	    
	    class Position {
	        int x;
	        int y;
	        
	        Position (int x, int y) {
	            this.x = x;
	            this.y = y;
	        }
	        
	        // Can also use hashcode() equals()        
	        boolean isEqual(Position p) {
	            return p.x == x && p.y == y;
	        }
	    }
	}

	/**
	 * Your SnakeGame object will be instantiated and called as such:
	 * SnakeGame obj = new SnakeGame(width, height, food);
	 * int param_1 = obj.move(direction);
	 */
