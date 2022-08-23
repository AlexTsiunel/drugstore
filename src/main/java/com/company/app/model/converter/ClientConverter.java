package com.company.app.model.converter;

import com.company.app.model.api.Convert;
import com.company.app.model.dto.ClientDto;
import com.company.app.model.entity.Client;

public class ClientConverter extends Convert<ClientDto, Client> {
private final DigestUtil digestUtil;

    public ClientConverter(DigestUtil digestUtil) {
        this.digestUtil = digestUtil;
    }

    @Override
    public ClientDto convertEntityToDto(Client entity) {
        ClientDto clientDto = new ClientDto();
        if (entity != null) {
            clientDto.setId(entity.getId());
            clientDto.setFirstName(entity.getFirstName());
            clientDto.setLastName(entity.getLastName());
            clientDto.setEmail(entity.getEmail());
            clientDto.setDeleted(entity.isDeleted());
        }
        return clientDto;
    }

    @Override
    public Client convertDtoToEntity(ClientDto clientDto) {
        Client client = new Client();
        if (clientDto != null) {
            client.setId(clientDto.getId());
            client.setFirstName(clientDto.getFirstName());
            client.setLastName(clientDto.getLastName());
            client.setEmail(clientDto.getEmail());
            String hashedPassword = digestUtil.hashPassword(clientDto.getPassword());
            client.setPassword(hashedPassword);
            client.setDeleted(client.isDeleted());
        }
        return client;
    }
}
