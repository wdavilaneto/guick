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
 * Urna
 * A classe Urna representa o mapeamento Objeto-Relacional
 * da entidade de negocio armazenada na tabela SEVO_URNA.
 *Segundo o pedido do glpi 29180 esta tabela não terá auditoria.
 */
@Entity
@Table(name = "SEVO_URNA")
@SequenceGenerator(name = "SEVO_SQ_URNA_DK", sequenceName = "SEVO_SQ_URNA_DK")
//@Immutable ?
//@Cacheable
//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@JsonIdentityInfo(generator=JSOGGenerator.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Urna implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEVO_SQ_URNA_DK")
    @Column(name="URNA_DK" )
    private Long id;

    @Size(max = 4000)
    @Column(name = "URNA_MSG_COMPOSTA", length = 4000)
    String msgComposta;

    @NotNull
    @Column(name = "URNA_VERSAO", nullable = false, length = 22)
    Long versao;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "urna")
    private List<Cedula> cedulaCollection;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "URNA_SELE_DK", nullable = false)
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
     * Getter para msgComposta.
     * @return valor do campo
     */
    public String getMsgComposta() {
        return this.msgComposta;
    }

    /**
     * Setter para msgComposta.
     * @param value valor para msgComposta
     */
    public void setMsgComposta(String value) {
        this.msgComposta = value;
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
     * Getter para cedulaCollection.
     * @return valor do campo
     */
    public List<Cedula> getCedulaCollection() {
        return this.cedulaCollection;
    }

    /**
     * Setter para cedulaCollection.
     * @param value valor para cedulaCollection
     */
    public void setCedulaCollection(List<Cedula> value) {
        this.cedulaCollection = value;
    }



    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(id)
                .append(msgComposta)
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

        Urna rhs = (Urna) obj;

        // For performance reasons we first eliminate @id (primary key) difference
        if (id != null && (!id.equals(rhs.id)) ) {
            return false;
        }
        // so lets check all properties
        return new EqualsBuilder()
                .append(msgComposta, rhs.msgComposta)
                .append(versao, rhs.versao)
                .append(eleicao, rhs.eleicao)
                .isEquals();

    }

}
