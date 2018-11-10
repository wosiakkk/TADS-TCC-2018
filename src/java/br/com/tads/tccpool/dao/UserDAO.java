/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tads.tccpool.dao;

import br.com.tads.tccpool.beans.Privacidade;
import br.com.tads.tccpool.beans.User;
import br.com.tads.tccpool.utils.ConnectionFactory;
import br.com.tads.tccpool.utils.MD5;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author onurb
 */
public class UserDAO {

    private static final String QUERY_CONSULTA_CAMINHO_USUARIO = "SELECT caminho, nome_usuario FROM tcc1.tb_caminho_usuario;";
    private static final String QUERY_INSERT_PRIVACIDADE_USER = "INSERT INTO `tcc1`.`tb_privacidade`(`TB_USUARIO_NR_SEQ`,`PRIVACIDADE_TELEFONE`,`PRIVACIDADE_ENDERECO`,`PRIVACIDADE_DESCRICAO`,`PRIVACIDADE_INTERESSES`)VALUES(?,?,?,?,?);";
    private static final String QUERY_UPDATE_PRIVACIDADE_USER = "UPDATE `tcc1`.`tb_privacidade` SET `PRIVACIDADE_TELEFONE` = ?,`PRIVACIDADE_ENDERECO` = ?,`PRIVACIDADE_DESCRICAO` = ?,`PRIVACIDADE_INTERESSES` = ? WHERE `ID_PRIVACIDADE` = ?;";
    private static final String QUERY_SELECT_PRIVACIDADE_USER = "SELECT * FROM tcc1.tb_privacidade WHERE TB_USUARIO_NR_SEQ = ?;";

    private static final String QUERY_INSERT_ENDERECO_USER = "INSERT INTO `tcc1`.`tb_endereco`\n"
            + "(`NM_RUA`,\n"
            + "`NM_ESTADO`,\n"
            + "`NR_RUA`,\n"
            + "`NR_CEP`,\n"
            + "`DS_COMPLEMENTO`,\n"
            + "`NM_CIDADE`)\n"
            + "VALUES\n"
            + "(?,?,?,?,?,?)";

    private static final String QUERY_CONSULTA_ID_ENDERECO_USER = "SELECT CD_ENDERECO FROM tcc1.tb_usuario WHERE NR_SEQ = ?";
    private static final String QUERY_LOGIN = "SELECT NR_SEQ, DS_EMAIL, NM_NOME, TP_USUARIO, DS_FOTO, DS_SENHA FROM TB_USUARIO WHERE DS_EMAIL = ? AND DS_SENHA = ?";
    private static final String QUERY_LOGIN_GOOGLE = "SELECT NR_SEQ, NM_NOME, DS_EMAIL, DS_FOTO,TP_USUARIO FROM TB_USUARIO WHERE DS_EMAIL = ?";
    private static final String QUERY_SIMPLE_INSERT_MODADM = "INSERT INTO TB_USUARIO"
            + " (NM_NOME,DS_EMAIL,DS_SENHA,TP_USUARIO,DS_FOTO)"
            + " VALUES (?,?,?,?,?)";
    private static final String QUERY_SIMPLE_INSERT_USR = "INSERT INTO TB_USUARIO"
            + " (NM_NOME,DS_EMAIL,DS_SENHA,TP_USUARIO)"
            + " VALUES (?,?,?,?)";
    private static final String QUERY_SENHA_USR = "SELECT tb_usuario.DS_SENHA FROM tb_usuario WHERE NR_SEQ = ?";
    private static final String QUERY_UPDATE_SENHA = "UPDATE tb_usuario SET tb_usuario.DS_SENHA = ? WHERE tb_usuario.NR_SEQ = ?";
    private static final String QUERY_SIMPLE_INSERT_GOOGLE = "INSERT INTO TB_USUARIO"
            + " (NM_NOME,DS_EMAIL,DS_FOTO,TP_USUARIO)"
            + " VALUES (?,?,?,?)";
    private static final String QUERY_INSERT_END = "INSERT INTO TB_ENDERECO (NM_RUA,NM_ESTADO,NR_RUA,NR_CEP,DS_COMPLEMENTO,NM_CIDADE)"
            + " values(?,?,?,?,?,?)";
    private static final String QUERY_SELECT_USR = "SELECT\n"
            + "user.NR_SEQ,\n"
            + "user.NM_NOME,\n"
            + "user.DS_EMAIL,\n"
            + "user.NR_TELEFONE, \n"
            + "user.NR_CELULAR,\n"
            + "user.DS_SENHA, \n"
            + "user.CD_ENDERECO, \n"
            + "user.DS_FOTO, \n"
            + "user.DS_DESCRICAO_USER, \n"
            + "user.DS_INTERESSES, \n"
            + "endr.NM_RUA,\n"
            + "endr.NM_ESTADO,\n"
            + "endr.NR_RUA,\n"
            + "endr.NR_CEP,\n"
            + "endr.DS_COMPLEMENTO,\n"
            + "endr.NM_CIDADE\n"
            + "FROM tcc1.tb_usuario AS user \n"
            + "LEFT JOIN tcc1.tb_endereco AS endr ON user.CD_ENDERECO = endr.NR_SEQ \n"
            + "WHERE \n"
            + "user.NR_SEQ = ? AND user.TP_USUARIO = 2";

