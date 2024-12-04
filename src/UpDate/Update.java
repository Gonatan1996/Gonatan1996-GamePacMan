package UpDate;

import Graphics.GamePanel;
import Objects.Block;
import Objects.GeneralElement;
import Objects.Ghost;
import Objects.PacMan;

import javax.swing.*;

public class Update {
    public static Update update;
    Ghost ghost = Ghost.newGhost();
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


    public void upDateMoveUp(GeneralElement element) {
        element.moveUp();
    }

    public void upDateMoveDown(GeneralElement element) {
       element.moveDown();
    }

    public void upDateMoveRight(GeneralElement element) {
        element.moveRight();

    }

    public void upDateMoveLeft(GeneralElement element) {
        element.moveLeft();
    }

    private boolean canMoveUp(GeneralElement generalElement,int tempX, int y) {
        if (tempX * generalElement.getWidth() == generalElement.getPoint().x && !(GamePanel.generalElements[(y-speed)/generalElement.getWidth()][tempX] instanceof Block)){
            return true;
        }
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

    public void moveElement(GeneralElement generalElement,int tempX,int tempY,int x,int y){

        switch (generalElement.getDirection()) {
            case "UP" -> {
                if (canTurn(generalElement,"UP", tempX, tempY, x, y)){
                    upDateMoveUp(generalElement);
                }else {
                    if (generalElement instanceof Ghost) {
                        ((Ghost) generalElement).canMove = false;
                        ((Ghost) generalElement).up = false;
                    }
                }
            }
            case "DOWN" -> {
                if (canTurn(generalElement,"DOWN", tempX, tempY, x, y)) {
                    upDateMoveDown(generalElement);
                }else {
                    if (generalElement instanceof Ghost) {
                        ((Ghost) generalElement).canMove = false;
                        ((Ghost) generalElement).down = false;
                    }
                }
            }
            case "RIGHT" -> {
                if (canTurn(generalElement,"RIGHT", tempX, tempY, x, y)){
                    upDateMoveRight(generalElement);
                }else {
                    if (generalElement instanceof Ghost) {
                        ((Ghost) generalElement).canMove = false;
                        ((Ghost) generalElement).right = false;
                    }
                }
            }
            case "LEFT" -> {
                if (canTurn(generalElement,"LEFT", tempX, tempY, x, y)){
                    upDateMoveLeft(generalElement);
                }else {
                    if (generalElement instanceof Ghost) {
                        ((Ghost) generalElement).canMove = false;
                        ((Ghost) generalElement).left = false;
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
                if (canMoveUp(red,tempX, redY))upDateMoveUp(red);
            }
            if (pacmanX > redX) upDateMoveRight(red);
            else upDateMoveLeft(red);
            return;
        }

        if (pacmanX > redX && !_else) {
            if (canMoveRight(red, tempX, tempY) && !left) {
                down = true;
                up = true;
                flipDirectionRight(red);
                upDateMoveRight(red);
                if (pacmanY > redY && canMoveDown(red,tempX,tempY)){
                    upDateMoveDown(red);
                }
                if (pacmanY < redY && canMoveUp(red,tempX, redY)){
                    upDateMoveUp(red);
                }
            }
            else if (pacmanY > redY) {
                if (canMoveDown(red, tempX, tempY) && down) {
                    left = false;
                    up = false;
                    upDateMoveDown(red);
                } else if (canMoveUp(red, tempX, redY)) {
                    up = true;
                    left = false;
                    down = false;
                    upDateMoveUp(red);
                } else if (canMoveLeft(red, redX, tempY)) {
                    flipDirectionLeft(red);
                    upDateMoveLeft(red);
                    left = true;
                }
            } else if (pacmanY < redY) {
                if (canMoveUp(red,tempX, redY) && up){
                    left = false;
                    upDateMoveUp(red);
                }
                else if (canMoveDown(red, tempX, tempY) && down) {
                    up = false;
                    left = false;
                    upDateMoveDown(red);
                } else if (canMoveLeft(red, redX, tempY)) {
                   flipDirectionLeft(red);
                    upDateMoveLeft(red);
                    left = true;
                }
            }
            else if (canMoveUp(red, tempX, redY) && up){
                down = false;
                upDateMoveUp(red);
            }
            else if (canMoveDown(red, tempX, tempY) && down) {
                up = false;
                upDateMoveDown(red);
            }
        }

        else if (pacmanX < redX && !_else) {
            if (canMoveLeft(red, redX, tempY) && !right) {
                flipDirectionLeft(red);
                down = true;
                up = true;
                upDateMoveLeft(red);
                if (pacmanY > redY && canMoveDown(red,tempX,tempY)){
                    upDateMoveDown(red);
                }
                if (pacmanY < redY && canMoveUp(red,tempX, redY)){
                    upDateMoveUp(red);
                }
            } else if (pacmanY > redY) {
                if (canMoveDown(red, tempX, tempY) && down){
                    right = false;
                    upDateMoveDown(red);
                }
                else if (canMoveUp(red, tempX, redY)) {
                    down = false;
                    right = false;
                    upDateMoveUp(red);
                } else if (canMoveRight(red, tempX, tempY)) {
                    down = true;
                    up = false;
                    flipDirectionRight(red);
                    upDateMoveRight(red);
                    right = true;
                }
            } else if (pacmanY < redY) {
                if (canMoveUp(red, tempX, redY) && up){
                    right = false;
                    upDateMoveUp(red);
                }
                else if (canMoveDown(red, tempX, tempY)) {
                    up = false;
                    right = false;
                    upDateMoveDown(red);
                } else if (canMoveRight(red, tempX, tempY)) {
                    up = true;
                    down = false;
                    flipDirectionRight(red);
                    upDateMoveRight(red);
                    right = true;
                }
            }
            else if (canMoveUp(red, tempX, redY) && up){
                down = false;
                upDateMoveUp(red);
            }
            else if (canMoveDown(red, tempX, tempY)) {
                up = false;
                upDateMoveDown(red);
            }else if (canMoveRight(red, tempX, tempY)) {
                flipDirectionRight(red);
                upDateMoveRight(red);
                right = true;
            }

        }else {
            _else = true;
            if (pacmanY > redY) {
                if (canMoveDown(red, tempX, tempY) && down) {
                    _else = false;
                    right = true;
                    left = true;
                    upDateMoveDown(red);
                }
                else if (canMoveRight(red, tempX, tempY) && right){
                    flipDirectionRight(red);
                    up = false;
                    upDateMoveRight(red);
                }
                else if (canMoveLeft(red, redX, tempY)) {
                    flipDirectionLeft(red);
                    right = false;
                    up = false;
                    upDateMoveLeft(red);
                } else if (canMoveUp(red, tempX, redY)) {
                    up = true;
                    _else = false;
                    upDateMoveUp(red);
                }

            } else if (pacmanY < redY) {
                if (canMoveUp(red, tempX, redY) && up) {
                    _else = false;
                    upDateMoveUp(red);
                }
                else if (canMoveRight(red, tempX, tempY) && right){
                    down = false;
                    flipDirectionRight(red);
                    upDateMoveRight(red);
                }
                else if (canMoveLeft(red, redX, tempY)) {
                    flipDirectionLeft(red);
                    upDateMoveLeft(red);
                    down = false;
                    right = false;
                    _else = false;


                } else if (canMoveDown(red, tempX, tempY) && down) {
                    upDateMoveDown(red);
                    down = true;
                    _else = false;
                }
            }



        }

    }

    public void ghostEatenMove(Ghost ghost){
        int rootX = 280,rootY = 260,gx = ghost.getPoint().x,
                gy = ghost.getPoint().y,
                tempX = gx / width,
                tempY = gy / height;



//        System.out.println("pointY " + ghost.getPoint().y);
//        System.out.println("gy " +gy);
//        System.out.println("rootY " +rootY);

        if (rootX > gx && !_else) {
            if (canMoveRight(ghost, tempX, tempY) && !left) {
                System.out.println(51);
                down = true;
                up = true;
               flipDirectionRight(ghost);
                upDateMoveRight(ghost);
                if (rootY > gy && canMoveDown(ghost,tempX,tempY)){
                    System.out.println(52);
                    upDateMoveDown(ghost);
                }
                if (rootY < gy && canMoveUp(ghost,tempX,gy)){
                    System.out.println(53);upDateMoveUp(ghost);
                }
            }
            else if (rootY > gy) {
                if (canMoveDown(ghost, tempX, tempY) && down) {
                    System.out.println(54);
                    left = false;
                    up = false;
                    upDateMoveDown(ghost);
                } else if (canMoveUp(ghost, tempX, gy)) {
                    System.out.println(55);
                    up = true;
                    left = false;
                    down = false;
                    upDateMoveUp(ghost);
                } else if (canMoveLeft(ghost, gx, tempY)) {
                    System.out.println(56);
                    flipDirectionLeft(ghost);
                    upDateMoveLeft(ghost);
                    left = true;
                }
            } else if (rootY < gy) {
                if (canMoveUp(ghost,tempX,gy) && up){
                    System.out.println(57);
                    left = false;
                    upDateMoveUp(ghost);
                }
                else if (canMoveDown(ghost, tempX, tempY) && down) {
                    System.out.println(58);
                    up = false;
                    left = false;
                    upDateMoveDown(ghost);
                } else if (canMoveLeft(ghost, gx, tempY)) {
                    System.out.println(59);
                    flipDirectionLeft(ghost);
                    upDateMoveLeft(ghost);
                    left = true;
                }
            }
            else if (canMoveUp(ghost, tempX, gy) && up){
                System.out.println(60);
                down = false;
                upDateMoveUp(ghost);
            }
            else if (canMoveDown(ghost, tempX, tempY) && down) {
                System.out.println(61);
                up = false;
                upDateMoveDown(ghost);
            }
        }

        else if (rootX < gx && !_else) {
            if (canMoveLeft(ghost, gx, tempY) && !right) {
                System.out.println(62);
                down = true;
                up = true;
                flipDirectionLeft(ghost);
                upDateMoveLeft(ghost);
                if (rootY > gy && canMoveDown(ghost,tempX,tempY)){
                    System.out.println(63);
                    upDateMoveDown(ghost);
                }
                if (rootY < gy && canMoveUp(ghost,tempX,gy)){
                    System.out.println(64);
                    upDateMoveUp(ghost);
                }
            } else if (rootY > gy) {
                if (canMoveDown(ghost, tempX, tempY) && down){
                    System.out.println(65);
                    right = false;
                    upDateMoveDown(ghost);
                }
                else if (canMoveUp(ghost, tempX, gy)) {
                    System.out.println(66);
                    down = false;
                    right = false;
                    upDateMoveUp(ghost);
                } else if (canMoveRight(ghost, tempX, tempY)) {
                    System.out.println(67);
                    down = true;
                    up = false;
                    flipDirectionRight(ghost);
                    upDateMoveRight(ghost);
                    right = true;
                }
            } else if (rootY < gy) {
                if (canMoveUp(ghost, tempX, gy) && up){
                    System.out.println(68);
                    right = false;
                    upDateMoveUp(ghost);
                }
                else if (canMoveDown(ghost, tempX, tempY)) {
                    System.out.println(69);
                    up = false;
                    right = false;
                    upDateMoveDown(ghost);
                } else if (canMoveRight(ghost, tempX, tempY)) {
                    System.out.println(70);
                    up = true;
                    down = false;
                    flipDirectionRight(ghost);
                    upDateMoveRight(ghost);
                    right = true;
                }
            }
            else if (canMoveUp(ghost, tempX,gy) && up){
                System.out.println(71);
                down = false;
                upDateMoveUp(ghost);
            }
            else if (canMoveDown(ghost, tempX, tempY)) {
                System.out.println(72);
                up = false;
                upDateMoveDown(ghost);
            }else if (canMoveRight(ghost, tempX, tempY)) {
                System.out.println(73);
                flipDirectionRight(ghost);
                upDateMoveRight(ghost);
                right = true;
            }

        } else {
            _else = true;
            if (rootY > gy) {
                if (canMoveDown(ghost, tempX, tempY) && down) {
                    System.out.println(74);
                    _else = false;
                    right = true;
                    left = true;
                    upDateMoveDown(ghost);
                }
                else if (canMoveRight(ghost, tempX, tempY) && right){
                    System.out.println(75);
                    up = false;
                    flipDirectionRight(ghost);
                    upDateMoveRight(ghost);
                }
                else if (canMoveLeft(ghost, gx, tempY)) {
                    System.out.println(76);
                    right = false;
                    up = false;
                    flipDirectionLeft(ghost);
                    upDateMoveLeft(ghost);
                } else if (canMoveUp(ghost, tempX, gy)) {
                    System.out.println(77);
                    up = true;
                    _else = false;
                    upDateMoveUp(ghost);
                }

            } else if (rootY < gy) {
                if (canMoveUp(ghost, tempX, gy) && up) {
                    System.out.println(78);
                    _else = false;
                    upDateMoveUp(ghost);
                }
                else if (canMoveRight(ghost, tempX, tempY) && right){
                    System.out.println(79);
                    down = false;
                    flipDirectionRight(ghost);
                    upDateMoveRight(ghost);
                }
                else if (canMoveLeft(ghost, gx, tempY)) {
                    System.out.println(80);
                    flipDirectionLeft(ghost);
                    upDateMoveLeft(ghost);
                    down = false;
                    right = false;

                } else if (canMoveDown(ghost, tempX, tempY) && down) {
                    System.out.println(81);
                    upDateMoveDown(ghost);
                    down = true;
                }
            }
        }
    }
}

