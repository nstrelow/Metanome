package de.uni_potsdam.hpi.metanome.frontend.client.runs;

import java.util.List;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.DockPanel;

import de.uni_potsdam.hpi.metanome.frontend.client.BasePage;
import de.uni_potsdam.hpi.metanome.frontend.client.jarchooser.JarChooser;
import de.uni_potsdam.hpi.metanome.frontend.client.parameter.InputParameter;
import de.uni_potsdam.hpi.metanome.frontend.client.parameter.ParameterTable;
import de.uni_potsdam.hpi.metanome.frontend.client.services.ExecutionService;
import de.uni_potsdam.hpi.metanome.frontend.client.services.ExecutionServiceAsync;

/**
 * The Run Configuration page allow to specify all parameters for an algorithm execution:
 * The algorithm itself is chosen through a JarChooser widget, and the ParameterTable allows
 * to specify algorithm specific parameters.
 * The page can be referenced (and switched to) by other pages with pre-set values. Executing an 
 * algorithm navigates to the corresponding Results page. 
 */
public class RunConfigurationPage extends DockPanel{
	protected BasePage basePage;
	protected ParameterTable parameterTable;
	protected JarChooser jarChooser;
	
	protected ExecutionServiceAsync executionService;
	
	
	/**
	 * Constructor. Initializes ExecutoinService and registers given algorithms.
	 * However, more algorithms can be registered whenever they become available,
	 * through <link>addAlgorithms(String... algorithmNames)</link>
	 * 
	 * @param algorithmNames 
	 */
	public RunConfigurationPage(BasePage basePage, String... algorithmNames){
		this.setWidth("100%");
		
		this.basePage = basePage;
		this.addJarChooser(algorithmNames);
		
		this.executionService = GWT.create(ExecutionService.class);
	}
	
		
	/**
	 * Adds a widget for user's parameter input to the tab 
	 * 
	 * @param paramList	list of required parameters
	 */
	public void addParameterTable(List<InputParameter> paramList){
		if (parameterTable != null) {
			this.remove(parameterTable);
		}
		parameterTable = new ParameterTable(paramList);
		this.add(parameterTable, DockPanel.WEST);
	}

	/**
	 * Method to add more algorithms after construction.
	 * 
	 * @param algorithmNames
	 */
	public void addAlgorithms(String... algorithmNames){
		this.jarChooser.addAlgorithms(algorithmNames);
	}
	
	/**
	 * Adds the JarChooser object for this tab.
	 * must be implemented in subclasses to use algorithm specific JarChooser
	 * 
	 * @param filenames	list of filenames (without path) of matching algorithms
	 */
	public void addJarChooser(String... filenames) {
		jarChooser = new JarChooser(filenames);
		this.add(jarChooser, DockPanel.NORTH);
	}
	
	/**
	 * 
	 * @return the <link>JarChooser</link> object of this tab
	 */
	public JarChooser getJarChooser() {
		return jarChooser;
	}
	
	/**
	 * 
	 * @return	the name of the algorithm that is currently selected on this page's JarChooser
	 */
	public String getCurrentlySelectedAlgorithm(){
		return this.jarChooser.getSelectedAlgorithm();
	}
	
	/**
	 * Select the given algorithm on the underlying JarChooser.
	 * 
	 * @param algorithmName	the value to select
	 */
	public void selectAlgorithm(String algorithmName) {
		this.jarChooser.setSelectedAlgorithm(algorithmName);
		this.jarChooser.submit();
	}
	
	/**
	 * Execute the currently selected algorithm and switch to results page.
	 * @param parameters	parameters to use for the algorithm execution
	 */
	public void callExecutionService(List<InputParameter> parameters) {
		final String algorithmName = getCurrentlySelectedAlgorithm();
		basePage.startExecutionAndResultPolling(executionService, algorithmName, parameters);
	}

}
