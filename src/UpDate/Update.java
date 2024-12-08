package UpDate;

import Graphics.GamePanel;
import Objects.Block;
import Objects.GeneralElement;
import Objects.Ghost;
import Objects.PacMan;

import javax.swing.*;

public class Update {
    public static Update update;
    public int speed = 4;
    private final int width = 20;
    private final int height = 20;
    private boolean right = true,left= true,up= true,down= true,_else;


    private Update() {
    }
    public static Update newUpdate(){
        if (Update.update == null){
            Update.update = new Update();
        }
        return Update.update;
    }


    private boolean canMoveUp(GeneralElement generalElement,int tempX, int y) {
        return tempX * generalElement.getWidth() == generalElement.getPoint().x && !(GamePanel.generalElements[(y-speed)/generalElement.getWidth()][tempX] instanceof Block);
    }

    private boolean canMoveDown(GeneralElement generalElement,int tempX, int tempY) {
        return tempX * generalElement.getWidth() == generalElement.getPoint().x && !(GamePanel.generalElements[tempY + 1][tempX] instanceof Block);
    }

    private boolean canMoveLeft(GeneralElement generalElement,int x, int tempY) {
        return tempY * generalElement.getWidth() == generalElement.getPoint().y && !(GamePanel.generalElements[tempY][(x - speed)/ generalElement.getWidth()] instanceof Block);
    }

    private boolean canMoveRight(GeneralElement generalElement,int tempX, int tempY) {
        return tempY * generalElement.getWidth() == generalElement.getPoint().y && !(GamePanel.generalElements[tempY][tempX + 1] instanceof Block);
    }

    public void moveElement(GeneralElement element,int tempX,int tempY,int x,int y){

        switch (element.getDirection()) {
            case "UP" -> {
                if (canTurn(element,"UP", tempX, tempY, x, y)){
                    element.moveUp();
                }else {
                    if (element instanceof Ghost) {
                        ((Ghost) element).canMove = false;
                        ((Ghost) element).up = false;
                    }
                }
            }
            case "DOWN" -> {
                if (canTurn(element,"DOWN", tempX, tempY, x, y)) {
                    element.moveDown();
                }else {
                    if (element instanceof Ghost) {
                        ((Ghost) element).canMove = false;
                        ((Ghost) element).down = false;
                    }
                }
            }
            case "RIGHT" -> {
                if (canTurn(element,"RIGHT", tempX, tempY, x, y)){
                    element.moveRight();
                }else {
                    if (element instanceof Ghost) {
                        ((Ghost) element).canMove = false;
                        ((Ghost) element).right = false;
                    }
                }
            }
            case "LEFT" -> {
                if (canTurn(element,"LEFT", tempX, tempY, x, y)){
                    element.moveLeft();
                }else {
                    if (element instanceof Ghost) {
                        ((Ghost) element).canMove = false;
                        ((Ghost) element).left = false;
                    }
                }
            }
        }
    }

    public void flipDirectionRight(GeneralElement generalElement) {
        if ((generalElement.getPoint().x + generalElement.getWidth() > 555)){
            generalElement.getPoint().x = 4;
        }
    }

    public void flipDirectionLeft(GeneralElement generalElement){
        if ((generalElement.getPoint().x) < 10) {
            generalElement.getPoint().x = 540;
        }
    }

    public boolean canTurn(GeneralElement generalElement,String preferred,int tempX,int tempY,int x,int y){
        return switch (preferred) {
            case "UP" -> canMoveUp(generalElement,tempX, y);
            case "DOWN" -> canMoveDown(generalElement,tempX, tempY);
            case "RIGHT" -> canMoveRight(generalElement,tempX, tempY);
            case "LEFT" -> canMoveLeft(generalElement,x, tempY);
            default -> false;
        };
    }

