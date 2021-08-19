package genelectrovise.bizarre.spring.server.cmd.ui;

import java.awt.Dimension;

import javax.swing.JFrame;

public class CmdWindow extends JFrame {

	public CmdWindow() {
		initWindow();
	}

	protected void initWindow() {
		setVisible(true);
		setSize(new Dimension(1200, 900));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Bizarre (Spring) Server Management Console");
	}
}
