package com.chang.recmv.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class TimeEntity {
	@Column(name = "creation_date")
	@CreatedDate
	private String creationDate;

	@Column(name = "update_date")
	@LastModifiedDate
	private String updateDate;

	@PrePersist // 등록 전에 실행
	public void onPrePersist() {
		this.creationDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
		this.updateDate = this.creationDate;
	}

	@PreUpdate // 업데이터 전에 실행
	public void onPreUpdate() {
		this.updateDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
	}
}