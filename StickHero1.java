import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
/*
 <applet width=450 height=800 code="StickHero1.class"> </applet> 
*/
public class StickHero1 extends Applet implements MouseListener, MouseMotionListener, Runnable {
    int width, height;
    int mx, my; // the mouse coordinates
    boolean isElongating = false;
    boolean isCharacterMoving = false;
    boolean isFrameMoving = false;
    boolean isGameOver=false;
    int playerPosition = 0;
//    int bonusPosition = 0;
    boolean allowedToClick1 = true;
    boolean allowedToClick2 = false;
    boolean bonusTaken=false;
    boolean isDown=false;
//    boolean isClicked=false;
    int nextBoxWidth = (int)((Math.random() * (100 - 30)) + 30);
    int nextBoxWidth2 = (int)((Math.random() * (100 - 30)) + 30);
    int length = 0;
    Thread t = null;
    int frameLength = 0;
    int movedLeft = 0;
    boolean isStill = true;
    int score = 0;
    int bestScore = 0;
    int gap = 300;
    boolean fall = false;
    boolean isFalling = false;
    int playerPositionY = 0;
    int intervalSpeed = 5;
    int intervalFall = 1;
    int bonusX = (int)((Math.random() * (300 - 50)) + 50);
    int dummy = 0;
    int downValue = 0;


    //isStill
    //isElong
    //isCharacterMov
    //isFrmMov


    public void init() {

        Frame title = (Frame) this.getParent().getParent();
        title.setTitle("Stick Hero");

        width = getSize().width;
        height = getSize().height;
        setBackground(Color.black);
        setForeground(Color.white);

        mx = 0;
        my = height / 2;
        addMouseListener(this);
        addMouseMotionListener(this);

    }

    public void mouseEntered(MouseEvent e) {
        // called when the pointer enters the applet's rectangular area
    }

    public void mouseExited(MouseEvent e) {
        // called when the pointer leaves the applet's rectangular area
    }



