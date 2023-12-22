package com.app.matrimonial.model;

import jakarta.persistence.*;

import lombok.*;


@Getter
@Setter
@Builder
@Entity
@Table(name = "file")
@NoArgsConstructor
@AllArgsConstructor
public class FileAttachment{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "file_generator")
	@SequenceGenerator(name = "file_generator", sequenceName = "file_SEQUENCE", initialValue=1, allocationSize = 1)
	@Column(name = "file_id", nullable = false)
	private Integer fileId;

	@Column(name = "file_name", nullable = false)
	private String fileName;

	@Column(name = "file_type", nullable = false)
	private String fileType;

	@Column(name = "file_size", nullable = false)
	private long fileSize;

	private String fileLink;


	@Lob
	@Column(length = 548414)
	private byte[] data;

	private  String randomId;


}
