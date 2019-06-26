package wcci.BlogPlatform;
import java.util.Date;

//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.ManyToOne;



//@Entity
public class Post{
//@ManyToOne
private Author author;

	
@Id
@GeneratedValue
Long id;
String title ;
String body;
String authors;
Date publishDate=new Date();
String genere;
String category;
String tags;
protected Post () {
	
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getBody() {
	return body;
}
public void setBody(String body) {
	this.body = body;
}
public String getAuthors() {
	return authors;
}

public Date getPublishDate() {
	return publishDate;
}
public void setPublishDate(Date publishDate) {
	this.publishDate = publishDate;
}
public String getGenere() {
	return genere;
}
public void setGenere(String genere) {
	this.genere = genere;
}
public String getCategory() {
	return category;
}
public void setCategory(String category) {
	this.category = category;
}
public String getTags() {
	return tags;
}


}
