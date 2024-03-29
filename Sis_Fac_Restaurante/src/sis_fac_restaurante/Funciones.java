package sis_fac_restaurante;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

//Se crea la clase Funciones que va a poseer todas las funciones del programa
public class Funciones {
    
    //Se crea cantidad de Productos que va a guardar los productos(registros) que van a ser desplegados en las tablas
    public int cantidadProductos;
    
    //Variable menu que va a poseer todos los elementos del menu del restaurante
    Menus menu = new Menus();
    
    //La variable principal, va a conectar con el form principal que posee la matriz con los pedidos que se van a realizar
    Sis_Fac_Restaurante principal = new Sis_Fac_Restaurante();
    
    //Variable que va a sumar el precio subtotal sumando todas las ordenes
    public int sumaSubtotal;
    public int Servicio;
    public int Impuestos;
    public int montoFinal;
    
    //Funcion que se va a encargar de mostrar los diferentes menus del restaurante
    public void Mostrar_Tabla(String categoria, javax.swing.JTable tblProductos)
    {
        //Se inicializa cantidad productos en 0 que va a contar los productos que se van a desplegar en el formulario
        cantidadProductos = 0;
        
        //Utilizando DefaultTableModel se genera la variable modeloTabla que va a controlar los tamaños de las tablas
        DefaultTableModel modeloTabla = (DefaultTableModel) tblProductos.getModel();
        
        //Se declara la variable que va a controlar el tamaño de las columnas
        TableColumnModel columnSize = tblProductos.getColumnModel();
        
        //Variable que se encarga del centrado de las columnas
        DefaultTableCellRenderer centradoColumna = new DefaultTableCellRenderer();
                
        //Se define la cantidad de filas en 100 para inicializar la tabla
        modeloTabla.setRowCount(100);
        
        //Se define la cantidad de columnas
        modeloTabla.setColumnCount(3);
        
        //Se le da tamaño a las columnas
        columnSize.getColumn(0).setMaxWidth(30);
        columnSize.getColumn(0).setPreferredWidth(30);
        columnSize.getColumn(2).setMaxWidth(110);
        columnSize.getColumn(2).setPreferredWidth(80);
        
        //Se Centran los valores de las columnas en la tabla
        centradoColumna.setHorizontalAlignment(0);
        tblProductos.getColumnModel().getColumn(0).setCellRenderer(centradoColumna);
        tblProductos.getColumnModel().getColumn(2).setCellRenderer(centradoColumna);
        
        //Ciclo para rellenar la tabla de el formulario con los valores dentro del menu
        for (int j=0; j<(menu.menuEntero.length); j++)
        {
            for (int i=0; i<3; i++)
            {
                //Si la categoria del producto en el menu coincide con lo que se requiere, se acomoda en la tabla
                if (categoria.equals(menu.menuEntero[j][0]))
                {
                    //Se agrega 1 por 1 los valores del menu en la tabla, duplicando el menu en la tabla del formulario
                    tblProductos.setValueAt(menu.menuEntero[j][i+1], cantidadProductos, i);
                }
            }
            
            //Condicion para contar la cantidad de productos en la tabla
            if (categoria.equals(menu.menuEntero[j][0]))
            {
                //Se suma 1 valor a la cantidad de productos para determinar el tamaño de la tabla al final del ciclo
                cantidadProductos += 1;
            }
        }
        
        //Se ajusta el tamaño de la tabla dependiendo del numero de productos en el menu
        modeloTabla.setRowCount(cantidadProductos);
    }
    
