package org.vaadin.addons.javaee.fields.converter;

import java.util.Locale;

import org.vaadin.addons.javaee.container.EntityContainer;
import org.vaadin.addons.javaee.container.EntityItem;

import com.googlecode.javaeeutils.jpa.PersistentEntity;
import com.vaadin.data.util.converter.Converter;
import com.vaadin.ui.AbstractSelect;

public class SelectEntityConverter<ENTITY extends PersistentEntity> implements Converter<Object, ENTITY> {

    private static final long serialVersionUID = 1L;

    private EntityContainer<ENTITY> container;

    private AbstractSelect select;

    public SelectEntityConverter(EntityContainer<ENTITY> subContainer, AbstractSelect select) {
        this.container = subContainer;
        this.select = select;
    }

    @SuppressWarnings("unchecked")
    @Override
    public ENTITY convertToModel(Object value, Locale locale) throws Converter.ConversionException {
        if (value == null) {
            return null;
        }
        if (!(value instanceof Long)) {
            return null;
        }
        return ((EntityItem<ENTITY>) container.getItem(value)).getEntity();
    }

    @Override
    public Object convertToPresentation(ENTITY value, Locale locale) throws Converter.ConversionException {
        if (value == null) {
            return select.getNullSelectionItemId();
        }
        return value.getId();
    }

    @Override
    public Class<ENTITY> getModelType() {
        return container.getEntityClass();
    }

    @Override
    public Class<Object> getPresentationType() {
        return Object.class;
    }

}
