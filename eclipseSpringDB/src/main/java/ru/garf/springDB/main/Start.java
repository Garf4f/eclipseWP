package ru.garf.springDB.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ru.garf.springDB.dao.impls.SQLiteDAO;
import ru.garf.springDB.dao.objects.MP3;

public class Start {

	public static void main(String[] args) {
		MP3 mp3 = new MP3();
		mp3.setName("4f");
		mp3.setAuthor("Garfff");

		// new SQLiteDAO().insertWithJDBC(mp3);

		ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
		SQLiteDAO sqliteDAO = (SQLiteDAO) context.getBean("sqliteDAO");

		sqliteDAO.delete(1);
		sqliteDAO.delete(2);
		sqliteDAO.delete(3);

	}

}
