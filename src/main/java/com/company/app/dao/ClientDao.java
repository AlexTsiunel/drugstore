package com.company.app.dao;

import com.company.app.model.entity.Client;

public interface ClientDao extends AbstractDao<Long, Client> {
    Client getByEmail(String email);
}
