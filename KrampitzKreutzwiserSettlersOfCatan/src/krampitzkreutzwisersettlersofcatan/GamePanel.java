/*
 * Lukas Krampitz and Evan Kreutzwiser
 * Nov 8, 2020
 * The JPanel that is the main part of the game
 */
package krampitzkreutzwisersettlersofcatan;

import dataFiles.OldCode;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.ImageIcon;
import textures.ImageRef;

/**
 * @author Evan
 * @author Tacitor
 */
public class GamePanel extends javax.swing.JPanel {

    private final GameFrame superFrame; //ref to the JFrame this kept in

    private final ArrayList<Tile> tiles; //All the data for the tiles in one convient place
    private final ArrayList<NodeSettlement> settlementNodes; // Every settlement node of the board
    private final ArrayList<NodeRoad> roadNodes; // Every road node of the board
    private final int[] tileTypes = new int[]{1, 3, 4, 2, 2, 5, 1, 4, 3, 0, 4, 2, 4, 5, 1, 2, 3, 3, 5}; //the type of tile from left to right, and top to bottom
    private final int[][] tilePos = new int[19 * 2][2]; //the x, y position to draw the tile images
    
    //images for the cards
    private final static Image CARD_CLAY = new ImageIcon(ImageRef.class.getResource("cardClay.png")).getImage();
    private final static Image CARD_WHEAT = new ImageIcon(ImageRef.class.getResource("cardWheat.png")).getImage();
    private final static Image CARD_ORE = new ImageIcon(ImageRef.class.getResource("cardOre.png")).getImage();
    private final static Image CARD_SHEEP = new ImageIcon(ImageRef.class.getResource("cardSheep.png")).getImage();
    private final static Image CARD_WOOD = new ImageIcon(ImageRef.class.getResource("cardWood.png")).getImage();

    //images for the roads
    private final static Image RED_ROAD_H = new ImageIcon(ImageRef.class.getResource("redRoadH.png")).getImage(); //horizontal road
    private final static Image BLUE_ROAD_H = new ImageIcon(ImageRef.class.getResource("blueRoadH.png")).getImage();
    private final static Image RED_ROAD_R = new ImageIcon(ImageRef.class.getResource("redRoadR.png")).getImage(); //diagonal to the right (refernce point is the top of the road)
    private final static Image BLUE_ROAD_R = new ImageIcon(ImageRef.class.getResource("blueRoadR.png")).getImage();
    private final static Image RED_ROAD_L = new ImageIcon(ImageRef.class.getResource("redRoadL.png")).getImage(); //diagonal to the left
    private final static Image BLUE_ROAD_L = new ImageIcon(ImageRef.class.getResource("blueRoadL.png")).getImage();

    //images for the settlements
    private final static Image BLUE_HOUSE_L = new ImageIcon(ImageRef.class.getResource("blueHouseL.png")).getImage();
    private final static Image RED_HOUSE_L = new ImageIcon(ImageRef.class.getResource("redHouseL.png")).getImage();
    private final static Image BLUE_HOUSE_S = new ImageIcon(ImageRef.class.getResource("blueHouseS.png")).getImage();
    private final static Image RED_HOUSE_S = new ImageIcon(ImageRef.class.getResource("redHouseS.png")).getImage();

    //image for the thief
    private final static Image THIEF = new ImageIcon(ImageRef.class.getResource("thief.png")).getImage();

    //the image for the water ring
    private final Image WATER_RING;

