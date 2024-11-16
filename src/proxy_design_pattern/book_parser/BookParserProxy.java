package 
proxy_design_pattern.book_parser;

public class BookParserProxy implements IBookParser{

    String bookName;
    private BookParser bookParser;

    public BookParserProxy(String bookName) {
        this.bookName = bookName;
    }

    @Override
    public int getTotalNumberOfPages() {

        // lazy initialization
        if(bookParser == null) {
            bookParser = new BookParser(bookName);
        }
        return bookParser.getTotalNumberOfPages(); // actual call to real object.
    }
}
