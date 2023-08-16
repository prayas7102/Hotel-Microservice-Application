package com.hotel.service.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "micro_hotel")
public class Hotel {
	@Id
	@Column(name = "ID")
	private String Id;
	@Column(name = "name", length = 25)
	private String name;
	private String location;
	private String about;
}
