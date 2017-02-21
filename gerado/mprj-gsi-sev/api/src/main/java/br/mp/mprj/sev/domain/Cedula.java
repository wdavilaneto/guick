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
 * Cedula
 * A classe Cedula representa o mapeamento Objeto-Relacional
 * da entidade de negocio armazenada na tabela SEVO_CEDULA.
 *${entity.table.comment}
 */
@Entity
@Table(name = "SEVO_CEDULA")
@JsonIdentityInfo(generator=JSOGGenerator.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Cedula implements Serializable {

    @Id
    @Column(name="SCED_DK" )
    private String id;

    @NotNull
    @Size(max = 4000)
    @Column(name = "SCED_MSG_CRIPTOGRAFADA", nullable = false, length = 4000)
    String msgCriptografada;

    @NotNull
    @Size(max = 1)
    @Column(name = "SCED_IN_CALCULADA", nullable = false, length = 1)
    String calculada;

    @NotNull
    @Column(name = "SCED_VERSAO", nullable = false, length = 22)
    Long versao;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "SCED_URNA_DK", nullable = false)
    private Urna urna;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "SEVO_VOTO_APURADO", joinColumns = {
            @JoinColumn(name = "VOAP_SCED_DK", nullable = false, updatable = false)
            }, inverseJoinColumns = {
            @JoinColumn(name = "VOAP_SCAD_DK", nullable = false, updatable = false)
            })
    @JsonIgnore
    List<Candidato> candidatoCollection; 


    /**
     * Getter para id.
     * @return valor do campo
     */
    public String getId() {
        return this.id;
    }

    /**
     * Setter para id.
     * @param value valor para id
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Getter para msgCriptografada.
     * @return valor do campo
     */
    public String getMsgCriptografada() {
        return this.msgCriptografada;
    }

    /**
     * Setter para msgCriptografada.
     * @param value valor para msgCriptografada
     */
    public void setMsgCriptografada(String value) {
        this.msgCriptografada = value;
    }

    /**
     * Getter para calculada.
     * @return valor do campo
     */
    public String getCalculada() {
        return this.calculada;
    }

    /**
     * Setter para calculada.
     * @param value valor para calculada
     */
    public void setCalculada(String value) {
        this.calculada = value;
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
     * Getter para urna.
     * @return valor do campo
     */
    public Urna getUrna() {
        return this.urna;
    }

    /**
     * Setter para urna.
     * @param value valor para urna
     */
    public void setUrna(Urna value) {
        this.urna = value;
    }

    /**
     * Getter para candidatoCollection.
     * @return valor do campo
     */
    public List<Candidato> getCandidatoCollection() {
        return this.candidatoCollection;
    }

    /**
     * Setter para candidatoCollection.
     * @param value valor para candidatoCollection
     */
    public void setCandidatoCollection(List<Candidato> value) {
        this.candidatoCollection = value;
    }



    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(id)
                .append(msgCriptografada)
                .append(calculada)
                .append(versao)
                .append(urna)
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

        Cedula rhs = (Cedula) obj;

        // For performance reasons we first eliminate @id (primary key) difference
        if (id != null && (!id.equals(rhs.id)) ) {
            return false;
        }
        // so lets check all properties
        return new EqualsBuilder()
                .append(msgCriptografada, rhs.msgCriptografada)
                .append(calculada, rhs.calculada)
                .append(versao, rhs.versao)
                .append(urna, rhs.urna)
                .isEquals();

    }

}
