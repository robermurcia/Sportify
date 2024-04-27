package Sportify.controller;

import Sportify.controller.model.noticia.NoticiaDetailWeb;
import Sportify.controller.model.noticia.NoticiaListWeb;
import Sportify.domain.entity.Noticia;
import Sportify.domain.service.NoticiaService;
import Sportify.http_response.Response;
import Sportify.mapper.NoticiaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RequestMapping("/noticias")
@RestController
public class NoticiaController {

    @Autowired
    private NoticiaService noticiaService;

    public static final String NOTICIAS ="/weblibreria/noticias";

    @Value("${LIMIT}")
    private int LIMIT;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    public Response getAll(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer pageSize) {
        pageSize = (pageSize != null)? pageSize : LIMIT;

        List<Noticia> noticias = (page != null)?noticiaService.getAll(page, pageSize) : noticiaService.getAll(null,null);
        List<NoticiaListWeb> noticiaListWebs = noticias.stream()
                .map(noticia -> NoticiaMapper.mapper.toNoticiaListWeb(noticia))
                .toList();
        long totalRecords = noticiaService.getTotalNumberOfRecords();
        Response response = Response.builder()
                .data(noticiaListWebs)
                .totalRecords(totalRecords)
                .build();
        return response;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Response findNoticiaById(@PathVariable int id) {
        Optional<Noticia> noticiaOptional = noticiaService.find(id);
        if (noticiaOptional.isPresent()) {
            Noticia noticia = noticiaOptional.get();
            NoticiaDetailWeb noticiaDetailWeb = NoticiaMapper.mapper.toNoticiaDetailWeb(noticia);
            return Response.builder()
                    .data(noticiaDetailWeb)
                    .build();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Noticia no encontrada");
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        noticiaService.delete(id);
    }
}
