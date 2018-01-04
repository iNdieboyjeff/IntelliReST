package uk.me.jeffsutton.xml.charles;

import org.simpleframework.xml.*;

import java.util.List;

/**
 * <p>Root for a document conforming to the Charles proxy XML session format</p>
 *
 * @author Jeff Sutton
 */
@Root(name="charles-session")
public class CharlesSession {

    @ElementList(name="transaction", required=false, entry="transaction", inline=true)
    List<Transaction> transaction;

    public List<Transaction> getTransaction() {return this.transaction;}
    public void setTransaction(List<Transaction> value) {this.transaction = value;}


}