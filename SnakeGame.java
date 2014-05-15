import objectdraw.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
//TODO SPEED FROM SLIDERS Random snake stops TODO ADD ID NAME ETC


public class SnakeGame extends WindowController implements ActionListener, ChangeListener {

	//private JDrawingCanvas canvas;
	private DrawingCanvas can;
	private JLabel highScore;//
	private JPanel topPanel;//
	private JPanel bottomPanel;//
	private JLabel score;
	private JLabel gameOver;
	private JButton newGame;//
	private JButton reset;////
	private JSlider speed;//
	private static SnakeGame fram; 
	
	private GameGrid game;
	private SnakeMover move;
	
	private int cScore;
	private int hScore;
	
	public void begin(){
		//JFrame f = new JFrame("Snake");
		//Container contentPane = f.getContentPane();
		//contentPane.setLayout(new BorderLayout());
		topPanel = new JPanel();
		bottomPanel = new JPanel();
		
		hScore = 0;
		cScore = 0;
		highScore = new JLabel("High Score: " + hScore);
		score = new JLabel("Score: " + cScore);
		gameOver = new JLabel("                                    ");
		topPanel.add(score);
		topPanel.add(highScore);
		topPanel.add(gameOver);
		fram.add (topPanel, BorderLayout.NORTH);
		
		
		newGame = new JButton("New Game");
		reset = new JButton("Reset");
		speed = new JSlider(SwingConstants.HORIZONTAL , 1 , 20 , 1);
		bottomPanel.add(newGame);
		bottomPanel.add(reset);
		bottomPanel.add(speed);
		fram.add (bottomPanel, BorderLayout.SOUTH);
		
		//canvas = new JDrawingCanvas();
		//contentPane.add (canvas, BorderLayout.CENTER);
		can = canvas;
		newGame.addActionListener(this);
		reset.addActionListener(this);
		speed.addChangeListener(this);
		//move = new SnakeMover(game);
		//canvas.addKeyListener(move);
		//canvas.requestFocusInWindow();
		
		//contentPane.validate();
		//f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//f.pack();
		//f.setVisible(true);
		
		
		
	} 
	
	public void fail(){//handles loosing game
		gameOver.setText("                        Game Over");
		//game.draw();
		//topPanel.add(gameOver);
		move.end();
	}
	
	public void score(){
		if(cScore == hScore){
			cScore += 10;
			hScore += 10;
			score.setText("Score: " + cScore);
			highScore.setText("High Score: " + hScore);
		}
		else{
			cScore += 10;
			score.setText("Score: " + cScore);
		}
	}
	
	public void actionPerformed(ActionEvent e){
		
		if(e.getSource() == newGame){
			if(move != null){move.end();}
			gameOver.setText("                                  ");
			cScore = 0;
			score.setText("Score: " + cScore);
			System.out.println("callednewgame");
			Coord head;
			
			int i = can.getHeight();
			System.out.println(i+"IIIIIIIIIIIIIIIIIIIII");
			int j = can.getWidth();
			System.out.println(j+"IIIIIIIIIIIIIIIIIIIII");
			int n = 1;
			char c;
			head = new Coord(j/20,0);
			Snake s = new Snake(head , n);
			game = new GameGrid(i/10/**rows**/ , j/10/**cols**/ , s, can);
			new FilledRect(0,0, i , j , can);
			move = new SnakeMover(game , this , speed.getValue());
			//canvas.addKeyListener(move);
			canvas.requestFocusInWindow();
			game.draw();
			System.out.println(s);
		}
		
		else if(e.getSource() == reset){
			if(move != null){move.end();}
			cScore = 0;
			hScore = 0;
			score.setText("Score: " + cScore);
			highScore.setText("High Score: " + hScore);
			speed.setValue(1);
		}
	}
	
	public void stateChanged(ChangeEvent e) {
  		int i = speed.getValue();
  		System.out.println("called"+i);
  		//move.setSpeed(i);
 		canvas.requestFocusInWindow();
        }
        
        public int getSpeed(){
        	return speed.getValue();
        }



	public static void main(String[] args) 
	{
		fram = new SnakeGame();
		fram.startController(600,600);
		
		

	}
}
