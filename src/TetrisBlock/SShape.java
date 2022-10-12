/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TetrisBlock;

import tetrisgame.TetrisBlock;
import java.awt.Color;
/**
 *
 * @author s9708
 */
public class SShape extends TetrisBlock{
    public SShape() {
        
        super(new int[][]{{1,0},
                          {1,1},
                          {0,1}},Color.MAGENTA);
    }
    
}
