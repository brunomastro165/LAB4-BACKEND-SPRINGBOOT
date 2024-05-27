package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.business.facade.impl.PromocionFacadeImpl;
import com.example.buensaborback.business.mapper.ArticuloInsumoMapper;
import com.example.buensaborback.business.mapper.ArticuloManufacturadoMapper;
import com.example.buensaborback.business.services.ImagenArticuloService;
import com.example.buensaborback.business.services.ImagenPromocionService;
import com.example.buensaborback.domain.dto.ArticuloInsumo.ArticuloInsumoDto;
import com.example.buensaborback.domain.dto.ArticuloManufacturado.ArticuloManufacturadoCreateDto;
import com.example.buensaborback.domain.dto.ArticuloManufacturado.ArticuloManufacturadoDto;
import com.example.buensaborback.domain.dto.Categoria.CategoriaDto;
import com.example.buensaborback.domain.dto.Promocion.PromocionCreateDto;
import com.example.buensaborback.domain.dto.Promocion.PromocionDto;
import com.example.buensaborback.domain.dto.Promocion.PromocionEditDto;
import com.example.buensaborback.domain.dto.PromocionDetalle.PromocionDetalleCreateDto;
import com.example.buensaborback.domain.dto.PromocionDetalle.PromocionDetalleDto;
import com.example.buensaborback.domain.entities.*;
import com.example.buensaborback.presentation.base.BaseControllerImpl;
import com.example.buensaborback.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin("*")
@RequestMapping("/promocion")
public class PromocionController extends BaseControllerImpl<Promocion, PromocionDto, PromocionCreateDto, PromocionEditDto, Long, PromocionFacadeImpl> {
    @Autowired
    private ImagenPromocionService imageService;
    @Autowired
    private PromocionRepository promocionRepository;
    @Autowired
    private PromocionDetalleRepository promocionDetalleRepository;
    @Autowired
    private ArticuloRepository articuloRepository;
    @Autowired
    private ArticuloManufacturadoMapper articuloManufacturadoMapper;
    @Autowired
    private ArticuloInsumoMapper articuloInsumoMapper;

    public PromocionController(PromocionFacadeImpl facade) {
        super(facade);
    }
    @GetMapping("")
    public ResponseEntity<List<PromocionDto>> getAll() {
        // Obtén todas las promociones con el facade
        List<PromocionDto> promociones = facade.getAll();

        for (PromocionDto promo : promociones) {
            if(!promo.isEliminado() && promocionRepository.getById(promo.getId()).getDetalles() != null) {
                // Crea un array de detalles

                Set<PromocionDetalle> detalles = promocionRepository.getById(promo.getId()).getDetalles();

                Set<PromocionDetalleDto> newDetalles = new HashSet<>();
                for (PromocionDetalle detalle : detalles) {
                    // Comprueba si el detalle ha sido eliminado
                    if (!detalle.isEliminado() && detalle.getArticulo() != null) {
                        Articulo articulo = articuloRepository.getById(detalle.getArticulo().getId());
                        System.out.println(articulo);
                        if (articulo instanceof ArticuloManufacturado) {
                            System.out.println("El detalle:" + detalle.getId() + " tiene un articuloManufacturado");
                            ArticuloManufacturadoDto dto = articuloManufacturadoMapper.toDTO((ArticuloManufacturado) articulo);
                            newDetalles.add(new PromocionDetalleDto(detalle.getCantidad(), null, dto));
                        } else if (articulo instanceof ArticuloInsumo) {
                            System.out.println("El detalle:" + detalle.getId() + " tiene un articuloInsumo");
                            ArticuloInsumoDto insumoDto = articuloInsumoMapper.toDTO((ArticuloInsumo) articulo);
                            newDetalles.add(new PromocionDetalleDto(detalle.getCantidad(), insumoDto, null));
                        } else {
                            System.out.println("El detalle:" + detalle.getId() + " no tiene articulo asignado");
                        }
                    }
                }
                promo.setDetalles(newDetalles);
            }
        }

        return ResponseEntity.ok(promociones);
    }


    @Override
    @GetMapping("/{id}")
    public ResponseEntity<PromocionDto> getById(@PathVariable Long id) {
        //obtengo la promocion con el facade aca los articulos son nulos
        PromocionDto promo = facade.getById(id);

        //creo un array de detalles posta
        Set<PromocionDetalle> detalles = promocionRepository.getById(promo.getId()).getDetalles();

        Set<PromocionDetalleDto> newDetalles = new HashSet<>();
        for (PromocionDetalle detalle : detalles) {
            Articulo articulo = articuloRepository.getById(detalle.getArticulo().getId());
            System.out.println(articulo);
            if (articulo instanceof ArticuloManufacturado) {
                System.out.println("El detalle:"+detalle.getId()+" tiene un articuloManufacturado");
                ArticuloManufacturadoDto dto = articuloManufacturadoMapper.toDTO((ArticuloManufacturado) articulo);
                newDetalles.add(new PromocionDetalleDto(detalle.getCantidad(), null, dto));
            } else if (articulo instanceof ArticuloInsumo) {
                System.out.println("El detalle:"+detalle.getId()+" tiene un articuloInsumo");
                ArticuloInsumoDto insumoDto = articuloInsumoMapper.toDTO((ArticuloInsumo) articulo);
                newDetalles.add(new PromocionDetalleDto(detalle.getCantidad(), insumoDto, null));
            }else{
                System.out.println("El detalle:"+detalle.getId()+" no tiene articulo asignado");
            }
        }
        promo.setDetalles(newDetalles);

        return ResponseEntity.ok(promo);
    }


    @PostMapping(value = "/save", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<PromocionDto> create(@RequestPart("entity") PromocionCreateDto entity,
                                               @RequestPart("files") MultipartFile[] files) {
        System.out.println(entity);
        PromocionDto promocion = facade.createNew(entity);
        Set<PromocionDetalle> detalles = promocionRepository.getById(promocion.getId()).getDetalles();
        List<PromocionDetalle> detalles2 = new ArrayList<>();
        for (PromocionDetalle detalle:
             detalles) {
            detalles2.add(detalle);
        }
        int i = 0;
        for (PromocionDetalleCreateDto detalle:
             entity.getDetalles()) {
            PromocionDetalle aux = promocionDetalleRepository.getById(detalles2.get(i).getId());
            aux.setArticulo(articuloRepository.getById(detalle.getIdArticulo()));
            promocionDetalleRepository.save(aux);
            i++;
        }

        promocion.setImagenes(imageService.uploadImagesP(files, promocion.getId()));

        return ResponseEntity.ok(promocion);
    }

}
