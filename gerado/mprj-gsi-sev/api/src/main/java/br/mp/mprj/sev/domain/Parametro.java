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
 * Parametro
 * A classe Parametro representa o mapeamento Objeto-Relacional
 * da entidade de negocio armazenada na tabela SEVO_PARAMETRO.
 *${entity.table.comment}
 */
@Entity
@Table(name = "SEVO_PARAMETRO")
@SequenceGenerator(name = "SEVO_SQ_SEPA_DK", sequenceName = "SEVO_SQ_SEPA_DK")
//@Immutable ?
//@Cacheable
//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@JsonIdentityInfo(generator=JSOGGenerator.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Parametro implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEVO_SQ_SEPA_DK")
    @Column(name="SEPA_DK" )
    private Long id;

    @NotNull
    @Size(max = 256)
    @Column(name = "SEPA_DS_PARAMETRO", nullable = false, length = 256)
    String descricao;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parametro")
    private List<EleicaoParametro> eleicaoParametroCollection;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "SEPA_STPP_DK", nullable = false)
    private TipoParametro tipoParametro;



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
     * Getter para tipoParametro.
     * @return valor do campo
     */
    public TipoParametro getTipoParametro() {
        return this.tipoParametro;
    }

    /**
     * Setter para tipoParametro.
     * @param value valor para tipoParametro
     */
    public void setTipoParametro(TipoParametro value) {
        this.tipoParametro = value;
    }

    /**
     * Getter para eleicaoParametroCollection.
     * @return valor do campo
     */
    public List<EleicaoParametro> getEleicaoParametroCollection() {
        return this.eleicaoParametroCollection;
    }

    /**
     * Setter para eleicaoParametroCollection.
     * @param value valor para eleicaoParametroCollection
     */
    public void setEleicaoParametroCollection(List<EleicaoParametro> value) {
        this.eleicaoParametroCollection = value;
    }



    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(id)
                .append(descricao)
                .append(tipoParametro)
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

        Parametro rhs = (Parametro) obj;

        // For performance reasons we first eliminate @id (primary key) difference
        if (id != null && (!id.equals(rhs.id)) ) {
            return false;
        }
        // so lets check all properties
        return new EqualsBuilder()
                .append(descricao, rhs.descricao)
                .append(tipoParametro, rhs.tipoParametro)
                .isEquals();

    }

}
