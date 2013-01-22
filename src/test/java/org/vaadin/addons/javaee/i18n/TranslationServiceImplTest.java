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
package org.vaadin.addons.javaee.i18n;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.enterprise.inject.Instance;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class TranslationServiceImplTest {

    private static final String KEY1 = "key";

    private static final String VALUE1 = "Value";

    private static final String KEY3 = "key3";

    private static final String VALUE3 = "Value3";

    private static final String KEY_KEY3 = "key.key3";

    private static final String VALUE_KEY_KEY3 = "ValueKK3";

    private static final String UNKNOWN_KEY2 = "key2";

    private static final String KEY1_KEY = "key1.key";

    private static final String KEY2_KEY3 = UNKNOWN_KEY2 + "." + KEY3;

    @InjectMocks
    private TranslationServiceImpl translation = new TranslationServiceImpl();

    @Mock
    Instance<TranslationSPI> providers;

    @Mock
    TranslationSPI spi;

    @Mock
    private SelectedLocale selectedLocale;

    private Locale locale = Locale.GERMAN;

    List<String> unknownValues = Arrays.asList(UNKNOWN_KEY2, KEY1_KEY, KEY2_KEY3);

    @Before
    @SuppressWarnings("unchecked")
    public void initMock() {
        MockitoAnnotations.initMocks(this);
        when(selectedLocale.getLocale()).thenReturn(locale);
        for (String value : unknownValues) {
            when(spi.get(value, locale)).thenReturn(value);
        }
        when(spi.get(KEY1, locale)).thenReturn(VALUE1);
        when(spi.get(KEY_KEY3, locale)).thenReturn(VALUE_KEY_KEY3);
        when(spi.get(KEY3, locale)).thenReturn(VALUE3);
        Iterator<TranslationSPI> iterator = mock(Iterator.class);
        when(iterator.hasNext()).thenReturn(true).thenReturn(false);
        when(iterator.next()).thenReturn(spi);
        when(providers.iterator()).thenReturn(iterator);
    }

    @Test
    public void testGetSimple() {
        assertEquals(VALUE1, translation.getText(KEY1));
    }

    @Test
    public void testGetSimpleNotFound() {
        assertEquals(UNKNOWN_KEY2, translation.getText(UNKNOWN_KEY2));
    }

    @Test
    public void testGetTwoElements() {
        assertEquals(VALUE_KEY_KEY3, translation.getText(KEY_KEY3));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testGetLastOfTwoElements() {
        Iterator<TranslationSPI> iterator = mock(Iterator.class);
        when(iterator.hasNext()).thenReturn(true).thenReturn(false).thenReturn(true).thenReturn(false);
        when(iterator.next()).thenReturn(spi);
        when(providers.iterator()).thenReturn(iterator);
        assertEquals(VALUE1, translation.getText(KEY1_KEY));
    }

    @Test
    public void testGetPossibleKeyVariations() {
        TranslationServiceImpl translation = new TranslationServiceImpl();
        List<String> list = translation.getPossibleKeyVariations(KEY1_KEY);
        assertEquals("List[0]", KEY1_KEY, list.get(0));
        assertEquals("List[1]", KEY1, list.get(1));
    }
}
