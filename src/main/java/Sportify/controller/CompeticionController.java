package Sportify.controller;

import Sportify.controller.model.competicion.CompeticionCreateWeb;
import Sportify.controller.model.competicion.CompeticionDetailWeb;
import Sportify.controller.model.competicion.CompeticionListWeb;
import Sportify.controller.model.competicion.CompeticionUpdateWeb;
import Sportify.domain.entity.Competicion;
import Sportify.domain.service.CompeticionService;
import Sportify.http_response.Response;
import Sportify.mapper.CompeticionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RequestMapping("/competiciones")
@RestController
public class CompeticionController {

    @Autowired
    private CompeticionService competicionService;

    public static final String COMPETICIONES ="/weblibreria/competiciones";

    @Value("${LIMIT}")
    private int LIMIT;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    public Response getAll(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer pageSize) {
        pageSize = (pageSize != null)? pageSize : LIMIT;

        List<Competicion> competicions = (page != null)?competicionService.getAll(page, pageSize) : competicionService.getAll(null,null);
        List<CompeticionListWeb> competicionListWebs = competicions.stream()
                .map(competicion -> CompeticionMapper.mapper.toCompeticionListWeb(competicion))
                .toList();
        long totalRecords = competicionService.getTotalNumberOfRecords();
        Response response = Response.builder()
                .data(competicionListWebs)
                .totalRecords(totalRecords)
                .build();
        return response;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Response findCompeticionById(@PathVariable int id) {
        Optional<Competicion> competicionOptional = competicionService.find(id);
        if (competicionOptional.isPresent()) {
            Competicion competicion = competicionOptional.get();
            CompeticionDetailWeb competicionDetailWeb = CompeticionMapper.mapper.toCompeticionDetailWeb(competicion);
            return Response.builder()
                    .data(competicionDetailWeb)
                    .build();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Competición no encontrada");
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        competicionService.delete(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public Response create(@RequestBody CompeticionCreateWeb competicionCreateWeb) {
        Competicion competicion = competicionService.create(CompeticionMapper.mapper.toCompeticion(competicionCreateWeb),competicionCreateWeb.getEquiposId());
        return Response.builder().data(CompeticionMapper.mapper.toCompeticionDetailWeb(competicion)).build();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public Response update(@PathVariable("id") int id, @RequestBody CompeticionUpdateWeb competicionUpdateWeb) {
        Competicion competicion = competicionService.update(CompeticionMapper.mapper.toCompeticion(competicionUpdateWeb),id,competicionUpdateWeb.getEquiposId());
        return Response.builder().data(CompeticionMapper.mapper.toCompeticionDetailWeb(competicion)).build();
    }
}
