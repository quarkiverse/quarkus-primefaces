/*
* Licensed to the Apache Software Foundation (ASF) under one or more
* contributor license agreements.  See the NOTICE file distributed with
* this work for additional information regarding copyright ownership.
* The ASF licenses this file to You under the Apache License, Version 2.0
* (the "License"); you may not use this file except in compliance with
* the License.  You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package io.quarkiverse.primefaces.extensions.it;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;
import org.primefaces.extensions.model.inputphone.Country;

import lombok.Data;
import lombok.extern.jbosslog.JBossLog;

@Data
@JBossLog
@Named
@ViewScoped
public class InputPhoneController implements Serializable {

    private static final long serialVersionUID = 1L;
    private String phoneNumber;
    private Map<String, String> localizedCountries;

    @PostConstruct
    protected void init() {
        log.info("InputPhoneController postConstruct()");
        localizedCountries = new HashMap<>();
        localizedCountries.put("nl", "Nederland");
        localizedCountries.put("be", "België");
        localizedCountries.put("de", "Duitsland");
        phoneNumber = "610-867-5309";
    }

    public void onCountrySelect(final SelectEvent<Country> event) {
        final Country country = event.getObject();
        final String selected = "Selected " + country.getName();
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_WARN, selected, null));
        log.info(selected);
    }

    public void submit() {
        String submitted = "Phone Number  " + phoneNumber;
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, submitted, null));
        log.info(submitted);
    }

}
