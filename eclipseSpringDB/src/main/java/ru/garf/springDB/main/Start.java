package ru.garf.springDB.main;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ru.garf.springDB.dao.interfaces.MP3DAO;
import ru.garf.springDB.dao.objects.MP3;

public class Start {

	public static void main(String[] args) {
		MP3 track1 = new MP3();
		track1.setName("track1");
		track1.setAuthor("Garf_4f");
		MP3 track2 = new MP3();
		track2.setName("track2");
		track2.setAuthor("Garf_4f");

		List<MP3> list = new ArrayList<MP3>();
		list.add(track1);
		list.add(track2);

		ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
		MP3DAO sqliteDAO = (MP3DAO) context.getBean("sqliteDAO");

		System.out.println(sqliteDAO.insertList(list).length);

		for (MP3 mp4 : sqliteDAO.getAllMP3()) {
			System.out.println(mp4);
		}

	}

}
