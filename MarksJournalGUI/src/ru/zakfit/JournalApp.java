package ru.zakfit;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import java.awt.Color;

import javax.swing.JInternalFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSpinner;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.BevelBorder;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;


public class JournalApp {

	private static Journal classJnl;
	private static Object[] columnsMarksHeader = new String[] {"Оценки"};
	private static Integer[] cellMarksValues = new Integer[]{0,2,3,4,5};
	private DefaultTableModel tableMarksModel;
	private DefaultTableModel tableJournalModel;
	//Интерфейс
	private JFrame frame;
	private JTextField textPupilNumber;
	private JTable tableJournal;
	private JTable tableMarks;

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
		//выравнивание ячеек
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
		leftRenderer.setHorizontalAlignment( JLabel.LEFT );
		//Desktop приложения
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.LIGHT_GRAY);
		desktopPane.setBounds(0, 0, 780, 540);
		frame.getContentPane().add(desktopPane);
		//Форма создания нового журнала
		JInternalFrame newJnlFrame = new JInternalFrame("\u041D\u043E\u0432\u044B\u0439 \u0436\u0443\u0440\u043D\u0430\u043B");
		newJnlFrame.setBounds(10, 10, 306, 201);
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
		
		newJnlFrame.setVisible(false);
		
		//Форма добавления/редактирования ученика
		JInternalFrame addPupilFrame = new JInternalFrame("\u041D\u043E\u0432\u044B\u0439 \u0443\u0447\u0435\u043D\u0438\u043A");
		addPupilFrame.setResizable(true);
		addPupilFrame.setBounds(10, 10, 300, 200);
		desktopPane.add(addPupilFrame);
		GridBagLayout gblPupil = new GridBagLayout();
		gblPupil.columnWidths = new int[]{2, 0, 0, 60, 79, 2};
		gblPupil.rowHeights = new int[]{2, 14, 20, 0, 23, 2};
		gblPupil.columnWeights = new double[]{0.01, 1.0, 1.0, 0.0, 1.0, 0.01, Double.MIN_VALUE};
		gblPupil.rowWeights = new double[]{0.01, 0.0, 1.0, 1.0, 1.0, 0.01, Double.MIN_VALUE};
		addPupilFrame.getContentPane().setLayout(gblPupil);
		
		JLabel lblPupilNumber = new JLabel("\u041D\u043E\u043C\u0435\u0440 \u0443\u0447\u0435\u043D\u0438\u043A\u0430:");
		//lblPupilNumber.setBounds(10, 11, 90, 14);
		GridBagConstraints gbc_lblPupilNumber = new GridBagConstraints();
		gbc_lblPupilNumber.insets = new Insets(0, 0, 2, 2);
		gbc_lblPupilNumber.gridx = 1;
		gbc_lblPupilNumber.gridy = 1;
		gbc_lblPupilNumber.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblPupilNumber.fill = GridBagConstraints.NONE;
		addPupilFrame.getContentPane().add(lblPupilNumber, gbc_lblPupilNumber);
		
		textPupilNumber = new JTextField();
		textPupilNumber.setToolTipText("Номер ученика");
		textPupilNumber.setPreferredSize(new Dimension(70, 20));		
		//textPupilNumber.setBounds(10, 25, 86, 20);
		GridBagConstraints gbc_textPupilNumber = new GridBagConstraints();
		gbc_textPupilNumber.insets = new Insets(0, 0, 2, 2);
		gbc_textPupilNumber.gridx = 1;
		gbc_textPupilNumber.gridy = 2;
		gbc_textPupilNumber.anchor = GridBagConstraints.NORTHWEST;
		gbc_textPupilNumber.fill = GridBagConstraints.HORIZONTAL;
		addPupilFrame.getContentPane().add(textPupilNumber, gbc_textPupilNumber);
		
		JLabel lblMarks = new JLabel("Таблица оценок");
		//lblMarks.setBounds(110, 11, 60, 14);
		GridBagConstraints gbc_lblMarks = new GridBagConstraints();
		gbc_lblMarks.insets = new Insets(0, 0, 2, 2);
		gbc_lblMarks.gridx = 2;
		gbc_lblMarks.gridy = 1;
		gbc_lblMarks.gridwidth = 2;
		gbc_lblMarks.gridheight = 1;
		gbc_lblMarks.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblMarks.fill = GridBagConstraints.NONE;
		addPupilFrame.getContentPane().add(lblMarks, gbc_lblMarks);
		
		//модель таблицы оценок ученика 
		tableMarksModel = new DefaultTableModel();
		tableMarksModel.setColumnIdentifiers(columnsMarksHeader);
		
		// модель ячейки оценок - выпадающий список с цифрами 0,2,3,4,5
		JComboBox<Integer> marksComboBox = new JComboBox<Integer>(cellMarksValues); 	
		DefaultCellEditor tableMarksCellEditor = new DefaultCellEditor(marksComboBox);
		
		tableMarks = new JTable(tableMarksModel);
		tableMarks.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
		tableMarks.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		//tableMarks.setBounds(99, 25, 163, 168);
		//tableMarks.setRowHeight(25);
		//устанавливаем модель ячеек
		tableMarks.getColumnModel().getColumn(0).setCellEditor(tableMarksCellEditor);
		JScrollPane scrlPaneTableMarks = new JScrollPane(tableMarks);
		//scrlPaneTableMarks.setPreferredSize(new Dimension(40, 150));
		GridBagConstraints gbc_scrlPaneTableMarks = new GridBagConstraints();
		gbc_scrlPaneTableMarks.insets = new Insets(0, 0, 2, 0);
		gbc_scrlPaneTableMarks.gridx = 2;
		gbc_scrlPaneTableMarks.gridy = 2;
		gbc_scrlPaneTableMarks.gridwidth = 3;
		gbc_scrlPaneTableMarks.gridheight = 2;
		gbc_scrlPaneTableMarks.anchor = GridBagConstraints.NORTHWEST;
		gbc_scrlPaneTableMarks.fill = GridBagConstraints.BOTH;
		addPupilFrame.getContentPane().add(scrlPaneTableMarks, gbc_scrlPaneTableMarks);
		//addPupilFrame.getContentPane().add(tableMarks);
		
		JButton btnOK2 = new JButton("\u041E\u041A");
		//btnOK2.setBounds(147, 204, 60, 23);
		GridBagConstraints gbc_btnOK2 = new GridBagConstraints();
		gbc_btnOK2.insets = new Insets(0, 0, 2, 0);
		gbc_btnOK2.gridx = 3;
		gbc_btnOK2.gridy = 4;
		gbc_btnOK2.anchor = GridBagConstraints.SOUTHEAST;
		gbc_btnOK2.fill = GridBagConstraints.NONE;
		addPupilFrame.getContentPane().add(btnOK2, gbc_btnOK2);
		
		JButton btnCancel2 = new JButton("\u041E\u0442\u043C\u0435\u043D\u0430");
		//btnCancel2.setBounds(217, 204, 79, 23);
		GridBagConstraints gbc_btnCancel2 = new GridBagConstraints();
		gbc_btnCancel2.insets = new Insets(0, 0, 2, 0);
		gbc_btnCancel2.gridx = 4;
		gbc_btnCancel2.gridy = 4;
		gbc_btnCancel2.anchor = GridBagConstraints.SOUTHEAST;
		gbc_btnCancel2.fill = GridBagConstraints.NONE;
		addPupilFrame.getContentPane().add(btnCancel2, gbc_btnCancel2);
				
		addPupilFrame.setVisible(false);
		//Форма журнала
		JInternalFrame journalFrame = new JInternalFrame("\u0416\u0443\u0440\u043D\u0430\u043B");
		journalFrame.setResizable(true);
		journalFrame.setBounds(10, 10, 400, 300);
		desktopPane.add(journalFrame);
		GridBagLayout gblJournal = new GridBagLayout();
		gblJournal.columnWidths = new int[]{0, 0, 0, 0, 0};
		gblJournal.rowHeights = new int[]{0, 0, 0, 23, 0};
		gblJournal.columnWeights = new double[]{0.01, 0.1, 1.0, 1.0, 0.01, Double.MIN_VALUE};
		gblJournal.rowWeights = new double[]{0.01, 1.0, 1.0, 1.0, 0.01, Double.MIN_VALUE};
		journalFrame.getContentPane().setLayout(gblJournal);
		
		JLabel lblJournal = new JLabel("\u0416\u0443\u0440\u043D\u0430\u043B \u0443\u0441\u043F\u0435\u0432\u0430\u0435\u043C\u043E\u0441\u0442\u0438");
		//lblJournal.setBounds(120, 11, 132, 14);
		GridBagConstraints gbc_lblJournal = new GridBagConstraints();
		gbc_lblJournal.insets = new Insets(0, 0, 2, 0);
		gbc_lblJournal.gridx = 1;
		gbc_lblJournal.gridy = 1;
		gbc_lblJournal.gridwidth = 3;
		gbc_lblJournal.gridheight = 1;
		gbc_lblJournal.anchor = GridBagConstraints.SOUTH;
		gbc_lblJournal.fill = GridBagConstraints.NONE;
		journalFrame.getContentPane().add(lblJournal, gbc_lblJournal);
		
		//Модель таблицы журнала
		tableJournalModel = new DefaultTableModel();
		//Таблица журнала
		tableJournal = new JTable(tableJournalModel);
		tableJournal.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		//tableJournal.setBounds(10, 42, 231, 96);
		
		JScrollPane scrllPnJournal = new JScrollPane(tableJournal);
		//scrllPnJournal.setBounds(10, 42, 351, 163);
		GridBagConstraints gbc_scrllPnJournal = new GridBagConstraints();
		gbc_scrllPnJournal.insets = new Insets(0, 0, 2, 0);
		gbc_scrllPnJournal.gridx = 1;
		gbc_scrllPnJournal.gridy = 2;
		gbc_scrllPnJournal.gridwidth = 3;
		gbc_scrllPnJournal.gridheight = 1;
		gbc_scrllPnJournal.anchor = GridBagConstraints.NORTHWEST;
		gbc_scrllPnJournal.fill = GridBagConstraints.BOTH;
		journalFrame.getContentPane().add(scrllPnJournal, gbc_scrllPnJournal);
		
		JButton btnOK3 = new JButton("\u041E\u041A");
		//btnOK3.setBounds(212, 216, 60, 23);
		btnOK3.setPreferredSize(new Dimension(60, 23));
		btnOK3.setMinimumSize(btnOK3.getPreferredSize());
		GridBagConstraints gbc_btnOK3 = new GridBagConstraints();
		gbc_btnOK3.insets = new Insets(0, 0, 2, 0);
		gbc_btnOK3.gridx = 2;
		gbc_btnOK3.gridy = 3;
		gbc_btnOK3.anchor = GridBagConstraints.LINE_END;
		gbc_btnOK3.fill = GridBagConstraints.NONE;
		journalFrame.getContentPane().add(btnOK3, gbc_btnOK3);
		
		JButton btnCancel3 = new JButton("\u041E\u0442\u043C\u0435\u043D\u0430");
		//btnCancel3.setBounds(282, 216, 79, 23);
		btnCancel3.setPreferredSize(new Dimension(79, 23));
		btnCancel3.setMinimumSize(btnCancel3.getPreferredSize());
		GridBagConstraints gbc_btnCancel3 = new GridBagConstraints();
		gbc_btnCancel3.insets = new Insets(0, 0, 2, 0);
		gbc_btnCancel3.gridx = 3;
		gbc_btnCancel3.gridy = 3;
		gbc_btnCancel3.anchor = GridBagConstraints.CENTER;
		gbc_btnCancel3.fill = GridBagConstraints.NONE;
		journalFrame.getContentPane().add(btnCancel3, gbc_btnCancel3);
		
		// Меню
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("\u0424\u0430\u0439\u043B");
		menuBar.add(mnFile);
		
		JMenuItem mntmOpenJournal = new JMenuItem("\u041E\u0442\u043A\u0440\u044B\u0442\u044C \u0436\u0443\u0440\u043D\u0430\u043B");
		mnFile.add(mntmOpenJournal);
		
		JMenuItem menuNewJournal = new JMenuItem("\u0421\u043E\u0437\u0434\u0430\u0442\u044C \u0436\u0443\u0440\u043D\u0430\u043B");
		mnFile.add(menuNewJournal);
		
		JMenuItem mntmCloseJournal = new JMenuItem("\u0417\u0430\u043A\u0440\u044B\u0442\u044C \u0436\u0443\u0440\u043D\u0430\u043B");
		mnFile.add(mntmCloseJournal);
		
		
		JMenuItem mntmExit = new JMenuItem("\u0412\u044B\u0445\u043E\u0434");
		mnFile.add(mntmExit);
				
		JMenu mnJnl = new JMenu("\u0416\u0443\u0440\u043D\u0430\u043B");
		menuBar.add(mnJnl);
		
		JMenuItem mntmShowJnl = new JMenuItem("\u041F\u043E\u043A\u0430\u0437\u0430\u0442\u044C \u0436\u0443\u0440\u043D\u0430\u043B");
		mntmShowJnl.setEnabled(false);
		mnJnl.add(mntmShowJnl);
		
		JMenu menuEdit = new JMenu("\u0420\u0435\u0434\u0430\u043A\u0442\u0438\u0440\u043E\u0432\u0430\u0442\u044C");
		menuBar.add(menuEdit);
		
		JMenuItem mntmEditPupil = new JMenuItem("\u0414\u043E\u0431\u0430\u0432\u0438\u0442\u044C \u0443\u0447\u0435\u043D\u0438\u043A\u0430");
		mntmEditPupil.setEnabled(false);
		menuEdit.add(mntmEditPupil);
		// кнопка ОК формы создания журнала
		btnOK1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mntmEditPupil.setEnabled(true);
				mntmShowJnl.setEnabled(true);
				classJnl.setMaxMarks((Integer) spinMarks.getValue());
				classJnl.setMaxPupils((Integer) spinPupils.getValue());			
				newJnlFrame.setVisible(false);
				
			}
		});
		//кнопка ОК формы добавления ученика
		btnOK2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Pupil tmpPup = new Pupil();
				Integer[] pupMarks = new Integer[classJnl.getMaxMarks()];
				//выводим таблицу из режима редактирования для чтения всех значений
				if (tableMarks.isEditing()) 
					tableMarks.getCellEditor().stopCellEditing();
				for (int i = 0; i<classJnl.getMaxMarks(); i++){
					if (tableMarks.getModel().getValueAt(i, 0) != null) 
							pupMarks[i] = Integer.parseInt(tableMarks.getModel().getValueAt(i, 0).toString());
					else  pupMarks[i] = 0;
				}
				if (!textPupilNumber.getText().isEmpty()) {
					tmpPup.setPupNumber(Integer.parseInt(textPupilNumber.getText()));
					tmpPup.setPupMarks(pupMarks);
					tmpPup.calcAvgMark();
					classJnl.addPupil(tmpPup);
					addPupilFrame.setVisible(false);
				}
				else
				{
					JOptionPane.showMessageDialog(frame,"Не заполнен номер ученика", "Ошибка", JOptionPane.ERROR_MESSAGE);	
				}	
			}
		});
		// кнопка ОК формы Показать журнал
		btnOK3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				journalFrame.setVisible(false);
			}
		});
		// кнопка Отмена формы создания дурнала
		btnCancel1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuNewJournal.setEnabled(true);
				newJnlFrame.setVisible(false);
			}
		});
		// кнопка Отмена формы добавления ученика
		btnCancel2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addPupilFrame.setVisible(false);
			}
		});
		// кнопка Отмена формы Показать журнал
		btnCancel3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				journalFrame.setVisible(false);
			}
		});
		//меню Создать журнал
		menuNewJournal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				classJnl = new Journal();
				newJnlFrame.setVisible(true);
				spinPupils.setValue(1);
				spinMarks.setValue(1);
				mntmShowJnl.setEnabled(false);
				menuNewJournal.setEnabled(false);
			}
		});
		// меню Закрыть журнал
		mntmCloseJournal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				classJnl = null;
				mntmEditPupil.setEnabled(false);
				mntmShowJnl.setEnabled(false);
				menuNewJournal.setEnabled(true);
			}
		});
		// меню показать журнал
		mntmShowJnl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//массив заголовка таблицы
				String[] journalHeader = new String[classJnl.getMaxMarks()+2];
				journalHeader[0] = "Номер ученика";
				for (int i= 1; i<=classJnl.getMaxMarks(); i++){
					journalHeader[i] = Integer.toString(i);
				}
				journalHeader[classJnl.getMaxMarks()+1] = "Средний балл";
				tableJournalModel.setColumnIdentifiers(journalHeader);
				tableJournalModel.setRowCount(classJnl.getMaxPupils());
				//выставляем выравнивание ячеек
				for (int i= 0; i<=classJnl.getMaxMarks(); i++){
					tableJournal.getColumnModel().getColumn(i).setCellRenderer( centerRenderer );
					tableJournal.getTableHeader().getColumnModel().getColumn(i).setCellRenderer( centerRenderer );
				}
				tableJournal.getColumnModel().getColumn(classJnl.getMaxMarks()+1).setCellRenderer( leftRenderer );
				tableJournal.getTableHeader().getColumnModel().getColumn(classJnl.getMaxMarks()+1).setCellRenderer( centerRenderer );
				//заполняем, когда есть ученики
				if (classJnl.getPupils() != null)
				{
					ArrayList<Pupil> pupils = classJnl.getPupils();
					for (int i=0; i<pupils.size();i++){
					Pupil tmpPup = classJnl.getPupilByIndex(i);
					tableJournalModel.setValueAt(tmpPup.getPupNumber(), i, 0);
					Integer[] tmpMarks = tmpPup.getPupMarks();
					for (int j = 0; j < classJnl.getMaxMarks(); j++) {
						tableJournalModel.setValueAt(tmpMarks[j], i, j+1);
					}
					tableJournalModel.setValueAt(tmpPup.getAvgMark(), i, classJnl.getMaxMarks()+1);
				}
				}
				journalFrame.setVisible(true);
			}
		});
		// меню Добавить ученика
		mntmEditPupil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int cntPup =0;
				if (classJnl.getPupils() != null) 
					cntPup = classJnl.getPupils().size();
				if (cntPup < classJnl.getMaxPupils())
				{
					textPupilNumber.setText("");
					//ограничиваем количество строк таблицы
					tableMarksModel.setRowCount(classJnl.getMaxMarks());
					for (int i=0; i<tableMarksModel.getRowCount();i++){
						tableMarksModel.setValueAt(0, i, 0);
					}
					addPupilFrame.setVisible(true);
				}
				else
				{
					JOptionPane.showMessageDialog(frame,"Превышено максимальное количество учеников!", "Ошибка", JOptionPane.ERROR_MESSAGE);	
				}
		}	
		});
		//меню Выход
		mntmExit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});

		
	}
}
