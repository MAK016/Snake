import java.util.*;
import objectdraw.*;

public class tess extends WindowController{
	
	public void begin(){

		Coord head;
		int i = 50;
		int j = 50;
		int n = 1;
		char c;
		
		head = new Coord(i/2,0);
		Snake s = new Snake(head , n);
		GameGrid game = new GameGrid(i/**rows**/ , j/**cols**/ , s, canvas);
		SnakeMover move = new SnakeMover(game);
		//canvas.addKeyListener(move);
		requestFocusInWindow();
		game.draw();
		System.out.println(s);

		
	}
	
	
	public static void main(String[] args){
		new tess().startController(500 , 500);
		
		
	}
}
