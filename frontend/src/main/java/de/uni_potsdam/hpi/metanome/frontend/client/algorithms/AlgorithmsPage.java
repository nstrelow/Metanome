package de.uni_potsdam.hpi.metanome.frontend.client.algorithms;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.uni_potsdam.hpi.metanome.frontend.client.BasePage;
import de.uni_potsdam.hpi.metanome.frontend.client.services.FinderService;
import de.uni_potsdam.hpi.metanome.frontend.client.services.FinderServiceAsync;

public class AlgorithmsPage extends VerticalPanel {

	private final FlexTable uccList;
	private final FlexTable fdList;
	private final FlexTable indList;
	private final FlexTable statsList;
	
	protected final FinderServiceAsync finderService;
	protected final BasePage basePage;
	
	public AlgorithmsPage(BasePage parent){
		this.setWidth("100%");
		this.setSpacing(5);
		
		this.finderService = GWT.create(FinderService.class);
		this.basePage = parent;
		
		this.add(new HTML("<b>Unique Column Combinations</b>"));
		this.uccList = new FlexTable();
		this.add(this.uccList);
		listUccAlgorithms();
		
		this.add(new HTML("<hr><b>Functional Dependencies</b>"));
		this.fdList = new FlexTable();
		this.add(this.fdList);
		listFdAlgorithms();
		
		this.add(new HTML("<hr><b>Inclusion Dependencies</b>"));
		this.indList = new FlexTable();
		this.add(this.indList);
		listIndAlgorithms();
		
		this.add(new HTML("<hr><b>Basic Statistics</b>"));
		this.statsList = new FlexTable();
		this.add(this.statsList);
		listStatsAlgorithms();
		
		this.add(new HTML("<hr>"));
		Label temporaryAddContent = new Label();
		temporaryAddContent.setText("To add a new algorithm, put its jar in the designated folder.");
		this.add(temporaryAddContent);
	}

	private void listUccAlgorithms() {
		finderService.listUniqueColumnCombinationsAlgorithms(getCallback(this.uccList));
	}


	private void listFdAlgorithms() {
		finderService.listFunctionalDependencyAlgorithms(getCallback(this.fdList));
	}

	private void listIndAlgorithms() {
		finderService.listInclusionDependencyAlgorithms(getCallback(this.indList));		
	}

	private void listStatsAlgorithms() {
		finderService.listBasicStatisticsAlgorithms(getCallback(this.statsList));
	}

	protected AsyncCallback<String[]> getCallback(final FlexTable list) {
		return new AsyncCallback<String[]>() {
			public void onFailure(Throwable caught) {
				// TODO: Do something with errors.
				caught.printStackTrace();
			}
			
			public void onSuccess(String[] result) { 
				addAlgorithmsToList(result, list);
				basePage.addAlgorithmsToRunConfigurations(result);
			}
		};
	}

	protected void addAlgorithmsToList(String[] algorithmNames, FlexTable list) {
		int row = list.getRowCount();
		for(String algorithmName : algorithmNames){		
			//Using the HTML title to associate an algorithm with each button.
			Button runButton = new Button("Run");
			runButton.setTitle(algorithmName);
			runButton.addClickHandler(new ClickHandler() {	
				@Override
				public void onClick(ClickEvent event) {
					jumpToRunConfiguration(((Button) event.getSource()).getTitle());
				}
			});
			
			list.setText(row, 0, algorithmName);
			list.setWidget(row, 1, runButton);			
			row++;
		}
	}

	protected void jumpToRunConfiguration(String algorithmName) {
		basePage.jumpToRunConfiguration(algorithmName);
	}
}