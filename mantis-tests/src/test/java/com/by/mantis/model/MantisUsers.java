package com.by.mantis.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class MantisUsers extends ForwardingSet<MantisUserData> {

    private Set<MantisUserData> delegate;

    public MantisUsers(MantisUsers user) {
        this.delegate = new HashSet<MantisUserData>(user.delegate);
    }

    public MantisUsers() {
        this.delegate = new HashSet<>();
    }

    public MantisUsers(Collection<MantisUserData> users) {
        this.delegate = new HashSet<MantisUserData>(users);
    }

    @Override
    protected Set<MantisUserData> delegate() {
        return delegate;
    }

    public MantisUsers withAdded(MantisUserData contact) {
        MantisUsers contacts = new MantisUsers(this);
        contacts.add(contact);
        return contacts;
    }

    public MantisUsers withOut(MantisUserData contact) {
        MantisUsers contacts = new MantisUsers(this);
        contacts.remove(contact);
        return contacts;
    }
}
