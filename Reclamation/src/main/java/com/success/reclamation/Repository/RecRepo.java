package com.success.reclamation.Repository;

import com.success.reclamation.model.Reclammation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecRepo extends JpaRepository<Reclammation, Long> {

}
