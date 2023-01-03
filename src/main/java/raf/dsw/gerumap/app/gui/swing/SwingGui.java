package raf.dsw.gerumap.app.gui.swing;

import javax.swing.JOptionPane;
import raf.dsw.gerumap.app.core.GUI;
import raf.dsw.gerumap.app.gui.observer.IPublisher;
import raf.dsw.gerumap.app.gui.swing.view.MainFrame;
import raf.dsw.gerumap.app.messageGenerator.Message;
import raf.dsw.gerumap.app.messageGenerator.MessageGeneratorImpl;

public class SwingGui implements GUI {

	@Override
	public void run() {
		MainFrame mainFrame = MainFrame.getInstance();
		mainFrame.init();
		mainFrame.setVisible(true);
	}

	@Override
	public void update(IPublisher publisher) {
		if (publisher instanceof MessageGeneratorImpl) {
			Message message = ((MessageGeneratorImpl) publisher).getMessage();
			JOptionPane optionPane = new JOptionPane();
			optionPane.setMessage(message.getMessage());
			switch (message.getLevel()) {
				case INFO -> optionPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);
				case WARNING -> optionPane.setMessageType(JOptionPane.WARNING_MESSAGE);
				case ERROR -> optionPane.setMessageType(JOptionPane.ERROR_MESSAGE);
			}
			optionPane.setMessageType(JOptionPane.ERROR_MESSAGE);
			optionPane.createDialog(MainFrame.getInstance(), message.getLevel().name())
				.setVisible(true);

		}
	}
}
