/*******************************************************************************
 * Copyright 2011 Joachim Ansorg, mail@ansorg-it.com
 * File: BashProjectSettingsConfigurable.java, Class: BashProjectSettingsConfigurable
 * Last modified: 2011-04-30 16:33
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package com.ansorgit.plugins.bash.settings;

import com.ansorgit.plugins.bash.util.BashIcons;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Disposer;
import org.jetbrains.annotations.Nls;

import javax.swing.*;

/**
 * Date: 12.05.2009
 * Time: 18:51:48
 *
 * @author Joachim Ansorg
 */
public class BashProjectSettingsConfigurable implements Configurable {
    private final Project project;
    private BashProjectSettingsPane settingsPanel;

    public BashProjectSettingsConfigurable(Project project) {
        this.project = project;
    }

    @Nls
    public String getDisplayName() {
        return "BashSupport";
    }

    public Icon getIcon() {
        return BashIcons.BASH_FILE_ICON;
    }

    public String getHelpTopic() {
        return null;
    }

    public JComponent createComponent() {
        if (settingsPanel == null) {
            settingsPanel = new BashProjectSettingsPane();
        }

        return settingsPanel.getPanel();
    }

    public boolean isModified() {
        if (project == null) {
            return false;
        }

        return settingsPanel != null && settingsPanel.isModified(BashProjectSettings.storedSettings(project));
    }

    public void apply() throws ConfigurationException {
        settingsPanel.storeSettings(BashProjectSettings.storedSettings(project));
    }

    public void reset() {
        settingsPanel.setData(BashProjectSettings.storedSettings(project));
    }

    public void disposeUIResources() {
        if (settingsPanel != null) {
            Disposer.dispose(settingsPanel);
            this.settingsPanel = null;
        }
    }
}