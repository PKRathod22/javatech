package com.pk.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.envers.RelationTargetAuditMode;

import lombok.Getter;
import lombok.Setter;

@Entity
@Audited
@Setter
@Getter
@Table(name="city")
public class City implements Serializable {
  private static final long serialVersionUID = 1L;
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Long id;
  
  @Column
  String name;
 
  @Column
  String state;
 
  @Column
  @NotAudited
  String country;

  @Column
  @NotAudited
  String map;
  
  @Embedded
  SystemTrack systemTrack;
  
  /*@ManyToOne
  @JoinColumn(name = "contact_id", foreignKey = @ForeignKey(name = "FK_CITYID"))
  @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
  Contact contact;*/
  
  @OneToMany(cascade=CascadeType.ALL)
  @JoinColumn(name = "city_id", foreignKey = @ForeignKey(name = "FK_CITYID"))
  @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
  List<Contact> contact;
  
  
  
}