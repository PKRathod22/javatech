package com.pk.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Version;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter

public class SystemTrack implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/*Created User Name */
	@Column(name="create_user", length=30, nullable=false)
	String createUser;

	/*Created Date */
	@Column(name="create_date", nullable=false)
	Date createDate;

	/*Last Updated User Name */
	@Column(name="last_updated_user", length=30, nullable=false)
	String lastUpdatedUser;

	/*Last Updated Date */
	@Column(name="last_updated_date", nullable=false)
	Date lastUpdatedDate;

}
