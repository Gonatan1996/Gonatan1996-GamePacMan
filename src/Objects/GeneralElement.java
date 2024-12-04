package Objects;

import java.awt.*;

public abstract class  GeneralElement implements Element{
    protected Point point = new Point();
    protected int speed;
    protected boolean isEaten;
    protected int width = 20,height = 20;
    protected Image image;


    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public boolean getIsEaten() {
        return isEaten;
    }

    public void setEaten(boolean eaten) {
        isEaten = eaten;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    protected void setPreferredDirection(String preferredDirection) {
    }

    public String getPreferredDirection() {
        return "";
    }

    public void setDirection(String direction) {
    }

    public String getDirection() {
        return "";
    }

    public boolean checkCollision(GeneralElement element){
        Rectangle rectangle1 = new Rectangle(this.point.x,this.point.y,width,height);
        Rectangle rectangle2 = new Rectangle(element.getPoint().x,element.getPoint().y,width,height);
        return rectangle1.intersects(rectangle2);
    }
     public void moveUp(){
         point.y -= speed;

    }
    public void moveDown(){
         point.y += speed;
    }
    public void moveRight(){
         point.x += speed;

    }
    public void moveLeft(){
         point.x -= speed;

    }



}
