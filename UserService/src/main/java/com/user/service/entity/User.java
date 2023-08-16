package com.user.service.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "micro_users")
public class User {
	@Id
	@Column(name = "ID")
	private String userId;
	@Column(name = "name", length = 15)
	private String name;
	private String email;
	private String about;
	@Transient
	private List<Rating> ratings = new ArrayList<>();
}
