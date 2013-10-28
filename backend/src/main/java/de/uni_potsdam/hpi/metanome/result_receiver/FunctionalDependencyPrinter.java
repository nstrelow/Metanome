package de.uni_potsdam.hpi.metanome.result_receiver;

import java.io.FileNotFoundException;
import java.io.OutputStream;

import de.uni_potsdam.hpi.metanome.algorithm_integration.ColumnCombination;
import de.uni_potsdam.hpi.metanome.algorithm_integration.ColumnIdentifier;
import de.uni_potsdam.hpi.metanome.algorithm_integration.result_receiver.CouldNotReceiveResultException;
import de.uni_potsdam.hpi.metanome.algorithm_integration.result_receiver.FunctionalDependencyResultReceiver;

public class FunctionalDependencyPrinter extends ResultPrinter implements FunctionalDependencyResultReceiver {

	protected static final String FD_SEPARATOR = " --> ";
	
	public FunctionalDependencyPrinter(OutputStream outStream) {
		super(outStream);
	}

	public FunctionalDependencyPrinter(String fileName, String subdirectoryName) throws FileNotFoundException {
		super(fileName, subdirectoryName);
	}
	
	@Override
	public void receiveResult(ColumnCombination determinant, ColumnIdentifier dependent) throws CouldNotReceiveResultException {
		outStream.println(determinant + FD_SEPARATOR + dependent);
	}
	
}