    private static final String QUERY_EDIT_PERFIL = "UPDATE tb_usuario SET\n"
            + " NM_NOME = ?,"
            + " DS_DESCRICAO_USER = ?,"
            + " DS_INTERESSES= ?"
            + " WHERE"
            + " NR_SEQ = ?";

    private static final String QUERY_GERAR_PERFIL = " SELECT tb_usuario.NM_NOME, "
            + "tb_usuario.DS_FOTO, tb_usuario.DS_DESCRICAO_USER,\n"
            + " tb_usuario.DS_INTERESSES FROM tcc1.tb_usuario "
            + "WHERE tb_usuario.NR_SEQ = ?";

    private static final String QUERY_EDIT_USR = "UPDATE tb_usuario set NM_NOME = ?, "
            + "DS_FOTO = ?, NR_TELEFONE = ?, NR_CELULAR = ?, DS_DESCRICAO_USER = ?, "
            + "DS_INTERESSES = ?, CD_ENDERECO = ? WHERE NR_SEQ = ?";

    private static final String QUERY_EDIT_END = "UPDATE tb_endereco SET\n"
            + "NM_RUA = ?,"
            + "NM_ESTADO = ?,"
            + "NR_RUA = ?,"
            + "NR_CEP = ?,"
            + "DS_COMPLEMENTO = ?,"
            + "NM_CIDADE = ?\n"
            + "WHERE NR_SEQ = ?";

    private static final String QUERY_SOLICITAR_AMIZADE = "INSERT into tb_amizade (tb_amizade.id_solicitante, "
            + "tb_amizade.id_solicitado, tb_status_amizade_NR_STATUS_AMIGO, "
            + "tb_amizade.tb_usuario_NR_SEQ) VALUES (?,?,?,?)";

    private static final String QUERY_ACEITAR_AMIZADE = "UPDATE tb_amizade SET "
            + "tb_status_amizade_NR_STATUS_AMIGO = 2 WHERE "
            + "id_solicitado = ? AND id_solicitante = ? "
            + "and tb_status_amizade_NR_STATUS_AMIGO = 1";

    private static final String QUERY_BUSCAR_USUARIOS = "SELECT * FROM tb_usuario WHERE NM_NOME LIKE ? AND NR_SEQ != ?";

    private static final String QUERY_SELECIONAR_IDS_AMIZADE = "SELECT tb_amizade.id_solicitado, tb_amizade.id_solicitante,"
            + " tb_amizade.tb_status_amizade_NR_STATUS_AMIGO, tb_amizade.id_solicitante_bloq "
            + "from tb_amizade where tb_amizade.tb_usuario_NR_SEQ = ?";

    private static final String QUERY_SELECIONAR_IDS_AMIGOS_ACEITOS = "SELECT tb_amizade.tb_usuario_NR_SEQ FROM tb_amizade "
            + "WHERE (tb_amizade.tb_usuario_NR_SEQ NOT IN (?) AND"
            + " tb_amizade.tb_status_amizade_NR_STATUS_AMIGO = 2) AND (tb_amizade.id_solicitado = ? OR tb_amizade.id_solicitante = ?)";

    private static final String QUERY_SELECIONAR_IDS_AMIZADE_PENDENTE = "SELECT DISTINCT tb_amizade.id_solicitante "
            + "FROM tb_amizade WHERE tb_amizade.id_solicitado = ? "
            + "AND tb_amizade.tb_status_amizade_NR_STATUS_AMIGO = 1";

    private static final String QUERY_SELECIONAR_IDS_AMIZADE_BLOQUEADAS = "SELECT DISTINCT tb_amizade.id_solicitante "
            + "FROM tb_amizade WHERE tb_amizade.id_solicitado = ? "
            + "AND tb_amizade.tb_status_amizade_NR_STATUS_AMIGO = 3";
    private static final String QUERY_SELECIONAR_IDS_AMIZADE_BLOQUEADAS_2 = "SELECT DISTINCT tb_amizade.id_solicitado "
            + "FROM tb_amizade WHERE tb_amizade.id_solicitante = ? "
            + "AND tb_amizade.tb_status_amizade_NR_STATUS_AMIGO = 3";

