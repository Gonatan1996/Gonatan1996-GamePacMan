package UpDate;

import Graphics.GamePanel;
import Objects.Block;
import Objects.GeneralElement;
import Objects.Ghost;
import Objects.PacMan;

import javax.swing.*;

public class Update {
    PacMan pacMan;
    Ghost ghost;
    public int speed = 4;
    private final int width = 20;
    private final int height = 20;
    private boolean right = true,left= true,up= true,down= true,_else;


    public Update(PacMan pacMan, Ghost ghost) {
        this.pacMan = pacMan;
        this.ghost = ghost;
    }

    public void upDateMoveUp(GeneralElement element) {
        element.getPoint().y -= speed;
        if (element instanceof PacMan) {
            pacMan.setImage(new ImageIcon("src/Images/pacmanUp.gif"));
        } else if (element instanceof Ghost) {
            ghost.setImageUp_Down();
        }
    }

    public void upDateMoveDown(GeneralElement element) {
        element.getPoint().y += speed;
        if (element instanceof PacMan) {
                pacMan.setImage(new ImageIcon("src/Images/pacmanDown.gif"));
        } else if (element instanceof Ghost) {
            ghost.setImageUp_Down();
        }
    }

    public void upDateMoveRight(GeneralElement element) {
        element.getPoint().x += speed;
        if (element instanceof PacMan) {
                pacMan.setImage(new ImageIcon("src/Images/pacmanRight.gif"));
        } else if (element instanceof Ghost) {
            ghost.setImageLeft_Right();
        }
    }

    public void upDateMoveLeft(GeneralElement element) {
        element.getPoint().x -= speed;
        if (element instanceof PacMan) {
                pacMan.setImage(new ImageIcon("src/Images/pacmanLeft.gif"));
        } else if (element instanceof Ghost) {
            ghost.setImageLeft_Right();
        }
    }

    private boolean canMoveUp(GeneralElement generalElement,int tempX, int y) {
        return tempX * generalElement.width == generalElement.getPoint().x && !(GamePanel.generalElements[(y-speed)/generalElement.width][tempX] instanceof Block);
    }

    private boolean canMoveDown(GeneralElement generalElement,int tempX, int tempY) {
        return tempX * generalElement.width == generalElement.getPoint().x && !(GamePanel.generalElements[tempY + 1][tempX] instanceof Block);
    }

    private boolean canMoveLeft(GeneralElement generalElement,int x, int tempY) {
        return tempY * generalElement.width == generalElement.getPoint().y && !(GamePanel.generalElements[tempY][(x - speed)/ generalElement.width] instanceof Block);
    }

    private boolean canMoveRight(GeneralElement generalElement,int tempX, int tempY) {
        return tempY * generalElement.width == generalElement.getPoint().y && !(GamePanel.generalElements[tempY][tempX + 1] instanceof Block);
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
        if ((generalElement.getPoint().x + generalElement.width > 555)){
            generalElement.getPoint().x = 4;
        }
    }

