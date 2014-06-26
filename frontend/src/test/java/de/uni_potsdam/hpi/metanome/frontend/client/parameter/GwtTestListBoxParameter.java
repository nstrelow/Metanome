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

package de.uni_potsdam.hpi.metanome.frontend.client.parameter;

import com.google.gwt.junit.client.GWTTestCase;
import de.uni_potsdam.hpi.metanome.algorithm_integration.AlgorithmConfigurationException;
import de.uni_potsdam.hpi.metanome.algorithm_integration.configuration.ConfigurationSettingListBox;
import de.uni_potsdam.hpi.metanome.algorithm_integration.configuration.ConfigurationSpecification;
import de.uni_potsdam.hpi.metanome.algorithm_integration.configuration.ConfigurationSpecificationListBox;
import org.junit.Test;

import java.util.ArrayList;

public class GwtTestListBoxParameter extends GWTTestCase {

    @Test
    public void testCreateWithFixedNumber() throws AlgorithmConfigurationException {
        //Setup
        int noOfValues = 3;
        ArrayList<String> values = new ArrayList<>();
        values.add("Column 1");
        values.add("Column 3");
        values.add("Column 2");
        ConfigurationSettingListBox setting = new ConfigurationSettingListBox(values);
        ConfigurationSpecificationListBox specification = new ConfigurationSpecificationListBox("enum", noOfValues);
        specification.setSettings(new ConfigurationSettingListBox[]{setting, setting, setting});

        //Execute
        InputParameterListBoxWidget widget = new InputParameterListBoxWidget(specification);

        //Check
        assertEquals(noOfValues, widget.inputWidgets.size());
        assertEquals(noOfValues, widget.getWidgetCount());
        assertFalse(widget.inputWidgets.get(0).isOptional);
    }

    @Test
    public void testCreateWithArbitraryNumber() throws AlgorithmConfigurationException {
        //Setup
        int noOfValues = ConfigurationSpecification.ARBITRARY_NUMBER_OF_VALUES;
        ArrayList<String> values = new ArrayList<>();
        values.add("Column 1");
        values.add("Column 3");
        values.add("Column 2");
        ConfigurationSettingListBox setting = new ConfigurationSettingListBox(values);
        ConfigurationSpecificationListBox specification = new ConfigurationSpecificationListBox("enum", noOfValues);
        specification.setSettings(setting);

        //Execute
        InputParameterListBoxWidget widget = new InputParameterListBoxWidget(specification);

        //Check
        assertEquals(1, widget.inputWidgets.size());        //expecting one default input field
        assertEquals(widget.getWidgetCount(), 2);            //default input field + add button
        assertTrue(widget.inputWidgets.get(0).isOptional);    //input field must be optional
    }

    @Test
    public void testAddInput() throws AlgorithmConfigurationException {
        //Setup
        ArrayList<String> values = new ArrayList<>();
        values.add("Column 1");
        values.add("Column 3");
        values.add("Column 2");
        ConfigurationSettingListBox setting = new ConfigurationSettingListBox(values);
        ConfigurationSpecificationListBox specification = new ConfigurationSpecificationListBox("enum",
                ConfigurationSpecification.ARBITRARY_NUMBER_OF_VALUES);
        specification.setSettings(setting);
        InputParameterListBoxWidget widget = new InputParameterListBoxWidget(specification);
        int previousCount = widget.getWidgetCount();
        int listCount = widget.inputWidgets.size();

        //Execute
        widget.addInputField(true);

        //Check
        assertEquals(previousCount + 1, widget.getWidgetCount());
        assertEquals(listCount + 1, widget.inputWidgets.size());
    }

    @Test
    public void testRemoveInput() throws AlgorithmConfigurationException {
        //Setup
        ArrayList<String> values = new ArrayList<>();
        values.add("Column 1");
        values.add("Column 3");
        values.add("Column 2");
        ConfigurationSettingListBox setting = new ConfigurationSettingListBox(values);
        ConfigurationSpecificationListBox specification = new ConfigurationSpecificationListBox("enum",
                ConfigurationSpecification.ARBITRARY_NUMBER_OF_VALUES);
        specification.setSettings(setting);
        InputParameterListBoxWidget widget = new InputParameterListBoxWidget(specification);
        int previousCount = widget.getWidgetCount();
        int listCount = widget.inputWidgets.size();

        //Execute
        widget.inputWidgets.get(0).removeSelf();

        //Check
        assertEquals(previousCount - 1, widget.getWidgetCount());
        assertEquals(listCount - 1, widget.inputWidgets.size());
    }

    @Test
    public void testRetrieveValues() {
        //Setup
        ArrayList<String> values = new ArrayList<>();
        values.add("Column 1");
        values.add("Column 3");
        values.add("Column 2");
        String expectedSelectedValue = "Column 3";
        ConfigurationSettingListBox expectedSetting = new ConfigurationSettingListBox(values, expectedSelectedValue);
        ConfigurationSpecificationListBox expectedSpecification = new ConfigurationSpecificationListBox("enum",
                ConfigurationSpecification.ARBITRARY_NUMBER_OF_VALUES);
        expectedSpecification.setSettings(expectedSetting);
        InputParameterListBoxWidget widget = null;
        try {
            widget = new InputParameterListBoxWidget(expectedSpecification);
        } catch (AlgorithmConfigurationException e) {
            fail();
        }

        //Execute
        ConfigurationSettingListBox[] specification = widget.getUpdatedSpecification().getSettings();

        //Check
        assertEquals(expectedSpecification.getSettings().length, specification.length);
        assertEquals(values.size(), specification[0].values.size());
        assertEquals(expectedSelectedValue, specification[0].selectedValue);
        assertEquals(values, specification[0].values);
    }

    @Override
    public String getModuleName() {
        return "de.uni_potsdam.hpi.metanome.frontend.Metanome";
    }
}