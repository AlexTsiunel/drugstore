package com.company.app.model.converter;

import com.company.app.model.api.Convert;
import com.company.app.model.dto.ClientDto;
import com.company.app.model.entity.Client;

public class ClientConverter implements Convert<ClientDto, Client> {
    private OrderConverter orderConverter;
    private RecipeConverter recipeConverter;

    public ClientConverter(OrderConverter orderConverter, RecipeConverter recipeConverter) {
        this.orderConverter = orderConverter;
        this.recipeConverter = recipeConverter;
    }

    @Override
    public ClientDto convertEntityToDto(Client entity) {
        ClientDto clientDto = new ClientDto();
        if(entity != null){
            clientDto.setId(entity.getId());
            clientDto.setFirstName(entity.getFirstName());
            clientDto.setLastName(entity.getLastName());
            clientDto.setEmail(entity.getEmail());
            clientDto.setPassword(entity.getPassword());
            clientDto.setOrders(orderConverter.convertEntitiesToDtos(entity.getOrders()));
            clientDto.setRecipes(recipeConverter.convertEntitiesToDtos(entity.getRecipes()));
        }
        return clientDto;
    }

    @Override
    public Client convertDtoToEntity(ClientDto clientDto) {
        Client client = new Client();
        if(clientDto != null){
            client.setId(clientDto.getId());
            client.setFirstName(clientDto.getFirstName());
            client.setLastName(clientDto.getLastName());
            client.setEmail(clientDto.getEmail());
            client.setPassword(clientDto.getPassword());
            client.setOrders(orderConverter.convertDtosToEntities(clientDto.getOrders()));
            client.setRecipes(recipeConverter.convertDtosToEntities(clientDto.getRecipes()));
        }
        return client;
    }
}
