package isutc.pfc.framawork;

/**
 * Pai de todas as excepcoes causado por mal codificacao ou configuracao nas aplicacoes ISUTC.
 */
public class FrameworkException extends RuntimeException
{
   private String _errorCode;
	
	/**
	 * Construir uma FrameworkException, com a dada mensagem explicativa e codigo
	 * 
	 * @param message mensagem acrescentado melhor explicacao
    * @param errorCode codigo da tabela ONDE
	 */
	public FrameworkException(String message, String errorCode)
	{
		super(message);
      
      _errorCode = errorCode;
	}
   
   /**
    * Construir uma FrameworkException, acusando o dado problema original,
    *  com a dada mensagem explicativa e codigo
    * 
    * @param message mensagem acrescentado melhor explicacao
    * @param cause o problema original
    */
   public FrameworkException(String message, Throwable cause, String errorCode)
   {
      super(message, cause);
      
      _errorCode = errorCode;
   }
   
   //=== implementar ISUTCError ===
   
   public String getErrorCode() { return _errorCode; }
}
