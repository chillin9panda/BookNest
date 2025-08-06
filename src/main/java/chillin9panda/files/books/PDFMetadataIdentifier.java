package chillin9panda.files.books;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;

public class PDFMetadataIdentifier {
  public static BookMetadatas getPDFMetadata(String path) {
    try {
      PDDocument doc = Loader.loadPDF(new File(path));
      PDDocumentInformation info = doc.getDocumentInformation();
      String title = info.getTitle();
      String author = info.getAuthor();
      doc.close();

      BookMetadatas metadata = new BookMetadatas();
      metadata.setTitle(title);
      metadata.setAuthor(author);
      return metadata;
    } catch (IOException e) {
      throw new IllegalStateException("Failed to get pdf metadata: ", e);
    }
  }

}
