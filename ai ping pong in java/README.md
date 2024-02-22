# Project Details
 1. This project is an updated version of my project "ping-pong game". I rewrote that project entirely in Java Swing while also adding the functionality of AI player to make it more interesting. In this projects next version , I am planning to add AI level.
 2. For AI logic , I just calculated the approximate position where the ball might reach using straight lines equations . The computer bar moves there and tries to reach there before the ball. 
 3. As ball speeds up , we can directly follow ball for faster flick of ball.
 4. User can move simply by using "W" and "S" keys.
 5. If we have player2 then they can play with "I" and "K" keys.

# Working Video


# Challenges Faced
1. The first challenge was to make the ball move faster as time goes on. So to make it faster I just reduced Thread waiting time . For every 50 units shift the ball becomes faster by 1millisecond. 
2. The next challenge was to calculate the predicted position when ball moves much faster. During that faster movement time , I just made the computer to follow balls movement to get correct position accurately.
3. Restarting the game just needed to restart both the threads with default values for variables and redrawing the frame. 

# Future Update
1. I would add any updates to truly automate this after learning ML