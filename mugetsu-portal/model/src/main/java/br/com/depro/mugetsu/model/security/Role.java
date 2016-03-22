package br.com.depro.mugetsu.model.security;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import br.com.depro.fw.typezero.infrastructure.entidade.EntidadeBase;
import br.com.depro.mugetsu.model.cenum.security.TipoAcessoEnum;

/**
 * Classe representa o privilegio do usuario <br><br>
 *
 * <strong>Historico
 * <ul>
 * <li><strong>@author rsouza
 * @version 1.0 - Data: 15/03/2012</li>
 * </ul>
 *
 * @since 1.0
 *
 */
@Entity
public class Role extends EntidadeBase {

    private String prefixo;
    private String descricao;
    private TipoAcessoEnum tipoAcesso;
    private List<Permissao> permissoes = new ArrayList<Permissao>();

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    /**
     * @return the prefixo
     */
    public String getPrefixo() {
        return prefixo;
    }

    /**
     * @param prefixo the prefixo to set
     */
    public void setPrefixo(String prefixo) {
        this.prefixo = prefixo;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the permissoes
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "RolePermissao", joinColumns =
            @JoinColumn(name = "role_fk"), inverseJoinColumns =
            @JoinColumn(name = "permissao_fk"))
    public List<Permissao> getPermissoes() {
        return permissoes;
    }

    /**
     * @param permissoes the permissoes to set
     */
    public void setPermissoes(List<Permissao> permissoes) {
        this.permissoes = permissoes;
    }

    /**
     * @return the tipoAcesso
     */
    @Enumerated(value = EnumType.STRING)
    public TipoAcessoEnum getTipoAcesso() {
        return tipoAcesso;
    }

    /**
     * @param tipoAcesso the tipoAcesso to set
     */
    public void setTipoAcesso(TipoAcessoEnum tipoAcesso) {
        this.tipoAcesso = tipoAcesso;
    }
}
