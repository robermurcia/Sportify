package Sportify.controller;

import Sportify.controller.model.equipo.EquipoCreateWeb;
import Sportify.controller.model.equipo.EquipoDetailWeb;
import Sportify.controller.model.equipo.EquipoListWeb;
import Sportify.domain.entity.Equipo;
import Sportify.domain.service.EquipoService;
import Sportify.http_response.Response;
import Sportify.mapper.EquipoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RequestMapping("/equipos")
@RestController
public class EquipoController {

    @Autowired
    private EquipoService equipoService;

    public static final String EQUIPOS ="/weblibreria/equipos";

    @Value("${LIMIT}")
    private int LIMIT;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    public Response getAll(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer pageSize) {
        pageSize = (pageSize != null)? pageSize : LIMIT;

        List<Equipo> equipos = (page != null)?equipoService.getAll(page, pageSize) : equipoService.getAll(null,null);
        List<EquipoListWeb> equipoListWebs = equipos.stream()
                .map(equipo -> EquipoMapper.mapper.toEquipoListWeb(equipo))
                .toList();
        long totalRecords = equipoService.getTotalNumberOfRecords();
        Response response = Response.builder()
                .data(equipoListWebs)
                .totalRecords(totalRecords)
                .build();
        return response;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Response findEquipoById(@PathVariable int id) {
        Optional<Equipo> equipoOptional = equipoService.find(id);
        if (equipoOptional.isPresent()) {
            Equipo equipo = equipoOptional.get();
            EquipoDetailWeb equipoDetailWeb = EquipoMapper.mapper.toEquipoDetailWeb(equipo);
            return Response.builder()
                    .data(equipoDetailWeb)
                    .build();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Equipo no encontrado");
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        equipoService.delete(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public Response create(@RequestBody EquipoCreateWeb equipoCreateWeb) {
        List<Integer> competicionIds = equipoCreateWeb.getCompeticionIds();
        Equipo equipo = equipoService.create(EquipoMapper.mapper.toEquipo(equipoCreateWeb), competicionIds);
        return Response.builder().data(EquipoMapper.mapper.toEquipoDetailWeb(equipo)).build();
    }
}