    /**
     * Creates new form NewGamePanel
     *
     * @param frame ref to the frame
     */
    public GamePanel(GameFrame frame) {
        // Initalize variables
        superFrame = frame; //save refernce
        tiles = new ArrayList(); //init the master tile array list
        settlementNodes = new ArrayList(); // Init the settlement node array list
        roadNodes = new ArrayList(); // Init the road node array list

        // Initialize the window and board
        initComponents(); //add the buttons and other Swing elements

        loadTilePos(); //read in the coodinates of where each of the 19 tiles goes
        loadTiles(); //load the ArrayList of tiles with position and type data
        loadNodes(); // Create and link all of the board's settlement and road nodes
        WATER_RING = new ImageIcon(ImageRef.class.getResource("waterRing.png")).getImage();

        // Add the mouse listener that calls the mouse click event handler
        addMouseListener(new MouseAdapter() {
            /**
             * Triggered when the user clicks on the game panel.
             * Calls the game panel's click event handler.
             * @param evt The event representing the mouse click
             */
            @Override
            public void mouseClicked(MouseEvent evt) {
                // Send the mouse event over to the game panel's click handlers
                mouseClick(evt);
            }
        });
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

    /**
     * Handles mouse input, based on the state of the game
     * @param event The event triggered by the mouse click
     */
    public void mouseClick(MouseEvent event) {
        // TODO: Add click handling code
        System.out.println("Click recieved");
    }
    
    //overrides paintComponent in JPanel class
    //performs custom painting
    /**
     * This does the set up for the drawing in 2d graphics
     *
     * @param g
     */
    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);//does the necessary work to prepare the panel for drawing
        draw(g); //add the custom drawing (the game)
    }

    /**
     * Draw the game.
     *
     * @param g
     */
    private void draw(Graphics g) {
        //the Graphics2D class is the class that handles all the drawing
        //must be casted from older Graphics class in order to have access to some newer methods
        Graphics2D g2d = (Graphics2D) g;
        //draw a string on the panel        
        g2d.drawString("Java 2D", 50, 50); //(text, x, y)        }

        System.out.println("GamePannel draw function called"); //and indecation of how many times the draw function runs

        //draw the ring of water
        g2d.drawImage(WATER_RING, 1920 / 2 - WATER_RING.getWidth(null) / 2, 1080 / 2 - WATER_RING.getHeight(null) / 2, null);

        //draw the board using the new way. the coordinates inside the tile objects come from the old way of drawing the baord
        for (int i = 0; i < 19; i++) {
            g2d.drawImage(tiles.get(i).getImage(), tiles.get(i).getXPos(), tiles.get(i).getYPos(), null);

            //check where the thief is and draw it there
            if (tiles.get(i).hasThief()) {

                //draw the thief
                g2d.drawImage(THIEF, tiles.get(i).getXPos() + 150 / 2 - 12, tiles.get(i).getYPos() + 130 / 2 - 56 / 2, null);
            }
        }

        //draw testing art
        //cards
        g2d.drawImage(CARD_CLAY, 100, 1080 - 125, null); //space them 100 pixels apart and align the hight to 2 from the bottom
        g2d.drawImage(CARD_ORE, 200, 1080 - 125, null);
        g2d.drawImage(CARD_WHEAT, 300, 1080 - 125, null);
        g2d.drawImage(CARD_WOOD, 400, 1080 - 125, null);
        g2d.drawImage(CARD_SHEEP, 500, 1080 - 125, null);

        //settlements/houses
        g2d.drawImage(RED_HOUSE_L, (tiles.get(5).getXPos()), (tiles.get(5).getYPos() + 4), null); //align x to the tiles corner
        g2d.drawImage(BLUE_HOUSE_L, (tiles.get(14).getXPos()), (tiles.get(14).getYPos() + 4), null); //agign y to the tile with offset a spacer
        g2d.drawImage(RED_HOUSE_S, (tiles.get(2).getXPos()), (tiles.get(5).getYPos() + 4), null); //align x to the tiles corner
        g2d.drawImage(BLUE_HOUSE_S, (tiles.get(9).getXPos()), (tiles.get(14).getYPos() + 4), null); //agign y to the tile with offset a spacer

        // Draw the 72 road nodes
        NodeRoad road;
        Image image;
        for (int i = 0; i < 72; i++) {
            road = roadNodes.get(i);
            switch (road.getOrientation()) {
                case 0: // Horizontal road ( -- )
                    // Store the road image for the player's color
                    if (road.getPlayer() == 1 || road.getPlayer() == 0) {
                        image = RED_ROAD_H;
                    } else {
                        image = BLUE_ROAD_H;
                    }
                    break;
                case 1: // Road pointing to the top left ( \ ) 
                    // Store the road image for the player's color
                    if (road.getPlayer() == 1 || road.getPlayer() == 0) {
                        image = RED_ROAD_L;
                    } else {
                        image = BLUE_ROAD_L;
                    }
                    break;
                case 2: // Road pointing to the top right ( / ) 
                    // Store the road image for the player's color
                    if (road.getPlayer() == 1 || road.getPlayer() == 0) {
                        image = RED_ROAD_R;
                    } else {
                        image = BLUE_ROAD_R;
                    }
                    break;
                default: // Make the compiler happy and error handling
                    image = RED_ROAD_H; // Just add an image so theres something to render
                    break;
            }

            // Draw the road image saved above, at the node's position
            g2d.drawImage(image, road.getXPos() - RED_ROAD_H.getWidth(null) / 2,
                    road.getYPos() - image.getHeight(null) / 2, null);
        }

        // TODO: Replace this with image drawing
        // Draw squares to represent settlements
        NodeSettlement node;
        for (int i = 0; i < 54; i++) {
            node = settlementNodes.get(i);
            g2d.drawRect(node.getXPos() - 15, node.getYPos() - 15, 30, 30);
        }

        //add alignment lines
        g2d.drawLine(1920 / 2, 0, 1920 / 2, 1080);
        g2d.drawLine(0, 1080 / 2, 1920, 1080 / 2);
    }

    /**
     * Load the data into the mater Tile ArrayList
     */
    private void loadTiles() {
        Tile newTile;
        for (int i = 0; i < 19; i++) {
            //int r = (int) (Math.random() * 6);
            newTile = new Tile(tilePos[i][0], tilePos[i][1], tileTypes[i]); //set the position and a type based on the text file
            //check for the desert tile and start the thief off there
            if (newTile.getType() == 0) { //type 0 is the desert type
                newTile.setThief(true);
            }
            tiles.add(newTile);
        }
        // Add a null entry for the settlement noed file to reference where there isnt a connection
        tiles.add(null);
    }

    /**
     * Create the board's node network of settlements and roads. All of the node
     * data is read from files
     */
    public final void loadNodes() {

        // Declare node attribute arrays
        // Settlement attribute arrayLists
        int settlementX;
        int settlementY;
        int settlementLinkToRoad1[] = new int[54];
        int settlementLinkToRoad2[] = new int[54];
        int settlementLinkToRoad3[] = new int[54];
        int settlementLinkToHex1[] = new int[54];
        int settlementLinkToHex2[] = new int[54];
        int settlementLinkToHex3[] = new int[54];
        // Road attribute arrayLists
        int roadX;
        int roadY;
        int roadRotation;
        int roadLinkToSettlement1[] = new int[72];
        int roadLinkToSettlement2[] = new int[72];

        // Declare variables
        Scanner fileReader;
        InputStream settlementFile = OldCode.class.getResourceAsStream("settlementData.txt");
        InputStream roadFile = OldCode.class.getResourceAsStream("roadData.txt");

        // Try to read the settlement file
        try {
            // Create the scanner to read the file
            fileReader = new Scanner(settlementFile);

            // Read the entire file into an array
            for (int i = 0; i < 54; i++) {
                // Read the position data
                settlementX = fileReader.nextInt();
                settlementY = fileReader.nextInt();
                // Read the road connection data
                settlementLinkToRoad1[i] = fileReader.nextInt();
                settlementLinkToRoad2[i] = fileReader.nextInt();
                settlementLinkToRoad3[i] = fileReader.nextInt();
                // Read the tile connection data
                settlementLinkToHex1[i] = fileReader.nextInt();
                settlementLinkToHex2[i] = fileReader.nextInt();
                settlementLinkToHex3[i] = fileReader.nextInt();

                // Add an unlinked settlement
                settlementNodes.add(new NodeSettlement(settlementX, settlementY));

                // Blank line is skipped by int reader
            }
        } catch (Exception e) {
            // Output the jsvs error to the standard output
            System.out.println("Error reading settlement data file: " + e);
        }

        // Try to read the road file
        try {
            // Create the scanner to read the file
            fileReader = new Scanner(roadFile);

            // Read the entire file into an array
            for (int i = 0; i < 72; i++) {
                // Read the position data
                roadX = fileReader.nextInt();
                roadY = fileReader.nextInt();
                // Read the rotation
                roadRotation = fileReader.nextInt();
                // Read the road connection data
                roadLinkToSettlement1[i] = fileReader.nextInt();
                roadLinkToSettlement2[i] = fileReader.nextInt();

                // Add a road linked with the settlements created above 
                roadNodes.add(new NodeRoad(roadX, roadY, roadRotation,
                        settlementNodes.get(roadLinkToSettlement1[i]),
                        settlementNodes.get(roadLinkToSettlement2[i])));

                // Blank line is skipped by int reader
            }
            // Add a null entry for the file to reference where there isnt a connection
            roadNodes.add(null);
        } catch (Exception e) {
            // Output the jsvs error to the standard output
            System.out.println("Error reading settlement data file: " + e);
        }

        // Link the settlements to the tiles and roads
        for (int i = 0; i < 54; i++) {
            // For every settlement

            // Set the roads of the settlement to the roads at the index store in the arrays
            // There are 3 arrays of roads to link to the 54 different settlements
            settlementNodes.get(i).setRoad(1, roadNodes.get(settlementLinkToRoad1[i]));
            settlementNodes.get(i).setRoad(2, roadNodes.get(settlementLinkToRoad2[i]));
            settlementNodes.get(i).setRoad(3, roadNodes.get(settlementLinkToRoad3[i]));

            // Repeat to link the 3 hexagons to the settlement
            settlementNodes.get(i).setTile(1, tiles.get(settlementLinkToHex1[i]));
            settlementNodes.get(i).setTile(2, tiles.get(settlementLinkToHex2[i]));
            settlementNodes.get(i).setTile(3, tiles.get(settlementLinkToHex3[i]));

        }
    }

    /**
     * read in the tile positions
     */
    public final void loadTilePos() {

        // Declare variables
        Scanner fileReader;
        InputStream file = OldCode.class.getResourceAsStream("tilePos.txt");

        // Try to read the file
        try {
            // Create the scanner to read the file
            fileReader = new Scanner(file);

            // Read the entire file into an array
            for (int i = 0; i < 19; i++) {
                for (int j = 0; j < 2; j++) {
                    // Read the line of the file into the next index
                    tilePos[i][j] = Integer.parseInt(fileReader.nextLine());
                }

                //skip the next blank line but only if the scanner is not at the end
                if (i < 18) {
                    fileReader.nextLine();
                }
            }
        } catch (Exception e) {
            // Output the jsvs error to the standard output
            System.out.println("Error reading Tile Position file: " + e);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backBtn;
    // End of variables declaration//GEN-END:variables
}
