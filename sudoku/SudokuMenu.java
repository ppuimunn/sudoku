package sudoku;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class SudokuMenu extends JMenuBar{
	private JMenu newGame, file, options, help;
	private JMenuItem reset, exit;
	private JMenuItem normal, hard, expert; 
	private JMenuItem musicOff, musicOn;
	public static int hiddenCells=1;
    
    public SudokuMenu() {    	
    	newGame = new JMenu("New Game");
    	normal = new JMenuItem("Normal");
    	hard = new JMenuItem("Hard");
    	expert = new JMenuItem("Expert");
    	newGame.add(normal);
        newGame.add(hard);
        newGame.add(expert);
        this.add(newGame);
        
        normal.addActionListener(new DifficultyListener());
        hard.addActionListener(new DifficultyListener());
        expert.addActionListener(new DifficultyListener());
        
        file = new JMenu("File");
    	reset = new JMenuItem("Reset Game");
    	exit = new JMenuItem("Exit Game");
    	file.add(reset);
    	file.add(exit);
    	this.add(file);
    	
    	reset.addActionListener(new ResetListener());
    	exit.addActionListener(new ExitListener());
    	
    	options = new JMenu("Options");
    	musicOn = new JMenuItem("Music On");
    	musicOff = new JMenuItem("Music Off");
    	options.add(musicOn);
    	options.add(musicOff);
    	this.add(options);
    	
    	musicOn.addActionListener(new MusicListener());
    	musicOff.addActionListener(new MusicListener());
    	
    	help = new JMenu("Help");
        this.add(help);
        help.addMouseListener(new HelpListener());
	}
    
    
    private class DifficultyListener implements ActionListener {
	      @Override
	      public void actionPerformed(ActionEvent e) {
	    	  JMenuItem difficulty = (JMenuItem)e.getSource();
	    	  String diffText = difficulty.getText();
	    	  switch (diffText){
	    	  	case "Normal":  
	    	  		hiddenCells=10;
	    	  		break;
	    	  	case "Hard":
	    	  		hiddenCells=40;
	    	  		break;
	    	  	case "Expert":
	    	  		hiddenCells=70;
	    	  		break;
	    	  }
	    	  SudokuMain.board.init();
	    	  SudokuMain.counterLabel.setText("00:00");
	    	  SudokuMain.second =0;
	    	  SudokuMain.minute =0;
	    	  SudokuMain.timer.start();
	      }
	 }
    
    private class ResetListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			GameBoard.ResetGame();
			SudokuMain.counterLabel.setText("00:00");
			SudokuMain.second =0;
			SudokuMain.minute =0;
			SudokuMain.timer.start();
		}
	}
    
    private class ExitListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			int askExit = JOptionPane.showConfirmDialog(null, "Exit the game?");
			if (askExit == JOptionPane.YES_OPTION) {
				System.exit(0);
			}
		}
    }  
    
    private class HelpListener implements MouseListener{

		@Override
		public void mouseReleased(MouseEvent e) {}
		@Override
		public void mousePressed(MouseEvent e) {}
		@Override
		public void mouseExited(MouseEvent e) {}
		@Override
		public void mouseEntered(MouseEvent e) {}
		
		@Override
		public void mouseClicked(MouseEvent e) {
		    JOptionPane.showMessageDialog(null, "HOW TO PLAY SUDOKU: \n"
					+ "\nFill in numbers from 1 to 9 such that it  "
					+ "\nappears exactly once in every row, column "
					+ "\nand 3x3 region."
					+ "\n\nPress \"Enter\" to check your answer.", 
					"Instructions", JOptionPane.PLAIN_MESSAGE);
		}
	}
    
    private class MusicListener implements ActionListener{
			
			@Override
			public void actionPerformed(ActionEvent e
					) {
				JMenuItem onOff = (JMenuItem)e.getSource();
				String onOffText = onOff.getText();
				System.out.println(onOffText);
				if (onOffText == "Music On") {
					SudokuMain.soundClipBgm.setFramePosition(0);
					SudokuMain.soundClipBgm.loop(Clip.LOOP_CONTINUOUSLY);
					FloatControl volume = (FloatControl) SudokuMain.soundClipBgm.getControl(FloatControl.Type.MASTER_GAIN);
					volume.setValue(-10);
				} else if (onOffText == "Music Off") {
					SudokuMain.soundClipBgm.stop();
				}
			}
		
    }
}