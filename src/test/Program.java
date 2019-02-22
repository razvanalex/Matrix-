package test;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Program extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private Timer timer;
	private final int DELAY = 10;
	public static final int WindowWidth = 800;
	public static final int WindowHeight = 600;
	
	JLabel label;
	
	private Snake s;
	private Board b;
	private Food f;
	
	private int difficulty = 10;
	
	public Program() {
		init();
	}
	
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(WindowWidth, WindowHeight);
    }

	private void init() {
		addKeyListener(new TAdapter());
		setFocusable(true);
		setBackground(Color.BLACK);
		
		b = new Board(20);
		b.Init();
		s = new Snake(1, 20, 10, new Vector2D(WindowWidth / 2, WindowHeight / 2));
		f = new Food(20, 1);
		f.genRandomPosition();
		
		label = new JLabel("Score: " + (s.getSizeOfSnake() - 1));
        label.setForeground(Color.YELLOW);
        label.setFont(new Font("Consolas", Font.BOLD, 14));   
        this.add(label);
        this.validate();
        
		timer = new Timer(DELAY, this);
        timer.start();
	}
	
	public void update() {
		int level = s.getSpeedOfSnake();
		
		if (s.getSizeOfSnake() % difficulty == 0) {
			level -= 1;
			if (level < 1)
				level = 1;
		}
		
		s.Update();
		if (Vector2D.Equals(s.getHeadPosition(), f.getPosition())) {
			s.setSpeedOfSnake(level);
			f.genRandomPosition();
			s.increaseSize();
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, getWidth(), getHeight());
        
        label.setText("Score: " + (s.getSizeOfSnake() - 1));
        
		b.Draw(g2d);
		s.Draw(g2d);
		f.Draw(g2d);
		
		g2d.dispose();
		this.validate();
    }
    
	@Override
	public void actionPerformed(ActionEvent e) {
		update();
		repaint();		
	}
	
	public static void close(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_Q)
			System.exit(0);
	}

    private class TAdapter extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
        	
        }

        @Override
        public void keyPressed(KeyEvent e) {
        	s.Move(e);
        	Program.close(e);
        }
    }
	
}