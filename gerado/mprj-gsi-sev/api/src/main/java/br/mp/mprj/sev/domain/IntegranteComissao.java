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
 * IntegranteComissao
 * A classe IntegranteComissao representa o mapeamento Objeto-Relacional
 * da entidade de negocio armazenada na tabela SEVO_INTEGRANTE_COMISSAO.
 *${entity.table.comment}
 */
@Entity
@Table(name = "SEVO_INTEGRANTE_COMISSAO")
@SequenceGenerator(name = "SEVO_SQ_SICM_DK", sequenceName = "SEVO_SQ_SICM_DK")
@JsonIdentityInfo(generator=JSOGGenerator.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class IntegranteComissao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEVO_SQ_SICM_DK")
    @Column(name="SICM_DK" )
    private Long id;

    @NotNull
    @Size(max = 8)
    @Column(name = "SICM_CDMATRICULA", nullable = false, length = 8)
    String cdmatricula;

    @Temporal(TemporalType.DATE)
    @JsonSerialize(using = JsonDateSerializer.class)
    @JsonDeserialize(using = JsonDateDeserializer.class)
    @Column(name = "SICM_DT_VALIDACAO", length = 7)
    Date dataValidacao;

    @NotNull
    @Size(max = 145)
    @Column(name = "SICM_NM_INTEGRANTE_COMISSAO", nullable = false, length = 145)
    String nome;

    @NotNull
    @Size(max = 200)
    @Column(name = "SICM_EMAIL_INTEGRANTE_COMISSAO", nullable = false, length = 200)
    String email;

    @NotNull
    @Size(max = 200)
    @Column(name = "SICM_LOGIN", nullable = false, length = 200)
    String login;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "SICM_SELE_DK", nullable = false)
    private Eleicao eleicao;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "SICM_TICM_DK", nullable = false)
    private TipoIntegranteComissao tipoIntegranteComissao;



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
     * Getter para dataValidacao.
     * @return valor do campo
     */
    public Date getDataValidacao() {
        if (this.dataValidacao != null) {
            return (Date) this.dataValidacao.clone();
        }
        return null;
    }

    /**
     * Setter para dataValidacao.
     * @param value valor para dataValidacao
     */
    public void setDataValidacao(Date value) {
        if (value != null) {
            this.dataValidacao = (Date) value.clone();
        } else {
            this.dataValidacao = null;
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
     * Getter para tipoIntegranteComissao.
     * @return valor do campo
     */
    public TipoIntegranteComissao getTipoIntegranteComissao() {
        return this.tipoIntegranteComissao;
    }

    /**
     * Setter para tipoIntegranteComissao.
     * @param value valor para tipoIntegranteComissao
     */
    public void setTipoIntegranteComissao(TipoIntegranteComissao value) {
        this.tipoIntegranteComissao = value;
    }



    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(id)
                .append(cdmatricula)
                .append(dataValidacao)
                .append(nome)
                .append(email)
                .append(login)
                .append(eleicao)
                .append(tipoIntegranteComissao)
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

        IntegranteComissao rhs = (IntegranteComissao) obj;

        // For performance reasons we first eliminate @id (primary key) difference
        if (id != null && (!id.equals(rhs.id)) ) {
            return false;
        }
        // so lets check all properties
        return new EqualsBuilder()
                .append(cdmatricula, rhs.cdmatricula)
                .append(dataValidacao, rhs.dataValidacao)
                .append(nome, rhs.nome)
                .append(email, rhs.email)
                .append(login, rhs.login)
                .append(eleicao, rhs.eleicao)
                .append(tipoIntegranteComissao, rhs.tipoIntegranteComissao)
                .isEquals();

    }

}
