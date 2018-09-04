/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tads.tccpool.dao;

import br.com.tads.tccpool.beans.Anuncio;
import br.com.tads.tccpool.beans.Imovel;
import br.com.tads.tccpool.beans.Material;
import br.com.tads.tccpool.beans.Movel;
import br.com.tads.tccpool.beans.User;
import br.com.tads.tccpool.utils.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author onurb
 */
public class AnuncioDAO {

    private static final String QUERY_INSERT_IMOVEL = "INSERT INTO tb_imovel (NR_QNT_PESSOAS,NR_PET, TB_CATEGORIA_IMOVEL_ID_CATEGORIA_IMOVEL) VALUES (?,?,?);";
    private static final String QUERY_UPDATE_IMOVEL = "UPDATE tb_imovel SET NR_QNT_PESSOAS = ?,  NR_PET = ?, TB_CATEGORIA_IMOVEL_ID_CATEGORIA_IMOVEL = ? WHERE ID_IMOVEL = ?;";
    private static final String QUERY_DELETE_ANUNCIO = "delete from tb_anuncio where ID_ANUNCIO = ?";
    private static final String QUERY_DELETE_FOTOS_ANUNCIO = "delete from tb_fotos where TB_ANUNCIO_ID_ANUNCIO = ?";
    private static final String QUERY_DELETE_ENDERECO_ANUNCIO = "delete from tb_endereco_anuncio where ID_ENDERECO = ?";
    private static final String QUERY_INSERT_END = "INSERT INTO tb_endereco_anuncio (DS_RUA, DS_ESTADO,NR_NUMERO,NR_CEP,DS_COMPLEMENTO,DS_CIDADE)"
            + " VALUES (?,?,?,?,?,?)";
    private static final String QUERY_UPDATE_END = "UPDATE tb_endereco_anuncio SET DS_RUA = ?, DS_ESTADO = ?,NR_NUMERO = ?,NR_CEP = ?,DS_COMPLEMENTO = ?,DS_CIDADE = ? where ID_ENDERECO = ?";
    private static final String QUERY_UPDATE_END_SEM_COMP = "UPDATE tb_endereco_anuncio SET DS_RUA = ?, DS_ESTADO = ?,NR_NUMERO = ?,NR_CEP = ?,DS_CIDADE = ? where ID_ENDERECO = ?";
    private static final String QUERY_INSERT_IMOVEL_ANUNCIO = "INSERT INTO tb_anuncio "
            + "(DS_DESCRICAO,NR_VALOR,TB_CATEGORIA_ID_CATEGORIA,TB_STATUS_ID_STATUS,TB_ENDERECO_ID_ENDERECO,TB_IMOVEL_idTB_IMOVEL,DS_TITULO, TB_USUARIO_NR_SEQ) "
            + "VALUES "
            + "(?,?,?,?,?,(select max(id_imovel) from tb_imovel),?, ?)";
    private static final String QUERY_UPDATE_IMOVEL_ANUNCIO = "UPDATE tb_anuncio SET DS_DESCRICAO = ?,NR_VALOR = ?,TB_STATUS_ID_STATUS = ?,DS_TITULO = ? WHERE ID_ANUNCIO = ?";
    private static final String QUERY_UPDATE_MOVEL_ANUNCIO = "UPDATE tb_anuncio SET DS_DESCRICAO = ?,NR_VALOR = ?,TB_STATUS_ID_STATUS = ?,DS_TITULO = ? WHERE ID_ANUNCIO = ?";
    private static final String QUERY_UPDATE_MATERIAL_ANUNCIO = "UPDATE tb_anuncio SET DS_DESCRICAO = ?,NR_VALOR = ?,TB_STATUS_ID_STATUS = ?,DS_TITULO = ? WHERE ID_ANUNCIO = ?";
    private static final String QUERY_UPDATE_MATERIAL = "UPDATE tb_material SET TB_CATEGORIA_MATERIAL_ID_CATEGORIA_MATERIAL = ? WHERE ID_MATERIAL = ?";
    private final String QUERY_CONSULTA_PENDENTES_IMOVEL = "SELECT\n"
            + "             tb_imovel.ID_IMOVEL,\n"
            + "             tb_imovel.NR_PET,\n"
            + "             tb_imovel.NR_QNT_PESSOAS,\n"
            + "             tb_categoria_imovel.DS_DESCRICAO AS DESC_TIPO,\n"
            + "             TB_ANUNCIO.ID_ANUNCIO,\n"
            + "             TB_ANUNCIO.DS_DESCRICAO,\n"
            + "             TB_ANUNCIO.NR_VALOR,\n"
            + "			 TB_ANUNCIO.TB_CATEGORIA_ID_CATEGORIA,\n"
            + "             TB_ANUNCIO.TB_STATUS_ID_STATUS,\n"
            + "             TB_ANUNCIO.TB_ENDERECO_ID_ENDERECO,\n"
            + "             TB_ANUNCIO.DS_TITULO,\n"
            + "             tb_anuncio.TB_IMOVEL_idTB_IMOVEL,\n"
            + "             tb_endereco_anuncio.ID_ENDERECO,\n"
            + "             tb_endereco_anuncio.DS_RUA,\n"
            + "             tb_endereco_anuncio.DS_ESTADO,\n"
            + "             tb_endereco_anuncio.NR_NUMERO,\n"
            + "             tb_endereco_anuncio.NR_CEP,\n"
            + "             tb_endereco_anuncio.DS_COMPLEMENTO,\n"
            + "             tb_endereco_anuncio.DS_CIDADE\n"
            + "             FROM\n"
            + "             tb_anuncio\n"
            + "             INNER JOIN\n"
            + "             tb_endereco_anuncio ON ID_ENDERECO = TB_ENDERECO_ID_ENDERECO\n"
            + "             INNER JOIN\n"
            + "             tb_imovel ON ID_IMOVEL = TB_IMOVEL_idTB_IMOVEL\n"
            + "             INNER JOIN\n"
            + "             tb_categoria_imovel ON TB_CATEGORIA_IMOVEL_ID_CATEGORIA_IMOVEL = ID_CATEGORIA_IMOVEL\n"
            + "             WHERE\n"
            + "             TB_ANUNCIO.TB_STATUS_ID_STATUS = ?\n"
            + "             AND TB_ANUNCIO.TB_CATEGORIA_ID_CATEGORIA = ?";

