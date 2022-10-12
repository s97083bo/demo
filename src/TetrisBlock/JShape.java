/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TetrisBlock ;

import java.awt.Color;
import tetrisgame.TetrisBlock;

/**
 *
 * @author s9708
 */
public class JShape extends TetrisBlock{

    public JShape() {
        
        super(new int[][]{{0,1},
                          {0,1},
                          {1,1}},Color.ORANGE);
    }
    
}
