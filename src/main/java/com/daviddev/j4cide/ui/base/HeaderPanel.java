package com.daviddev.j4cide.ui.base;

import javax.swing.JPanel;


import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.daviddev.j4cide.core.Environment;
import com.daviddev.j4cide.external.FlatSVGIcon;
import com.daviddev.j4cide.ui.UiApplication;
import com.daviddev.j4cide.util.Borders;
import com.daviddev.j4cide.util.ColorUtil;

public class HeaderPanel extends JPanel implements MouseListener {

	private static final long serialVersionUID = -1317187259572468792L;
	
	private JPanel titlePanel;
	private JPanel contentPanel;

	private JLabel headerTitle;
	private JButton closeButton;
	
	public HeaderPanel(int width, int height) {
		
		setSize(new Dimension(Math.max(width, 40), Math.max(height, 40)));
		setPreferredSize(getSize());
		
		titlePanel = new JPanel();
		titlePanel.addMouseListener(this);
		
		contentPanel = new JPanel();
		
		GroupLayout groupLayout = new GroupLayout(this);
		
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(titlePanel, GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
				.addComponent(contentPanel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(titlePanel, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addComponent(contentPanel, GroupLayout.DEFAULT_SIZE, 665, Short.MAX_VALUE))
		);
		
		headerTitle = new JLabel("Header Panel");
		headerTitle.setBorder(Borders.HEADER_TITLE_BORDER);
		
		closeButton = new JButton("");
		closeButton.setHorizontalTextPosition(SwingConstants.CENTER);
		closeButton.setIcon(new FlatSVGIcon(new File("./profile/icons/close_16px.svg")));
		closeButton.setContentAreaFilled(false);
		closeButton.setFocusable(false);
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		
		GroupLayout gl_titlePanel = new GroupLayout(titlePanel);
		gl_titlePanel.setHorizontalGroup(
			gl_titlePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_titlePanel.createSequentialGroup()
					.addComponent(headerTitle, GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(closeButton))
		);
		gl_titlePanel.setVerticalGroup(
			gl_titlePanel.createParallelGroup(Alignment.LEADING)
				.addComponent(headerTitle, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
				.addComponent(closeButton, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
		);
		
		titlePanel.setLayout(gl_titlePanel);
		setLayout(groupLayout);
		updateColors();
	}
	
	public JPanel getContentPanel() {
		return contentPanel;
	}
	
	public void updateColors() {
		titlePanel.setBackground(null);
		setBackground(ColorUtil.darker(UiApplication.bg(), 0.01f));
		setBorder(Borders.createBorderedPane(1, 1, 1, 1));
	}

	public void setTitle(String title) { 
		headerTitle.setText(title);
	}
	
	public void setIcon(ImageIcon icon) {
		headerTitle.setIcon(icon);
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {
		titlePanel.setBackground(ColorUtil.darker(UiApplication.bg(), 0.2f));
	}

	@Override
	public void mouseExited(MouseEvent e) {
		titlePanel.setBackground(null);		
	}
}
