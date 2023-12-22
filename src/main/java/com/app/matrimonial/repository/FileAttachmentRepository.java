package com.app.matrimonial.repository;

import com.app.matrimonial.model.FileAttachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FileAttachmentRepository extends JpaRepository<FileAttachment,Integer> {

	List<FileAttachment> findByfileId(Integer fileId);

	FileAttachment findByFileId(Integer fileId);

    Optional<FileAttachment> findByFileName(String fileName);

	Optional<FileAttachment> findByRandomId(String randomId);
}
