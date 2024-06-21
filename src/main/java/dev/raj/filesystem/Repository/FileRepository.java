package dev.raj.filesystem.Repository;

import dev.raj.filesystem.Models.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {

    File save(File file);


    @Override
    Optional<File> findById(Long aLong);
}
