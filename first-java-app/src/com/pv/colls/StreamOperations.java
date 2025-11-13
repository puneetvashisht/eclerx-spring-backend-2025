package com.pv.colls;

import java.util.ArrayList;
import java.util.List;


public class StreamOperations {

    static class Book implements Comparable<Book> {
        private long isbn;
        private String title;
        private double rating;
        private double price;
        private String source;

        public Book(long isbn, String title, double rating, double price, String source) {
            this.isbn = isbn;
            this.title = title;
            this.rating = rating;
            this.price = price;
            this.source = source;
        }

        public long getIsbn() {
            return isbn;
        }

        public String getTitle() {
            return title;
        }

        public double getRating() {
            return rating;
        }

        public double getPrice() {
            return price;
        }

        public String getSource() {
            return source;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + (int) (isbn ^ (isbn >>> 32));
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Book other = (Book) obj;
            if (isbn != other.isbn)
                return false;
            return true;
        }

        @Override
        public String toString() {
            return "Book [isbn=" + isbn + ", title=" + title + ", rating=" + rating + ", price=" + price + ", source="
                    + source + "]";
        }

        @Override
        public int compareTo(Book o) {
            return this.title.compareTo(o.title);
        }

    }

    /*
     * // Stream pipeline (a common structure):
     * (a) set-up stream source (~ tables in SQL world)
     * (b) 0 or more intermediate operations (~ WHERE clause) -- lazy
     * & return Stream<T>, i.e., transforms a stream into another stream
     * (c) terminal operation (~ column names) -- eager
     * & return NON-STREAM. Terminates (closes) a stream
     */

    private static void slice(List<Book> books) {
        System.out.println("\nSlice ... ");

        // Print at most 5 DISTINCT books with rating >= 4.5
        // DB world: select distinct (ISBN) from book where rating >= 4.5 limit 0, 5;

    }

    private static void find(List<Book> books) {
        // findFirst needs more work in parallel env. Use findAny if it does the job.
        // java.util.Optional ~
        // (a) to avoid dealing with null -- in case of find,
        // (b) to know if stream is empty -- in case of reduction operation

    }

    // Queries:
    // (a) Is there at least one highly rated book (>= 4.8) that is inexpensive (<=
    // $50)?
    // (b) Do all the books have a rating >= 4.8
    // (c) Check if none of books have bad rating (2.0)?
    private static void match(List<Book> books) {

    }

    // Find the lowest priced book with a rating > 4.5
    private static void reduce(List<Book> books) {

    }

    // collect into different collections
    private static void collectToCollection(List<Book> books) {

    }

    private static void collectToMap(List<Book> books) {

    }

    public static void main(String[] args) {

        List<Book> books = new ArrayList<>();

        books.addAll(DataExtractor.getFromAmazon("java"));
        books.addAll(DataExtractor.getFromBarnesAndNoble("java"));

        slice(books);
        // match(books);
        // find(books);
        // reduce(books);
        // collectToCollection(books);
    }

}
