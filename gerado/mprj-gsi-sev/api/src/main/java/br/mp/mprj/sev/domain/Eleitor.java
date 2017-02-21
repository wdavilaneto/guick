/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package br.mp.mprj.sev.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

import javax.validation.constraints.*;

import br.mp.mprj.commons.json.JsonDateDeserializer;
import br.mp.mprj.commons.json.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.annotations.*;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import org.springframework.format.annotation.DateTimeFormat;
//import org.hibernate.annotations.Cache;
//import org.hibernate.annotations.CacheConcurrencyStrategy;


/**
 * Eleitor
 * A classe Eleitor representa o mapeamento Objeto-Relacional
 * da entidade de negocio armazenada na tabela SEVO_ELEITOR.
 *${entity.table.comment}
 */
@Entity
@Table(name = "SEVO_ELEITOR")
@SequenceGenerator(name = "SEVO_SQ_SEIT_DK", sequenceName = "SEVO_SQ_SEIT_DK")
@JsonIdentityInfo(generator=JSOGGenerator.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Eleitor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEVO_SQ_SEIT_DK")
    @Column(name="SEIT_DK" )
    private Long id;

    @NotNull
    @Size(max = 8)
    @Column(name = "SEIT_CDMATRICULA", nullable = false, length = 8)
    String cdmatricula;

    @Temporal(TemporalType.DATE)
    @JsonSerialize(using = JsonDateSerializer.class)
    @JsonDeserialize(using = JsonDateDeserializer.class)
    @Column(name = "SEIT_DT_VOTO", length = 7)
    Date dataVoto;

    @Size(max = 100)
    @Column(name = "SEIT_CD_AUTENTICACAO", length = 100)
    String codigoAutenticacao;

    @Size(max = 100)
    @Column(name = "SEIT_ENDERECO_IP", length = 100)
    String enderecoIp;

    @Column(name = "SEIT_COMPROVANTE_VOTACAO")
    @Lob
    @Basic(fetch = FetchType.LAZY)
    byte[] comprovanteVotacao;

    @NotNull
    @Size(max = 145)
    @Column(name = "SEIT_NM_ELEITOR", nullable = false, length = 145)
    String nome;

    @NotNull
    @Size(max = 200)
    @Column(name = "SEIT_EMAIL", nullable = false, length = 200)
    String email;

    @Size(max = 60)
    @Column(name = "SEIT_DS_CARGO", length = 60)
    String descricaoCargo;

    @NotNull
    @Size(max = 200)
    @Column(name = "SEIT_LOGIN", nullable = false, length = 200)
    String login;

    @Column(name = "SEIT_VERSAO", precision = 12, scale = 0, length = 22)
    Long versao;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "SEIT_SELE_DK", nullable = false)
    private Eleicao eleicao;



    /**
     * Getter para id.
     * @return valor do campo
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Setter para id.
     * @param value valor para id
     */
    public void setId(Long value) {
        this.id = value;
    }

    /**
     * Getter para cdmatricula.
     * @return valor do campo
     */
    public String getCdmatricula() {
        return this.cdmatricula;
    }

    /**
     * Setter para cdmatricula.
     * @param value valor para cdmatricula
     */
    public void setCdmatricula(String value) {
        this.cdmatricula = value;
    }

    /**
     * Getter para dataVoto.
     * @return valor do campo
     */
    public Date getDataVoto() {
        if (this.dataVoto != null) {
            return (Date) this.dataVoto.clone();
        }
        return null;
    }

    /**
     * Setter para dataVoto.
     * @param value valor para dataVoto
     */
    public void setDataVoto(Date value) {
        if (value != null) {
            this.dataVoto = (Date) value.clone();
        } else {
            this.dataVoto = null;
        }
    }

    /**
     * Getter para codigoAutenticacao.
     * @return valor do campo
     */
    public String getCodigoAutenticacao() {
        return this.codigoAutenticacao;
    }

    /**
     * Setter para codigoAutenticacao.
     * @param value valor para codigoAutenticacao
     */
    public void setCodigoAutenticacao(String value) {
        this.codigoAutenticacao = value;
    }

    /**
     * Getter para enderecoIp.
     * @return valor do campo
     */
    public String getEnderecoIp() {
        return this.enderecoIp;
    }

    /**
     * Setter para enderecoIp.
     * @param value valor para enderecoIp
     */
    public void setEnderecoIp(String value) {
        this.enderecoIp = value;
    }

    /**
     * Getter para comprovanteVotacao.
     * @return valor do campo
     */
    public byte[] getComprovanteVotacao() {
        if (this.comprovanteVotacao != null) {
            return (byte[]) this.comprovanteVotacao.clone();
        }
        return null;
    }

    /**
     * Setter para comprovanteVotacao.
     * @param value valor para comprovanteVotacao
     */
    public void setComprovanteVotacao(byte[] value) {
        if (value != null) {
            this.comprovanteVotacao = (byte[]) value.clone();
        } else {
            this.comprovanteVotacao = null;
        }
    }

    /**
     * Getter para nome.
     * @return valor do campo
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Setter para nome.
     * @param value valor para nome
     */
    public void setNome(String value) {
        this.nome = value;
    }

    /**
     * Getter para email.
     * @return valor do campo
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Setter para email.
     * @param value valor para email
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Getter para descricaoCargo.
     * @return valor do campo
     */
    public String getDescricaoCargo() {
        return this.descricaoCargo;
    }

    /**
     * Setter para descricaoCargo.
     * @param value valor para descricaoCargo
     */
    public void setDescricaoCargo(String value) {
        this.descricaoCargo = value;
    }

    /**
     * Getter para login.
     * @return valor do campo
     */
    public String getLogin() {
        return this.login;
    }

    /**
     * Setter para login.
     * @param value valor para login
     */
    public void setLogin(String value) {
        this.login = value;
    }

    /**
     * Getter para versao.
     * @return valor do campo
     */
    public Long getVersao() {
        return this.versao;
    }

    /**
     * Setter para versao.
     * @param value valor para versao
     */
    public void setVersao(Long value) {
        this.versao = value;
    }

    /**
     * Getter para eleicao.
     * @return valor do campo
     */
    public Eleicao getEleicao() {
        return this.eleicao;
    }

    /**
     * Setter para eleicao.
     * @param value valor para eleicao
     */
    public void setEleicao(Eleicao value) {
        this.eleicao = value;
    }



    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(id)
                .append(cdmatricula)
                .append(dataVoto)
                .append(codigoAutenticacao)
                .append(enderecoIp)
                .append(comprovanteVotacao)
                .append(nome)
                .append(email)
                .append(descricaoCargo)
                .append(login)
                .append(versao)
                .append(eleicao)
               .toHashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) {
            return false;
        }

        Eleitor rhs = (Eleitor) obj;

        // For performance reasons we first eliminate @id (primary key) difference
        if (id != null && (!id.equals(rhs.id)) ) {
            return false;
        }
        // so lets check all properties
        return new EqualsBuilder()
                .append(cdmatricula, rhs.cdmatricula)
                .append(dataVoto, rhs.dataVoto)
                .append(codigoAutenticacao, rhs.codigoAutenticacao)
                .append(enderecoIp, rhs.enderecoIp)
                .append(comprovanteVotacao, rhs.comprovanteVotacao)
                .append(nome, rhs.nome)
                .append(email, rhs.email)
                .append(descricaoCargo, rhs.descricaoCargo)
                .append(login, rhs.login)
                .append(versao, rhs.versao)
                .append(eleicao, rhs.eleicao)
                .isEquals();

    }

}
