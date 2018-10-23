package ru.stqa.pft.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Groups extends ForwardingSet<GroupData> {

    private Set<GroupData> delegate;

    public Groups(Groups groups) {
        this.delegate = new HashSet<>(groups.delegate);
    }

    public Groups(Collection<GroupData> groups) {
        this.delegate = new HashSet<>(groups);
    }

    public Groups() {
        this.delegate = new HashSet<>();
    }

    @Override
    protected Set<GroupData> delegate() {
        return delegate;
    }

    public Groups whithAdded(GroupData group) {
        Groups groups = new Groups(this);
        groups.add(group);
        return groups;
    }

    public Groups whithout (GroupData group) {
        Groups groups = new Groups(this);
        groups.remove(group);
        return groups;
    }
}
