package ru.garf.springDB.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ru.garf.springDB.dao.interfaces.MP3DAO;
import ru.garf.springDB.dao.objects.MP3;

public class Start {

	public static void main(String[] args) {
		MP3 mp3 = new MP3();
		mp3.setName("4f");
		mp3.setAuthor("Garfff");

		// new SQLiteDAO().insertWithJDBC(mp3);

		ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
		MP3DAO sqliteDAO = (MP3DAO) context.getBean("sqliteDAO");

		// List<MP3> list = sqliteDAO.getAllMP3();
		// System.out.println(sqliteDAO.getMP3ById(5));
		for (MP3 mp4 : sqliteDAO.getAllMP3()) {
			System.out.println(mp4);
		}
		System.out.println(sqliteDAO.getMP3ListByAuthor("кина"));

	}

}
