package za.oo.wethinkcode.api;

import java.util.List;
import za.oo.wethinkcode.domain.Quote;

public interface QuoteDB {
    /**
     * Get a single quote by id.
     * @param id the Id of the quote
     * @return a Quote
     */
    Quote get(Integer id);

    /**
     * Get all quotes in the database
     * @return A list of quotes
     */
    List<Quote> all();

    /**
     * Add a single quote to the database.
     * @param quote the quote to add
     * @return the newly added Quote
     */
    Quote add(Quote quote);
}