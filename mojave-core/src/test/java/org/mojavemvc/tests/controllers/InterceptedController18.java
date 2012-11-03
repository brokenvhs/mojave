/*
 * Copyright (C) 2011-2012 Mojavemvc.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.mojavemvc.tests.controllers;

import java.util.List;

import org.mojavemvc.annotations.InterceptedBy;
import org.mojavemvc.annotations.OPTIONSAction;
import org.mojavemvc.annotations.StatelessController;
import org.mojavemvc.tests.interceptors.Interceptor1;
import org.mojavemvc.tests.interceptors.Interceptor1b;
import org.mojavemvc.views.JspView;
import org.mojavemvc.views.View;

/**
 * 
 * @author Luis Antunes
 */
@StatelessController("intercepted18")
@InterceptedBy(Interceptor1.class)
public class InterceptedController18 {

    public static List<String> invocationList;

    @OPTIONSAction
    @InterceptedBy(Interceptor1b.class)
    public View someAction() {

        invocationList.add("optionsAction");
        return new JspView("param.jsp").withAttribute("var", "optionsAction");
    }
}
