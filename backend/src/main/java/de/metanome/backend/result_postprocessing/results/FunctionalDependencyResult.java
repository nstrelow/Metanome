/*
 * Copyright 2015 by the Metanome project
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

package de.metanome.backend.result_postprocessing.results;

import com.fasterxml.jackson.annotation.JsonTypeName;

import de.metanome.algorithm_integration.ColumnCombination;
import de.metanome.algorithm_integration.ColumnIdentifier;

/**
 * Represents an functional dependency result with different ranking values.
 */
@JsonTypeName("FunctionalDependencyResult")
public class FunctionalDependencyResult implements RankingResult {

  protected ColumnCombination determinant;
  protected ColumnIdentifier dependant;

  public ColumnCombination getDeterminant() {
    return determinant;
  }

  public void setDeterminant(ColumnCombination determinant) {
    this.determinant = determinant;
  }

  public ColumnIdentifier getDependant() {
    return dependant;
  }

  public void setDependant(ColumnIdentifier dependant) {
    this.dependant = dependant;
  }
}
