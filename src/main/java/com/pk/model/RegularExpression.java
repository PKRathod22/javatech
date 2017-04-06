package com.pk.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(
		   name = "regular_expression", 
		   uniqueConstraints = {
				   @UniqueConstraint(columnNames = {"reg_exp_name"}, name="UK_REGEXP_NAME")}
		   
		)
public class RegularExpression {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Long id;

	@Version
	@Column(name = "version_lock")
	long versionLock;
	
	@Column(name="reg_exp_name",length=50, nullable=false)
	String regExpName;
	
	@Column(name="java_reg_exp",length=255, nullable=false)
	String javaRegExp;
	
	@Column(name="js_reg_exp",length=255, nullable=false)
	String jsRegExp;
	
}
