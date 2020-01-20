package com.miage.altea.tp.pokemon_type_api.repository;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.miage.altea.tp.pokemon_type_api.bo.PokemonType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PokemonTypeRepositoryImpl implements PokemonTypeRepository{
    private List<PokemonType> pokemons;

    public PokemonTypeRepositoryImpl() {
        try {
            var pokemonsStream = this.getClass().getResourceAsStream("/pokemons.json");
            var objectMapper = new ObjectMapper();
            var pokemonsArray = objectMapper.readValue(pokemonsStream, PokemonType[].class);
            this.pokemons = Arrays.asList(pokemonsArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public PokemonType findPokemonTypeById(int id) {
        System.out.println("Loading Pokemon information for Pokemon id " + id);

        for(PokemonType pt : pokemons){
            if(pt.getId() == id){
                return pt;
            }
        }
        return new PokemonType();
    }

    @Override
    public PokemonType findPokemonTypeByName(String name) {
        System.out.println("Loading Pokemon information for Pokemon name " + name);

        for(PokemonType pt : pokemons){
            if(pt.getName().equals(name)){
                return pt;
            }
        }
        return new PokemonType();
    }

    @Override
    public List<PokemonType> findAllPokemonType() {
        List<PokemonType> res = new ArrayList<PokemonType>() ;
        for(PokemonType pt : pokemons){
            res.add(pt);
        }
        return res;
    }
}
