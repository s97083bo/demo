/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tetrisgame;

/**
 *
 * @author s9708
 */
import java.awt.*;
import java.util.Random;

public class TetrisBlock {

    private int[][] shape;
    private Color color;
    private int x, y;
    private int[][][] shapes;
    private int current;
    //private Color[] avalibleColor={Color.BLUE,Color.GREEN};

    public TetrisBlock(int[][] shape,Color color) {
        this.shape = shape;
        this.color=color;
        initShape();
    }

    public void initShape() {
        shapes = new int[4][][];
        for (int i = 0; i < 4; i++) {
            int r = shape[0].length;
            int c = shape.length;
            shapes[i] = new int[r][c];                 //設定好方塊的4種轉向
            for (int j = 0; j < r; j++) {
                for (int k = 0; k < c; k++) {
                    shapes[i][j][k] = shape[c - k - 1][j];
                }
            }
            shape = shapes[i];
        }

    }

    public void spawn(int gridWidth) {
        Random r =new Random();
        current=r.nextInt(4);
        shape=shapes[current];                       //隨機生成一種方向的方塊  出現在中間地圖外
        y = -getHeight();
        x = (gridWidth - getWidth()) / 2;
        //color=avalibleColor[r.nextInt(avalibleColor.length)];
    }

    public int[][] getShape() {
        return shape;
    }

    public Color getColor() {
        return color;
    }

    public int getHeight() {
        return shape.length;
    }

    public int getWidth() {
        return shape[0].length;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public void moveDown() {
        y++;
    }

    public void moveLeft() {
        x--;
    }

    public void moveRight() {
        x++;
    }

    public void rorate() {
        current++;
        if (current >= 4) {               //超過[4] 返回[0]
            current = 0;
        }
        shape = shapes[current];
    }

    public int getBottomEdge() {
        return y + getHeight();     //取得方塊最下面y位置
    }

    public int getRightEdge() {
        return x+getWidth();       //取得方塊最右邊x位置
    }

    public int getLeftEdge() {      //取得方塊最左邊x位置
        return x;
    }
}
