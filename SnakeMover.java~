import java.*;
import objectdraw.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class SnakeMover extends ActiveObject implements KeyListener{

	private GameGrid game;
	private int i;
	private boolean running;
	private SnakeGame gui;
	private int speedmult;
	private int timesturned;
	private int timesmoved;
	
	public SnakeMover(GameGrid g , SnakeGame sn , int sp){
		game = g;
		g.getCanvas().addKeyListener(this);
		gui = sn;
		i = 0;
		running = true;
		speedmult = sp;
		timesturned = 0;
		timesmoved = 0;
		start();
	}
	
	public void setSpeed(int i){//multiplies speed
		
		speedmult = i;
		//gui.speed.setValue(i);
		//run();
		
	}
	
	
	public void run(){
		while(running){
			speedmult = gui.getSpeed();
			//System.out.println("called"+speedmult);
			if(i>=(21-speedmult)){
				
				if(game.moveSnake()){
					pause(50);
					i=0;
					timesmoved++;
					System.out.println(game.getSnake());
					if(timesmoved%10==0){game.addObstacle();}
					System.out.println(speedmult);
				}
				else{gui.fail();System.out.println("IHAVEFAILED");break;}
			}
			else{
				pause(50);
				i++;
			}
		}
	}
	
	public void end(){//ends game
		running = false;
	}


	public void keyPressed(KeyEvent e){
		char c = e.getKeyChar();
		if(running){
			if(c=='l'){
				if(game.turnSnakeRight()){
					gui.score();
					timesturned++;
					timesmoved++;
					System.out.println(timesturned);
					if((timesturned%10 == 0) && timesturned/10>=speedmult){
						if(speedmult!=20){speedmult++;}
						System.out.println("speedchnage");
					}
				}
				else{gui.fail();System.out.println("calledrightturn");}
			}
			if(c=='j'){
				if(game.turnSnakeLeft()){
					gui.score();
					timesturned++;
					timesmoved++;
					System.out.println(timesturned);
					if((timesturned%10 == 0) && timesturned/10>=speedmult){
						if(speedmult!=20){speedmult++;}
						System.out.println("speedchnage");
					}
				}
				else{gui.fail();System.out.println("calledrightturn");}
			}
		}
	}
	
	public void keyReleased(KeyEvent e){

	}
	
	public void keyTyped(KeyEvent e){
	}
}
