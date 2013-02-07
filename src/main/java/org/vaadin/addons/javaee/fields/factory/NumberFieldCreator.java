package org.vaadin.addons.javaee.fields.factory;

import javax.validation.constraints.Digits;

import org.vaadin.addons.javaee.fields.NumberField;
import org.vaadin.addons.javaee.i18n.TranslationService;
import org.vaadin.addons.javaee.jpa.EntityContainer;

import com.vaadin.server.Sizeable.Unit;

public class NumberFieldCreator<FIELD extends NumberField> extends AbstractFieldCreator<FIELD> {

    static final int DEFAULT_SIZE_EM = 4;

    public NumberFieldCreator(TranslationService translationService, EntityContainer<?> container, String fieldName, Class<FIELD> fieldType) {
        super(translationService, container, fieldName, fieldType);
    }

    @Override
    protected void initializeField(FIELD field) {
        super.initializeField(field);
        Digits digits = container.getAnnotation(fieldName, Digits.class);
        if (digits != null) {
            field.setWidth(digits.integer() + digits.fraction() + 1, Unit.EM);
        } else {
            field.setWidth(DEFAULT_SIZE_EM, Unit.EM);
        }
        field.setNullRepresentation("");
    }

    @Override
    @SuppressWarnings("unchecked")
    protected Class<FIELD> getDefaultFieldType() {
        return (Class<FIELD>) NumberField.class;
    };

}