    public void flipDirectionLeft(GeneralElement generalElement){
        if ((generalElement.getPoint().x) < 10) {
            generalElement.getPoint().x = 560;
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

    public void ghostRedMove(Ghost red) {
        flipDirectionLeft(red);
        flipDirectionRight(red);

        if (red.getIsEaten()){
            ghostRedEatenMove(red);
            return;
        }

        int x = red.getPoint().x,
                y = red.getPoint().y,
                tempX = x / width,
                tempY = y / height;


        if (red.point.x == 280 && red.point.y == 260) {
            for (int i = 0; i < 10; i++) {
                if (canMoveUp(red,tempX,y))upDateMoveUp(red);
            }
            if (pacMan.getPoint().x > red.point.x) upDateMoveRight(red);
            else upDateMoveLeft(red);
            return;
        }

        if (pacMan.getPoint().x > red.point.x && !_else) {
            if (canMoveRight(red, tempX, tempY) && !left) {
                down = true;
                up = true;
                upDateMoveRight(red);
                if (pacMan.getPoint().y > red.point.y && canMoveDown(red,tempX,tempY))upDateMoveDown(red);
                if (pacMan.getPoint().y < red.point.y && canMoveUp(red,tempX,y))upDateMoveUp(red);
            }
            else if (pacMan.getPoint().y > red.point.y) {
                if (canMoveDown(red, tempX, tempY) && down) {
                    left = false;
                    up = false;
                    upDateMoveDown(red);
                } else if (canMoveUp(red, tempX, y)) {
                    up = true;
                    left = false;
                    down = false;
                    upDateMoveUp(red);
                } else if (canMoveLeft(red, x, tempY)) {
                    upDateMoveLeft(red);
                    left = true;
                }
            } else if (pacMan.getPoint().y < red.point.y) {
                if (canMoveUp(red,tempX,y) && up){
                    left = false;
                    upDateMoveUp(red);
                }
                else if (canMoveDown(red, tempX, tempY) && down) {
                    up = false;
                    left = false;
                    upDateMoveDown(red);
                } else if (canMoveLeft(red, x, tempY)) {
                    upDateMoveLeft(red);
                    left = true;
                }
            }
            else if (canMoveUp(red, tempX, y) && up){
                down = false;
                upDateMoveUp(red);
            }
            else if (canMoveDown(red, tempX, tempY) && down) {
                up = false;
                upDateMoveDown(red);
            }
        }

        else if (pacMan.getPoint().x < red.point.x && !_else) {
            if (canMoveLeft(red, x, tempY) && !right) {
                down = true;
                up = true;
                upDateMoveLeft(red);
                if (pacMan.getPoint().y > red.point.y && canMoveDown(red,tempX,tempY))upDateMoveDown(red);
                if (pacMan.getPoint().y < red.point.y && canMoveUp(red,tempX,y))upDateMoveUp(red);
            } else if (pacMan.getPoint().y > red.point.y) {
                if (canMoveDown(red, tempX, tempY) && down){
                    right = false;
                    upDateMoveDown(red);
                }
                else if (canMoveUp(red, tempX, y)) {
                    down = false;
                    right = false;
                    upDateMoveUp(red);
                } else if (canMoveRight(red, tempX, tempY)) {
                    down = true;
                    up = false;
                    upDateMoveRight(red);
                    right = true;
                }
            } else if (pacMan.getPoint().y < red.point.y) {
                if (canMoveUp(red, tempX, y) && up){
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
                    upDateMoveRight(red);
                    right = true;
                }
            }
            else if (canMoveUp(red, tempX, y) && up){
                down = false;
                upDateMoveUp(red);
            }
            else if (canMoveDown(red, tempX, tempY)) {
                up = false;
                upDateMoveDown(red);
            }else if (canMoveRight(red, tempX, tempY)) {
                upDateMoveRight(red);
                right = true;
            }

        } else {
            _else = true;
            if (pacMan.getPoint().y > red.point.y) {
                if (canMoveDown(red, tempX, tempY) && down) {
                    _else = false;
                    right = true;
                    left = true;
                    upDateMoveDown(red);
                }
                else if (canMoveRight(red, tempX, tempY) && right){
                    up = false;
                    upDateMoveRight(red);
                }
                else if (canMoveLeft(red, x, tempY)) {
                    right = false;
                    up = false;
                    upDateMoveLeft(red);
                } else if (canMoveUp(red, tempX, y)) {
                    up = true;
                    upDateMoveUp(red);
                }

            } else if (pacMan.getPoint().y < red.point.y) {
                if (canMoveUp(red, tempX, y) && up) {
                    _else = false;
                    upDateMoveUp(red);
                }
                else if (canMoveRight(red, tempX, tempY) && right){
                    down = false;
                    upDateMoveRight(red);
                }
                else if (canMoveLeft(red, x, tempY)) {
                    upDateMoveLeft(red);
                    down = false;
                    right = false;

                } else if (canMoveDown(red, tempX, tempY) && down) {
                    upDateMoveDown(red);
                    down = true;
                }
            }



        }

    }

    public void ghostRedEatenMove(Ghost red){
        int rootX = 280,rootY = 260,x = red.getPoint().x,
                y = red.getPoint().y,
                tempX = x / width,
                tempY = y / height;


        if (rootX > red.point.x && !_else) {
            if (canMoveRight(red, tempX, tempY) && !left) {
                down = true;
                up = true;
                upDateMoveRight(red);
                if (rootY > red.point.y && canMoveDown(red,tempX,tempY))upDateMoveDown(red);
                if (rootY < red.point.y && canMoveUp(red,tempX,y))upDateMoveUp(red);
            }
            else if (rootY > red.point.y) {
                if (canMoveDown(red, tempX, tempY) && down) {
                    left = false;
                    up = false;
                    upDateMoveDown(red);
                } else if (canMoveUp(red, tempX, y)) {
                    up = true;
                    left = false;
                    down = false;
                    upDateMoveUp(red);
                } else if (canMoveLeft(red, x, tempY)) {
                    upDateMoveLeft(red);
                    left = true;
                }
            } else if (rootY < red.point.y) {
                if (canMoveUp(red,tempX,y) && up){
                    left = false;
                    upDateMoveUp(red);
                }
                else if (canMoveDown(red, tempX, tempY) && down) {
                    up = false;
                    left = false;
                    upDateMoveDown(red);
                } else if (canMoveLeft(red, x, tempY)) {
                    upDateMoveLeft(red);
                    left = true;
                }
            }
            else if (canMoveUp(red, tempX, y) && up){
                down = false;
                upDateMoveUp(red);
            }
            else if (canMoveDown(red, tempX, tempY) && down) {
                up = false;
                upDateMoveDown(red);
            }
        }

        else if (rootX < red.point.x && !_else) {
            if (canMoveLeft(red, x, tempY) && !right) {
                down = true;
                up = true;
                upDateMoveLeft(red);
                if (rootY > red.point.y && canMoveDown(red,tempX,tempY))upDateMoveDown(red);
                if (rootY < red.point.y && canMoveUp(red,tempX,y))upDateMoveUp(red);
            } else if (rootY > red.point.y) {
                if (canMoveDown(red, tempX, tempY) && down){
                    right = false;
                    upDateMoveDown(red);
                }
                else if (canMoveUp(red, tempX, y)) {
                    down = false;
                    right = false;
                    upDateMoveUp(red);
                } else if (canMoveRight(red, tempX, tempY)) {
                    down = true;
                    up = false;
                    upDateMoveRight(red);
                    right = true;
                }
            } else if (rootY < red.point.y) {
                if (canMoveUp(red, tempX, y) && up){
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
                    upDateMoveRight(red);
                    right = true;
                }
            }
            else if (canMoveUp(red, tempX, y) && up){
                down = false;
                upDateMoveUp(red);
            }
            else if (canMoveDown(red, tempX, tempY)) {
                up = false;
                upDateMoveDown(red);
            }else if (canMoveRight(red, tempX, tempY)) {
                upDateMoveRight(red);
                right = true;
            }

        } else {
            _else = true;
            if (rootY > red.point.y) {
                if (canMoveDown(red, tempX, tempY) && down) {
                    _else = false;
                    right = true;
                    left = true;
                    upDateMoveDown(red);
                }
                else if (canMoveRight(red, tempX, tempY) && right){
                    up = false;
                    upDateMoveRight(red);
                }
                else if (canMoveLeft(red, x, tempY)) {
                    right = false;
                    up = false;
                    upDateMoveLeft(red);
                } else if (canMoveUp(red, tempX, y)) {
                    up = true;
                    upDateMoveUp(red);
                }

            } else if (rootY < red.point.y) {
                if (canMoveUp(red, tempX, y) && up) {
                    _else = false;
                    upDateMoveUp(red);
                }
                else if (canMoveRight(red, tempX, tempY) && right){
                    down = false;
                    upDateMoveRight(red);
                }
                else if (canMoveLeft(red, x, tempY)) {
                    upDateMoveLeft(red);
                    down = false;
                    right = false;

                } else if (canMoveDown(red, tempX, tempY) && down) {
                    upDateMoveDown(red);
                    down = true;
                }
            }
        }
    }
}

