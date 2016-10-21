package ru.garf.springDB.dao.interfaces;

import java.util.List;
import java.util.Map;

import ru.garf.springDB.dao.objects.MP3;

public interface MP3DAO {

	int insert(MP3 mp3);

	void delete(MP3 mp3);

	void delete(int id);

	MP3 getMP3ById(int id);

	int getMP3Count();

	List<MP3> getMP3ListByName(String name);

	List<MP3> getMP3ListByAuthor(String author);

	List<MP3> getAllMP3();

	Map<String, Integer> getStat();

}
