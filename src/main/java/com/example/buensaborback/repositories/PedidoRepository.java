package com.example.buensaborback.repositories;

import com.example.buensaborback.domain.entities.Pedido;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import java.time.LocalDate;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import org.springframework.data.repository.query.Param;

@Repository
public interface PedidoRepository extends BaseRepository<Pedido, Long> {
    @Query("select sum(p.total) from Pedido p where p.fechaPedido between :fechaInicio and :fechaFin and p.sucursal.id = :sucursal")
    Optional<Double> getIngresos(@Param("fechaInicio") LocalDate fechaInicio, @Param("fechaFin") LocalDate fechaFin, @Param("sucursal") Long sucursal);

    @Query("select c.nombre, count(p.id) as cantidad_pedidos " +
            "from Pedido p join p.cliente c " +
            "where p.fechaPedido between :fechaInicio and :fechaFin and p.sucursal = :sucursal " +
            "group by c.nombre")
    List<Object[]> getCantidadPedidosPorCliente(@Param("fechaInicio") LocalDate fechaInicio, @Param("fechaFin") LocalDate fechaFin, @Param("sucursal") Long sucursal);

    @Query("select sum(p.total - p.totalCosto) as ganancia " +
            "from Pedido p " +
            "where p.fechaPedido between :fechaInicio and :fechaFin and p.sucursal = :sucursal")
    Optional<Double> getGanancia(@Param("fechaInicio") LocalDate fechaInicio, @Param("fechaFin") LocalDate fechaFin, @Param("sucursal") Long sucursal);

    @Query("select dp.articulo.denominacion as articulo, sum(dp.cantidad) as cantidad " +
            "from Pedido p join p.detallesPedido dp " +
            "where p.fechaPedido between :fechaInicio and :fechaFin and p.sucursal = :sucursal " +
            "group by dp.articulo.denominacion " +
            "order by cantidad desc")
    List<Object[]> getRankingArticulos(@Param("fechaInicio") LocalDate fechaInicio, @Param("fechaFin") LocalDate fechaFin, @Param("sucursal") Long sucursal);

    @Query("select dp.promocion.denominacion as promocion, sum(dp.cantidad) as cantidad " +
            "from Pedido p join p.detallesPedido dp " +
            "where p.fechaPedido between :fechaInicio and :fechaFin and p.sucursal = :sucursal " +
            "group by dp.promocion.denominacion " +
            "order by cantidad desc")
    List<Object[]> getRankingPromocion(@Param("fechaInicio") LocalDate fechaInicio, @Param("fechaFin") LocalDate fechaFin, @Param("sucursal") Long sucursal);

}



