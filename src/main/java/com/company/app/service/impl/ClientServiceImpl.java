package com.company.app.service.impl;

import com.company.app.dao.ClientDao;
import com.company.app.model.converter.ClientConverter;
import com.company.app.model.dto.ClientDto;

import com.company.app.model.entity.Client;
import com.company.app.service.ClientService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ClientServiceImpl implements ClientService {
    private final ClientDao clientDao;
    private final ClientConverter clientConverter;

    private static Logger logger = LogManager.getLogger(ClientServiceImpl.class);

    public ClientServiceImpl(ClientDao clientDao, ClientConverter clientConverter) {
        this.clientDao = clientDao;
        this.clientConverter = clientConverter;
    }

    @Override
    public ClientDto getById(Long id) {
        logger.debug("Calling the 'getById' method");
        Client client = clientDao.getById(id);
        return clientConverter.convertEntityToDto(client);
    }

    @Override
    public ClientDto saveOrUpdate(ClientDto clientDto) {
        logger.debug("Calling the 'saveOrUpdate' method");
        Client client = clientDao.saveOrUpdate(clientConverter.convertDtoToEntity(clientDto));
        return clientConverter.convertEntityToDto(client);
    }

    @Override
    public List<ClientDto> getAll() {
        logger.debug("Calling the 'getAll' method");
        return clientConverter.convertEntitiesToDtos(clientDao.getAll());
    }

    @Override
    public void delete(Long id) {
        logger.debug("Calling the 'delete' method");
        clientDao.delete(id);
    }
}
