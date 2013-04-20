/*
 * Copyright (C) 2011-2013 Mojavemvc.org
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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.mojavemvc.annotations.Action;
import org.mojavemvc.annotations.DefaultAction;
import org.mojavemvc.annotations.Model;
import org.mojavemvc.annotations.Param;
import org.mojavemvc.annotations.StatelessController;
import org.mojavemvc.forms.UploadedFile;
import org.mojavemvc.tests.forms.SomeForm;
import org.mojavemvc.tests.forms.SomeFormWithBoolean;
import org.mojavemvc.tests.forms.SubmittableForm;
import org.mojavemvc.views.JSP;
import org.mojavemvc.views.View;

/**
 * @author Luis Antunes
 */
@StatelessController("form-controller")
public class FormController {

    @Action("process")
    public View processForm(@Model SomeForm form) {

        return new JSP("form-result").withAttribute("username", form.getUserName()).withAttribute("password",
                form.getPassword());
    }

    @Action("form2")
    public View form2() {
        return new JSP("form2");
    }

    @Action("process2")
    public View processForm2(@Model SomeForm form, @Param("p1") String p1) {

        return new JSP("form-result2").withAttribute("username", form.getUserName())
                .withAttribute("password", form.getPassword()).withAttribute("p1", p1);
    }

    @Action("form3")
    public View form3() {
        return new JSP("form3");
    }

    @Action("process3")
    public View processForm3(@Model SubmittableForm form) {

        return new JSP("form-result3").withAttribute("username", form.getUserName()).withAttribute("password",
                form.getPassword());
    }

    @Action("populated")
    public View populatedForm() {

        SomeForm form = new SomeForm();
        form.setPassword("pswd");
        form.setUserName("uname");

        return new JSP("form").withModel(form);
    }

    @Action("process4")
    public View processFormWithBoolean(@Model SomeFormWithBoolean form) {

        return new JSP("form-result4").withAttribute("flag", form.isSomeFlag());
    }

    @Action("form4")
    public View form4() {
        return new JSP("form4");
    }
    
    @Action("form5")
    public View form5() {
        return new JSP("form5");
    }
    
    @Action("upload")
    public View upload(@Param("file") UploadedFile file, @Param("userName") String userName,
            @Param("p1") String p1) {
        
        String content = "null";
        InputStream in = null;
        try {
            in = file.getInputStream();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length = 0;
            while ((length = in.read(buffer)) != -1) {
               out.write(buffer, 0, length);
            }
            content = new String(out.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) in.close(); 
            } catch (Exception e) {
            }
        }
        
        String fileName = file.getFileName();
        if (fileName != null && fileName.trim().length() != 0) {
            int lastIdx = fileName.lastIndexOf(File.separatorChar);
            if (lastIdx != -1) {
                fileName = fileName.substring(lastIdx + 1);
            }
        }
        
        return new JSP("form-result5")
            .withAttribute("file-name", fileName)
            .withAttribute("file-size", file.getSize())
            .withAttribute("file-contenttype", file.getContentType())
            .withAttribute("file-inmemory", file.isInMemory())
            .withAttribute("file-username", userName)
            .withAttribute("file-content", content)
            .withAttribute("query-param", p1);
    }

    @DefaultAction
    public View someDefaultAction() {

        return new JSP("form");
    }

}