    private static final String QUERY_NOME_AMIGOS_BLOQUEADOS_ID = "SELECT tb_usuario.NM_NOME, tb_usuario.DS_FOTO, tb_usuario.NR_SEQ FROM tb_usuario WHERE tb_usuario.NR_SEQ = ?";

    private static final String QUERY_NOME_AMIGOS_ACEITOS_ID = "SELECT tb_usuario.NM_NOME, "
            + "tb_usuario.DS_FOTO, tb_usuario.NR_SEQ FROM tb_usuario WHERE tb_usuario.NR_SEQ = ?";

    private static final String QUERY_REJEITAR_PEDIDO = "DELETE FROM tb_amizade WHERE tb_amizade.id_solicitado = ? "
            + "AND tb_amizade.id_solicitante = ? AND tb_amizade.tb_status_amizade_NR_STATUS_AMIGO = 1";

    private static final String QUERY_REJEITAR_E_BLOQUEAR = "UPDATE tb_amizade SET \n"
            + "            tb_amizade.tb_status_amizade_NR_STATUS_AMIGO =3, tb_amizade.id_solicitante_bloq = ? WHERE (tb_amizade.id_solicitado = ? \n"
            + "            AND tb_amizade.id_solicitante = ?) OR (tb_amizade.id_solicitado = ? \n"
            + "            AND tb_amizade.id_solicitante = ?)";

    private static final String QUERY_DESBLOQUEAR_USUARIO = "UPDATE tb_amizade "
            + "SET "
            + "tb_amizade.tb_status_amizade_NR_STATUS_AMIGO = 2 "
            + "WHERE "
            + "(tb_amizade.tb_status_amizade_NR_STATUS_AMIGO = 3) "
            + "AND (tb_amizade.id_solicitado = ? "
            + "OR tb_amizade.id_solicitante = ?) "
            + "AND (tb_amizade.id_solicitado = ? "
            + "OR tb_amizade.id_solicitante = ?) ";

    private static final String QUERY_EXCLUIR_AMIZADE = "DELETE FROM tb_amizade "
            + "WHERE "
            + "(tb_amizade.id_solicitado = ? "
            + "AND tb_amizade.id_solicitante = ?) "
            + "OR (tb_amizade.id_solicitado = ? "
            + "AND tb_amizade.id_solicitante = ?)";

    private static final String query_nova_lista_bloqueados = "SELECT distinct tb_amizade.id_solicitado, tb_amizade.id_solicitante "
            + "from tb_amizade where (tb_amizade.id_solicitante_bloq = ? and tb_amizade.tb_status_amizade_NR_STATUS_AMIGO=3)";

    private static final String QUERY_VERIFICAR_AMIZADE_EXISTENTE = "SELECT * FROM tcc1.tb_amizade WHERE (tb_amizade.tb_usuario_NR_SEQ = ? AND tb_amizade.tb_status_amizade_NR_STATUS_AMIGO = 2)\n"
            + "AND (tb_amizade.id_solicitado = ? OR tb_amizade.id_solicitante =?)";

    //******************************
    // implementado apenas para finalizar a sprint da lista de amigos, pois os nomes do user podem ser iguais
    // futuramente a busca será aprimorada
    private static final String QUERY_BUSCAR_ID_POR_NOME = "select tb_usuario.NR_SEQ FROM tb_usuario where tb_usuario.NM_NOME = ?";
    //***************************************************
    //***************************************************

    private Connection con = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;

