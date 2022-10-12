/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tetrisgame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author s9708
 */
public class GameThread extends Thread {

    private GameArea ga;
    private GameForm gf;
    private int score;
    private int level = 1;
    private int scorePerLevel = 2;
    private int pause = 600;
    private int speedup = 50;

    public GameThread(GameArea ga, GameForm gf) {
        this.ga = ga;
        this.gf = gf;
    }

    @Override
    public void run() {
        while (true) {
            ga.spawnBlock();
            while (ga.moveBlockDown()) {
                try {
                    Thread.sleep(pause);              //停頓點
                } catch (InterruptedException ex) {
                    Logger.getLogger(GameThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ga.isBlockOutOfBounds()) {
                Graphics g=ga.getGraphics();
                g.setFont(new Font("細明本", Font.PLAIN, 38));
                g.setColor(Color.RED);                       //GameOver
                g.drawString("GameOver",5, 160);
                break;
            }
            ga.moveBlockToBackground();
            score += ga.clearLines();
            gf.updateScore(score);

            int lvl = score / scorePerLevel + 1;
            if (lvl > level) {
                level = lvl;                     //每兩條+50毫秒
                gf.updateLevel(level);
                pause -= speedup;
            }
        }
    }
}
