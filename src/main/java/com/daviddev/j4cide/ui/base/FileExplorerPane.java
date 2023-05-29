package com.daviddev.j4cide.ui.base;


import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.io.File;

import javax.swing.border.EmptyBorder;

import com.daviddev.j4cide.annotation.ExeceptionHandlerMaker;
import com.daviddev.j4cide.api.CodeSceneChild;
import com.daviddev.j4cide.api.Interactable;
import com.daviddev.j4cide.ui.IconMapper;
import com.daviddev.j4cide.ui.UiApplication;
import com.daviddev.j4cide.ui.UiCodeScene;
import com.daviddev.j4cide.ui.component.FileExplorerTree;
import com.daviddev.j4cide.util.ColorUtil;

import javax.swing.JScrollPane;

@ExeceptionHandlerMaker
public class FileExplorerPane extends HeaderPanel implements CodeSceneChild, Interactable {

	private static final long serialVersionUID = -8261955161217960392L;
	
	private final UiCodeScene codeScene;

	private FileExplorerTree explorerTree;
	private JScrollPane scrollPane;
	
	public FileExplorerPane(UiCodeScene codeScene) {
		super(100, 700);
		
		this.codeScene = codeScene;

		getContentPanel().setOpaque(false);
		setTitle("Arquivos do Ambiente");
		setIcon(IconMapper.icon(IconMapper.ENV_ICON));
		
		getContentPanel().setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		scrollPane.setFocusable(false);
		scrollPane.setBorder(null);
	
		getContentPanel().add(scrollPane, BorderLayout.CENTER);
		
		explorerTree = new FileExplorerTree(this, new File(getCodeScene().getProjectFolder()));
		
		explorerTree.addMouseListener(new MouseAdapter() {
	        
	    });
		
		explorerTree.setRowHeight(20);
		explorerTree.setBorder(new EmptyBorder(5, 2, 5, 2));
		explorerTree.setOpaque(true);
		explorerTree.setBackground(ColorUtil.darker(UiApplication.bg(), 0.01f));
		explorerTree.setBounds(20, 20, 100, 100);
		scrollPane.setViewportView(explorerTree);
		
	}
	
	@Override
	public void open() {
		setVisible(true);
		getCodeScene().getEditorDividerPane().setDividerLocation(0.2d);
		getCodeScene().getEditorDividerPane().revalidate();
	}
	
	@Override
	public void close() {
		setVisible(false);
	}
	
	public void reload() {
		/*
		 * explorerTree.reload();
		 * tabbedPane.reload();
		 * */
		
		/*
		 * reload tree
		 * reload code editors
		 * */
	}

	public FileExplorerTree getExplorerTree() {
		return explorerTree;
	}
	
	@Override
	public UiCodeScene getCodeScene() {
		return this.codeScene;
	}

}
