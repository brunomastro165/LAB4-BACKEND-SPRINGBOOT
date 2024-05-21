package com.example.buensaborback.business.mapper;

import com.example.buensaborback.business.services.ArticuloInsumoService;
import com.example.buensaborback.business.services.ArticuloManufacturadoService;
import com.example.buensaborback.business.services.SucursalService;
import com.example.buensaborback.domain.dto.Categoria.CategoriaCreateDto;
import com.example.buensaborback.domain.dto.Categoria.CategoriaDto;
import com.example.buensaborback.domain.entities.Categoria;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {SucursalService.class, ArticuloManufacturadoService.class, ArticuloInsumoService.class})
public interface CategoriaMapper extends BaseMapper<Categoria, CategoriaDto, CategoriaCreateDto, CategoriaCreateDto> {
}
