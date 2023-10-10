package game.main;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.sound.midi.MidiEvent;
import javax.swing.JFrame;

import game.component.PanelGame;

public class Main extends JFrame{
	
	public Main() {
		init();
	}
	
	public void init() {
		setTitle("Javaswing game");
		setSize(683,384);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		PanelGame panelGame = new PanelGame();
		add(panelGame);
		addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent e) {
				panelGame.start();
			}
		});
	}
	
	public static void main(String args[]) {
		Main main = new Main();
		main.setVisible(true);
		
	}
	

}