    public UserDAO() {
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

    public void inserirUser(User u) throws SQLException {
        try {
            stmt = con.prepareStatement(QUERY_SIMPLE_INSERT_USR);
            stmt.setString(1, u.getNome());
            stmt.setString(2, u.getEmail());
            stmt.setString(3, u.getSenha());
            stmt.setInt(4, u.getTipoUsuario());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            stmt.close();
            con.close();
        }
    }

    public void inserirUserGoogle(User u) throws SQLException {
        try {
            stmt = con.prepareStatement(QUERY_SIMPLE_INSERT_GOOGLE);
            stmt.setString(1, u.getNome());
            stmt.setString(2, u.getEmail());
            stmt.setString(3, u.getFoto());
            stmt.setInt(4, u.getTipoUsuario());
            stmt.executeUpdate();
            con.close();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            stmt.close();
            con.close();
        }
    }

    public User verificaEmail(String email) throws SQLException {
        User u = null;
        try {
            stmt = con.prepareStatement(QUERY_LOGIN_GOOGLE);
            stmt.setString(1, email);
            rs = stmt.executeQuery();
            while (rs.next()) {
                u = new User();
                u.setId(rs.getInt("NR_SEQ"));
                u.setEmail(rs.getString("DS_EMAIL"));
                u.setNome(rs.getString("NM_NOME"));
                u.setFoto(rs.getString("DS_FOTO"));
                u.setTipoUsuario(rs.getInt("TP_USUARIO"));
            }
        } catch (SQLException e) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
            u = null;
        } finally {
            stmt.close();
            rs.close();
            con.close();
        }
        return u;
    }

