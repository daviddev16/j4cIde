package com.daviddev.j4cide.ui.dialog;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.daviddev.j4cide.ui.IconMapper;
import com.daviddev.j4cide.ui.model.CloseActionType;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UiSaveOrExitDialog extends JDialog implements ActionListener {

	private static final long serialVersionUID = 3600497783432759793L;
	private final JPanel contentPanel = new JPanel();

	private CloseActionType closeActionType = CloseActionType.CANCELLED;

	public static CloseActionType decide() {
		UiSaveOrExitDialog exitDialog = new UiSaveOrExitDialog();
		exitDialog.setVisible(true);
		return exitDialog.getCloseActionType();
	}
	
	private UiSaveOrExitDialog() {
		setTitle("?");
		setModal(true);
		setBounds(100, 100, 563, 145);
		getContentPane().setLayout(new BorderLayout());
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel title = new JLabel("<html>Deseja salvar este arquivo \"main.c\"? Caso <b>não</b>, "
				+ "as alterações serão <b>perdidas</b>.</<html>");
		title.setHorizontalTextPosition(SwingConstants.LEFT);
		title.setHorizontalAlignment(SwingConstants.LEFT);
		title.setBounds(72, 11, 465, 32);
		
		JButton saveAndExitBtn = new JButton("Salvar e Sair");
		saveAndExitBtn.setActionCommand(CloseActionType.SAVE_AND_EXIT.name());
		saveAndExitBtn.addActionListener(this);
		saveAndExitBtn.setBounds(208, 67, 116, 27);

		JButton cancelBtn = new JButton("Cancelar");
		cancelBtn.setActionCommand(CloseActionType.CANCELLED.name());
		cancelBtn.addActionListener(this);
		cancelBtn.setBounds(446, 67, 91, 27);

		JButton exitBtn = new JButton("Sair sem Salvar");
		exitBtn.setActionCommand(CloseActionType.ONLY_EXIT.name());
		exitBtn.addActionListener(this);
		exitBtn.setBounds(327, 67, 116, 27);
		
		JLabel icon = new JLabel();
		icon.setIcon(IconMapper.icon(IconMapper.WARNING_ICON));
		icon.setIconTextGap(50);
		icon.setHorizontalAlignment(SwingConstants.CENTER);
		icon.setBounds(18, 11, 32, 32);

		contentPanel.add(title);
		contentPanel.add(saveAndExitBtn);
		contentPanel.add(cancelBtn);
		contentPanel.add(exitBtn);
		contentPanel.add(icon);
		getRootPane().setDefaultButton(saveAndExitBtn);
		setLocationRelativeTo(null);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		this.closeActionType = CloseActionType.valueOf(e.getActionCommand());
		dispose();
	}

	public CloseActionType getCloseActionType() {
		return closeActionType;
	}

	
}
