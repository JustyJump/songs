package com.justy.media.repo;

import com.justy.media.models.Song;
import org.springframework.data.repository.CrudRepository;


public interface SongRepository extends CrudRepository<Song, Long> {

}
