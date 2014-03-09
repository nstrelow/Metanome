/*
 * Copyright 2014 by the Metanome project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.uni_potsdam.hpi.metanome.algorithm_integration.results;

import java.io.Serializable;

import de.uni_potsdam.hpi.metanome.algorithm_integration.result_receiver.CouldNotReceiveResultException;
import de.uni_potsdam.hpi.metanome.algorithm_integration.result_receiver.OmniscientResultReceiver;

/**
 * All Results need to be sendable to an {@link OmniscientResultReceiver}.
 * 
 * @author Jakob Zwiener
 */
public interface Result extends Serializable {

	/**
	 * Sends a result to an {@link OmniscientResultReceiver}.
	 * 
	 * @param resultReceiver
	 * 
	 * @throws CouldNotReceiveResultException 
	 */
	public void sendResultTo(OmniscientResultReceiver resultReceiver) throws CouldNotReceiveResultException;
	
}
