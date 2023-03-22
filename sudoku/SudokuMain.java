package sudoku;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JButton;
import javax.swing.Timer;
import java.text.DecimalFormat;

import javax.sound.sampled.*;
import java.net.URL;

/** main Sudoku program **/

public class SudokuMain extends JFrame {
	// private variables
	public static GameBoard board = new GameBoard();
	SudokuMenu menu = new SudokuMenu();
	String bgm = "sounds/bgm.wav";
	String correct = "sounds/rightAns.wav";
	String wrong = "sounds/wrongAns.wav";
	public static Clip soundClipBgm, soundClipCorrect, soundClipWrong;

	public static JLabel counterLabel;
	public static Timer timer;
	
	public static int second, minute;
	String ddSecond, ddMinute;	
	DecimalFormat dFormat = new DecimalFormat("00");
	

	// Constructor
	public SudokuMain() {
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());

		// make menu bar
		JMenuBar mBar = new JMenuBar();
		mBar.add(menu);
		setJMenuBar(mBar);
		
		//make timer bar
		counterLabel = new JLabel("");
		Panel pnl = new Panel();
		Label lbl = new Label("Time:");
		pnl.add(lbl);
		pnl.add(counterLabel);
		pnl.setVisible(true);
		Button pauseBtn = new Button("Pause");
		pnl.add(pauseBtn); 
		Button resumeBtn = new Button("Resume"); // Button is a component
		pnl.add(resumeBtn); 
		
		
		resumeBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	System.out.println("You clicked resume");
        		timer.start();
            	
            }
        });
		
		pauseBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent c) {
            	System.out.println("You clicked pause");
        		timer.stop();
            	
            }
        });
		
		counterLabel.setText("00:00");
		second =0;
		minute =0;
		normalTimer();
		
		timer.start();
		

		// music
		try {
			URL url = this.getClass().getClassLoader().getResource(bgm);
			if (url == null) {
				System.err.println("Couldn't find file: " + bgm);
			} else {
				AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
				soundClipBgm = AudioSystem.getClip();
				soundClipBgm.open(audioIn);
			}

			url = this.getClass().getClassLoader().getResource(correct);
			if (url == null) {
				System.err.println("Couldn't find file: " + correct);
			} else {
				AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
				soundClipCorrect = AudioSystem.getClip();
				soundClipCorrect.open(audioIn);
			}

			url = this.getClass().getClassLoader().getResource(wrong);
			if (url == null) {
				System.err.println("Couldn't find file: " + wrong);
			} else {
				AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
				soundClipWrong = AudioSystem.getClip();
				soundClipWrong.open(audioIn);
			}
		} catch (UnsupportedAudioFileException e) {
			System.err.println("Audio Format not supported!");
		} catch (Exception e) {
			e.printStackTrace();
		}

		// play music
		soundClipBgm.loop(Clip.LOOP_CONTINUOUSLY);
		FloatControl volume = (FloatControl) soundClipBgm.getControl(FloatControl.Type.MASTER_GAIN);
		volume.setValue(-10);
		
		add(pnl, BorderLayout.PAGE_END);

		cp.add(board, BorderLayout.CENTER);
		board.init();

		pack(); // Pack the UI components, instead of setSize()
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Handle window closing
		setTitle("Sudoku");
		setVisible(true);
	}
	
	public void normalTimer() {
		
		timer = new Timer(1000, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				second++;
				
				ddSecond = dFormat.format(second);
				ddMinute = dFormat.format(minute);				
				counterLabel.setText(ddMinute + ":" + ddSecond);
				
				if(second==60) {
					second=0;
					minute++;
					
					ddSecond = dFormat.format(second);
					ddMinute = dFormat.format(minute);
					counterLabel.setText(ddMinute + ":" + ddSecond);
				}
			}
		});
	}
	
	/** The entry main() entry method */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new SudokuMain();
			}
		});
		

	}
}
