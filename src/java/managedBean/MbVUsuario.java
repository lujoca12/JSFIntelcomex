/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import Clases.ClsEmpleado;
import Clases.ClsGenerarUserClaves;

import Clases.Document;
import Dao.DaoTDetallePermiso;
import Dao.DaoTEmpresa;
import Dao.DaoTMenu;
import Dao.DaoTTipoUsuario;
import Dao.DaoTUsuario;
import Dao.LocalizacionDao;
import Pojo.TbCanton;

import Pojo.TbDetallePermiso;
import Pojo.TbEmpresa;
import Pojo.TbPais;
import Pojo.TbParroquia;
import Pojo.TbPermiso;
import Pojo.TbProvincia;

import Pojo.TbTipousuario;
import Pojo.TbUsuarios;
import encriptacion.Class_Encript;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import org.primefaces.model.TreeNode;
import org.primefaces.model.DefaultTreeNode;

import java.util.Properties;
import javax.mail.Session;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author server
 */
@Named(value = "mbVUsuario")
@ViewScoped
public class MbVUsuario implements Serializable {

    /**
     * Creates a new instance of MbVUsuario
     */
    private TbUsuarios tUsuario;
    private TbEmpresa tEmpresa;
    private List<SelectItem> lstUsuario;
    private List<SelectItem> lstTodosUsuarios;
    private String usuario;
    private String telefono;
    private String celular;
    private String clave;
    private int idRol;
    String msg = "";
    boolean band = false;
    private List<TbPermiso> lstMenus;
    private List<TbDetallePermiso> lstMenusDetalle;

    private ClsEmpleado theme;
    private List<ClsEmpleado> lstTheme;
    
    private ClsEmpleado themeUsuarios;
    private List<ClsEmpleado> lstThemeUsuarios;

    // TreeNode instance
    private TreeNode root;
    private TreeNode[] checkboxSelectedTreeNodes;
    private Document selectedDocument;
    private TreeNode selectedNode;
    
    private int estado;
    private boolean estadoCorreo;
    private boolean cedOpasap;
    private boolean mostrarEliminados;
    
    
    private List<SelectItem> lstPais;
    private List<SelectItem> lstCanton;
    private List<SelectItem> lstProvincia;
    private List<SelectItem> lstParroquia;
    private List<SelectItem> lstEmpresa;
    
    private String idPaisOrigen;
    private String idProvinciaNac = "";
    private String idCantonNac = "";
    private String idParroquiaNac = "";
    private String idEcuador;
    
    

    public MbVUsuario() {
        tUsuario = new TbUsuarios();
        tEmpresa = new TbEmpresa();
        llenarCboDocentes();
        llenarCboUsuarios();
        cargarPaises();
        cargarEmpresas();
    }
    private void cargarPaises(){
        lstPais = new ArrayList<>();
        LocalizacionDao locDao = new LocalizacionDao();
        List<TbPais> paises = locDao.getPaises();
        for (TbPais p : paises) {
            SelectItem item = new SelectItem(p.getId(), p.getNombre());
            lstPais.add(item);
            if (p.getNombre().equalsIgnoreCase("ecuador")) {
                idEcuador = String.valueOf(p.getId());
            }
        }
    }
    
