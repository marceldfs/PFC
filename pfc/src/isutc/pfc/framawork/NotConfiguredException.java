package isutc.pfc.framawork;

/**
 * Representa dados impossiveis dado ao logico do sistema
 */
public class NotConfiguredException extends FrameworkException
{
	private Object _data;
	
	/**
	 * Indicar problema geral com a configuracao do sistema, relativo ao dado objecto
     *
	 */
	public NotConfiguredException(String message, Object data)
	{
		this(message, data, "600");
	}
	
	/**
	 * Indicar problema especifico com a configuracao do sistema, relativo ao dado objecto
	 * 
	 * @param data a entidade mal-configurada
	 */
	public NotConfiguredException(String message, Object data, String errorCode)
	{
		super(message, errorCode);
		_data = data;
	}
	
	public String toString()
	{
		return "Invalid configuration, not prepared for: " + _data + " because: " + super.toString();
	}
}

