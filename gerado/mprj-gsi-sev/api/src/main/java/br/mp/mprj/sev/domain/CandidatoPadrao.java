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
 * CandidatoPadrao
 * A classe CandidatoPadrao representa o mapeamento Objeto-Relacional
 * da entidade de negocio armazenada na tabela SEVO_CANDIDATO_PADRAO.
 *${entity.table.comment}
 */
@Entity
@Table(name = "SEVO_CANDIDATO_PADRAO")
@SequenceGenerator(name = "SEVO_SQ_CAPD_DK", sequenceName = "SEVO_SQ_CAPD_DK")
//@Immutable ?
//@Cacheable
//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@JsonIdentityInfo(generator=JSOGGenerator.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CandidatoPadrao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEVO_SQ_CAPD_DK")
    @Column(name="CAPD_DK" )
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "CAPD_DS_CANDIDATO_PADRAO", nullable = false, length = 50)
    String descricao;

    @Column(name = "CAPD_FOTO")
    @Lob
    @Basic(fetch = FetchType.LAZY)
    byte[] foto;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "candidatoPadrao")
    private List<Candidato> candidatoCollection;


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
     * Getter para descricao.
     * @return valor do campo
     */
    public String getDescricao() {
        return this.descricao;
    }

    /**
     * Setter para descricao.
     * @param value valor para descricao
     */
    public void setDescricao(String value) {
        this.descricao = value;
    }

    /**
     * Getter para foto.
     * @return valor do campo
     */
    public byte[] getFoto() {
        if (this.foto != null) {
            return (byte[]) this.foto.clone();
        }
        return null;
    }

    /**
     * Setter para foto.
     * @param value valor para foto
     */
    public void setFoto(byte[] value) {
        if (value != null) {
            this.foto = (byte[]) value.clone();
        } else {
            this.foto = null;
        }
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
                .append(descricao)
                .append(foto)
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

        CandidatoPadrao rhs = (CandidatoPadrao) obj;

        // For performance reasons we first eliminate @id (primary key) difference
        if (id != null && (!id.equals(rhs.id)) ) {
            return false;
        }
        // so lets check all properties
        return new EqualsBuilder()
                .append(descricao, rhs.descricao)
                .append(foto, rhs.foto)
                .isEquals();

    }

}
