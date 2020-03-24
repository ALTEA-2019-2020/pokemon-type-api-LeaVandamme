package controller;

import com.miage.altea.tp.pokemon_type_api.bo.PokemonType;
import com.miage.altea.tp.pokemon_type_api.controller.PokemonTypeController;
import com.miage.altea.tp.pokemon_type_api.service.PokemonTypeService;
import org.junit.jupiter.api.Test;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PokemonTypeControllerTest {

    @Test
    void getPokemonType_shouldCallTheService(){
        var service = mock(PokemonTypeService.class);
        var controller = new PokemonTypeController(service);

        var pikachu = new PokemonType();
        pikachu.setId(25);
        pikachu.setName("pikachu");
        when(service.getPokemonType(25)).thenReturn(pikachu);

        var pokemon = controller.getPokemonTypeFromId(25);
        assertEquals("pikachu", pokemon.getName());

        verify(service).getPokemonType(25);
    }

    @Test
    void getAllPokemonTypes_shouldCallTheService(){
        var service = mock(PokemonTypeService.class);
        var controller = new PokemonTypeController(service);

        controller.getAllPokemonTypes();

        verify(service).getAllPokemonTypes();
    }

    @Test
    void pokemonTypeController_shouldBeAnnotated(){
        var controllerAnnotation =
                PokemonTypeController.class.getAnnotation(RestController.class);
        assertNotNull(controllerAnnotation);

        var requestMappingAnnotation =
                PokemonTypeController.class.getAnnotation(RequestMapping.class);
        assertArrayEquals(new String[]{"/pokemon-types"}, requestMappingAnnotation.value());
    }

    @Test
    void getPokemonTypeFromId_shouldBeAnnotated() throws NoSuchMethodException {
        var getPokemonTypeFromId =
                PokemonTypeController.class.getDeclaredMethod("getPokemonTypeFromId", int.class);
        var getMapping = getPokemonTypeFromId.getAnnotation(GetMapping.class);

        assertNotNull(getMapping);
        assertArrayEquals(new String[]{"/{id}"}, getMapping.value());
    }

    @Test
    void getAllPokemonTypes_shouldBeAnnotated() throws NoSuchMethodException {
        var getAllPokemonTypes =
                PokemonTypeController.class.getDeclaredMethod("getAllPokemonTypes");
        var getMapping = getAllPokemonTypes.getAnnotation(GetMapping.class);

        assertNotNull(getMapping);
        assertArrayEquals(new String[]{"/"}, getMapping.value());
    }

    @Test
    void getPokemonFromName_shouldBeAnnotated() throws NoSuchMethodException {
        var getAllPokemonTypes =
                PokemonTypeController.class.getDeclaredMethod("getPokemonTypeFromName", String.class);
        var getMapping = getAllPokemonTypes.getAnnotation(GetMapping.class);

        assertNotNull(getMapping);
        assertArrayEquals(new String[]{"/"}, getMapping.value());
    }

    @Test
    void getPokemonFromName_shouldCallTheService(){
        var service = mock(PokemonTypeService.class);
        var controller = new PokemonTypeController(service);

        var pikachu = new PokemonType();
        pikachu.setId(25);
        pikachu.setName("pikachu");
        when(service.getPokemonTypeByName("pikachu")).thenReturn(pikachu);

        var pokemon = controller.getPokemonTypeFromName("pikachu");
        assertEquals("pikachu", pokemon.getName());

        verify(service).getPokemonTypeByName("pikachu");
    }

    @Test
    void getPokemonFromTypes_shouldBeAnnotated() throws NoSuchMethodException {
        var getAllPokemonTypes =
                PokemonTypeController.class.getDeclaredMethod("getPokemonTypeFromTypes", List.class);
        var getMapping = getAllPokemonTypes.getAnnotation(GetMapping.class);

        assertNotNull(getMapping);
        assertArrayEquals(new String[]{"/"}, getMapping.value());
    }

    @Test
    void getPokemonFromTypes_shouldCallTheService(){
        var service = mock(PokemonTypeService.class);
        var controller = new PokemonTypeController(service);
        List<String> types = new ArrayList<>();
        String type = "electric";
        types.add(type);
        controller.getPokemonTypeFromTypes(types);

        verify(service).getPokemonTypeByTypes(types);
    }

}
