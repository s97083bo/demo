package tetrisgame;
import java.awt.event.ActionEvent;
import javax.swing.*;
public class GameForm extends JFrame {
    private GameArea ga;
    public GameForm() {
        initComponents();
        ga=new GameArea(gameAreaPlaceholder,10);
        this.add(ga);
        intiControls();
        startGame();
    }
    
    private void intiControls(){
    InputMap im=this.getRootPane().getInputMap();
    ActionMap am=this.getRootPane().getActionMap();
    
    im.put(KeyStroke.getKeyStroke("RIGHT"), "right");
    im.put(KeyStroke.getKeyStroke("LEFT"), "left");
    im.put(KeyStroke.getKeyStroke("UP"), "up");
    im.put(KeyStroke.getKeyStroke("DOWN"), "down");
    
    am.put("right",new AbstractAction(){
    @Override
    public void actionPerformed(ActionEvent e){
        ga.moveBlockRight();
    }
    });
    am.put("left",new AbstractAction(){
    @Override
    public void actionPerformed(ActionEvent e){
        ga.moveBlockLeft();
    }
    });
    am.put("up",new AbstractAction(){
    @Override
    public void actionPerformed(ActionEvent e){
        ga.rotateBlock();
    }
    });
    am.put("down",new AbstractAction(){
    @Override
    public void actionPerformed(ActionEvent e){
        ga.dropBlock();
    }
    });
    
    
    
    }
    public void startGame(){
        new GameThread(ga,this).start();
    }
    
    public void updateScore(int score){
        ScoreDisplay.setText("Score: "+score);
    }
    public void updateLevel(int level){
        LevelDisplay.setText("Level: "+level);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        gameAreaPlaceholder = new javax.swing.JPanel();
        ScoreDisplay = new javax.swing.JLabel();
        LevelDisplay = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        gameAreaPlaceholder.setBackground(new java.awt.Color(204, 255, 255));
        gameAreaPlaceholder.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        javax.swing.GroupLayout gameAreaPlaceholderLayout = new javax.swing.GroupLayout(gameAreaPlaceholder);
        gameAreaPlaceholder.setLayout(gameAreaPlaceholderLayout);
        gameAreaPlaceholderLayout.setHorizontalGroup(
            gameAreaPlaceholderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 196, Short.MAX_VALUE)
        );
        gameAreaPlaceholderLayout.setVerticalGroup(
            gameAreaPlaceholderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 296, Short.MAX_VALUE)
        );

        ScoreDisplay.setText("Score:0");

        LevelDisplay.setText("Level:1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(142, Short.MAX_VALUE)
                .addComponent(gameAreaPlaceholder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ScoreDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LevelDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(56, 56, 56))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(gameAreaPlaceholder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(ScoreDisplay)
                .addGap(33, 33, 33)
                .addComponent(LevelDisplay)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GameForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LevelDisplay;
    private javax.swing.JLabel ScoreDisplay;
    private javax.swing.JPanel gameAreaPlaceholder;
    // End of variables declaration//GEN-END:variables
}
