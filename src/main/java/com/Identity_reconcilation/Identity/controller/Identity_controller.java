package com.Identity_reconcilation.Identity.controller;

import com.Identity_reconcilation.Identity.request.IdentityRequest;
import com.Identity_reconcilation.Identity.request.UpdateContactRequest;
import com.Identity_reconcilation.Identity.response.IdentitiesResponse;
import com.Identity_reconcilation.Identity.response.UpdateContactResponse;
import com.Identity_reconcilation.Identity.services.Identity_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/identity")
public class Identity_controller {
    @Autowired
    private Identity_service identityService;
    @PostMapping("/getDetails")
    public ResponseEntity<IdentitiesResponse> customerIdentity(@RequestBody IdentityRequest request){
       return ResponseEntity.ok(identityService.getCustomerInfo(request));
    }
    @PostMapping("/updateContacts")
    public ResponseEntity<UpdateContactResponse> updateContact(@RequestBody UpdateContactRequest request){
        return ResponseEntity.ok(identityService.updateContactInfo(request));
    }
}
