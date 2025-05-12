package 
proxy_design_pattern.book_parser;

public class Client {

    public static void main(String[] args) {

        // Without using the proxy
        IBookParser bookParser = new BookParser("Harry-Potter"); // Here the bookParser object got created.
        System.out.println(bookParser.getTotalNumberOfPages());

        // With Proxy
        IBookParser bookParser1 = new BookParserProxy("Harry-Potter");
        System.out.println(bookParser.getTotalNumberOfPages()); // Here the BookParse object got created.

    }
}