    public void mouseClicked(MouseEvent e) {    		
//    	if(isCharacterMoving==true && isDown==false) {
//    		downValue = 0;
//    		isDown = true;
//    	
//    	}
//         	
//         else if(isCharacterMoving==true && isDown==true) {
//         	downValue = 10;
//         	isDown=false;
//         }
        // called after a press and release of a mouse button
        // with no motion in between
        // (If the user presses, drags, and then releases, there will be
        // no click event generated.)
    }
    public void mousePressed(MouseEvent e) { // called after a button is pressed down
    	if(isCharacterMoving==true && isDown==false) {
    		downValue = 0;
    		isDown = true;
    	
    	}
         	
         else if(isCharacterMoving==true && isDown==true) {
         	downValue = 10;
         	isDown=false;
         }
        if (allowedToClick1 == true) {
            isStill = false;
            isElongating = true;
            allowedToClick1 = false;
            allowedToClick2 = true;


            // setBackground(Color.gray);
            //repaint();
            t = new Thread(this);
            t.start();
        }
        e.consume();


        // "Consume" the event so it won't be processed in the
        // default manner by the source which generated it.
    }
    public void mouseReleased(MouseEvent e) { // called after a button is released
        if (allowedToClick2 == true) {
            isElongating = false;
            isCharacterMoving = true;
            if (length < (gap - 30) || length > (gap + nextBoxWidth - 30)) {
                fall = true;
            } else {
                fall = false;
            }
            System.out.println("Is going to fall? " + fall);
            allowedToClick2 = false;
            // t = null;
            // t = new Thread( this );
            // t.start();

            // setBackground(Color.black);
            // repaint();
        }
        e.consume();
    }
    public void mouseMoved(MouseEvent e) { // called during motion when no buttons are down
        // mx = e.getX();
        // my = e.getY();
        // showStatus("Mouse at (" + mx + "," + my + ")");
        // repaint();
        // e.consume();
    }
    public void mouseDragged(MouseEvent e) { // called during motion with buttons down
        // mx = e.getX();
        // my = e.getY();
        // showStatus("Mouse at (" + mx + "," + my + ")");
        // repaint();
        // e.consume();
    }
    public void paint(Graphics g) {
        showStatus("Greetings");
        // g.drawString("Score: " + score, 50, 50);
        if(isGameOver==true )
        {
        	g.setColor(Color.red);
            g.fillRect(0,0,1000,1000);
            g.setColor(Color.black);
            Font fEnd=new Font("Arial",Font.BOLD,28);
            g.setFont(fEnd);
            g.drawString("GAME OVER",100,250);
            isGameOver=false;  
        } if(bonusTaken==false) {
        	g.setColor(Color.blue);
        	if(isFrameMoving==false)
        		g.fillOval(bonusX-10,my,10,10);//bonus graphic
        	if((playerPosition+10)==(bonusX) && downValue==10) {
            	bonusTaken = true;
            	score++;
            	System.out.println("Collected");
            	repaint();
            }
        } if (bonusTaken == true) {
//        	g.setColor(Color.blue);
//            g.fillOval(bonusX,my-10,10,10);
//            if(playerPosition==bonusX) {
//            	score++;
//            	g.setColor(Color.black);
//                g.fillOval(bonusX,my-10,10,10);
//                g.setColor(Color.blue);
//            }
    	} if (isStill == true) {
    	
            g.drawString("Score: " + score, 50, 50);
            g.drawString("Best Score: " + bestScore, 200, 50);
            g.setColor(Color.white);
            g.fillRect(mx, my, 30, my);
            g.fillRect(mx + gap, my, nextBoxWidth, my);
            System.out.println(nextBoxWidth);
            g.setColor(Color.green);
            g.fillOval(mx + 10 + playerPosition, my - 10, 10, 10);
            bonusTaken=false;
//            g.fillOval(280,my,10,10);
        } else if (isElongating == true) {
            g.setColor(Color.white);
            g.fillRect(mx, my, 30, my);
            g.fillRect(mx + gap, my, nextBoxWidth, my);
            g.setColor(Color.green);
            g.fillOval(mx + 10 + playerPosition, my - 10, 10, 10);
            g.setColor(Color.white);
            g.drawLine(mx + 30, my, mx + 30, my - length);
        } else if (isCharacterMoving == true) {
//            System.out.println(playerPosition);
            g.setColor(Color.white);
            g.fillRect(mx, my, 30, my);
            g.fillRect(mx + gap, my, nextBoxWidth, my);
            g.setColor(Color.white);
            g.drawLine(mx + 30, my, mx + 30 + length, my);
            g.setColor(Color.green);
            g.fillOval(mx + 10 + playerPosition, my - 10 + downValue, 10, 10);
            g.setColor(Color.black);
//            if(isDown==true && (playerPosition + 10) == gap)
//            	fall = true;
        } else if (isFrameMoving == true) {
            g.setColor(Color.white);
//        	g.fillOval(bonusX - 10 - movedLeft,my,10,10);//bonus graphic
            g.fillRect(mx - movedLeft, my, 30, my);
            g.fillRect(mx + gap - movedLeft, my, nextBoxWidth, my);
            g.fillRect(mx + gap - movedLeft + gap + nextBoxWidth - 30, my, nextBoxWidth2, my);
            g.setColor(Color.green);
            g.fillOval(mx + 10 + playerPosition - movedLeft, my - 10, 10, 10);
            g.setColor(Color.white);
            g.drawLine(mx + 30 - movedLeft, my, mx + 30 + length - movedLeft, my);
            g.setColor(Color.black);
        } else if (isFalling == true) {
            g.setColor(Color.white);
            g.fillRect(mx, my, 30, my);
            g.fillRect(mx + gap, my, nextBoxWidth, my);
            g.setColor(Color.white);
            g.drawLine(mx + 30, my, mx + 30 + length, my);
            g.setColor(Color.green);
            g.fillOval(mx + 10 + playerPosition, my - 10 + playerPositionY, 10, 10);
            g.setColor(Color.black);
          }
    }

