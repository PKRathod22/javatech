package com.pk.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import org.hibernate.envers.Audited;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pk.enumeration.LovStatus;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Audited
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "user_contact",uniqueConstraints = {@UniqueConstraint(columnNames = {"contact_email"}, name="UK_CONTACT_EMAIL")
})
public class Contact  implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/*Auto Generation Id*/
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Long id;
	
	@Version
	@Column(name = "version_lock")
	long versionLock;

	@Embedded
	SystemTrack systemTrack;
	
	@Column(name="contact_name", nullable=false, length=100)
	String name;
	
	@Column(name="contact_email", nullable=false, length=100)
	String email;
	
	@Column(name="message", nullable=false, length=1000)
	String message;
	
	@Column(name = "status", nullable = false, length = 10)
	@Enumerated(EnumType.STRING)
	LovStatus status;

}
