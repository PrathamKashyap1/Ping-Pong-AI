
/*
 * 
 * -----------This section tells how the program is structured------------------
 * First we have the declarations.
 * then we have the basic initilizations of buttons and ball. We also have keylistener to operate on different inputs for player/players movement.
 * then we have panel focuslistener so that we can always maintain panel focus and listen to keys pressed.
 * Then we declare a Runnable interface to guide how ball should move. Ball if bounce from top edge,bottom edge and playerbars. Game will end if it goes to either ends of Frame.
 * Then we have an interface to implement computer logic. We will run this thread only if we dont have a second player.
 * Then we have a restart logic to assign all default values and repaint the frame as a new one.
 *
 * then we have a section to set frame and panel properties.
 * 
 * 
 */

 import javax.swing.*;
 import java.awt.*;
 import java.awt.event.*;
import java.util.Random;
 public class Main {
 JFrame f;
 ImageIcon img = new ImageIcon("Red-ball.png");
 String player2;
 boolean iscomp = true ,gamestate = true;
 JButton playerButton,restartButton,player2Button;
 JLabel ball;
 int movY,movX;
 int score,sleeptime;
 static Runnable ballmovementlogic , computerlogic;
 Thread ballMovementsThread,computerLogicThread;
 Random randnumber = new Random();

 Main(){
     
     f= new JFrame("AI ping pong");
     playerButton= new JButton("");
     player2Button= new JButton("");
     restartButton = new JButton("Restart");
     
     JPanel p = new JPanel();
     playerButton.setBackground(Color.orange);
     playerButton.setBackground(Color.orange);
     playerButton.setBounds(10,30,30,80);
     player2Button.setBounds(p.getWidth()- 30,30,30,80);
     f.repaint();
     
     ball = new JLabel();
     ball.setBounds(10,50,20,20);
     ImageIcon scaledIcon = new ImageIcon(img.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
     
    ball.setIcon(scaledIcon);
     
     p.setSize(400,400);
     JPanel p1 = new JPanel();
     p.addKeyListener(new KeyListener() {
         public void keyTyped(KeyEvent e) {
             if(e.getKeyChar()=='s'){
                 if(playerButton.getY()<p.getHeight()-playerButton.getHeight()){
                 playerButton.setBounds(30,playerButton.getY()+5,30,80);
                 f.repaint();
                 player2Button.setBounds(player2Button.getX(),player2Button.getY(),30,80);
             }}
             if(e.getKeyChar()=='w'){
                 if(playerButton.getY()>=0){
                     playerButton.setLocation(30, playerButton.getY()-5);
                  f.repaint();
                 }
             }
             
             if(!iscomp){
                if(e.getKeyChar()=='k'){
                 if(player2Button.getY()<p.getHeight()-player2Button.getHeight()){
                 player2Button.setBounds( 400 ,player2Button.getY()+5,30,80);
                 f.repaint();
             }}
             if(e.getKeyChar()=='i'){
                 if(player2Button.getY()>=0){
                     player2Button.setLocation(400 , player2Button.getY()-5);
                  f.repaint();
                 }
             }
             }
         }
         @Override
         public void keyPressed(KeyEvent e) {}
         public void keyReleased(KeyEvent e) {}
     });
 
       
     p.addFocusListener(new FocusListener() {
         public void focusGained(FocusEvent e) {}
         public void focusLost(FocusEvent e) {
             p.grabFocus();
         }
     });

     ballmovementlogic = new Runnable() {
            public void run() {
                 gamestate = true;
                 int counter=0;
                 sleeptime = 100;
                while (gamestate) {
                    int panelWidth = p.getWidth();
                    int panelHeight = p.getHeight();
                    
                    if (ball.getY() <= 0 || ball.getY() >= panelHeight - ball.getHeight()) {
                        movY = -movY;
                    }
                    if (ball.getX() <= playerButton.getX()+30 && ball.getX() >playerButton.getX()+5) {
                        if(ball.getY() > (playerButton.getY()-playerButton.getHeight()/2) && ball.getY() < (playerButton.getY() + playerButton.getHeight()/2)){
                        movX = -movX;
                        score++;
                        if(movY<0){
                        movY = -(randnumber.nextInt(2)+3);}
                        else{movY = randnumber.nextInt(2)+3;
                       }
                        
                    }
                }
                    if(ball.getX() >= player2Button.getX() -60  && ball.getX() < player2Button.getX()-50){
                    if(ball.getY() > (player2Button.getY()-player2Button.getHeight()/2) && ball.getY() < (player2Button.getY() + player2Button.getHeight()/2)){
                        movX = -movX;
                    }}
                    if(ball.getX() >= panelWidth - ball.getWidth()  || ball.getX()<5){
                        if(ball.getX() >= panelWidth - ball.getWidth()){player2 = "player1";}
                        JOptionPane.showMessageDialog(f, new JLabel("Press Restart to play again Your score is "+score) , "Game Over! "+ player2 +" wins", 1);
                        
                           gamestate = false;
                       
                    }
                    try {
                       if(counter==50){
                           counter=0;
                           sleeptime--;
                       }
                        Thread.sleep(sleeptime);
                        counter++;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    ball.setLocation(ball.getX() + movX, ball.getY() + movY);
                    player2Button.setBounds(player2Button.getX(),player2Button.getY(),30,80);
                    f.repaint();
                }
            }
     };

     int choice = JOptionPane.showConfirmDialog(f, new JLabel("DO you want player 2 to be Computer or not? For second player the controls would be 'I' and 'K'"), "Player 2 selection", 1);
     
     

    if(choice == 1){iscomp = false;}
    else{iscomp = true;
    }

     if(iscomp == true){
         player2 = "Computer";
     }
     else{
         player2 = "player 2";
     }
 

     computerlogic = new Runnable() {
        public void run(){

            int targetY=p.getHeight()/2;
            int panelWidth = p.getWidth();
            int panelHeight = p.getHeight();
            player2Button.setBounds(player2Button.getX(),player2Button.getY(),30,80);
            playerButton.setLocation(playerButton.getX(),playerButton.getY());
            int ballX,ballDX,ballDY,ballY,predictedBallX,predictedBallY;
            while (gamestate) {
                
                 ballX = ball.getX();
                 ballY = ball.getY();
                 ballDX = ballX - ball.getX(); 
                 ballDY = ballY - ball.getY(); 
                 predictedBallX = ballX + ballDX;
                 predictedBallY = ballY + ballDY;
                
                 //This logic can work even when the speed of ball increases after sometime
                if(ball.getX()>100) {
                    if(ball.getX()>panelWidth/2 - 10 && movX > 0){
                    targetY = ball.getY();
                }
                else if(movX>0){
                    targetY = ((ballY - ballDY)/(ballX - ballDX))*(player2Button.getX() - ballX) + ballY;
                }
            }
    
                int currentY = player2Button.getY();
                if (currentY < targetY ) {
                    if(targetY - currentY > 5){
                    currentY += 5; }
                player2Button.setLocation(400, currentY);
                } else if (currentY > targetY ) {
                    if(currentY - targetY >5 ){
                    currentY -= 5;}
                 player2Button.setLocation(400, currentY);
                }
                
                try {
                    Thread.sleep(sleeptime- 7 );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        
        }
     };


     //computer logic 
     computerLogicThread = new Thread(computerlogic);
 
      movX=4;
      movY=5;
      
    ballMovementsThread = new Thread(ballmovementlogic);
 
    ballMovementsThread.start();
    if(iscomp){
        computerLogicThread.start();
    }
 
    
    //restarting game with default values
    restartButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e){
            score=0;

            if(gamestate != true){
            gamestate = true;
            sleeptime = 100;
            ballMovementsThread = new Thread(ballmovementlogic);
            ballMovementsThread.start();
            if(iscomp){
            computerLogicThread = new Thread(computerlogic);
            
            computerLogicThread.start();
            }}
            f.revalidate();
        }
     });
    
 
     p.setBackground(Color.cyan);
     f.getContentPane().add(p1,BorderLayout.NORTH);
     p.grabFocus();
     f.getContentPane().add(p,BorderLayout.CENTER);
     p.add(playerButton);
     p.add(player2Button);
     p.add(ball);
     f.add(restartButton,BorderLayout.SOUTH);
     p1.setLayout(new GridLayout(2,2));
     p1.add(new JLabel("W to move up and S to move down "));
     if(!iscomp){
        p1.add(new JLabel("I to move up and K to move down for player 2"));
     }
     f.add(p1,BorderLayout.NORTH);
     f.setSize(500,500);
     f.setVisible(true);
     p.setFocusable(true);
     f.setResizable(false);
     f.setLayout(new BorderLayout());
     f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 }
 public static void main(String[] args) {
     new Main();
 }
 }
 
 
 
