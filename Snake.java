public class Snake{
	
	Coord[] parts;
	Coord head;
	int length;
	int facing; // 1 for north, 2 for east, 3 for south, 4 for west
	
	//contructs a snake with length n and head at Coord c (snake is vertical and body parts stacked above it) snake facing down
	public Snake(Coord c , int n){
		head = new Coord(c);
		length = n;
		parts = new Coord[length];
		facing = 3;
		
		parts[0] = head;
		for(int i = 1 ; i < length ; i++){
			Coord temp = new Coord(parts[i-1]);
			temp.setY(temp.getY()-1);
			
			parts[i] = temp;
		}
		
		
	}
	
	public Snake (Coord[] a , Coord b, int i , int j){//constructor for making sopies
		parts = a;
		head = b;
		length = i;
		facing = j;
	}
	
	public static Snake copy(Snake s){
		return new Snake(s.parts , s.head , s.length, s.facing);
	}
	
	public Coord getHead(){
		return head;
	}
	
	public int getLength(){
		return length;
	}
	
	public Coord[] getParts(){
		return parts;
	}
	
	public Coord getFrontOfHead(){
		Coord temp = new Coord(head);
		switch(facing){
			case 3:
				temp.setY(temp.getY()+1);
				break;
			case 1:
				temp.setY(temp.getY()-1);
				break;
			case 2:
				temp.setX(temp.getX()+1);
				break;
			case 4:
				temp.setX(temp.getX()-1);
				break;
		}
		return temp;
	}
	
	public String toString(){
		String str = "";
		for(int i = 0 ; i < length ; i++){
			str += parts[i].toString()+"-";
		}
		
		str += "facing " + facing;
		return str;
	}
	
	public boolean intersects(Coord c){
		boolean ret = false;
		for(int i = 0 ; i < length ; i++){
			if(parts[i].equals(c)){ret = true;}
		}
		return ret;
	}
	
	public boolean intersectsExceptTail(Coord c){
		boolean ret = false;
		for(int i = 0 ; i < length - 1 ; i++){
			if(parts[i].equals(c)){ret = true;}
		}
		return ret;
	}	
	
	public boolean isHead(Coord c){
		boolean ret = false;
		ret = c.equals(head);
		return ret;
	}	
	
	public boolean move(){
		
		switch(facing) {
			case 3://facing south
				
				Coord newhead = new Coord(head);
				newhead.setY(newhead.getY()+ 1);
				if(this.intersectsExceptTail(newhead)){
					System.out.println("call"+this);
					return false;
				}
				else{
					head = newhead;
					for(int i = length - 2 ; i >= 0 ; i--){
						parts[i+1] = parts[i];
					}
					parts[0] = newhead;
				}
				return true;
			case 1://facing north
				newhead = new Coord(head);
				newhead.setY(newhead.getY()- 1);
				if(this.intersectsExceptTail(newhead)){
					return false;
				}
				else{
					head = newhead;
					for(int i = length - 2 ; i >= 0 ; i--){
						parts[i+1] = parts[i];
					}
					parts[0] = newhead;
				}
				return true;	
			case 2://facing east
				newhead = new Coord(head);
				newhead.setX(newhead.getX()+ 1);
				if(this.intersectsExceptTail(newhead)){
					return false;
				}
				else{
					head = newhead;
					for(int i = length - 2 ; i >= 0 ; i--){
						parts[i+1] = parts[i];
					}
					parts[0] = newhead;
				}
				return true;
			
			case 4://facing west
				newhead = new Coord(head);
				newhead.setX(newhead.getX()- 1);
				if(this.intersectsExceptTail(newhead)){
					return false;
				}
				else{
				
					head = newhead;
					for(int i = length - 2 ; i >= 0 ; i--){
						parts[i+1] = parts[i];
					}
					parts[0] = newhead;
				}
				return true;
		}
		
		return false;
	}
	
	public boolean turn(int i){//1 for right -1 for left
		
		switch(facing) {
			case 3://facing south
				
				Coord newhead = new Coord(head);
				if(i==1){newhead.setX(newhead.getX() - 1);}
				else{newhead.setX(newhead.getX() + 1);}
				if(this.intersectsExceptTail(newhead)){
					return false;
				}
				else{
					head = newhead;
					Coord[] temp = new Coord[length + 1];
					for(int j =  0; j < length ; j++){
						temp[j+1] = parts[j];
					}
					length++;
					parts = temp;
					parts[0] = newhead;
				}
				if(i==1){facing = 4;}
				if(i==-1){facing = 2;}
				return true;
			
				
			case 1://facing north
				
				newhead = new Coord(head);
				if(i==1){newhead.setX(newhead.getX() + 1);}
				else{newhead.setX(newhead.getX() - 1);}
				if(this.intersectsExceptTail(newhead)){
					return false;
				}
				else{
					head = newhead;
					Coord[] temp = new Coord[length + 1];
					for(int j =  0; j < length ; j++){
						temp[j+1] = parts[j];
					}
					length++;
					parts = temp;
					parts[0] = newhead;
				}
				if(i==1){facing = 2;}
				if(i==-1){facing = 4;}
				return true;
				
			case 2://facing east
				
				newhead = new Coord(head);
				if(i==1){newhead.setY(newhead.getY() + 1);}
				else{newhead.setY(newhead.getY() - 1);}
				if(this.intersectsExceptTail(newhead)){
					return false;
				}
				else{
					head = newhead;
					Coord[] temp = new Coord[length + 1];
					for(int j =  0; j < length ; j++){
						temp[j+1] = parts[j];
					}
					length++;
					parts = temp;
					parts[0] = newhead;
				}
				if(i==1){facing = 3;}
				if(i==-1){facing = 1;}
				return true;
				
			case 4://facing west
				
				newhead = new Coord(head);
				if(i==1){newhead.setY(newhead.getY() - 1);}
				else{newhead.setY(newhead.getY() + 1);}
				if(this.intersectsExceptTail(newhead)){
					return false;
				}
				else{
					head = newhead;
					Coord[] temp = new Coord[length + 1];
					for(int j =  0; j < length ; j++){
						temp[j+1] = parts[j];
					}
					length++;
					parts = temp;
					parts[0] = newhead;
				}
				if(i==1){facing = 1;}
				if(i==-1){facing = 3;}
				return true;

			
		}
	
		return false;
	}
	
	
}
