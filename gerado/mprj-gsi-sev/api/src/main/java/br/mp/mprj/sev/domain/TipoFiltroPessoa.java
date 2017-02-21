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
 * TipoFiltroPessoa
 * A classe TipoFiltroPessoa representa o mapeamento Objeto-Relacional
 * da entidade de negocio armazenada na tabela SEVO_TP_FILTRO_PESSOA.
 *${entity.table.comment}
 */
@Entity
@Table(name = "SEVO_TP_FILTRO_PESSOA")
@SequenceGenerator(name = "SEVO_SQ_STFP_DK", sequenceName = "SEVO_SQ_STFP_DK")
//@Immutable ?
//@Cacheable
//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@JsonIdentityInfo(generator=JSOGGenerator.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TipoFiltroPessoa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEVO_SQ_STFP_DK")
    @Column(name="STFP_DK" )
    private Long id;

    @Size(max = 50)
    @Column(name = "STFP_DS_TP_FILTRO", length = 50)
    String descricaoTipoFiltro;

    @NotNull
    @Size(max = 4000)
    @Column(name = "STFP_CONSULTA", nullable = false, length = 4000)
    String consulta;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "STFP_SELE_DK", nullable = false)
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
     * Getter para descricaoTipoFiltro.
     * @return valor do campo
     */
    public String getDescricaoTipoFiltro() {
        return this.descricaoTipoFiltro;
    }

    /**
     * Setter para descricaoTipoFiltro.
     * @param value valor para descricaoTipoFiltro
     */
    public void setDescricaoTipoFiltro(String value) {
        this.descricaoTipoFiltro = value;
    }

    /**
     * Getter para consulta.
     * @return valor do campo
     */
    public String getConsulta() {
        return this.consulta;
    }

    /**
     * Setter para consulta.
     * @param value valor para consulta
     */
    public void setConsulta(String value) {
        this.consulta = value;
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
                .append(descricaoTipoFiltro)
                .append(consulta)
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

        TipoFiltroPessoa rhs = (TipoFiltroPessoa) obj;

        // For performance reasons we first eliminate @id (primary key) difference
        if (id != null && (!id.equals(rhs.id)) ) {
            return false;
        }
        // so lets check all properties
        return new EqualsBuilder()
                .append(descricaoTipoFiltro, rhs.descricaoTipoFiltro)
                .append(consulta, rhs.consulta)
                .append(eleicao, rhs.eleicao)
                .isEquals();

    }

}
