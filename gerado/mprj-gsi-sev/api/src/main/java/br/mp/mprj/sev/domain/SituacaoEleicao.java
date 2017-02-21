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
 * SituacaoEleicao
 * A classe SituacaoEleicao representa o mapeamento Objeto-Relacional
 * da entidade de negocio armazenada na tabela SEVO_SITUACAO_ELEICAO.
 *${entity.table.comment}
 */
@Entity
@Table(name = "SEVO_SITUACAO_ELEICAO")
@SequenceGenerator(name = "SEVO_SQ_SSIE_DK", sequenceName = "SEVO_SQ_SSIE_DK")
//@Immutable ?
//@Cacheable
//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@JsonIdentityInfo(generator=JSOGGenerator.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SituacaoEleicao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEVO_SQ_SSIE_DK")
    @Column(name="SSIE_DK" )
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "SSIE_DS_SITUACAO", nullable = false, length = 50)
    String descricaoSituacao;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "situacaoEleicao")
    private List<Eleicao> eleicaoCollection;


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
     * Getter para descricaoSituacao.
     * @return valor do campo
     */
    public String getDescricaoSituacao() {
        return this.descricaoSituacao;
    }

    /**
     * Setter para descricaoSituacao.
     * @param value valor para descricaoSituacao
     */
    public void setDescricaoSituacao(String value) {
        this.descricaoSituacao = value;
    }

    /**
     * Getter para eleicaoCollection.
     * @return valor do campo
     */
    public List<Eleicao> getEleicaoCollection() {
        return this.eleicaoCollection;
    }

    /**
     * Setter para eleicaoCollection.
     * @param value valor para eleicaoCollection
     */
    public void setEleicaoCollection(List<Eleicao> value) {
        this.eleicaoCollection = value;
    }



    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(id)
                .append(descricaoSituacao)
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

        SituacaoEleicao rhs = (SituacaoEleicao) obj;

        // For performance reasons we first eliminate @id (primary key) difference
        if (id != null && (!id.equals(rhs.id)) ) {
            return false;
        }
        // so lets check all properties
        return new EqualsBuilder()
                .append(descricaoSituacao, rhs.descricaoSituacao)
                .isEquals();

    }

}
