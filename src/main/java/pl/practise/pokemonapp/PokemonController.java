package pl.practise.pokemonapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.practise.pokemonapp.pokemonDetails.NoPokemonFoundException;
import pl.practise.pokemonapp.pokemonDetails.PokemonDetailsResponse;
import pl.practise.pokemonapp.pokemonDetails.PokemonDetailsService;
import pl.practise.pokemonapp.pokemonList.Pokemon;
import pl.practise.pokemonapp.pokemonList.PokemonListService;

import java.util.List;

@RestController
@RequestMapping("/pokemon")
class PokemonController {

    private final PokemonListService pokemonListService;
    private final PokemonDetailsService pokemonDetailsService;

    @Autowired
    PokemonController(PokemonListService pokemonListService,
                      PokemonDetailsService pokemonDetailsService) {
        this.pokemonListService = pokemonListService;
        this.pokemonDetailsService = pokemonDetailsService;
    }

    @GetMapping("/list")
    List<Pokemon> getPokemonList(){
        return pokemonListService.getPokemonList();
    }

    @GetMapping("/{name}")
    PokemonDetailsResponse getPokemonDetails(@PathVariable String name) {
        return pokemonDetailsService.getPokemonDetails(name);
    }

    @ExceptionHandler(value = NoPokemonFoundException.class)
    public ResponseEntity<ErrorMessage> handleNoPokemonFoundException(NoPokemonFoundException exception) {
        return new ResponseEntity<>(new ErrorMessage(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
