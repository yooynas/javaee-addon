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
package org.vaadin.addons.javaee.container.jpa.filter;

import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;

import com.googlecode.javaeeutils.jpa.PersistentEntity;
import com.vaadin.data.Container.Filter;
import com.vaadin.data.util.filter.Compare.Equal;

public class EqualFilterTranslator implements FilterTranslator<Equal> {

    @Override
    public Class<Equal> getAcceptedClass() {
        return Equal.class;
    }

    @Override
    public <ENTITY extends PersistentEntity> Predicate translate(Equal filter, CriteriaBuilder builder, Root<ENTITY> root,
            Map<Class<? extends Filter>, FilterTranslator<?>> filters) {
        String propertyId = (String) filter.getPropertyId();
        Expression<String> property = navigateThroughPath(root, propertyId);
        return builder.equal(property, filter.getValue());
    }

    @SuppressWarnings("unchecked")
    protected <ENTITY extends PersistentEntity> Path<String> navigateThroughPath(Root<ENTITY> root, String propertyId) {
        Path<?> path = root;
        for (String pathElement : StringUtils.split(propertyId, ".")) {
            path = path.get(pathElement);
        }
        return (Path<String>) path;
    }

}