    public void ghostRedMove(Ghost red,PacMan pacMan) {
        System.out.println("redMove");
        if (red.getIsEaten()){
            ghostEatenMove(red);
            return;
        }

        int redX = red.getPoint().x,
                redY = red.getPoint().y,
                tempX = redX / width,
                tempY = redY / height,
        pacmanX = pacMan.getPoint().x,
        pacmanY = pacMan.getPoint().y;

//        System.out.println("redY" + redY);
//        System.out.println("redX" + redX);

        if (redX == 280 && redY == 260) {
            for (int i = 0; i < 10; i++) {
                if (canMoveUp(red,tempX, redY))red.moveUp();
            }
            if (pacmanX > redX) red.moveRight();
            else red.moveLeft();
            return;
        }

        if (pacmanX > redX && !_else) {
            if (canMoveRight(red, tempX, tempY) && !left) {
                down = true;
                up = true;
                flipDirectionRight(red);
                red.moveRight();
                if (pacmanY > redY && canMoveDown(red,tempX,tempY)){
                    red.moveDown();
                }
                if (pacmanY < redY && canMoveUp(red,tempX, redY)){
                    red.moveUp();
                }
            }
            else if (pacmanY > redY) {
                if (canMoveDown(red, tempX, tempY) && down) {
                    left = false;
                    up = false;
                    red.moveDown();
                } else if (canMoveUp(red, tempX, redY)) {
                    up = true;
                    left = false;
                    down = false;
                    red.moveUp();
                } else if (canMoveLeft(red, redX, tempY)) {
                    flipDirectionLeft(red);
                    red.moveLeft();
                    left = true;
                }
            } else if (pacmanY < redY) {
                if (canMoveUp(red,tempX, redY) && up){
                    left = false;
                    red.moveUp();
                }
                else if (canMoveDown(red, tempX, tempY) && down) {
                    up = false;
                    left = false;
                    red.moveDown();
                } else if (canMoveLeft(red, redX, tempY)) {
                   flipDirectionLeft(red);
                    red.moveLeft();
                    left = true;
                }
            }
            else if (canMoveUp(red, tempX, redY) && up){
                down = false;
                red.moveUp();
            }
            else if (canMoveDown(red, tempX, tempY) && down) {
                up = false;
                red.moveDown();            }
        }

        else if (pacmanX < redX && !_else) {
            if (canMoveLeft(red, redX, tempY) && !right) {
                flipDirectionLeft(red);
                down = true;
                up = true;
                red.moveLeft();
                if (pacmanY > redY && canMoveDown(red,tempX,tempY)){
                    red.moveDown();
                }
                if (pacmanY < redY && canMoveUp(red,tempX, redY)){
                    red.moveUp();
                }
            } else if (pacmanY > redY) {
                if (canMoveDown(red, tempX, tempY) && down){
                    right = false;
                    red.moveDown();
                }
                else if (canMoveUp(red, tempX, redY)) {
                    down = false;
                    right = false;
                    red.moveUp();
                } else if (canMoveRight(red, tempX, tempY)) {
                    down = true;
                    up = false;
                    flipDirectionRight(red);
                    red.moveRight();
                    right = true;
                }
            } else if (pacmanY < redY) {
                if (canMoveUp(red, tempX, redY) && up){
                    right = false;
                    red.moveUp();
                }
                else if (canMoveDown(red, tempX, tempY)) {
                    up = false;
                    right = false;
                    red.moveDown();
                } else if (canMoveRight(red, tempX, tempY)) {
                    up = true;
                    down = false;
                    flipDirectionRight(red);
                    red.moveRight();
                    right = true;
                }
            }
            else if (canMoveUp(red, tempX, redY) && up){
                down = false;
                red.moveUp();
            }
            else if (canMoveDown(red, tempX, tempY)) {
                up = false;
                red.moveDown();
            }else if (canMoveRight(red, tempX, tempY)) {
                flipDirectionRight(red);
                red.moveRight();
                right = true;
            }

        }else {
            _else = true;
            if (pacmanY > redY) {
                if (canMoveDown(red, tempX, tempY) && down) {
                    _else = false;
                    right = true;
                    left = true;
                    red.moveDown();
                }
                else if (canMoveRight(red, tempX, tempY) && right){
                    flipDirectionRight(red);
                    up = false;
                    red.moveRight();
                }
                else if (canMoveLeft(red, redX, tempY)) {
                    flipDirectionLeft(red);
                    right = false;
                    up = false;
                    red.moveLeft();
                } else if (canMoveUp(red, tempX, redY)) {
                    up = true;
                    _else = false;
                    red.moveUp();
                }

            } else if (pacmanY < redY) {
                if (canMoveUp(red, tempX, redY) && up) {
                    _else = false;
                    red.moveUp();
                }
                else if (canMoveRight(red, tempX, tempY) && right){
                    down = false;
                    flipDirectionRight(red);
                    red.moveRight();
                }
                else if (canMoveLeft(red, redX, tempY)) {
                    flipDirectionLeft(red);
                    red.moveLeft();
                    down = false;
                    right = false;
                    _else = false;


                } else if (canMoveDown(red, tempX, tempY) && down) {
                    red.moveDown();
                    down = true;
                    _else = false;
                }
            }



        }

    }

