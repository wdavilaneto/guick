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
 * TipoIntegranteComissao
 * A classe TipoIntegranteComissao representa o mapeamento Objeto-Relacional
 * da entidade de negocio armazenada na tabela SEVO_TP_INTEGRANTE_COMISSAO.
 *${entity.table.comment}
 */
@Entity
@Table(name = "SEVO_TP_INTEGRANTE_COMISSAO")
@SequenceGenerator(name = "SEVO_SQ_TICM_DK", sequenceName = "SEVO_SQ_TICM_DK")
//@Immutable ?
//@Cacheable
//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@JsonIdentityInfo(generator=JSOGGenerator.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TipoIntegranteComissao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEVO_SQ_TICM_DK")
    @Column(name="TICM_DK" )
    private Long id;

    @NotNull
    @Size(max = 145)
    @Column(name = "TICM_NM_TP_INTEGRANTE", nullable = false, length = 145)
    String nomeTipoIntegrante;

    @Size(max = 250)
    @Column(name = "TICM_DS_TP_INTEGRANTE", length = 250)
    String descricaoTipoIntegrante;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tipoIntegranteComissao")
    private List<IntegranteComissao> integranteComissaoCollection;


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
     * Getter para nomeTipoIntegrante.
     * @return valor do campo
     */
    public String getNomeTipoIntegrante() {
        return this.nomeTipoIntegrante;
    }

    /**
     * Setter para nomeTipoIntegrante.
     * @param value valor para nomeTipoIntegrante
     */
    public void setNomeTipoIntegrante(String value) {
        this.nomeTipoIntegrante = value;
    }

    /**
     * Getter para descricaoTipoIntegrante.
     * @return valor do campo
     */
    public String getDescricaoTipoIntegrante() {
        return this.descricaoTipoIntegrante;
    }

    /**
     * Setter para descricaoTipoIntegrante.
     * @param value valor para descricaoTipoIntegrante
     */
    public void setDescricaoTipoIntegrante(String value) {
        this.descricaoTipoIntegrante = value;
    }

    /**
     * Getter para integranteComissaoCollection.
     * @return valor do campo
     */
    public List<IntegranteComissao> getIntegranteComissaoCollection() {
        return this.integranteComissaoCollection;
    }

    /**
     * Setter para integranteComissaoCollection.
     * @param value valor para integranteComissaoCollection
     */
    public void setIntegranteComissaoCollection(List<IntegranteComissao> value) {
        this.integranteComissaoCollection = value;
    }



    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(id)
                .append(nomeTipoIntegrante)
                .append(descricaoTipoIntegrante)
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

        TipoIntegranteComissao rhs = (TipoIntegranteComissao) obj;

        // For performance reasons we first eliminate @id (primary key) difference
        if (id != null && (!id.equals(rhs.id)) ) {
            return false;
        }
        // so lets check all properties
        return new EqualsBuilder()
                .append(nomeTipoIntegrante, rhs.nomeTipoIntegrante)
                .append(descricaoTipoIntegrante, rhs.descricaoTipoIntegrante)
                .isEquals();

    }

}
