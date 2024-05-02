package com.example.buensaborback;

import com.example.buensaborback.entities.*;
import com.example.buensaborback.entities.enums.*;
import com.example.buensaborback.repositories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class BuenSaborBackApplication {
// Aca tiene que inyectar todos los repositorios
// Es por ello que deben crear el paquete reositorio

// Ejemplo  @Autowired
//	private ClienteRepository clienteRepository;

	private static final Logger logger = LoggerFactory.getLogger(BuenSaborBackApplication.class);

	@Autowired
	DetallePedidoRepository detallePedidoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private FacturaRepository facturaRepository;

	@Autowired
	private PaisRepository paisRepository;

	@Autowired
	private ProvinciaRepository provinciaRepository;

	@Autowired
	private LocalidadRepository localidadRepository;

	@Autowired
	private EmpresaRepository empresaRepository;

	@Autowired
	private SucursalRepository	sucursalRepository;

	@Autowired
	private DomicilioRepository domicilioRepository;

	@Autowired
	private UnidadMedidaRepository unidadMedidaRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ArticuloInsumoRepository articuloInsumoRepository;

	@Autowired
	private ArticuloManufacturadoRepository articuloManufacturadoRepository;

	@Autowired
	private ArticuloManufacturadoDetalleRepository articuloManufacturadoDetalleRepository;

	@Autowired
	private ImagenRepository imagenRepository;

	@Autowired
	private PromocionRepository promocionRepository;

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	public static void main(String[] args) {
		SpringApplication.run(BuenSaborBackApplication.class, args);
		logger.info("Estoy activo en el main");
	}



	@Bean
	CommandLineRunner init() {
		return args -> {

			Categoria categoriaPadre = null;
			Set<Articulo> articulos = new HashSet<>();
			Set<Categoria> categorias = new HashSet<>();

			Imagen i2 = Imagen.builder().
					articulo(null).
					cliente(null).
					promocion(null).
					url("img").
					build();
			imagenRepository.save(i2);

			Set<Imagen> imagenes = new HashSet<>();

			Set<ArticuloManufacturadoDetalle> articulosManufacturadosDetalles = new HashSet<>();
			Categoria categoria = Categoria.builder().
					subCategorias(categorias).
					categoriaPadre(categoriaPadre).
					denominacion("lacteos").
					articulos(articulos).
					build();
			categorias.add(categoria);

			categoriaRepository.save(categoria);

			ArticuloInsumo articuloInsumo = ArticuloInsumo.builder()
					.precioCompra(300.00)
					.esParaElaborar(true)
					.stockMaximo(10)
					.stockActual(7)
					.precioVenta(400.00)
					.denominacion("queso")
					.categoria(categoria)
					.build();

			articuloInsumoRepository.save(articuloInsumo);

			ArticuloManufacturadoDetalle articuloManufacturadoDetalle = ArticuloManufacturadoDetalle.builder()
					.cantidad(3)
					.articuloInsumo(articuloInsumo)
					.build();

			articuloManufacturadoDetalleRepository.save(articuloManufacturadoDetalle);

			articulosManufacturadosDetalles.add(articuloManufacturadoDetalle);

			ArticuloManufacturado articuloManufacturado = ArticuloManufacturado.builder().
					descripcion("manso articulo").
					articuloManufacturadoDetalles(articulosManufacturadosDetalles).
					denominacion("pizza").
					imagenes(new HashSet<>()).
					categoria(categoria).
					precioVenta(600.00).
					preparacion("fuego lento").
					tiempoEstimadoMinutos(3).
					build();

			articuloManufacturadoRepository.save(articuloManufacturado);
			Usuario usuario = Usuario.builder().
					auth0Id("sdadsadf").
					username("RSrsr").
					build();
			usuarioRepository.save(usuario);
			Cliente cliente = Cliente.builder().
					email("pepito@example.com").
					apellido("rodrigues").
					pedidos(null).
					domicilios(null).
					nombre("Pablo").
					telefono("2324324").
					usuario(usuario).
					build();
			clienteRepository.save(cliente);
			LocalDate date = LocalDate.now();
			LocalTime localTime = LocalTime.now();
			Pais pais = Pais.builder().
					nombre("Pais").
					build();
			paisRepository.save(pais);
			Provincia provincia = Provincia.builder().
					nombre("provincia").
					pais(pais).
					build();
			provinciaRepository.save(provincia);
			Localidad localidad = Localidad.builder().
					nombre("localidad").
					provincia(provincia).
					build();
			localidadRepository.save(localidad);
			Domicilio domicilio = Domicilio.builder().
					cp(5501).
					calle("calle ejemplo").
					nroDpto(2324).
					localidad(localidad).
					build();
			domicilioRepository.save(domicilio);
			Empresa empresa = Empresa.builder().
					nombre("empresa").
					cuil(232145215).
					razonSocial("razon social").
					sucursales(null).
					build();
			empresaRepository.save(empresa);
			Sucursal sucursal = Sucursal.builder().
					nombre("sucursal").
					domicilio(domicilio).
					categorias(categorias).
					empresa(empresa).
					horarioApertura(localTime).
					promociones(new HashSet<>()).
					build();
			sucursalRepository.save(sucursal);

			Pedido pedido = Pedido.builder().
					fechaPedido(date).
					detallePedidos(null).
					formaPago(FormaPago.MercadoPago).
					cliente(cliente).
					estado(Estado.Pendiente).
					horaEstimadaFinalizacion(localTime).
					domicilio(domicilio).
					total(232424.32).
					horaEstimadaFinalizacion(localTime).
					sucursal(sucursal).
					build();
			pedidoRepository.save(pedido);

			Factura factura = Factura.builder().
					formaPago(FormaPago.MercadoPago).
					pedido(pedido).
					mpPaymentId(2332).
					mpMerchantOrderId(3232).
					mpPaymentType("sdad").
					totalVenta(323.23).
					fechaFacturacion(date).
					mpPreferenceId("sdsa").
					build();
			facturaRepository.save(factura);
			Promocion promocion = Promocion.builder().
					tipoPromocion(TipoPromocion.promocion).
					denominacion("promocion").
					articulos(articulos).
					precioPromocional(2323.32).
					descripcionDescuento("descripcion").
					fechaDesde(date).
					fechaHasta(date).
					horaDesde(localTime).
					horaHasta(localTime).
					precioPromocional(232.5).
					imagenes(imagenes).
					build();
			promocionRepository.save(promocion);
			UnidadMedida unidadMedida = UnidadMedida.builder().
					denominacion("unidad").
					build();
			unidadMedidaRepository.save(unidadMedida);
			DetallePedido detallePedido = DetallePedido.builder().
					pedido(pedido).
					articulo(articuloInsumo).
					cantidad(3).
					subTotal(20.4).
					build();
			detallePedidoRepository.save(detallePedido);

			Imagen i1 = Imagen.builder().
					articulo(articuloInsumo).
					cliente(cliente).
					promocion(promocion).
					url("img").
					build();
			articuloInsumo.setUnidadMedida(unidadMedida);
			articuloInsumoRepository.save(articuloInsumo);
			imagenRepository.save(i1);
			sucursal.getPromociones().add(promocion);
			sucursalRepository.save(sucursal);
			promocion.getArticulos().add(articuloInsumo);
			promocionRepository.save(promocion);
			logger.info("----------------ESTOY----FUNCIONANDO---------------------");

		};


	}

}