    public void ghostEatenMove(Ghost red){
        int redX = red.getPoint().x,
                redY = red.getPoint().y,
                tempX = redX / width,
                tempY = redY / height,
                rootX = 280,
                rootY = 260;


        if (rootX > redX && !_else) {
            if (canMoveRight(red, tempX, tempY) && !left) {
                down = true;
                up = true;
                flipDirectionRight(red);
                red.moveRight();
                if (rootY > redY && canMoveDown(red,tempX,tempY)){
                    red.moveDown();
                }
                if (rootY < redY && canMoveUp(red,tempX, redY)){
                    red.moveUp();
                }
            }
            else if (rootY > redY) {
                if (canMoveDown(red, tempX, tempY) && down) {
                    left = false;
                    up = false;
                    red.moveDown();
                } else if (canMoveUp(red, tempX, redY)) {
                    up = true;
                    left = false;
                    down = false;
                    red.moveUp();
                } else if (canMoveLeft(red, redX, tempY)) {
                    flipDirectionLeft(red);
                    red.moveLeft();
                    left = true;
                }
            } else if (rootY < redY) {
                if (canMoveUp(red,tempX, redY) && up){
                    left = false;
                    red.moveUp();
                }
                else if (canMoveDown(red, tempX, tempY) && down) {
                    up = false;
                    left = false;
                    red.moveDown();
                } else if (canMoveLeft(red, redX, tempY)) {
                    flipDirectionLeft(red);
                    red.moveLeft();
                    left = true;
                }
            }
            else if (canMoveUp(red, tempX, redY) && up){
                down = false;
                red.moveUp();
            }
            else if (canMoveDown(red, tempX, tempY) && down) {
                up = false;
                red.moveDown();            }
        }

        else if (rootX < redX && !_else) {
            if (canMoveLeft(red, redX, tempY) && !right) {
                flipDirectionLeft(red);
                down = true;
                up = true;
                red.moveLeft();
                if (rootY > redY && canMoveDown(red,tempX,tempY)){
                    red.moveDown();
                }
                if (rootY < redY && canMoveUp(red,tempX, redY)){
                    red.moveUp();
                }
            } else if (rootY > redY) {
                if (canMoveDown(red, tempX, tempY) && down){
                    right = false;
                    red.moveDown();
                }
                else if (canMoveUp(red, tempX, redY)) {
                    down = false;
                    right = false;
                    red.moveUp();
                } else if (canMoveRight(red, tempX, tempY)) {
                    down = true;
                    up = false;
                    flipDirectionRight(red);
                    red.moveRight();
                    right = true;
                }
            } else if (rootY < redY) {
                if (canMoveUp(red, tempX, redY) && up){
                    right = false;
                    red.moveUp();
                }
                else if (canMoveDown(red, tempX, tempY)) {
                    up = false;
                    right = false;
                    red.moveDown();
                } else if (canMoveRight(red, tempX, tempY)) {
                    up = true;
                    down = false;
                    flipDirectionRight(red);
                    red.moveRight();
                    right = true;
                }
            }
            else if (canMoveUp(red, tempX, redY) && up){
                down = false;
                red.moveUp();
            }
            else if (canMoveDown(red, tempX, tempY)) {
                up = false;
                red.moveDown();
            }else if (canMoveRight(red, tempX, tempY)) {
                flipDirectionRight(red);
                red.moveRight();
                right = true;
            }

        }else {
            _else = true;
            if (rootY > redY) {
                if (canMoveDown(red, tempX, tempY) && down) {
                    _else = false;
                    right = true;
                    left = true;
                    red.moveDown();
                }
                else if (canMoveRight(red, tempX, tempY) && right){
                    flipDirectionRight(red);
                    up = false;
                    red.moveRight();
                }
                else if (canMoveLeft(red, redX, tempY)) {
                    flipDirectionLeft(red);
                    right = false;
                    up = false;
                    red.moveLeft();
                } else if (canMoveUp(red, tempX, redY)) {
                    up = true;
                    _else = false;
                    red.moveUp();
                }

            } else if (rootY < redY) {
                if (canMoveUp(red, tempX, redY) && up) {
                    _else = false;
                    red.moveUp();
                }
                else if (canMoveRight(red, tempX, tempY) && right){
                    down = false;
                    flipDirectionRight(red);
                    red.moveRight();
                }
                else if (canMoveLeft(red, redX, tempY)) {
                    flipDirectionLeft(red);
                    red.moveLeft();
                    down = false;
                    right = false;
                    _else = false;


                } else if (canMoveDown(red, tempX, tempY) && down) {
                    red.moveDown();
                    down = true;
                    _else = false;
                }
            }



        }

    }
}

