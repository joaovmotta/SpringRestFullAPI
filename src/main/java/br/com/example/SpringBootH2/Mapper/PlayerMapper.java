package br.com.example.SpringBootH2.Mapper;

import br.com.example.SpringBootH2.Entity.Player;
import br.com.example.SpringBootH2.Request.PlayerRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface PlayerMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true)
    })
    Player requestToPlayer(PlayerRequest request);
}
