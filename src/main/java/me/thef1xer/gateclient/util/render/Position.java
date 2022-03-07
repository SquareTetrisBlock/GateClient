package me.thef1xer.gateclient.util.render;

public class Position {

  private final Animate posX;
  private final Animate posY;

  public Position(double posX, double posY, float speed) {
    this.posX = new Animate(posX, speed);
    this.posY = new Animate(posY, speed);
  }

  public void interpolate(float targetX, float targetY) {
    posX.interpolate(targetX);
    posY.interpolate(targetY);
  }

  public double getX() {
    return posX.getValue();
  }

  public double getY() {
    return posY.getValue();
  }

}
