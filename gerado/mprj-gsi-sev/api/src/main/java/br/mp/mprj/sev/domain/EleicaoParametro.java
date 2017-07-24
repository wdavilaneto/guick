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
 * EleicaoParametro
 * A classe EleicaoParametro representa o mapeamento Objeto-Relacional
 * da entidade de negocio armazenada na tabela SEVO_ELEICAO_PARAMETRO.
 *${entity.table.comment}
 */
@Entity
@Table(name = "SEVO_ELEICAO_PARAMETRO")
@SequenceGenerator(name = "SEVO_SQ_ELPA_DK", sequenceName = "SEVO_SQ_ELPA_DK")
//@Immutable ?
//@Cacheable
//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@JsonIdentityInfo(generator=JSOGGenerator.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class EleicaoParametro implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEVO_SQ_ELPA_DK")
    @Column(name="ELPA_DK" )
    private Long id;

    @NotNull
    @Size(max = 4000)
    @Column(name = "ELPA_VL_PARAMETRO", nullable = false, length = 4000)
    String valorParametro;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "ELPA_SELE_DK", nullable = false)
    private Eleicao eleicao;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "ELPA_SEPA_DK", nullable = false)
    private Parametro parametro;



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
     * Getter para valorParametro.
     * @return valor do campo
     */
    public String getValorParametro() {
        return this.valorParametro;
    }

    /**
     * Setter para valorParametro.
     * @param value valor para valorParametro
     */
    public void setValorParametro(String value) {
        this.valorParametro = value;
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
     * Getter para parametro.
     * @return valor do campo
     */
    public Parametro getParametro() {
        return this.parametro;
    }

    /**
     * Setter para parametro.
     * @param value valor para parametro
     */
    public void setParametro(Parametro value) {
        this.parametro = value;
    }



    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(id)
                .append(valorParametro)
                .append(eleicao)
                .append(parametro)
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

        EleicaoParametro rhs = (EleicaoParametro) obj;

        // For performance reasons we first eliminate @id (primary key) difference
        if (id != null && (!id.equals(rhs.id)) ) {
            return false;
        }
        // so lets check all properties
        return new EqualsBuilder()
                .append(valorParametro, rhs.valorParametro)
                .append(eleicao, rhs.eleicao)
                .append(parametro, rhs.parametro)
                .isEquals();

    }

}