    private final String QUERY_CONSULTA_MOVEL_ID = "select DS_DESCRICAO, DS_TITULO, NR_VALOR, TB_STATUS_ID_STATUS from tb_anuncio WHERE ID_ANUNCIO = ?";
    private final String QUERY_CONSULTA_MATERIAL_ID = "select a.DS_DESCRICAO, a.DS_TITULO, a.NR_VALOR, a.TB_STATUS_ID_STATUS, a.TB_MATERIAL_ID_MATERIAL, M.TB_CATEGORIA_MATERIAL_ID_CATEGORIA_MATERIAL, cm.DS_DESCRICAO as TIPO_DESC  from tb_anuncio A left JOIN tb_material M ON A.TB_MATERIAL_ID_MATERIAL = M.ID_MATERIAL left join tb_categoria_material cm on m.TB_CATEGORIA_MATERIAL_ID_CATEGORIA_MATERIAL = cm.ID_CATEGORIA_MATERIAL WHERE ID_ANUNCIO = ?";
    private final String QUERY_CONSULTA_IMOVEL_ID = "SELECT "
            + "tb_imovel.NR_PET, "
            + "tb_imovel.NR_QNT_PESSOAS, "
            + "tb_categoria_imovel.DS_DESCRICAO AS DESC_TIPO, "
            + "TB_ANUNCIO.DS_DESCRICAO, "
            + "TB_ANUNCIO.NR_VALOR, "
            + "TB_ANUNCIO.DS_TITULO, "
            + "TB_ANUNCIO.ID_ANUNCIO, "
            + "TB_ENDERECO_ID_ENDERECO, "
            + "tb_endereco_anuncio.DS_RUA, "
            + "tb_endereco_anuncio.DS_ESTADO, "
            + "tb_endereco_anuncio.NR_NUMERO, "
            + "tb_endereco_anuncio.NR_CEP, "
            + "tb_endereco_anuncio.DS_COMPLEMENTO, "
            + "tb_endereco_anuncio.DS_CIDADE "
            + "FROM "
            + "tb_anuncio "
            + "INNER JOIN "
            + "tb_endereco_anuncio ON ID_ENDERECO = TB_ENDERECO_ID_ENDERECO "
            + "INNER JOIN "
            + "tb_imovel ON ID_IMOVEL = TB_IMOVEL_idTB_IMOVEL "
            + "INNER JOIN "
            + "tb_categoria_imovel ON TB_CATEGORIA_IMOVEL_ID_CATEGORIA_IMOVEL = ID_CATEGORIA_IMOVEL "
            + "WHERE "
            + "tb_anuncio.ID_ANUNCIO = ?";