    public void run() {

        // System.out.println("Yes");



        try {
            if (isElongating == true) {
                System.out.println("Elongating.");
                while (true) {
                    length++;
                    repaint();
                    Thread.sleep(intervalSpeed);
                    if (isCharacterMoving == true)
                        break;
                }
            }
            if (isCharacterMoving == true) {
                System.out.println("Character is Moving.");
//                if((playerPosition+10)==(bonusX-50)) {
//                	bonusTaken = true;
//                	System.out.println("Collected");
//                	repaint();
//                }
//                if(isDown==false && (playerPosition + 10) >= gap)
//                	fall = true;
                if (fall == false) {
                    while (playerPosition < gap - 10 + nextBoxWidth - 20) {
                    	if(isDown==false && (playerPosition + 10 + 10)>=gap) { //CHANGEEEE
                    		System.out.println("YAY");
                    		isCharacterMoving = false;
                            isFalling = true;
                            while (playerPositionY < my +10) {
                                playerPositionY = playerPositionY + 2;
                                repaint();
                                Thread.sleep(intervalFall);
                            }
                    		//fall=true;
                            isGameOver=true;
                            repaint();
                            Thread.sleep(1000);
                            playerPosition = 0;
                            playerPositionY = 0;
                            // isCharacterMoving = false;
                            isFrameMoving = false;
                            
                            isStill = true;
                            length = 0;
                            playerPosition = 0;
                            nextBoxWidth = nextBoxWidth2;
                            nextBoxWidth2 = (int)((Math.random() * (100 - 30)) + 30);
                            score = 0;
                            allowedToClick1 = true;
                            isDown = true;
                            repaint();
                            return;
                    	
//                    		while (playerPositionY < my +10) {
//                                playerPositionY = playerPositionY + 2;
//                                repaint();
//                                Thread.sleep(intervalFall);
//                            }
                    	}
                    	
                        playerPosition++;
                        repaint();
                        Thread.sleep(intervalSpeed);
                        isFalling= false;
                    }
//                    if(isFalling==true) {
//                    	while (playerPositionY < my +10) {
//                            playerPositionY = playerPositionY + 2;
//                            repaint();
//                            Thread.sleep(intervalFall);
//                        }
//                    }
                    if(isFalling == false) {
                    	 isCharacterMoving = false;
                         isFrameMoving = true;
                    }
                   
                    
                }
            else
            {//fall==true
                    while (playerPosition < 20 + length) {
                    	
                    	if(isDown==false && (playerPosition + 10 + 10)>=gap) { //CHANGEEEEE
                    		System.out.println("YAY");
                    		isCharacterMoving = false;
                            isFalling = true;
                            while (playerPositionY < my +10) {
                                playerPositionY = playerPositionY + 2;
                                repaint();
                                Thread.sleep(intervalFall);
                            }
                    		//fall=true;
                            isGameOver=true;
                            repaint();
                    	    break;
                    	}
                    	
                        playerPosition++;
                        repaint();
                        Thread.sleep(intervalSpeed);
                    }
                    isCharacterMoving = false;
                    isFalling = true;
                    while (playerPositionY < my +10) {
                        playerPositionY = playerPositionY + 2;
                        repaint();
                        Thread.sleep(intervalFall);
                    }
                    Thread.sleep(500);
                    playerPosition = 0;
                    playerPositionY = 0;
                    
                    // isCharacterMoving = false;
                    isFrameMoving = false;
                    isStill = true;
                    length = 0;
                    playerPosition = 0;
                    nextBoxWidth = nextBoxWidth2;
                    nextBoxWidth2 = (int)((Math.random() * (100 - 30)) + 30);
                    bonusX = (int)((Math.random() * (300 - 50)) + 50);
                    score = 0;
                    allowedToClick1 = true;
                    repaint();
                    isGameOver=true;
                    repaint();
                    return;
                }

            }
            if (isFrameMoving == true) {
                System.out.println("Frame is Moving.");
                frameLength = gap + nextBoxWidth - 30;
                movedLeft = 0;
                while (movedLeft < frameLength) {
                    repaint();
                    Thread.sleep(intervalSpeed);
                    movedLeft++;
                }
                isFrameMoving = false;
                isStill = true;
                
                length = 0;
                playerPosition = 0;
                nextBoxWidth = nextBoxWidth2;
                nextBoxWidth2 = (int)((Math.random() * (100 - 30)) + 30);
                bonusX = (int)((Math.random() * (300 - 50)) + 50);
                score = score + 1;
                if (score > bestScore) {
                    bestScore = score;
                }
                allowedToClick1 = true;
                repaint();
            }

        } catch (InterruptedException e) {

            System.out.println("Error.");

        }

    }
}