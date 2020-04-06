package edu.project.fin;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class GUI extends JFrame implements KeyListener {
    private static final long serialVersionUID = 1L;
    private JFrame frame = new JFrame();
    private Player player;
    private Boolean gameStart = false;
    private Boolean pauseThread = false;
    private int firstLaunch = 0;
    private int width = 450;
    private int height = 500;
    private int xPlayer = 7;
    private int yPlayer = 7;
    private int minutes = 0;
    private int seconds = 45;
    private static final int TILES = 12;
    private static final int GAP = 1;
    private JTextField heartPane = new JTextField();
    private JTextField gameTimer = new JTextField();
    private JTextField pauseText = new JTextField();
    private JPanel[][] tilePanels = new JPanel[TILES][TILES];

    private class AlienThread extends Thread {
	@Override
	public void run() {
	    Alien alien = new Marshmallow_man_Alien();
	    alienDraw(alien);
	}
    }

    private class SnakeThread extends Thread {
	@Override
	public void run() {
	    Alien alien = new Snake_Alien();
	    snakeDraw(alien);
	}
    }

    private class OgreThread extends Thread {
	@Override
	public void run() {
	    Alien alien = new Ogre_Alien();
	    ogreDraw(alien);
	}
    }

    private class ItemThread extends Thread {
	@Override
	public void run() {
	    Item item = new Heart();
	    dropItem(item);
	}
    }

    private class HealthThread extends Thread {
	@Override
	public void run() {
	    while (gameStart == true) {
		try {
		    AlienThread.sleep(50);
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
		heartPane.setText(" ");
		for (int i = 0; i < player.getHealth(); i++) {
		    heartPane.setText(heartPane.getText() + "\u2665");
		}
	    }
	}
    }

    private class TimeThread extends Thread {
	@Override
	public void run() {
	    while (player.getAlive() == true) {
		while (pauseThread == true) {
		    try {
			AlienThread.sleep(50);
		    } catch (InterruptedException e) {
			e.printStackTrace();
		    }
		}
		try {
		    AlienThread.sleep(1000);
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
		seconds -= 1;
		if (seconds < 0) {
		    minutes -= 1;
		    if (minutes >= 0) {
			seconds = 59;
		    }

		}
		gameTimer.setText(minutes + ":" + seconds);
	    }
	}
    }

    public GUI(Player player) {
	this.player = player;
    }

    public void startMenu() {
	frame.getContentPane().removeAll();
	frame.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
	JPanel panel = new JPanel();
	frame.getContentPane().add(panel);
	frame.setSize(width, height);
	panel.setLayout(new BorderLayout(0, 0));

	JPanel panel_1 = new JPanel();
	panel.add(panel_1, BorderLayout.CENTER);
	panel_1.setLayout(new GridLayout(3, 1, 0, 10));

	JButton btnStart = new JButton("Start");
	btnStart.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent arg0) {
		gameStart();
	    }
	});
	panel_1.add(btnStart);

	JButton btnQuit = new JButton("Quit");
	btnQuit.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent arg0) {
		System.exit(0);
	    }
	});
	panel_1.add(btnQuit);

	JButton btnCredits = new JButton("Credits");
	btnCredits.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent arg0) {
		credits();
	    }
	});
	panel_1.add(btnCredits);

	JPanel titlePanel = new JPanel();
	panel.add(titlePanel, BorderLayout.NORTH);
	titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 10));

	JLabel lblAlienGame = new JLabel();
	lblAlienGame.setFont(new Font("Helvetica", Font.BOLD, 66));
	lblAlienGame.setText("ALIEN GAME");
	lblAlienGame.setSize(50, 50);
	titlePanel.add(lblAlienGame);

	JPanel westSidePanel = new JPanel();
	FlowLayout flowLayout = (FlowLayout) westSidePanel.getLayout();
	flowLayout.setHgap(60);
	panel.add(westSidePanel, BorderLayout.WEST);

	JPanel eastSidePanel = new JPanel();
	FlowLayout flowLayout_1 = (FlowLayout) eastSidePanel.getLayout();
	flowLayout_1.setHgap(60);
	panel.add(eastSidePanel, BorderLayout.EAST);

	JPanel southSidePanel = new JPanel();
	FlowLayout flowLayout_2 = (FlowLayout) southSidePanel.getLayout();
	flowLayout_2.setVgap(15);
	panel.add(southSidePanel, BorderLayout.SOUTH);
	if (firstLaunch == 0) {
	    frame.setLocationRelativeTo(null);
	}
	firstLaunch++;
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setFocusable(true);
	frame.setResizable(false);
	frame.setVisible(true);
    }

    public void gameStart() {
	frame.getContentPane().removeAll();
	frame.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
	frame.setSize(width, height);
	JPanel panel = new JPanel();
	frame.getContentPane().add(panel);
	panel.setLayout(new BorderLayout(0, 0));

	// Top HUD

	JPanel topHUD = new JPanel();
	panel.add(topHUD, BorderLayout.NORTH);
	topHUD.setLayout(new BoxLayout(topHUD, BoxLayout.X_AXIS));

	heartPane.setFont(new Font("Arial", Font.PLAIN, 36));
	heartPane.setBackground(Color.BLACK);
	heartPane.setForeground(Color.RED);
	heartPane.setEditable(false);

	topHUD.add(heartPane);
	gameTimer.setFont(new Font("Arial", Font.PLAIN, 36));
	heartPane.setBackground(Color.BLACK);
	heartPane.setForeground(Color.RED);

	topHUD.add(gameTimer);
	gameTimer.setFont(new Font("Arial", Font.PLAIN, 36));
	gameTimer.setBackground(Color.BLACK);
	gameTimer.setForeground(Color.RED);
	gameTimer.setEditable(false);
	gameTimer.setText(minutes + ":" + seconds);

	// Gameplay

	JPanel gameScreen = new JPanel();

	gameScreen.setBackground(Color.BLACK);
	gameScreen.setForeground(Color.WHITE);
	panel.add(gameScreen, BorderLayout.CENTER);
	gameScreen.setLayout(new GridLayout(TILES, TILES, 0, 0));
	gameScreen.setBackground(Color.BLACK);
	gameScreen.setLayout(new GridLayout(TILES, TILES, GAP, GAP));
	gameScreen.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));

	// Starting Hearts
	for (int i = 0; i < tilePanels.length; i++) {
	    for (int j = 0; j < tilePanels.length; j++) {
		tilePanels[j][i] = new JPanel(new GridBagLayout());
		tilePanels[j][i].setBackground(Color.WHITE);
		gameScreen.add(tilePanels[j][i]);
	    }
	}

	tilePanels[7][7].setBackground(Color.BLUE);
	frame.addKeyListener(this);

	// Bottom HUD
	JPanel bottomHUD = new JPanel();
	TimeThread time = new TimeThread();
	ItemThread item = new ItemThread();
	HealthThread health = new HealthThread();
	AlienThread alien_1 = new AlienThread();
	AlienThread alien_2 = new AlienThread();
	AlienThread alien_3 = new AlienThread();
	AlienThread alien_4 = new AlienThread();
	AlienThread alien_5 = new AlienThread();
	SnakeThread snake_1 = new SnakeThread();
	SnakeThread snake_2 = new SnakeThread();
	SnakeThread snake_3 = new SnakeThread();
	SnakeThread snake_4 = new SnakeThread();
	OgreThread ogre_1 = new OgreThread();
	OgreThread ogre_2 = new OgreThread();
	OgreThread ogre_3 = new OgreThread();
	FlowLayout fl_bottomHUD = (FlowLayout) bottomHUD.getLayout();
	fl_bottomHUD.setVgap(25);
	bottomHUD.setBackground(Color.BLACK);
	bottomHUD.setForeground(Color.WHITE);
	panel.add(bottomHUD, BorderLayout.SOUTH);
	bottomHUD.add(pauseText);
	pauseText.setFont(new Font("Arial", Font.PLAIN, 18));
	pauseText.setBackground(Color.BLACK);
	pauseText.setForeground(Color.RED);
	gameTimer.setEditable(false);
	pauseText.setText("                       ");
	gameStart = true;

	// Start all threads
	time.start();
	item.start();
	health.start();
	alien_1.start();
	alien_2.start();
	alien_3.start();
	alien_4.start();
	alien_5.start();
	snake_1.start();
	snake_2.start();
	snake_3.start();
	snake_4.start();
	ogre_1.start();
	ogre_2.start();
	ogre_3.start();

	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setFocusable(true);
	frame.setResizable(false);
	frame.setVisible(true);
    }

    @Override
    public void keyPressed(KeyEvent e) {
	if (player.getAlive() == true && gameStart == true) {
	    if (e.getKeyCode() == KeyEvent.VK_LEFT && xPlayer > 0 && pauseThread == false) {
		tilePanels[xPlayer][yPlayer].setBackground(Color.WHITE);
		xPlayer -= 1;
		tilePanels[xPlayer][yPlayer].setBackground(Color.BLUE);
		frame.repaint();
	    }

	    if (e.getKeyCode() == KeyEvent.VK_RIGHT && xPlayer < TILES - 1 && pauseThread == false) {
		tilePanels[xPlayer][yPlayer].setBackground(Color.WHITE);
		xPlayer += 1;
		tilePanels[xPlayer][yPlayer].setBackground(Color.BLUE);
		frame.repaint();
	    }

	    if (e.getKeyCode() == KeyEvent.VK_UP && yPlayer > 0 && pauseThread == false) {
		tilePanels[xPlayer][yPlayer].setBackground(Color.WHITE);
		yPlayer -= 1;
		tilePanels[xPlayer][yPlayer].setBackground(Color.BLUE);
		frame.repaint();
	    }

	    if (e.getKeyCode() == KeyEvent.VK_DOWN && yPlayer < TILES - 1 && pauseThread == false) {
		tilePanels[xPlayer][yPlayer].setBackground(Color.WHITE);
		yPlayer += 1;
		tilePanels[xPlayer][yPlayer].setBackground(Color.BLUE);
		frame.repaint();
	    }
	    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
		if (pauseThread == true) {
		    pauseThread = false;
		} else {
		    pauseThread = true;
		}
		pauseScreen();
	    }
	}
    }

    public void alienDraw(Alien alien) {
	Random alien_move = new Random();
	int xAlien = 0;
	int yAlien = 0;
	int moveAlien = alien_move.nextInt(4);

	// Marshmallow Man Spawned
	if (alien.getName().equals("Marshmallow Man")) {
	    if (moveAlien == 0) { // Spawn - Top
		xAlien = alien_move.nextInt(TILES - 1);
	    }
	    if (moveAlien == 1) { // Spawn - Left
		yAlien = alien_move.nextInt(TILES - 1);
	    }
	    if (moveAlien == 2) { // Spawn - Bottom
		xAlien = alien_move.nextInt(TILES - 1);
		yAlien = TILES - 1;
	    }
	    if (moveAlien == 3) { // Spawn - Right
		xAlien = TILES - 1;
		yAlien = alien_move.nextInt(TILES - 1);
	    }
	    tilePanels[xAlien][yAlien].setBackground(Color.GREEN);
	    while (alien.getHealth() > 0 && player.getAlive() == true && seconds > 31) {
		while (pauseThread == true) {
		    try {
			AlienThread.sleep(50);
		    } catch (InterruptedException e) {
			e.printStackTrace();
		    }
		}
		try {
		    AlienThread.sleep(alien.getMovement());
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
		tilePanels[xAlien][yAlien].setBackground(Color.WHITE);
		if (xPlayer > xAlien && yPlayer < yAlien) {
		    moveAlien = alien_move.nextInt(2);
		    if (moveAlien == 0) {
			xAlien += 1;
		    } else {
			yAlien -= 1;
		    }
		} else if (xPlayer < xAlien && yPlayer > yAlien) {
		    moveAlien = alien_move.nextInt(2);
		    if (moveAlien == 0) {
			xAlien -= 1;
		    } else {
			yAlien += 1;
		    }
		} else if (xPlayer > xAlien) {
		    xAlien += 1;
		} else if (xPlayer < xAlien) {
		    xAlien -= 1;
		} else if (yPlayer > yAlien) {
		    yAlien += 1;
		} else if (yPlayer < yAlien) {
		    yAlien -= 1;
		}
		if (tilePanels[xAlien][yAlien].equals(tilePanels[xPlayer][yPlayer])) {
		    player.setHealth(player.getHealth() - 1);
		    heartPane.setText(" ");
		    if (player.getHealth() <= 0) {
			player.setAlive(false);
			gameOver();
		    }
		}
		tilePanels[xAlien][yAlien].setBackground(Color.GREEN);
		frame.repaint();
	    }
	}
	tilePanels[xAlien][yAlien].setBackground(Color.WHITE);
    }

    public void snakeDraw(Alien alien) {
	Random alien_move = new Random();
	int xAlien = 0;
	int yAlien = 0;
	int moveAlien = alien_move.nextInt(4);

	// Snake Alien Spawned
	while (seconds > 30) {
	    try {
		AlienThread.sleep(50);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	}
	if (alien.getName().equals("Snake Alien")) {
	    if (moveAlien == 0) { // Spawn - Top
		xAlien = alien_move.nextInt(TILES - 1);
	    }
	    if (moveAlien == 1) { // Spawn - Left
		yAlien = alien_move.nextInt(TILES - 1);
	    }
	    if (moveAlien == 2) { // Spawn - Bottom
		xAlien = alien_move.nextInt(TILES - 1);
		yAlien = TILES - 2;
	    }
	    if (moveAlien == 3) { // Spawn - Right
		xAlien = TILES - 2;
		yAlien = alien_move.nextInt(TILES - 1);
	    }
	    tilePanels[xAlien][yAlien].setBackground(Color.ORANGE);
	    while (alien.getHealth() > 0 && player.getAlive() == true && seconds > 16) {
		while (pauseThread == true) {
		    try {
			AlienThread.sleep(50);
		    } catch (InterruptedException e) {
			e.printStackTrace();
		    }
		}
		try {
		    AlienThread.sleep(alien.getMovement());
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
		tilePanels[xAlien][yAlien].setBackground(Color.WHITE);
		if (xPlayer > xAlien) {
		    xAlien += 1;
		}
		if (xPlayer < xAlien) {
		    xAlien -= 1;
		}
		if (yPlayer > yAlien) {
		    yAlien += 1;
		}
		if (yPlayer < yAlien) {
		    yAlien -= 1;
		}
		if (tilePanels[xAlien][yAlien].equals(tilePanels[xPlayer][yPlayer])) {
		    player.setHealth(player.getHealth() - 1);
		    if (player.getHealth() <= 0) {
			player.setAlive(false);
			gameOver();
		    }
		}
		tilePanels[xAlien][yAlien].setBackground(Color.ORANGE);
		frame.repaint();
	    }
	}
	tilePanels[xAlien][yAlien].setBackground(Color.WHITE);
    }

    public void ogreDraw(Alien alien) {
	Random alien_move = new Random();
	int xAlien = 0;
	int yAlien = 0;
	int moveAlien = alien_move.nextInt(4);

	// Ogre Alien Stage
	while (seconds > 15) {
	    try {
		AlienThread.sleep(50);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	}
	if (alien.getName().equals("Ogre Alien")) {
	    if (moveAlien == 0) { // Spawn - Top
		xAlien = alien_move.nextInt(TILES - 2);
	    }
	    if (moveAlien == 1) { // Spawn - Left
		yAlien = alien_move.nextInt(TILES - 2);
	    }
	    if (moveAlien == 2) { // Spawn - Bottom
		xAlien = alien_move.nextInt(TILES - 2);
		yAlien = TILES - 2;
	    }
	    if (moveAlien == 3) { // Spawn - Right
		xAlien = TILES - 2;
		yAlien = alien_move.nextInt(TILES - 2);
	    }
	    tilePanels[xAlien][yAlien].setBackground(Color.RED);
	    tilePanels[xAlien + 1][yAlien].setBackground(Color.RED);
	    tilePanels[xAlien][yAlien + 1].setBackground(Color.RED);
	    tilePanels[xAlien + 1][yAlien + 1].setBackground(Color.RED);
	    while (alien.getHealth() > 0 && player.getAlive() == true && seconds > 0) {
		while (pauseThread == true) {
		    try {
			AlienThread.sleep(50);
		    } catch (InterruptedException e) {
			e.printStackTrace();
		    }
		}
		try {
		    AlienThread.sleep(alien.getMovement());
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
		tilePanels[xAlien][yAlien].setBackground(Color.WHITE);
		tilePanels[xAlien + 1][yAlien].setBackground(Color.WHITE);
		tilePanels[xAlien][yAlien + 1].setBackground(Color.WHITE);
		tilePanels[xAlien + 1][yAlien + 1].setBackground(Color.WHITE);
		if (xPlayer > xAlien + 1 && yPlayer < yAlien) {
		    moveAlien = alien_move.nextInt(2);
		    if (moveAlien == 0) {
			xAlien += 1;
		    } else {
			yAlien -= 1;
		    }
		} else if (xPlayer < xAlien && yPlayer > yAlien + 1) {
		    moveAlien = alien_move.nextInt(2);
		    if (moveAlien == 0) {
			xAlien -= 1;
		    } else {
			yAlien += 1;
		    }
		} else if (xPlayer > xAlien + 1) {
		    xAlien += 1;
		} else if (xPlayer < xAlien) {
		    xAlien -= 1;
		} else if (yPlayer > yAlien + 1) {
		    yAlien += 1;
		} else if (yPlayer < yAlien) {
		    yAlien -= 1;
		}
		if (tilePanels[xAlien][yAlien].equals(tilePanels[xPlayer][yPlayer])
			|| tilePanels[xAlien + 1][yAlien].equals(tilePanels[xPlayer][yPlayer])
			|| tilePanels[xAlien][yAlien + 1].equals(tilePanels[xPlayer][yPlayer])
			|| tilePanels[xAlien + 1][yAlien + 1].equals(tilePanels[xPlayer][yPlayer])) {
		    player.setHealth(player.getHealth() - 1);
		    if (player.getHealth() <= 0) {
			player.setAlive(false);
			gameOver();
		    }
		}
		tilePanels[xAlien][yAlien].setBackground(Color.RED);
		tilePanels[xAlien + 1][yAlien].setBackground(Color.RED);
		tilePanels[xAlien][yAlien + 1].setBackground(Color.RED);
		tilePanels[xAlien + 1][yAlien + 1].setBackground(Color.RED);
		frame.repaint();
	    }
	}
	tilePanels[xAlien][yAlien].setBackground(Color.WHITE);
	tilePanels[xAlien + 1][yAlien].setBackground(Color.WHITE);
	tilePanels[xAlien][yAlien + 1].setBackground(Color.WHITE);
	tilePanels[xAlien + 1][yAlien + 1].setBackground(Color.WHITE);
	if (player.getHealth() > 0) {
	    gameWin();
	}
    }

    public void dropItem(Item item) {
	Random itemRarity = new Random();
	int xItem = 0;
	int yItem = 0;
	int dropItem;
	for (int i = 0; i < seconds; i++) {
	    dropItem = itemRarity.nextInt(item.getRarity());
	    try {
		AlienThread.sleep(item.getSpawnRate());
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	    if (dropItem == 0) {
		xItem = itemRarity.nextInt(12);
		yItem = itemRarity.nextInt(12);
		while (dropItem == 0) {
		    tilePanels[xItem][yItem].setBackground(Color.MAGENTA);
		    try {
			AlienThread.sleep(50);
		    } catch (InterruptedException e) {
			e.printStackTrace();
		    }
		    if (tilePanels[xItem][yItem].equals(tilePanels[xPlayer][yPlayer])) {
			player.setHealth(player.getHealth() + 1);
			dropItem = 100;
		    }
		}
	    }
	}
    }

    public void gameOver() {
	frame.getContentPane().removeAll();
	frame.getContentPane().setLayout(new BorderLayout(0, 0));

	JPanel textGameOver = new JPanel();
	FlowLayout flowLayout = (FlowLayout) textGameOver.getLayout();
	flowLayout.setVgap(100);
	frame.getContentPane().add(textGameOver, BorderLayout.NORTH);

	JLabel lblGameOver = new JLabel("Game Over");
	lblGameOver.setFont(new Font("Tahoma", Font.PLAIN, 64));
	textGameOver.add(lblGameOver);

	JPanel buttonsGameOver = new JPanel();
	frame.getContentPane().add(buttonsGameOver, BorderLayout.CENTER);

	JButton btnQuit_1 = new JButton("Quit Game");
	buttonsGameOver.add(btnQuit_1);
	btnQuit_1.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent arg0) {
		System.exit(0);
	    }
	});
	frame.setVisible(true);
    }

    public void gameWin() {
	frame.getContentPane().removeAll();
	frame.getContentPane().setLayout(new BorderLayout(0, 0));

	JPanel textGameOver = new JPanel();
	FlowLayout flowLayout = (FlowLayout) textGameOver.getLayout();
	flowLayout.setVgap(100);
	frame.getContentPane().add(textGameOver, BorderLayout.NORTH);

	JLabel lblGameOver = new JLabel("You Win");
	lblGameOver.setFont(new Font("Tahoma", Font.PLAIN, 64));
	textGameOver.add(lblGameOver);

	JPanel buttonsGameOver = new JPanel();
	frame.getContentPane().add(buttonsGameOver, BorderLayout.CENTER);

	JButton btnQuit_1 = new JButton("Quit Game");
	buttonsGameOver.add(btnQuit_1);
	btnQuit_1.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent arg0) {
		System.exit(0);
	    }
	});
	frame.setVisible(true);
    }

    public void credits() {

	frame.getContentPane().removeAll();
	frame.getContentPane().setLayout(new BorderLayout(0, 0));

	JPanel textCredits = new JPanel();
	FlowLayout flowLayout = (FlowLayout) textCredits.getLayout();
	flowLayout.setVgap(200);

	JLabel lblCredits = new JLabel("CREDITS");
	lblCredits.setHorizontalAlignment(SwingConstants.CENTER);
	lblCredits.setFont(new Font("Tahoma", Font.PLAIN, 80));
	frame.getContentPane().add(lblCredits, BorderLayout.NORTH);

	JLabel lblNames = new JLabel("Barud Tesfaye & Cal Carlson");
	lblNames.setFont(new Font("Tahoma", Font.PLAIN, 30));
	lblNames.setHorizontalAlignment(SwingConstants.CENTER);
	frame.getContentPane().add(lblNames, BorderLayout.CENTER);
	JPanel buttonsGameOver = new JPanel();
	frame.getContentPane().add(buttonsGameOver, BorderLayout.SOUTH);
	JButton btnQuit_1 = new JButton("Main Menu");
	buttonsGameOver.add(btnQuit_1);
	btnQuit_1.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent arg0) {
		startMenu();
	    }

	});
	frame.setVisible(true);
    }

    public void pauseScreen() {
	if (pauseThread == true) {
	    pauseText.setText(" Game Paused");
	} else {
	    pauseText.setText("");
	}
    }

    @Override
    public void keyReleased(KeyEvent arg0) {
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
    }

}