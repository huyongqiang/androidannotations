/**
 * Copyright (C) 2010-2015 eBusiness Information, Excilys Group
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed To in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.androidannotations.internal.core.handler;

import javax.lang.model.element.Element;

import org.androidannotations.AndroidAnnotationsEnvironment;
import org.androidannotations.ElementValidation;
import org.androidannotations.annotations.InjectMenu;
import org.androidannotations.handler.BaseAnnotationHandler;
import org.androidannotations.holder.HasOptionsMenu;

import com.helger.jcodemodel.JBlock;
import com.helger.jcodemodel.JExpr;
import com.helger.jcodemodel.JVar;

public class InjectMenuHandler extends BaseAnnotationHandler<HasOptionsMenu> {

	public InjectMenuHandler(AndroidAnnotationsEnvironment environment) {
		super(InjectMenu.class, environment);
	}

	@Override
	public void validate(Element element, ElementValidation valid) {
		validatorHelper.enclosingElementHasEActivityOrEFragment(element, valid);

		validatorHelper.isDeclaredType(element, valid);

		validatorHelper.extendsMenu(element, valid);

		validatorHelper.isNotPrivate(element, valid);
	}

	@Override
	public void process(Element element, HasOptionsMenu holder) {
		String fieldName = element.getSimpleName().toString();
		JBlock body = holder.getOnCreateOptionsMenuMethodBody();
		JVar menuParam = holder.getOnCreateOptionsMenuMenuParam();

		body.assign(JExpr._this().ref(fieldName), menuParam);
	}
}