    //Funcion que se va a encontrar en la clase Mesas para mostrar el resumen del pedido actual de la mesa
    public void Mostrar_Pedidos(javax.swing.JTable tblPedidos, int numeroMesa)
    {
        //Se inicializa cantidad productos en 0 que va a contar los productos que se van a desplegar en el formulario
        cantidadProductos = 0;
        
        //Utilizando DefaultTableModel se genera la variable modeloTabla que va a controlar los tamaños de las tablas
        DefaultTableModel modeloTabla = (DefaultTableModel) tblPedidos.getModel();
        
        //Se declara la variable que va a controlar el tamaño de las columnas
        TableColumnModel columnSize = tblPedidos.getColumnModel();
        
        //Variable que se encarga el centrado de las columnas
        DefaultTableCellRenderer centradoColumna = new DefaultTableCellRenderer();
        
        //Se define la cantidad de filas en 100 para inicializar la tabla
        modeloTabla.setRowCount(100);
        
        //Se define la cantidad de columnas
        modeloTabla.setColumnCount(2);
        
        //Se le da tamaño a las columnas
        columnSize.getColumn(0).setMaxWidth(40);
        columnSize.getColumn(0).setPreferredWidth(40);
        
        //Se Centran los valores de las columnas en la tabla
        centradoColumna.setHorizontalAlignment(0);
        tblPedidos.getColumnModel().getColumn(0).setCellRenderer(centradoColumna);
        
        //Ciclo para rellenar la tabla de el formulario con los valores dentro del menu
        for (int j=0; j<(principal.pedidosMesas.length); j++)
        {
            //Si la categoria del producto en el menu coincide con lo que se requiere, se acomoda en la tabla
            if (numeroMesa == principal.pedidosMesas[j].NumeroMesa)
            {
                //Se agrega 1 por 1 los valores del menu en la tabla, duplicando el menu en la tabla del formulario
                tblPedidos.setValueAt(principal.pedidosMesas[j].CantidadProducto, cantidadProductos, 0);
                tblPedidos.setValueAt(principal.pedidosMesas[j].NombreProducto, cantidadProductos, 1);
                
                //Se suma 1 valor a la cantidad de productos para determinar el tamaño de la tabla al final del ciclo
                cantidadProductos += 1;
            }
        }
        
        //Se ajusta el tamaño de la tabla dependiendo del numero de productos en el menu
        modeloTabla.setRowCount(cantidadProductos);
    }
    
    
    //Funcion que se va a encargar de mostrar la cuenta(factura) de la mesa que se seleccione al precionar el btnCobrarMesa
    public void Mostrar_Cuenta(javax.swing.JTable tblPedidosCobrar, int numeroMesa, javax.swing.JTextField txtsubtotal, javax.swing.JTextField txtservicio, javax.swing.JTextField txtimpuestos, javax.swing.JTextField txtFinal)
    {
        //Se inicializa cantidad productos en 0 que va a contar los productos que se van a desplegar en el formulario
        cantidadProductos = 0;
        
        //Utilizando DefaultTableModel se genera la variable modeloTabla que va a controlar los tamaños de las tablas
        DefaultTableModel modeloTabla = (DefaultTableModel) tblPedidosCobrar.getModel();
        
        //Se declara la variable que va a controlar el tamaño de las columnas
        TableColumnModel columnSize = tblPedidosCobrar.getColumnModel();
        
        //Variable que se encarga el centrado de las columnas
        DefaultTableCellRenderer centradoColumna = new DefaultTableCellRenderer();
        
        //Se define la cantidad de filas en 100 para inicializar la tabla
        modeloTabla.setRowCount(100);
        
        //Se define la cantidad de columnas
        modeloTabla.setColumnCount(5);
        
        //Se le da tamaño a las columnas
        columnSize.getColumn(0).setMaxWidth(40);
        columnSize.getColumn(0).setPreferredWidth(40);
        columnSize.getColumn(2).setMaxWidth(40);
        columnSize.getColumn(2).setPreferredWidth(40);
        columnSize.getColumn(3).setMaxWidth(60);
        columnSize.getColumn(3).setPreferredWidth(60);
        columnSize.getColumn(4).setMaxWidth(60);
        columnSize.getColumn(4).setPreferredWidth(60);
        
        //Se Centran los valores de las columnas en la tabla
        centradoColumna.setHorizontalAlignment(0);
        tblPedidosCobrar.getColumnModel().getColumn(0).setCellRenderer(centradoColumna);
        tblPedidosCobrar.getColumnModel().getColumn(2).setCellRenderer(centradoColumna);
        tblPedidosCobrar.getColumnModel().getColumn(3).setCellRenderer(centradoColumna);
        tblPedidosCobrar.getColumnModel().getColumn(4).setCellRenderer(centradoColumna);
        
        //Ciclo para rellenar la tabla de el formulario con los valores dentro del menu
        for (int j=0; j<(principal.pedidosMesas.length); j++)
        {
            //Si la categoria del producto en el menu coincide con lo que se requiere, se acomoda en la tabla
            if (numeroMesa == principal.pedidosMesas[j].NumeroMesa)
            {
                //Se agrega 1 por 1 los valores del menu en la tabla, duplicando el menu en la tabla del formulario
                tblPedidosCobrar.setValueAt(principal.pedidosMesas[j].NumeroMesa, cantidadProductos, 0);
                tblPedidosCobrar.setValueAt(principal.pedidosMesas[j].NombreProducto, cantidadProductos, 1);
                tblPedidosCobrar.setValueAt(principal.pedidosMesas[j].CantidadProducto, cantidadProductos, 2);
                tblPedidosCobrar.setValueAt(principal.pedidosMesas[j].PrecioUnitario, cantidadProductos, 3);
                tblPedidosCobrar.setValueAt(principal.pedidosMesas[j].PrecioFinal, cantidadProductos, 4);
                
                //Se suma 1 valor a la cantidad de productos para determinar el tamaño de la tabla al final del ciclo
                cantidadProductos += 1;
            }
        }
        
        //Se ajusta el tamaño de la tabla dependiendo del numero de productos en el menu
        modeloTabla.setRowCount(cantidadProductos);
        
        //------------------------A continuacion se procede a rellenar los text fields del formulario--------------------------//
        
        //Se declara las variables que se van a utilizar para dar la factura final, en 0
        sumaSubtotal = 0;
        Servicio = 0;
        Impuestos = 0;
        montoFinal = 0;
        
        //Ciclo para acumular el subtotal de la cuenta en la variable subtotal
        for (int i=0; i<(principal.pedidosMesas.length); i++)
        {
            if (numeroMesa == principal.pedidosMesas[i].NumeroMesa)
            {
                sumaSubtotal = sumaSubtotal + principal.pedidosMesas[i].PrecioFinal;
            }
        }
        
        //Se muestra el subtotal en el textbox de Subtotal
        txtsubtotal.setText(String.valueOf(sumaSubtotal));
        
        //Se muestra el subtotal en el textbox de Servicio(10%)
        Servicio = (sumaSubtotal/100)*10;
        txtservicio.setText(String.valueOf(Servicio));
        
        //Se muestra el subtotal en el textbox de Impuestos(13%)
        Impuestos = (sumaSubtotal/100)*13;
        txtimpuestos.setText(String.valueOf(Impuestos));
        
        //Se muestra el subtotal en el textbox del monto final
        montoFinal = sumaSubtotal + Servicio + Impuestos;
        txtFinal.setText(String.valueOf(montoFinal));
    }
    
