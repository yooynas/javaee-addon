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

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.vaadin.virkki.cdiutils.TextBundle;

/**
 * Generic translation service. To extend it, just create a new TranslationSPI. The impl looks at all TranslationSPI implementations inside
 * the CDI container and uses them. The order cannot be specified.
 * 
 * @author contact@thomas-letsch.de
 * 
 */
public interface TranslationService extends TextBundle, Serializable {

    Map<String, String> get(String prefix, List<String> values);

}
