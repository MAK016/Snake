import objectdraw.*;
import java.awt.*;
public class GameGrid{

	private Coord[][] grid;
	int l,w;//height and witdh;
	Snake s;
	DrawingCanvas c;
	private Coord[] obstacles;
	
	public GameGrid(int i, int j , Snake snake , DrawingCanvas can){//creates an i*j grid with a snake s
		grid = new Coord[i][j];
		w = j;
		l = i;
		s = snake;
		c = can;
		obstacles = new Coord[0];
	}
	
	public boolean hasSnake(int i , int j){//check if point i j has snake part
		return s.intersects(new Coord(i,j));
	}
	
	public boolean hasSnakeHead(int i , int j){//check if point i j has snake head
		return s.isHead(new Coord(i,j));
	}
	
	public boolean isInGrid(Coord c){//checks if c is in the grid
		//return((c.getX()>=0&&c.getX()<w)&&(c.getY()>=0&&c.getY()<l));
		boolean ret = (c.getX()>=0&&c.getX()<w)&&(c.getY()>=0&&c.getY()<l);
		if(!ret){System.out.println(c + "   " + l +" " +w);}
		return ret;
	}
	
	public DrawingCanvas getCanvas(){
		return c;
	}
	
	public void addObstacle(){
		if(obstacles.length == 0){
			obstacles = new Coord[1];
			obstacles[0] = new Coord((int)(Math.random()*l),(int)(Math.random()*w));
			
		}
		else if(obstacles.length != 0){
		
			Coord[] temp = new Coord[obstacles.length+1];
			for(int i = 0 ; i < obstacles.length; i++){
				temp[i] = obstacles[i];
			}
			System.out.println("Obstacvle added");
			temp[obstacles.length] = new Coord((int)(Math.random()*l),(int)(Math.random()*w));
			obstacles = temp;
			System.out.println(obstacles);
		}
	}
	
	public boolean isObstacle(int i , int j){
		boolean ret = false;
		for(int k = 0 ; k < obstacles.length ; k++){
			
			ret = obstacles[k].equals(new Coord(i,j));
			if(ret){return ret;}
		}
		return ret;
	}
	
	
	
	public Snake getSnake(){
		return s;
	}
	
	
	public String toString(){
		String str = "";
		for(int j = 0 ; j < l ; j++){
			for(int i = 0 ; i < w ; i++){
				if(hasSnakeHead(i,j)){str += "H";}
				else if(hasSnake(i,j)){str += "#";}
				else{str += ".";}
			}
			str +="\n";
		}
		return str;
	}
	
	public void draw(){//draws gamegrid on canvas
		c.clear();
		FilledRect temp;
		new FramedRect(0,0,w*10,l*10,c);
		/*for(int j = 0 ; j < l ; j++){
			for(int i = 0 ; i < w ; i++){
				if(hasSnakeHead(i,j)){
					temp = new FilledRect(10*i , 10*j , 10 , 10 , c);
					temp.setColor(Color.RED);
				}
				else if(hasSnake(i,j)){
					temp = new FilledRect(10*i , 10*j , 10 , 10 , c);
					temp.setColor(Color.GREEN);
				}
				else if(isObstacle(i,j)){
					temp = new FilledRect(10*i , 10*j , 10 , 10 , c);
				}
			}
		}**/
		Coord[] snakeParts = s.getParts();
		temp = new FilledRect(10*snakeParts[0].getX() , 10*snakeParts[0].getY() , 10 , 10 , c);
		temp.setColor(Color.RED);
		for(int i = 1 ; i < snakeParts.length ; i++){
			temp = new FilledRect(10*snakeParts[i].getX() , 10*snakeParts[i].getY() , 10 , 10 , c);
			temp.setColor(Color.GREEN);
		}
		
		for(int i = 0 ; i < obstacles.length ; i++){
			new FilledRect(10*obstacles[i].getX() , 10*obstacles[i].getY() , 10 , 10 , c);
		}
		
	}
	
	public boolean moveSnake(){
		Snake temp = Snake.copy(s);
		if(temp.move()){
		
			if((isInGrid(temp.getHead())&&!isObstacle(temp.getHead().getX(),temp.getHead().getY()))&&(isInGrid(temp.getFrontOfHead())&&!isObstacle(temp.getFrontOfHead().getX(),temp.getFrontOfHead().getY()))){
				s = temp;
				draw();
				//System.out.println(s+"tess");
				return true;
			}
			System.out.println("can move but out of grid or at obstacle");
			
			System.out.println(temp+""+isInGrid(temp.getHead()));
			System.out.println(!isObstacle(temp.getHead().getX(),temp.getHead().getY()));draw();
		}
		return false;
	}
	
	public boolean turnSnakeRight(){
		Snake temp = Snake.copy(s);
		if(temp.turn(1)){
		
			if(isInGrid(temp.getHead())&&!isObstacle(temp.getHead().getX(),temp.getHead().getY())){
				s = temp;
				draw();
				//System.out.println(s);
				return true;
			}
		}
		return false;		
	}
	
	public boolean turnSnakeLeft(){
		Snake temp = Snake.copy(s);
		if(temp.turn(-1)){
		
			if(isInGrid(temp.getHead())&&!isObstacle(temp.getHead().getX(),temp.getHead().getY())){
				s = temp;
				draw();
				//System.out.println(s);
				return true;
			}
		}
		return false;		
	}
	
	
} 