    //Funcion que se va a encontrar en la clase RegistroUsuarios para mostrar los usuarios existentes
    public void MostrarUsuarios(javax.swing.JTable tblUsuarios)
    {
        //Variable que va a controlar la cantidad de usuarios que se van a imprimir en la tabla
        cantidadProductos = 0;
        
        //Utilizando DefaultTableModel se genera la variable modeloTabla que va a controlar los tamaños de las tablas
        DefaultTableModel modeloTabla = (DefaultTableModel) tblUsuarios.getModel();
        
        //Variable que se encarga el centrado de las columnas
        DefaultTableCellRenderer centradoColumna = new DefaultTableCellRenderer();
        
        //Se define la cantidad de filas en 10 para inicializar la tabla
        modeloTabla.setRowCount(10);
        
        //Se define la cantidad de columnas
        modeloTabla.setColumnCount(1);
        
        //Se Centran los valores de las columnas en la tabla
        centradoColumna.setHorizontalAlignment(0);
        tblUsuarios.getColumnModel().getColumn(0).setCellRenderer(centradoColumna);
        
        //Ciclo para rellenar la tabla de el formulario con los valores dentro de la matriz de usuarios
        for (int j=0; j<10; j++)
        {
            //Mientras el valor de usuario en la matriz no este vacio
            if (!" ".equals(principal.usuarios[j][0]))
            {
               //Se agrega 1 por 1 los valores del menu en la tabla, imprimiendo todos los usuarios en sistema
                tblUsuarios.setValueAt(principal.usuarios[j][0], cantidadProductos, 0);
                cantidadProductos += 1;
            }
        }
        modeloTabla.setRowCount(cantidadProductos);
    }
}