    private void cargarEmpresas(){
        try {
            lstEmpresa = new ArrayList<>();
            DaoTEmpresa daoEmpresa = new DaoTEmpresa();
            List<TbEmpresa> empresa = daoEmpresa.getEmpresa();
            for (TbEmpresa e : empresa) {
                SelectItem item = new SelectItem(e.getIdEmpresa(), e.getRazonSocial());
                lstEmpresa.add(item);
            }
        } catch (Exception ex) {
            Logger.getLogger(MbVUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   
   public void onPaisChange() {
        lstProvincia = new ArrayList<>();
        lstCanton = new ArrayList<>();
        lstParroquia = new ArrayList<>();
        LocalizacionDao locDao = new LocalizacionDao();
        List<TbProvincia> provincia = locDao.getProvincias(idPaisOrigen);
        for (TbProvincia p : provincia) {
            SelectItem item = new SelectItem(p.getId(), p.getNombre());
            lstProvincia.add(item);
        }
    }
    
    public void onProvinciaChange() {
        try {
            lstCanton = new ArrayList<>();
            lstParroquia = new ArrayList<>();
            LocalizacionDao locDao = new LocalizacionDao();
            List<TbCanton> canton = locDao.getCantonProvicia(idProvinciaNac);
            for (TbCanton c : canton) {
                SelectItem item = new SelectItem(c.getIdCanton(), c.getNombre());
                lstCanton.add(item);
            }
        } catch (Exception ex) {
            Logger.getLogger(MbVEmpresa.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void onCantonChange(){
        try {
            lstParroquia = new ArrayList<>();
            LocalizacionDao locDao = new LocalizacionDao();
            List<TbParroquia> parroquia = locDao.getParroquiaCanton(idCantonNac);
            for (TbParroquia p : parroquia) {
                SelectItem item = new SelectItem(p.getId(), p.getNombre());
                lstParroquia.add(item);
            }
        } catch (Exception ex) {
            Logger.getLogger(MbVEmpresa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<SelectItem> getLstEmpresa() {
        return lstEmpresa;
    }

    public void setLstEmpresa(List<SelectItem> lstEmpresa) {
        this.lstEmpresa = lstEmpresa;
    }
    
    public List<SelectItem> getLstPais() {
        return lstPais;
    }

    public void setLstPais(List<SelectItem> lstPais) {
        this.lstPais = lstPais;
    }

    public List<SelectItem> getLstCanton() {
        return lstCanton;
    }

    public void setLstCanton(List<SelectItem> lstCanton) {
        this.lstCanton = lstCanton;
    }

    public List<SelectItem> getLstProvincia() {
        return lstProvincia;
    }

    public void setLstProvincia(List<SelectItem> lstProvincia) {
        this.lstProvincia = lstProvincia;
    }

    public List<SelectItem> getLstParroquia() {
        return lstParroquia;
    }

    public void setLstParroquia(List<SelectItem> lstParroquia) {
        this.lstParroquia = lstParroquia;
    }

    public String getIdPaisOrigen() {
        return idPaisOrigen;
    }

    public void setIdPaisOrigen(String idPaisOrigen) {
        this.idPaisOrigen = idPaisOrigen;
    }

    public String getIdProvinciaNac() {
        return idProvinciaNac;
    }

    public void setIdProvinciaNac(String idProvinciaNac) {
        this.idProvinciaNac = idProvinciaNac;
    }

    public String getIdCantonNac() {
        return idCantonNac;
    }

    public void setIdCantonNac(String idCantonNac) {
        this.idCantonNac = idCantonNac;
    }

    public String getIdParroquiaNac() {
        return idParroquiaNac;
    }

    public void setIdParroquiaNac(String idParroquiaNac) {
        this.idParroquiaNac = idParroquiaNac;
    }

    public String getIdEcuador() {
        return idEcuador;
    }

    public void setIdEcuador(String idEcuador) {
        this.idEcuador = idEcuador;
    }
    
    public TbEmpresa gettEmpresa() {
        return tEmpresa;
    }

    public void settEmpresa(TbEmpresa tEmpresa) {
        this.tEmpresa = tEmpresa;
    }
    
    public String getUsuario() {
        return usuario;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public TreeNode[] getCheckboxSelectedTreeNodes() {
        return checkboxSelectedTreeNodes;
    }

    public void setCheckboxSelectedTreeNodes(TreeNode[] checkboxSelectedTreeNodes) {
        this.checkboxSelectedTreeNodes = checkboxSelectedTreeNodes;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    public TbUsuarios gettUsuario() {
        return tUsuario;
    }

    public void settUsuario(TbUsuarios tUsuario) {
        this.tUsuario = tUsuario;
    }

    public Document getSelectedDocument() {
        return selectedDocument;
    }

    public void setSelectedDocument(Document selectedDocument) {
        this.selectedDocument = selectedDocument;
    }

    public void onClickChange() {

        asignarPermisosAusuarios();
    }

    public TreeNode getSelectedNode() {
        return selectedNode;
    }

    public void setSelectedNode(TreeNode selectedNode) {
        this.selectedNode = selectedNode;
    }

    public ClsEmpleado getTheme() {
        return theme;
    }

    public void setTheme(ClsEmpleado theme) {
        this.theme = theme;
    }

    public List<ClsEmpleado> getLstTheme() {
        return lstTheme;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public ClsEmpleado getThemeUsuarios() {
        return themeUsuarios;
    }

    public void setThemeUsuarios(ClsEmpleado themeUsuarios) {
        this.themeUsuarios = themeUsuarios;
    }

    public List<ClsEmpleado> getLstThemeUsuarios() {
        return lstThemeUsuarios;
    }

    public boolean isCedOpasap() {
        return cedOpasap;
    }

    public void setCedOpasap(boolean cedOpasap) {
        this.cedOpasap = cedOpasap;
    }

    public boolean isMostrarEliminados() {
        return mostrarEliminados;
    }

    public void setMostrarEliminados(boolean mostrarEliminados) {
        this.mostrarEliminados = mostrarEliminados;
    }
    
    
    public void llenarCboDocentes() {
        estado =0;
        this.lstTheme = new ArrayList<ClsEmpleado>();
        try {
            DaoTUsuario daoTusuario = new DaoTUsuario();

            List<TbUsuarios> lstUsuario = daoTusuario.getDocentes(mostrarEliminados);
            
            
            this.lstTheme.add(new ClsEmpleado("-1", "Ninguno", "Ninguno"));
            for (TbUsuarios user : lstUsuario) {
                this.lstTheme.add(new ClsEmpleado(user.getCedula(), 
                        user.getApellidos() + " " + user.getNombres(), 
                        String.valueOf(user.getTbTipousuario().getId())));
            }
        } catch (Exception ex) {

        }
        
    }
    
    public void llenarCboUsuarios() {
        estado =0;
        this.lstThemeUsuarios = new ArrayList<ClsEmpleado>();
        try {
            DaoTUsuario daoTusuario = new DaoTUsuario();

            List<TbUsuarios> lstUsuario = daoTusuario.getTodosUsuarios(mostrarEliminados);
            if(this.lstThemeUsuarios.size() > 0)
                this.lstThemeUsuarios.clear();
            
            this.lstThemeUsuarios.add(new ClsEmpleado("-1", "Ninguno", "Ninguno"));
            for (TbUsuarios user : lstUsuario) {
                this.lstThemeUsuarios.add(new ClsEmpleado(user.getCedula(), 
                        user.getApellidos() + " " + user.getNombres()+" ("+user.getTbTipousuario().getNombre()+")", 
                        String.valueOf(user.getTbTipousuario().getId())));
            }
        } catch (Exception ex) {

        }
    }

    public List<SelectItem> getLstTodosUsuarios() {
        this.lstTodosUsuarios = new ArrayList<SelectItem>();
        try {
            DaoTTipoUsuario daoTtipoUsuario = new DaoTTipoUsuario();

            List<TbTipousuario> lstTtipoUsuario = daoTtipoUsuario.getTodosTipoUsuarios();
            if(lstTodosUsuarios.size() > 0)
                lstTodosUsuarios.clear();
            for (TbTipousuario tipoUser : lstTtipoUsuario) {
                SelectItem usuarioItem = new SelectItem(tipoUser.getId(), tipoUser.getNombre());
                this.lstTodosUsuarios.add(usuarioItem);
            }
        } catch (Exception ex) {

        }
        return lstTodosUsuarios;
    }

    //Metodo para cargar Usuarios
    public List<SelectItem> getLstUsuario() throws Exception {
        this.lstUsuario = new ArrayList<SelectItem>();
        DaoTUsuario daoTUsuario = new DaoTUsuario();

        List<TbUsuarios> lstUser = daoTUsuario.getTodosUsuarios(mostrarEliminados);
        if(lstUsuario.size() > 0)
            lstUsuario.clear();
        
        for (TbUsuarios usuario : lstUser) {
            SelectItem usuarioItem = new SelectItem(usuario.getCedula(), usuario.getApellidos() + " " + usuario.getNombres());
            this.lstUsuario.add(usuarioItem);
        }

        return lstUsuario;
    }

    //Metodo para crear arbol de permisos
    public void asignarPermisosAusuarios() {
        TreeNode node0 = null;

        TreeNode node00 = null;
        
        
        try {
            if(this.theme == null){
                this.estado = 0; 
                root = new DefaultTreeNode(new Document("Files", 0, "Folder"), null);
            } else {
                this.estado = 1;
                DaoTMenu daoTmenu = new DaoTMenu();
                lstMenus = daoTmenu.getTodosPermisos(false);
                if (lstMenus != null) {
                    root = new DefaultTreeNode(new Document("Files", 0, "Folder"), null);
                    for (TbPermiso p : lstMenus) {
                        if (p.getPadre() == 0) {
                            node0 = new DefaultTreeNode(new Document(p.getDescripcion(), p.getId(), "Folder"), root);

                            lstMenusDetalle = daoTmenu.getEstadoPermisoUsuario(this.theme.getId(), p.getId());
                            if (!lstMenusDetalle.isEmpty()) {
                                if (lstMenusDetalle.get(0).getEstado().equals('1')) {
                                    node0.setSelected(true);
                                } else {
                                    node0.setSelected(false);
                                }
                            }

                            for (TbPermiso p1 : lstMenus) {
                                if (p.getId() == p1.getPadre()) {
                                    node00 = new DefaultTreeNode(new Document(p1.getDescripcion(), p1.getId(), "Folder"), node0);
                                    node00.setRowKey(String.valueOf(p1.getId()));

                                    daoTmenu = new DaoTMenu();
                                    lstMenusDetalle = daoTmenu.getEstadoPermisoUsuario(this.theme.getId(), p1.getId());
                                    if (!lstMenusDetalle.isEmpty()) {
                                        if (lstMenusDetalle.get(0).getEstado().equals('1')) {
                                            node00.setSelected(true);
                                        } else {
                                            node00.setSelected(false);
                                        }
                                    }
                                }
                            }
                        }
                    }

                } else {
                    root = new DefaultTreeNode(new Document("Files", 0, "Folder"), null);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(MbVUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //Metodo para recorrer los nodos seleccionados   

    public void displaySelectedNodes(TreeNode[] nodes) {
        if (nodes != null && nodes.length > 0) {
            try {

                DaoTDetallePermiso daoTDetPerm = new DaoTDetallePermiso();
                msg = daoTDetPerm.registrar(root.getChildren(), this.theme.getId());
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje de la Aplicacion", msg);
                FacesContext.getCurrentInstance().addMessage(null, message);

            } catch (Exception ex) {
                Logger.getLogger(MbVUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void vaciarCajas() {
        tUsuario = new TbUsuarios();
        tEmpresa = new TbEmpresa();
        clave = "";
        telefono = "";
        celular = "";
        cedOpasap = false;
        idParroquiaNac = "";
        idPaisOrigen = "";
        idProvinciaNac = "";
        idCantonNac = "";
    }

    public void registrarCliente() {
        DaoTUsuario daoTusuario = new DaoTUsuario();

        try {
            DaoTTipoUsuario daoTipoUsuario = new DaoTTipoUsuario();
            TbTipousuario tipoUsuario = new TbTipousuario();
            tipoUsuario = (TbTipousuario) daoTipoUsuario.getTipoUsuarios("cliente");

            //tUsuario.setClave(Class_Encript.getStringMessageDigest(this.clave, Class_Encript.SHA256));
            tUsuario.setTelefono(tUsuario.getTelefono().replaceAll("[()-]", ""));
            tUsuario.setEstado('1');
            
            String usuarioGenerado = ClsGenerarUserClaves.getUsuarioAleatorio(10);
            String claveGenerada = ClsGenerarUserClaves.getPassword(ClsGenerarUserClaves.MINUSCULAS.concat(ClsGenerarUserClaves.MAYUSCULAS).concat(ClsGenerarUserClaves.ESPECIALES),10);
            tUsuario.setPass(Class_Encript.getStringMessageDigest(claveGenerada, Class_Encript.SHA256));
            tUsuario.setLogin(usuarioGenerado);
            
            band = daoTusuario.verificarUsuarioNick(tUsuario.getLogin());
            if (band) {
                if (tipoUsuario != null) {
                    tUsuario.setTbTipousuario(tipoUsuario);
                    //enviarEmail(claveGenerada);
//                    if(estadoCorreo){
                        tUsuario.setTbEmpresa(tEmpresa);
                        TbParroquia tParroquia = new TbParroquia();
                        tParroquia.setId(idParroquiaNac);
                        tUsuario.setTbParroquia(tParroquia);
                        band = daoTusuario.registrar(tUsuario);
                        if(band)
                            vaciarCajas();
//                    }
                }
                 else
                           mensajesError("No existe Tipo Usuario"); 
            } else {
                mensajesError("Usuario ya existe");
            }

        } catch (Exception e) {
            vaciarCajas();
        }
        if (band) {
            mensajesOk("Datos procesados correctamente");
        } else {
            mensajesError("Error al procesar datos");
        }
    }
    
    public void actualizarDatos(){
        DaoTUsuario daoTusuario = new DaoTUsuario();
        try {
            TbTipousuario tipoUser = new TbTipousuario();
            tipoUser.setId(Integer.parseInt(this.theme.getName()));
            tUsuario.setTbTipousuario(tipoUser);
            tUsuario.setTelefono(tUsuario.getTelefono().replaceAll("[()-]", ""));
            tUsuario.setTelefono(tUsuario.getTelefono().replaceAll("[()-]", ""));
            
//            String usuarioGenerado = ClsGenerarUserClaves.getUsuarioAleatorio(10);
//            String claveGenerada = ClsGenerarUserClaves.getPassword(ClsGenerarUserClaves.MINUSCULAS.concat(ClsGenerarUserClaves.MAYUSCULAS).concat(ClsGenerarUserClaves.ESPECIALES),10);
//            tUsuario.setClave(Class_Encript.getStringMessageDigest(claveGenerada, Class_Encript.SHA256));
//            tUsuario.setNick(usuarioGenerado);
            
            
//             enviarEmail(claveGenerada);
//                    if(estadoCorreo){
                        band = daoTusuario.update(tUsuario);
//                    }
            
        } catch (Exception ex) {
            Logger.getLogger(MbVUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (band) {
            this.estado = 0;
            mensajesOk("Datos actualizados correctamente");
            llenarCboDocentes();
            llenarCboUsuarios();
        } else {
            this.estado = 1;
            mensajesError("Error al actualizar datos");
        }
    }
    
    public void recuperar(){
        DaoTUsuario daoTusuario = new DaoTUsuario();
        try {
            TbTipousuario tipoUser = new TbTipousuario();
            tipoUser.setId(Integer.parseInt(this.theme.getName()));
            tUsuario.setTbTipousuario(tipoUser);
            tUsuario.setTelefono(tUsuario.getTelefono().replaceAll("[()-]", ""));
            //tUsuario.setCelular(tUsuario.getCelular().replaceAll("[()-]", ""));
            
            String usuarioGenerado = ClsGenerarUserClaves.getUsuarioAleatorio(10);
            String claveGenerada = ClsGenerarUserClaves.getPassword(ClsGenerarUserClaves.MINUSCULAS.concat(ClsGenerarUserClaves.MAYUSCULAS).concat(ClsGenerarUserClaves.ESPECIALES),10);
            tUsuario.setPass(Class_Encript.getStringMessageDigest(claveGenerada, Class_Encript.SHA256));
            tUsuario.setLogin(usuarioGenerado);
            tUsuario.setEstado('1');
            
             //enviarEmail(claveGenerada);
                    if(estadoCorreo){
                        band = daoTusuario.update(tUsuario);
                    }
            
        } catch (Exception ex) {
            Logger.getLogger(MbVUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (band) {
            this.estado = 0;
            mensajesOk("Usuario recuperado correctamente");
            llenarCboDocentes();
            llenarCboUsuarios();
        } else {
            this.estado = 1;
            mensajesError("Error al recuperar usuario");
        }
    }
    
    public void eliminarDatos(){
        DaoTUsuario daoTusuario = new DaoTUsuario();
        try {
            TbTipousuario tipoUser = new TbTipousuario();
            tipoUser.setId(Integer.parseInt(this.theme.getName()));
            tUsuario.setTbTipousuario(tipoUser);
            tUsuario.setEstado('0');
            tUsuario.setTelefono(tUsuario.getTelefono().replaceAll("[()-]", ""));
            //tUsuario.setCelular(tUsuario.getCelular().replaceAll("[()-]", ""));
            band = daoTusuario.update(tUsuario);
        } catch (Exception ex) {
            Logger.getLogger(MbVUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (band) {
            this.estado = 0;
            mensajesOk("Datos eliminados correctamente");
            llenarCboDocentes();
            llenarCboUsuarios();
        } else {
            this.estado = 1;
            mensajesError("Error al eliminar datos");
        }
    }
        
    public void registrarUsuarios() {
        DaoTUsuario daoTusuario = new DaoTUsuario();

        try {
            DaoTTipoUsuario daoTipoUsuario = new DaoTTipoUsuario();
            TbTipousuario tipoUsuario = new TbTipousuario();

            //tUsuario.setClave(Class_Encript.getStringMessageDigest(this.clave, Class_Encript.SHA256));
            tUsuario.setTelefono(telefono.replaceAll("[()-]", ""));
            //tUsuario.sette(celular.replaceAll("[()-]", ""));
            tUsuario.setEstado('1');
            
            String usuarioGenerado = ClsGenerarUserClaves.getUsuarioAleatorio(10);
            String claveGenerada = ClsGenerarUserClaves.getPassword(ClsGenerarUserClaves.MINUSCULAS.concat(ClsGenerarUserClaves.MAYUSCULAS).concat(ClsGenerarUserClaves.ESPECIALES),10);
            tUsuario.setPass(Class_Encript.getStringMessageDigest(claveGenerada, Class_Encript.SHA256));
            tUsuario.setLogin(usuarioGenerado);
            
            band = daoTusuario.verificarUsuarioNick(tUsuario.getLogin());
            if (band) {
                tipoUsuario.setId(idRol);
                tUsuario.setTbTipousuario(tipoUsuario);
//                if(validarCedula()){
                    //enviarEmail(claveGenerada);
                    if(estadoCorreo){
                        band = daoTusuario.registrar(tUsuario);
                        vaciarCajas();
                    }
//                }else{
//                    return;
//                }
                
            } else {
                mensajesError("Usuario ya existe");
            }

            } catch (Exception e) {
            vaciarCajas();
        }
        if (band) {
            mensajesOk("Datos procesados correctamente");
        } else {
            mensajesError("Error al procesar datos");
        }
    }
    
//    public void enviarEmail(String claveGen) throws Exception {
//        postgradoDao psgDao = new postgradoDao();
//        Postgrado psg = psgDao.getPostgrado();
//        if(psg.getEmail() != null || psg.getEmail().isEmpty()){
//        final String username = psg.getEmail();
//        final String password = psg.getClaveEmail();
//
//        Properties props = new Properties();
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.smtp.host", "smtp.gmail.com");
//        props.put("mail.smtp.port", "587");
//        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
//
//        Session session = Session.getInstance(props,
//                new javax.mail.Authenticator() {
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(username, password);
//            }
//        });
//
//        try {
//            
//            
//            String msj = "Saludos Sr(a). "+tUsuario.getNombres()+" "+tUsuario.getApellidos()+" \n Bienvenido a la Unidad de Postgrado \n usuario: "+tUsuario.getNick()+" \n clave:"+claveGen+"";
//                    
//
//            Message message = new MimeMessage(session);
//            message.setFrom(new InternetAddress(username));
//            message.setRecipients(Message.RecipientType.TO,
//                    InternetAddress.parse(tUsuario.getEmail()));
//            message.setSubject("POSTGRADO UTEQ");
//            //message.setText("La entrevista tendrá lugar en " + lugar + " el " + dateFormat.format(fecha));
//            message.setText(msj);
//            Transport.send(message);
//            estadoCorreo = true;
//            
//        } catch (AddressException e) {
//            System.out.println(e.toString());
//            FacesMessage m = new FacesMessage("Error", "La dirección de correo no existe");
//            FacesContext.getCurrentInstance().addMessage(null, m);
//            estadoCorreo = false;
//            // ...
//        } catch (MessagingException e) {
//            // ...
//            System.out.println(e.toString());
//            FacesMessage m = new FacesMessage("Error", "No se ha podido conectar con el servidor, inténtelo de nuevo");
//            FacesContext.getCurrentInstance().addMessage(null, m);
//            estadoCorreo = false;
//        } catch (Exception ex) {
//            
//            System.out.println(ex.toString());
//            FacesMessage m = new FacesMessage("Error", "No se ha podido guardar los datos");
//            FacesContext.getCurrentInstance().addMessage(null, m);
//            estadoCorreo = false;
//        }
//        }else{
//            FacesMessage m = new FacesMessage("Error", "No se tiene un correo registrado en el sistema");
//            FacesContext.getCurrentInstance().addMessage(null, m);
//            estadoCorreo = false;
//        }
//
//    }
    

    
    public void cargarDatosDocentes(){
        DaoTUsuario daoTusuario = new DaoTUsuario();
        try {
            if(this.theme == null)
                this.estado = 0;
            else{
                this.estado = 1;
                this.tUsuario = daoTusuario.getUsuarioEdicion(this.theme.getId());
            }
            
            
        } catch (Exception ex) {
            Logger.getLogger(MbVUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void mensajesOk(String msg) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje de la Aplicacion", msg);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    private void mensajesError(String msg) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje de la Aplicacion", msg);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

}
