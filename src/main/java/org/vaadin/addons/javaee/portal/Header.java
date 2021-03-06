/*******************************************************************************
 * Copyright 2012 Thomas Letsch
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 *******************************************************************************/
package org.vaadin.addons.javaee.portal;

import javax.inject.Inject;

import org.vaadin.addons.javaee.i18n.TranslationKeys;
import org.vaadin.addons.javaee.i18n.TranslationService;
import org.vaadin.virkki.cdiutils.application.UIContext.UIScoped;
import org.vaadin.virkki.cdiutils.componentproducers.Preconfigured;
import org.vaadin.virkki.cdiutils.mvp.ViewComponent;

import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

@UIScoped
public class Header extends ViewComponent {

    private static final long serialVersionUID = 1L;

    public static final String ID = "Header";

    @Inject
    protected TranslationService translationService;

    @Inject
    @Preconfigured(styleName = "toolbar", spacing = true)
    private HorizontalLayout layout;

    @Inject
    @Preconfigured(styleName = "portal_title", id = "PortalTitle")
    private Label title;

    public Header() {
        setId(ID);
    }

    public void init() {
        layout.setSizeFull();
        layout.addComponent(title);
        setCompositionRoot(layout);
        title.setValue(translationService.getText(TranslationKeys.TITLE_PORTAL));
    }

    public void setTitle(String newTitle) {
        title.setValue(newTitle);
    }

    public void addComponent(Component component) {
        layout.addComponent(component);
    }
}
