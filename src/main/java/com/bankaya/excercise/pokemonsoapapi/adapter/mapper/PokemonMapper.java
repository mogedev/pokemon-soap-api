package com.bankaya.excercise.pokemonsoapapi.adapter.mapper;

import com.bankaya.excercise.pokemonsoapapi.domain.data.VersionDetailType;
import com.bankaya.excercise.pokemonsoapapi.domain.data.VersionDetailsListType;
import com.bankaya.excercise.pokemonsoapapi.domain.data.VersionType;
import com.bankaya.excercise.pokemonsoapapi.model.client.Pokemon;
import com.bankaya.excercise.pokemonsoapapi.model.client.VersionDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public abstract class PokemonMapper {

    public static final PokemonMapper INSTANCE = Mappers.getMapper(PokemonMapper.class);

    @Mapping(target = "abilities.ability", source = "abilities")
    @Mapping(target = "heldItems.heldItem", source = "heldItems")
    public abstract com.bankaya.excercise.pokemonsoapapi.domain.data.Pokemon pokemonToPokemonDTO(Pokemon pokemon);

    VersionDetailsListType map(List<VersionDetail> versionDetails) {
        if (versionDetails == null) {
            return null;
        }

        VersionDetailsListType versionDetailsListType = new VersionDetailsListType();
        versionDetailsListType.getVersionDetail().addAll(versionDetails.stream().map(versionDetail -> {
            var version = versionDetail.version();
            var versionDetailType = new VersionDetailType();
            versionDetailType.setRarity(versionDetail.rarity());
            var versionType = new VersionType();
            versionType.setName(version.name());
            versionType.setUrl(version.url());
            versionDetailType.setVersion(versionType);
            return versionDetailType;
        }).toList());
        return versionDetailsListType;
    }

}
