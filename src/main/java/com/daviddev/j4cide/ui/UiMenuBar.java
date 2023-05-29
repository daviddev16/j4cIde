package com.daviddev.j4cide.ui;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

import com.daviddev.j4cide.ui.action.CreateAction;
import com.daviddev.j4cide.ui.action.DeleteAction;
import com.daviddev.j4cide.ui.action.ExecuteAction;
import com.daviddev.j4cide.ui.action.ExportAction;
import com.daviddev.j4cide.ui.action.SaveAction;
import com.daviddev.j4cide.ui.handler.ActionsHandler;

public class UiMenuBar extends JMenuBar {

	private static final long serialVersionUID = 3277915417819999283L;

	public UiMenuBar() {

		JMenu mnFile = new JMenu("Arquivo");
		mnFile.add(ActionsHandler.actionOf(SaveAction.class));
		mnFile.add(ActionsHandler.actionOf(CreateAction.class));
		mnFile.add(ActionsHandler.actionOf(DeleteAction.class));
		mnFile.add(ActionsHandler.actionOf(ExportAction.class));
		add(mnFile);
		
		JMenu mnBuild = new JMenu("Construção");
		mnBuild.add(ActionsHandler.actionOf(ExecuteAction.class));
		add(mnBuild);
		
		/*JMenuItem mntmNew = new JMenuItem("Novo...");
		mnFile.add(mntmNew);
		
		JMenuItem mntmExportar = new JMenuItem("Exportar...");
		mnFile.add(mntmExportar);
		
		JMenuItem mntmSave = new JMenuItem("Salvar");
		mnFile.add(mntmSave);
		
		JMenu mnCompiler = new JMenu("Compilador");
		add(mnCompiler);
		
		JMenuItem mntmRun = new JMenuItem("Rodar");
		mnCompiler.add(mntmRun);
		
		JMenuItem mntmStop = new JMenuItem("Parar");
		mnCompiler.add(mntmStop);
		
		JMenuItem mntmKillContext = new JMenuItem("Matar Contextos");
		mnCompiler.add(mntmKillContext);
		
		JMenu mnView = new JMenu("Visualização");
		add(mnView);
		
		JMenuItem mntmShowLogs = new JMenuItem("Exibir Logs");
		mnView.add(mntmShowLogs);
		
		JMenuItem mntmShowFileExplorer = new JMenuItem("Exibir Explorador de Arquivos");
		mnView.add(mntmShowFileExplorer);*/
	}
	
}
