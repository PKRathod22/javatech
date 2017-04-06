package com.pk.configuration;

import org.hibernate.envers.RevisionListener;

public class AuditRevisionListener implements RevisionListener {

    public void newRevision(Object revisionEntity) {
    	AuditEntity exampleRevEntity = (AuditEntity) revisionEntity;
        exampleRevEntity.setUsername("audit");
    }
}
