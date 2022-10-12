/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TetrisBlock;

import java.awt.Color;
import tetrisgame.TetrisBlock;

/**
 *
 * @author s9708
 */
public class IShape extends TetrisBlock {

    public IShape() {
        super(new int[][]{{1, 1, 1, 1}}, Color.cyan);
    }

    @Override
    public void rorate() {
        super.rorate();
        if (this.getWidth() == 1) {
            this.setX(this.getX() + 1);
            this.setY(this.getY() - 1);
        } else {
            this.setX(this.getX() - 1);
            this.setY(this.getY() + 1);
        }
    }
}