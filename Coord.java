public class Coord{

	private int x;
	private int y;
	
	public Coord(int i , int j){
		x = i;
		y = j;
	}
	
	public Coord(Coord inital){
		x = inital.getX();
		y = inital.getY();
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public void setX(int i){
		x = i;
	}
	
	public void setY(int j){
		y = j;
	}
	
	public boolean equals(Coord a){
		return(this.x == a.getX() && this.y == a.getY());
	}
	
	public String toString(){
		String str;
		str = "(" + this.x + "," + this.y + ")";
		return str;
	}

}
