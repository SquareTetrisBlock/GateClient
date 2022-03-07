package me.thef1xer.gateclient.util.render;

public class Animate {

  private double value;
  private float speed;
  private long lastRenderTime;

  public Animate(double value, float speed) {
    this.value = value;
    this.speed = speed;
  }

  public void interpolate(double target) {
    long delta = System.currentTimeMillis() - this.lastRenderTime;
    this.lastRenderTime = System.currentTimeMillis();
    this.value = move(target, value, delta, speed);
  }

  public double getValue() {
    return value;
  }

  public void setValue(double value) {
    this.value = value;
  }

  public float getSpeed() {
    return speed;
  }

  public void setSpeed(float speed) {
    this.speed = speed;
  }

  //It just works
  private double move(double target, double current, long delta, float speed) {
    if(delta < 1)
      delta = 1;

    double diff = target - current;

    boolean dir = target > current;

    current+=(diff/50)*(delta * speed);
    if(dir)
      if(current > target)
        current = target;
    if(!dir)
      if(current < target)
        current = target;
    return current;
  }

}
