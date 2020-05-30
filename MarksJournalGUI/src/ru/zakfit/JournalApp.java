package ru.zakfit;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JDesktopPane;
import javax.swing.UIManager;

import java.awt.Color;

import javax.swing.JInternalFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSpinner;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.PropertyVetoException;

import javax.swing.JTable;
import javax.swing.JScrollBar;
import javax.swing.SpinnerNumberModel;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;
import javax.swing.AbstractAction;
import javax.swing.Action;

public class JournalApp {

	private static Journal classJnl;
	//Интерфейс
	private JFrame frame;
	private JTextField textPupilNumber;
	private JTable tableJournal;
	private JTable tableMarks;
	private final Action action = new SwingAction();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//ставим Look and Feel в стиле Windows
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"); 
					JournalApp window = new JournalApp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public JournalApp() {
		//создаем экземпляр журнала
		classJnl = new Journal();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\u0416\u0443\u0440\u043D\u0430\u043B \u0443\u0441\u043F\u0435\u0432\u0430\u0435\u043C\u043E\u0441\u0442\u0438");
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.LIGHT_GRAY);
		desktopPane.setBounds(0, 0, 780, 540);
		frame.getContentPane().add(desktopPane);
		
		JInternalFrame newJnlFrame = new JInternalFrame("\u041D\u043E\u0432\u044B\u0439 \u0436\u0443\u0440\u043D\u0430\u043B");
		try {
			newJnlFrame.setClosed(true);
		} catch (PropertyVetoException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		newJnlFrame.setBounds(27, 26, 306, 201);
		desktopPane.add(newJnlFrame);
		newJnlFrame.getContentPane().setLayout(null);
		
		JButton btnOK1 = new JButton("\u041E\u041A");
		btnOK1.setBounds(131, 137, 60, 23);
		newJnlFrame.getContentPane().add(btnOK1);
		
		JButton btnCancel1 = new JButton("\u041E\u0442\u043C\u0435\u043D\u0430");
		btnCancel1.setBounds(201, 137, 79, 23);
		newJnlFrame.getContentPane().add(btnCancel1);
		
		JLabel lblPupilsCount = new JLabel("\u041A\u043E\u043B\u0438\u0447\u0435\u0441\u0442\u0432\u043E \u0443\u0447\u0435\u043D\u0438\u043A\u043E\u0432:");
		lblPupilsCount.setBounds(10, 27, 132, 14);
		newJnlFrame.getContentPane().add(lblPupilsCount);
		
		JLabel lblMarksCount = new JLabel("\u041C\u0430\u043A\u0441\u0438\u043C\u0430\u043B\u044C\u043D\u043E\u0435 \u043A\u043E\u043B\u0438\u0447\u0435\u0441\u0442\u0432\u043E \r\n\u043E\u0446\u0435\u043D\u043E\u043A:");
		lblMarksCount.setBounds(10, 65, 181, 37);
		newJnlFrame.getContentPane().add(lblMarksCount);
		
		JSpinner spinPupils = new JSpinner();
		spinPupils.setModel(new SpinnerNumberModel(1, 1, 10, 1));
		spinPupils.setToolTipText("\u043D\u0435 \u0431\u043E\u043B\u0435\u0435 10!");
		spinPupils.setBounds(131, 24, 29, 20);
		newJnlFrame.getContentPane().add(spinPupils);
		
		JSpinner spinMarks = new JSpinner();
		spinMarks.setModel(new SpinnerNumberModel(1, 1, 10, 1));
		lblMarksCount.setLabelFor(spinMarks);
		spinMarks.setToolTipText("\u043D\u0435 \u0431\u043E\u043B\u0435\u0435 10!");
		spinMarks.setBounds(195, 73, 38, 20);
		newJnlFrame.getContentPane().add(spinMarks);
		
		JInternalFrame addPupilFrame = new JInternalFrame("\u041D\u043E\u0432\u044B\u0439 \u0443\u0447\u0435\u043D\u0438\u043A");
		try {
			addPupilFrame.setClosed(true);
		} catch (PropertyVetoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		addPupilFrame.setBounds(371, 26, 319, 201);
		desktopPane.add(addPupilFrame);
		addPupilFrame.getContentPane().setLayout(null);
		
		JButton btnOK2 = new JButton("\u041E\u041A");
		btnOK2.setBounds(141, 137, 60, 23);
		addPupilFrame.getContentPane().add(btnOK2);
		
		JButton btnCancel2 = new JButton("\u041E\u0442\u043C\u0435\u043D\u0430");
		btnCancel2.setBounds(214, 137, 79, 23);
		addPupilFrame.getContentPane().add(btnCancel2);
		
		JLabel lblPupilNumber = new JLabel("\u041D\u043E\u043C\u0435\u0440 \u0443\u0447\u0435\u043D\u0438\u043A\u0430:");
		lblPupilNumber.setBounds(10, 11, 90, 14);
		addPupilFrame.getContentPane().add(lblPupilNumber);
		
		textPupilNumber = new JTextField();
		textPupilNumber.setToolTipText("\u0422\u043E\u043B\u044C\u043A\u043E \u0446\u0438\u0444\u0440\u044B!");
		textPupilNumber.setBounds(10, 25, 86, 20);
		addPupilFrame.getContentPane().add(textPupilNumber);
		textPupilNumber.setColumns(10);
		
		JLabel lblMarks = new JLabel("\u041E\u0446\u0435\u043D\u043A\u0438:");
		lblMarks.setBounds(99, 11, 60, 14);
		addPupilFrame.getContentPane().add(lblMarks);
		
		tableMarks = new JTable();
		tableMarks.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tableMarks.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableMarks.setBounds(99, 25, 163, 100);
		addPupilFrame.getContentPane().add(tableMarks);
		
		JScrollBar scrlMarks = new JScrollBar();
		scrlMarks.setBounds(265, 26, 17, 100);
		addPupilFrame.getContentPane().add(scrlMarks);
		
		JInternalFrame journalFrame = new JInternalFrame("\u0416\u0443\u0440\u043D\u0430\u043B");
		try {
			journalFrame.setClosed(true);
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		journalFrame.setBounds(27, 253, 294, 211);
		desktopPane.add(journalFrame);
		journalFrame.getContentPane().setLayout(null);
		
		JButton btnOK3 = new JButton("\u041E\u041A");
		btnOK3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnOK3.setBounds(119, 147, 60, 23);
		journalFrame.getContentPane().add(btnOK3);
		
		JButton btnCancel3 = new JButton("\u041E\u0442\u043C\u0435\u043D\u0430");
		btnCancel3.setBounds(189, 147, 79, 23);
		journalFrame.getContentPane().add(btnCancel3);
		
		JLabel lblJournal = new JLabel("\u0416\u0443\u0440\u043D\u0430\u043B \u0443\u0441\u043F\u0435\u0432\u0430\u0435\u043C\u043E\u0441\u0442\u0438");
		lblJournal.setBounds(69, 11, 132, 14);
		journalFrame.getContentPane().add(lblJournal);
		
		tableJournal = new JTable();
		tableJournal.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tableJournal.setBounds(10, 42, 231, 96);
		journalFrame.getContentPane().add(tableJournal);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(251, 42, 17, 94);
		journalFrame.getContentPane().add(scrollBar);
		journalFrame.setVisible(true);
		addPupilFrame.setVisible(true);
		newJnlFrame.setVisible(true);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("\u0424\u0430\u0439\u043B");
		menuBar.add(mnFile);
		
		JMenuItem mntmOpenJournal = new JMenuItem("\u041E\u0442\u043A\u0440\u044B\u0442\u044C \u0436\u0443\u0440\u043D\u0430\u043B");
		mnFile.add(mntmOpenJournal);
		
		JMenuItem menuNewJournal = new JMenuItem("\u0421\u043E\u0437\u0434\u0430\u0442\u044C \u0436\u0443\u0440\u043D\u0430\u043B");
		mnFile.add(menuNewJournal);
		
		JMenuItem mntmExit = new JMenuItem("\u0412\u044B\u0445\u043E\u0434");
		mnFile.add(mntmExit);
		
		JMenu mnJnl = new JMenu("\u0416\u0443\u0440\u043D\u0430\u043B");
		menuBar.add(mnJnl);
		
		JMenuItem mntmShowJnl = new JMenuItem("\u041F\u043E\u043A\u0430\u0437\u0430\u0442\u044C \u0436\u0443\u0440\u043D\u0430\u043B");
		mnJnl.add(mntmShowJnl);
		
		JMenu menuEdit = new JMenu("\u0420\u0435\u0434\u0430\u043A\u0442\u0438\u0440\u043E\u0432\u0430\u0442\u044C");
		menuBar.add(menuEdit);
		
		JMenuItem mntmEditPupil = new JMenuItem("\u0414\u043E\u0431\u0430\u0432\u0438\u0442\u044C \u0443\u0447\u0435\u043D\u0438\u043A\u0430");
		menuEdit.add(mntmEditPupil);
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
