package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.business.facade.impl.PromocionFacadeImpl;
import com.example.buensaborback.business.mapper.ArticuloInsumoMapper;
import com.example.buensaborback.business.mapper.ArticuloManufacturadoMapper;
import com.example.buensaborback.business.mapper.PromocionMapper;
import com.example.buensaborback.business.services.ImagenPromocionService;
import com.example.buensaborback.domain.dto.ArticuloInsumo.ArticuloInsumoDto;
import com.example.buensaborback.domain.dto.ArticuloManufacturado.ArticuloManufacturadoCreateDto;
import com.example.buensaborback.domain.dto.ArticuloManufacturado.ArticuloManufacturadoDto;
import com.example.buensaborback.domain.dto.ImagenArticuloDto.ImagenArticuloDto;
import com.example.buensaborback.domain.dto.ImagenPromocion.ImagenPromocionDto;
import com.example.buensaborback.domain.dto.Promocion.PromocionCreateDto;
import com.example.buensaborback.domain.dto.Promocion.PromocionDto;
import com.example.buensaborback.domain.dto.Promocion.PromocionEditDto;
import com.example.buensaborback.domain.dto.PromocionDetalle.PromocionDetalleCreateDto;
import com.example.buensaborback.domain.dto.PromocionDetalle.PromocionDetalleDto;
import com.example.buensaborback.domain.dto.Sucursal.SucursalShortDto;
import com.example.buensaborback.domain.entities.*;
import com.example.buensaborback.presentation.base.BaseControllerImpl;
import com.example.buensaborback.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.stream.Collectors;

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
    @Autowired
    private SucursalRepository sucursalRepository;
    @Autowired
    private PromocionMapper promocionMapper;
    @Autowired
    private ImagenPromocionRepository imagenPromocionRepository;

    public PromocionController(PromocionFacadeImpl facade) {
        super(facade);
    }

    @GetMapping("")
    public ResponseEntity<List<PromocionDto>> getAll() {
        // Obtén todas las promociones con el facade
        List<PromocionDto> promociones = facade.getAll();
        for (PromocionDto promo : promociones) {
            if (!promo.isEliminado() && promocionRepository.getById(promo.getId()).getDetalles() != null) {
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
        // Obtengo la promoción con el facade, aquí los artículos son nulos
        PromocionDto promo = facade.getById(id);

        // Creo un conjunto de detalles
        Set<PromocionDetalle> detalles = promocionRepository.getById(promo.getId()).getDetalles();

        Set<PromocionDetalleDto> newDetalles = detalles.stream().map(detalle -> {
            Articulo articulo = articuloRepository.getById(detalle.getArticulo().getId());
            if (articulo instanceof ArticuloManufacturado) {
                ArticuloManufacturadoDto dto = articuloManufacturadoMapper.toDTO((ArticuloManufacturado) articulo);
                return new PromocionDetalleDto(detalle.getCantidad(), null, dto);
            } else if (articulo instanceof ArticuloInsumo) {
                ArticuloInsumoDto insumoDto = articuloInsumoMapper.toDTO((ArticuloInsumo) articulo);
                return new PromocionDetalleDto(detalle.getCantidad(), insumoDto, null);
            } else {
                return null;
            }
        }).filter(Objects::nonNull).collect(Collectors.toSet());

        promo.setDetalles(newDetalles);

        return ResponseEntity.ok(promo);
    }

    /*
    @PutMapping(value = "save/{id}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<PromocionDto> edit(@RequestPart("entity") PromocionCreateDto entity,
                                             @RequestPart("files") MultipartFile[] files, @PathVariable Long id) {

        PromocionDto promocion = facade.createNew(entity);
        List<ImagenPromocionDto> imagenes = imageService.uploadImagesP(files, id);
        for (ImagenPromocionDto imagen :
                imagenes) {
            promocion.getImagenes().add(imagen);
        }
        PromocionCreateDto promocionActualizada = entity;
        promocionActualizada.setId(id);
        promocion = facade.createNew(promocionActualizada);


        return ResponseEntity.ok(promocion);
    }
     */
    @PostMapping(value = "/save", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<PromocionDto> createOrUpdate(@RequestPart("entity") PromocionCreateDto entity,
                                                       @RequestPart("files") MultipartFile[] files) {
        try {
            PromocionDto promocion = facade.createNew(entity);
            Set<PromocionDetalle> detalles = promocionRepository.getById(promocion.getId()).getDetalles();
            List<PromocionDetalle> detalles2 = new ArrayList<>(detalles);
            int i = 0;
            for (PromocionDetalleCreateDto detalle : entity.getDetalles()) {
                PromocionDetalle aux = promocionDetalleRepository.getById(detalles2.get(i).getId());
                aux.setArticulo(articuloRepository.getById(detalle.getIdArticulo()));
                promocionDetalleRepository.save(aux);
                i++;
            }
            List<ImagenPromocionDto> imagenes = imageService.uploadImagesP(files, promocion.getId());
            for (ImagenPromocionDto imagen : imagenes) {
                promocion.getImagenes().add(imagen);
            }
            Promocion p = promocionMapper.toEntity(promocion);
            for (SucursalShortDto sucursalDto : promocion.getSucursales()) {
                Sucursal sucursal = sucursalRepository.getById(sucursalDto.getId());
                sucursal.getPromociones().add(p);
                sucursalRepository.save(sucursal);
            }
            return ResponseEntity.ok(promocion);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }



}
