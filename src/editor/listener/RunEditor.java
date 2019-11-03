package editor.listener;

import editor.EditorLuncher;

public class RunEditor implements Runnable {

	@Override
	public void run() {
		EditorLuncher editorLuncher = new EditorLuncher(800, 600);
		editorLuncher.start();
	}
}
