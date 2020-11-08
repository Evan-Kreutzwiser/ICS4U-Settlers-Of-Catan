/*
 * Lukas Krampitz
 * Nov 8, 2020
 * The JPanel that is the main part of the game
 */
package krampitzkreutzwisersettlersofcatan;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author Tacitor
 */
public class GamePanel extends javax.swing.JPanel {
    
    private final GameFrame superFrame;
    private Image WOOD_TILE; 
    private Image WHEAT_TILE; 
    private Image SHEEP_TILE; 
    private Image ORE_TILE; 
    private Image DESERT_TILE; 
    private Image CLAY_TILE; 

    /**
     * Creates new form NewGamePanel
     */
    public GamePanel(GameFrame frame) {
        superFrame = frame;
        initComponents();
        loadImage();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backBtn = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(1920, 1080));
        setMinimumSize(new java.awt.Dimension(1920, 1080));
        setPreferredSize(new java.awt.Dimension(1920, 1080));

        backBtn.setText("<  Exit");
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(backBtn)
                .addContainerGap(1845, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(1046, Short.MAX_VALUE)
                .addComponent(backBtn)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        // Hide this window and show the main menu
        superFrame.getMainMenu().setVisible(true); //show the main menu
        superFrame.setVisible(false); //hide the parent frame 
    }//GEN-LAST:event_backBtnActionPerformed

    //overrides paintComponent in JPanel class
    //performs custom painting
    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);//does the necessary work to prepare the panel for drawing
        draw(g); //add the custom drawing (the game)
    }

    //draw the game
    private void draw(Graphics g) {
        //the Graphics2D class is the class that handles all the drawing
        //must be casted from older Graphics class in order to have access to some newer methods
        Graphics2D g2d = (Graphics2D) g;
        //draw a string on the panel        
        g2d.drawString("Java 2D", 50, 50); //(text, x, y)        }
        
        //draw center tile
        g2d.drawImage(DESERT_TILE, 1920 / 2 - (150 / 2), 1080 / 2 - (130 / 2), null); //draw the image with the upper left corner on the x,y pos
        
        
        //draw 1 below center
        g2d.drawImage(WOOD_TILE, 1920 / 2 - (150 / 2), 1080 / 2 - (130 / 2) + 130, null); 
        //draw 2 below center
        g2d.drawImage(CLAY_TILE, 1920 / 2 - (150 / 2), 1080 / 2 - (130 / 2) + (130 * 2), null); 
        
        
        //draw 1 above center
        g2d.drawImage(ORE_TILE, 1920 / 2 - (150 / 2), 1080 / 2 - (130 / 2) - 130, null); 
        //draw 2 above center
        g2d.drawImage(SHEEP_TILE, 1920 / 2 - (150 / 2), 1080 / 2 - (130 / 2) - (130 * 2), null); //(x,y) (cut the screen in half then subtract half the tile size to center then add the y offset, cut the screen in half then subtract half the tile size to center then add the x offset)
        

        //draw 1 above center, 1 right
        g2d.drawImage(WHEAT_TILE, 1920 / 2 - (150 / 2) + (112), 1080 / 2 - (130 / 2) - (130 / 2), null); //for x pos: center it and then shift by 112 with is the length requires to align properly
        //draw 1 below center, 1 right
        g2d.drawImage(WHEAT_TILE, 1920 / 2 - (150 / 2) + (112), 1080 / 2 - (130 / 2) + (130 / 2), null); 
        //draw 2 above center, 1 right
        g2d.drawImage(CLAY_TILE, 1920 / 2 - (150 / 2) + (112), 1080 / 2 - (130 / 2) - (130 / 2) - (130), null); //for the y pos: first center, then align to the next row of hexes, then move it up by one full hight
        //draw 2 below center, 1 right
        g2d.drawImage(WOOD_TILE, 1920 / 2 - (150 / 2) + (112), 1080 / 2 - (130 / 2) + (130 / 2) + (130), null); 
        
        
        //draw 1 above center, 1 left
        g2d.drawImage(WHEAT_TILE, 1920 / 2 - (150 / 2) - (112), 1080 / 2 - (130 / 2) - (130 / 2), null); //for x pos: center it and then shift by 112 with is the length requires to align properly
        //draw 1 below center, 1 left
        g2d.drawImage(WHEAT_TILE, 1920 / 2 - (150 / 2) - (112), 1080 / 2 - (130 / 2) + (130 / 2), null); 
        //draw 2 above center, 1 left
        g2d.drawImage(CLAY_TILE, 1920 / 2 - (150 / 2) - (112), 1080 / 2 - (130 / 2) - (130 / 2) - (130), null); //for the y pos: first center, then align to the next row of hexes, then move it up by one full hight
        //draw 2 below center, 1 left
        g2d.drawImage(WOOD_TILE, 1920 / 2 - (150 / 2) - (112), 1080 / 2 - (130 / 2) + (130 / 2) + (130), null); 
        
        //draw right most column
        //draw 2 right of center
        g2d.drawImage(SHEEP_TILE, 1920 / 2 - (150 / 2) + (112 * 2), 1080 / 2 - (130 / 2), null);
        //draw 2 right of center, 1 above
        g2d.drawImage(SHEEP_TILE, 1920 / 2 - (150 / 2) + (112 * 2), 1080 / 2 - (130 / 2) - (130), null);
        //draw 2 right of center, 1 below
        g2d.drawImage(SHEEP_TILE, 1920 / 2 - (150 / 2) + (112 * 2), 1080 / 2 - (130 / 2) + (130), null);
        
        //draw left most column
        //draw 2 left of center
        g2d.drawImage(SHEEP_TILE, 1920 / 2 - (150 / 2) - (112 * 2), 1080 / 2 - (130 / 2), null);
        //draw 2 left of center, 1 above
        g2d.drawImage(SHEEP_TILE, 1920 / 2 - (150 / 2) - (112 * 2), 1080 / 2 - (130 / 2) - (130), null);
        //draw 2 left of center, 1 below
        g2d.drawImage(SHEEP_TILE, 1920 / 2 - (150 / 2) - (112 * 2), 1080 / 2 - (130 / 2) + (130), null);
        
        //add alignment lines
        g2d.drawLine(1920 / 2, 0, 1920 / 2, 1080);
        g2d.drawLine(0, 1080 / 2, 1920, 1080 / 2);
    }
    
    private void loadImage() {
        try {
            WOOD_TILE = new ImageIcon("src\\textures\\wood.png").getImage();
            CLAY_TILE = new ImageIcon("src\\textures\\clay.png").getImage();
            DESERT_TILE = new ImageIcon("src\\textures\\desert.png").getImage();
            ORE_TILE = new ImageIcon("src\\textures\\ore.png").getImage();
            SHEEP_TILE = new ImageIcon("src\\textures\\sheep.png").getImage();
            WHEAT_TILE = new ImageIcon("src\\textures\\wheat.png").getImage();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backBtn;
    // End of variables declaration//GEN-END:variables
}
