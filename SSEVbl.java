import java.util.ArrayList;
import java.util.HashMap;
import edu.rit.pj2.Vbl;

/**
 * Class SSEVbl is a reduction variable shared by multiple threads
 * <P>
 * Each thread finds its own SSE which are all reduced together
 * and the result is stored in the original shared variable 
 *
 * @author  Chandni Pakalapati
 * 	    Priyanka Samanta
 * @version 10-Dec-2015
 */
public class SSEVbl implements Vbl {
	
	HashMap<Integer,ArrayList<Integer>> finalClusterMap;
	Double SSE;
	
	/**
	 * Construct a new sse reduction variable
	 */
	public SSEVbl(){
		this.SSE = Double.MAX_VALUE;
		this.finalClusterMap = new HashMap<Integer,ArrayList<Integer>>();
	}
	
	/**
	 * To find the minimum SSE
	 *
	 * @param  SSEcalculated,pointClusterMap  
	 *
	 * @return  void 
	 */
	public void minSSE(double SSEcalculated,HashMap<Integer,ArrayList<Integer>> pointClusterMap){
		if(SSEcalculated < this.SSE){
			this.SSE = SSEcalculated;
			this.finalClusterMap = pointClusterMap;
		}
	}

	/**
	 * Create a clone of this shared variable.
	 *
	 * @return  The cloned object.
	 */
	public Object clone(){
		SSEVbl vbl = null;
		try {
			vbl = (SSEVbl) super.clone();
			vbl.copy(this);
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return vbl;
	}

	/**
	 * Make this SSEVbl to be a deep copy of the given SSEVbl.
	 *
	 * @param  prime  SSEVbl to copy.
	 *
	 * @return  This SSEVbl.
	 */
	public SSEVbl copy(SSEVbl sse){
		this.SSE = sse.SSE;
		this.finalClusterMap = sse.finalClusterMap;
		return this;
	}

	/**
	 * Set this shared variable to the given shared variable.
	 *
	 * @param  vbl  Shared variable.
	 *
	 * @exception  ClassCastException
	 *     (unchecked exception) Thrown if the class of <TT>vbl</TT> is not
	 *     compatible with the class of this shared variable.
	 */
	public void set(Vbl vbl){
		this.copy(((SSEVbl)vbl));
	}

	/**
	 * Reduce the given shared variable into this shared variable. The two
	 * variables are combined together, and the result is stored in this shared
	 * variable. 
	 *
	 * @param  vbl  Shared variable.
	 *
	 * @exception  ClassCastException
	 *     (unchecked exception) Thrown if the class of <TT>vbl</TT> is not
	 *     compatible with the class of this shared variable.
	 */
	public void reduce(Vbl vbl){
		if(this.SSE.compareTo(((SSEVbl) vbl).SSE) > 0){
			this.SSE = ((SSEVbl)vbl).SSE;
			this.finalClusterMap = ((SSEVbl)vbl).finalClusterMap;
		}
	}

}
