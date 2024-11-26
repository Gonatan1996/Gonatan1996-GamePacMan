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
        if ((generalElement.getPoint().x + generalElement.width > 556)){
            generalElement.getPoint().x = 0;
        }
    }

    public void flipDirectionLeft(GeneralElement generalElement){
        if ((generalElement.getPoint().x) == 0) {
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




}
