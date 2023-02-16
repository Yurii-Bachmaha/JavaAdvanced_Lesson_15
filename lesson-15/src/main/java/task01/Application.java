package task01;

import java.util.Arrays;
import java.util.HashSet;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class Application {

	public static void main(String[] args) {

		Session session = HibernateSessionFactory.getSessionFactory().openSession();

		Transaction transaction = session.beginTransaction();

		Post post = new Post("Post");

		Comment comment1 = new Comment("Wendy Ball");
		comment1.setPost(post);
		Comment comment2 = new Comment("Sonia Bowen");
		comment2.setPost(post);
		Comment comment3 = new Comment("John Bushell");
		comment3.setPost(post);
		Comment comment4 = new Comment("Jeremy Eaton");
		comment4.setPost(post);

		post.setComments(new HashSet<>(Arrays.asList(comment1, comment2, comment3, comment4)));

		session.persist(post);
		transaction.commit();

		Post postDB = session.get(Post.class, 1);
		System.out.println(postDB + ", has a " + postDB.getComments());

		Comment commentDB = (Comment) session.get(Comment.class, 1);
		System.out.println(commentDB + ", belongs to the " + commentDB.getPost());

		session.close();
	}
}