    private static final String QUERY_INSERT_CAMINHO = "INSERT INTO tb_fotos(ds_caminho,tb_anuncio_id_anuncio)VALUES(?,?)";

    private final String QUERY_INSERT_MOVEL_ANUNCIO = "INSERT INTO tb_anuncio (DS_DESCRICAO,NR_VALOR,DS_TITULO,TB_CATEGORIA_ID_CATEGORIA,TB_STATUS_ID_STATUS, TB_USUARIO_NR_SEQ"
            + ") VALUES(?,?,?,?,?,?)";

    private final String QUERY_INSERT_MATERIAL = "INSERT INTO tb_material (TB_CATEGORIA_MATERIAL_ID_CATEGORIA_MATERIAL) VALUES (?)";
    private final String QUERY_INSERT_MATERIAL_ANUNCIO = "INSERT INTO tb_anuncio (DS_DESCRICAO,NR_VALOR,TB_CATEGORIA_ID_CATEGORIA,TB_STATUS_ID_STATUS, TB_USUARIO_NR_SEQ, DS_TITULO,TB_MATERIAL_ID_MATERIAL"
            + ") VALUES(?,?,?,?,?,?,?)";

    private final String QUERY_CONSULTA_ID_FOTO = "select max(tb_anuncio_id_anuncio) as id from tb_fotos;";

    private final String QUERY_BUSCAR_FOTOS_POR_ID = "SELECT * FROM TB_FOTOS WHERE TB_ANUNCIO_ID_ANUNCIO = ?";

    private final String UPDATE_STATUS = "UPDATE TB_ANUNCIO SET TB_STATUS_ID_STATUS = ? WHERE ID_ANUNCIO = ?";

    private final String QUERY_BUSCAR_ANUNCIOS_APROVADOS = "SELECT\n"
            + "	ID_ANUNCIO,\n"
            + "	DS_TITULO,\n"
            + "    ANUNCIO.DS_DESCRICAO,\n"
            + "    NR_VALOR,\n"
            + "    CAT_ANUNCIO.DS_DESCRICAO AS DS_CATEGORIA,\n"
            + "    FOTO.DS_CAMINHO AS DS_CAMINHO\n"
            + "FROM\n"
            + "	TB_ANUNCIO AS ANUNCIO\n"
            + "    INNER JOIN\n"
            + "		TB_CATEGORIA_ANUNCIO AS CAT_ANUNCIO ON CAT_ANUNCIO.ID_CATEGORIA = ANUNCIO.TB_CATEGORIA_ID_CATEGORIA\n"
            + "    INNER JOIN\n"
            + "		TB_FOTOS AS FOTO ON FOTO.TB_ANUNCIO_ID_ANUNCIO = ANUNCIO.ID_ANUNCIO\n"
            + "WHERE\n"
            + "	TB_STATUS_ID_STATUS = 2\n"
            + "    GROUP BY ANUNCIO.ID_ANUNCIO";

    //query para buscar anuncios do usuario apos pressiona o botão ''meus nuancios'' na home
    private final String QUERY_BUCASR_ANUNCIOS_DO_USUARIO = "SELECT\n"
            + " tb_anuncio.TB_USUARIO_NR_SEQ,"
            + " tb_anuncio.DS_DESCRICAO,"
            + " tb_anuncio.TB_CATEGORIA_ID_CATEGORIA,"
            + " tb_anuncio.ID_ANUNCIO,"
            + " tb_status.DS_DESCRICAO as ESTADO,"
            + " tb_categoria_anuncio.DS_DESCRICAO as DS_CAT\n"
            + " FROM\n"
            + " tb_anuncio\n"
            + " INNER JOIN tb_categoria_anuncio on ID_CATEGORIA = TB_CATEGORIA_ID_CATEGORIA\n"
            + " INNER JOIN tb_status on ID_STATUS = TB_STATUS_ID_STATUS"
            + " WHERE\n"
            + " TB_USUARIO_NR_SEQ = ?";

    //query para descobrir o tipo de anuncio
    private final String QUERY_TIPO_ANUNCIO = "SELECT TB_CATEGORIA_ID_CATEGORIA from tb_anuncio where ID_ANUNCIO = ?";

