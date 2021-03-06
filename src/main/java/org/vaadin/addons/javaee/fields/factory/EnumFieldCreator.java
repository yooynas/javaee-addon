package org.vaadin.addons.javaee.fields.factory;

import java.util.EnumSet;

import javax.inject.Inject;

import org.vaadin.addons.javaee.form.MultiColumnStyle;
import org.vaadin.addons.javaee.i18n.TranslationService;

import com.vaadin.data.Item;
import com.vaadin.ui.AbstractSelect;
import com.vaadin.ui.OptionGroup;

public class EnumFieldCreator<FIELD extends AbstractSelect> extends AbstractFieldCreator<FIELD> {

    public static final Object CAPTION_PROPERTY_ID = "Caption";

    @Inject
    private TranslationService translationService;

    @Override
    @SuppressWarnings("unchecked")
    protected Class<FIELD> getDefaultFieldType() {
        return (Class<FIELD>) OptionGroup.class;
    }

    @Override
    protected void initializeField(FIELD field) {
        field.setMultiSelect(false);
        field.setNullSelectionAllowed(false);
        populateEnumSelect(field);
    };

    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected void populateEnumSelect(FIELD field) {
        field.addContainerProperty("id", String.class, null);
        field.addContainerProperty(CAPTION_PROPERTY_ID, String.class, null);
        field.setItemCaptionPropertyId(CAPTION_PROPERTY_ID);
        Class<Enum> dataType = (Class<Enum>) container.getType(fieldSpec.getName());
        EnumSet<?> enumSet = EnumSet.allOf(dataType);
        for (Object r : enumSet) {
            Item newItem = field.addItem(r);
            String i18nKey = dataType.getSimpleName() + "." + r.toString();
            newItem.getItemProperty(CAPTION_PROPERTY_ID).setValue(translationService.getText(i18nKey));
            newItem.getItemProperty("id").setValue(r.toString());
        }
        if (MultiColumnStyle.HORIZONTAL.equals(fieldSpec.getMultiColumnStyle()) || (enumSet.size() <= 3)) {
            field.setStyleName("horizontal");
        }
    }

}
