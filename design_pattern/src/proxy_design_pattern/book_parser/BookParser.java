package 
proxy_design_pattern.book_parser;

public class BookParser implements IBookParser{

    private String bookName;

    public BookParser(String bookName) {
        this.bookName = bookName;
    }

    @Override
    public int getTotalNumberOfPages() {
        return 110; // after doing some processing....
    }
}