    /**
     *
     * @param login
     * @param senha
     * @return
     * @throws SQLException
     */
    public User verificaLogin(String login, String senha) throws SQLException {
        List<String> lista = new ArrayList<>();
        User u = null;
        try {
            stmt = con.prepareStatement(QUERY_LOGIN);
            stmt.setString(1, login);
            stmt.setString(2, senha);
            rs = stmt.executeQuery();

            while (rs.next()) {
                u = new User();
                u.setId(rs.getInt("NR_SEQ"));
                u.setEmail(rs.getString("DS_EMAIL"));
                u.setNome(rs.getString("NM_NOME"));
                u.setFoto(rs.getString("DS_FOTO"));
                u.setTipoUsuario(rs.getInt("TP_USUARIO"));
                u.setSenha(rs.getString("DS_SENHA"));
            }
            stmt = con.prepareStatement(QUERY_CONSULTA_CAMINHO_USUARIO);
            rs = stmt.executeQuery();
            while(rs.next()){
                String caminho = rs.getString("caminho");
                lista.add(caminho);
            }
            if(u!=null)
                u.setCaminhos(lista);

        } catch (SQLException e) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
            u = null;
        } finally {
            stmt.close();
            rs.close();
            con.close();
        }
        return u;
    }

    public User buscarUser(int idUser) {

        if (idUser == 1 || idUser == 3) {
            User mod = new User();
            mod.setId(idUser);
            mod.setNome("Moderador");
            mod.setFoto("img\\fotosPerfil\\mod.png");
            return mod;
        } else {

            try {
                stmt = con.prepareStatement(QUERY_SELECT_USR);
                stmt.setInt(1, idUser);
                rs = stmt.executeQuery();
                if (rs.next()) {
                    User u = new User();
                    u.setId(idUser);
                    //   u.setCpf(rs.getString("NR_CPF"));
                    if (rs.getString("NM_NOME") != null) {
                        u.setNome(rs.getString("NM_NOME"));
                    }
                    if (rs.getString("DS_EMAIL") != null) {
                        u.setEmail(rs.getString("DS_EMAIL"));
                    }
                    if (rs.getString("DS_SENHA") != null) {
                        u.setSenha(rs.getString("DS_SENHA"));
                    }
                    if (rs.getString("NR_TELEFONE") != null) {
                        u.setTel(rs.getString("NR_TELEFONE"));
                    }
                    if (rs.getString("NR_CELULAR") != null) {
                        u.setCel(rs.getString("NR_CELULAR"));
                    }
                    if (rs.getString("NR_CEP") != null) {
                        u.setCep(rs.getString("NR_CEP"));
                    }
                    u.setNumero(rs.getInt("NR_RUA"));
                    if (rs.getString("NM_RUA") != null) {
                        u.setLogradouro(rs.getString("NM_RUA"));
                    }
                    if (rs.getString("DS_COMPLEMENTO") != null) {
                        u.setComplemento(rs.getString("DS_COMPLEMENTO"));
                    }
                    if (rs.getString("NM_ESTADO") != null) {
                        u.setEstado(rs.getString("NM_ESTADO"));
                    }
                    if (rs.getString("NM_CIDADE") != null) {
                        u.setCidade(rs.getString("NM_CIDADE"));
                    }
                    //   u.setInstituicao(rs.getInt("CD_INST"));
                    u.setCdEndereco(rs.getInt("CD_ENDERECO"));
                    if (rs.getString("DS_FOTO") != null) {
                        u.setFoto(rs.getString("DS_FOTO"));
                    }
                    if (rs.getString("DS_DESCRICAO_USER") != null) {
                        u.setDescricao(rs.getString("DS_DESCRICAO_USER"));
                    }
                    if (rs.getString("DS_INTERESSES") != null) {
                        u.setInteresses(rs.getString("DS_INTERESSES"));
                    }

                    return u;
                } else {
                    throw new SQLException();
                }
            } catch (SQLException e) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
                return null;
            } finally {
                try {
                    con.close();
                    stmt.close();
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void editarPerfil(User u) throws SQLException {
        stmt = con.prepareStatement(QUERY_EDIT_PERFIL);
        stmt.setString(1, u.getNome());
        stmt.setString(2, u.getDescricao());
        stmt.setString(3, u.getInteresses());
        stmt.setInt(4, u.getId());
        stmt.executeUpdate();
        stmt.close();
        rs.close();
        con.close();
    }

    public User gerarPerfil(int idUser) throws SQLException {
        User u = new User();
        stmt = con.prepareStatement(QUERY_GERAR_PERFIL);
        stmt.setInt(1, idUser);
        rs = stmt.executeQuery();
        if (rs.next()) {
            u.setId(idUser);
            u.setNome(rs.getString("NM_NOME"));
            u.setFoto(rs.getString("DS_FOTO"));
            u.setDescricao(rs.getString("DS_DESCRICAO_USER"));
            u.setInteresses(rs.getString("DS_INTERESSES"));
            stmt.close();
            rs.close();
            con.close();
            return u;
        } else {
            stmt.close();
            rs.close();
            con.close();
            return u;
        }
    }

    public void editarUser(User u) {

        try {

            int idEnd = 0;
            stmt = con.prepareStatement(QUERY_CONSULTA_ID_ENDERECO_USER);
            stmt.setInt(1, u.getId());
            rs = stmt.executeQuery();
            if (rs.next()) {
                idEnd = rs.getInt("CD_ENDERECO");
            }
            if (idEnd != 0) {
                stmt = con.prepareStatement(QUERY_EDIT_END);
                stmt.setString(1, u.getLogradouro());
                stmt.setString(2, u.getEstado());
                stmt.setInt(3, u.getNumero());
                stmt.setString(4, u.getCep());
                stmt.setString(5, u.getComplemento());
                stmt.setString(6, u.getCidade());
                stmt.setInt(7, u.getCdEndereco());
                stmt.executeUpdate();
            } else {
                stmt = con.prepareStatement(QUERY_INSERT_ENDERECO_USER);
                stmt.setString(1, u.getLogradouro());
                stmt.setString(2, u.getEstado());
                stmt.setInt(3, u.getNumero());
                stmt.setString(4, u.getCep());
                stmt.setString(5, u.getComplemento());
                stmt.setString(6, u.getCidade());
                stmt.executeUpdate();

                stmt = con.prepareStatement("SELECT LAST_INSERT_ID() AS ID");
                rs = stmt.executeQuery();
                if (rs.next()) {
                    idEnd = rs.getInt("ID");
                }

            }

            stmt = con.prepareStatement(QUERY_EDIT_USR);
            stmt.setString(1, u.getNome());
            stmt.setString(2, u.getFoto());
            stmt.setString(3, u.getTel());
            stmt.setString(4, u.getCel());
            stmt.setString(5, u.getDescricao());
            stmt.setString(6, u.getInteresses());
            if (u.getCdEndereco() != 0) {
                stmt.setInt(7, u.getCdEndereco());
            } else {
                stmt.setInt(7, idEnd);
            }
            stmt.setInt(8, u.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                con.close();
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public Boolean solicitarAmizade(int idSolicitante, int idSolicitado) throws SQLException {
        //alterando para o solicitante:
        try {
            stmt = con.prepareStatement(QUERY_SOLICITAR_AMIZADE);
            stmt.setInt(1, idSolicitante);
            stmt.setInt(2, idSolicitado);
            stmt.setInt(3, 1);
            stmt.setInt(4, idSolicitante);
            stmt.executeUpdate();

            stmt.close();
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            con.close();
            stmt.close();
        }
        return true;
    }

    public Boolean solicitarAmizade2(int idSolicitante, int idSolicitado) throws SQLException {
        //ALTERANDO PARA O SOLICITADO
        try {
            stmt = con.prepareStatement(QUERY_SOLICITAR_AMIZADE);
            stmt.setInt(1, idSolicitante);
            stmt.setInt(2, idSolicitado);
            stmt.setInt(3, 1);
            stmt.setInt(4, idSolicitado);
            stmt.executeUpdate();

            stmt.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            con.close();
            stmt.close();
        }
        return true;
    }

    public ArrayList buscarUsuariosDinamicamente(String s, int userLogado) throws SQLException {
        ArrayList usuariosAchados = new ArrayList();

        try {
            stmt = con.prepareStatement(QUERY_BUSCAR_USUARIOS);
            stmt.setString(1, "%" + s + "%");
            stmt.setInt(2, userLogado);
            rs = stmt.executeQuery();
            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("NR_SEQ"));
                u.setNome(rs.getString("NM_NOME"));
                usuariosAchados.add(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.close();
            stmt.close();
            rs.close();
        }
        return usuariosAchados;
    }

    public int checarAmizade(int idSessao, int idPerfil) throws SQLException {

        try {
            int statusAmizade;
            int idSolicitante;
            int idSolicitado;
            int idSolicitanteBloq;
            stmt = con.prepareStatement(QUERY_SELECIONAR_IDS_AMIZADE);
            stmt.setInt(1, idSessao);
            rs = stmt.executeQuery();
            while (rs.next()) {
                idSolicitante = rs.getInt("id_solicitante");
                idSolicitado = rs.getInt("id_solicitado");
                statusAmizade = rs.getInt("tb_status_amizade_NR_STATUS_AMIGO");
                idSolicitanteBloq = rs.getInt("id_solicitante_bloq");
                if (idPerfil == idSolicitante || idPerfil == idSolicitado) {
                    if (idSessao == idSolicitante && idPerfil == idSolicitado && statusAmizade == 1) {
                        return 1; //solicitação enviada
                    } else if (idSessao == idSolicitado && idPerfil == idSolicitante && statusAmizade == 1) {
                        return 2;//aceitar solicitação
                    } else if (statusAmizade == 2) {
                        return 3;//vocês já são amigos
                    } else if (idSessao == idSolicitante && idPerfil == idSolicitado && statusAmizade == 3 && idSolicitante == idSolicitanteBloq) {
                        return 4;//usuario bloqueado
                    } else if (idSessao == idSolicitante && idPerfil == idSolicitado && statusAmizade == 3 && idSolicitado == idSolicitanteBloq) {
                        return 5;//usuario bloqueado
                    } else if (idSessao == idSolicitado && idPerfil == idSolicitante && statusAmizade == 3 && idPerfil == idSolicitanteBloq) {
                        return 5;//você foi bloqueado por esse usuário
                    } else if (idSessao == idSolicitado && idPerfil == idSolicitante && statusAmizade == 3 && idSolicitado == idSolicitanteBloq) {
                        return 4;//usuario bloqueado
                    } else if (idSessao == idSolicitado && idPerfil == idSolicitante && statusAmizade == 3 && idPerfil == idSolicitanteBloq) {
                        return 5;//você foi bloqueado por esse usuário
                    }
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.close();
            stmt.close();
            rs.close();
        }

        return 0; //nenhuma ação de amizade
    }

    //verificar se já são amigos para evitar duplicidade
    public Boolean verfAmizade(int idUsr, int idSolicitacao) throws SQLException {
        try {
            stmt = con.prepareStatement(QUERY_VERIFICAR_AMIZADE_EXISTENTE);
            stmt.setInt(1, idUsr);
            stmt.setInt(2, idSolicitacao);
            stmt.setInt(3, idSolicitacao);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return false;
    }

    public Boolean aceitarAmizade(int idSolicitante, int idSolicitado) throws SQLException {
        Boolean v = false;
        if ((verfAmizade(idSolicitante, idSolicitado))) {
            return false;
        } else {
            try {
                stmt = con.prepareStatement(QUERY_ACEITAR_AMIZADE);
                stmt.setInt(1, idSolicitado);
                stmt.setInt(2, idSolicitante);
                stmt.executeUpdate();
                v = true;
            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
            con.close();
            stmt.close();
            rs.close();
        }
        }
        return v;
    }

    public Boolean rejeitarPedidoAmizade(int idSessaoSolicitado, int idSolicitante) throws SQLException {
        Boolean verifica = false;
        try {
            stmt = con.prepareStatement(QUERY_REJEITAR_PEDIDO);
            stmt.setInt(1, idSessaoSolicitado);
            stmt.setInt(2, idSolicitante);
            stmt.executeUpdate();
            verifica = true;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.close();
            stmt.close();
        }
        return verifica;
    }

    public Boolean rejeitarBloquear(int idSessao, int idSolicitante) throws SQLException {
        Boolean verifica = false;
        try {
            stmt = con.prepareStatement(QUERY_REJEITAR_E_BLOQUEAR);
            stmt.setInt(1, idSessao);
            stmt.setInt(2, idSolicitante);
            stmt.setInt(3, idSessao);
            stmt.setInt(4, idSessao);
            stmt.setInt(5, idSolicitante);
            stmt.executeUpdate();
            verifica = true;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.close();
            stmt.close();
        }
        return verifica;
    }

    public Boolean desbloquearUsuario(int idSessao, int idDesbloqueio) throws SQLException {
        Boolean verifica = false;
        try {
            stmt = con.prepareStatement(QUERY_DESBLOQUEAR_USUARIO);
            stmt.setInt(1, idSessao);
            stmt.setInt(2, idSessao);
            stmt.setInt(3, idDesbloqueio);
            stmt.setInt(4, idDesbloqueio);
            stmt.executeUpdate();
            verifica = true;

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.close();
            stmt.close();
        }
        return verifica;
    }

    public Boolean excluirAmizade(int idSessao, int idAmigo) throws SQLException {
        Boolean verifica = false;
        try {
            stmt = con.prepareStatement(QUERY_EXCLUIR_AMIZADE);
            stmt.setInt(1, idAmigo);
            stmt.setInt(2, idSessao);
            stmt.setInt(3, idSessao);
            stmt.setInt(4, idAmigo);
            stmt.executeUpdate();
            verifica = true;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.close();
            stmt.close();
        }
        return verifica;
    }

    public List<User> gerarListaAmigosAceitos(int idUserLogado) throws SQLException {
        ArrayList<Integer> listaIds = new ArrayList<>();
        ArrayList<User> listaUsers = new ArrayList<>();
        try {
            stmt = con.prepareStatement(QUERY_SELECIONAR_IDS_AMIGOS_ACEITOS);
            stmt.setInt(1, idUserLogado);
            stmt.setInt(2, idUserLogado);
            stmt.setInt(3, idUserLogado);
            rs = stmt.executeQuery();
            while (rs.next()) {
                listaIds.add(rs.getInt("tb_usuario_NR_SEQ"));
            }
            stmt = con.prepareStatement(QUERY_NOME_AMIGOS_ACEITOS_ID);
            for (Integer id : listaIds) {
                User u = new User();
                stmt.setInt(1, id);

                rs = stmt.executeQuery();
                if (rs.next()) {
                    if (rs.getInt("NR_SEQ") != idUserLogado) {
                        u.setNome(rs.getString("NM_NOME"));
                        u.setFoto(rs.getString("DS_FOTO"));
                        u.setId(id);
                        listaUsers.add(u);
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.close();
            stmt.close();
            rs.close();
        }
        return listaUsers;
    }

    public List<User> gerarListaDePedidosDeAmizade(int idUserLogado) throws SQLException {
        ArrayList<Integer> listaIds = new ArrayList<>();
        ArrayList<User> listaUsers = new ArrayList<>();
        try {
            stmt = con.prepareStatement(QUERY_SELECIONAR_IDS_AMIZADE_PENDENTE);
            stmt.setInt(1, idUserLogado);
            rs = stmt.executeQuery();
            while (rs.next()) {
                listaIds.add(rs.getInt("id_solicitante"));
            }
            stmt = con.prepareStatement(QUERY_NOME_AMIGOS_ACEITOS_ID);
            for (Integer id : listaIds) {
                User u = new User();
                stmt.setInt(1, id);
                rs = stmt.executeQuery();
                if (rs.next()) {
                    u.setNome(rs.getString("NM_NOME"));
                    u.setFoto(rs.getString("DS_FOTO"));
                    u.setId(id);
                    listaUsers.add(u);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.close();
            stmt.close();
            rs.close();
        }
        return listaUsers;
    }

    public List<User> gerarListaBloqueados(int idUserLogado) throws SQLException {
        ArrayList<Integer> listaIds = new ArrayList<>();
        ArrayList<User> listaUsers = new ArrayList<>();
        try {
            stmt = con.prepareStatement(query_nova_lista_bloqueados);
            stmt.setInt(1, idUserLogado);
            rs = stmt.executeQuery();
            while (rs.next()) {
                if (rs.getInt("id_solicitante") != idUserLogado) {
                    listaIds.add(rs.getInt("id_solicitante"));
                } else if (rs.getInt("id_solicitado") != idUserLogado) {
                    listaIds.add(rs.getInt("id_solicitado"));
                }
            }
            stmt = con.prepareStatement(QUERY_NOME_AMIGOS_BLOQUEADOS_ID);
            for (Integer id : listaIds) {
                User u = new User();
                stmt.setInt(1, id);
                rs = stmt.executeQuery();
                if (rs.next()) {
                    u.setNome(rs.getString("NM_NOME"));
                    u.setFoto(rs.getString("DS_FOTO"));
                    u.setId(id);
                    listaUsers.add(u);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.close();
            stmt.close();
            rs.close();
        }
        return listaUsers;
    }

    //******************************
    // implementado apenas para finalizar a sprint da lista de amigos, pois os nomes do user podem ser iguais
    // futuramente a busca será aprimorada
    public int buscarIdPorNome(String nome) throws SQLException {
        int id = -1;
        try {
            stmt = con.prepareStatement(QUERY_BUSCAR_ID_POR_NOME);
            stmt.setString(1, nome);
            rs = stmt.executeQuery();
            if (rs.next()) {
                id = rs.getInt("NR_SEQ");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.close();
            stmt.close();
            rs.close();
        }
        return id;
    }

    public void inserirModOrAdm(User u) throws SQLException {
        try {
            stmt = con.prepareStatement(QUERY_SIMPLE_INSERT_MODADM);
            stmt.setString(1, u.getNome());
            stmt.setString(2, u.getEmail());
            stmt.setString(3, u.getSenha());
            stmt.setInt(4, u.getTipoUsuario());
            stmt.setString(5, u.getFoto());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            stmt.close();
            con.close();
        }
    }

    public Boolean verificaSenha(String senhaPassada, int idSessao) throws SQLException {
        String senhaBuscada;
        String md5 = MD5.criptografar(senhaPassada);
        try {
            stmt = con.prepareStatement(QUERY_SENHA_USR);
            stmt.setInt(1, idSessao);
            rs = stmt.executeQuery();
            if (rs.next()) {

            }
            senhaBuscada = rs.getString("DS_SENHA");
            if (senhaBuscada.equalsIgnoreCase(md5)) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            con.close();
            stmt.close();
            rs.close();
        }
        return false;
    }

    public Boolean alterarSenha(String novaSenha, int idSesao) throws SQLException {
        String md5 = MD5.criptografar(novaSenha);
        try {
            stmt = con.prepareStatement(QUERY_UPDATE_SENHA);
            stmt.setString(1, md5);
            stmt.setInt(2, idSesao);
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.close();
            stmt.close();
        }
        return false;
    }
    //***************************************
    //***************************************

    public boolean editarPrivacidade(Privacidade p) {

        try {
            int ret = 0;
            int idPr = 0;
            stmt = con.prepareStatement(QUERY_SELECT_PRIVACIDADE_USER);
            stmt.setInt(1, p.getIdUser());
            rs = stmt.executeQuery();
            if (rs.next()) {
                idPr = rs.getInt("ID_PRIVACIDADE");
            }
            if (idPr != 0) {
                stmt = con.prepareStatement(QUERY_UPDATE_PRIVACIDADE_USER);
                stmt.setInt(1, p.getPrivacidadeTelefone());
                stmt.setInt(2, p.getPrivacidadeEndereco());
                stmt.setInt(3, p.getPrivacidadeDescricao());
                stmt.setInt(4, p.getPrivacidadeInteresses());
                stmt.setInt(5, idPr);
                ret = stmt.executeUpdate();
                if (ret == 2) {
                    return false;
                }
            } else {
                stmt = con.prepareStatement(QUERY_INSERT_PRIVACIDADE_USER);
                stmt.setInt(1, p.getIdUser());
                stmt.setInt(2, p.getPrivacidadeTelefone());
                stmt.setInt(3, p.getPrivacidadeEndereco());
                stmt.setInt(4, p.getPrivacidadeDescricao());
                stmt.setInt(5, p.getPrivacidadeInteresses());
                ret = stmt.executeUpdate();
                if (ret == 2) {
                    return false;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                con.close();
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return true;
    }

    public Privacidade buscarPrivacidade(int idUser) {
        Privacidade p = new Privacidade();
        try {
            stmt = con.prepareStatement(QUERY_SELECT_PRIVACIDADE_USER);
            stmt.setInt(1, idUser);
            rs = stmt.executeQuery();
            if (rs.next()) {
                p.setId(rs.getInt("ID_PRIVACIDADE"));
                p.setIdUser(idUser);
                p.setPrivacidadeDescricao(rs.getInt("PRIVACIDADE_DESCRICAO"));
                p.setPrivacidadeEndereco(rs.getInt("PRIVACIDADE_ENDERECO"));
                p.setPrivacidadeInteresses(rs.getInt("PRIVACIDADE_INTERESSES"));
                p.setPrivacidadeTelefone(rs.getInt("PRIVACIDADE_TELEFONE"));
            }
        } catch (SQLException e) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                con.close();
                stmt.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return p;
    }
}
