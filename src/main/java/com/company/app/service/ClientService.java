package com.company.app.service;

import com.company.app.model.dto.ClientDto;

public interface ClientService extends AbstractService<Long, ClientDto>{
    ClientDto login (String email, String password);
}