    //query para pegar um imovel de um anuncio    
    private final String QUERY_IMOVEL_POR_ANUNCIO = "SELECT tb_imovel.ID_IMOVEL,tb_imovel.NR_PET,tb_imovel.NR_QNT_PESSOAS,tb_categoria_imovel.DS_DESCRICAO AS DESC_TIPO,TB_ANUNCIO.ID_ANUNCIO,TB_ANUNCIO.DS_DESCRICAO,\n"
            + "				TB_ANUNCIO.NR_VALOR,\n"
            + "				TB_ANUNCIO.TB_CATEGORIA_ID_CATEGORIA,\n"
            + "				TB_ANUNCIO.TB_STATUS_ID_STATUS,\n"
            + "				TB_ANUNCIO.TB_ENDERECO_ID_ENDERECO,\n"
            + "				TB_ANUNCIO.DS_TITULO,\n"
            + "				tb_anuncio.TB_IMOVEL_idTB_IMOVEL,\n"
            + "				tb_endereco_anuncio.ID_ENDERECO,\n"
            + "				tb_endereco_anuncio.DS_RUA,\n"
            + "				tb_endereco_anuncio.DS_ESTADO,\n"
            + "				tb_endereco_anuncio.NR_NUMERO,\n"
            + "				tb_endereco_anuncio.NR_CEP,\n"
            + "				tb_endereco_anuncio.DS_COMPLEMENTO,\n"
            + "				tb_endereco_anuncio.DS_CIDADE\n"
            + "		FROM\n"
            + "			tb_anuncio\n"
            + "		INNER JOIN\n"
            + "            tb_endereco_anuncio ON ID_ENDERECO = TB_ENDERECO_ID_ENDERECO\n"
            + "		INNER JOIN\n"
            + "			tb_imovel ON ID_IMOVEL = TB_IMOVEL_idTB_IMOVEL\n"
            + "		INNER JOIN\n"
            + "			tb_categoria_imovel ON TB_CATEGORIA_IMOVEL_ID_CATEGORIA_IMOVEL = ID_CATEGORIA_IMOVEL\n"
            + "		WHERE\n"
            + "			tb_anuncio.ID_ANUNCIO = ?";

    //query para retornar o id de um imovel de um anuncio
    private final String QUERY_RETORNO_ID_IMOVEL_POR_IDANUNCIO = "SELECT tb_anuncio.TB_IMOVEL_idTB_IMOVEL "
            + "from tb_anuncio where tb_anuncio.ID_ANUNCIO = ?";

    private Connection con = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;

    public AnuncioDAO() {
        ConnectionFactory cf = new ConnectionFactory();
        con = cf.getConnection();
    }

