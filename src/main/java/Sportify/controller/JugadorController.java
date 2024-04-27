package Sportify.controller;

import Sportify.controller.model.jugador.JugadorDetailWeb;
import Sportify.controller.model.jugador.JugadorListWeb;
import Sportify.domain.entity.Jugador;
import Sportify.domain.service.JugadorService;
import Sportify.http_response.Response;
import Sportify.mapper.JugadorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RequestMapping("/jugadores")
@RestController
public class JugadorController {
    @Autowired
    private JugadorService jugadorService;

    public static final String JUGADORES ="/weblibreria/jugadores";

    @Value("${LIMIT}")
    private int LIMIT;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    public Response getAll(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer pageSize) {
        pageSize = (pageSize != null)? pageSize : LIMIT;

        List<Jugador> jugadors = (page != null)?jugadorService.getAll(page, pageSize) : jugadorService.getAll(null,null);
        List<JugadorListWeb> jugadorListWebs = jugadors.stream()
                .map(jugador -> JugadorMapper.mapper.toJugadorListWeb(jugador))
                .toList();
        long totalRecords = jugadorService.getTotalNumberOfRecords();
        Response response = Response.builder()
                .data(jugadorListWebs)
                .totalRecords(totalRecords)
                .build();
        return response;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Response findJugadorById(@PathVariable int id) {
        Optional<Jugador> jugadorOptional = jugadorService.find(id);
        if (jugadorOptional.isPresent()) {
            Jugador jugador = jugadorOptional.get();
            JugadorDetailWeb jugadorDetailWeb = JugadorMapper.mapper.toJugadorDetailWeb(jugador);
            return Response.builder()
                    .data(jugadorDetailWeb)
                    .build();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Jugador no encontrado");
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        jugadorService.delete(id);
    }
}
