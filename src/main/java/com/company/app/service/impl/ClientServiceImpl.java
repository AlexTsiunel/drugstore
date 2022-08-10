package com.company.app.service.impl;

import com.company.app.dao.ClientDao;
import com.company.app.model.converter.ClientConverter;
import com.company.app.model.dto.ClientDto;

import com.company.app.model.entity.Client;
import com.company.app.model.exception.NoDeleteElementException;
import com.company.app.model.exception.NoSuchElementException;
import com.company.app.service.ClientService;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@Log4j2
public class ClientServiceImpl implements ClientService {
    private final ClientDao clientDao;
    private final ClientConverter clientConverter;


    public ClientServiceImpl(ClientDao clientDao, ClientConverter clientConverter) {
        this.clientDao = clientDao;
        this.clientConverter = clientConverter;
    }

    @Override
    public ClientDto getById(Long id) {
        log.debug("Calling the 'getById' method");
        Client client = clientDao.getById(id);
        if (client == null) {
            throw new NoSuchElementException(String.format("Client with id=%d not found", id));
        }
        return clientConverter.convertEntityToDto(client);
    }

    @Override
    public ClientDto saveOrUpdate(ClientDto clientDto) {
        log.debug("Calling the 'saveOrUpdate' method");
        Client client = clientDao.saveOrUpdate(clientConverter.convertDtoToEntity(clientDto));
        return clientConverter.convertEntityToDto(client);
    }

    @Override
    public List<ClientDto> getAll() {
        log.debug("Calling the 'getAll' method");

        return clientConverter.convertEntitiesToDtos(clientDao.getAll());
    }

    @Override
    public void delete(Long id) {
        log.debug("Calling the 'delete' method");
        if (!clientDao.delete(id)) {
            throw new NoDeleteElementException("Failed to delete client with id=" + id);
        }
    }

    @Override
    public ClientDto login(String email, String password) {
        log.debug("Calling the 'login' method");
        Client client = clientDao.getByEmail(email);
        if (client == null) {
            throw new NoSuchElementException(String.format("Client with email: %s not found", email));
        }
        if (!client.getPassword().equals(password)) {
            throw new NoSuchElementException("Wrong password entered");
        }
        return clientConverter.convertEntityToDto(client);
    }
}
