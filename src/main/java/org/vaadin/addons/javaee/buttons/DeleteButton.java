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
package org.vaadin.addons.javaee.buttons;

import org.vaadin.addons.javaee.TranslationKeys;
import org.vaadin.addons.javaee.buttons.clickhandler.DeleteClickHandler;
import org.vaadin.addons.javaee.buttons.handler.CanHandleDeleteButton;

public class DeleteButton extends BasicButton {

    public DeleteButton() {
        super(TranslationKeys.BUTTON_DELETE);
    }

    public DeleteButton(CanHandleDeleteButton canHandle) {
        super(TranslationKeys.BUTTON_DELETE);
        addListener(new DeleteClickHandler(this, canHandle));
    }

    public DeleteButton(CanHandleDeleteButton canHandle, String title) {
        super(TranslationKeys.BUTTON_DELETE, title);
        addListener(new DeleteClickHandler(this, canHandle));
    }

}
