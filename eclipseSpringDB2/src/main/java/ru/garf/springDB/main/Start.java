package ru.garf.springDB.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ru.garf.springDB.dao.interfaces.MP3DAO;
import ru.garf.springDB.dao.objects.Author;
import ru.garf.springDB.dao.objects.MP3;

public class Start {

	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
		MP3DAO sqliteDAO = (MP3DAO) context.getBean("sqliteDAO");

		MP3 mp3 = new MP3();
		mp3.setName("LalalaLalala");

		Author author = new Author();
		author.setName("Ruslan");

		mp3.setAuthor(author);

		sqliteDAO.insert(mp3);

		for (MP3 mp4 : sqliteDAO.getAllMP3()) {
			System.out.println(mp4);
		}

	}

}
