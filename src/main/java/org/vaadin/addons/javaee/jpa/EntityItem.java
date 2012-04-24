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
package org.vaadin.addons.javaee.jpa;

import com.googlecode.javaeeutils.jpa.PersistentEntity;
import com.vaadin.data.Buffered;
import com.vaadin.data.Property;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.util.BeanItem;


public class EntityItem<ENTITY extends PersistentEntity> extends BeanItem<ENTITY> implements Buffered {

    private final EntityContainer<ENTITY> jpaContainer;

    public EntityItem(EntityContainer<ENTITY> jpaContainer, ENTITY bean) {
        super(bean);
        this.jpaContainer = jpaContainer;
    }

    @Override
    public Property<?> getItemProperty(Object id) {
        Property<?> itemProperty = super.getItemProperty(id);
        if (itemProperty == null) {
            addNestedItem(id);
            itemProperty = super.getItemProperty(id);
        }
        return itemProperty;
    }

    private void addNestedItem(Object id) {
        if (id instanceof String) {
            String nestedId = (String) id;
            if (nestedId.contains(".")) {
                addNestedProperty(nestedId);
            }
        }
    }

    @Override
    public void commit() throws SourceException, InvalidValueException {
        if (getBean().getId() == null) {
            jpaContainer.addItem(getBean());
        } else {
            jpaContainer.updateItem(this);
        }
    }

    @Override
    public void discard() throws SourceException {
        // TODO: implement!
    }

    @Override
    public boolean isWriteThrough() {
        return false;
    }

    @Override
    public void setWriteThrough(boolean writeThrough) throws SourceException, InvalidValueException {
    }

    @Override
    public boolean isReadThrough() {
        return false;
    }

    @Override
    public void setReadThrough(boolean readThrough) throws SourceException {
    }

    @Override
    public void setBuffered(boolean buffered) {
    }

    @Override
    public boolean isBuffered() {
        return true;
    }

    @Override
    public boolean isModified() {
        return false;
    }

}
