package com.daviddev.j4cide.ui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ColorUIResource;

import com.daviddev.j4cide.Util;
import com.daviddev.j4cide.core.ApplicationContextManager;
import com.daviddev.j4cide.core.Environment;
import com.daviddev.j4cide.core.IconMapper;
import com.daviddev.j4cide.external.FlatSVGIcon;
import com.daviddev.j4cide.external.FlatSVGUtils;
import com.daviddev.j4cide.factory.FileTreeNodeFactory;
import com.daviddev.j4cide.model.CideStyle;
import com.daviddev.j4cide.ui.component.UiButton;
import com.daviddev.j4cide.ui.handler.ActionsHandler;
import com.daviddev.j4cide.util.ColorUtil;
import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.util.SystemInfo;

import javax.management.InstanceAlreadyExistsException;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JDialog;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.JToolBar;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class UiApplication extends JFrame {

	public static CideStyle DEFAULT_STYLE;
	
	static {
		try {
			DEFAULT_STYLE = new CideStyle("defaultStyle", new File(".\\profile\\themes\\monokai.xml"), 
					Font.createFont(Font.TRUETYPE_FONT, new File(".//profile//fonts//FiraMono-Regular.ttf")), "flatDarculaLaf");
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
	}
	
	private static final long serialVersionUID = 6077917334900022692L;
	private JPanel contentPane;
	
	public static final String GLOBAL_BG_KEY = "globalBackground";

	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				ApplicationContextManager.initializeContextManager();
				
				try {
					if (SystemInfo.isMacOS) {
						System.setProperty("apple.laf.useScreenMenuBar", "true");
						System.setProperty("apple.awt.application.name", "FlatLaf Demo");
						System.setProperty("apple.awt.application.appearance", "system");
					}
					if (SystemInfo.isLinux) {
						JFrame.setDefaultLookAndFeelDecorated(true);
						JDialog.setDefaultLookAndFeelDecorated(true);
					}
					
					FlatDarculaLaf.setup();
					
					UIManager.put( "Button.arc", 2);
					UIManager.put(GLOBAL_BG_KEY, UIManager.get("Panel.background"));
					System.out.println(UIManager.get(GLOBAL_BG_KEY));
					
					Toolkit.getDefaultToolkit().setDynamicLayout(true);
					
					IconMapper.registerAllIcons();
					ActionsHandler.registerAllActions();
					FileTreeNodeFactory.initialize();
					
					
					UiApplication frame = new UiApplication();
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setVisible(true);
					ApplicationContextManager.getContextManager().setUiApplication(frame);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static Color bg() {
		ColorUIResource colorResource = ((ColorUIResource)UIManager.get(GLOBAL_BG_KEY));
		return colorResource;
	}
	
	/**
	 * Create the frame.
	 * 
	 * @throws IOException
	 */
	public UiApplication() throws IOException {
		
		setIconImage(FlatSVGUtils.svg2image(new File("./profile/icons/cpp_16px.svg").toURI().toURL(), 1.5f));
		setTitle("Uni C eso (IDE para projetos em C)");
		try {
			//new Environment(new File("./profile"));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		setBounds(0, 0, 1200, 1000);
		
		UiToolBar toolBar = new UiToolBar();
		
		UiCodeScene codeScene = new UiCodeScene();
		ApplicationContextManager.getContextManager().setCurrentCodeScene(codeScene);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addComponent(toolBar, GroupLayout.DEFAULT_SIZE, 1184, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(6)
					.addComponent(codeScene, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(6))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(toolBar, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addComponent(codeScene, GroupLayout.DEFAULT_SIZE, 926, Short.MAX_VALUE)
					.addGap(6))
		);
		
		
		/*EditorRootPane editorRootPane = new EditorRootPane();
		splitPane.setRightComponent(editorRootPane);
		splitPane.setContinuousLayout(true);
		splitPane.setDividerLocation(0.5);
		
		FileExplorerPane fileExplorerPane = new FileExplorerPane(editorRootPane);
		splitPane.setLeftComponent(fileExplorerPane);
		
		ConsolePane consolePane = new ConsolePane();
		splitPane_1.setRightComponent(consolePane);
		splitPane_1.setDividerLocation(1);
		*/

		getContentPane().setLayout(groupLayout);
		
		UiMenuBar menuBar = new UiMenuBar();
		setJMenuBar(menuBar);
		
		/*JMenu mnFile = new JMenu("Arquivo");
		mnFile.add(new SaveAction());
		menuBar.add(mnFile);
		
		JMenuItem mntmNew = new JMenuItem("Novo...");
		mnFile.add(mntmNew);
		
		JMenuItem mntmExportar = new JMenuItem("Exportar...");
		mnFile.add(mntmExportar);
		
		JMenuItem mntmSave = new JMenuItem("Salvar");
		mnFile.add(mntmSave);
		
		JMenu mnCompiler = new JMenu("Compilador");
		menuBar.add(mnCompiler);
		
		JMenuItem mntmRun = new JMenuItem("Rodar");
		mnCompiler.add(mntmRun);
		
		JMenuItem mntmStop = new JMenuItem("Parar");
		mnCompiler.add(mntmStop);
		
		JMenuItem mntmKillContext = new JMenuItem("Matar Contextos");
		mnCompiler.add(mntmKillContext);
		
		JMenu mnView = new JMenu("Visualização");
		menuBar.add(mnView);
		
		JMenuItem mntmShowLogs = new JMenuItem("Exibir Logs");
		mnView.add(mntmShowLogs);
		
		JMenuItem mntmShowFileExplorer = new JMenuItem("Exibir Explorador de Arquivos");
		mnView.add(mntmShowFileExplorer);*/
		
	}
}
