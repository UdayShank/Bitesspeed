package com.Identity_reconcilation.Identity.services;

import com.Identity_reconcilation.Identity.entity.Contact;
import com.Identity_reconcilation.Identity.request.IdentityRequest;
import com.Identity_reconcilation.Identity.request.UpdateContactRequest;
import com.Identity_reconcilation.Identity.response.IdentitiesResponse;
import com.Identity_reconcilation.Identity.response.IdentityResponse;
import com.Identity_reconcilation.Identity.response.UpdateContactResponse;
import com.Identity_reconcilation.Identity.repository.ContactRepository;
import com.Identity_reconcilation.Identity.util.Errors;
import com.Identity_reconcilation.Identity.util.ReconcilationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@Slf4j
public class Identity_service {
    @Autowired
    private ContactRepository contactRepository;

    public UpdateContactResponse updateContactInfo(UpdateContactRequest request) {
        UpdateContactResponse response = new UpdateContactResponse();
        try {
            Contact contact = new Contact();
            List<Contact> contactByEmail = contactRepository.findByEmail(request.getEmail());
            List<Contact> contactByPhone = contactRepository.findByPhoneNumber(request.getPhoneNumber());
            if (!contactByEmail.isEmpty() && !contactByPhone.isEmpty()){
                response.setMessage("Both the emailId and PhoneNo are present");
                return response;
            }
            if (!contactByEmail.isEmpty()) {
                for (Contact entity : contactByEmail) {
                    if (entity.getLinkedID() == 0) {
                        contact.setLinkedID(entity.getId());
                    } else {
                        contact.setLinkedID(entity.getLinkedID());
                    }
                }
            }else if (!contactByPhone.isEmpty()) {
                for (Contact entity : contactByPhone) {
                    if (entity.getLinkedID() == 0) {
                        contact.setLinkedID(entity.getId());
                    } else {
                        contact.setLinkedID(entity.getLinkedID());
                    }
                }
            }else {
                contact.setLinkedID(0);
            }

            contact.setLinkPrecedence(request.getLinkPrecedence());
            contact.setEmail(request.getEmail());
            contact.setPhoneNumber(request.getPhoneNumber());
            contact.setCreatedAt(LocalDateTime.now());
            contact.setUpdatedAt(LocalDateTime.now());
            contactRepository.save(contact);
            response.setMessage("Contact details were added successfully");

            return response;
        } catch (Exception e) {
            throw new ReconcilationException(HttpStatus.UNPROCESSABLE_ENTITY.value(), Errors.UNABLE_TO_ADD_CONTACTS);
        }
    }

    public IdentitiesResponse getCustomerInfo(IdentityRequest request) {
        try {
            IdentitiesResponse response = new IdentitiesResponse();
            List<IdentityResponse> identityResponses = new ArrayList<>();
            Set<String> uniqueEmails = new HashSet<>();

            List<Contact> contactByEmail = contactRepository.findByEmail(request.getEmail());
            List<Contact> contactByPhone = contactRepository.findByPhoneNumber(request.getPhoneNumber());

            if (contactByEmail.isEmpty() && contactByPhone.isEmpty()) {
                response.setMessage("Details not found in the DB");
                return response;
            }

            identityResponses.addAll(createIdentityResponses(contactByEmail, uniqueEmails));
            identityResponses.addAll(createIdentityResponsesForPhone(contactByPhone, identityResponses, uniqueEmails));

            response.setDetails(identityResponses);
            response.setMessage("Details found");
            return response;
        } catch (Exception e) {
            throw new ReconcilationException(HttpStatus.UNPROCESSABLE_ENTITY.value(), Errors.UNABLE_TO_GET_CONTACTS);
        }
    }

    private List<IdentityResponse> createIdentityResponses(List<Contact> contacts, Set<String> uniqueEmails) {
        List<IdentityResponse> identityResponses = new ArrayList<>();
        for (Contact contact : contacts) {
            if (uniqueEmails.add(contact.getEmail())) {
                IdentityResponse identityResponse = createIdentityResponse(contact);
                identityResponses.add(identityResponse);
            }
        }
        return identityResponses;
    }

    private List<IdentityResponse> createIdentityResponsesForPhone(List<Contact> contacts, List<IdentityResponse> existingResponses, Set<String> uniqueEmails) {
        List<IdentityResponse> identityResponses = new ArrayList<>();
        for (Contact contact : contacts) {
            if (!uniqueEmails.contains(contact.getEmail())) {
                IdentityResponse identityResponse = createIdentityResponse(contact);
                identityResponses.add(identityResponse);
            }
        }
        return identityResponses;
    }


    private IdentityResponse createIdentityResponse(Contact contact) {
        IdentityResponse identityResponse = new IdentityResponse();
        identityResponse.setId(contact.getId());
        identityResponse.setEmail(contact.getEmail());
        identityResponse.setPhoneNumber(contact.getPhoneNumber());
        identityResponse.setLinkedID(contact.getLinkedID() != null ? contact.getLinkedID() : 0);
        identityResponse.setLinkPrecedence(contact.getLinkPrecedence());
        identityResponse.setCreatedAt(contact.getCreatedAt());
        identityResponse.setUpdatedAt(contact.getUpdatedAt());
        identityResponse.setDeletedAt(contact.getDeletedAt() != null ? contact.getDeletedAt() : null);
        return identityResponse;
    }
}
