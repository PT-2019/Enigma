package editor.utils.managers;

import editor.utils.Window;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Minimize implements ActionListener {

	private Window window;

	public Minimize(Window window){
		this.window = window;
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		this.window.setExtendedState(JFrame.ICONIFIED);
	}
}