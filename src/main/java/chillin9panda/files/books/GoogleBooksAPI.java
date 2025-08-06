package chillin9panda.files.books;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONArray;
import org.json.JSONObject;

public class GoogleBooksAPI {
  public static JSONObject searchBook(String title, String author) {
    try {
      String query = URLEncoder.encode("intitle:" + title + "+inauthor:" + author, "UTF-8");
      String urlString = "https://www.googleapis.com/books/v1/volumes?q=" + query;
      URL url = new URI(urlString).toURL();

      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("GET");

      BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
      StringBuilder response = new StringBuilder();
      String line;
      while (null != (line = in.readLine())) {
        response.append(line);
      }
      in.close();

      JSONObject result = new JSONObject(response.toString());
      return result;
    } catch (Exception e) {
      throw new RuntimeException("Failed to fetch from google books API:", e);
    }
  }

  public static BookMetadatas extractMetadata(JSONObject result) {
    JSONArray items = result.getJSONArray("items");
    BookMetadatas data = new BookMetadatas();

    if (!items.isEmpty()) {
      JSONObject volumeInfo = items.getJSONObject(0).getJSONObject("volumeInfo");

      data.setTitle(volumeInfo.optString("title"));

      JSONArray authorsArray = volumeInfo.optJSONArray("authors");
      if (null != authorsArray && authorsArray.length() > 0) {
        data.setAuthor(String.join(", ", authorsArray.toList().stream().map(Object::toString).toArray(String[]::new)));
      }

      data.setDescription(volumeInfo.optString("description"));
      data.setPublisher(volumeInfo.optString("publisher"));
      data.setPublicationDate(volumeInfo.optString("publicationDate"));
      data.setPageCount(volumeInfo.optInt("pageCount"));

      JSONObject imageLinks = volumeInfo.optJSONObject("imageLinks");
      if (null != imageLinks) {
        data.setThumbnailLink(imageLinks.optString("thumbnail"));
      }

      JSONArray isbns = volumeInfo.optJSONArray("industryIdentifiers");
      if (null != isbns) {
        for (int i = 0; i < isbns.length(); i++) {
          JSONObject isbn = isbns.getJSONObject(i);
          String type = isbn.optString("type");
          if (type.equals("ISBN_13") || type.equals("ISBN_10")) {
            data.setIsbn(isbn.optString("isbn"));
            break;
          }
        }
      }
    }
    return data;
  }
}