    //método para fechar a conexão do bd
    public void close() throws SQLException {
        if (rs != null) {
            try {
                rs.close();
            } catch (Exception e) {

            } finally {
                rs = null;
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (Exception e) {

            } finally {
                stmt = null;
            }
        }
        con.close();
        con = null;
    }

    public int getIdFoto() throws SQLException {
        int id = 0;
        stmt = con.prepareStatement(QUERY_CONSULTA_ID_FOTO);
        rs = stmt.executeQuery();
        while (rs.next()) {
            id = rs.getInt("id");
        }
        return id;
    }

    public void inserirImovel(Imovel u, int cat, List<String> caminho, User us) throws SQLException {
        int id;
        stmt = con.prepareStatement(QUERY_INSERT_IMOVEL);
        stmt.setInt(1, u.getQuantidade_pessoas());
        stmt.setInt(2, u.getBoolean_pet());
        stmt.setInt(3, u.getTipo());
        stmt.executeUpdate();
        stmt.clearParameters();

        stmt = con.prepareStatement(QUERY_INSERT_END);
        stmt.setString(1, u.getRua());
        stmt.setString(2, u.getEstado());
        stmt.setInt(3, u.getNumero());
        stmt.setString(4, u.getCep());
        stmt.setString(5, u.getComplemento());
        stmt.setString(6, u.getCidade());
        stmt.executeUpdate();
        //pegando o id do endereço recem add
        stmt = con.prepareStatement("SELECT last_insert_id() as ID");
        rs = stmt.executeQuery();
        if (rs.next()) {
            stmt = con.prepareStatement(QUERY_INSERT_IMOVEL_ANUNCIO);
            stmt.setString(1, u.getDescricao());
            stmt.setFloat(2, u.getPreco());
            stmt.setInt(3, cat);
            stmt.setInt(4, 1);
            stmt.setInt(5, rs.getInt("ID"));
            stmt.setString(6, u.getTitulo());
            stmt.setInt(7, us.getId());

            stmt.executeUpdate();
        }
        stmt = con.prepareStatement("SELECT last_insert_id() as ID");
        rs = stmt.executeQuery();
        if (rs.next()) {
            stmt = con.prepareStatement(QUERY_INSERT_CAMINHO);
            for (int i = 0; i < caminho.size(); i++) {
                stmt.setString(1, caminho.get(i));
                stmt.setInt(2, rs.getInt("ID"));
                stmt.executeUpdate();
            }
        }
        con.close();
        stmt.close();
        rs.close();

    }/////

    public void inserirMovel(Movel m, int cat, List<String> caminho, User u) throws SQLException {
        stmt = con.prepareStatement(QUERY_INSERT_MOVEL_ANUNCIO);
        stmt.setString(1, m.getDescricao());
        stmt.setFloat(2, m.getPreco());
        stmt.setString(3, m.getDescricao());
        stmt.setInt(4, cat);
        stmt.setInt(5, 1);
        stmt.setInt(6, u.getId());
        stmt.executeUpdate();
        stmt = con.prepareStatement("SELECT last_insert_id() as ID");
        rs = stmt.executeQuery();
        if (rs.next()) {
            int id = rs.getInt("ID");

            stmt = con.prepareStatement(QUERY_INSERT_CAMINHO);
            for (int i = 0; i < caminho.size(); i++) {
                stmt.setString(1, caminho.get(i));
                stmt.setInt(2, id);
                stmt.executeUpdate();
            }
        }

        con.close();
        stmt.close();
        rs.close();

    }

    public void inserirMaterial(Material m, int cat, List<String> caminho, User u) throws SQLException {
        stmt = con.prepareStatement(QUERY_INSERT_MATERIAL);
        stmt.setInt(1, m.getTipo());
         stmt.executeUpdate();
         int id1 = 0;
         stmt = con.prepareStatement("SELECT last_insert_id() as ID");
        rs = stmt.executeQuery();
        if (rs.next()) {
            id1 = rs.getInt("ID");
        }
        
        stmt = con.prepareStatement(QUERY_INSERT_MATERIAL_ANUNCIO);
        stmt.setString(1, m.getDescricao());
        stmt.setFloat(2, m.getPreco());
        stmt.setInt(3, cat);
        stmt.setInt(4, 1);
        stmt.setInt(5, u.getId());
        stmt.setString(6, m.getTitulo());
        stmt.setInt(7, id1);
        stmt.executeUpdate();
        stmt = con.prepareStatement("SELECT last_insert_id() as ID");
        rs = stmt.executeQuery();
        if (rs.next()) {
            int id = rs.getInt("ID");
            stmt = con.prepareStatement(QUERY_INSERT_CAMINHO);
            for (int i = 0; i < caminho.size(); i++) {
                stmt.setString(1, caminho.get(i));
                stmt.setInt(2, id);
                stmt.executeUpdate();
            }
        }
        con.close();
        stmt.close();
        rs.close();
    }

    public List<Imovel> buscarPendente() throws SQLException {
        stmt = con.prepareStatement(QUERY_CONSULTA_PENDENTES_IMOVEL);
        stmt.setInt(1, 1);
        stmt.setInt(2, 1);
        rs = stmt.executeQuery();
        List<Imovel> list = new ArrayList<Imovel>();

        while (rs.next()) {
            //DESCRICAO, TITULO, PRECO, RUA, NUMERO, ESTADO, CIDADE

            Imovel i = new Imovel();
            i.setId(rs.getInt("ID_ANUNCIO"));
            i.setBoolean_pet(rs.getInt("NR_PET"));
            i.setQuantidade_pessoas(rs.getInt("NR_QNT_PESSOAS"));
            i.setTipoDesc(rs.getString("DESC_TIPO"));
            i.setDescricao(rs.getString("DS_DESCRICAO"));
            i.setPreco(rs.getFloat("NR_VALOR"));
            i.setRua(rs.getString("DS_RUA"));
            i.setNumero(rs.getInt("NR_NUMERO"));
            i.setTitulo(rs.getString("DS_TITULO"));
            i.setEstado(rs.getString("DS_ESTADO"));
            i.setCidade(rs.getString("DS_CIDADE"));
            i.setCep(rs.getString("NR_CEP"));
            i.setComplemento(rs.getString("DS_COMPLEMENTO"));

            list.add(i);

        }
        con.close();
        stmt.close();
        rs.close();
        return list;
    }

    public Imovel buscarImovelPorId(int id) throws SQLException {
        stmt = con.prepareStatement(QUERY_CONSULTA_IMOVEL_ID);
        stmt.setInt(1, id);
        rs = stmt.executeQuery();
        Imovel i = new Imovel();
        if (rs.next()) {
            i.setId(rs.getInt("ID_ANUNCIO"));
            i.setBoolean_pet(rs.getInt("NR_PET"));
            i.setQuantidade_pessoas(rs.getInt("NR_QNT_PESSOAS"));
            i.setTipoDesc(rs.getString("DESC_TIPO"));
            i.setDescricao(rs.getString("DS_DESCRICAO"));
            i.setPreco(rs.getFloat("NR_VALOR"));
            i.setEnderecoId(rs.getInt("TB_ENDERECO_ID_ENDERECO"));
            i.setRua(rs.getString("DS_RUA"));
            i.setNumero(rs.getInt("NR_NUMERO"));
            i.setTitulo(rs.getString("DS_TITULO"));
            i.setEstado(rs.getString("DS_ESTADO"));
            i.setCidade(rs.getString("DS_CIDADE"));
            i.setCep(rs.getString("NR_CEP"));
            i.setComplemento(rs.getString("DS_COMPLEMENTO"));
            stmt = con.prepareStatement(QUERY_BUSCAR_FOTOS_POR_ID);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            List<String> f = new ArrayList<>();
            while (rs.next()) {
                f.add(rs.getString("DS_CAMINHO"));
            }
            i.setFotos(f);
        }
        con.close();
        stmt.close();
        rs.close();
        return i;
    }

    public void aprovarAnuncio(String status, int id) throws SQLException {
        if (status.equalsIgnoreCase("sim")) {
            stmt = con.prepareStatement(UPDATE_STATUS);
            stmt.setInt(1, 2);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        } else {
            stmt = con.prepareStatement(UPDATE_STATUS);
            stmt.setInt(1, 4);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        }
        con.close();
        stmt.close();
    }

    public List<Anuncio> buscarAnuncioAprovado() throws SQLException {
        List<Anuncio> lista = new ArrayList<Anuncio>();
        stmt = con.prepareStatement(QUERY_BUSCAR_ANUNCIOS_APROVADOS);
        rs = stmt.executeQuery();
        while (rs.next()) {
            Anuncio anuncio = new Anuncio();
            anuncio.setIdAnuncio(rs.getInt("ID_ANUNCIO"));
            anuncio.setTitulo(rs.getString("DS_TITULO"));
            anuncio.setValor(rs.getFloat("NR_VALOR"));
            anuncio.setDescricao(rs.getString("DS_DESCRICAO"));
            anuncio.setCategoria(rs.getString("DS_CATEGORIA"));
            anuncio.setCaminhoFoto(rs.getString("DS_CAMINHO"));
            lista.add(anuncio);
        }
        con.close();
        stmt.close();
        rs.close();
        return lista;
    }

    public List<Anuncio> buscarAnunciosDoUsuario(int idUsuario) throws SQLException {
        List<Anuncio> lista = new ArrayList<Anuncio>();
        stmt = con.prepareStatement(QUERY_BUCASR_ANUNCIOS_DO_USUARIO);
        stmt.setInt(1, idUsuario);
        rs = stmt.executeQuery();
        while (rs.next()) {
            Anuncio anuncio = new Anuncio();
            anuncio.setIdUsuario(rs.getInt("TB_USUARIO_NR_SEQ"));
            anuncio.setDescricao(rs.getString("DS_DESCRICAO"));
            anuncio.setIdCategoria(rs.getInt("TB_CATEGORIA_ID_CATEGORIA"));
            anuncio.setIdAnuncio(rs.getInt("ID_ANUNCIO"));
            anuncio.setCategoria(rs.getString("DS_CAT"));
            anuncio.setStatusAnuncio(rs.getString("ESTADO"));
            lista.add(anuncio);
        }
        con.close();
        stmt.close();
        rs.close();
        return lista;
    }

    public int verificaTipoAnuncio(int idAnuncio) throws SQLException {
        int retorno = 0;
        Anuncio anuncio = new Anuncio();
        stmt = con.prepareStatement(QUERY_TIPO_ANUNCIO);
        stmt.setInt(1, idAnuncio);
        rs = stmt.executeQuery();
        if (rs.next()) {
            retorno = rs.getInt("TB_CATEGORIA_ID_CATEGORIA");
        }

        /*if(anuncio.getIdMovel()>0){
            retorno = 2;
        }else if(anuncio.getIdMaterial()>0){
            retorno = 3;
        }else if (anuncio.getIdImovel()>0){
            retorno = 1;
        }*/
        con.close();
        stmt.close();
        rs.close();
        return retorno;
    }

    public Imovel exibirImovel(int idAnuncio) throws SQLException {
        Imovel i = new Imovel();
        stmt = con.prepareStatement(QUERY_IMOVEL_POR_ANUNCIO);
        stmt.setInt(1, idAnuncio);
        rs = stmt.executeQuery();
        if (rs.next()) {
            i.setId(rs.getInt("ID_ANUNCIO"));
            i.setBoolean_pet(rs.getInt("NR_PET"));
            i.setQuantidade_pessoas(rs.getInt("NR_QNT_PESSOAS"));
            i.setTipoDesc(rs.getString("DESC_TIPO"));
            i.setDescricao(rs.getString("DS_DESCRICAO"));
            i.setPreco(rs.getFloat("NR_VALOR"));
            i.setRua(rs.getString("DS_RUA"));
            i.setNumero(rs.getInt("NR_NUMERO"));
            i.setTitulo(rs.getString("DS_TITULO"));
            i.setEstado(rs.getString("DS_ESTADO"));
            i.setCidade(rs.getString("DS_CIDADE"));
            i.setCep(rs.getString("NR_CEP"));
            i.setComplemento(rs.getString("DS_COMPLEMENTO"));
        }
        con.close();
        stmt.close();
        rs.close();
        return i;
    }

    public int retornoIdImovelPorIdAnuncio(int idAnuncio) throws SQLException {
        int id = 0;
        stmt = con.prepareStatement(QUERY_RETORNO_ID_IMOVEL_POR_IDANUNCIO);
        stmt.setInt(1, idAnuncio);
        rs = stmt.executeQuery();
        if (rs.next()) {
            id = rs.getInt("TB_IMOVEL_idTB_IMOVEL");
        }
        con.close();
        stmt.close();
        rs.close();
        return id;
    }

    public void deletarAnuncio(int idAnuncio) throws SQLException {
        stmt = con.prepareStatement(QUERY_DELETE_FOTOS_ANUNCIO);
        stmt.setInt(1, idAnuncio);
        stmt.executeUpdate();

        stmt = con.prepareStatement(QUERY_DELETE_ANUNCIO);
        stmt.setInt(1, idAnuncio);
        stmt.executeUpdate();

        con.close();
        stmt.close();

    }

    public void deletarAnuncioImovel(int idAnuncio, Imovel i) throws SQLException {
        stmt = con.prepareStatement(QUERY_DELETE_FOTOS_ANUNCIO);
        stmt.setInt(1, idAnuncio);
        stmt.executeUpdate();

        stmt = con.prepareStatement(QUERY_DELETE_ANUNCIO);
        stmt.setInt(1, idAnuncio);
        stmt.executeUpdate();

        stmt = con.prepareStatement(QUERY_DELETE_ENDERECO_ANUNCIO);
        stmt.setInt(1, i.getEnderecoId());
        stmt.executeUpdate();

        con.close();
        stmt.close();

    }

    public void updateImovel(Imovel u, int idAnuncio) throws SQLException {
        int idEnd = 0;
        try {
            stmt = con.prepareStatement(QUERY_UPDATE_IMOVEL);
            stmt.setInt(1, u.getQuantidade_pessoas());
            stmt.setInt(2, u.getBoolean_pet());
            stmt.setInt(3, u.getTipo());
            stmt.setInt(4, u.getId());
            stmt.executeUpdate();
            stmt.clearParameters();

            stmt = con.prepareStatement("SELECT TB_ENDERECO_ID_ENDERECO FROM tb_anuncio where ID_ANUNCIO = ?");
            stmt.setInt(1, idAnuncio);
            rs = stmt.executeQuery();
            if (rs.next()) {
                idEnd = rs.getInt("TB_ENDERECO_ID_ENDERECO");
            }

            stmt = con.prepareStatement(QUERY_UPDATE_END);
            stmt.setString(1, u.getRua());
            stmt.setString(2, u.getEstado());
            stmt.setInt(3, u.getNumero());
            stmt.setString(4, u.getCep());
            stmt.setString(5, u.getComplemento());
            stmt.setString(6, u.getCidade());
            stmt.setInt(7, idEnd);
            stmt.executeUpdate();
            stmt.clearParameters();

            stmt = con.prepareStatement(QUERY_UPDATE_IMOVEL_ANUNCIO);
            stmt.setString(1, u.getDescricao());
            stmt.setFloat(2, u.getPreco());
            stmt.setInt(3, 1);
            stmt.setString(4, u.getTitulo());
            stmt.setInt(5, idAnuncio);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new SQLException(e);
        } finally {
            con.close();
            stmt.close();
            rs.close();
        }

    }

    public Movel buscarMovelPorId(int id) throws SQLException {
        stmt = con.prepareStatement(QUERY_CONSULTA_MOVEL_ID);
        stmt.setInt(1, id);
        rs = stmt.executeQuery();
        Movel m = new Movel();
        if (rs.next()) {
            //m.setId(rs.getInt("ID_ANUNCIO"));
            if (rs.getString("DS_TITULO") != null) {
                m.setTitulo(rs.getString("DS_TITULO"));
            }
            if (rs.getString("DS_DESCRICAO") != null) {
                m.setDescricao(rs.getString("DS_DESCRICAO"));
            }
            m.setPreco(rs.getInt("NR_VALOR"));
            stmt = con.prepareStatement(QUERY_BUSCAR_FOTOS_POR_ID);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            List<String> f = new ArrayList<>();
            while (rs.next()) {
                f.add(rs.getString("DS_CAMINHO"));
            }
            m.setFotos(f);
        }
        con.close();
        stmt.close();
        rs.close();
        return m;
    }

    public Material buscarMaterialPorId(int id) throws SQLException {
        stmt = con.prepareStatement(QUERY_CONSULTA_MATERIAL_ID);
        stmt.setInt(1, id);
        rs = stmt.executeQuery();
        Material m = new Material();
        if (rs.next()) {
            //m.setId(rs.getInt("ID_ANUNCIO"));
            if (rs.getString("DS_TITULO") != null) {
                m.setTitulo(rs.getString("DS_TITULO"));
            }
            if (rs.getString("DS_DESCRICAO") != null) {
                m.setDescricao(rs.getString("DS_DESCRICAO"));
            }
            m.setPreco(rs.getInt("NR_VALOR"));
            m.setStatus(rs.getInt("TB_STATUS_ID_STATUS"));
            m.setId(rs.getInt("TB_MATERIAL_ID_MATERIAL"));
            m.setTipo(rs.getInt("TB_CATEGORIA_MATERIAL_ID_CATEGORIA_MATERIAL"));

            if(rs.getString("TIPO_DESC") != null){
            m.setTipoDesc(rs.getString("TIPO_DESC"));
            }
            
            stmt = con.prepareStatement(QUERY_BUSCAR_FOTOS_POR_ID);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            List<String> f = new ArrayList<>();
            while (rs.next()) {
                f.add(rs.getString("DS_CAMINHO"));
            }
            m.setFotos(f);
        }
        con.close();
        stmt.close();
        rs.close();
        return m;
    }

    public void updateMovel(Movel m, int idAnuncio) throws SQLException {

        try {
            stmt = con.prepareStatement(QUERY_UPDATE_MOVEL_ANUNCIO);
            stmt.setString(1, m.getDescricao());
            stmt.setFloat(2, m.getPreco());
            stmt.setInt(3, 1);
            stmt.setString(4, m.getTitulo());
            stmt.setInt(5, idAnuncio);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new SQLException(e);
        } finally {
            con.close();
            stmt.close();

        }

    }
  
    public void updateMaterial(Material m, int idAnuncio) throws SQLException {
        
        try{
            stmt = con.prepareStatement(QUERY_UPDATE_MATERIAL_ANUNCIO);
            stmt.setString(1, m.getDescricao());
            stmt.setFloat(2, m.getPreco());
            stmt.setInt(3, 1);
            stmt.setString(4, m.getTitulo());    
            stmt.setInt(5, idAnuncio);
            stmt.executeUpdate();
            
            stmt = con.prepareStatement(QUERY_UPDATE_MATERIAL);
            stmt.setInt(1,m.getTipo());
            stmt.setInt(2,m.getId());
            stmt.executeUpdate();
        
        }catch(SQLException e){
           throw new SQLException(e);
        }finally{
        con.close();
        stmt.close();
        }
    }
}
