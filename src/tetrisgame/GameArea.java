/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tetrisgame;

import TetrisBlock.*;
import java.awt.Color;
import java.awt.*;
import java.util.Random;
import javax.swing.*;

public class GameArea extends JPanel {

    private int gridRows;
    private int gridColumns;
    private int gridCellSize;
    private Color[][] background;

    private TetrisBlock block;
    private TetrisBlock[] blocks;

    public GameArea(JPanel placeholder, int columns) {
        placeholder.setVisible(false);
        this.setBounds(placeholder.getBounds());
        this.setBackground(placeholder.getBackground());
        this.setBorder(placeholder.getBorder());

        gridColumns = columns;
        gridCellSize = this.getBounds().width / gridColumns;
        gridRows = this.getBounds().height / gridCellSize;

        background = new Color[gridRows][gridColumns];
        blocks = new TetrisBlock[]{new IShape(), new JShape(), new LShape(), new OShape(), new SShape(), new TShape(), new ZShape()};
    }

    public void spawnBlock() {
        Random r = new Random();
        block = blocks[r.nextInt(blocks.length)];        //隨機生成某一種類型的方塊
        block.spawn(gridColumns);
    }

    public boolean isBlockOutOfBounds() {
        if (block.getY() < 0) {
            block = null;
            return true;                            //判斷是否已經疊到頂了 ((gameover
        }
        return false;
    }

    public boolean moveBlockDown() {
        if (checkBottom() == false) {

            return false;
        }
        block.moveDown();                      //確認下面沒東西 方塊往下一格
        repaint();
        return true;
    }

    public void moveBlockLeft() {
        if (block == null) {
            return;
        }
        if (checkLeft() == false) {          //往左移動
            return;
        }
        block.moveLeft();
        repaint();
    }

    public void moveBlockRight() {
        if (block == null) {
            return;
        }                                          //往右移動
        if (checkRight() == false) {
            return;
        }
        block.moveRight();
        repaint();
    }

    public void rotateBlock() {
        if (block == null) {
            return;
        }
        block.rorate();                                      //確認 不會撞到左右邊界 轉向

        if (block.getLeftEdge() < 0) {
            block.setX(0);
        }
        if(block.getRightEdge()>=gridColumns){
        block.setX(gridColumns-block.getWidth());
        }
        if(block.getBottomEdge()>=gridRows){
        block.setY(gridRows-block.getHeight());
        }
        repaint();
    }

    public void dropBlock() {
        if (block == null) {
            return;
        }
        while (checkBottom()) {                    //急落下 
            block.moveDown();
        }
        repaint();
    }

    public boolean checkBottom() {
        if (block.getBottomEdge() == gridRows) {
            return false;
        }
        int[][] shape = block.getShape();
        int w = block.getWidth();
        int h = block.getHeight();
        for (int col = 0; col < w; col++) {
            for (int row = h - 1; row >= 0; row--) {
                if (shape[row][col] != 0) {                    //確認底下是否有其他方塊
                    int x = col + block.getX();
                    int y = row + block.getY() + 1;
                    if (y < 0) {   //起始不算
                        break;
                    }
                    if (background[y][x] != null) {
                        return false;
                    }
                    break;
                }
            }
        }
        return true;
    }

    private boolean checkLeft() {
        if (block.getLeftEdge() == 0) {
            return false;
        }
        int[][] shape = block.getShape();
        int w = block.getWidth();                                      //移動前 確認方塊左
        int h = block.getHeight();
        for (int row = 0; row < h; row++) {
            for (int col = 0; col < w; col++) {
                if (shape[row][col] != 0) {
                    int x = col + block.getX() - 1;
                    int y = row + block.getY();
                    if (y < 0) {
                        break;
                    }
                    if (background[y][x] != null) {
                        return false;
                    }
                    break;
                }
            }
        }
        return true;
    }

    private boolean checkRight() {
        if (block.getRightEdge() == gridColumns) {
            return false;
        }
        int[][] shape = block.getShape();
        int w = block.getWidth();
        int h = block.getHeight();
        for (int row = 0; row < h; row++) {
            for (int col = w - 1; col >= 0; col--) {         //確認方塊右
                if (shape[row][col] != 0) {
                    int x = col + block.getX() + 1;
                    int y = row + block.getY();
                    if (y < 0) {
                        break;
                    }
                    if (background[y][x] != null) {
                        return false;
                    }
                    break;
                }
            }
        }
        return true;
    }

    public int clearLines() {
        boolean linefilled;
        int linesCleared = 0;
        for (int r = gridRows - 1; r >= 0; r--) {
            linefilled = true;                                      //確認是否有行可以消
            for (int c = 0; c < gridColumns; c++) {
                if (background[r][c] == null) {
                    linefilled = false;
                    break;
                }
            }
            if (linefilled) {                                       //消行集合體(?
                linesCleared++;
                clearLine(r);
                shiftDown(r);
                clearLine(0);
                r++;      //重新判斷同一行
                repaint();
            }
        }
        return linesCleared;
    }

    private void clearLine(int r) {
        for (int i = 0; i < gridColumns; i++) {             //負責消行
            background[r][i] = null;
        }
    }

    private void shiftDown(int r) {
        for (int row = r; row > 0; row--) {
            for (int col = 0; col < gridColumns; col++) {      //負責消行後往下移動
                background[row][col] = background[row - 1][col];
            }
        }
    }

    public void moveBlockToBackground() {
        int[][] shape = block.getShape();
        int h = block.getHeight();
        int w = block.getWidth();
        int xPos = block.getX();
        int yPos = block.getY();
        Color color = block.getColor();                 //方塊固定後 移至背景
        for (int r = 0; r < h; r++) {
            for (int c = 0; c < w; c++) {
                if (shape[r][c] == 1) {
                    background[r + yPos][c + xPos] = color;
                }
            }
        }
    }

    private void drawBlock(Graphics g) {
        int h = block.getHeight();
        int w = block.getWidth();
        Color c = block.getColor();
        int[][] shape = block.getShape();

        for (int row = 0; row < h; row++) {            //顯示正在操控的方塊
            for (int col = 0; col < w; col++) {
                if (shape[row][col] == 1) {

                    int x = (block.getX() + col) * gridCellSize;
                    int y = (block.getY() + row) * gridCellSize;
                    drawGridSquare(g, c, x, y);
                }

            }

        }
    }

    private void drawBackground(Graphics g) {
        Color color;
        for (int r = 0; r < gridRows; r++) {
            for (int c = 0; c < gridColumns; c++) {   //顯示已成為背景的方塊
                color = background[r][c];
                if (color != null) {
                    int x = c * gridCellSize;
                    int y = r * gridCellSize;
                    drawGridSquare(g, color, x, y);
                }
            }

        }
    }

    private void drawGridSquare(Graphics g, Color color, int x, int y) {
        g.setColor(color);
        g.fillRect(x, y, gridCellSize, gridCellSize);
        g.setColor(Color.black);                        //就...顯示
        g.drawRect(x, y, gridCellSize, gridCellSize);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBackground(g);
        for (int i = 0; i < gridRows; i++) {           //背景格子

            for (int j = 0; j < gridColumns; j++) {
                g.drawRect(j * gridCellSize, i * gridCellSize, gridCellSize, gridCellSize);

            }
        }
        drawBlock(g);
    }
}
