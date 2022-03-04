package controller;

import business.LibraryMember;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import exceptions.ExistingMemberIdException;
import exceptions.MissingRequiredInformationException;

import java.util.HashMap;

public class LibraryMemberController extends BaseController {
    public LibraryMemberController(DataAccess dataAccess) {
        super(dataAccess);
    }

    public LibraryMember addLibraryMember(LibraryMember member) throws MissingRequiredInformationException, ExistingMemberIdException {
        // Validate required information member
        HashMap<String, String> errors = validateLibraryMemberInfo(member);
        if (errors.keySet().size() > 0) {
            throw new MissingRequiredInformationException(errors);
        }

        // Check existing member id
        errors = checkExistingMemberId(member.getMemberId());
        if (errors != null && errors.size() > 0) {
            throw new ExistingMemberIdException(errors);
        }

        dataAccess.saveNewMember(member);

        return member;
    }

    private HashMap<String, String> validateLibraryMemberInfo(LibraryMember member) {
        HashMap<String, String> errors = new HashMap<>();
        if (member.getMemberId().isEmpty()) {
            errors.put("memberId", "Please enter member Id");
        }

        return errors;
    }

    private HashMap<String, String> checkExistingMemberId(String memberId) {
        LibraryMember member = dataAccess.getMember(memberId);
        if (member == null) return null;

        HashMap<String, String> errors = new HashMap<>();
        errors.put("memberId", "Member id " + memberId + " is existing.");
        return errors;
    }

    public LibraryMember findMemberId(String memberId) {
        LibraryMember member = dataAccess.getMember(memberId);
        if (member == null) {
            throw new IllegalArgumentException("MemberId is not existing");
        }

        return member;
    }
}
