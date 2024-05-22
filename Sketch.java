import processing.core.PApplet;

public class Sketch extends PApplet {
  // Variables
  boolean upPressed;
  boolean downPressed;
  boolean aPressed;
  boolean wPressed;
  boolean sPressed;
  boolean dPressed;

  // X and Y for snowflakes
  float[] snowX = new float[50];
  float[] snowY = new float[50];
  int[] snowDiameter = new int[50];
  int speed = 2;
  int playerX;
  int playerY;
  int totalLives = 3;

  public void settings() {
    size(600, 600);
  }

  public void setup() {

    background(0);

    // Generate random x and y values for snowflakes
    for (int i = 0; i < snowX.length; i++) {
      snowX[i] = random(width);
      snowY[i] = random(height) - height / 2;
      snowDiameter[i] = 20;
    }
    playerX = width / 2;
    playerY = height - height / 6;
  }

  public void draw() {
    // Ends Game if lives are 0
    if (totalLives == 0) {
      fill(255);
      background(0);
      textSize(50);
      text("Game Over", 170, height / 2);
    } else {
      background(0);

      // Draw snow
      fill(255);
      if (speed == 0) {
        speed++;
      }
      if (speed > 40) {
        speed = 2;
      }
      if (upPressed == true) {
        speed++;
      }
      if (downPressed == true) {
        speed--;
      }
      for (int i = 0; i < snowX.length; i++) {
        ellipse(snowX[i], snowY[i], snowDiameter[i], snowDiameter[i]);
        snowY[i] += speed;

        // Reset snowflakes
        if (snowY[i] > height) {
          snowY[i] = 0;
          snowDiameter[i] = 20;
        }
        // Collison detection
        float d = dist(snowX[i], snowY[i], playerX, playerY);
        if (d < snowDiameter[i]) {
          snowDiameter[i] = 0;
          totalLives--;
        }

      }

      // Player
      fill(50, 50, 200);
      if (wPressed == true) {
        playerY -= 2;
      }
      if (sPressed == true) {
        playerY += 2;
      }
      if (aPressed == true) {
        playerX -= 2;
      }
      if (dPressed == true) {
        playerX += 2;
      }
      // Stop player from leaving
      if (playerX >= width) {
        playerX = 0;
      } else if (playerX <= 0) {
        playerX = width;
      }
      if (playerY > height - 15) {
        playerY = height - 15;
      }
      if (playerY < 0 + 15) {
        playerY = 0 + 15;
      }
      ellipse(playerX, playerY, 30, 30);

      // lives
      for (int lives = totalLives; lives > 0; lives--) {
        fill(200, 50, 50);
        rect(lives * 35 + 15, 15, 30, 30);
      }
    }
  }

  
  /**
   * A method that allows the use of different keys on the keyboard
   *
   * @author P. Liu
   */
  public void keyPressed() {

    if (keyCode == UP) {
      upPressed = true;
    }
    if (keyCode == DOWN) {
      downPressed = true;
    }
    if (key == 'w') {
      wPressed = true;
    }
    if (key == 'a') {
      aPressed = true;
    }
    if (key == 's') {
      sPressed = true;
    }
    if (key == 'd') {
      dPressed = true;
    }

  }

  /**
   * A method that allows the use of different keys on the keyboard
   *
   * @author P. Liu
   */
  public void keyReleased() {

    if (keyCode == UP) {
      upPressed = false;
    }
    if (keyCode == DOWN) {
      downPressed = false;
    }
    if (key == 'w') {
      wPressed = false;
    }
    if (key == 'a') {
      aPressed = false;
    }
    if (key == 's') {
      sPressed = false;
    }
    if (key == 'd') {
      dPressed = false;
    }
  }

  /**
   * A Method that makes a snowball disapear when clicked
   *
   * @author P. Liu
   */
  public void mouseClicked() {
    for (int i = 0; i < snowX.length; i++) {
      if (abs(dist(mouseX, mouseY, snowX[i], snowY[i])) < 30) {
        System.out.println(i);
        snowDiameter[i] = 0;
      }
    }
  }
}
