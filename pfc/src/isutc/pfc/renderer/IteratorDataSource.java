package isutc.pfc.renderer;

import java.util.Iterator;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.WrapDynaBean;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class IteratorDataSource implements JRDataSource
{
	 private Iterator _iterator;
	    private DynaBean _current;  //objecto actual
	    
	    public IteratorDataSource(Iterator iterator) 
	    { 
	    	_iterator = iterator; 
	    }
	    
	    public Object getFieldValue(JRField field) throws JRException
	    {
	        try 
	        {
	            return _current.get(field.getName());
	        }
	        catch(Exception e)
	        {
	            e.printStackTrace();  //TODO: LOGGING
	            throw new JRException("Could not get field " + field, e);
	        } 
	    }

	    public boolean next() throws JRException
	    { 
	        if(_iterator.hasNext())
	        {
	            _current = new WrapDynaBean(_iterator.next());
	            return true;
	        }
	        else { return false; }
	    }
}
