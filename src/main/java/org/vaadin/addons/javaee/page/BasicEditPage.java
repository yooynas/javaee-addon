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
package org.vaadin.addons.javaee.page;

import javax.inject.Inject;

import org.vaadin.addons.javaee.buttons.ButtonBar;
import org.vaadin.addons.javaee.buttons.SaveButton;
import org.vaadin.addons.javaee.buttons.handler.CanHandleSaveButton;
import org.vaadin.addons.javaee.form.BasicEntityForm;
import org.vaadin.addons.javaee.i18n.TranslationKeys;
import org.vaadin.addons.javaee.i18n.TranslationService;

import com.googlecode.javaeeutils.jpa.PersistentEntity;

public abstract class BasicEditPage<ENTITY extends PersistentEntity> extends AbstractContentView implements CanHandleSaveButton {

    private static final long serialVersionUID = 1L;

    public static final int EDIT_FORM_RATIO = 40;

    public static final int REST_RATIO = 55;

    @Inject
    protected TranslationService translationService;

    private BasicEntityForm<ENTITY> form;

    private final String entityName;

    public BasicEditPage(String pageName) {
        this(pageName, pageName);
    }

    public BasicEditPage(String pageName, String entityName) {
        super(pageName);
        this.entityName = entityName;
    }

    @Override
    protected void initView() {
        super.initView();
        form = getForm();
        addComponent(form, EDIT_FORM_RATIO);
        ButtonBar buttonBar = initButtons();
        addComponent(buttonBar, BUTTON_RATIO);
    }

    protected abstract BasicEntityForm<ENTITY> getForm();

    protected ButtonBar initButtons() {
        ButtonBar buttonBar = new ButtonBar();
        SaveButton saveButton = new SaveButton(this, translationService.getText(TranslationKeys.BUTTON_SAVE));
        buttonBar.addComponent(saveButton);
        return buttonBar;
    }

    @Override
    public void saveClicked() {
        form.save();
    }

    public String getEntityName() {
        return entityName;
    }
}
