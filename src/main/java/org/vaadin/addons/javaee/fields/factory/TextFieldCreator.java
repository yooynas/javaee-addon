package org.vaadin.addons.javaee.fields.factory;

import javax.validation.constraints.Size;

import org.vaadin.addons.javaee.fields.spec.FieldSpecification;
import org.vaadin.addons.javaee.jpa.EntityContainer;

import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.AbstractTextField;
import com.vaadin.ui.TextField;

public class TextFieldCreator<FIELD extends AbstractTextField> extends AbstractFieldCreator<FIELD> {

    private static final int MAX_SIZE = 15;

    public TextFieldCreator(EntityContainer<?> container, FieldSpecification fieldSpec) {
        super(container, fieldSpec);
    }

    @Override
    protected void initializeField(FIELD field) {
        field.setNullRepresentation("");
        Size size = container.getAnnotation(fieldName, Size.class);
        if (size != null && size.max() < Integer.MAX_VALUE) {
            int maxSize = Math.min(size.max(), MAX_SIZE);
            field.setWidth(maxSize, Unit.EM);
        }
    };

    @Override
    @SuppressWarnings("unchecked")
    protected Class<FIELD> getDefaultFieldType() {
        return (Class<FIELD>) TextField.class;
    }

}
